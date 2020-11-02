
public enum Stanowisko {
    CEO("Dyrektor", 1, "Company Management and Ownership"),
    Manager("Kierownik", 2, "Team Management"),
    Employee("Pracownik",3,"Performs Basic Activities"),
    Trainee("Stazysta",4,"Nothing interesting");

    private final String name;
    private final Integer hierarchy;
    private final String description;

    Stanowisko(String name, Integer hierarchy, String description)
    {
                this.name = name;
                this.hierarchy = hierarchy;
                this.description = description;
    }

    public Integer getHierarchy() {
        return hierarchy;
    }
    public String getDescription() {
        return description;
    }
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Stanowisko{ " +
                "name = " + getName() +
                ", hierarchy = " + getHierarchy() +
                ", description = " + getDescription() +
                '}';
    }
}
