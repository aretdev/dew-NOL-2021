package main;

public class User {

	private String dni ;
	private String pass ;
	
	public User(String dni, String password) {
		this.dni = dni;
		this.pass = password;
	}
	
	public String getDni() {
		return this.dni;
	}
	public String getPassword() {
		return this.pass;
	}
}
