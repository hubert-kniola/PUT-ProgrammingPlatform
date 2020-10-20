public class Pracownik extends Osoba{
    private final Stanowisko stanowisko;
    private final Integer salary;

    public Pracownik(String firstName, String lastName, Stanowisko stanowisko, Integer salary) {
        super(firstName, lastName);

        this.stanowisko = stanowisko;
        this.salary = salary;
    }

    public Integer getSalary() {
        return salary;
    }

    public Stanowisko getStanowisko() {
        return stanowisko;
    }

    @Override
    public String toString() {
        return "Pracownik: { firstName = "+ getFirstName() +
                ", lastName = " + getLastName() +
                ", stanowisko = " + getStanowisko() +
                ", salary=" + getSalary() +
                '}';
    }
}
