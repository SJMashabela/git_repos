
public class UserInfo {
	private String name;
	private String surname;
	private String date_of_birth;
	private int age;
	private int dist;

	public UserInfo(String name, String surname, String date_of_birth, int age, int dist) {
		super();
		this.name = name;
		this.surname = surname;
		this.date_of_birth = date_of_birth;
		this.age = age;
		this.dist = dist;
	}

	public UserInfo() {

	}

	public String getDate_of_birth() {
		
		return date_of_birth;
	}

	public void setDate_of_birth(String date_of_birth) {
		
		this.date_of_birth = date_of_birth;
	}

	public String getName() {

		

		return name;
	}

	public void setName(String name) {
		
		if (name.isEmpty()) {

			throw new IllegalArgumentException("Please enter a name... \n");

		} else if (!name.matches("[a-zA-Z]*")) {

			throw new IllegalArgumentException("Please enter a valid name");

		}
		this.name = name;
	}

	public String getSurname() {

		return surname;
	}

	public void setSurname(String surname) {
		
		if (surname.isEmpty()) {

			throw new IllegalArgumentException("Please enter a name... \n");

		} else if (!surname.matches("[a-zA-Z]*")) {

			throw new IllegalArgumentException("Please enter a valid name");

		}
		this.surname = surname;
	}

	public int getAge() {

		return age;
	}

	public void setAge(int age) {
		if(age < 0) {
			throw new IllegalArgumentException();
		}

		this.age = age;
	}

	public int getDist() {
		return dist;
	}

	public void setDist(int dist) {
		
		this.dist = dist;
	}

}
