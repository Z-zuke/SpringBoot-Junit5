import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class StudentTest {
    @Test
    public void shouldReturn18WhenStudentAge10() {
        Student student = new Student("S10001", "张三", "男", 8);
        int actualResult = student.getStudentAge();
        int expectResult = 18;
        assertEquals(expectResult, actualResult);
        log.info("shouldReturn18WhenStudentAge10");
    }
}