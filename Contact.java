package GroupProject;

public class Contact {
	private String name;
	private String birthday;
	private String phone;
	private String email;
	private String address;
	
	public Contact() {
		name = "";
		birthday = "";
		phone = "";
		email = "";
		address = "";
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getBirthday() {
		return birthday;
	}
	
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	@Override
	public String toString() {
		return "name " + name + "; "
				+ "birthday " + birthday + "; "
				+ "phone " + phone + "; "
				+ "email " + email + "; "
				+ "address " + address;
	}
}
