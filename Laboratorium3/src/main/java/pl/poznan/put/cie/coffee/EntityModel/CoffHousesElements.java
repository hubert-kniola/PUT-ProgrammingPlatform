package pl.poznan.put.cie.coffee.EntityModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "COFFEE_HOUSES")
public class CoffHousesElements {
    @Id
    @Column(name = "STORE_ID")
    private Integer id;

    @Column(name = "CITY")
    private String city;

    @Column(name = "COFFEE")
    private Integer coffee;

    @Column(name = "MERCH")
    private Integer merch;

    @Column(name = "TOTAL")
    private Integer total;

    public CoffHousesElements(Integer storeId, String city, Integer coffee, Integer merch, Integer total) {
        this.city = city;
        this.id = storeId;
        this.coffee = coffee;
        this.merch = merch;
        this.total = total;
    }

    public CoffHousesElements(){ }

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getCity() { return city; }

    public void setCity(String city) { this.city = city; }

    public int getCoffee() { return coffee; }

    public void setCoffee(int coffee) { this.coffee = coffee; }

    public int getMerch() { return merch; }

    public void setMerch(int merch) { this.merch = merch; }

    public int getTotal() { return total; }

    public void setTotal(int total) { this.total = total; }

    @Override
    public String toString() {
        return "CoffHouses{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", coffee=" + coffee +
                ", merch=" + merch +
                ", total=" + total +
                '}';
    }
}
