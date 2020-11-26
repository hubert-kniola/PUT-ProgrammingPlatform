package pl.poznan.put.cie;

import org.apache.lucene.document.FloatPoint;
import org.apache.lucene.search.Query;

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
        };

        Query q = FloatPoint.newRangeQuery("price", 20.0f, 100.0f);

        for (var el : queries) { t2.showResultsForQuery(el, hitsPerPage); }
        t2.showResultForRange(q, hitsPerPage);
    }
}


