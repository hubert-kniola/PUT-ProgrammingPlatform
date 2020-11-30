package pl.poznan.put.cie;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TopDocs;
import pl.poznan.put.cie.Tools.Item;

import java.io.IOException;

public class Task2 {

    private final StandardAnalyzer analyzer;
    private final IndexSearcher searcher;

    public Task2() throws IOException {
        var context = new Task1();
        context.indexItems();
        var reader = DirectoryReader.open(context.getDirectory()); //open reader
        analyzer = context.getAnalyzer();
        searcher = new IndexSearcher(reader); //searcher
    }

    public void showResultsForQuery(String query, int hitsPerPage) throws IOException {
        var result = executeQuery(query, hitsPerPage); //result
        System.out.printf("\nFound %d for '%s', showing %d.\n", result.totalHits.value, query, hitsPerPage);

        for (var h : result.scoreDocs) {
            var document = searcher.doc(h.doc);
            System.out.println(Item.fromDocument(document).toString());
        }
    }

    public void showResultForRange(Query query, int hitsPerPage) throws IOException {
        var res = searcher.search(query, hitsPerPage);
        System.out.printf("\nFound %d for '%s', showing %d.\n", res.totalHits.value, query, hitsPerPage);

        for (var h : res.scoreDocs)
        {
            var document = searcher.doc(h.doc);
            System.out.println(Item.fromDocument(document).toString());
        }
    }

    public TopDocs executeQuery(String query, int hitsPerPage) {
        try {
            Query q = new QueryParser("name", analyzer).parse(query);
            return searcher.search(q, hitsPerPage);
        } catch (ParseException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
