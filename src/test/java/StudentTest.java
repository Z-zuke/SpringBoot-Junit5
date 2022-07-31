import org.junit.Assert;
import org.testng.annotations.Test;

import static org.junit.Assert.*;

public class StudentTest {
    @Test
    public void shouldReturn18WhenStudentAge10() {
        Student student = new Student("S10001", "张三", "男", 8);
        int actualResult = student.getStudentAge();
        int expectResult = 18;
        Assert.assertEquals(expectResult, actualResult);
    }

}