import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class cUserApp {

	private static String temp;
	private static int monthN[] = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

	private static Scanner sc = new Scanner(System.in);
	private static ArrayList<cUser> arrUser = new ArrayList<cUser>();
	private static cUser objUser = null;

	private static String fname, lname, email, dob;
	private static int age = 0;

	private static int count;

	private static String path = "User_Database_Serializable/src/";

	public static void main(String[] args) {
		
		try {
			
			
			
			while (count != 5) {
				showMenu();

				if (count == 1) {
					Add();
					// showMenu();
				} else if (count == 2) {
					update();
					// showMenu();
				} else if (count == 3) {
					delete();
					// showMenu();
				} else if (count == 4) {
					listAll();
					// showMenu();
				} else {
					System.out.println("Goodbye...");
				}

			}
			
			
		}catch(Exception e) {
			
			System.out.println("Error: " + e.getMesssage());
		}

	}

	// write to file
	public static void serialiseToFile(cUser object, String filePath) throws IOException {

		FileOutputStream fileOutputStream = new FileOutputStream(filePath);
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
		objectOutputStream.writeObject(object);
		objectOutputStream.flush();
		objectOutputStream.close();

	}

	// read from file
	public static ArrayList<cUser> deserializeFromFile(String filePath)
			throws IOException, ClassCastException, ClassNotFoundException {

		FileInputStream fileInputStream = new FileInputStream("UserData.txt");
		ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

		objUser = new cUser();
		objUser = (cUser) objectInputStream.readObject();

		if (arrUser.contains(objUser) == false) {
			arrUser.add(objUser);
		}

		objectInputStream.close();

		return arrUser;

	}

	// delete user

	public static void delete() {
		String msg = "";
		System.out.println("Enter email: ");
		String search = sc.next();

		for (cUser user : arrUser) {

			if (user.getEmail() == search) {

				arrUser.remove(user);

				int i = arrUser.indexOf(user);

				try {

					serialiseToFile(arrUser.get(i), path);

				} catch (IOException e) {

					System.out.println(e.getMessage());

				}

				msg = "User was deleted.";

			} else {
				msg = "User was not found";
			}
		}

		System.out.println(msg);

	}

	// update user
	// takes in email
	public static void update() {
		String msg = "";

		// get email from user to be updated

		System.out.println();
		String temp = sc.next();

		for (cUser user : arrUser) {
			int i = arrUser.indexOf(user);

			if (user.getEmail() == temp) {
				// remove the user once found
				arrUser.remove(user);

				// get new user inputs
				System.out.println("Enter your name: ");
				fname = sc.next();

				System.out.println("Enter your surname: ");
				lname = sc.next();

				System.out.println("Enter updated email: ");
				email = sc.next();

				System.out.println("Enter updated date of birth(dd/mm/yyyy)");
				dob = sc.next();

				// validate date of birth and calculate age
				String date = dob;

				int day = Integer.parseInt(date.split("/")[0]);
				int mon = Integer.parseInt(date.split("/")[1]);
				int year = Integer.parseInt(date.split("/")[2]);

				if (!isValidDate(day, mon, year)) {
					System.out.println("Invalid date of birth");
				} else {

					// calculate age
					age = 2022 - year;
					objUser.setAge(age);
				}
				objUser = new cUser(fname, lname, email, dob, age);

				arrUser.get(i).setFname(objUser.getFname());
				arrUser.get(i).setLname(objUser.getLname());
				arrUser.get(i).setEmail(objUser.getEmail());
				arrUser.get(i).setDob(objUser.getDob());
				arrUser.get(i).setAge(objUser.getAge());

				msg = "Hello " + fname + ", your details were updated";

			} else {

				msg = "User was not found.";
			}

			// write the new array list to file
			try {
				if (!arrUser.contains(user)) {
					serialiseToFile(arrUser.get(i), path);
				}

			} catch (IOException e) {

				System.out.println(e.getMessage());

			}
		}

		System.out.println(msg);

	}

	// list users method
	public static ArrayList<cUser> listAll() {

		int i;
		try {

		} catch (Exception e) {

		}

		for (i = 0; i < arrUser.size(); i++) {

			fname = arrUser.get(i).getFname();
			lname = arrUser.get(i).getLname();
			email = arrUser.get(i).getEmail();
			dob = arrUser.get(i).getDob();
			age = arrUser.get(i).getAge();
			objUser = new cUser(fname, lname, email, dob, age);

			System.out.println(objUser.toString());

		}

		return arrUser;

	}

	public static cUser Add() {

		// get user inputs
		System.out.println("Enter your name: ");
		fname = sc.next();

		System.out.println("Enter your surname: ");
		lname = sc.next();

		System.out.println("Enter your email: ");
		email = sc.next();

		System.out.println("Enter your date of birth(dd/mm/yyyy): ");
		dob = sc.next();

		// validate dob and calculate age
		String date = dob;
		int day = Integer.parseInt(date.split("/")[0]);
		int mon = Integer.parseInt(date.split("/")[1]);
		int year = Integer.parseInt(date.split("/")[2]);

		if (!isValidDate(day, mon, year)) {
			System.out.println("Invalid date of birth");
		} else {

			// calculate age
			age = 2022 - year;
			objUser.setAge(age);
		}
		
		objUser = new cUser();
		objUser.setFname(fname);
		objUser.setLname(lname);
		objUser.setEmail(email);
		objUser.setDob(dob);
		objUser.setAge(age);

		arrUser.add(objUser);

		arrUser.add(objUser);
		
		/// write the new array list to file
		try {
			int i;
			for(i =0; i < arrUser.size(); i++){
			serialiseToFile(arrUser.get(i), path);}

		} catch (IOException ex) {

			System.out.println(ex.getMessage());

		}
		
		
		System.out.println("Hello " + fname + " " + lname + " your details have been saved to the database");

		return objUser;
	}

	// check if date is a leap year
	public static boolean isLeapYear(int yy) {
		if (yy % 400 == 0) {
			return true;
		} else if (yy % 100 == 0) {
			return false;
		} else {
			return yy % 4 == 0;
		}

	}

	// check validity of the date
	public static boolean isValidDate(int dd, int mm, int yy) {
		if (dd < 1 || mm < 1 || yy < 1 || mm > 12) {
			return false;
		} else if (mm == 2 && isLeapYear(yy)) {
			return dd <= 29;
		} else {
			return dd <= monthN[mm];
		}
	}

	// Display menu
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
