package ceni.endra.backend.beans;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.google.gson.Gson;

import ceni.endra.util.Representable;

@EnableJpaRepositories("ceni.endra.*")
@ComponentScan(basePackages = { "ceni.endra.*" })
@EntityScan(basePackages = {"ceni.endra.*"})  
@Entity
@Table(name="destination", schema="public")
public class Destination implements Serializable, Representable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7220952433501511766L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "nIdDestination", nullable = false)
	private Integer nIdDestination;
	
	@Column(name = "tName")
	private String tName;
	
	@Column(name = "tCity")
	private String tCity;
	
	@Column(name = "tState")
	private String tState;
	
	@Column(name = "dDelete")
	private String dDelete;
	
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "destination")
	private Set<Travel> travels;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "destination")
	private Set<Accomodation> accomodations;
	
	public Destination() {}

	public Integer getnIdDestination() {
		return nIdDestination;
	}

	public void setnIdDestination(Integer nIdDestination) {
		this.nIdDestination = nIdDestination;
	}

//	public Set<Travel> getTravels() {
//		return travels;
//	}
//
//	public void setTravels(Set<Travel> travels) {
//		this.travels = travels;
//	}

//	public Set<Accomodation> getAccomodations() {
//		return accomodations;
//	}
//
//	public void setAccomodations(Set<Accomodation> accomodations) {
//		this.accomodations = accomodations;
//	}

	public String gettName() {
		return tName;
	}

	public void settName(String tName) {
		this.tName = tName;
	}

	public String gettCity() {
		return tCity;
	}

	public void settCity(String tCity) {
		this.tCity = tCity;
	}

	public String gettState() {
		return tState;
	}

	public void settState(String tState) {
		this.tState = tState;
	}

	public String getdDelete() {
		return dDelete;
	}

	public void setdDelete(String dDelete) {
		this.dDelete = dDelete;
	}

	@Override
	public String toString() {
		return new Gson().toJson(this);
	}

	@Override
	public String createDummyObjectForRepresentation() {
		this.nIdDestination = 0;
		this.tName = "";
		this.tCity = "";
		this.tState = "";
		return this.toString();
	}
	
//	public String toString() {
//		this.nIdDestination = 0;
//		this.tName = "";
//		this.tCity = "";
//		this.tState = "";
//		return this.nIdDestination+" "+this.tName+this.tCity+this.tState;
//	}
	
}
