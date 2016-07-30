package bg.unisofia.fmi.rest;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import bg.unisofia.fmi.context.impl.UserContext;
import bg.unisofia.fmi.dao.UserDAO;


public class UserManager {

	@EJB
	UserDAO userDao;
	
	@EJB
	UserContext userContext;
	
	@Path("login")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response loginUser(String userName, String password) {
		try {
			if(userDao.authenticateUser(userName, password)){
				userContext.setUser(userDao.findByUsername(userName));
				return Response.ok().build();
			}
		} catch (Exception e) {
			return Response.serverError().build();
		}
		return null;
	}
}

