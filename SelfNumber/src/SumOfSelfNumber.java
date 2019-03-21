import java.util.ArrayList;

public class SumOfSelfNumber {
    public static void main(String[] args) {
        System.out.printf("공사 중 입니다.");
    }

    public static int[] generators(int n) {
        ArrayList<Integer> resultList = new ArrayList<>();
        for(int i = 1; i <= n; i++) {
            resultList.add(d(i));
        }
        int [] result = convertIntegers(resultList);
        return result;
    }

    public static int d(int n) {
        int result = n;
        while(n > 0) {
            result += n % 10;
            n = n/10;
        }
        return result;
    }

    public static int[] convertIntegers(ArrayList<Integer> integers)
    {
        int[] intNumbers = new int[integers.size()];
        for (int i=0; i < intNumbers.length; i++) {
            intNumbers[i] = integers.get(i).intValue();
        }
        return intNumbers;
    }
}
