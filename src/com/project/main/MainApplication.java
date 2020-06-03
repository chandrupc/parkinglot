package com.project.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

import com.project.model.ParkingLot;
import com.project.model.VehicleDetails;
import com.project.util.Utils;

/**
 * 
 * @author chandru
 *
 */
public class MainApplication {

	private static BufferedReader br = null;
	private static ParkingLot currentLot = null;

	static {
		br = new BufferedReader(new InputStreamReader(System.in));
	}

	public static void main(String[] args) {
		try {
			runApp();
		} catch (Exception e) {
			Utils.contactAdmin(e);
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				Utils.contactAdmin(e);
			}
		}
	}

	private static void runApp() {
		try {

			Utils.displayAppDescrption();
			boolean flag = true;
			while (flag) {
				System.out.println("Type your command");
				String input = br.readLine().toLowerCase().trim();
				switch (input) {
				case "create_parking_lot":
				case "create":
					int maxSlot = Integer.valueOf(Utils.getUserInput(br, "[0-9]{1,}$"));
					currentLot = AppService.createParkingLot(maxSlot);
					System.out.println("\nCreated a parking lot with " + maxSlot + " slots\n");
//					AppService.lotStatus(currentLot);
					break;

				case "park":
					boolean isSlotAvailable = AppService.checkSlotAvailability(currentLot);
					if (isSlotAvailable) {
						System.out.println("\nVehicle number to park\n");
						String vehicleNo = Utils.getUserInput(br, "[A-Z]{2}-[0-9]{1,2}-([A-Z]{1,3}-)?[0-9]{1,4}");
						System.out.println("\nVehicle color to park\n");
						String vehicleColor = Utils.getUserInput(br, "[A-Za-z]{3,}");
						int allocatedSlot = AppService.allocateParkingForVehicle(currentLot, vehicleNo, vehicleColor);
						System.out.println("\nAllocated slot number: " + allocatedSlot + "\n");
//						AppService.lotStatus(currentLot);
					} else {
						if (currentLot == null) {
							System.out.println("\nYou didn't own a parking lot yet!!\n");
						} else {
							System.out.println("\nSorry, parking lot is full\n");
						}
					}
					break;

				case "status":
					AppService.lotStatus(currentLot);
					break;

				case "leave":
					int relieveSlot = Integer.valueOf(Utils.getUserInput(br, "[0-9]{1,}$"));
					int freeSlot = AppService.relieveSlot(currentLot, relieveSlot);
					if (freeSlot > 0) {
						System.out.println("\nSlot No " + freeSlot + " is free.\n");
					}
//					AppService.lotStatus(currentLot);
					break;

				case "search":
					Utils.displaySearchDescription();
					int searchOption = Integer.valueOf(Utils.getUserInput(br, "[0-9]{1}$"));
					switch (searchOption) {
					case 1:
						System.out.println("\nCool now enter the vehicle color to search\n");
						String vehicleColor = Utils.getUserInput(br, "[A-Za-z]{3,}");
						int count = 0;
						if (currentLot != null) {
							for (Map.Entry<Integer, VehicleDetails> each : currentLot.getDetails().entrySet()) {
								if (each.getValue().getVehicleColor().equals(vehicleColor)) {
									Utils.displaySearchResults(each);
									count++;
								}
							}
							if (count == 0) {
								System.out.println("OOPS!@@ Not Found");
							}
						} else {
							System.out.println("\nYou dont own a parking lot yet!\n");
						}
						break;
					case 2:
						count = 0;
						System.out.println("\nCool now enter the vehicle number to search\n");
						String vehicleNo = Utils.getUserInput(br, "[A-Z]{2}-[0-9]{1,2}-([A-Z]{1,3}-)?[0-9]{1,4}");
						if (currentLot != null) {
							for (Map.Entry<Integer, VehicleDetails> each : currentLot.getDetails().entrySet()) {
								if (each.getValue().getVehicleNumber().equals(vehicleNo)) {
									Utils.displaySearchResults(each);
									count++;
								}
							}
							if (count == 0) {
								System.out.println("OOPS!@@ Not Found");
							}
						} else {
							System.out.println("\nYou dont own a parking lot yet!\n");
						}
						break;
					case 3:
						count = 0;
						System.out.println("Cool now enter the slot number to search");
						int slotNo = Integer.valueOf(Utils.getUserInput(br, "[0-9]{1,}$"));
						if (currentLot != null) {
							for (Map.Entry<Integer, VehicleDetails> each : currentLot.getDetails().entrySet()) {
								if (each.getKey() == slotNo) {
									count++;
									Utils.displaySearchResults(each);
								}
							}
							if (count == 0) {
								System.out.println("OOPS!@@ Not Found");
							}
						} else {
							System.out.println("\nYou dont own a parking lot yet!\n");
						}
						break;
					default:
						System.err.println("\nInvalid search option. Please try again with valid search option\n");
					}
					break;

				case "help":
					Utils.displayAppDescrption();
					break;

				case "exit":
					System.out.println("\n\n*********Bye Bye :-)***********\n\n");
					flag = false;
					break;

				default:
					System.err.println(
							"OoPs!! you provided a invalid choice.\nType help to view guide\nType exit to quit the app----:-(");
					break;
				}
			}
		} catch (

		Exception e) {
			Utils.contactAdmin(e);
		}
	}

}
