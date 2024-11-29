
public class Donor extends User {

	private double amountDonated;

	public Donor() {
		super();
		amountDonated = 0;
	}

	public Donor(String name, String emailAddress, String contactNumber, double amountDonated) {
		super(name, emailAddress, contactNumber);
		this.amountDonated = amountDonated;
	}

	public double getAmountDonated() {
		return amountDonated;
	}

	public void setAmountDonated(double amountDonated) {
		this.amountDonated = amountDonated;
	}

	@Override
	public String getDetails() {
		String details = super.getDetails();
		return details + (amountDonated != 0 ? " - P" + amountDonated : "");
	}
}
