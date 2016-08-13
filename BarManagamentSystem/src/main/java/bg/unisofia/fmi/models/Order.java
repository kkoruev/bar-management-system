package bg.unisofia.fmi.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the order database table.
 * 
 */
@Entity
@NamedQuery(name="Order.findAll", query="SELECT o FROM Order o")
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="order_id")
	private int orderId;

	private String comment;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="completed_at")
	private Date completedAt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_at")
	private Date createdAt;

	private String status;

	//bi-directional many-to-one association to Bill
	@ManyToOne
	@JoinColumn(name="bill_id")
	private Bill bill;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="taken_by_id")
	private User user;

//	//bi-directional many-to-one association to OrderItem
//	@OneToMany(mappedBy="order")
//	private List<OrderItem> orderItems;

	public Order() {
	}

	public int getOrderId() {
		return this.orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getCompletedAt() {
		return this.completedAt;
	}

	public void setCompletedAt(Date completedAt) {
		this.completedAt = completedAt;
	}

	public Date getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Bill getBill() {
		return this.bill;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
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
//		orderItem.setOrder(this);
//
//		return orderItem;
//	}
//
//	public OrderItem removeOrderItem(OrderItem orderItem) {
//		getOrderItems().remove(orderItem);
//		orderItem.setOrder(null);
//
//		return orderItem;
//	}

}