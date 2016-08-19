package bg.unisofia.fmi.rest;


import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import bg.unisofia.fmi.dao.ItemDAO;
import bg.unisofia.fmi.models.Item;


@Stateless
@Path("/items")
public class ItemManager {
	
	@EJB
	ItemDAO itemDAO;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addItem(Item item) {
		try {
			System.out.println(item);
			itemDAO.addItem(item);
		} catch (Exception e) {
			return Response.serverError().build();
			// TODO: handle exception
		}
		return Response.status(Response.Status.CREATED).build();
	}
	
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Item> getItems() {
		return itemDAO.getItems();
	}
	
	
}
