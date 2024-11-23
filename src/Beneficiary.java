
public class Beneficiary extends User {

	private double amountReceived;

	public Beneficiary() {
		super();
		// To initialize without parameter.
	}

	public Beneficiary(String name, String emailAddress, String contactNumber) {
		super(name, emailAddress, contactNumber);
	}

	public double getAmountReceived() {
		return amountReceived;
	}

	public void setAmountReceived(double amountReceived) {
		this.amountReceived = amountReceived;
	}

	@Override
	public String getDetails() {
		String details = super.getDetails();
		return details + (amountReceived != 0 ? " - ₱" + amountReceived : "");
	}
}
