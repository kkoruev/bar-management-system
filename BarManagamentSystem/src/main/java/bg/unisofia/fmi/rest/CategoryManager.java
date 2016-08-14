package bg.unisofia.fmi.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import bg.unisofia.fmi.dao.CategoryDAO;
import bg.unisofia.fmi.models.Category;


@Path("/categories")
public class CategoryManager {
	
	@EJB 
	private CategoryDAO categoryDAO;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addCategory(Category category){
		if(category.getName() != null) {
			categoryDAO.addCategory(category);
			return Response.ok().build();
		}
		return Response.serverError().build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Category> getCategories() {
		return categoryDAO.getCategories();
	}
}
