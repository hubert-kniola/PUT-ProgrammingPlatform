import java.io.IOException;
import java.util.Scanner;

public class Laboratorium2 {
    public static void main(String[] args) throws IOException
    {
        var selectedNumber = 0;
        var firma = new Firma();
        firma.addWorker(new Pracownik("Hubert", "Kniola", Stanowisko.Trainee, 500));
        firma.addWorker(new Pracownik("Jordan", "Kondracki", Stanowisko.Trainee, 600));
        firma.addWorker(new Pracownik("Bartosz", "Kosmala", Stanowisko.Manager, 4000));
        do
        {
            System.out.println("0. Exit | 1. Zadanie 1 | 2. Zadanie 1 | 3. Zadanie 3 | 4. Zadanie 4 | 5. Zadanie 5 | 6. Zadanie 6 | 7. Zadanie 7");
            System.out.println("Select task: ");
            Scanner scan = new Scanner(System.in);
            selectedNumber = scan.nextInt();
        switch (selectedNumber) {
            case 0:
                break;
            case 1:
                var osoba = new Osoba("Hubert", "Kniola");
                System.out.println("Imie: " + osoba.getFirstName() + ", nazwisko: " + osoba.getLastName());
                break;
            case 2:
                var pracownik = new Pracownik("Hubert", "Kniola", null, 5000);
                System.out.println("Imie: " + pracownik.getFirstName() + ", nazwisko: " + pracownik.getLastName() + ", stanowisko: "
                        + pracownik.getStanowisko() + ", pensja: " + pracownik.getSalary());
                break;
            case 3:
                var pracownik1 = new Pracownik("Hubert", "Kniola", Stanowisko.Manager, 5000);
                System.out.format("Imie: %s\nNazwisko: %s\nStanowisko: %s | %s\n",
                        pracownik1.getFirstName(),
                        pracownik1.getLastName(),
                        pracownik1.getStanowisko().getName(),
                        pracownik1.getStanowisko().getDescription());
                break;
            case 4:
                System.out.format("Rozmiar firmy: %s, workers:\n", firma.getSize());
                firma.printWorker();
                break;
            case 5:
                for (Pracownik p : firma)
                    System.out.println(p.getSalary());
                break;
            case 6:
                var iter = firma.iterator(Stanowisko.Manager);
                iter.forEachRemaining(System.out::println);
                break;
            case 7:
                System.out.println("Srednia pensja = " + firma.averageOfSalary());
                System.out.println("Srednia pensja dla danego stanowiska = " + firma.averageOfSalary(Stanowisko.Trainee));
                break;
            default:
                System.out.println("Selected task does not exist!");
        }
        }while(selectedNumber != 0);
    }
}
