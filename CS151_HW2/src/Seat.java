
/**
* Fall 2024 CS151 Assignment #2
* @author Joyce Liu 
* @version 1.0 9/28/24 
* */


/**
 * A Seat has a seat number, seat letter, and passenger that can be "get" and printed.
 */
public class Seat {

	private int seatNumber;
	private String seatLetter;
	private String passenger;
	
	/**
	 * Constructs a Seat with a seat number, seat letter, and passenger
	 * @param seatNumber - an integer of the seat number
	 * @param seatLetter - a string of the seat letter
	 * @param passenger - a string with the name of the passenger
	 */
	public Seat(int seatNumber, String seatLetter, String passenger) {
		this.seatNumber = seatNumber;
		this.seatLetter = seatLetter;
		this.passenger = passenger;

	}
	
	/**
	 * Returns the seat number of a seat
	 * @return an integer seat number
	 */
	public int getSeatNumber() {
		return seatNumber;
	}

	/**
	 * Returns the letter of the seat
	 * @return a string of the seat letter
	 */
	public String getSeatLetter() {
		return seatLetter;
	}

	/**
	 * Returns the passenger's name
	 * @return a string of the passenger's name
	 */
	public String getPassenger() {
		return passenger;
	}

	
	/**
	 * Prints the service and price of a seat depending on the seat number
	 * @param flight - a Flight
	 * @param seatNum - an integer seat number
	 * @param seatLetter - a string seat letter
	 */
	public void printSeatInfo(Flight flight, int seatNum, String seatLetter) {

		String seat = seatNum + seatLetter;

		if (seatNum < 4) {
			System.out.println("Seat Number: " + seat);
			System.out.println("Service: First Class");
			System.out.println("Price: $1000");

		}
		if ((((seatNum >= 16 && seatNum <= 26) || seatNum == 40) && !flight.getSpecialSeats().contains(seat))
				|| seatNum == 39) {
			System.out.println("Seat Number: " + seat);
			System.out.println("Service: Economy Plus");
			System.out.println("Price: $500");
		}

		if ((seatNum >= 25) || (seatNum == 25 || seatNum == 26) && flight.getSpecialSeats().contains(seat)) {
			System.out.println("Seat Number: " + seat);
			System.out.println("Service: Economy");
			System.out.println("Price: $250");
		}


	}

//	public void printView(Flight f) {
//
//		int balance = 0;
////			System.out.println("Name: " + s.getPassenger());
//
//		int seatNum = getSeatNumber();
//		String seatLetter = getSeatLetter();
//		String seat = seatNum + seatLetter;
//
//		if (seatNum < 4) {
//			System.out.println(seatNum + seatLetter + " $1000");
//			balance += 1000;
//
//		}
//		if ((((seatNum >= 16 && seatNum <= 26) || seatNum == 40) && !f.getSpecialSeats().contains(seat))
//				|| seatNum == 39) {
//			System.out.println(seatNum + seatLetter + " $500");
//			balance += 500;
//		}
//
//		if ((seatNum >= 25) || (seatNum == 25 || seatNum == 26) && f.getSpecialSeats().contains(seat)) {
//			System.out.println(seatNum + seatLetter + " $250");
//			balance += 250;
//		}
//
//		System.out.println("Balance: " + balance);
//
//	}
}
