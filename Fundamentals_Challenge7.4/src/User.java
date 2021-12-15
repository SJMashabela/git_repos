import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import java.time.format.DateTimeFormatter;

public class User {
	public static void main(String[] args) {
		UserInfo user = new UserInfo();
		//DateTimeFormatter dtf = null;
		String year = null;

		Scanner sc = new Scanner(System.in);

		try {

			System.out.println("What is your name...");
			user.setName(sc.nextLine());
			user.getName().trim();
			
			System.out.println("What is your surname...");
			user.setSurname(sc.nextLine());
			user.getName().trim();

			System.out.println("Enter your date of birth (DD/MM/YYYY)...");
			user.setDate_of_birth(sc.nextLine());

			System.out.println("How far is favorite store from your home? (KM)");
			user.setDist(sc.nextInt());

		
			SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");

			sdf.setLenient(false);
			// Validate dob

			Date date = sdf.parse(user.getDate_of_birth());
			date.toString();

		} catch (Exception e) {

			System.out.println("Error: " + e);

		}

		year = user.getDate_of_birth().split("/")[2];

		// Calculate age

		user.setAge(2021 - Integer.parseInt(year));

		// Print outputs
		System.out.println("Hello " + user.getName() + " " + user.getSurname());
		System.out.println("You are " + user.getAge() + " years old.");

		// Convert distance to miles
		double mileDist = user.getDist() * 0.621371;

		System.out.println("Distance to your favorute store(miles): " + mileDist);

		// Convert distance to meters
		double meterDist = user.getDist() * 1000;
		System.out.println("Distance to your favorute store(meters): " + meterDist);

		// Convert age to seconds
		int ageSec = user.getAge() * 31556952;

		System.out.println("Age(seconds): " + ageSec);

		// Convert age to milliseconds
		int ageMilli = (int) (user.getAge() * 31556952000l);
		System.out.println("Age(milliseconds): " + ageMilli);

		// Convert year to binary

		int bday = Integer.parseInt(year);
		// convert birthday to binary
		String bdayBin = Integer.toBinaryString(bday);
		System.out.println("Birthday in binary: " + bdayBin);

		// convert birthday to octal
		String bdayOct = Integer.toOctalString(bday);
		System.out.println("Birthday in Octal: " + bdayOct);

		// convert birthday to hexadecimal
		String bdayHex = Integer.toHexString(bday);
		System.out.println("Birthday in Hexadecimal: " + bdayHex);

		sc.close();

	}

}
