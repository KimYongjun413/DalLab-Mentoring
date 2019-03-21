
public class SumOfSelfNumber {
    public static void main(String[] args) {
        System.out.printf("공사 중 입니다.");
    }

    public static int d(int n) {
        int result = n;
        while(n > 0) {
            result += n % 10;
            n = n/10;
        }
        return result;
    }


}
