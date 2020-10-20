import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

public class Zadanie3 {

    public static long uniqueCount() throws IOException {
        System.out.println("Get file name (String): ");
        Scanner scan = new Scanner(System.in);
        var fileName = scan.nextLine();

        String path = "C:\\Users\\Dell\\source\\repos\\PlatformyProgramowania2020-21\\" + fileName + ".txt";

        return Files.lines(Paths.get(path)) //reading text, finding path
                .flatMap(x -> Arrays.stream(x.toLowerCase().split("\\W+"))) //transform text to token
                .distinct() //only unique words
                .filter(x -> x.length() >= 3) //size > 3
                .count(); //counting
    }
}
