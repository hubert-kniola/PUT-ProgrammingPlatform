import java.util.LinkedList;
import java.util.Scanner;
import java.util.ArrayList;

public class Zadanie1 {

    public void stopWatch()
    {
        System.out.println("Get one number (int): ");
        Scanner scan = new Scanner(System.in);
        var numberOfElements = scan.nextInt();

        //zwykła tablica
        long startTime1 = System.nanoTime();
        int[] normalTable = new int[numberOfElements];
        for(int i=0; i<numberOfElements ;i++)
        {
            normalTable[i] = i;
        }
        long estimatedTime1 = System.nanoTime() - startTime1;
        double estimatedTimeSec1 = (double) estimatedTime1 / 1_000_000_000;
        System.out.println("Esimated time:");
        System.out.println("normalTable [ns]: "+ estimatedTime1);
        System.out.println("normalTable [s]: "+ estimatedTimeSec1);

        //arrayList
        long startTime2 = System.nanoTime();
        ArrayList<Integer> arrayList1 = new ArrayList<Integer>();
        for(int i=0; i<numberOfElements; i++)
        {
            arrayList1.add(i);
        }
        long estimatedTime2 = System.nanoTime() - startTime2;
        double estimatedTimeSec2 = (double) estimatedTime2 / 1_000_000_000;
        System.out.println("arrayList [ns]: " + estimatedTime2);
        System.out.println("arrayList: [s]: " + estimatedTimeSec2);

        //arrayList - ze znana wielkością
        long startTime3 = System.nanoTime();
        ArrayList<Integer> arrayList2 = new ArrayList<Integer>(numberOfElements);
        for(int i=0; i<numberOfElements; i++)
        {
            arrayList2.add(i);
        }
        long estimatedTime3 = System.nanoTime() - startTime3;
        System.out.println("arrayList (with the size from the beginning)[ns]: "+ estimatedTime3);
        double estimatedTimeSec3 = (double) estimatedTime3 / 1_000_000_000;
        System.out.println("arrayList (with the size from the beginning)[s]: " + estimatedTimeSec3);

        //linkedList
        long startTime4 = System.nanoTime();
        LinkedList<Integer> linkList = new LinkedList<Integer>();
        for(int i=0; i<numberOfElements; i++)
        {
            linkList.add(i);
        }
        long estimatedTime4 = System.nanoTime() - startTime4;
        System.out.println("linkedList [ns]: "+ estimatedTime4);
        double estimatedTimeSec4 = (double) estimatedTime4 / 1_000_000_000;
        System.out.println("linkedList [s]: "+ estimatedTimeSec4);
    }
}
