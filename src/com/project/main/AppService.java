package com.project.main;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.project.model.ParkingLot;
import com.project.model.VehicleDetails;
import com.project.util.Utils;

/**
 * 
 * @author chandru
 *
 */
public class AppService {

	public static ParkingLot createParkingLot(int maxSlots) {
		ParkingLot lot = new ParkingLot();
		try {
			Set<Integer> availableSlots = new HashSet<Integer>();
			Map<Integer, VehicleDetails> details = new HashMap<Integer, VehicleDetails>();
			for (int i = 1; i <= maxSlots; i++) {
				availableSlots.add(i);
			}
			lot.setMaxSlot(maxSlots);
			lot.setAvailableSlots(availableSlots);
			lot.setDetails(details);
		} catch (Exception e) {
			Utils.contactAdmin(e);
		}
		return lot;
	}

	public static int allocateParkingForVehicle(ParkingLot currentLot, String vehicleNo, String vehicleColor) {
		int slotNo = 0;
		try {
			Set<Integer> tmp = currentLot.getAvailableSlots();
			int nearestSlot = tmp.iterator().next();
			slotNo = nearestSlot;
			Map<Integer, VehicleDetails> map = currentLot.getDetails();
			VehicleDetails details = new VehicleDetails(vehicleNo, vehicleColor);
			map.put(nearestSlot, details);
			tmp.remove(nearestSlot);
		} catch (Exception e) {
			Utils.contactAdmin(e);
		}
		return slotNo;
	}

	public static void lotStatus(ParkingLot currentLot) {
		try {
			if (currentLot != null) {
				Map<Integer, VehicleDetails> details = currentLot.getDetails();
				if (details != null && details.size() > 0) {
					System.out.println("Slot No. Registeration No. Color");
					for (Map.Entry<Integer, VehicleDetails> each : details.entrySet()) {
						System.out.printf("%6d", each.getKey());
						System.out.printf("%15s", each.getValue().getVehicleNumber());
						System.out.printf("%10s", each.getValue().getVehicleColor());
						System.out.println();
					}
				} else {
					System.out.println("\nThere are no vehicles parked in the lot\n");
				}
			} else {
				System.out.println("\nYou didn't own a parking lot yet!!\n");
			}
		} catch (Exception e) {
			Utils.contactAdmin(e);
		}
	}

	public static int relieveSlot(ParkingLot currentLot, int relieveSlot) {
		int freeSlot = 0;
		try {
			if (currentLot != null) {
				Map<Integer, VehicleDetails> details = currentLot.getDetails();
				if (details != null && details.size() > 0 && details.containsKey(relieveSlot)) {
					Set<Integer> availableSlots = currentLot.getAvailableSlots();
					details.remove(relieveSlot);
					availableSlots.add(relieveSlot);
					freeSlot = relieveSlot;
				} else {
					System.out.println("\nNo slots to relieve\n");
				}
			} else {
				System.out.println("\nYou didn't own a parking lot yet!!\n");
			}
		} catch (Exception e) {
			Utils.contactAdmin(e);
		}
		return freeSlot;
	}

	public static boolean checkSlotAvailability(ParkingLot currentLot) {
		try {
			if (currentLot != null && currentLot.getAvailableSlots().size() > 0) {
				return true;
			}
		} catch (Exception e) {
			Utils.contactAdmin(e);
		}
		return false;
	}
}
