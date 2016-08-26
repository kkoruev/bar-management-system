package bg.unisofia.fmi.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="waiterCounterBills")
public class WaiterStatisticsDTO {
	String username;
	Integer countBills;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getCountBills() {
		return countBills;
	}

	public void setCountBills(Integer countBills) {
		this.countBills = countBills;
	}

}
