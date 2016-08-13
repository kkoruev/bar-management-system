package bg.unisofia.fmi.enums;

public enum Role {
	ADMIN, BARTENDER, WAITER;
	
	public static boolean contains(String role) {
		for(Role r : Role.values()) {
			if(r.name().equals(role)) {
				return true;
			}
		}
		return false;
	}
}
