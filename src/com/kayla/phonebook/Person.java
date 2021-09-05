package com.kayla.phonebook;

public class Person extends Address {

	private String firstName;
	private String secondName;
	private String thirdName;
	private String fourthName;
	private String lastName;
	private String phoneNumber;
	private int entryNumber;
	private Address address;

	public Person() {
		super();
	}

	public void getInfo(String firstName, String lastName, String phoneNumber) {

		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
	}

	public void getInfo(String firstName, String secondName, String lastName, String phoneNumber) {

		this.firstName = firstName;
		this.secondName = secondName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
	}

	public void getInfo(String firstName, String secondName, String thirdName, String lastName, String phoneNumber) {

		this.firstName = firstName;
		this.secondName = secondName;
		this.thirdName = thirdName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
	}

	public void getInfo(String firstName, String secondName, String thirdName, String fourthName, String lastName,
			String phoneNumber) {
		this.firstName = firstName;
		this.secondName = secondName;
		this.thirdName = thirdName;
		this.fourthName = fourthName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public String getThirdName() {
		return thirdName;
	}

	public void setThirdName(String thirdName) {
		this.thirdName = thirdName;
	}

	public String getFourthName() {
		return fourthName;
	}

	public void setFourthName(String fourthName) {
		this.fourthName = fourthName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public int getEntryNumber() {
		return entryNumber;
	}

	public void setEntryNumber(int entryNumber) {
		this.entryNumber = entryNumber;
	}

	public String fullName() {
		if (secondName == null && thirdName == null && fourthName == null) {
			return firstName + " " + lastName;
		} else if (thirdName == null && fourthName == null) {
			return firstName + " " + secondName + " " + lastName;
		} else if (fourthName == null) {
			return firstName + " " + secondName + " " + thirdName + " " + lastName;
		} else if (firstName != null && secondName != null && thirdName != null && fourthName != null
				&& lastName != null) {
			return firstName + " " + secondName + " " + thirdName + " " + fourthName + " " + lastName;
		} else {
			return "Invalid name entry";
		}
	}

	public void showInfo() {
		if (secondName == null && thirdName == null && fourthName == null) {
			System.out.println("Name:         " + firstName + " " + lastName);
		} else if (thirdName == null && fourthName == null) {
			System.out.println("Name:         " + firstName + " " + secondName + " " + lastName);
		} else if (fourthName == null) {
			System.out.println("Name:         " + firstName + " " + secondName + " " + thirdName + " " + lastName);
		} else if (firstName != null && secondName != null && thirdName != null && fourthName != null
				&& lastName != null) {
			System.out.println("Name:         " + firstName + " " + secondName + " " + thirdName + " " + fourthName
					+ " " + lastName);
		} else {
			System.out.println("Invalid name entry");
		}
		String pNumber = convertPhoneNumber(phoneNumber);
		System.out.println("Phone Number: " + pNumber);
		// }

		// public void showAddress() {
		if (getStreetAddress() != null) {
			System.out.println("Address:      " + getStreetAddress());
			System.out.println("              " + getCity() + ", " + getState() + " " + getZipCode());
		}
	}

	public void showName() {
		System.out.println("Entry #" + entryNumber + ": " + fullName());
	}

	public static String convertPhoneNumber(String phone) {
		char[] digits = phone.toCharArray();

		String area = "";
		String middle = "";
		String end = "";
		for (int i = 0; i < 3; i++) {
			area += digits[i];
		}
		for (int i = 3; i < 6; i++) {
			middle += digits[i];
		}
		for (int i = 6; i < 10; i++) {
			end += digits[i];
		}

		return "(" + area + ")" + middle + "-" + end;
	}

	public void addAddress(String streetAddress, String city, String state, String zipCode) {
		this.setStreetAddress(streetAddress);
		this.setCity(city);
		this.setState(state);
		this.setZipCode(zipCode);
	}

}
