import java.util.Date;
import java.util.Scanner;

public class Main {

	public static final Scanner scanner = new Scanner(System.in);

	public static final CaringHandsManager caringHandsManager = new CaringHandsManager();

	public static void main(String[] args) {
		System.out.println("========================================");
		System.out.println("=    CARING HANDS MANAGEMENT SYSTEM    =");

		boolean programRunning = true;
		while (programRunning) {
			programRunning = mainMenu();
		}
	}

	public static boolean mainMenu() {
		System.out.println("========================================");
		System.out.println(" 1. See Donation Drives");
		System.out.println(" 2. Start Donation Drive");
		System.out.println(" 3. Cancel Donation Drive");
		System.out.println(" 4. Check Donation Drive");
		System.out.println(" 5. Disburse Donation");
		System.out.println(" 6. See Past Disbursement");
		System.out.println("99. End Program");
		String choice = safeRetrieveInput("Choose the operation: ");
		System.out.println("++++++++++++++++++++++++++++++++++++++++");

		if (choice == null) {
			return true;
		}

		int choiceCode = -1;
		try {
			choiceCode = Integer.parseInt(choice);
		} catch (Exception e) {
			return true;
		}

		switch (choiceCode) {
		case 1:
			seeDonationDrives();
			break;
		case 2:
			startDonationDrive();
			break;
		case 3:
			cancelDonationDrive();
			break;
		case 4:
			showInnerMenu();
			break;
		case 5:
			disburseDonation();
			break;
		case 6:
			seePastDisbursements();
			break;
		case 99:
			return false;
		}

		return true;
	}

	private static void seeDonationDrives() {
		System.out.println("========================================");
		caringHandsManager.printAllDrives();
		System.out.println("++++++++++++++++++++++++++++++++++++++++");
	}

	private static void startDonationDrive() {
		System.out.println("========================================");
		String name = safeRetrieveString("Input Name                   : ");
		Date startDate = safeRetrieveDate("Input Start Date (dd-MM-yyyy): ");
		Date endDate = safeRetrieveDate("Input End Date   (dd-MM-yyyy): ");
		caringHandsManager.addDrive(name, startDate, endDate);
		System.out.println("Donation drive registered successfully!");
		System.out.println("++++++++++++++++++++++++++++++++++++++++");
	}

	private static void cancelDonationDrive() {
		System.out.println("========================================");
		if (caringHandsManager.hasOngoingDrive()) {

			caringHandsManager.printAllDrivesWithIndex();

			boolean hasFound = false;
			while (!hasFound) {

				int index = safeRetrieveInt("Please input the drive number (-1 to go back to main menu): ");
				if (index == -1) {
					break;
				}

				hasFound = caringHandsManager.cancelDrive(index);
			}

			if (hasFound) {
				System.out.println("Donation drive cancelled successfully!");
			}
		} else {
			System.out.println("No ongoing drives.");
		}
		System.out.println("++++++++++++++++++++++++++++++++++++++++");
	}

	public static void disburseDonation() {
		System.out.println("========================================");
		caringHandsManager.printAllDrivesWithIndex();
		boolean hasFound = false;
		while (!hasFound) {
			int index = safeRetrieveInt("Please input the drive number (-1 to go back to previous menu): ");
			if (index == -1) {
				break;
			}
			hasFound = caringHandsManager.hasDonor(index) && caringHandsManager.hasBeneficiary(index);
			if (hasFound) {
				caringHandsManager.disburseDonation(index);
				System.out.println("Donation has been disbursed!");
			}
		}
		System.out.println("++++++++++++++++++++++++++++++++++++++++");
	}

	public static void seePastDisbursements() {
		System.out.println("========================================");
		caringHandsManager.printAllDrivesWithIndex(true);
		boolean hasFound = false;
		while (!hasFound) {
			int index = safeRetrieveInt("Please input the drive number (-1 to go back to previous menu): ");
			if (index == -1) {
				break;
			}
			hasFound = caringHandsManager.seePastDisbursement(index);
		}
		System.out.println("++++++++++++++++++++++++++++++++++++++++");
	}

	private static void showInnerMenu() {
		System.out.println("========================================");
		System.out.println(" 1. See Donors");
		System.out.println(" 2. See Beneficiaries");
		System.out.println(" 3. Add Donor");
		System.out.println(" 4. Add Beneficiary");
		System.out.println(" 5. Remove Donor");
		System.out.println(" 6. Remove Beneficiary");
		System.out.println("99. Back To Main Menu");
		String choice = safeRetrieveInput("Choose the operation: ");
		System.out.println("++++++++++++++++++++++++++++++++++++++++");

		int choiceCode = -1;
		try {
			choiceCode = Integer.parseInt(choice);
		} catch (Exception e) {
			// Do Nothing
		}

		switch (choiceCode) {
		case 1:
			seeDonors();
			break;
		case 2:
			seeBeneficiaries();
			break;
		case 3:
			addDonor();
			break;
		case 4:
			addBeneficiary();
			break;
		case 5:
			removeDonor();
			break;
		case 6:
			removeBeneficiary();
			break;
		case 99:
			return;
		}

		showInnerMenu();
	}

