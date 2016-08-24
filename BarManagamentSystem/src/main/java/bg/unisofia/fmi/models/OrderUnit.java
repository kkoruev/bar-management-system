package bg.unisofia.fmi.models;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import java.util.Date;
import java.util.List;


@Entity
@Table(name="order_unit")
@NamedQuery(name="OrderUnit.findAll", query="SELECT o FROM OrderUnit o")
@XmlRootElement
public class OrderUnit implements Serializable {
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

	@ManyToMany
	@JoinTable(
	 	name="order_item", 
	 	joinColumns={@JoinColumn(name="order_id")},
	 	inverseJoinColumns={@JoinColumn(name="item_id")}
	)
	private List<Item> items;

	public OrderUnit() {
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
	
	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}


}