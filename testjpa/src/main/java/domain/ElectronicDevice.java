package domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class ElectronicDevice extends SmartDevices{
	
	private String brand;
	private String model;
	private Person owner;
	
	public ElectronicDevice() {
		super();
	}

	public ElectronicDevice(String brand, String model) {
		super();
		this.brand = brand;
		this.model = model;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	@ManyToOne
	public Person getOwner() {
		return owner;
	}

	public void setOwner(Person owner) {
		this.owner = owner;
	}

	
	
	
	
	

}
