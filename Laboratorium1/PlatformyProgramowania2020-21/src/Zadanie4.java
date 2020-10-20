import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Zadanie4 {

    public static void mostPopular() throws IOException {
        System.out.println("Get file name (String): ");
        Scanner scan = new Scanner(System.in);
        var fileName = scan.nextLine();

        String path = "C:\\Users\\Dell\\source\\repos\\PlatformyProgramowania2020-21\\" + fileName + ".txt";

        Files.lines(Paths.get(path))
                .flatMap(Pattern.compile("\\W+")::splitAsStream) //transform text to token with regex
                .filter(s -> s.length() >= 3) //size > 3
                .map(String::toLowerCase) //convert characters to lowercase
                .collect(Collectors.groupingBy(w->w, Collectors.summingInt(w->1)))  //counting words with groups
                .entrySet() //create element in the HashMap
                .stream()  //create sequential stream
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())) //sorting
                .limit(8) //set limit on showed words
                .forEach(e -> System.out.println(e.getKey() + ": " + e.getValue())); //printing
    }
}
