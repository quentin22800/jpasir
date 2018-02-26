package servelet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Person;


@WebServlet(name="addperson",urlPatterns={"/addPerson"})
public class AddPerson extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		out.print("<html>\n"
				+ "<body>\n"
				+ "<FORM Method='POST' Action='/addPerson'>\n"
				+ "Name : 		<INPUT type=text size=20 name='name'><BR>"
				+ "<INPUT type=submit value=Send>\n"
				+ "</FORM>\n"
				+ "</body>\n"
				+ "</html>");

	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		EntityManagerFactory factory = Persistence
				.createEntityManagerFactory("mysql");
		EntityManager manager = factory.createEntityManager();

		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		
		try {
			Person p = new Person();
			p.setName(request.getParameter("name"));
			
			manager.persist(p);
		} catch (Exception e) {
			e.printStackTrace();
		}
		tx.commit();
		
		manager.close();
		factory.close();
		
		
		
		out.println("<HTML>\n<BODY>\n" +
				"<H1>Insertion dans la base ok</H1>\n" +			
				"</BODY></HTML>");
		response.sendRedirect("/infos");
	}
}
