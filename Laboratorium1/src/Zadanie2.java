import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Scanner;

public class Zadanie2 {

    public static BigInteger iterFactorial( int n )
    {
        BigInteger result = BigInteger.ONE;
        for (int i = n; i > 0; i--) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }

    public void makeCalculation()
    {
        System.out.println("Get one number (int): ");
        Scanner scan = new Scanner(System.in);
        var numberOfElements = scan.nextInt();

        long startTime = System.nanoTime();
        BigInteger resultNumber = iterFactorial(numberOfElements);
        long estimatedTime = System.nanoTime() - startTime;
        double estimatedTimeSec = (double) estimatedTime / 1_000_000_000;
        NumberFormat numFormat = new DecimalFormat();
        numFormat = new DecimalFormat("0.######E0"); //scientific notation format
        System.out.println("Estimated time [ns]: "+ estimatedTime);
        System.out.println("Estimated time [s]: "+ estimatedTimeSec);
        System.out.println("Result of factorial (Normal Notation): " + resultNumber);
        System.out.println("Result of factorial (Scientific Notation): " + numFormat.format(resultNumber));
        //Long range [-9 223 372 036 854 775 808, +9 223 372 036 854 775 807].
        //BigInteger is designed to represent ginormous numbers, not designed for efficiency.
    }
}
