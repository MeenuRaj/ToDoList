package model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the TUSERS database table.
 * 
 */
@Entity
@Table(name="TUSERS", schema="TESTDB")
@NamedQuery(name="Tuser.findAll", query="SELECT t FROM Tuser t")
public class Tuser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	private String password;

	private String username;

	public Tuser() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}