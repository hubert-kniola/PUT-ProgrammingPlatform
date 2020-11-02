public class Osoba {
    private String firstName;
    private String lastName;

    public Osoba(String firstName, String lastName)
    {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String name){
        this.firstName = name;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String name){
        this.lastName = name;
    }
}
