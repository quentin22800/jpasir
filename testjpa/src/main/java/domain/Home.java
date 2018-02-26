package domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.codehaus.jackson.annotate.JsonBackReference;


@Entity
public class Home {
    private Long id;
    private String adresse;
    private Person owner;
    private List<Heater> heatrs = new ArrayList<Heater>(); 
    
    public Home() {
        super();
    }
    
    public Home(String adresse, List<Heater> heatrs) {
        this.adresse = adresse;
        this.heatrs = heatrs;
    }
    
    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getAdresse() {
        return adresse;
    }
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
    
    @OneToMany(fetch = FetchType.EAGER, mappedBy="homeOwner", cascade=CascadeType.PERSIST)
	public List<Heater> getHeatrs() {
		return heatrs;
	}
	public void setHeatrs(List<Heater> heatrs) {
		this.heatrs = heatrs;
	}
	
	@ManyToOne
	public Person getOwner() {
		return owner;
	}
	public void setOwner(Person owner) {
		this.owner = owner;
	}

	
	
	
    
    
	
    
}

