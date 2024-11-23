import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DonationDrive {

	private String name;
	private Date startDate;
	private Date endDate;
	private List<Donor> donors = new ArrayList<Donor>();
	private List<Beneficiary> beneficiaries = new ArrayList<Beneficiary>();

	public DonationDrive() {

	}

	public DonationDrive(String name, Date startDate, Date endDate) {
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public boolean hasDonor() {
		return !donors.isEmpty();
	}

	public boolean hasBeneficiary() {
		return !beneficiaries.isEmpty();
	}

	public boolean addDonor(Donor donor) {

		if (donorExists(donor.getName())) {
			return false;
		}

		donors.add(donor);

		return true;
	}

	public boolean donorExists(String name) {

		if (name == null) {
			return true;
		}

		for (Donor donor : donors) {
			if (donor.getName().equalsIgnoreCase(name)) {
				return true;
			}
		}

		return false;
	}

	public boolean removeDonor(int index) {

		if (index < 0 || index >= donors.size()) {
			return false;
		}

		donors.remove(index);

		return true;
	}

	public boolean addBeneficiary(Beneficiary beneficiary) {

		if (beneficiaryExists(beneficiary.getName())) {
			return false;
		}

		beneficiaries.add(beneficiary);

		return true;
	}

	public boolean beneficiaryExists(String name) {

		if (name == null) {
			return true;
		}

		for (Beneficiary beneficiary : beneficiaries) {
			if (beneficiary.getName().equalsIgnoreCase(name)) {
				return true;
			}
		}

		return false;
	}

	public boolean removeBeneficiary(int index) {

		if (index < 0 || index >= beneficiaries.size()) {
			return false;
		}

		beneficiaries.remove(index);

		return true;
	}

	public double totalDonation() {
		double total = 0;
		for (Donor donor : donors) {
			total += donor.getAmountDonated();
		}
		return total;
	}

	public void printAllDonors(boolean forUpdate) {

		for (int i = 0; i < donors.size(); i++) {

			Donor donor = donors.get(i);

			if (forUpdate) {
				System.out.println(donor.getIndexedDetails(i));
			} else {
				System.out.println(donor.getDetails());
			}
		}

		if (donors.size() == 0) {
			System.out.println("No Donor Yet");
		}
	}

	public void printAllBeneficiaries(boolean forUpdate) {

		for (int i = 0; i < beneficiaries.size(); i++) {

			Beneficiary beneficiary = beneficiaries.get(i);

			if (forUpdate) {
				System.out.println(beneficiary.getIndexedDetails(i));
			} else {
				System.out.println(beneficiary.getDetails());
			}
		}

		if (beneficiaries.size() == 0) {
			System.out.println("No Beneficiary Yet");
		}
	}

	public void disburseDonation() {

		double totalDonation = totalDonation();
		System.out.println("Total Donation: ₱" + totalDonation);

		double amountEach = Math.floor(totalDonation / beneficiaries.size());
		System.out.println("Each Will Get: ₱" + amountEach);

		System.out.println("List of Beneficiaries:");
		for (Beneficiary benificiary : beneficiaries) {
			benificiary.setAmountReceived(amountEach);
			System.out.println(benificiary.getDetails());
		}
	}

	public void displayPastDisbursement() {
		System.out.println("Total Donation: ₱" + totalDonation());
		System.out.println("Donors:");
		printAllDonors(false);
		System.out.println("Beneficiaries:");
		printAllBeneficiaries(false);
	}
}
