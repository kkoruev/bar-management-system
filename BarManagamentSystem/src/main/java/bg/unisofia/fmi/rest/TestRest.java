package bg.unisofia.fmi.rest;

import javax.ejb.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Singleton
@Path("/resources")
public class TestRest {

	@GET
	@Path("/number")
	public int getNumber() {
		return 1;
	}

	@GET
	@Path("/string")
	public String getString() {
		return "A string";
	}
}
