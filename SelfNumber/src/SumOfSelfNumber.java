import java.util.ArrayList;
import java.util.Scanner;

public class SumOfSelfNumber {
    public static void main(String[] args) {
        System.out.print("자연수 몇 까지 Self Number의 합을 구할까요? : ");
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        System.out.println("\nSelf Number의 합은 " + sum(selfNumbers(number)) + "입니다.");
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
        ArrayList<Integer> selfNumbersList = new ArrayList<>();
        int[] generatorsArray = generators(n);
        for(int i=1; i < n; i++) {
            if( !contains(generatorsArray,i) ) {
                selfNumbersList.add((i));
            }
        }
        return convertIntegers(selfNumbersList);
    }
    public static boolean contains(int[] array, int item) {
        for (int number : array) {
            if (item == number) return true;
        }
        return false;
    }

    public static int sum(int[] array) {
        int result = 0;
        for(int number : array) {
            result += number;
        }
        return result;
    }
}
