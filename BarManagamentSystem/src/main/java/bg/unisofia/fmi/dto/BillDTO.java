package bg.unisofia.fmi.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class BillDTO {

	private String tableName;

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

}
