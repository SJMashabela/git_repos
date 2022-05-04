import java.util.Scanner;
import java.util.regex.Pattern;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;

public class UserDatabase{
	static Scanner scanner = new Scanner(System.in);
	public static void main(String[] args){
		ArrayList<String> userNames = new ArrayList<String>();
		ArrayList<String> userSurnames = new ArrayList<String>();
		ArrayList<String> dateOfBirth = new ArrayList<String>();
		ArrayList<String> userEmails = new ArrayList<String>();
		
		/*While loop ensure that the program doesn't terminate but keeps 
		showing the user the menu*/
		while(true){
			
			int menuOption = getMenuOption();
			if(menuOption == 1){ //Add option
				addUser(userNames, userSurnames, userEmails, dateOfBirth);
			}else if(menuOption == 2){ //Update option 
				updateUserDetails(userNames, userSurnames, userEmails,
								 dateOfBirth);
			}else if(menuOption == 3){ //Delete option
				deleteUserDetails(userNames, userSurnames, userEmails,
								 dateOfBirth);
				}
			else if(menuOption == 4){	// List Option
				listUsers(userNames, userSurnames, userEmails,
								 dateOfBirth); 
			}
		}	
	}
	
	/*Displays the menu to user and prompt to choose an option
	return a number for a certain menu option.
	*/
	static int getMenuOption(){
		Scanner scannerMenu = new Scanner(System.in);
		
		int option;
		while(true){
			System.out.println("---------------Menu----------------");
			System.out.println("(1) Add \t\t (2) Update \n");
			System.out.println("(3) Delete \t\t (4) List \n");
			System.out.println("-----------------------------------");
			System.out.print("Choose an option(1-4) : ");
			try{
				option = scannerMenu.nextInt();
				break;
				
			}catch(Exception e){
				System.out.println("Option not valid");
				scannerMenu.next();
			}
		}
		return option;
	}
	
	static int updateMenuOption(){
		Scanner scannerMenu = new Scanner(System.in);
		
		int option;
		while(true){
			System.out.println("---------------Update Details----------------");
			System.out.println("(1) Name \t\t (2) Surname \n");
			System.out.println("(3) Email \t\t (4) Date of birth \n");
			System.out.println("(5) All Details \n");
			System.out.println("-----------------------------------");
			System.out.print("Choose an option(1-5) : ");
			try{
				option = scannerMenu.nextInt();
				if(option >= 1 && option <= 5){
					break;
				}
				
			}catch(Exception e){
				System.out.println("Option not valid");
				scannerMenu.next();
			}
		}
		return option;
	}
	
	//Menu Options methods
	
	static void addUser(ArrayList<String> name, ArrayList<String> surname, 
					ArrayList<String> emails, 
					      ArrayList<String> dateOfBirth){
		String userName = getUserName();
		name.add(userName);
		String userSurname = getUserSurname();
		surname.add(userSurname);
		String userEmail = getUserEmail();
		emails.add(userEmail);
		String dob = getUserDOB();
		dateOfBirth.add(dob);
		System.out.println("Hello " + userName + " " +userSurname +
					" your details have been saved. \n");
	}
	
	/*Takes an arraylist of emails and first checks if it's not empty
	then check if the user exists and updates the details.
	*/
	static void updateUserDetails(ArrayList<String> name, ArrayList<String> surname, 
						ArrayList<String> emails, 
					      		ArrayList<String> dateOfBirth){
      		int updateOption;
      		String userName;
      		String userSurname;
      		String userEmail;
      		String dob;
		try{
			lengthOfList(emails);
			System.out.print("Enter your email: ");
			String email = scanner.nextLine();
			int temp = emails.size();
			for(int i = 0; i < emails.size(); i++){
				if(email.toLowerCase().equals(emails.get(i).toLowerCase())){
					updateOption = updateMenuOption();
					if(updateOption == 1){
						userName = getUserName();
						name.set(i, userName);
					}else if(updateOption == 2){
						userSurname = getUserSurname();
						surname.set(i, userSurname);
					}else if(updateOption == 3){
						userEmail = getUserEmail();
						emails.set(i, userEmail);
					}else if(updateOption == 4){
						dob = getUserDOB();
						dateOfBirth.set(i, dob);	
					}else if(updateOption == 5){
						userName = getUserName();
						name.set(i, userName);
						userSurname = getUserSurname();
						surname.set(i, userName);
						userEmail = getUserEmail();
						emails.set(i, userEmail);
						dob = getUserDOB();
						dateOfBirth.set(i, dob);
					}
				}	
			}
		}catch(Exception e){
			System.out.println("No users available");
		}		
	}
	
