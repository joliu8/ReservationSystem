
/**
* Fall 2024 CS151 Assignment #2
* @author Joyce Liu 
* @version 1.0 9/28/24 
* */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

/**
* A Flight has ArrayList and HashMap data structures to manage employeeIDs,
* SingleUsers, current reservations, and available seating. 
*/
public class Flight {
	
	private String currUser;
	private ArrayList<SingleUser> userList;
	private ArrayList<Seat> currReservations;
	private HashMap<String, ArrayList<String>> currReservationsView; //name, all the seats w/ price
	private HashMap<String, String> manifestList; //seat, name
	private HashMap<Integer, ArrayList<String>> availableFirstClassSeats;
	private HashMap<Integer, ArrayList<String>> availableEconomyPlusSeats;
	private HashMap<Integer, ArrayList<String>> availableEconomySeats;//
	
	private ArrayList<String> employeeIDList;
	
	private ArrayList<String> specialSeats;

	/**
	 * Constructs a Flgiht, initalizes the ArrayLists and HashMaps, and loads CL34 and Users
	 * text files.
	 */
	public Flight() {

		userList = new ArrayList<>();
		currReservations = new ArrayList<>();
		manifestList = new HashMap<>();
		availableFirstClassSeats = new HashMap<>();
		availableEconomyPlusSeats = new HashMap<>();
		availableEconomySeats = new HashMap<>();
		currReservationsView = new HashMap<>();
		employeeIDList = new ArrayList<>(Arrays.asList("ABC12345", "DEF67890"));
		specialSeats = new ArrayList<String>(Arrays.asList("25D","25E","25F","25G","26D","25E","25F","25G", "40A", "40B", "40C", "40J", "40K", "40L"));
		
		
		availableFirstClassSeats.put(1, new ArrayList<String>(Arrays.asList("A", "B", "C", "D", "E", "F", "G", "K", "L")));
		availableFirstClassSeats.put(2, new ArrayList<String>(Arrays.asList("A", "B", "C", "D", "E", "F", "G", "K", "L")));
		availableFirstClassSeats.put(3, new ArrayList<String>(Arrays.asList("A", "B", "C", "D", "E", "F", "G", "K", "L")));
		availableFirstClassSeats.put(4, new ArrayList<String>(Arrays.asList("A", "B", "C", "K", "L")));
		
		availableEconomyPlusSeats.put(16, new ArrayList<String>(Arrays.asList("A", "B", "C", "D", "E", "F", "G", "J","K", "L")));
		availableEconomyPlusSeats.put(17, new ArrayList<String>(Arrays.asList("A", "B", "C", "D", "E", "F", "G", "J","K", "L")));
		availableEconomyPlusSeats.put(18, new ArrayList<String>(Arrays.asList("A", "B", "C", "D", "E", "F", "G", "J","K", "L")));
		availableEconomyPlusSeats.put(19, new ArrayList<String>(Arrays.asList("A", "B", "C", "D", "E", "F", "G", "J","K", "L")));
		availableEconomyPlusSeats.put(20, new ArrayList<String>(Arrays.asList("A", "B", "C", "D", "E", "F", "G", "J","K", "L")));
		availableEconomyPlusSeats.put(21, new ArrayList<String>(Arrays.asList("A", "B", "C", "D", "E", "F", "G", "J","K", "L")));
		availableEconomyPlusSeats.put(22, new ArrayList<String>(Arrays.asList("A", "B", "C", "D", "E", "F", "G", "J","K", "L")));
		availableEconomyPlusSeats.put(23, new ArrayList<String>(Arrays.asList("A", "B", "C", "D", "E", "F", "G", "J","K", "L")));
		availableEconomyPlusSeats.put(24, new ArrayList<String>(Arrays.asList("A", "B", "C", "D", "E", "F", "G", "J","K", "L")));
		availableEconomyPlusSeats.put(25, new ArrayList<String>(Arrays.asList("A", "B", "K", "L")));
		availableEconomyPlusSeats.put(26, new ArrayList<String>(Arrays.asList("A", "B", "C", "J","K", "L")));
		availableEconomyPlusSeats.put(39, new ArrayList<String>(Arrays.asList("A", "B", "C", "J","K", "L")));
		availableEconomyPlusSeats.put(40, new ArrayList<String>(Arrays.asList("D", "E", "F", "G")));
		
		availableEconomySeats.put(25, new ArrayList<String>(Arrays.asList("D", "E", "F", "G")));
		availableEconomySeats.put(26, new ArrayList<String>(Arrays.asList("D", "E", "F", "G")));
		availableEconomySeats.put(27, new ArrayList<String>(Arrays.asList("A", "B", "C", "D", "E", "F", "G", "J","K", "L")));
		availableEconomySeats.put(28, new ArrayList<String>(Arrays.asList("A", "B", "C", "D", "E", "F", "G", "J","K", "L")));
		availableEconomySeats.put(29, new ArrayList<String>(Arrays.asList("A", "B", "C", "D", "E", "F", "G", "J","K", "L")));
		availableEconomySeats.put(30, new ArrayList<String>(Arrays.asList("A", "B", "C", "D", "E", "F", "G", "J","K", "L")));
		availableEconomySeats.put(31, new ArrayList<String>(Arrays.asList("A", "B", "C", "D", "E", "F", "G", "J","K", "L")));
		availableEconomySeats.put(32, new ArrayList<String>(Arrays.asList("A", "B", "C", "D", "E", "F", "G", "J","K", "L")));
		availableEconomySeats.put(33, new ArrayList<String>(Arrays.asList("A", "B", "C", "D", "E", "F", "G", "J","K", "L")));
		availableEconomySeats.put(34, new ArrayList<String>(Arrays.asList("A", "B", "C", "D", "E", "F", "G", "J","K", "L")));
		availableEconomySeats.put(35, new ArrayList<String>(Arrays.asList("A", "B", "C", "D", "E", "F", "G", "J","K", "L")));
		availableEconomySeats.put(36, new ArrayList<String>(Arrays.asList("A", "B", "C", "D", "E", "F", "G", "J","K", "L")));
		availableEconomySeats.put(37, new ArrayList<String>(Arrays.asList("A", "B", "K", "L")));
		availableEconomySeats.put(40, new ArrayList<String>(Arrays.asList("A", "B", "C", "J","K", "L")));
		availableEconomySeats.put(41, new ArrayList<String>(Arrays.asList("A", "B", "C", "D", "E", "F", "G", "J","K", "L")));
		availableEconomySeats.put(42, new ArrayList<String>(Arrays.asList("A", "B", "C", "D", "E", "F", "G", "J","K", "L")));
		availableEconomySeats.put(43, new ArrayList<String>(Arrays.asList("A", "B", "C", "D", "E", "F", "G", "J","K", "L")));
		availableEconomySeats.put(44, new ArrayList<String>(Arrays.asList("A", "B", "C", "D", "E", "F", "G", "J","K", "L")));
		availableEconomySeats.put(45, new ArrayList<String>(Arrays.asList("A", "B", "C", "D", "E", "F", "G", "J","K", "L")));
		availableEconomySeats.put(46, new ArrayList<String>(Arrays.asList("A", "B", "C", "D", "E", "F", "G", "J","K", "L")));
		availableEconomySeats.put(47, new ArrayList<String>(Arrays.asList("A", "B", "C", "D", "E", "F", "G", "J","K", "L")));
		availableEconomySeats.put(48, new ArrayList<String>(Arrays.asList("A", "B", "D", "E", "F", "G", "K", "L")));
		availableEconomySeats.put(49, new ArrayList<String>(Arrays.asList("A", "B", "D", "E", "F", "G", "K", "L")));
		availableEconomySeats.put(50, new ArrayList<String>(Arrays.asList("A", "B", "D", "E", "F", "G", "K", "L")));
		availableEconomySeats.put(51, new ArrayList<String>(Arrays.asList("A", "B", "D", "E", "F", "G", "K", "L")));
		availableEconomySeats.put(52, new ArrayList<String>(Arrays.asList("D", "E", "F", "G")));

	}
	
