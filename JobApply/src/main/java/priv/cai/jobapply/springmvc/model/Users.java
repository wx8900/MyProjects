package priv.cai.jobapply.springmvc.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="Users")
public class Users implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Size(min=0, max=10)
	@Column(name = "category", nullable = true)
	private String category; // IT, Sales, Marketing, Accounting
	
	@Size(min=0, max=1)
	@Column(name = "jobtype", nullable = true)
	private String jobtype; //Full-time (171),  Part-time (20), Contract (12), Temporary (3), Internship (2)
	
	@NotNull
	@Size(min=1, max=50)
	@Column(name = "username", nullable = false)
	private String username;
	
	@NotNull
	@Size(min=1, max=50)
	@Column(name = "password", nullable = false)
	private String password;
	
	@Size(min=0, max=1)
	@Column(name = "experiencelevel", nullable = true)
	private String experiencelevel;  //Mid Level (103), Entry Level (43), Senior Level (14)

	@Size(min=1, max=100)
	@Column(name = "title", nullable = true)
	private String title;
	
	@Size(min=0, max=50)
	@Column(name = "state", nullable = true)
	private String state;
	
	@Size(min=0, max=50)
	@Column(name = "city", nullable = true)
	private String city;
	
	@Size(min=0, max=50)
	@Column(name = "street", nullable = true)
	private String street;
	
	@Size(min=0, max=10)
	@Column(name = "zipcode", nullable = true)
	private String zipcode;
	
	@Size(min=0, max=100)
	@Column(name = "email", nullable = true)
	private String email;
	
	@Size(min=0, max=1)
	@Column(name = "valid", nullable = true)
	private String valid;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getJobtype() {
		return jobtype;
	}

	public void setJobtype(String jobtype) {
		this.jobtype = jobtype;
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

	public String getExperiencelevel() {
		return experiencelevel;
	}

	public void setExperiencelevel(String experiencelevel) {
		this.experiencelevel = experiencelevel;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getValid() {
		return valid;
	}

	public void setValid(String valid) {
		this.valid = valid;
	}
}
