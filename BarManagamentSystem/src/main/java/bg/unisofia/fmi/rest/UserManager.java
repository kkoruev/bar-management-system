package bg.unisofia.fmi.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import bg.unisofia.fmi.models.User;

@Path("user")
public class UserManager {

	@Path("login")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response loginUser(User user) {
		if (user.getName() != null) {
			return Response.ok().build();
		}
		return Response.serverError().build();
	}
}
