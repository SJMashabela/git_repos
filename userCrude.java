import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
public class userCrude {
    public static void main(String[]args) {
        List<userMenu> aList = new ArrayList<userMenu>();
        Scanner in = new Scanner(System.in);

        int choice;
            do {
                //Menu that has the options for Add, Delete, Update, and List users.
                System.out.println("=====SELECT OPTIONS===============");
                System.out.println("= 1. Add user                    =");
                System.out.println("= 2. Delete user                 =");
                System.out.println("= 3. Update user                 =");
                System.out.println("= 4. List users                  =");
                System.out.println("= 0. To exit                     =");
                System.out.println("==================================");

                //Choice for select case when the user has selected the options.
                choice = in.nextInt();

                //String regex for name,surname,email.
                String regex = "[A-Za-z-]*";
                String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";

                //Switch case for options.
                switch (choice) {
                    //Case 1 for adding the information of the person.
                    case 1:
                        System.out.println("Please enter your name:");
                        String name = in.next();
                        if (name.matches(regex) && !name.isEmpty()) {
                        } else {
                            System.out.println("Please Enter a valid name E.g Mickael");
                            name = in.next();
                        }
                        System.out.println("Please enter your surname:");
                        String surname = in.next();
                        if (surname.matches(regex) && !surname.isEmpty()) {
                        } else {
                            System.out.println("Please Enter a valid surname E.g Jackson");
                            surname = in.next();
                        }
                        System.out.println("Please enter your email:");
                        String email = in.next();
                        if (email.matches(emailRegex) && !email.isEmpty()) {

                        } else {
                            System.out.println("Please Enter a valid email E.g jackson@gmail.com");
                            email = in.next();
                        }

                        System.out.println("Please enter your Date of birth(DD/MM/YYYY)");
                        String dob = in.next();
                        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                        try {
                            LocalDate date1 = LocalDate.parse(dob, dateFormat);
                            var year = dob.split("/")[2];
                            int year1=Integer.parseInt(year);
                            if(year1>=2022){
                                System.out.println("Invalid year Please Try again");
                                dob=in.next();
                            }
                            int yearsold = 2021 - year1;

                            System.out.println("Your age is: " + yearsold);

                        } catch (DateTimeParseException e) {
                            System.out.println("ERROR:" + e);
                        }
                        aList.add(new userMenu(name, surname, dob, email));
                        System.out.println("Hello " + name + " " + surname + " " + "your information has been saved to the database.");
                        break;

                        //Case to for Deleting the record
                    case 2:
                        boolean found = false;
                        System.out.println("Enter email");
                        email = in.next();
                        System.out.println("----------------");
                        Iterator<userMenu> a;
                        a = aList.iterator();
                        while (a.hasNext()) {
                            userMenu e = a.next();
                            if (Objects.equals(e.getEmail(), email)) {
                                a.remove();
                                found = true;
                            }
                        }
                        if (!found) {
                            System.out.println("Record not found");
                        } else {
                            System.out.println("Record deleted Successfully");
                        }
                        break;

                        //Case 3 for updating the user information if there email is found.
                    case 3:
                        found = false;
                        System.out.println("Enter email to update the user");
                        email = in.next();
                        ListIterator<userMenu> li = aList.listIterator();
                        while (li.hasNext()) {
                            userMenu e = li.next();
                            if (Objects.equals(e.getEmail(), email)) {
                                System.out.println("Enter new name");
                                name = in.next();
                                if (name.matches(regex) && !name.isEmpty()) {
                                } else {
                                    System.out.println("Please Enter a valid name E.g Mickael");
                                    name = in.next();
                                }
                                System.out.println("Enter new surname");
                                surname = in.next();
                                if (surname.matches(regex) && !surname.isEmpty()) {
                                } else {
                                    System.out.println("Please Enter a valid Surname");
                                    surname= in.next();
                                }
                                System.out.println("Enter new Date of birth");
                                dob = in.next();
                                DateTimeFormatter dateFormat1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                                try {
                                    LocalDate date1 = LocalDate.parse(dob, dateFormat1);
                                } catch (DateTimeParseException f) {
                                    System.out.println("ERROR:" + f);
                                }
                                System.out.println("Enter new Email");
                                email = in.next();
                                if (email.matches(emailRegex) && !email.isEmpty()) {
                                } else {
                                    System.out.println("Please Enter a valid email");
                                    email= in.next();
                                }
                                li.set(new userMenu(name, surname, dob, email));
                                found = true;
                            }
                        }
                        if (!found) {
                            System.out.println("Record not found! Please add a new user.");
                        } else {
                            System.out.println("Record is updated Successfully ");
                            System.out.println();
                        }
                        break;

                        //Case 4 for listing the registered users.
                    case 4:
                        System.out.println("The list of users:");
                        Iterator<userMenu> i = aList.iterator();
                        while (i.hasNext()) {
                            userMenu u = i.next();
                            System.out.println(u);
                        }
                        System.out.println();
                        break;
                    case 0:
                        System.exit(0);
                }
            } while (choice >= 1 && choice <= 4);
       }

}
