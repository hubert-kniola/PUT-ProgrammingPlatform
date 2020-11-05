package pl.poznan.put.cie.coffee.EntityModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity(name = "MERCH_INVENTORY")
public class MerchElements {
    @Id
    @Column(name = "ITEM_ID")
    private Integer id;

    @Column(name = "ITEM_NAME")
    private String name;

    @Column(name = "SUP_ID")
    private Integer supId;

    @Column(name = "QUAN")
    private Integer quan;

    @Column(name = "DATE_VAL")
    private Timestamp date;

    public MerchElements(Integer ID, String name, Integer supID, Integer quan, Timestamp date) {
        this.id = ID;
        this.name = name;
        this.supId = supID;
        this.quan = quan;
        this.date = date;
    }

    public MerchElements(){ }

    public Integer getID() { return id; }

    public void setID(Integer ID) { this.id = ID; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public Integer getQuan() { return quan; }

    public void setQuan(Integer quan) { this.quan = quan; }

    public Integer getSupID() { return supId; }

    public void setSupID(Integer supID) { this.supId = supID; }

    public Timestamp getDate() { return date; }

    public void setDate(Timestamp date) { this.date = date; }

    @Override
    public String toString() {
        return "MerchElements{" +
                "ID=" + id +
                ", name='" + name + '\'' +
                ", supID=" + supId +
                ", quan=" + quan +
                ", date=" + date +
                '}';
    }
}
