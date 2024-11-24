


/**
* Fall 2024 CS151 Assignment #2
* @author Joyce Liu 
* @version 1.0 9/28/24 
* */

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

/**
* A reservation system creates a Flight object, prompts for user input and executes the program based on the input. 
* */
public class ReservationSystem {
	
	
	/**
	 * Manages the entire program depending on user input
	 * @param args - the command line arguments
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Flight flightCL34 = new Flight();
		try {
			
	        String reservationFileName = args[0];
	        String userFileName = args[1];

	        
	        File fileCL34 = new File(reservationFileName);
	        File fileUser = new File(userFileName);

			if (fileCL34.exists() && fileUser.exists()) {
				flightCL34.loadCL34(fileCL34);
				flightCL34.loadUser(fileUser);

				System.out.println("Existing Reservations and Users are loaded. \n");
			} else {
				fileCL34.createNewFile();
				fileUser.createNewFile();
				System.out.println("CL34 and Users are now created. \n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Scanner scnr = new Scanner(System.in);

		System.out.println("Are you a public user or admin ? (Type “public user” or “admin”)");

		while (scnr.hasNextLine()) {
			String input = scnr.nextLine();

			if (input.equals("public user")) {
				System.out.println("Are you a first-time user? (Type [Y] for yes or [N] for no)");
			} else if (input.equals("y")) {
				System.out.println("What is your name? ");
				String inputName = scnr.nextLine();
				System.out.println("What do you want your user ID to be ? ");
				String inputID = scnr.nextLine();
				System.out.println("What do you want your password to be ?");
				String inputPassword = scnr.nextLine();
				
				SingleUser newUser = new SingleUser(inputName, inputID, inputPassword);

				flightCL34.addUser(newUser);

				System.out.println("You have successfully created an account!\n");

				printUserMenu();

//				for (SingleUser su : flightCL34.getUserList()) {
//					System.out.println("36: " + su.toString());
//				}

			} else if (input.equals("n")) {
				System.out.println("What is your user ID ?");
				String inputID = scnr.nextLine();

				boolean idMatch = checkID(flightCL34, inputID);
				while (idMatch == false) {
					System.out.println("What is your user ID ?");
					inputID = scnr.nextLine();
					idMatch = checkID(flightCL34, inputID);
				}

				System.out.println("What is your password ?");
				String inputPassword = scnr.nextLine();

				boolean passwordMatch = checkPassword(flightCL34, inputPassword);
				while (passwordMatch == false) {
					System.out.println("What is your password ?");
					inputPassword = scnr.nextLine();
					passwordMatch = checkPassword(flightCL34, inputPassword);
				}

				checkUserLogin(flightCL34, inputID, inputPassword);
				printUserMenu();

			} else if (input.equals("a")) {

				printSeatAvailability(flightCL34);
				printUserMenu();

			} else if (input.equals("r")) {

				System.out.println("What name do you want the reservation under?");
				String rName = scnr.nextLine();

				System.out.println(
						"Please enter the number associated with the seat you want to reserve (e.g. For seat 1K, type in [1])");
				int rSeatNum = scnr.nextInt();

				boolean seatNumIsValid = checkSeatNum(flightCL34, rSeatNum);
				while (seatNumIsValid == false) {
					System.out.println(
							"Please enter the number associated with the seat you want to reserve (e.g. For seat 1K, type in [1])\n");
//					rSeatNum = Integer.parseInt(scnr.nextLine());
					rSeatNum = scnr.nextInt();
					seatNumIsValid = checkSeatNum(flightCL34, rSeatNum);

				}

				System.out.println(
						"Please enter the letter associated with the seat you want to reserve (e.g. For seat 1K, type in [K])");
				String rLetter = scnr.next().toUpperCase();
//				System.out.println("106 " + rLetter);

				boolean seatLetterIsValid = checkSeatLetter(flightCL34, rSeatNum, rLetter);

//				System.out.println(seatLetterIsValid);
				while (seatLetterIsValid == false) {
					System.out.println(
							"Please enter the letter associated with the seat you want to reserve (e.g. For seat 1K, type in [K])\n");
					rLetter = scnr.next().toUpperCase();
					seatLetterIsValid = checkSeatLetter(flightCL34, rSeatNum, rLetter);
				}

//				System.out.println("made it out of both");

				String seat = rSeatNum + rLetter;

				Seat newRev = new Seat(rSeatNum, rLetter, rName);
				flightCL34.addReservation(newRev);

//				if (seatNumIsValid && seatLetterIsValid) {

				newRev.printSeatInfo(flightCL34, newRev.getSeatNumber(), newRev.getSeatLetter());

				String inputC = scnr.nextLine();
				System.out.println("Is this correct? (Type “confirm” to confirm or “not confirm” to not confirm)");
				inputC = scnr.nextLine();

//				System.out.println("145: " + inputC );

				if (inputC.equals("confirm")) {

					flightCL34.addManifestList(seat, rName);
					String forView = rSeatNum + rLetter + " ";

//					System.out.println(flightCL34.getManifestList());

					if (rSeatNum < 4) {
						flightCL34.removeFirstClassSeat(rSeatNum, rLetter);
						forView += "$1000";

					}
					if ((((rSeatNum >= 16 && rSeatNum <= 26) || rSeatNum == 40)
							&& !flightCL34.ifSpecialViewContains(seat)) || rSeatNum == 39) {
						flightCL34.removeEconomyPlusSeat(rSeatNum, rLetter);
						forView += "$500";
					}
					if ((rSeatNum >= 25)
							|| (rSeatNum == 25 || rSeatNum == 26) && flightCL34.ifSpecialViewContains(seat)) {
						flightCL34.removeEconomySeat(rSeatNum, rLetter);
						forView += "$250";
					}

					flightCL34.addCurrRev(rName, forView);
//						System.out.println("166: " + flightCL34.getCurrReservationsView().get(rName));

					System.out.println("Your seat has been reserved! \n"
							+ "Do you want to make another reservation? (Type [R] for yes or [NR] for no)");
				}

				else if (inputC.equals("not confirm")) {
					System.out.println("Your reservation request has been voided");
					flightCL34.removeCurrRev(newRev);
					printUserMenu();
				}

			} else if (input.equals("nr")) {
				printUserMenu();
			} else if (input.equals("c")) {
				// delete from currReservations
				// delete from currReservations view
				// add back to the 3 arrays
				// delete from manifest list <- master list

//				for (Seat s : flightCL34.getCurrReservations()) {
//					s.printSeatInfo(flightCL34, s.getSeatNumber(), s.getSeatLetter());
//				}
				flightCL34.getManifestList().forEach((k, v) -> {
					System.out.println(k + ": " + v);
				});

				System.out.println();

				System.out.println(
						"Please enter the number and letter associated with the seat you want to cancel (e.g. For seat 1K, type in [1K])");
				String cancelSeat = scnr.nextLine();

				boolean isNumReserved = checkSeatReserved(flightCL34.getManifestList(), cancelSeat);
				while (isNumReserved == false) {
					System.out.println(
							"Please enter the number associated with the seat you want to cancel (e.g. For seat 1K, type in [1K])");
					cancelSeat = scnr.nextLine();
					isNumReserved = checkSeatReserved(flightCL34.getManifestList(), cancelSeat);
				}

				int cancelNum;
				String cancelLetter;
				if (cancelSeat.length() > 2) {
					cancelNum = Integer.parseInt(cancelSeat.substring(0, 2));
					cancelLetter = cancelSeat.substring(2, 3);
				} else {
					cancelNum = Integer.parseInt(cancelSeat.substring(0, 1));
					cancelLetter = cancelSeat.substring(1, 2);
				}

				cancelSeat(flightCL34, cancelNum, cancelLetter.toUpperCase());

				System.out.println(
						"You have canceled your reservation. Do you want to cancel another reservation? (Type [C] for yes or [NR] for no)");

			} else if (input.equals("v")) {
//				System.out.println(flightCL34.getCurrReservationsView());
//				System.out.println(Arrays.toString(flightCL34.getCurrReservationsView().entrySet().toArray()));

//				flightCL34.getCurrReservationsView().forEach((key, value) -> System.out.println(key + "'s Seats: " + value));

				
				//this chunk only works if we are resetting for every transaction
				
				System.out.println("--List of All Reservations--");
				flightCL34.getManifestList().forEach((k,v) -> {
					int seatNum = -1;
					
					
					if(k.length() == 2) {
						 seatNum = Integer.parseInt(k.substring(0,1));
					}
					else if(k.length()>2) {
						seatNum = Integer.parseInt(k.substring(0,2));
					}
					
					System.out.println("Name: " + v);
					System.out.print("Seat: " + k + " ") ;
					
					if (seatNum < 4) {
						System.out.print("$1000");

					}
					if ((((seatNum >= 16 && seatNum <= 26) || seatNum == 40)
							&& !flightCL34.ifSpecialViewContains(k)) || seatNum == 39) {
						System.out.print("$500");
					}
					if ((seatNum >= 25)
							|| (seatNum == 25 || seatNum == 26) && flightCL34.ifSpecialViewContains(k)) {
						System.out.print("$250");
					}
					
					System.out.println();
					
				});		
				
				System.out.println();
				if (flightCL34.getCurrReservationsView().size() < 1) {
					System.out.println("--No new reservations in this current transaction.--");
//					printUserMenu();
				}
				else {
					System.out.println("--List of Reservations Made in Current Transaction--");
				}
				
				flightCL34.getCurrReservationsView().forEach((k, v) -> {
					int balance = 0;
					System.out.println();
					System.out.println(k + "'s Seats: " + v);

					for (String s : v) {
						int startIndex = s.indexOf("$");
						int endIndex = s.length();

						int price = Integer.parseInt(s.substring(startIndex + 1, endIndex));
						balance += price;
					}

					System.out.println("Total Balance Due: $" + balance + "\n");
				});
				
				printUserMenu();

			} else if (input.equals("d")) {

				System.out.println("Are you a public user or admin ? (Type “public user” or “admin”)");

			} else if (input.equals("admin")) {

				System.out.println("What is your employee ID ?");
				String inputID = scnr.nextLine();

				boolean validEmployee = checkEmployeeID(flightCL34.getEmployeeIDList(), inputID);
				while (validEmployee == false) {
					System.out.println("What is your employee ID ?");
					inputID = scnr.nextLine();
					validEmployee = checkEmployeeID(flightCL34.getEmployeeIDList(), inputID);
				}

				System.out.println("You have successfully logged in!");
				printAdminMenu();

			} else if (input.equals("m")) {
				printManifestList(flightCL34);
				printAdminMenu();

			} else if (input.equals("x")) {
				try {
					File outputManifest = new File("CL34");
					FileWriter output = new FileWriter(outputManifest);
					
					File outputUser = new File("Users");
					FileWriter outputU = new FileWriter(outputUser);

					flightCL34.getManifestList().forEach((k, v) -> {

						try {
							output.write(k + ": " + v + System.getProperty("line.separator"));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					});
					
					for(SingleUser su : flightCL34.getUserList()) {
						outputU.write(su.getName() + System.getProperty("line.separator"));
						outputU.write(su.getUserID() + System.getProperty("line.separator"));
						outputU.write(su.getPassword() + System.getProperty("line.separator"));
					}
					
					System.out.println("Goodbye, safe travels!\n");
					
					output.close();
					outputU.close();
					
				} 
				catch (Exception e) {
					e.printStackTrace();

				}
				
				

			}

		} // end of while 52
		
		scnr.close();

	} // end of main

	private static void printUserMenu() {
		System.out.println(
				"Here is the transaction menu:\n Check [A]vailability  Make [R]eservation  [C]ancel Reservation   [V]iew Reservations  [D]one \n");
	}

	private static void printAdminMenu() {
		System.out.println("Here is the administration menu: \n Show [M]anifest list  E[X]it ");
	}

	private static void printSeatAvailability(Flight f) {

		for (String seat : f.getManifestList().keySet()) {
			String seatNumTemp;
			String seatLetterTemp;

			if (seat.length() > 2) {
				seatNumTemp = seat.substring(0, 2);
				seatLetterTemp = seat.substring(2, 3);
			} else {
				seatNumTemp = seat.substring(0, 1);
				seatLetterTemp = seat.substring(1, 2);
			}

			int seatNum = Integer.parseInt(seatNumTemp);

			if (seatNum < 4) {
				f.getFirstClassAvailability().get(seatNum).remove(seatLetterTemp);

			}
			if ((((seatNum >= 16 && seatNum <= 26) || seatNum == 40) && !f.getSpecialSeats().contains(seat))
					|| seatNum == 39) {
				f.getEconomyPlusAvailability().get(seatNum).remove(seatLetterTemp);
			}
			if ((seatNum >= 25) || (seatNum == 25 || seatNum == 26) && f.getSpecialSeats().contains(seat)) {
				f.getEconomyAvailability().get(seatNum).remove(seatLetterTemp);
			}
		}

		System.out.println("Seat Availability\n");
		System.out.println("First (price: $1000/seat)");
		printSpecificClassSeatAvailability(f.getFirstClassAvailability());
		System.out.println();

		System.out.println("Economy Plus (price: $500/seat)");
		printSpecificClassSeatAvailability(f.getEconomyPlusAvailability());
		System.out.println();

		System.out.println("Economy (price: $250/seat)");
		printSpecificClassSeatAvailability(f.getEconomyAvailability());
		System.out.println();

	}

	private static void printSpecificClassSeatAvailability(HashMap<Integer, ArrayList<String>> hm) {
		hm.forEach((k, v) -> {
			System.out.println(k + ": " + v);
		});
	}

	private static void checkUserLogin(Flight f, String id, String pw) {
		ArrayList<SingleUser> ul = f.getUserList();

		for (int i = 0; i < ul.size(); i++) {
			if (ul.get(i).getUserID().equals(id) && ul.get(i).getPassword().equals(pw)) {
				System.out.println("You have successfully logged in!");
				return;
			}
		}
		System.out.println("smth went wrong");

	}

	private static boolean checkID(Flight f, String id) {

		ArrayList<SingleUser> ul = f.getUserList();

		for (int i = 0; i < ul.size(); i++) {
			if (ul.get(i).getUserID().equals(id))
				return true;
		}
		System.out.println("Invalid user ID. Please try again");
		return false;

	}

	private static boolean checkPassword(Flight f, String pw) {

		ArrayList<SingleUser> ul = f.getUserList();

		for (int i = 0; i < ul.size(); i++) {
			if (ul.get(i).getPassword().equals(pw))
				return true;
		}
		System.out.println("Invalid password. Please try again");
		return false;

	}

	private static boolean checkSeatNum(Flight f, int seatNum) {

		boolean result = false;

		try {

			if (seatNum < 4) {
				result = f.getFirstClassAvailability().get(seatNum).size() > 0;

			}
			if ((((seatNum >= 16 && seatNum <= 26) || seatNum == 40)) // !f.getSpecialSeats().contains(seat)
					|| seatNum == 39) {
				result = f.getEconomyPlusAvailability().get(seatNum).size() > 0;
			}
			if ((seatNum >= 25) || (seatNum == 25 || seatNum == 26)) { // f.getSpecialSeats().contains(seat)
				result = f.getEconomyAvailability().get(seatNum).size() > 0;
//				System.out.println("284 " + f.getEconomyAvailability().get(seatNum).size());
//				System.out.println(f.getEconomyAvailability().get(seatNum).size() > 0);
			}
			if (seatNum == 25 || seatNum == 26 || seatNum == 40) {
				result = f.getEconomyPlusAvailability().get(seatNum).size() > 0
						|| f.getEconomyAvailability().get(seatNum).size() > 0;
//				System.out.println(f.getEconomyPlusAvailability().get(seatNum).size() > 0);
//				System.out.println(f.getEconomyAvailability().get(seatNum).size() > 0);
//				System.out.println(result);
				}

			if (result == false) {
				System.out.println("Invalid seat number. Please try again");
				return false;
			} else {
				return true; // problematic
			}
		} catch (Exception e) {
//			e.printStackTrace();
			System.out.println("Invalid seat number. Please try again");
			return false;
		}

	}

	private static boolean checkSeatLetter(Flight f, int seatNum, String seatLetter) {

		String seat = seatNum + seatLetter;
		boolean result = false;

		if (seatNum < 4) {
			result = f.getFirstClassAvailability().get(seatNum).contains(seatLetter);

		}
		if ((((seatNum >= 16 && seatNum <= 26) || seatNum == 40) && !f.getSpecialSeats().contains(seat))
				|| seatNum == 39) {
			result = f.getEconomyPlusAvailability().get(seatNum).contains(seatLetter);
		}
		if ((seatNum >= 25) || (seatNum == 25 || seatNum == 26) && f.getSpecialSeats().contains(seat)) {
			result = f.getEconomyAvailability().get(seatNum).contains(seatLetter);
		}
		
		if (seatNum == 25 || seatNum == 26 || seatNum == 40) {
			result = f.getEconomyPlusAvailability().get(seatNum).contains(seatLetter)
					|| f.getEconomyAvailability().get(seatNum).contains(seatLetter);
//			System.out.println(f.getEconomyPlusAvailability().get(seatNum).contains(seatLetter));
//			System.out.println(f.getEconomyAvailability().get(seatNum).contains(seatLetter));
//			System.out.println(result);
			}

		if (result == false) {
			System.out.println("Invalid seat letter. Please try again");
			return false;
		} else {
			return true; // problematic
		}

	}

	private static void cancelSeat(Flight f, int seatNum, String seatLetter) {

		// delete from currReservations
		// delete from currReservationsview
		// add back to the 3 arrays
		// delete from manifest list <- master list

		String seat = seatNum + seatLetter;
		String seatView = seat;

		if (seatNum < 4) {
			f.getFirstClassAvailability().get(seatNum).add(seatLetter);
			seatView += " $1000";

		}
		if ((((seatNum >= 16 && seatNum <= 26) || seatNum == 40) && !f.getSpecialSeats().contains(seat))
				|| seatNum == 39) {
			f.getEconomyPlusAvailability().get(seatNum).add(seatLetter);
			seatView += " $500";
		}
		if ((seatNum >= 25) || (seatNum == 25 || seatNum == 26) && f.getSpecialSeats().contains(seat)) {
			f.getEconomyAvailability().get(seatNum).add(seatLetter);
			seatView += " $250";
		}

		for (ArrayList<String> a : f.getCurrReservationsView().values()) {
			for (int j = 0; j < a.size(); j++) {
				if (a.get(j).equals(seatView)) {
					a.remove(j);
					j--;
				}

			}
		}
//		ArrayList<String> currReservationsViewTemp = f.getCurrReservationsView().get(seat);
//		System.out.println(0434);
//		if (currReservationsViewTemp != null) {
//			System.out.println(436);
//			for (int j = 0; j < currReservationsViewTemp.size(); j++) {
//				if (currReservationsViewTemp.get(j).equals(seat)) {
//					f.getCurrReservationsView().remove(seat);
//				}
//			}
//		}
//		f.getCurrReservationsView().forEach((k,v)->{
//			
//			
//		}
//				);

		ArrayList<Seat> currReservationsTemp = f.getCurrReservations();
		for (int i = 0; i < currReservationsTemp.size(); i++) {
			if (currReservationsTemp.get(i).getSeatNumber() == seatNum
					&& currReservationsTemp.get(i).getSeatLetter().equals(seatLetter)) {
				currReservationsTemp.remove(i);

			}
		}

		f.getManifestList().remove(seat);

	}

	private static boolean checkSeatReserved(HashMap<String, String> ml, String seat) {

		boolean ans = false;

		if (ml.containsKey(seat)) {
			ml.remove(seat);
			ans = true;
		}

		if (ans == false) {
			System.out.println("Invalid seat. Please try again.");
			return false;
		} else
			return true;

	}

	private static boolean checkEmployeeID(ArrayList<String> al, String input) {

		boolean ans = al.contains(input);

		if (ans == false) {
			System.out.println("Invalid employee ID. Please try again");
			return false;
		}
		return true;

	}

	private static void printManifestList(Flight f) {

		f.getManifestList().forEach((k, v) -> {
			System.out.println(k + ": " + v);

		});

	}
	
}