	private static void seeDonors() {
		System.out.println("========================================");

		caringHandsManager.printAllDrivesWithIndex();

		boolean hasFound = false;

		while (!hasFound) {

			int index = safeRetrieveInt("Please input the drive number (-1 to go back to previous menu): ");

			if (index == -1) {
				break;
			}

			hasFound = caringHandsManager.printAllDonors(index);
		}

		System.out.println("++++++++++++++++++++++++++++++++++++++++");
	}

	private static void seeBeneficiaries() {
		System.out.println("========================================");

		caringHandsManager.printAllDrivesWithIndex();

		boolean hasFound = false;

		while (!hasFound) {

			int index = safeRetrieveInt("Please input the drive number (-1 to go back to previous menu): ");

			if (index == -1) {
				break;
			}

			hasFound = caringHandsManager.printAllBeneficiaries(index);
		}

		System.out.println("++++++++++++++++++++++++++++++++++++++++");
	}

	public static void addDonor() {
		System.out.println("========================================");
		caringHandsManager.printAllDrivesWithIndex();
		boolean hasFound = false;
		while (!hasFound) {
			int index = safeRetrieveInt("Please input the drive number (-1 to go back to previous menu): ");
			if (index == -1) {
				break;
			}
			hasFound = caringHandsManager.isActiveDrive(index);
			if (hasFound) {
				String name = safeRetrieveString("Input Name: ");
				String email = safeRetrieveString("Email: ");
				String contact = safeRetrieveString("Contact Number: ");
				double amount = safeRetrieveDouble("Input Amount: ");
				caringHandsManager.addDonor(index, name, email, contact, amount);
				System.out.println("Donor added successfully!");
				System.out.println("++++++++++++++++++++++++++++++++++++++++");
			}
		}
	}

	public static void addBeneficiary() {
		System.out.println("========================================");
		caringHandsManager.printAllDrivesWithIndex();
		boolean hasFound = false;
		while (!hasFound) {
			int index = safeRetrieveInt("Please input the drive number (-1 to go back to previous menu): ");
			if (index == -1) {
				break;
			}
			hasFound = caringHandsManager.isActiveDrive(index);
			if (hasFound) {
				String name = safeRetrieveString("Input Name: ");
				String email = safeRetrieveString("Email: ");
				String contact = safeRetrieveString("Contact Number: ");
				caringHandsManager.addBeneficiary(index, name, email, contact);
				System.out.println("Beneficiary added successfully!");
				System.out.println("++++++++++++++++++++++++++++++++++++++++");
			}
		}
	}

	public static void removeDonor() {
		System.out.println("========================================");
		caringHandsManager.printAllDrivesWithIndex();
		boolean hasFound = false;
		while (!hasFound) {
			int index = safeRetrieveInt("Please input the drive number (-1 to go back to previous menu): ");
			if (index == -1) {
				break;
			}
			hasFound = caringHandsManager.hasDonor(index);
			if (hasFound) {
				hasFound = false;
				caringHandsManager.printAllDonors(index, true);
				while (!hasFound) {
					int userIndex = safeRetrieveInt("Select user: ");
					hasFound = caringHandsManager.removeDonor(index, userIndex);
				}
				System.out.println("Donor removed successfully!");
				System.out.println("++++++++++++++++++++++++++++++++++++++++");
			}
		}
	}

	public static void removeBeneficiary() {
		System.out.println("========================================");
		caringHandsManager.printAllDrivesWithIndex();
		boolean hasFound = false;
		while (!hasFound) {
			int index = safeRetrieveInt("Please input the drive number (-1 to go back to previous menu): ");
			if (index == -1) {
				break;
			}
			hasFound = caringHandsManager.hasBeneficiary(index);
			if (hasFound) {
				hasFound = false;
				caringHandsManager.printAllBeneficiaries(index);
				while (!hasFound) {
					int userIndex = safeRetrieveInt("Select user: ");
					hasFound = caringHandsManager.removeBeneficiary(index, userIndex);
				}
				System.out.println("Beneficiary removed successfully!");
				System.out.println("++++++++++++++++++++++++++++++++++++++++");
			}
		}
	}

	public static int safeRetrieveInt(String message) {

		String value = null;

		while (value == null || value.trim().isEmpty()) {
			value = safeRetrieveInput(message);
		}

		try {
			return Integer.parseInt(value);
		} catch (Exception e) {
			return safeRetrieveInt(message);
		}
	}

	public static String safeRetrieveString(String message) {
		String value = null;
		while (value == null || value.trim().isEmpty()) {
			value = safeRetrieveInput(message);
		}
		return value;
	}

	public static Date safeRetrieveDate(String message) {
		Date value = null;
		while (value == null) {
			String raw = safeRetrieveInput(message);
			try {
				value = CaringHandsManager.formatter.parse(raw);
			} catch (Exception e) {
				value = null;
			}
		}
		return value;
	}

	public static double safeRetrieveDouble(String message) {
		double value = -1;
		while (value == -1) {
			String raw = safeRetrieveInput(message);
			try {
				value = Double.parseDouble(raw);
				if (value <= 0) {
					value = -1;
				}
			} catch (Exception e) {
				value = -1;
			}
		}
		return value;
	}

	public static String safeRetrieveInput(String message) {
		try {
			System.out.print(message);
			String text = scanner.nextLine();
			return text;
		} catch (Exception e) {
			return null;
		}
	}
}
