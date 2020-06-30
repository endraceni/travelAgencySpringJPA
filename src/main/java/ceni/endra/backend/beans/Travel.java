package ceni.endra.backend.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;

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
@Table(name="travel", schema="public")
public class Travel implements Serializable, Representable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9190072108188046L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "idTravel", nullable = false)
	private Integer idTravel;
	
	@ManyToOne
	private Destination destination;
	
	@Column(name = "dStart")
	private Date dStart;
	

	@Column(name = "dEnd")
	private Date dEnd;
	
	@Column(name = "price")
	private double price;
	

	@Column(name = "dDelete")
	private String dDelete;
	
	@OneToOne(mappedBy = "travel")
	private Reservation reservation;
	
	public Travel() {}

	public Integer getIdTravel() {
		return idTravel;
	}

	public void setIdTravel(Integer idTravel) {
		this.idTravel = idTravel;
	}

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}


	public Destination getDestination() {
		return destination;
	}

	public void setDestination(Destination destination) {
		this.destination = destination;
	}

	public Date getdStart() {
		return dStart;
	}

	public void setdStart(Date dStart) {
		this.dStart = dStart;
	}

	public Date getdEnd() {
		return dEnd;
	}

	public void setdEnd(Date dEnd) {
		this.dEnd = dEnd;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
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
		this.idTravel = 0;
		this.destination = new Destination();
		this.dStart = new Date();
		this.dEnd = new Date();
		this.price = 0;
		this.dDelete = "0";
		return this.toString();
	}

}
