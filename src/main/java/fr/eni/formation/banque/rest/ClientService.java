package fr.eni.formation.banque.rest;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import fr.eni.formation.banque.Client;
import fr.eni.formation.banque.dao.ClientDao;

@Path("/clients")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ClientService {
	
	@Inject
	ClientDao dao; // EJB Stateless ClientDaoImpl

	public ClientService() {}

	@GET
	@Path("/texte")
	@Produces(MediaType.TEXT_PLAIN)
	public String texte() {
		return "Mon premier service Rest avec Jax-RS !";
	}
	
	@GET
	@Path("/objet")
	public Client objet() {
		return new Client("Ainslie","Ben");
	}
	
	@GET
	public List<Client> getAll() {
		return dao.readAll().collect(Collectors.toList());
	}

	@GET
	@Path("/{id}")
	public Client get(@PathParam("id") long id) {
		return dao.read(id);
	}
	
	@POST
	public Response post(Client client) {
		client = dao.create(client.getNom(),client.getPrenom());
		return Response
				.status(Response.Status.CREATED)
				.entity(client)
				.build();
	}
	
	@DELETE
	@Path("/{id}")
	public void delete(@PathParam("id") long id) {
		dao.delete(id);
	}
	
	
	
}
