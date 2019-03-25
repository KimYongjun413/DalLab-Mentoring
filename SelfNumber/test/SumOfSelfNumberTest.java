import org.junit.Test;

import static org.junit.Assert.*;

public class SumOfSelfNumberTest {

    private SumOfSelfNumber math;

    @Test
    public void generators() {
        assertArrayEquals(new int[]{2}, math.generators(1));
        assertArrayEquals(new int[]{2, 4, 6, 8}, math.generators(4));
        assertArrayEquals(new int[]{2, 4, 6, 8, 10}, math.generators(5));
    }

    @Test
    public void d() {
        assertEquals(2, math.d(1));
        assertEquals(10, math.d(5));
        assertEquals(107, math.d(103));
        assertEquals(5030, math.d(4999));
    }

    @Test
    public void selfNumbers() {
        assertArrayEquals(new int[] {1}, math.selfNumbers(2));
        assertArrayEquals(new int[] {1, 3}, math.selfNumbers(5));
        assertArrayEquals(new int[] {1, 3, 5, 7, 9}, math.selfNumbers(10));
        assertArrayEquals(new int[] {1, 3, 5, 7, 9, 20, 31, 42}, math.selfNumbers(50));
        assertArrayEquals(new int[] {1, 3, 5, 7, 9, 20, 31, 42, 53, 64, 75, 86, 97}, math.selfNumbers(100));
    }

    @Test
    public void contains() {
        assertTrue(math.contains(new int[] {1, 2, 3, 4}, 1));
        assertTrue(math.contains(new int[] {1, 2, 3, 4}, 4));

        assertTrue(math.contains(math.selfNumbers(5), 1));
        assertTrue(math.contains(math.selfNumbers(5), 3));
        assertTrue(math.contains(math.selfNumbers(50), 31));
        assertTrue(math.contains(math.selfNumbers(50), 42));
    }

    @Test
    public void sum() {
        assertEquals(10, math.sum(new int[] {2,8}));
        assertEquals(15, math.sum(new int[] {1,2,3,4,5}));
        assertEquals(55, math.sum(new int[] {1,2,3,4,5,6,7,8,9,10}));

        assertEquals(1, math.sum(math.selfNumbers(2)));
        assertEquals(4, math.sum(math.selfNumbers(5)));
        assertEquals(493, math.sum(math.selfNumbers(100)));
    }
}
