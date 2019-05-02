import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class JollyJumpersTest {
    JollyJumpers math = new JollyJumpers();

    @Test
    public void IsJolly() {
        assertEquals("Jolly", math.IsJolly("4 1 4 2 3"));
        assertEquals("Jolly", math.IsJolly("1 1"));
        assertEquals("Not jolly", math.IsJolly("2 1 4"));
        assertEquals("Jolly", math.IsJolly("3 -6 -5 -7"));
        assertEquals("Not jolly", math.IsJolly("4 1 1 3 2"));
        assertEquals("Not jolly", math.IsJolly("5 1 4 2 -1 6"));
        assertEquals("Jolly", math.IsJolly("6 11 7 4 2 1 6"));
    }
}
