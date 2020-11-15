package pl.poznan.put.cie.coffee.EntityModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "SUPPLIERS")
public class SupplierElements {

    @Id
    @Column(name = "SUP_ID")
    private Integer id;

    @Column(name = "SUP_NAME")
    private String name;

    @Column(name = "STREET")
    private String street;

    @Column(name = "CITY")
    private String city;

    @Column(name = "STATE")
    private String state;

    @Column(name = "ZIP")
    private String zip;

    public SupplierElements(Integer supId, String name, String street, String city, String state, String zip) {
        this.name = name;
        this.id = supId;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }

    public SupplierElements() { }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) { this.zip = zip;}

    @Override
    public String toString() {
        return "Coffee{" + "name=" + name +
                ", supplierId=" + id +
                ", street=" + street +
                ", city=" + city +
                ", state=" + state +
                ", zip=" + zip +
                '}';
    }
}