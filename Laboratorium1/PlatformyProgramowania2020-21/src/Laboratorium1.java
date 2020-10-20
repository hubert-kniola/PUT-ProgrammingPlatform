import java.io.IOException;
import java.util.Scanner;

public class Laboratorium1 {
    public static void main(String[] args) throws IOException {
        System.out.println("0. Exit\n1. Zadanie 1 - Creating lists\n2. Zadanie 2 - Counting factorials\n3. Zadanie 3 - Counting unique characters\n4. Zadanie 4 - 8 most popular expressions\n5. Zadanie 5 - Sorting\n");
        System.out.println("Select task: ");

        Scanner scan = new Scanner(System.in);
        var selectedNumber = scan.nextInt();
            switch (selectedNumber) {
                case 0:
                    break;
                case 1:
                    Zadanie1 zad1 = new Zadanie1();
                    zad1.stopWatch();
                    break;
                case 2:
                    Zadanie2 zad2 = new Zadanie2();
                    zad2.makeCalculation();
                    break;
                case 3:
                    Zadanie3 zad3 = new Zadanie3();
                    System.out.println("Unique words: " + zad3.uniqueCount());
                    break;
                case 4:
                    Zadanie4 zad4 = new Zadanie4();
                    zad4.mostPopular();
                    break;
                case 5:
                    Zadanie5 zad5 = new Zadanie5();
                    zad5.measureTime();
                    break;
                default:
                    System.out.println("Selected task does not exist!");
            }
    }
}
