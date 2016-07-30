package bg.unisofia.fmi.dto;

import javax.xml.bind.annotation.XmlRootElement;

import bg.unisofia.fmi.models.Role;

@XmlRootElement
public class UserDTO {

	private String username;
	private String password;
	private String name;
	//private Role role;

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

//	public Role getRole() {
//		return role;
//	}
//
//	public void setRole(Role role) {
//		this.role = role;
//	}

}
