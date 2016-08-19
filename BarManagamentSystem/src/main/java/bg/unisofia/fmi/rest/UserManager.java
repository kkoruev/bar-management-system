package bg.unisofia.fmi.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import bg.unisofia.fmi.context.impl.UserContextImpl;
import bg.unisofia.fmi.dao.BillDAO;
import bg.unisofia.fmi.dao.UserDAO;
import bg.unisofia.fmi.enums.Role;
import bg.unisofia.fmi.exceptions.InvalidUserException;
import bg.unisofia.fmi.models.Bill;
import bg.unisofia.fmi.models.User;

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
	public Response addUser(User user) {
		try {
			userDAO.registerUser(user);
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
	public Response loginUser(User user) {
		try {
			if (userDAO.authenticateUser(user.getUsername(),
					user.getPassword())) {
				userContext.setUser(userDAO.findByUsername(user
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
	public Response createBill(Bill bill) {
		try{
			billDAO.startBill(bill, userContext.getUser());
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
	
	@Path("/bills/open")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Bill> getOpenBillsByUser(){
		return billDAO.getOpenBillsByUser(userContext.getUser());
	}

	 @Path("/role")
	 @GET
	 @Produces(MediaType.APPLICATION_JSON)
	 public String getUserName() {
		 return userContext.getUser().getRole();
	 }
	 
	 @Path("/orders")
	 @POST
	 @Consumes(MediaType.APPLICATION_JSON)
	 public Response addOrder(Bill bill) {
		 
		 return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
	 }
	 
	 @Path("/orders/{billId}")
	 @GET
	 @Produces(MediaType.APPLICATION_JSON)
	 public Response getOrders(@PathParam("billId") int billId) {
		 try {
			 return Response.ok().entity(billDAO.getOrders(billId)).build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	 }
	 
}
