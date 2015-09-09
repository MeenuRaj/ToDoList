package model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the TLIST database table.
 * 
 */
@Entity
@Table(name="TLIST", schema="TESTDB")
@NamedQuery(name="Tlist.findAll", query="SELECT t FROM Tlist t")
public class Tlist implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long lid;

	private String completed;

	private String description;

	private String duedate;

	@Column(name="L_USERNAME")
	private String lUsername;

	private String status;

	public Tlist() {
	}

	public long getLid() {
		return this.lid;
	}

	public void setLid(long lid) {
		this.lid = lid;
	}

	public String getCompleted() {
		return this.completed;
	}

	public void setCompleted(String completed) {
		this.completed = completed;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDuedate() {
		return this.duedate;
	}

	public void setDuedate(String duedate) {
		this.duedate = duedate;
	}

	public String getLUsername() {
		return this.lUsername;
	}

	public void setLUsername(String lUsername) {
		this.lUsername = lUsername;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}