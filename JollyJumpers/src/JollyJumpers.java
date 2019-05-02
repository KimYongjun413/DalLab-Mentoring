import java.util.Scanner;

public class JollyJumpers {
    public static void main(String[] args) {
        showRequest();
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        System.out.println(isJolly(line));
    }

    public static void showRequest() {
        System.out.println("입력될 수의 갯수와 수열을 입력하세요.(ex : 4 1 4 2 3)");
    }

    public static String isJolly(String line) {

        int[] numbers = stringToInt(line);
        int[] diff = new int[numbers.length];

        return getResult(numbers, diff);
    }

    public static int[] stringToInt(String line) {

        String[] stringNumber = line.split(" ");
        int numbersCount = Integer.parseInt(stringNumber[0]);
        int[] number = new int[numbersCount];

        for(int i=0; i < numbersCount; i++) {
            number[i] = Integer.parseInt(stringNumber[i+1]);
        }
        return number;
    }

    public static String getResult(int[] numbers, int[] diff) {
        diff = setDiff(numbers, diff);
        for(int i=1; i < diff.length ; i++)
            if(diff[i] == 0) {
                return "Not jolly";
            }
        return "Jolly";
    }

    private static int[] setDiff(int[] numbers, int[] diff) {
        for(int i=0; i < numbers.length - 1; i++) {
            diff[getValidNumber(numbers.length, Math.abs(numbers[i] - numbers[i+1]))]++;
        }
        return diff;
    }

    private static int getValidNumber(int numbersLength, int absSub) {
        if(absSub <= numbersLength)
            return absSub;
        return 0;
    }


}
