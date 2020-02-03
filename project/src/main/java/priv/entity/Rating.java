package priv.entity;

public enum Rating {
	Rating1(1.0d), Rating2(2.0d), Rating3(3.0d), Rating4(4.0d), Rating5(5.0d);
	
	private Double index;

	private Rating(Double number) {
		setIndex(number);
	}

	public Double getIndex() {
		return index;
	}

	public void setIndex(Double index) {
		this.index = index;
	}
	
}