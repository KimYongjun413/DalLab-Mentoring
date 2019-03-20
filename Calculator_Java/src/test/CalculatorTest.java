import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CalculatorTest extends Calculator {
    //delta - the maximum delta between expected and actual for which both numbers are still considered equal.
    private static final double DELTA = 1e-15;
    private Calculator cal;

    @Before
    public void setup() {
        cal = new Calculator();
        //System.out.println("setup");
    }

    @Test
    public void postFix() {
        assertEquals("0.03 2 +", cal.postFix("0.03 + 2"));
        assertEquals("0.3 2 +", cal.postFix("0.3 + 2"));
        assertEquals("1000 2 +", cal.postFix("1,000 + 2"));
        assertEquals("1000.0 2 +", cal.postFix("1,000.0 + 2"));

        assertEquals("31 2 +", cal.postFix("31 + 2"));
        assertEquals("11 2 * 3 +", cal.postFix("11 * 2 + 3"));
        assertEquals("1 2 * 3 4 * +", cal.postFix("1 * 2 + 3 * 4"));
        assertEquals("1 2 + 3 4 + *", cal.postFix("(1 + 2) * (3 + 4)"));
        assertEquals("5 15 2 5 * 3 - 54 2 / * + * 123 * 30 50 * 1500 / -", cal.postFix("5*(15+(2*5-3)*(54/2))*123-30*50/1500"));
        assertEquals("5 15 2 5 * 3 - + *", cal.postFix("5*(15+(2*5-3))"));

        assertEquals("0", cal.postFix("5*abcd"));
        assertEquals("2.0 1.1 -", cal.postFix("2.0-1.1"));
        assertEquals("2.0000 1.0010 -", cal.postFix("2.0000-1.0010"));
    }

    @Test
    public void calculation() {
        BinaryTree bt = new BinaryTree();
        assertEquals( String.valueOf(2.03),bt.calculation(bt.binaryTreeNode(postFix("0.03+2"))));
        assertEquals( String.valueOf(2.3),bt.calculation(bt.binaryTreeNode(postFix("0.3+2"))));
        assertEquals( String.valueOf(1002.0),bt.calculation(bt.binaryTreeNode(postFix("1,000 + 2"))));
        assertEquals( String.valueOf(1002.0),bt.calculation(bt.binaryTreeNode(postFix("1,000.0 + 2"))));

        assertEquals( String.valueOf(33.0),bt.calculation(bt.binaryTreeNode(postFix("31 + 2"))));
        assertEquals( String.valueOf(25.0),bt.calculation(bt.binaryTreeNode(postFix("11 * 2 + 3"))));
        assertEquals( String.valueOf(14.0),bt.calculation(bt.binaryTreeNode(postFix("1 * 2 + 3 * 4"))));
        assertEquals( String.valueOf(21.0),bt.calculation(bt.binaryTreeNode(postFix("(1 + 2) * (3 + 4)"))));
        assertEquals( String.valueOf(125459.0),bt.calculation(bt.binaryTreeNode(postFix("5*(15+(2*5-3)*(54/2))*123-30*50/1500"))));
        assertEquals( String.valueOf(110.0),bt.calculation(bt.binaryTreeNode(postFix("5*(15+(2*5-3))"))));

        assertEquals( String.valueOf(0),bt.calculation(bt.binaryTreeNode(postFix("5*a"))));
        assertEquals( String.valueOf(0),bt.calculation(bt.binaryTreeNode(postFix("5*+"))));
        assertEquals( String.valueOf(0),bt.calculation(bt.binaryTreeNode(postFix("5*!!"))));
        assertEquals( String.valueOf(0.9),bt.calculation(bt.binaryTreeNode(postFix("2.0 - 1.1"))));
        assertEquals( String.valueOf(0),bt.calculation(bt.binaryTreeNode(postFix("2.A-1.001"))));

    }

    @Test
    public void add() {
        assertEquals("2.03", cal.add("0.03", "2"));
        assertEquals("2.0", cal.add("1", "1"));
        assertEquals("0.0", cal.add("-1", "1"));
        assertEquals("-2.0", cal.add("-1", "-1"));
        assertEquals("0.0", cal.add("1", "-1"));
    }

    @Test
    public void subtract() {
        assertEquals("0.9", cal.subtract("2.0", "1.1"));
        assertEquals("0", cal.subtract("1", "1"));
        assertEquals("-2", cal.subtract("-1", "1"));
        assertEquals("0", cal.subtract("-1", "-1"));
        assertEquals("2", cal.subtract("1", "-1"));
        assertEquals("0.1", cal.subtract("1", "0.9"));
    }

    @Test
    public void multiply() {
        assertEquals("0.0", cal.multiply("0", "0"));
        assertEquals("0.0", cal.multiply("2", "0"));
        assertEquals("0.06", cal.multiply("2", "0.030"));
        assertEquals("0.1", cal.multiply("1", "0.1"));
    }


    @Test
    public void divide() {
        assertEquals("1.0", cal.divide("3", "3"));
        assertEquals("5.0", cal.divide("1", "0.2"));
        assertEquals("0.5", cal.divide("1", "2"));
        assertEquals("0.3333333333333333", cal.divide("1", "3"));
        assertEquals("0", cal.divide("0", "0"));
        assertEquals("infinity", cal.divide("1", "0"));
        assertEquals("-infinity", cal.divide("-1", "0"));
        assertEquals("0.1", cal.divide("1", "10"));
    }

    @After
    public void teardown() {
        //System.out.println("teardown");
    }
}
