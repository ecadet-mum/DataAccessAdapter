package business;

import java.io.Serializable;

public class Person implements Serializable {
	private static final long serialVersionUID = 3665880920647848288L;
	private String firstName;
	private String lastName;
	private String telephone;
	private Address address;
	private Integer idPerson;

	public Person(String fName, String lName, String telephone, Address a) {
		firstName = fName;
		lastName = lName;
		this.telephone = telephone;
		address = a;
		
	}
	
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getTelephone() {
		return telephone;
	}
	public Address getAddress() {
		return address;
	}
	public Integer getIdPerson(){ return idPerson;}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public void setIdPerson(Integer idPerson){
		this.idPerson=idPerson;
	}
	
	
	@Override
	public String toString() {
	    return String.format("%s, %s", firstName, lastName);
	}
}
