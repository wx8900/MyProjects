/**
 * 
 */
package priv.entity;

public class Review {
	
	private String reviewerID;
	private String asin;
	private String reviewerName;
	private String helpful;
	private String reviewText;
	private String overall;
	private String summary;
	private String unixReviewTime;
	private String reviewTime;

	public  Review () {
	}
	
	public Review(String reviewerID, String asin, String reviewerName,
			String helpful, String reviewText, String overall, String summary,
			String unixReviewTime, String reviewTime) {
		super();
		this.reviewerID = reviewerID;
		this.asin = asin;
		this.reviewerName = reviewerName;
		this.helpful = helpful;
		this.reviewText = reviewText;
		this.overall = overall;
		this.summary = summary;
		this.unixReviewTime = unixReviewTime;
		this.reviewTime = reviewTime;
	}

	public String getReviewerID() {
		return reviewerID;
	}

	public void setReviewerID(String reviewerID) {
		this.reviewerID = reviewerID;
	}

	public String getAsin() {
		return asin;
	}

	public void setAsin(String asin) {
		this.asin = asin;
	}

	public String getReviewerName() {
		return reviewerName;
	}

	public void setReviewerName(String reviewerName) {
		this.reviewerName = reviewerName;
	}

	public String getHelpful() {
		return helpful;
	}

	public void setHelpful(String helpful) {
		this.helpful = helpful;
	}

	public String getReviewText() {
		return reviewText;
	}

	public void setReviewText(String reviewText) {
		this.reviewText = reviewText;
	}

	public String getOverall() {
		return overall;
	}

	public void setOverall(String overall) {
		this.overall = overall;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getUnixReviewTime() {
		return unixReviewTime;
	}

	public void setUnixReviewTime(String unixReviewTime) {
		this.unixReviewTime = unixReviewTime;
	}

	public String getReviewTime() {
		return reviewTime;
	}

	public void setReviewTime(String reviewTime) {
		this.reviewTime = reviewTime;
	}

	@Override
	public String toString() {
		return "Review [reviewerID=" + reviewerID + ", asin=" + asin
				+ ", reviewerName=" + reviewerName + ", helpful=" + helpful
				+ ", reviewText=" + reviewText + ", overall=" + overall
				+ ", summary=" + summary + ", unixReviewTime=" + unixReviewTime
				+ ", reviewTime=" + reviewTime + "]";
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
