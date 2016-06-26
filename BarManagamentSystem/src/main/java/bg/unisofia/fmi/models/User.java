package bg.unisofia.fmi.models;

import java.io.Serializable;
import javax.persistence.*;

import java.util.LinkedList;
import java.util.List;


/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String creadentials;

	private String name;

	private String salt;

	private String username;

	//bi-directional many-to-one association to Order
	
	@OneToMany(mappedBy="user", cascade={CascadeType.PERSIST})
	private List<Order> orders;

	//bi-directional many-to-one association to Role
	@ManyToOne(cascade={CascadeType.PERSIST})
	private Role role;

	public User() {
		orders = new LinkedList<>();
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCreadentials() {
		return this.creadentials;
	}

	public void setCreadentials(String creadentials) {
		this.creadentials = creadentials;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSalt() {
		return this.salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Order> getOrders() {
		return this.orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public Order addOrder(Order order) {
		getOrders().add(order);
		order.setUser(this);

		return order;
	}

	public Order removeOrder(Order order) {
		getOrders().remove(order);
		order.setUser(null);

		return order;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}