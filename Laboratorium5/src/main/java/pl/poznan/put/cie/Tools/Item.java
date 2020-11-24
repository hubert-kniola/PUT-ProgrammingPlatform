package pl.poznan.put.cie.Tools;

import org.apache.lucene.document.*;

public class Item {

	private int id;
	private float price;
	private String name;
	private String category;
	private String description;

	public int getId() { return id; }

	public void setId(int id) { this.id = id; }

	public float getPrice() { return price; }

	public void setPrice(float price) { this.price = price; }

	public String getName() { return name; }

	public void setName(String name) { this.name = name; }

	public String getCategory() { return category; }

	public void setCategory(String category) { this.category = category; }

	public String getDescription() { return description; }

	public void setDescription(String description) { this.description = description; }

	public static Item fromDocument(Document document) {
		var item = new Item();

		var id = document.get("id");
		var price = document.get("price");

		if (id != null) {
			item.id = Integer.parseInt(id);
		}
		if (price != null) {
			item.price = Float.parseFloat(price);
		}

		item.name = document.get("name");
		item.category = document.get("category");
		item.description = document.get("description");

		return item;
	}

	public Document toDocument() {
		var doc = new Document();

		doc.add(new StringField("id", Integer.toString(getId()), Field.Store.YES));
		doc.add(new TextField("name", getName(), Field.Store.YES));
		doc.add(new FloatPoint("price", getPrice()));

		var category = getCategory();

		doc.add(new TextField("category", category == null ? "" : category, Field.Store.YES));
		doc.add(new TextField("description", getDescription(), Field.Store.NO));

		return doc;
	}

}
