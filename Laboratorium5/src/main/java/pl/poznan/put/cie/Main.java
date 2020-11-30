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
                "name:(Filtr NOT polaryzacyjny)",
                "name:Lustrzanka description:(autofokus NP-FH50 lekka)",
                "category:Filtry*",
                "name:pokrowce~2",
        };

        String[] queries1 = {
                "name:(Lustrzanka NOT Sony)",
                "name:Lustrzanka AND Sony description: Nieprzeciętna",
                "category:Obi*",
                "name:Czyszczące~2",
        };

        Query q = FloatPoint.newRangeQuery("price", 100.0f, 300.0f);

        for (var el : queries1) { t2.showResultsForQuery(el, hitsPerPage); }
        t2.showResultForRange(q, hitsPerPage);
    }
}


