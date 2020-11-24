package pl.poznan.put.cie;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        var t2 = new Task2();
        System.out.print("Enter how many items are to be displayed: ");
        Scanner scan = new Scanner(System.in);
        int hitsPerPage = scan.nextInt();

        String[] queries = {
                "name:(Lustrzanka NOT Sony)",
                "name:Lustrzanka description:(autofokus NP-FH50 lekka)",
                "category:Lampy*",
                "name:pokrowce~2",
                "price:[0.0 TO 1000.0]"
        };

        for (var q : queries) { t2.showResultsForQuery(q, hitsPerPage); }
    }
}
