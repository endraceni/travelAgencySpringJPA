/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ceni.endra.backend.beans;

import java.io.Serializable;

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

/**
 *
 * @author User
 */

@EnableJpaRepositories("ceni.endra.*")
@ComponentScan(basePackages = { "ceni.endra.*" })
@EntityScan(basePackages = {"ceni.endra.*"})  
@Entity
@Table(name="travelAgent", schema="public")
public class TravelAgent implements Serializable, Representable{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = -4759719341634290858L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "idAgent", nullable = false)
	private Integer idAgent;
	
	@Column(name = "agentName")
    private String agentName;
	
	@Column(name = "agentSurname")
    private String agentSurname;
	
	@Column(name = "salary")
    private Integer salary;
	
    @OneToOne(mappedBy = "travelAgent")
	private Login loginCredentials;
    
    @OneToOne(mappedBy = "travelAgent")
	private Reservation reservation;
    
    public TravelAgent() {
    }

	public Integer getIdAgent() {
		return idAgent;
	}

	public void setIdAgent(Integer idAgent) {
		this.idAgent = idAgent;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public String getAgentSurname() {
		return agentSurname;
	}

	public void setAgentSurname(String agentSurname) {
		this.agentSurname = agentSurname;
	}

	public Integer getSalary() {
		return salary;
	}

	public void setSalary(Integer salary) {
		this.salary = salary;
	}
	
	@Override
	public String toString() {
		return new Gson().toJson(this);
	}

	@Override
	public String createDummyObjectForRepresentation() {
		
		this.idAgent = 0;
		this.agentName = "";
		this.agentSurname = "";
		this.salary = 0;
		
		return this.toString();
	}

    
    
}
