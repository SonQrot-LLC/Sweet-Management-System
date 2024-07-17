package sweet.management;

import io.cucumber.junit.platform.engine.Cucumber;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Cucumber
class TempClassTest extends TempClass {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testAdd() {
        assert (TempClass.add(5, 6) == 11);
    }
}