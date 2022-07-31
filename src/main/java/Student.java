/**
 * 学生类
 *
 * @author zheng
 */
public class Student {
    /**
     * studentNo: 学号
     */
    private String studentNo;
    /**
     * studentName: 姓名
     */
    private String studentName;
    /**
     * studentGender: 性别
     */
    private String studentGender;
    /**
     * studentAge: 年龄
     */
    private int studentAge;

    public Student() {}

    public Student(String studentNo, String studentName, String studentGender, int studentAge) {
        this.setStudentNo(studentNo);
        this.setStudentName(studentName);
        this.setStudentGender(studentGender);
        this.setStudentAge(studentAge);
    }


    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentGender() {
        return studentGender;
    }

    public void setStudentGender(String studentGender) {
        this.studentGender = studentGender;
    }

    public int getStudentAge() {
        return studentAge;
    }

    public void setStudentAge(int studentAge) {
        if (studentAge < 10 || studentAge > 100) {
            this.studentAge = 18;
        } else {
            this.studentAge = studentAge;
        }
    }
}
