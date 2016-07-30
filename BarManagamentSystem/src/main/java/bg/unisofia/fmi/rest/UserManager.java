package bg.unisofia.fmi.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import bg.unisofia.fmi.models.Category;
import bg.unisofia.fmi.models.User;

@Path("/user")
public class UserManager {

	@Path("/login")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Category loginUser(User user) {
		if (user.getName() != null) {
			return new Category();
		}
		return null;
	}
}

