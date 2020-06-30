package ceni.endra.backend.beans;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name="traveller", schema="public")
public class Traveller implements Serializable, Representable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4237502636886759240L;


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "idTraveller", nullable = false)
	private Integer idTraveller;
	

	@Column(name = "tName")
	private String tName;
	

	@Column(name = "tSurname")
	private String tSurname;
	

	@Column(name = "dBirth")
	private Date dBirth;
	
	@OneToOne(mappedBy = "travelAgent")
	private Reservation reservation;
		
	public Traveller() {}
	
	public Integer getIdTraveller() {
		return idTraveller;
	}

	public void setIdTraveller(Integer idTraveller) {
		this.idTraveller = idTraveller;
	}

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	public String gettName() {
		return tName;
	}
	public void settName(String tName) {
		this.tName = tName;
	}
	public String gettSurname() {
		return tSurname;
	}
	public void settSurname(String tSurname) {
		this.tSurname = tSurname;
	}
	public Date getdBirth() {
		return dBirth;
	}
	public void setdBirth(Date dBirth) {
		this.dBirth = dBirth;
	}
	

	@Override
	public String toString() {
		return new Gson().toJson(this);
	}

	@Override
	public String createDummyObjectForRepresentation() {
		this.idTraveller = 0;
		this.tName = "";
		this.tSurname = "";
		this.dBirth = new Date();
		return this.toString();
	}

}
