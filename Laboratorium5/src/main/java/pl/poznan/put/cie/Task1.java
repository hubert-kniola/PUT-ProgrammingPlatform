package pl.poznan.put.cie;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.MMapDirectory;
import pl.poznan.put.cie.Tools.Item;
import pl.poznan.put.cie.Tools.ItemProvider;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Task1 {

    private StandardAnalyzer analyzer;
    private Directory directory;

    public void indexItems() throws IOException {
        analyzer = new StandardAnalyzer();
        directory = new MMapDirectory(getFilePath("IndexedItems"));

        if (directory.listAll().length > 1) return;
        System.out.println("Document indexing...");

        var config = new IndexWriterConfig(analyzer);
        try (IndexWriter writer = new IndexWriter(directory, config)) {
            try (ItemProvider provider = new ItemProvider(getFilePath("items.xml"))) {
                while (provider.hasNext()) {
                    Item item = provider.next();
                    writer.addDocument(item.toDocument());
                }
            } catch (XMLStreamException | IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static Path getFilePath(String fileName) { return Paths.get("src", "main", "resources", fileName); }

    public Directory getDirectory() { return directory; }

    public StandardAnalyzer getAnalyzer() { return analyzer; }
}
