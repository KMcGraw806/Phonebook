package com.kayla.phonebook;

import java.util.Scanner;

public class Main {
	static Scanner in = new Scanner(System.in);
	static Entries entry = new Entries();
	static Person[] person = new Person[0];

	public static void main(String[] args) {
		
		Person p1 = new Person();
		Person p2 = new Person();
		Person p3 = new Person();
		
		p1.getInfo("Frank", "Johnson", "4582367485");
		p1.addAddress("123 A St", "Salem", "OR", "97301");
		p2.getInfo("Mary", "A", "Berry", "7075138759");
		p2.addAddress("8910 Park Ave", "Fairfield", "CA", "94535");
		p3.getInfo("John", "Jacob", "Jingleheimer", "Schmidt", "4064981546");
		p3.addAddress("482 Name Dr", "Great Falls", "MT", "59405");
		
		Entries.addEntry(p1);
		Entries.addEntry(p2);
		Entries.addEntry(p3);
		
		Entries.mainMenu();
	}

	public static void exit() {

	}

}
