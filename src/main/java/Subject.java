/**
 * 专业类
 * @author zheng
 */
public class Subject {
    /**
     * subjectNo: 学科编号
     */
    private String subjectNo;

    /**
     * subjectName: 学科名称
     */
    private String subjectName;

    /**
     * subjectLife: 学制年限
     */
    private int subjectLife;

    public Subject() {}

    public Subject(String subjectNo, String subjectName, int subjectLife) {
        this.setSubjectNo(subjectNo);
        this.setSubjectName(subjectName);
        this.setSubjectLife(subjectLife);
    }


    public String getSubjectNo() {
        return subjectNo;
    }

    public void setSubjectNo(String subjectNo) {
        this.subjectNo = subjectNo;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getSubjectLife() {
        return subjectLife;
    }

    public void setSubjectLife(int subjectLife) {
        if (subjectLife < 0) {
            return;
        }
        this.subjectLife = subjectLife;
    }
}
