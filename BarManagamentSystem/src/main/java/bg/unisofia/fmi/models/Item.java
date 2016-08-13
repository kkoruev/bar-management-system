package bg.unisofia.fmi.models;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the item database table.
 * 
 */
@Entity
@NamedQuery(name="Item.findAll", query="SELECT i FROM Item i")
public class Item implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="item_id")
	private int itemId;

	private String description;

	private String name;

	private BigDecimal price;

	//bi-directional many-to-one association to Category
	@ManyToOne
	@JoinColumn(name="category_id")
	private Category category;

//	//bi-directional many-to-one association to OrderItem
//	@OneToMany(mappedBy="item")
//	private List<OrderItem> orderItems;

	public Item() {
	}

	public int getItemId() {
		return this.itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

//	public List<OrderItem> getOrderItems() {
//		return this.orderItems;
//	}
//
//	public void setOrderItems(List<OrderItem> orderItems) {
//		this.orderItems = orderItems;
//	}
//
//	public OrderItem addOrderItem(OrderItem orderItem) {
//		getOrderItems().add(orderItem);
//		orderItem.setItem(this);
//
//		return orderItem;
//	}
//
//	public OrderItem removeOrderItem(OrderItem orderItem) {
//		getOrderItems().remove(orderItem);
//		orderItem.setItem(null);
//
//		return orderItem;
//	}

}