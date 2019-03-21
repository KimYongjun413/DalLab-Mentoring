import org.junit.Test;

import static org.junit.Assert.*;

public class SumOfSelfNumberTest {

    private SumOfSelfNumber naturalNumbers;

    @Test
    public void d() {
        assertEquals(2, naturalNumbers.d(1));
        assertEquals(10, naturalNumbers.d(5));
        assertEquals(107, naturalNumbers.d(103));
        assertEquals(5030, naturalNumbers.d(4999));
    }

}
