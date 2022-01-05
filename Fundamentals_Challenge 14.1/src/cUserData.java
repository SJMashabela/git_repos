
public class cUserData {
	private String fname;
	private String lname;
	private String email;
	private String dob;
	private int age;
	
	public cUserData() {
		this.fname = "";
		this.lname = "";
		this.email = "";
		this.dob = "";
		this.age = 0;
		
	}

	public cUserData(String fname, String lname, String email, String dob, int age) {
		super();
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.dob = dob;
		this.age = age;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		
		if (fname.matches("[a-zA-Z]*")) {
			this.fname = fname;
		} else {
			throw new IllegalArgumentException("Please enter a valid name");
		}
		
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		
		if (lname.matches("[a-zA-Z]*")) {

			this.lname = lname;

		} else {

			throw new IllegalArgumentException("Please enter a valid surname");

		}
		
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		
		if (isValid(email)) {
			this.email = email;
		} else {
			throw new IllegalArgumentException("Invalid email address!!");
		}
		
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {

		if (age < 0) {
			throw new IllegalArgumentException();
		}

		this.age = age;
	}
	
	public boolean isValid(String email) {
		String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		return email.matches(regex);
	}

	public String toString() {
		return "=========User Data======== " + "\n Name: " + fname + "\n Surname: " + lname + "\n Email: " + email
				+ "\n Age: " + age + "\n========================";
	}
	
	
	
}
