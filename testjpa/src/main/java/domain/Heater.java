package domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
public class Heater extends SmartDevices{

	private String zone;
	private Home homeOwner;
	
	public Heater() {
		super();
	}

	public Heater(String zone, Home homeOwner) {
		super();
		this.zone = zone;
		this.homeOwner = homeOwner;
	}
	
	public String getZone() {
		return zone;
	}

	public void setZone(String zone) {
		this.zone = zone;
	}

	@ManyToOne
	@JsonIgnore
	public Home getHomeOwner() {
		return homeOwner;
	}

	public void setHomeOwner(Home homeOwner) {
		this.homeOwner = homeOwner;
	}	
	
	
}
