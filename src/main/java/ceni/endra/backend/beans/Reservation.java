/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ceni.endra.backend.beans;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name="reservation", schema="public")
public class Reservation implements Serializable, Representable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -8678977879845943204L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "idReservation")
	private Integer idReservation;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idTraveller", referencedColumnName = "idTraveller")
    private Traveller traveller;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idTravel", referencedColumnName = "idTravel")
    private Travel travel;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idAgent", referencedColumnName = "idAgent")
    private TravelAgent travelAgent;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idAccomodation", referencedColumnName = "idAccomodation")
    private Accomodation accomodation;
	
	@Column(name = "reservationDate")
    private Date reservationDate;
	
	@Column(name = "totalPrice")
    private double totalPrice;
	
	@Column(name = "cancelled")
    private Boolean cancelled = false;
	
	@Column(name = "cancelDate")
    private Date cancelDate;

    public Reservation() {
    }
    
    
	public Integer getIdReservation() {
		return idReservation;
	}


	public void setIdReservation(Integer idReservation) {
		this.idReservation = idReservation;
	}


	public Traveller getTraveller() {
		return traveller;
	}


	public void setTraveller(Traveller traveller) {
		this.traveller = traveller;
	}


	public Travel getTravel() {
		return travel;
	}


	public void setTravel(Travel travel) {
		this.travel = travel;
	}


	public TravelAgent getTravelAgent() {
		return travelAgent;
	}


	public void setTravelAgent(TravelAgent travelAgent) {
		this.travelAgent = travelAgent;
	}


	public Accomodation getAccomodation() {
		return accomodation;
	}


	public void setAccomodation(Accomodation accomodation) {
		this.accomodation = accomodation;
	}


	public Date getReservationDate() {
		return reservationDate;
	}


	public void setReservationDate(Date reservationDate) {
		this.reservationDate = reservationDate;
	}


	public double getTotalPrice() {
		return totalPrice;
	}


	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}


	public Boolean getCancelled() {
		return cancelled;
	}


	public void setCancelled(Boolean cancelled) {
		this.cancelled = cancelled;
	}


	public Date getCancelDate() {
		return cancelDate;
	}


	public void setCancelDate(Date cancelDate) {
		this.cancelDate = cancelDate;
	}


	@Override
	public String toString() {
		return new Gson().toJson(this);
	}

	@Override
	public String createDummyObjectForRepresentation() {
		
		this.idReservation = 0;
		this.travel = new Travel();
		this.traveller = new Traveller();
		this.travelAgent = new TravelAgent();
		this.accomodation = new Accomodation();
		this.cancelDate = new Date();
		this.cancelled = false;
		this.reservationDate = new Date();
		this.totalPrice = 0;
		return this.toString();
	}

    
    
    
}
