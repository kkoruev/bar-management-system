package bg.unisofia.fmi.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="item")
public class ItemDTO {

	private String name;
	private int quantity;
	private double price;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
