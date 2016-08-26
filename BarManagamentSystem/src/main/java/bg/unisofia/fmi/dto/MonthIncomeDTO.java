package bg.unisofia.fmi.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class MonthIncomeDTO {

	@XmlElement
	private List<Double> income;

	public List<Double> getIncome() {
		return income;
	}

	public void setIncome(List<Double> income) {
		this.income = income;
	}	
}
