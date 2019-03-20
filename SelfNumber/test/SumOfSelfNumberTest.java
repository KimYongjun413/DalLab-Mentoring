import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.Assert.*;

public class SumOfSelfNumberTest {

    private SumOfSelfNumber ssn;

    @Test
    public void d() {
        //1자리 수
        assertEquals(2,ssn.d(String.valueOf(1)));
        assertEquals(4,ssn.d(String.valueOf(2)));
        assertEquals(18,ssn.d(String.valueOf(9)));
        //2자리 수
        assertEquals(87,ssn.d(String.valueOf(75)));
        assertEquals(22,ssn.d(String.valueOf(20)));
        //3자리 수
        assertEquals(101,ssn.d(String.valueOf(100)));
        assertEquals(105,ssn.d(String.valueOf(102)));
        assertEquals(107,ssn.d(String.valueOf(103)));
        //4자리 수
        assertEquals(1001,ssn.d(String.valueOf(1000)));
        assertEquals(5005,ssn.d(String.valueOf(5000)));
        assertEquals(1244,ssn.d(String.valueOf(1234)));

    }

    @Test
    public void generators() {
        ArrayList test1 = new ArrayList();
        ArrayList test2 = new ArrayList();
        ArrayList test3 = new ArrayList();
        int[] gnrtNum1 = new int[]{2, 4, 6, 8, 10};//5까지
        int[] gnrtNum2 = new int[]{2, 4, 6, 8, 10, 11, 12, 14, 16, 18};//10까지
        int[] gnrtNum3 = new int[]{2, 4, 6, 8, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 21, 22, 23, 25, 27, 29};//20까지
        for(int i = 0; i < gnrtNum1.length; i++){ test1.add(gnrtNum1[i]);}
        for(int i = 0; i < gnrtNum2.length; i++){ test2.add(gnrtNum2[i]);}
        for(int i = 0; i < gnrtNum3.length; i++){ test3.add(gnrtNum3[i]);}
        assertEquals(test1, ssn.generator(1,5));
        assertEquals(test2, ssn.generator(1,10));
        assertEquals(test3, ssn.generator(1,20));
    }

    @Test
    public void selfNumber() {
        ArrayList exPectedResult = new ArrayList();
        int[] sfNum = {1, 3, 5, 7, 9, 20, 31, 42, 53, 64, 75, 86, 97};
        for(int i = 0; i < sfNum.length; i++){ exPectedResult.add(sfNum[i]);}

        ArrayList generator = ssn.generator(1, 100);
        ArrayList selfNumber = ssn.selfNumber(generator, 1, 100);

        assertEquals(exPectedResult, selfNumber);
    }
}