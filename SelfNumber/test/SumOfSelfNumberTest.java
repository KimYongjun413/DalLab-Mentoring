import org.junit.Test;

import static org.junit.Assert.*;

public class SumOfSelfNumberTest {

    private SumOfSelfNumber naturalNumbers;

    @Test
    public void generators() {
        assertArrayEquals(new int[]{2}, naturalNumbers.generators(1));
        assertArrayEquals(new int[]{2, 4, 6, 8}, naturalNumbers.generators(4));
        assertArrayEquals(new int[]{2, 4, 6, 8, 10}, naturalNumbers.generators(5));
    }

    @Test
    public void d() {
        assertEquals(2, naturalNumbers.d(1));
        assertEquals(10, naturalNumbers.d(5));
        assertEquals(107, naturalNumbers.d(103));
        assertEquals(5030, naturalNumbers.d(4999));
    }

    @Test
    public void selfNumbers() {
        assertArrayEquals(new int[] {1}, naturalNumbers.selfNumbers(2));
        assertArrayEquals(new int[] {1, 3}, naturalNumbers.selfNumbers(5));
        assertArrayEquals(new int[] {1, 3, 5, 7, 9}, naturalNumbers.selfNumbers(10));
        assertArrayEquals(new int[] {1, 3, 5, 7, 9, 20, 31, 42}, naturalNumbers.selfNumbers(50));
        assertArrayEquals(new int[] {1, 3, 5, 7, 9, 20, 31, 42, 53, 64, 75, 86, 97}, naturalNumbers.selfNumbers(100));
    }

    @Test
    public void contains() {
        assertTrue(naturalNumbers.contains(new int[] {1, 2, 3, 4}, 1));
        assertTrue(naturalNumbers.contains(new int[] {1, 2, 3, 4}, 4));

        assertTrue(naturalNumbers.contains(naturalNumbers.selfNumbers(5), 1));
        assertTrue(naturalNumbers.contains(naturalNumbers.selfNumbers(5), 3));
        assertTrue(naturalNumbers.contains(naturalNumbers.selfNumbers(50), 31));
        assertTrue(naturalNumbers.contains(naturalNumbers.selfNumbers(50), 42));
    }

    @Test
    public void sum() {
        assertEquals(10, naturalNumbers.sum(new int[] {2,8}));
        assertEquals(15, naturalNumbers.sum(new int[] {1,2,3,4,5}));
        assertEquals(55, naturalNumbers.sum(new int[] {1,2,3,4,5,6,7,8,9,10}));

        assertEquals(1, naturalNumbers.sum(naturalNumbers.selfNumbers(2)));
        assertEquals(4, naturalNumbers.sum(naturalNumbers.selfNumbers(5)));
        assertEquals(493, naturalNumbers.sum(naturalNumbers.selfNumbers(100)));
    }
}