	/**
	 * Reads the CL34 text file and adds the seat info into its respective data structures 
	 * precondition: the text file must be formatted where each line is:
	 * [the seat number/letter : name of the passenger]
	 * @param f - manifest list file named CL34
	 */
	public void loadCL34(File f) {

		try {
			Scanner scnr = new Scanner(f);

			while (scnr.hasNextLine()) {
//				String passenger = scnr.nextLine();
//				String seats = scnr.nextLine();
//				String seatNumTemp;
//				String seatLetterTemp;
//
//				String[] seatsTemp = seats.split(" ");
//				for (String s : seatsTemp) { // 2K
//
//					
//					if(s.length() > 2) {
//						seatNumTemp = s.substring(0,2);
//						seatLetterTemp = s.substring(2,3);
//					}
//					else {
//						seatNumTemp = s.substring(0,1);
//						seatLetterTemp = s.substring(1,2);
//					}
//					manifestList.put(s, passenger);
				
				String line = scnr.nextLine();
				String[] seatInfo = line.split(": ");
				
				manifestList.put(seatInfo[0], seatInfo[1]);
				
				
				}

			scnr.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Reads the Users text file and adds the user info into the Users ArrayList
	 * precondition: the text file must be formatted where:
	 *  first line is the user's name
	 *  second line is the user's userID
	 *  third line is the user's password
	 *  @param f - user list file named Users
	 */
	public void loadUser(File f) {
		try {
			Scanner scnr = new Scanner(f);

			while (scnr.hasNextLine()) {

				String name = scnr.nextLine();
				String userID = scnr.nextLine();
				String userPassword = scnr.nextLine();

				userList.add(new SingleUser(name, userID, userPassword));

			}

			scnr.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	/**
	 * Adds a user to the userList ArrayList
	 * @param s - a SingleUse to be added into the ArrayList
	 */
	public void addUser(SingleUser s) {
		userList.add(s);
	}
	
	/**
	 * Returns a list of all registered users
	 * @return an ArrayList of type SingleUser containing all users
	 */
	public ArrayList<SingleUser> getUserList(){
		return userList;
	}
	
	/**
	 * Returns a list of current reservations made in current transaction
	 * @return an ArrayList of type Seat containing current reservations
	 */
	public ArrayList<Seat> getCurrReservations(){
		return currReservations;
	}
	
	/**
	 * Returns the manifest list
	 * @return a HashMap current reservations where
	 * the key is the seat number/letter and
	 * the value is the name of the passenger
	 */
	public HashMap<String, String> getManifestList(){
		return manifestList;
	}
	
	/**
	 * Returns a list of available first class seats
	 * @return a HashMap of available first class seats where
	 * the key is the seat number
	 * the value is an ArrayList of type String containing the letters of seats that are available
	 */
	public HashMap<Integer, ArrayList<String>> getFirstClassAvailability(){
		return availableFirstClassSeats;
	}
	
	/**
	 * Returns a list of available economy plus seats
	 * @return a HashMap of available economy plus seats where
	 * the key is the seat number
	 * the value is an ArrayList of type String containing the letters of seats that are available
	 */
	public HashMap<Integer, ArrayList<String>> getEconomyPlusAvailability(){
		return availableEconomyPlusSeats;
	}
	
	/**
	 * Returns a list of available economy seats
	 * @return a HashMap of available economy seats where
	 * the key is the seat number
	 * the value is an ArrayList of type String containing the letters of seats that are available
	 */
	public HashMap<Integer, ArrayList<String>> getEconomyAvailability(){
		return availableEconomySeats;
	}
	
	/**
	 * Returns a list of economy seats that are not full rows of a particular service
	 * @return an ArrayList of type String containing economy seats
	 */
	public ArrayList<String> getSpecialSeats(){
		return specialSeats;
	}
	
	/**
	 * Returns a list employeeIDs
	 * @return an ArrayList of type String containing employeeIDs
	 */
	public ArrayList<String> getEmployeeIDList(){
		return employeeIDList;
	}
	
	/**
	 * Returns a list of current reservations with the passenger, their book seats, and total balance
	 * @return a HashMap of reservations where
	 * the key is the passenger's name
	 * the value is an ArrayList of type String containing the seat number/letter/price of each seat booked
	 */
	public HashMap<String, ArrayList<String>> getCurrReservationsView(){
		return currReservationsView;
	}
	
	/**
	 * Adds a seat to the current reservation list
	 * @param seat - new reserved Seat
	 */
	public void addReservation(Seat seat) {
		currReservations.add(seat);
	}
	
	/**
	 * Remove a seat from the list of available first class seats
	 * @param seatNum - seat number to be removed
	 * @param seatLetter - seat letter to be removed
	 */
	public void removeFirstClassSeat(int seatNum, String seatLetter) {
		availableFirstClassSeats.get(seatNum).remove(seatLetter);
	}
	
	/**
	 * Remove a seat from the list of available economy plus seats
	 * @param seatNum - seat number to be removed
	 * @param seatLetter - seat letter to be removed
	 */
	public void removeEconomyPlusSeat(int seatNum, String seatLetter) {
		availableEconomyPlusSeats.get(seatNum).remove(seatLetter);
	}
	
	/**
	 * Remove a seat from the list of available economy seats
	 * @param seatNum - seat number to be removed
	 * @param seatLetter - seat letter to be removed
	 */
	public void removeEconomySeat(int seatNum, String seatLetter) {
		availableEconomySeats.get(seatNum).remove(seatLetter);
	}
	
	
	/**
	 * Returns if the special seats list contains a particular seat
	 * @param seat - a string of a seat's number/letter 
	 * @return boolean of if special seats contains a particular seat
	 */
	public boolean ifSpecialViewContains(String seat) {
		return specialSeats.contains(seat);
		
	}
	
	/**
	 * Returns if the current reservations list contains a particular name
	 * @param key - a string of a passenger's name
	 * @return boolean of if a passenger has a reservation
	 */
	public boolean currRevContainsKey(String key) {
		return currReservationsView.containsKey(key);
		
	}

	/** 
	 * Adds a reservation to current reservations view
	 * @param name - a string of a passenger's name
	 * @param s - a string with the reservation information
	 */
	public void addCurrRev(String name, String s) {
		if (currReservationsView.containsKey(name)) {
			currReservationsView.get(name).add(s);
	} else {
		currReservationsView.put(name, new ArrayList<String>(Arrays.asList(s)));
	}

	}
	
	/** 
	 * Adds a seat and name to manifest list
	 * @param seat - a string with the seat number/letter
	 * @param name - a string with the passenger's name
	 */
	public void addManifestList(String seat, String name) {
		manifestList.put(seat, name);
	}
	
	/** 
	 * Sets a variable to the current user's name
	 * @param name - a string of a passenger's name
	 */
	public void setCurrUser(String name) {
		currUser = name;
	}
	
	/**
	 * Returns the user's name
	 * @return a string with the user's name
	 */
	public String getCurrUser() {
		return currUser;
	}
	
	/** 
	 * Removes a reservation from current reservations ArrayList
	 * @param seat - a Seat object
	 */
	public void removeCurrRev(Seat seat) {
		currReservations.remove(seat);

	}
}
