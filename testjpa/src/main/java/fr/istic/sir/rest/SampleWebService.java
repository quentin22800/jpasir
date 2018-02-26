package fr.istic.sir.rest;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import domain.ElectronicDevice;
import domain.Home;
import domain.Person;

@Path("/domain")
public class SampleWebService {
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayHello() {
		return "Bienvenu(e) sur la page d'accueil de notre api REST";
	}

	@GET
	@Path("/homes")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Home> getHomes() {
		EntityManagerFactory factory = getFactory();
		EntityManager manager = getEntityManager(factory);

		String s = "SELECT h FROM Home as h";

		Query q = manager.createQuery(s,Home.class);
		List<Home> res = q.getResultList();

		manager.close();
		factory.close();

		return res;
	}
	
	@GET
	@Path("/electronicDevices")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ElectronicDevice> getElectronicDevices() {
		EntityManagerFactory factory = getFactory();
		EntityManager manager = getEntityManager(factory);

		String s = "SELECT ed FROM ElectronicDevice as ed";

		Query q = manager.createQuery(s,ElectronicDevice.class);
		List<ElectronicDevice> res = q.getResultList();

		manager.close();
		factory.close();

		return res;
	}


	@GET
	@Path("/people")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Person> getPeople() {
		EntityManagerFactory factory = getFactory();
		EntityManager manager = getEntityManager(factory);

		String s = "SELECT p FROM Person as p";

		Query q = manager.createQuery(s,Person.class);
		List<Person> res = q.getResultList();

		manager.close();
		factory.close();

		return res;
	}


	@POST
	@Path("/addhome")
	public Response addHome(
			@QueryParam("adresse") String adresse,
			@QueryParam("idOwner") Long idOwner) {
		
		EntityManagerFactory factory = getFactory();
		EntityManager manager = getEntityManager(factory);

		EntityTransaction tx = manager.getTransaction();
		
		String queryString = "SELECT p FROM Person p WHERE p.id = :id";
				
		tx.begin();
		try {
			Query query = manager.createQuery(queryString);
			query.setParameter("id", idOwner);
			Person owner = (Person)query.getSingleResult();
			
			Home h = new Home();
			h.setAdresse(adresse);
			h.setOwner(owner);
			manager.persist(h);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		tx.commit();

		return Response.status(200)
				.entity("addHome is called, adresse : " + adresse)
				.build();
	}
	
	@DELETE
	@Path("/people")
	public void deletePerson(@QueryParam("idPerson") Long idPerson) {
		EntityManagerFactory factory = getFactory();
		EntityManager manager = getEntityManager(factory);

		Person p = manager.find(Person.class, idPerson);
		
		EntityTransaction tx = manager.getTransaction();
		
		tx.begin();
		try {
			manager.remove(p);
		} catch (Exception e) {
			e.printStackTrace();
		}
		tx.commit();
		manager.close();
		factory.close();
	}

	public EntityManager getEntityManager(EntityManagerFactory fact)
	{
		EntityManager manager = fact.createEntityManager();
		return manager;
	}

	public EntityManagerFactory getFactory()
	{
		EntityManagerFactory factory = Persistence
				.createEntityManagerFactory("mysql");
		return factory;
	}
}
