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
			
			ElectronicDevice d = new ElectronicDevice();
			d.setBrand("Samsung");
			d.setModel("A5");
			d.setOwner(p);
			
			List<Home> lhome= new ArrayList<Home>();
			List<Heater> lheater= new ArrayList<Heater>();
			List<ElectronicDevice> ldevice= new ArrayList<ElectronicDevice>();
			List<Person> pote = new ArrayList<Person>();
			
			lheater.add(ter1);			
			ldevice.add(d);
			h.setHeatrs(lheater);		
			lhome.add(h);	
			pote.add(p1);	
			
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
		
		manager.close();
		factory.close();
	}

}
