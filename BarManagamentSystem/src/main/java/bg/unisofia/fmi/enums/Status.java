package bg.unisofia.fmi.enums;

import java.io.Serializable;

public enum Status implements Serializable {
	PENDING, RECEIVED, COMPLETED, OVERDUE;
	
	public boolean contains(String status) {
		for(Status s : Status.values()) {
			if(s.equals(status)) {
				return true;
			}
		}
		return false;
	}
	
}
