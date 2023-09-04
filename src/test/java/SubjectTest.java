import jdk.jfr.Description;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;

import java.util.Arrays;
import org.junit.jupiter.api.Test;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.everyItem;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class SubjectTest {

    @Test
    public void shouldReturn0WhenSubjectLifeIsNegative() {
        Subject subject = new Subject("J001", "计算机技术", -2);
        int actualResult = subject.getSubjectLife();
        int expectResult = 0;
        assertEquals(expectResult, actualResult);
        log.info("shouldReturn0WhenSubjectLifeIsNegative");
    }
}