package pl.poznan.put.cie.coffeefx;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.util.converter.BigDecimalStringConverter;

import java.math.BigDecimal;

public class CoffeeFX {

    private SimpleStringProperty name;
    private SimpleIntegerProperty supplierId;
    private SimpleStringProperty price;
    private SimpleIntegerProperty sales;
    private SimpleIntegerProperty total;

    public CoffeeFX() {
    }

    public CoffeeFX(String nameN, int supplierIdN, BigDecimal priceN, int salesN, int totalN) {
        this.name = new SimpleStringProperty(nameN);
        this.supplierId = new SimpleIntegerProperty(supplierIdN);
        this.price = new SimpleStringProperty(new BigDecimalStringConverter().toString(priceN));
        this.sales = new SimpleIntegerProperty(salesN);
        this.total = new SimpleIntegerProperty(totalN);
    }

    public String getName() { return name.get(); }

    public void setName(String nameN) { name.set(nameN);}

    public int getSupplierId() {
        return supplierId.get();
    }

    public void setSupplierId(int supplierIdN) { supplierId.set(supplierIdN); }

    public String getPrice() {
        return price.get();
    }

    public void setPrice(BigDecimal priceN) {
        price.set(new BigDecimalStringConverter().toString(priceN));
    }

    public int getSales() {
        return sales.get();
    }

    public void setSales(int salesN) {
        sales.set(salesN);
    }

    public int getTotal() {
        return total.get();
    }

    public void setTotal(int totalN) {
        total.set(totalN);
    }
}