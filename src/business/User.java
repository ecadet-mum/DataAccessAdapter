package business;

import business.value.Permission;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

final public class User implements Serializable {
	
	private static final long serialVersionUID = 5147265048973262104L;

	private String id;
	
	private String password;
	
	private List<Permission> permissions = new ArrayList<Permission>();
	
	public User(String id, String pass, List<Permission>  permissions) {
		this.id = id;
		this.password = pass;
		this.permissions.addAll(permissions);
	}
	
	public String getId() {
		return id;
	}
	public String getPassword() {
		return password;
	}
	public List<Permission> getAuthorization() {
		return permissions;
	}
	
	public void addPermission(Permission permission) {
	    permissions.add(permission);
	}
	
	@Override
	public String toString() {
		return "[" + id + ":" + password + ", " + permissions.toString() + "]";
	}
	
}
