package com.project.util;

import java.io.BufferedReader;
import java.util.Map;

import com.project.model.VehicleDetails;

/**
 * 
 * @author chandru
 *
 */
public class Utils {

	public static void displayAppDescrption() {
		System.out.println("Welcome to the xxxxxxxx Parking APP");
		System.out.println("*******************Guide for APP *******************");
		System.out.println(
				"*******************command \"create_parking_lot or create\"  - for creating a new parking lot*******************");
		System.out.println(
				"*******************command \"park\"               - for parking a vehicle in the lot*******************");
		System.out.println(
				"*******************command \"status\"             - for details of vehicles in lot*******************");
		System.out.println(
				"*******************command \"leave\"              - for relieve the vehicle in lot*******************");
		System.out.println(
				"*******************command \"search\"              - for searching the details in lot*******************\n\n\n");

	}

	public static String getUserInput(BufferedReader br, String regex) {
		System.out.println("*************Provide a valid input*************");
		String value = "";
		boolean flag = true;
		try {
			while (flag) {
				String tmpValue = br.readLine().trim();
				if (tmpValue.matches(regex)) {
					value = tmpValue;
					flag = false;
				} else if (tmpValue.equalsIgnoreCase("exit")) {
					System.out.println("Quiting the Application ------- \n\nBye Bye:-)");
					System.exit(1);
				} else {
					System.out.println("Uh-oh! It seems like you provided a invalid input.");
					System.out.println("Please provide a valid input or type exit to quit");
				}
			}
		} catch (Exception e) {
			Utils.contactAdmin(e);
		}
		return value;
	}

	public static void contactAdmin(Exception e) {
		System.err.println("OOPS! Sorry there is some problem with application! Contact the admin");
		e.printStackTrace();
	}

	public static void displaySearchDescription() {
		try {
			System.out.println("*****command 1 - Search by Color");
			System.out.println("*****command 2 - Search by Vehicle Number");
			System.out.println("*****command 3 - Search by Slot Number\n\n");
		} catch (Exception e) {
			contactAdmin(e);
		}
	}

	public static void displaySearchResults(Map.Entry<Integer, VehicleDetails> each) {
		try {
			if (each != null) {
				System.out.printf("Slot Number    - %6d\n", each.getKey());
				System.out.printf("Vehicle Number - %8s\n", each.getValue().getVehicleNumber());
				System.out.printf("Vehicle Color  - %8s\n", each.getValue().getVehicleColor());
				System.out.println();
			}
		} catch (Exception e) {
			Utils.contactAdmin(e);
		}
	}
}
