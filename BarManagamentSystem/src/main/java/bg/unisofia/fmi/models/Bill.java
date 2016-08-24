package bg.unisofia.fmi.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * The persistent class for the bill database table.
 * 
 */
@Entity
@NamedQuery(name = "Bill.findAll", query = "SELECT b FROM Bill b")
@XmlRootElement
public class Bill implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="bill_id")
	private int billId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="completed_at")
	private Date completedAt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_at")
	private Date createdAt;

	@Column(name="table_name")
	private String tableName;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name = "owner_id")
	@XmlTransient
	private User user;

	//bi-directional many-to-one association to OrderUnit
	@OneToMany(mappedBy="bill")
	private List<OrderUnit> orderUnits;

	public Bill() {
	}

	public int getBillId() {
		return this.billId;
	}

	public void setBillId(int billId) {
		this.billId = billId;
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

	public String getTableName() {
		return this.tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<OrderUnit> getOrderUnits() {
		if(this.orderUnits == null) {
			this.orderUnits = new ArrayList<>();
		}
		return this.orderUnits;
	}

	public void setOrderUnits(List<OrderUnit> orderUnits) {
		this.orderUnits = orderUnits;
	}

	public OrderUnit addOrderUnit(OrderUnit orderUnit) {
		getOrderUnits().add(orderUnit);
		orderUnit.setBill(this);

		return orderUnit;
	}

	public OrderUnit removeOrderUnit(OrderUnit orderUnit) {
		getOrderUnits().remove(orderUnit);
		orderUnit.setBill(null);

		return orderUnit;
	}

}