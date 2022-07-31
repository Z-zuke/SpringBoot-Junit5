import org.testng.Assert;
import org.testng.annotations.Test;

import static org.junit.Assert.*;

public class SubjectTest {
    @Test
    public void shouldReturn0WhenSubjectLifeIsNegative() {
        Subject subject = new Subject("J001", "计算机技术", -2);
        int actualResult = subject.getSubjectLife();
        int expectResult = 0;
        Assert.assertEquals(actualResult, expectResult);
    }

}