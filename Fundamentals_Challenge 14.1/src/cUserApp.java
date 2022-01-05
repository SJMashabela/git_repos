import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class cUserApp {

	private static int count = 0;

	private static Scanner sc = new Scanner(System.in);
	private static ArrayList<cUserData> arrUser = new ArrayList();
	private static int monthN[] = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
	private static cUserData objUser = null;

	private static String fname, lname, email, dob;
	private static int age = 0;

	public static void main(String[] args) {

		try {
			while (count != 5) {
				int cnt = showMenu();

				switch (cnt) {

				case 1:
					Add();
					break;
				case 2:
					update();

					break;
				case 3:
					delete();
					break;
				case 4:
					listAll();
					break;
				case 5:
					System.exit(0);
					break;
				}

			}
		} catch (Exception e) {

		}

	}

	// delete user

	public static void delete() {

		System.out.println("Enter your email: ");
		String temp = sc.next();

		int i;
		for (i = 0; i < arrUser.size(); i++) {

			if (arrUser.get(i).getEmail() == temp) { // does not work -- must be fixed.

				System.out.println("Deleted!");

			} else {

				System.out.println("Email not found");
			}
		}

	}

	// update user
	// takes in email
	public static void update() {

		System.out.println("Enter your email: ");
		String temp = sc.next();

		int i;
		for (i = 0; i < arrUser.size(); i++) {

			if (temp == arrUser.get(i).getEmail()) // does not work -- must be fixed.
			{
				System.out.println("Enter updated name: ");
				fname = sc.next();

				System.out.println("Enter updated surname: ");
				lname = sc.next();

				System.out.println("Enter updated email: ");
				email = sc.next();

				System.out.println("Enter updated date of birth: ");
				dob = sc.next();

				System.out.println("Updated successfully!");

				// validate date of birth
				String date = objUser.getDob();
				int day = Integer.parseInt(date.split("/")[0]);
				int mon = Integer.parseInt(date.split("/")[1]);
				int year = Integer.parseInt(date.split("/")[2]);

				if (!isValidDate(day, mon, year)) {
					System.out.println();
				} else {

					// calculate age
					age = 2022 - year;
					objUser.setAge(age);
				}
				objUser = new cUserData(fname, lname, email, dob, age);

				// add values to array list

				arrUser.get(i).setFname(objUser.getFname());
				arrUser.get(i).setLname(objUser.getLname());
				arrUser.get(i).setEmail(objUser.getEmail());
				arrUser.get(i).setDob(objUser.getDob());
				arrUser.get(i).setAge(objUser.getAge());

				System.out.println("Hello " + fname + " " + lname + " your details have been updated.");

			} else {
				System.out.println("Unable to find the email provided.");
			}
		}

	}

	// list users method
	public static ArrayList<cUserData> listAll() {

		int i;
		for (i = 0; i < arrUser.size(); i++) {

			fname = arrUser.get(i).getFname();
			lname = arrUser.get(i).getLname();
			email = arrUser.get(i).getEmail();
			dob = arrUser.get(i).getDob();
			age = arrUser.get(i).getAge();
			objUser = new cUserData(fname, lname, email, dob, age);

			System.out.println(objUser.toString());

		}

		return arrUser;

	}

	public static cUserData Add() {
		objUser = new cUserData();

		// get user inputs
		System.out.println("Enter your name: ");
		fname = sc.next();
		objUser.setFname(fname);

		System.out.println("Enter your surname: ");
		lname = sc.next();
		objUser.setLname(lname);

		System.out.println("Enter your email: ");
		email = sc.next();
		objUser.setEmail(email);

		System.out.println("Enter your date of birth(dd/mm/yyyy)");
		dob = sc.next();
		objUser.setDob(dob);

		// validate date of birth
		String date = objUser.getDob();
		int day = Integer.parseInt(date.split("/")[0]);
		int mon = Integer.parseInt(date.split("/")[1]);
		int year = Integer.parseInt(date.split("/")[2]);

		if (!isValidDate(day, mon, year)) {
			System.out.println();
		} else {
			// calculate age
			age = 2022 - year;
			objUser.setAge(age);

			// add values to array list

			arrUser.add(objUser);
			System.out.println("Hello " + fname + " " + lname + " your details have been saved to the database");
		}

		return objUser;
	}

	public static boolean isLeapYear(int yy) {
		if (yy % 400 == 0) {
			return true;
		} else if (yy % 100 == 0) {
			return false;
		} else {
			return yy % 4 == 0;
		}

	}

	public static boolean isValidDate(int dd, int mm, int yy) {
		if (dd < 1 || mm < 1 || yy < 1 || mm > 12) {
			return false;
		} else if (mm == 2 && isLeapYear(yy)) {
			return dd <= 29;
		} else {
			return dd <= monthN[mm];
		}
	}

	public static int showMenu() {

		System.out.println("=========================");
		System.out.println("1 - Add a user");
		System.out.println("2 - Update a user");
		System.out.println("3 - Delete a user");
		System.out.println("4 - List all users");
		System.out.println("5 - Exit");
		count = sc.nextInt();

		return count;
	}
}
