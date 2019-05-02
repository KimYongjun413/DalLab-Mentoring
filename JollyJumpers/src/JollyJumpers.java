import java.util.Scanner;

public class JollyJumpers {
    public static void main(String[] args) {
        ShowRequest();
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        System.out.println(IsJolly(line));
    }

    public static void ShowRequest() {
        System.out.println("입력될 수의 갯수와 수열을 입력하세요.(ex : 4 1 4 2 3)");
    }

    public static String IsJolly(String line) {

        int[] numbers = StringToInt(line);
        int[] diff = new int[numbers.length];

        return getResult(numbers, diff);
    }

    public static int[] StringToInt(String line) {

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
            if(diff[i] == 0)  return "Not jolly";
        return "Jolly";
    }

    private static int[] setDiff(int[] numbers, int[] diff) {
        int absSub = 0;
        for(int i=0; i < numbers.length - 1; i++) {
            absSub = Math.abs(numbers[i] - numbers[i+1]);
            if(absSub > numbers.length) continue;
            diff[absSub]++;
        }
        return diff;
    }


}
