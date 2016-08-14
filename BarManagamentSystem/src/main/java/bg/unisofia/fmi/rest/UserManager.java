package bg.unisofia.fmi.rest;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import bg.unisofia.fmi.context.impl.UserContextImpl;
import bg.unisofia.fmi.dao.BillDAO;
import bg.unisofia.fmi.dao.UserDAO;
import bg.unisofia.fmi.dto.BillDTO;
import bg.unisofia.fmi.dto.UserDTO;
import bg.unisofia.fmi.enums.Role;
import bg.unisofia.fmi.exceptions.InvalidUserException;

@Stateless
@Path("/user")
public class UserManager {

	@EJB
	UserDAO userDAO;

	@EJB
	BillDAO billDAO;
	
	@Inject
	UserContextImpl userContext;

	@Path("/register")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addUser(UserDTO userDTO) {
		try {
			userDAO.registerUser(userDTO);
		} catch (Exception e) {
			return Response.serverError().build();
		}
		return Response.ok().build();
	}

	@Path("/roles")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Role[] getRoles() {
		//TODO: fix JSON response
		return Role.values();
	}

	@Path("/login")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response loginUser(UserDTO userDTO) {
		try {
			if (userDAO.authenticateUser(userDTO.getUsername(),
					userDTO.getPassword())) {
				userContext.setUser(userDAO.findByUsername(userDTO
						.getUsername()));
				return Response.ok().build();
			}
		} catch (Exception e) {
			return Response.serverError().build();
		}
		return Response.serverError().build();
	}
	
	@Path("/bills")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createBill(BillDTO billDTO) {
		try{
			billDAO.startBill(billDTO, userContext.getUser());
		}
		catch (InvalidUserException e) {
			//TODO return 400 BAD REQEUST
			return Response.serverError().build();
		}
		catch (Exception e) {
			return Response.serverError().build();
		}
		return Response.ok().build();
	}

	 @Path("/role")
	 @GET
	 @Consumes(MediaType.APPLICATION_JSON)
	 public String getUserName() {
		 return userContext.getUser().getRole();
	 }
}
