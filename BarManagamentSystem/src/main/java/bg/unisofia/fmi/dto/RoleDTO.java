package bg.unisofia.fmi.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RoleDTO {
	
	private String roleType;

	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}
		
}
