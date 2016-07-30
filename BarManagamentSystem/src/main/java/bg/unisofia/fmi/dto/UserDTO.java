package bg.unisofia.fmi.dto;

import javax.xml.bind.annotation.XmlRootElement;

import bg.unisofia.fmi.models.Role;

@XmlRootElement
public class UserDTO {

	private String username;
	private String password;
	private String name;
	private RoleDTO userRole;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public RoleDTO getUserRole() {
		return userRole;
	}

	public void setUserRole(RoleDTO userRole) {
		this.userRole = userRole;
	}

	
}
