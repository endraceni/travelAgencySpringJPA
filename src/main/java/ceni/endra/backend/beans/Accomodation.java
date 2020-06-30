package ceni.endra.backend.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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
@Table(name="accomodation", schema="public")
public class Accomodation implements Serializable, Representable{
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = -5120096418784388031L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "idAccomodation", nullable = false)
	private Integer idAccomodation;
	
	@Column(name = "accomodationType")
	 private String accomodationType;
	
	@Column(name = "address")
	 private String address;
	
	@Column(name = "cost")
	 private double cost;
	
	@OneToOne(mappedBy = "accomodation")
	private Reservation reservation;
	
	@ManyToOne
	private Destination destination;
	 	 
	 
	public Accomodation() {
	}
	
	public Integer getIdAccomodation() {
		return idAccomodation;
	}

	public void setIdAccomodation(Integer idAccomodation) {
		this.idAccomodation = idAccomodation;
	}

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	public String getAccomodationType() {
		return accomodationType;
	}
	public void setAccomodationType(String accomodationType) {
		this.accomodationType = accomodationType;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	
	public Destination getDestination() {
		return destination;
	}
	public void setDestination(Destination destination) {
		this.destination = destination;
	}
	@Override
	public String toString() {
		return new Gson().toJson(this);
	}
	
	@Override
	public String createDummyObjectForRepresentation() {
		this.idAccomodation = 0;
		this.destination = new Destination();
		this.accomodationType = "";
		this.address = "";
		this.cost = 0;
		return this.toString();
	}
	 
	 

}
