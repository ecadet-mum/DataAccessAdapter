package business;

import java.io.Serializable;

final public class LibraryMember extends Person implements Serializable {
	private  Integer memberId;
	
	private final CheckoutRecord checkoutRecord;
	
	public LibraryMember(Integer memberId, String fname, String lname, String tel,Address add) {
		super(fname,lname, tel, add);
		this.memberId = memberId;		
		checkoutRecord = new CheckoutRecord(this);        // library member with zero checkout entries
	}
	
	
	public Integer getMemberId() {
		return memberId;
	}

	public CheckoutRecord getCheckoutRecord() {
	    return checkoutRecord;
	}
	
	public void setMemberId(Integer memberId){
		this.memberId=memberId;
	}
	@Override
	public String toString() {
		return "Member Info: " + "ID: " + memberId + ", name: " + getFirstName() + " " + getLastName() + 
				", " + getTelephone() + " " + getAddress()+"\n";
	}

	private static final long serialVersionUID = -2226197306790714013L;
}
