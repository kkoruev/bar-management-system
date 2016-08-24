package bg.unisofia.fmi.models;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import java.util.List;


/**
 * The persistent class for the user database table.
 * 
 */
@XmlRootElement
@Entity
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@XmlTransient
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_id")
	private int userId;

	private String name;

	@XmlTransient
	@Column(name="password_hash")
	private String passwordHash;

	@XmlTransient
	@Column(name="password_salt")
	private String passwordSalt;

	private String role;

	private String username;

	//bi-directional many-to-one association to Bill
	@OneToMany(mappedBy="user")
	private List<Bill> bills;

//	//bi-directional many-to-one association to Order
	@XmlTransient
	@OneToMany(mappedBy="user")
	private List<OrderUnit> orderUnits;
	
	@Transient
	private String password;

	public User() {
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPasswordHash() {
		return this.passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public String getPasswordSalt() {
		return this.passwordSalt;
	}

	public void setPasswordSalt(String passwordSalt) {
		this.passwordSalt = passwordSalt;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Bill> getBills() {
		return this.bills;
	}

	public void setBills(List<Bill> bills) {
		this.bills = bills;
	}

	public Bill addBill(Bill bill) {
		getBills().add(bill);
		bill.setUser(this);

		return bill;
	}

	public Bill removeBill(Bill bill) {
		getBills().remove(bill);
		bill.setUser(null);

		return bill;
	}

	public List<OrderUnit> getOrderUnits() {
		return this.orderUnits;
	}

	public void setOrderUnits(List<OrderUnit> orderUnits) {
		this.orderUnits = orderUnits;
	}

	public OrderUnit addOrderUnit(OrderUnit orderUnit) {
		getOrderUnits().add(orderUnit);
		orderUnit.setUser(this);

		return orderUnit;
	}

	public OrderUnit removeOrderUnit(OrderUnit orderUnit) {
		getOrderUnits().remove(orderUnit);
		orderUnit.setUser(null);

		return orderUnit;
	}

}