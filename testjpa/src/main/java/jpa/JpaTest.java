package jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import domain.ElectronicDevice;
import domain.Heater;
import domain.Home;
import domain.Person;

public class JpaTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence
				.createEntityManagerFactory("mysql");
		EntityManager manager = factory.createEntityManager();

		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		try {
			
			Person p = new Person();
			p.setName("martin");
			
			Person p1 = new Person();
			p1.setName("michou");
			
			Home h = new Home();
			h.setAdresse("rue de la voiture");
			h.setOwner(p);
			
			Heater ter1 = new Heater();
			ter1.setZone("chambre");
			ter1.setHomeOwner(h);
			
			
			/*Home h = new Home();
			h.setAdresse("rue de la voiture");
			h.setOwner(p);
			/*Home h1 = new Home();
			h1.setAdresse("rue de la moto");
			h1.setOwner(p);
			Home h2 = new Home();
			h2.setAdresse("rue de la michto");
			h2.setOwner(p1);
			Heater ter1 = new Heater();
			Heater ter = new Heater();
			ter1.setZone("chambre");
			ter.setZone("toilettes");
			ter1.setHomeOwner(h);
		//	ter.setHomeOwner(h1);*/
			
			ElectronicDevice d = new ElectronicDevice();
			d.setBrand("Samsung");
			d.setModel("A5");
			d.setOwner(p);
			
			List<Home> lhome= new ArrayList<Home>();
			List<Heater> lheater= new ArrayList<Heater>();
		//	List<Heater> lheater1= new ArrayList<Heater>();
			List<ElectronicDevice> ldevice= new ArrayList<ElectronicDevice>();
		//	List<Home> lhome1= new ArrayList<Home>();
			List<Person> pote = new ArrayList<Person>();
		//	List<Person> pote1 = new ArrayList<Person>();
			
			lheater.add(ter1);
		//	lheater1.add(ter);
			
			ldevice.add(d);
			
			h.setHeatrs(lheater);
		//	h1.setHeatrs(lheater1);
			//h2.setHeatrs(lheater);
			
			lhome.add(h);
		//	lhome.add(h1);
			
		//	lhome1.add(h2);
			
			pote.add(p1);
		//	pote1.add(p);
			
			p.setFriends(pote);
			
			
			p.setDevices(ldevice);
			p.setHomes(lhome);

			
			manager.persist(p);
			manager.persist(p1);
				
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		tx.commit();
		
		String s = "SELECT e FROM Person as e";
		
		Query q = manager.createQuery(s,Person.class);
		//q.setParameter("name", "martin"); 
		List<Person> res = q.getResultList();
		List<Home> homes = new ArrayList<Home>();
		System.err.println(res.size());
		
		for (Person p : res){
			homes = p.getHomes();
			System.err.println(p.getName());
			for (Home h : homes){
				System.err.println(h.getAdresse());
			}
			
		}
		//System.err.println(res.get(0).getName());
		
		manager.close();
		factory.close();
	}

}
