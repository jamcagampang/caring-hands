import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CaringHandsManager {

	public static final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

	private List<DonationDrive> pending = new ArrayList<DonationDrive>();
	private List<DonationDrive> done = new ArrayList<DonationDrive>();

	public void printAllDrives() {
		System.out.println("All Pending:");
		for (DonationDrive drive : pending) {
			System.out.println("* " + drive.getName());
			System.out.println("  - Start Date    : " + formatter.format(drive.getStartDate()));
			System.out.println("  - End Date      : " + formatter.format(drive.getEndDate()));
			System.out.println("  - Total Donation: ₱" + drive.totalDonation());
		}
		if (pending.isEmpty()) {
			System.out.println("* None Yet");
		}

		System.out.println("All Done:");
		for (DonationDrive drive : done) {
			System.out.println("* " + drive.getName());
			System.out.println("  - Start Date    : " + formatter.format(drive.getStartDate()));
			System.out.println("  - End Date      : " + formatter.format(drive.getEndDate()));
			System.out.println("  - Total Donation: ₱" + drive.totalDonation());
		}
		if (done.isEmpty()) {
			System.out.println("* None Yet");
		}
	}

	public boolean hasOngoingDrive() {
		return !pending.isEmpty();
	}

	public void addDrive(String name, Date startDate, Date endDate) {
		DonationDrive drive = new DonationDrive(name, startDate, endDate);
		pending.add(drive);
	}

	public void printAllDrivesWithIndex() {
		printAllDrivesWithIndex(false);
	}

	public void printAllDrivesWithIndex(boolean past) {
		System.out.println(past ? "All Done" : "All Pending:");
		List<DonationDrive> list = past ? done : pending;
		for (int i = 0; i < list.size(); i++) {
			DonationDrive drive = list.get(i);
			System.out.println(i + " - " + drive.getName());
		}

		if (list.size() == 0) {
			System.out.println("No Donation Drive Yet");
		}
	}

	public boolean driveExists(int index, List<DonationDrive> list) {
		return index >= 0 && index < list.size();
	}

	public boolean isActiveDrive(int index) {
		return index >= 0 && index < pending.size();
	}

	public boolean hasDonor(int index) {
		return isActiveDrive(index) && pending.get(index).hasDonor();
	}

	public boolean hasBeneficiary(int index) {
		return isActiveDrive(index) && pending.get(index).hasBeneficiary();
	}

	public boolean cancelDrive(int index) {

		if (!driveExists(index, pending)) {
			return false;
		}

		pending.remove(index);
		return true;
	}

	public boolean printAllDonors(int index) {
		return printAllDonors(index, false);
	}

	public boolean printAllDonors(int index, boolean forUpdate) {

		if (!driveExists(index, pending)) {
			return false;
		}

		DonationDrive drive = pending.get(index);
		System.out.println("Donors of " + drive.getName() + ":");
		drive.printAllDonors(forUpdate);
		return true;
	}

	public boolean printAllBeneficiaries(int index) {
		return printAllBeneficiaries(index, false);
	}

	public boolean printAllBeneficiaries(int index, boolean forUpdate) {

		if (!driveExists(index, pending)) {
			return false;
		}

		DonationDrive drive = pending.get(index);
		System.out.println("Beneficiaries of " + drive.getName() + ":");
		drive.printAllBeneficiaries(forUpdate);
		return true;
	}

	public void addDonor(int index, String name, String emailAddress, String contactNumber, double amount) {
		Donor donor = new Donor(name, emailAddress, contactNumber, amount);
		DonationDrive drive = pending.get(index);
		drive.addDonor(donor);
	}

	public void addBeneficiary(int index, String name, String emailAddress, String contactNumber) {
		Beneficiary beneficiary = new Beneficiary(name, emailAddress, contactNumber);
		DonationDrive drive = pending.get(index);
		drive.addBeneficiary(beneficiary);
	}

	public boolean removeDonor(int driveIndex, int userIndex) {

		if (!driveExists(driveIndex, pending)) {
			return false;
		}

		DonationDrive drive = pending.get(driveIndex);
		return drive.removeDonor(userIndex);
	}

	public boolean removeBeneficiary(int driveIndex, int userIndex) {

		if (!driveExists(driveIndex, pending)) {
			return false;
		}

		DonationDrive drive = pending.get(driveIndex);
		return drive.removeBeneficiary(userIndex);
	}

	public boolean disburseDonation(int index) {

		if (!driveExists(index, pending)) {
			return false;
		}

		DonationDrive drive = pending.get(index);
		drive.disburseDonation();

		done.add(drive);
		pending.remove(drive);
		return true;
	}

	public boolean seePastDisbursement(int index) {

		if (!driveExists(index, done)) {
			return false;
		}

		DonationDrive drive = done.get(index);
		drive.displayPastDisbursement();
		return true;
	}
}
