package business;

import java.io.Serializable;

/* Immutable */
final public class Address implements Serializable {
	
	private static final long serialVersionUID = -891229800414574888L;
	private Integer idAddress;
	private String street;
	private String city;
	private String state;
	private String zip;

	public Address(String street, String city, String state, String zip) {
		this.street = street;
		this.city = city;
		this.state = state;
		this.zip = zip;
	}
	
	public String getStreet() {
		return street;
	}
	public String getCity() {
		return city;
	}
	public String getState() {
		return state;
	}
	public String getZip() {
		return zip;
	}

	public Integer getIdAddress(){
		return idAddress;
	}

	public void setIdAddress(Integer idAddress){
		this.idAddress=idAddress;
	}

	@Override
	public String toString() {
		return "(" + street + ", " + city + ", "+state+"," + zip + ")";
		
	}
}
