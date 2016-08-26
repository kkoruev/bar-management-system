package bg.unisofia.fmi.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="waiterCounterBills")
public class StatisticsDTO {
	String label;
	Double value;

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

}
