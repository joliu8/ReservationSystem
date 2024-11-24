
/**
* Fall 2024 CS151 Assignment #2
* @author Joyce Liu 
* @version 1.0 9/28/24 
* */

/**
 * A SingleUser has a name, userID, and password that can be "get".
 */
public class SingleUser {
	
	private String name;
	private String userID;
	private String password;
	
	/**
	 * Constructs a SingleUser with a name, userID, and password
	 * @param name - a string with the user's name
	 * @param userID - a string with the user's chosen userID
	 * @param password - a string with the user's chosen password
	 */
	public SingleUser(String name, String userID, String password) {
		this.name = name;
		this.userID = userID;
		this.password = password;
		
	}
	
	/**
	 * Returns the information of a SingleUser
	 * @return a string with the name, userID, and password
	 */
	@Override
	public String toString() {
		return name + " " + userID + " " + password;
	}
	
	/**
	 * Returns a userID
	 * @return a string with the user's userID
	 */
	public String getUserID() {
		return userID;
	}
	
	/**
	 * Returns a password
	 * @return a string with the user's password
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * Returns a name
	 * @return a string with the user's name
	 */
	public String getName() {
		return name;
	}
	
	}

