package pl.poznan.put.cie.coffee;

import java.math.BigDecimal;

public class Coffee {

	private String name;
	private int supplierId;
	private BigDecimal price;
	private int sales;
	private int total;

	public Coffee() {
	}

	public Coffee(String name, int supplierId, BigDecimal price, int sales, int total) {
		this.name = name;
		this.supplierId = supplierId;
		this.price = price;
		this.sales = sales;
		this.total = total;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public int getSales() {
		return sales;
	}

	public void setSales(int sales) {
		this.sales = sales;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "Coffee{" + "name=" + name + ", supplierId=" + supplierId + ", price=" + price + ", sales=" + sales + ", total=" + total + '}';
	}

}
