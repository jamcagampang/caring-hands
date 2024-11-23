
public abstract class User {

	private String name;
	private String emailAddress;
	private String contactNumber;

	public User() {
		// To initialize without parameter.
	}

	public User(String name, String emailAddress, String contactNumber) {
		this.name = name;
		this.emailAddress = emailAddress;
		this.contactNumber = contactNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getDetails() {
		String finalEmailAddress = emailAddress == null ? "N/A" : emailAddress;
		String finalContactNumber = contactNumber == null ? "N/A" : contactNumber;
		return "* " + name + " (" + finalEmailAddress + ", " + finalContactNumber + ")";
	}
	
	public String getIndexedDetails(int index) {
		return index + " - " + name;
	}
}
