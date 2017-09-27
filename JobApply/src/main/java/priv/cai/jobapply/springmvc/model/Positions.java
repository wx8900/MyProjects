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
@Table(name="Positions")
public class Positions implements Serializable, Comparable<Positions> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2126241894088340887L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Size(min=0, max=10)
	@Column(name = "category", nullable = true)
	private String category; // IT, Sales, Marketing, Accounting
	
	@Size(min=0, max=3)
	@Column(name = "jobtype", nullable = true)
	private String jobtype; //Full-time (171),  Part-time (20), Contract (12), Temporary (3), Internship (2)
	
	@Size(min=0, max=3)
	@Column(name = "experiencelevel", nullable = true)
	private String experiencelevel;  //Mid Level (103), Entry Level (43), Senior Level (14)

	@NotNull
	@Size(min=1, max=100)
	@Column(name = "title", nullable = false)
	private String title;
	
	@Size(min=0, max=50)
	@Column(name = "company", nullable = true)
	private String company;
	
	@Size(min=0, max=50)
	@Column(name = "location", nullable = true)
	private String location;
	
	@Size(min=0, max=10)
	@Column(name = "zipcode", nullable = true)
	private String zipcode;
	
	@Size(min=0, max=40)
	@Column(name = "salary", nullable = true)
	private String salary;
	
	@Size(min=0, max=80)
	@Column(name = "applyemail", nullable = true)
	private String applyemail;
	
	@Size(min=0, max=300)
	@Column(name = "description", nullable = true)
	private String description;
	
	@Size(min=0, max=15)
	@Column(name = "reviews", nullable = true)
	private String reviews;
	
	@Size(min=0, max=1)
	@Column(name = "stars", nullable = true)
	private String stars;
	
	@Size(min=0, max=1)
	@Column(name = "sponsored", nullable = true)
	private String sponsored;
	
	@Size(min=0, max=1)
	@Column(name = "likeit", nullable = true)
	private String likeit;
	
	@Size(min=0, max=15)
	@Column(name = "jobcreated", nullable = true)
	private String jobcreated;
	
	@Size(min=0, max=15)
	@Column(name = "joblastchecked", nullable = true)
	private String joblastchecked;
	
	@Size(min=0, max=25)
	@Column(name = "jobinsertdate", nullable = true)
	private String jobinsertdate;
	
	@Size(min=0, max=25)
	@Column(name = "jobapplydate", nullable = true)
	private String jobapplydate;
	
	@Size(min=0, max=25)
	@Column(name = "responsedate", nullable = true)
	private String responsedate;
	
	@Size(min=0, max=700)
	@Column(name = "titlehref", nullable = true)
	private String titlehref;
	
	@Size(min=0, max=160)
	@Column(name = "companyhref", nullable = true)
	private String companyhref;
	
	@Size(min=0, max=100)
	@Column(name = "comments", nullable = true)
	private String comments;
	
	public Positions() {
		
	}
	
	/*@NotEmpty
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "APP_USER_USER_PROFILE", 
             joinColumns = { @JoinColumn(name = "USER_ID") }, 
             inverseJoinColumns = { @JoinColumn(name = "USER_PROFILE_ID") })
    private Set<UserProfile> userProfiles = new HashSet<UserProfile>();*/

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

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getReviews() {
		return reviews;
	}

	public void setReviews(String reviews) {
		this.reviews = reviews;
	}

	public String getStars() {
		return stars;
	}

	public void setStars(String stars) {
		this.stars = stars;
	}

	public String getSponsored() {
		return sponsored;
	}

	public void setSponsored(String sponsored) {
		this.sponsored = sponsored;
	}

	public String getJobcreated() {
		return jobcreated;
	}

	public void setJobcreated(String jobcreated) {
		this.jobcreated = jobcreated;
	}

	public String getJoblastchecked() {
		return joblastchecked;
	}

	public void setJoblastchecked(String joblastchecked) {
		this.joblastchecked = joblastchecked;
	}

	public String getJobinsertdate() {
		return jobinsertdate;
	}

	public void setJobinsertdate(String jobinsertdate) {
		this.jobinsertdate = jobinsertdate;
	}

	public String getJobapplydate() {
		return jobapplydate;
	}

	public void setJobapplydate(String jobapplydate) {
		this.jobapplydate = jobapplydate;
	}

	public String getApplyemail() {
		return applyemail;
	}

	public void setApplyemail(String applyemail) {
		this.applyemail = applyemail;
	}

	public String getResponsedate() {
		return responsedate;
	}

	public void setResponsedate(String responsedate) {
		this.responsedate = responsedate;
	}

	public String getTitlehref() {
		return titlehref;
	}

	public void setTitlehref(String titlehref) {
		this.titlehref = titlehref;
	}

	public String getCompanyhref() {
		return companyhref;
	}

	public void setCompanyhref(String companyhref) {
		this.companyhref = companyhref;
	}

	public String getLikeit() {
		return likeit;
	}

	public void setLikeit(String likeit) {
		this.likeit = likeit;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((company == null) ? 0 : company.hashCode());
		result = prime * result + id;
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Positions other = (Positions) obj;
		if (company == null) {
			if (other.company != null)
				return false;
		} else if (!company.equals(other.company))
			return false;
		if (id != other.id)
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (salary == null) {
			if (other.salary != null)
				return false;
		} else if (!salary.equals(other.salary))
			return false;
		if (sponsored == null) {
			if (other.sponsored != null)
				return false;
		} else if (!sponsored.equals(other.sponsored))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public int compareTo(Positions other) {
		if(other.jobapplydate != null) {
			int i = jobapplydate.compareTo(other.jobapplydate);
			if (i != 0) return i;
		}
		
		if(other.jobcreated != null) {
			int i = jobcreated.compareTo(other.jobcreated);
			if (i != 0) return i;
		}
		
		if(other.joblastchecked != null) {
			int i = joblastchecked.compareTo(other.joblastchecked);
			if (i != 0) return i;
		}
		
	    return Integer.compare(id, other.id);
	}

	@Override
	public String toString() {
		return "Positions [id=" + id + ", category=" + category + ", jobtype=" + jobtype + ", experiencelevel="
				+ experiencelevel + ", title=" + title + ", company=" + company + ", location=" + location
				+ ", zipcode=" + zipcode + ", salary=" + salary + ", applyemail=" + applyemail + ", description="
				+ description + ", reviews=" + reviews + ", stars=" + stars + ", sponsored=" + sponsored + ", likeit="
				+ likeit + ", jobcreated=" + jobcreated + ", joblastchecked=" + joblastchecked + ", jobinsertdate="
				+ jobinsertdate + ", jobapplydate=" + jobapplydate + ", responsedate=" + responsedate + ", titlehref="
				+ titlehref + ", companyhref=" + companyhref + ", comments=" + comments + "]";
	}

}
