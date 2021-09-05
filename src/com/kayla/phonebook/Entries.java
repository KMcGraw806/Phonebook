package com.kayla.phonebook;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Entries {
	static Scanner in = new Scanner(System.in);
	static Person[] directory = new Person[0];

	public static void mainMenu() {

		System.out.println(" ");
		System.out.println("\tWelcome to the Main Menu! \nPlease choose from the following options:");

		System.out.println("--------------------------------------------");
		System.out.println(" ");

		System.out.println("1. Create a new entry.");
		System.out.println("2. Search for an existing entry.");
		System.out.println("3. Update an existing entry.");
		System.out.println("4. Delete an existing entry.");
		System.out.println("5. Show all existing entries.");
		System.out.println("6. Exit the program.");

		System.out.println(" ");
		System.out.println("--------------------------------------------");
		System.out.println("\tPlease enter your selection: ");
		int selection = in.nextInt();
		in.nextLine();
		System.out.println(" ");

		switch (selection) {
		case 1:
			create();
			break;
		case 2:
			System.out.println(
					"Search by: \n1. First Name \n2. Last Name \n3. Phone Number \n4. City \n5. State \n6. Zip Code");
			int pick = in.nextInt();
			in.nextLine();
			search(pick);
			break;
		case 3:
			System.out.println("Please select the entry you would like to update: ");
			for (Person name : directory) {
				name.showName();
			}
			int entry = in.nextInt();
			in.nextLine();
			updateInfo(entry);

			directory[entry - 1].showInfo();
			System.out.println("Is the information correct? \n1. Yes \n2. No");
			int correct = in.nextInt();
			in.nextLine();

			if (correct == 1) {
				mainMenu();
			} else if (correct == 2) {
				updateInfo(entry);
			}
			break;
		case 4:
			System.out.println("Please select the entry you would like to delete: ");
			System.out.println(" ");
			for (Person name : directory) {
				if (name != null) {
					name.showName();
				}
			}
			int select = in.nextInt();
			in.nextLine();
			deleteEntry(select);
			break;
		case 5:
			showAll();
			break;
		case 6:
			exitProgram();
			break;
		default:
			System.out.println("You have entered an invalid number");
			System.out.println("Please enter a number between 1 - 6");
			mainMenu();
		}
	}

	public static void create() {

		extraSpace();

		Person p = new Person();

		System.out.println("Please enter the full name: ");
		String name = in.nextLine().trim().replaceAll(" +", " ");
		System.out.println(" ");

		String[] fullName = name.split(" ");

		String phone = getPhoneNumber();
		System.out.println(" ");

		if (fullName.length == 2) {
			String first = fullName[0];
			String last = fullName[1];

			p.getInfo(first, last, phone);
		} else if (fullName.length == 3) {
			String first = fullName[0];
			String second = fullName[1];
			String last = fullName[2];

			p.getInfo(first, second, last, phone);
		} else if (fullName.length == 4) {
			String first = fullName[0];
			String second = fullName[1];
			String third = fullName[2];
			String last = fullName[3];

			p.getInfo(first, second, third, last, phone);
		} else if (fullName.length == 5) {
			String first = fullName[0];
			String second = fullName[1];
			String third = fullName[2];
			String fourth = fullName[3];
			String last = fullName[4];

			p.getInfo(first, second, third, fourth, last, phone);
		}

		System.out.println("Do you know the address for this entry? \n1. Yes \n2. No");
		int know = in.nextInt();
		in.nextLine();

		if (know == 1) {
			System.out.println("\n");
			System.out.println("Please enter the street address: ");
			String street = in.nextLine();
			System.out.println(" ");

			System.out.println("Please enter the city: ");
			String city = in.nextLine();
			System.out.println(" ");

			System.out.println("Please enter the state: ");
			String state = in.nextLine();
			System.out.println(" ");

			String zip = getZipCode();

			p.addAddress(street, city, state, zip);
		}

		addEntry(p);
		p.showInfo();
		System.out.println(" ");

		System.out.println("Is this information correct? \n1. Yes \n2. No");
		int done = in.nextInt();
		in.nextLine();
		if (done == 1) {
			System.out.println("\n");
			System.out.println("Your entry has been saved.");
			System.out.println("\n");
			System.out.println("Would you like to add another entry? \n1. Yes \n2. No");
			int another = in.nextInt();
			in.nextLine();
			if (another == 1) {
				create();
			} else if (another == 2) {
				mainMenu();

			} else {
				System.out.println("\n");
				System.out.println(
						"You have made an invalid selection. Your entry has been added. Returning to main menu");
				mainMenu();
			}
		} else if (done == 2) {
			updateInfo(p.getEntryNumber());
		} else {
			System.out.println("You have made an invalid selection. Returning to main menu.");
			mainMenu();
		}
	}

	public static void addEntry(Person person) {

		Person[] placeholder = new Person[directory.length + 1];

		for (int i = 0; i < directory.length; i++) {
			placeholder[i] = directory[i];
		}

		placeholder[placeholder.length - 1] = person;
		directory = placeholder;

		for (int i = 0; i < directory.length; i++) {
			directory[i].setEntryNumber(i + 1);
		}
	}

	public static void search(int input) {

		extraSpace();

		switch (input) {
		case 1:
			System.out.println("What first name would you like to search for?");
			String fName = in.next();
			System.out.println(" ");

			searchByFirst(fName);
//			break;
		case 2:
			System.out.println("What last name would you like to search for?");
			String lName = in.next();

			System.out.println(" ");
			searchByLast(lName);
			break;
		case 3:
			System.out.println("What phone number would you like to search for?");
			String phone = in.next();

			System.out.println(" ");
			searchByPhone(phone);
			break;
		case 4:
			System.out.println("What city would you like to search for?");
			String city = in.next();

			System.out.println(" ");
			searchByCity(city);
			break;
		case 5:
			System.out.println("What state would you like to search for?");
			String state = in.next();

			System.out.println(" ");
			searchByState(state);
			break;
		case 6:
			System.out.println("What zip code would you like to search for?");
			String zCode = in.next();

			System.out.println(" ");
			searchByZipCode(zCode);
			break;
		default:
			System.out.println("You have not entered a valid search parameter");
			System.out.println(
					"Please enter a number between 1 - 6 \n1. First Name \n2. Last Name \n3. Phone Number \n4. City \n5. State \n6. Zip Code");
			int newSearch = in.nextInt();
			in.nextLine();

			search(newSearch);
		}
	}

	public static void searchByFirst(String first) {
		for (int i = 0; i < directory.length; i++) {
			if (directory[i].getFirstName().equals(first)) {
				System.out.println("#" +directory[i].getEntryNumber());
				directory[i].showInfo();
				System.out.println(" ");
			}
		}
		
		System.out.println(" ");
		
		System.out.println("What would you like to do next? \n1. Update entry \n2. Delete entry \n3. Return to main menu");
		int next = in.nextInt();
		in.nextLine();
		
		if(next == 1) {
			System.out.println(" ");
			System.out.println("Please enter the entry number you would like to update:");
			int update = in.nextInt();
			in.nextLine();
			updateInfo(update);
		} else if(next == 2) {
			System.out.println(" ");
			System.out.println("Please enter the entry number you would like to delete:");
			int delete = in.nextInt();
			in.nextLine();
			deleteEntry(delete);
		} else if(next == 3) {
			System.out.println(" ");
			System.out.println("Returning to main menu.");
			mainMenu();
		} else {
			System.out.println(" ");
			System.out.println("You have made an invalid selection. Returning to main menu.");
			mainMenu();
		}
	}

	public static void searchByLast(String last) {
		for (int i = 0; i < directory.length; i++) {
			if (directory[i].getLastName().equals(last)) {
				System.out.println("#" +directory[i].getEntryNumber());
				directory[i].showInfo();
				System.out.println(" ");
			}
		}
		
		System.out.println(" ");
		
		System.out.println("What would you like to do next? \n1. Update entry \n2. Delete entry \n3. Return to main menu");
		int next = in.nextInt();
		in.nextLine();
		
		if(next == 1) {
			System.out.println(" ");
			System.out.println("Please enter the entry number you would like to update:");
			int update = in.nextInt();
			in.nextLine();
			updateInfo(update);
		} else if(next == 2) {
			System.out.println(" ");
			System.out.println("Please enter the entry number you would like to delete:");
			int delete = in.nextInt();
			in.nextLine();
			deleteEntry(delete);
		} else if(next == 3) {
			System.out.println(" ");
			System.out.println("Returning to main menu.");
			mainMenu();
		} else {
			System.out.println(" ");
			System.out.println("You have made an invalid selection. Returning to main menu.");
			mainMenu();
		}
	}

	public static void searchByPhone(String phone) {

		for (int i = 0; i < directory.length; i++) {
			if (directory[i].getPhoneNumber().equals(phone)) {
				System.out.println("#" +directory[i].getEntryNumber());
				directory[i].showInfo();
				System.out.println(" ");
			}
		}
		
		System.out.println(" ");
		
		System.out.println("What would you like to do next? \n1. Update entry \n2. Delete entry \n3. Return to main menu");
		int next = in.nextInt();
		in.nextLine();
		
		if(next == 1) {
			System.out.println(" ");
			System.out.println("Please enter the entry number you would like to update:");
			int update = in.nextInt();
			in.nextLine();
			updateInfo(update);
		} else if(next == 2) {
			System.out.println(" ");
			System.out.println("Please enter the entry number you would like to delete:");
			int delete = in.nextInt();
			in.nextLine();
			deleteEntry(delete);
		} else if(next == 3) {
			System.out.println(" ");
			System.out.println("Returning to main menu.");
			mainMenu();
		} else {
			System.out.println(" ");
			System.out.println("You have made an invalid selection. Returning to main menu.");
			mainMenu();
		}
		
	}

	public static void searchByCity(String city) {
		for (int i = 0; i < directory.length; i++) {
			if (directory[i].getCity().equals(city)) {
				System.out.println("#" +directory[i].getEntryNumber());
				directory[i].showInfo();
				System.out.println(" ");
			}
		}

		System.out.println(" ");
		
		System.out.println("What would you like to do next? \n1. Update entry \n2. Delete entry \n3. Return to main menu");
		int next = in.nextInt();
		in.nextLine();
		
		if(next == 1) {
			System.out.println(" ");
			System.out.println("Please enter the entry number you would like to update:");
			int update = in.nextInt();
			in.nextLine();
			updateInfo(update);
		} else if(next == 2) {
			System.out.println(" ");
			System.out.println("Please enter the entry number you would like to delete:");
			int delete = in.nextInt();
			in.nextLine();
			deleteEntry(delete);
		} else if(next == 3) {
			System.out.println(" ");
			System.out.println("Returning to main menu.");
			mainMenu();
		} else {
			System.out.println(" ");
			System.out.println("You have made an invalid selection. Returning to main menu.");
			mainMenu();
		}
		
	}

	public static void searchByState(String state) {
		for (int i = 0; i < directory.length; i++) {
			if (directory[i].getState().equals(state)) {
				System.out.println("#" +directory[i].getEntryNumber());
				directory[i].showInfo();
				System.out.println(" ");
			}
		}
		
		System.out.println(" ");

		System.out.println("What would you like to do next? \n1. Update entry \n2. Delete entry \n3. Return to main menu");
		int next = in.nextInt();
		in.nextLine();
		
		if(next == 1) {
			System.out.println(" ");
			System.out.println("Please enter the entry number you would like to update:");
			int update = in.nextInt();
			in.nextLine();
			updateInfo(update);
		} else if(next == 2) {
			System.out.println(" ");
			System.out.println("Please enter the entry number you would like to delete:");
			int delete = in.nextInt();
			in.nextLine();
			deleteEntry(delete);
		} else if(next == 3) {
			System.out.println(" ");
			System.out.println("Returning to main menu.");
			mainMenu();
		} else {
			System.out.println(" ");
			System.out.println("You have made an invalid selection. Returning to main menu.");
			mainMenu();
		}
	}

	public static void searchByZipCode(String zipCode) {
		for (int i = 0; i < directory.length; i++) {
			if (directory[i].getZipCode().equals(zipCode)) {
				System.out.println("#" +directory[i].getEntryNumber());
				directory[i].showInfo();
				System.out.println(" ");
			}
		}
		
		System.out.println(" ");

		System.out.println("What would you like to do next? \n1. Update entry \n2. Delete entry \n3. Return to main menu");
		int next = in.nextInt();
		in.nextLine();
		
		if(next == 1) {
			System.out.println(" ");
			System.out.println("Please enter the entry number you would like to update:");
			int update = in.nextInt();
			in.nextLine();
			updateInfo(update);
		} else if(next == 2) {
			System.out.println(" ");
			System.out.println("Please enter the entry number you would like to delete:");
			int delete = in.nextInt();
			in.nextLine();
			deleteEntry(delete);
		} else if(next == 3) {
			System.out.println(" ");
			System.out.println("Returning to main menu.");
			mainMenu();
		} else {
			System.out.println(" ");
			System.out.println("You have made an invalid selection. Returning to main menu.");
			mainMenu();
		}
	}

	public static void sort(Person[] person) {
		for (int i = 1; i < person.length; i++) {
			for (int j = 0; j < person.length - i; j++) {
				if (((person[j].fullName()).compareTo((person[j + 1].fullName()))) > 0) {
					
					Person temp = person[j];
					person[j] = person[j + 1];
					person[j + 1] = temp;
				}
			}
		}

		System.out.println("\n");

		for (Person per : person) {
			per.showInfo();
		}

		System.out.println("What would you like to do next? \n1. Return to main menu \n2. Update entry \n3. Delete entry \n4. Exit the program");
		int next = in.nextInt();
		in.nextLine();
		
		if(next == 1) {
			mainMenu();
		} else if(next == 2) {
			System.out.println("Please enter the entry number you would like to update: ");
			int entry = in.nextInt();
			in.nextLine();
			
			if(entry != person.length) {
				System.out.println("You have entered and invalid selection.");
				mainMenu();
			} else {
				updateInfo(entry);
			}
		} else if(next == 3) {
			System.out.println("Please enter the entry number you would like to delete: ");
			int entry = in.nextInt();
			in.nextLine();
			
			if(entry != person.length) {
				System.out.println("You have entered and invalid selection.");
				mainMenu();
			} else {
				deleteEntry(entry);
			}
		} else if(next == 4) {
			System.out.println("Are you sure you want to exit the program? \n1. Yes \n2. No");
			int sure = in.nextInt();
			in.nextLine();
			
			if(sure == 1) {
				exitProgram();
			} else if(sure == 2) {
				mainMenu();
			} else {
				System.out.println("You have made an invalid selection.");
				mainMenu();
			}
		} else {
			System.out.println("You have made an invalid selection.");
			mainMenu();
		}
	}

	public static void updateInfo(int entryNumber) {
		int index = entryNumber - 1;

		extraSpace();

		directory[index].showInfo();
		System.out.println(" ");
		System.out.println("Please select the item you would like to update: ");
		System.out.println(
				"1. Name \n2. Phone Number \n3. Street Address \n4. City \n5. State \n6. Zip Code \n7. The information is correct");
		System.out.println(" ");

		int selection = in.nextInt();
		in.nextLine();

		switch (selection) {
		case 1:
			updateName(entryNumber);
			break;
		case 2:
			updatePhoneNumber(entryNumber);
			break;
		case 3:
			updateStreetAddress(entryNumber);
			break;
		case 4:
			updateCity(entryNumber);
			break;
		case 5:
			updateState(entryNumber);
			break;
		case 6:
			updateZipCode(entryNumber);
			break;
		case 7:
			mainMenu();
			break;
		default:
			throw new InputMismatchException("Please enter a number between 1 - 8");
		}
	}

	public static void deleteEntry(int entry) {

		extraSpace();

		int index = entry - 1;

		directory[index].showInfo();
		System.out.println(" ");
		System.out.println("Do you wish to delete this entry? \n1. Yes \n2. Select New Entry \n3. Main Menu");
		int confirm = in.nextInt();
		in.nextLine();

		if (confirm == 1) {
			Person[] temp = new Person[directory.length - 1];
			int save = 0;

			if (directory[index] == directory[directory.length - 1]) {
				directory[index] = null;

				for (int i = 0; i < directory.length - 1; i++) {
					if (directory[i] != null) {
						save++;
					}
				}

				for (int j = 0; j < save; j++) {
					temp[j] = directory[j];
				}

				directory = temp;

			} else {
				directory[index] = null;

				for (int i = 0; i < directory.length; i++) {
					if (directory[i] != null) {
						save++;
					} else if (directory[i] == null) {
						directory[i] = directory[directory.length - 1];
						directory[directory.length - 1] = null;
					}
				}

				for (int j = 0; j <= save; j++) {
					temp[j] = directory[j];
				}

				directory = temp;
			}

			for (int i = 0; i < directory.length; i++) {
				directory[i].setEntryNumber(i + 1);
			}

			System.out.println(" ");
			System.out.println("\t\tUpdated Directory");
			System.out.println("----------------------------------------------------");
			for (Person person : directory) {
				System.out.println("Entry #" + person.getEntryNumber());
				person.showInfo();
				System.out.println("----------------------------------------------------");
			}

			System.out.println(" ");
			System.out.println("Would you like to delete another entry? \n1. Yes \n2. No, return to main menu");
			int another = in.nextInt();
			in.nextLine();

			if (another == 1) {
				System.out.println("Please enter the entry you would like to delete: ");
				int newEntry = in.nextInt();
				in.nextLine();
				System.out.println(" ");
				deleteEntry(newEntry);
			} else if (another == 2) {
				System.out.println("Returning to the main menu.");
				mainMenu();
			}

		} else if (confirm == 2) {
			System.out.println(" ");
			System.out.println("Please enter your new selection: ");
			for (Person person : directory) {
				person.getEntryNumber();
				person.showInfo();
				System.out.println(" ");
			}
			int newDelete = in.nextInt();
			in.nextLine();

			if (newDelete <= directory.length) {
				deleteEntry(newDelete);
			}
		} else if (confirm == 3) {
			mainMenu();
		} else {
			System.out.println(" ");
			System.out.println("Invalid selection, returning to main menu.");
			mainMenu();
		}

	}

	public static void showAll() {
		extraSpace();

		System.out.println("\t\t\s\s\s\s\sDirectory");
		System.out.println("----------------------------------------------------");
		for (Person person : directory) {
			System.out.println("Entry #" + person.getEntryNumber());
			person.showInfo();
			System.out.println("----------------------------------------------------");
		}

		System.out.println(" ");
		System.out.println(
				"Would you like to do next? \n1. Return to main menu \n2. Sort entries \n3. Update an entry \n4. Delete an entry \n5. Exit the program");
		System.out.println("--------------------------------------------");
		int whatNext = in.nextInt();
		if (whatNext == 1) {
			mainMenu();
		} else if (whatNext == 2) {
			sort(directory);
		} else if (whatNext == 3) {
			System.out.println("Please select the entry you would like to update: ");
			int entry = in.nextInt();
			in.nextLine();
			updateInfo(entry);
		} else if (whatNext == 4) {
			System.out.println("Please enter the entry number you would like to delete: ");
			int delete = in.nextInt();
			in.nextLine();
			deleteEntry(delete);
		} else if (whatNext == 5) {
			exitProgram();
		}
	}

	public static void updateName(int entryNumber) {
		int index = entryNumber - 1;

		extraSpace();

		System.out.println("Please enter correct name: ");
		String name = in.nextLine().trim().replaceAll(" +", " ");

		System.out.println(" ");
		
		String[] fullName = name.split(" ");

		if (fullName.length == 2) {
			String first = fullName[0];
			String last = fullName[1];
			
			directory[index].setFirstName(first);
			directory[index].setLastName(last);
			directory[index].setSecondName(null);
			directory[index].setThirdName(null);
			directory[index].setFourthName(null);

			System.out.println("Name has been changed to: " + directory[index].fullName());
		} else if (fullName.length == 3) {
			String first = fullName[0];
			String second = fullName[1];
			String last = fullName[2];
			
			directory[index].setFirstName(first);
			directory[index].setSecondName(second);
			directory[index].setLastName(last);
			directory[index].setThirdName(null);
			directory[index].setFourthName(null);

			System.out.println("Name has been changed to: " + directory[index].fullName());
		} else if (fullName.length == 4) {
			String first = fullName[0];
			String second = fullName[1];
			String third = fullName[2];
			String last = fullName[3];
			
			directory[index].setFirstName(first);
			directory[index].setSecondName(second);
			directory[index].setThirdName(third);
			directory[index].setLastName(last);
			directory[index].setFourthName(null);

			System.out.println("Name has been changed to: " + directory[index].fullName());
		} else if (fullName.length == 5) {
			
			String first = fullName[0];
			String second = fullName[1];
			String third = fullName[2];
			String fourth = fullName[3];
			String last = fullName[4];
			
			directory[index].setFirstName(first);
			directory[index].setSecondName(second);
			directory[index].setThirdName(third);
			directory[index].setFourthName(fourth);
			directory[index].setLastName(last);

			System.out.println("Name has been changed to: " + directory[index].fullName());

		} else {
			System.out.println("I just cannot handle this many names...");
		}

		System.out.println(" ");
		confirmEntry(entryNumber);

	}

	public static void updatePhoneNumber(int entryNumber) {
		int index = entryNumber - 1;

		extraSpace();

		System.out.println("Please enter correct phone number: ");
		String pNumber = in.next();

		System.out.println(" ");

		directory[index].setPhoneNumber(pNumber);
		System.out.println("Phone number has been changed to: " + directory[index].getPhoneNumber());

		System.out.println(" ");
		confirmEntry(entryNumber);
	}

	public static void updateStreetAddress(int entryNumber) {
		int index = entryNumber - 1;

		extraSpace();

		System.out.println("Please enter correct street address: ");
		String sAddress = in.nextLine();

		System.out.println(" ");

		directory[index].setStreetAddress(sAddress);
		System.out.println("Street address has been changed to: " + directory[index].getStreetAddress());

		System.out.println(" ");
		confirmEntry(entryNumber);
	}

	public static void updateCity(int entryNumber) {
		int index = entryNumber - 1;

		extraSpace();

		System.out.println("Please enter correct city: ");
		String city = in.nextLine();

		System.out.println(" ");

		directory[index].setCity(city);
		System.out.println("City has been changed to: " + directory[index].getCity());

		System.out.println(" ");
		confirmEntry(entryNumber);
	}

	public static void updateState(int entryNumber) {
		int index = entryNumber - 1;

		extraSpace();

		System.out.println("Please enter correct state: ");
		String state = in.nextLine();

		System.out.println(" ");

		directory[index].setState(state);
		System.out.println("State has been changed to: " + directory[index].getState());

		System.out.println(" ");
		confirmEntry(entryNumber);
	}

	public static void updateZipCode(int entryNumber) {
		int index = entryNumber - 1;

		extraSpace();

		System.out.println("Please enter correct zip code: ");
		String zCode = in.next();
		directory[index].setZipCode(zCode);
		System.out.println("Zip code has been changed to: " + directory[index].getZipCode());

		System.out.println(" ");
		confirmEntry(entryNumber);
	}

	public static void extraSpace() {
		System.out.println("\n");
	}

	public static void exitProgram() {
		extraSpace();
		System.out.println("Are you sure you would like to exit the program \n1. Yes \n2. No");
		int exit = in.nextInt();
		in.nextLine();

		if (exit == 1) {
			System.out.println("Thank you for using the phone book.");
			System.out.println("If you would like to return to the phone book, press enter");
			String space = in.nextLine();

			while (space != null) {
				System.out.println(space);

				if (space.isEmpty()) {

					mainMenu();
					;
				}

				if (in.hasNextLine()) {
					space = in.nextLine();
				} else {
					space = null;
				}
			}
		}

		else if (exit == 2) {
			System.out.println("Returning to the main menu");
			create();
		}
	}

	public static void confirmEntry(int entry) {
		int index = entry - 1;

		System.out.println("Is the following information correct? \n1. Yes \n2. No");
		System.out.println(" ");
		directory[index].showInfo();
		System.out.println(" ");

		int confirm = in.nextInt();
		in.nextLine();
		System.out.println(" ");
		if (confirm == 1) {
			System.out.println("This entry has been updated. Returning to main menu.");
			mainMenu();
		} else if (confirm == 2) {
			updateInfo(entry);
		}
	}

	public static String getPhoneNumber() {
		
		System.out.println("Please enter the 10 digit phone number: ");
		String phone = in.nextLine();
		phone = phone.replace("-", "");
		phone = phone.replace("(", "");
		phone = phone.replace(")", "");

		if (phone.length() == 10) {
			return phone;
		} else {
			System.out.println("\n");
			return getPhoneNumber();
		}
	}

	public static String getZipCode() {
		System.out.println("Please enter the zip code: ");
		String zip = in.nextLine();
		System.out.println(" ");
		
		if(zip.length() != 5) {
			System.out.println("\n");
			return getZipCode();
		} else {
			return zip;
		}
	}
}
