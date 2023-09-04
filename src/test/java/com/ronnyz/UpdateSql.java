package com.ronnyz;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * The class description.
 *
 * @author Ronnyz
 * @since 2023/9/3
 */
class UpdateSql {
    // 数据库连接
    private static final String DB_URL = "jdbc:h2:file:./testdb";
    private static final String DB_USER = "";
    private static final String DB_PASSWORD = "";
    private static final String DB_DRIVER = "org.h2.Driver";

    // data.sql文件路径
    private static final String DATA_SQL_PATH = "src/test/resources/data2.sql";

    public static void main(String[] args) throws Exception {
        updateAll();
        updateBatch();
    }

    /**
     * 更新脚本中的所有数据
     *
     * @throws Exception
     */
    private static void updateAll() throws Exception {
        List<Integer> targetIdList = Arrays.asList(1, 2, 3, 5);
        String tableName = "user";
        String querySql = "SELECT * FROM `" + tableName + "` WHERE id = ?";

        // 创建数据库连接
        Connection conn = getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        // 处理查询结果，写入文件
        try (PrintWriter writer = new PrintWriter(DATA_SQL_PATH)) {
            for (int id : targetIdList) {
                ps = conn.prepareStatement(querySql);
                ps.setInt(1, id);
                rs = ps.executeQuery();

                String insertSql = getInsertSql(rs, tableName);
                writer.print(insertSql);
            }
            writer.flush();
        }

        // 关闭连接
        closeResource(conn, ps, rs);
    }

    /**
     * 更新脚本中的部分数据，已存在的删除，然后插入所有新增数据
     *
     * @throws Exception
     */
    private static void updateBatch() throws Exception {
        Set<Integer> addIdList = new HashSet<>(Arrays.asList(2, 4, 6));
        String tableName = "user";
        String querySql = "SELECT * FROM `" + tableName + "` WHERE id = ?";

        // 创建数据库连接
        Connection conn = getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        // 读取现有data.sql中的数据，并删除需要更新的旧数据
        List<String> existingSqls = readExistingSql(DATA_SQL_PATH, addIdList);

        // 处理查询结果，写入文件
        try (PrintWriter writer = new PrintWriter(DATA_SQL_PATH)) {
            // 写入原有的数据
            for (String existsSql : existingSqls) {
                writer.println(existsSql);
            }
            // 写入新添加的数据
            for (int id : addIdList) {
                ps = conn.prepareStatement(querySql);
                ps.setInt(1, id);
                rs = ps.executeQuery();

                String insertSql = getInsertSql(rs, tableName);
                writer.print(insertSql);
            }
            writer.flush();
        }

        // 关闭连接
        closeResource(conn, ps, rs);
    }

    private static List<String> readExistingSql(String fileName, Set<Integer> excludeIds) throws Exception {
        List<String> existingSqls = new LinkedList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                boolean isContain = false;
                for (int id : excludeIds) {
                    if (line.contains(String.valueOf(id))) {
                        isContain = true;
                        break;
                    }
                }
                if (!isContain) {
                    existingSqls.add(line);
                }
            }
        }
        return existingSqls;
    }

    private static String getInsertSql(ResultSet rs, String table) throws SQLException {
        // 获取列名
        ResultSetMetaData md = rs.getMetaData();
        int colCount = md.getColumnCount();
        String[] colNames = new String[colCount];
        for (int i = 1; i <= colCount; i++) {
            colNames[i - 1] = md.getColumnName(i);
        }

        // 构建insert语句
        StringBuilder sb = new StringBuilder();
        while (rs.next()) {
            sb.append("INSERT INTO `").append(table).append("` (");
            for (String col : colNames) {
                sb.append(col).append(", ");
            }
            sb.setLength(sb.length() - 2); // 删除最后的", "
            sb.append(") VALUES (");

            for (int i = 1; i <= colCount; i++) {
                Object value = rs.getObject(i);
                if (value != null) {
                    sb.append("'").append(value).append("', ");
                } else {
                    sb.append("null").append(", ");
                }
            }
            sb.setLength(sb.length() - 2); // 删除最后的", "
            sb.append(");\n");
        }
        return sb.toString();
    }

    private static Connection getConnection() throws Exception {
        Class.forName(DB_DRIVER);
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    private static void closeResource(Connection conn, PreparedStatement ps, ResultSet rs) {
        try (conn; ps; rs) {
            // 无需手动关闭资源，try-with-resources会自动关闭
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}