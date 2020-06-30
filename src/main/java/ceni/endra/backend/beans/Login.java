package ceni.endra.backend.beans;

import java.io.Serializable;

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
@Table(name="login", schema="public")
public class Login  implements Serializable, Representable{
    
	/**
	 * 
	 */
	private static final long serialVersionUID = -3998609051479902577L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "idUser", nullable = false)
    private Integer idUser;
	
	@Column(name = "username")
    private String username;
	
	@Column(name = "password")
    private String password;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idAgent", referencedColumnName = "idAgent")
    private TravelAgent travelAgent;

    public Login() {
    }

	public Integer getIdUser() {
		return idUser;
	}

	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public TravelAgent getTravelAgent() {
		return travelAgent;
	}

	public void setTravelAgent(TravelAgent travelAgent) {
		this.travelAgent = travelAgent;
	}

	@Override
	public String toString() {
		return new Gson().toJson(this);
	}

	@Override
	public String createDummyObjectForRepresentation() {
		this.idUser = 0;
		this.username = "";
		this.password = "";
		this.travelAgent = new TravelAgent();
		return this.toString();
	}
}
