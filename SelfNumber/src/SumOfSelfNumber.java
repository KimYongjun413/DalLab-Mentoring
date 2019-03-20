import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class SumOfSelfNumber {
    public static void main(String[] args) {

        System.out.printf("자연수 몇 까지 Self Number의 합을 구할까요? : ");
        Scanner sc = new Scanner(System.in);
        int iTo = sc.nextInt();

        ArrayList generatorList = generator(1,iTo);
        showGenerator(generatorList);

        showSelfNumber(selfNumber(generatorList, 1, iTo));

    }

    public static int d(String n) {
        int result = 0;
        char[] charNumbers = n.toCharArray();

        for(int i=0; i< charNumbers.length; i++) {
            result += Integer.parseInt(String.valueOf(charNumbers[i]));
        }
        result += Integer.parseInt(n);

        return result;
    }

    public static ArrayList generator(int iFrom, int iTo) {
        ArrayList generatorList = new ArrayList();

        for(int i=iFrom; i<=iTo; i++) {
            generatorList.add(d(String.valueOf(i)));
        }

        return deDuplicationAndSort(generatorList);
    }

    public static ArrayList selfNumber(ArrayList generatorList, int iFrom, int iTo) {
        ArrayList selfNumberList = new ArrayList();

        for(int i=iFrom; i<=iTo; i++) {
            if(!generatorList.contains(i)){
                selfNumberList.add(i);
            }
        }
        return deDuplicationAndSort(selfNumberList);
    }

    public static void showSelfNumber(ArrayList selfNumberList) {
        System.out.printf("\nSelf Number : ");
        for(int i=0; i<selfNumberList.size(); i++) {
            System.out.printf("%d, ",selfNumberList.get(i));
        }
    }

    public static void showGenerator(ArrayList generatorList) {
        System.out.printf("\nGenerators(Deduplication And Sorted) : ");
        for(int i=0; i<generatorList.size(); i++) {
            System.out.printf("%d, ",generatorList.get(i));
        }
    }

    public static ArrayList deDuplicationAndSort(ArrayList arrayList) {
        ArrayList resultList = new ArrayList<String>();
        for (int i = 0; i < arrayList.size(); i++) {
            if (!resultList.contains(arrayList.get(i))) {
                resultList.add(arrayList.get(i));
            }
        }
        Collections.sort(resultList);
        return resultList;
    }

}
