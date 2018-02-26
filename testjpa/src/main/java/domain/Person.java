package domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonManagedReference;

@Entity
public class Person {
    private Long id;
    private String name;
    private List<Home> homes = new ArrayList<Home>();
    private List<ElectronicDevice> devices = new ArrayList<ElectronicDevice>();
    private List<Person> friends = new ArrayList<Person>();
    
    public Person() {
    	
    }
    
    public Person(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "owner", cascade=CascadeType.ALL)
	@JsonIgnore
	public List<Home> getHomes() {
		return homes;
	}

	public void setHomes(List<Home> homes) {
		this.homes = homes;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "owner", cascade=CascadeType.ALL)
	@JsonIgnore
	public List<ElectronicDevice> getDevices() {
		return devices;
	}

	public void setDevices(List<ElectronicDevice> devices) {
		this.devices = devices;
	}

	@ManyToMany(fetch = FetchType.EAGER)
	@JsonIgnore
	public List<Person> getFriends() {
		return friends;
	}

	public void setFriends(List<Person> friends) {
		this.friends = friends;
	}


	
	
    
    
	
}