	/*Takes an arraylist of emails and first checks if it's not empty
	then check if the user exists and deletes user if email matches ones 
	stored in database.
	*/
	static void deleteUserDetails(ArrayList<String> name, ArrayList<String> surname, 
						ArrayList<String> emails, 
					      		ArrayList<String> dateOfBirth){	
					      		
		try{
			lengthOfList(emails);  	//Check size of arraylist
			System.out.print("Enter your email: ");
			String email = scanner.nextLine();
			int temp = emails.size();
			for(int i = 0; i < emails.size(); i++){
				if(email.toLowerCase().equals(emails.get(i).toLowerCase())){
					String tempName = name.get(i);
					String tempsurname = surname.get(i);
					name.remove(i);
					surname.remove(i);
					emails.remove(i);
					dateOfBirth.remove(i);
					System.out.println("User "+ tempName + " " + tempsurname+
								 " has been deleted");
					i--;
				}
			}if(temp == emails.size()){
				System.out.println("User not found!");
			}	 
		}catch(Exception e){
			System.out.println("No users available");
		}			
	}
	
	// List all users
	// If list is empty System.out.println("No Users available");
	static void listUsers(ArrayList<String> names, ArrayList<String> surnames, 
						ArrayList<String> emails, 
					      		ArrayList<String> dateOfBirth){
		try{
			lengthOfList(names);     // Throws Exception if there are no users.
			for(int i = 0; i < names.size(); i++){
				System.out.println(names.get(i) + " " + surnames.get(i) +
						 " " + emails.get(i) + " " + dateOfBirth.get(i));
			}
		}catch(Exception e){
			System.out.println("No users available");
		}
	}
	//End of menu options methods
	
	
	//Start of user details validation methods
	
	/*Takes user name input and check if it's valid and 
	returns the name if it's valid
	*/
	static String getUserName(){
		while(true){
			System.out.print("Enter your name: ");
			String name = scanner.nextLine();
			try{
				validateName(name);
				return name;
			}catch(Exception e){
				System.out.println("Name Invalid!");
			}
		}
	}
	
	/*Takes user surname input and check if it's valid and returns 
	the surname if it's valid.
	*/
	static String getUserSurname(){
		while(true){
				System.out.print("Enter your surname: ");
				String surname = scanner.nextLine();
				try{
					validateSurname(surname);
					return surname;
				}catch(Exception e){
					System.out.println("Surname Invalid!");
				}
			}	
	}
	
	
	/*Takes user email input and check if it's valid and returns 
	the email if it's valid.
	*/
	static String getUserEmail(){
		while(true){
				System.out.print("Enter your email: ");
				String email = scanner.nextLine();
				try{
					validateEmail(email);
					return email;
				}catch(Exception e){
					System.out.println("Email Invalid!");
				}
			}	
	}
	
	/*Take user date of birth input and check if it's valid and returns
	the date of birth if it's valid.
	*/
	static String getUserDOB(){
		String dob;
		int yearCheck;
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateFormat.setLenient(false);
		while(true){
			System.out.print("Enter your date of birth(dd/MM/yyyy): ");
			dob = scanner.nextLine();
			try{
				Date date = dateFormat.parse(dob);
				if(dob.length() == 10){
					yearCheck = Integer.parseInt(dob.split("/")[2]);
					if(yearCheck < 2021){
						return dob;
					}else{
						System.out.println("Can't be born in the future");
					}
				}else{
					System.out.println("Date format not valid");
				}
			}catch(Exception e){
				System.out.println("Date of birth is not valid");
			}
		}
	}
	//End of user details methods
	
	//Throw this exception if user name is not valid
	static void validateName(String nameCheck) throws ThrowException{
		if(!Pattern.matches("^[a-zA-Z]+$",nameCheck)){
			throw new ThrowException("");
		}
	}
	
	//Throw this exception if user surname is not valid
	static void validateSurname(String surnameCheck) throws ThrowException{
		if(!Pattern.matches("^[a-zA-Z]+$",surnameCheck)){
			throw new ThrowException("");
		}
	}
	
	static void validateEmail(String emailCheck) throws ThrowException{
		if(!Pattern.matches("^(.+)@(.+)$",emailCheck)){
			throw new ThrowException("");
		}
	}
	
	//Throw this exception if there are no users(UPDATE, DELETE, LIST)
	static void lengthOfList(ArrayList<String> arrayList) throws ThrowException{
		ArrayList<String> userNames = arrayList;
		if(userNames.size() == 0){
			throw new ThrowException("");
		}
	}
	
}
