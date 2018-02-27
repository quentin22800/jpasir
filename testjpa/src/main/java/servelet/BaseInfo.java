package servelet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Person;

@WebServlet(name="baseinfo",urlPatterns={"/infos"})
public class BaseInfo extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		PrintWriter out = resp.getWriter();

		EntityManagerFactory factory = Persistence
				.createEntityManagerFactory("mysql");
		EntityManager manager = factory.createEntityManager();

		out.print("<HTML>\n<BODY>\n" +
				"<H1>Personne</H1>\n");

		String s = "SELECT e FROM Person as e";
		Query q = manager.createQuery(s,Person.class);

		List<Person> res = q.getResultList();
		System.err.println(res.size());

		for (Person p : res){
			out.print("<UL>\n <LI>ID: " + p.getId() + "</LI>\n"
					+ "<LI>Nom: " + p.getName() + "</LI></UL>\n");
		}
		
		out.print("<a href='/addPerson'><input type='button' value='Ajouter une personne'></a>");
		
		manager.close();
		factory.close();
	}
}
