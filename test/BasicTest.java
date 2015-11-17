import org.junit.Test;

import play.test.UnitTest;

public class BasicTest extends UnitTest {

    @Test
    public void aVeryImportantThingToTest() {
        assertEquals(2, 1 + 1);
    }

    public static void main(String[] args) {
        String a = "1";
        System.out.println(a.split(",").length);

    }
}
