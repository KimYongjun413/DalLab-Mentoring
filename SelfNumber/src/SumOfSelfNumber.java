import java.util.*;

public class SumOfSelfNumber {
    public static void main(String[] args) {
        System.out.print("공사 중 입니다.");
        selfNumbers(5);
    }

    public static int[] generators(int n) {
        ArrayList<Integer> resultList = new ArrayList<>();
        for(int i = 1; i <= n; i++) {
            resultList.add(d(i));
        }
        return convertIntegers(resultList);
    }

    public static int d(int n) {
        int result = n;
        while(n > 0) {
            result += n % 10;
            n = n/10;
        }
        return result;
    }

    public static int[] convertIntegers(ArrayList<Integer> integers) {
        int[] intNumbers = new int[integers.size()];
        for (int i=0; i < intNumbers.length; i++) {
            intNumbers[i] = integers.get(i);
        }
        return intNumbers;
    }

    public static int[] selfNumbers(int n) {
        ArrayList<Integer> selfNumberstList = new ArrayList<>();
        int[] generatorsArray = generators(n);
        for(int i=1; i < n; i++) {
            if( !contains(generatorsArray,i) ) {
                selfNumberstList.add((i));
            }
        }
        return convertIntegers(selfNumberstList);
    }
    public static boolean contains(int[] arr, int item) {
        for (int n : arr) {
            if (item == n) return true;
        }
        return false;
    }
}
