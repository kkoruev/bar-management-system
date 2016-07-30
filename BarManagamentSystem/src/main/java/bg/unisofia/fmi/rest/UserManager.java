package bg.unisofia.fmi.rest;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import bg.unisofia.fmi.context.impl.UserContext;
import bg.unisofia.fmi.dao.UserDAO;
import bg.unisofia.fmi.dto.UserDTO;

@Path("/user")
public class UserManager {

	@EJB
	UserDAO userDao;

	@EJB
	UserContext userContext;

	@Path("/register")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addUser(UserDTO userDTO) {
		return null;
	}

	@Path("/login")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response loginUser(UserDTO userDTO) {
		try {
			if (userDao.authenticateUser(userDTO.getUsername(), userDTO.getPassword())) {
				userContext.setUser(userDao.findByUsername(userDTO.getUsername()));
				return Response.ok().build();
			}
		} catch (Exception e) {
			return Response.serverError().build();
		}
		return Response.serverError().build();
	}

	@Path("/check")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	public String getUserName() {
		return userContext.getUser().getUsername();
	}
}
