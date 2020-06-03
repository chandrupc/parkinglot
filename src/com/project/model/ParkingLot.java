package com.project.model;

import java.util.Map;
import java.util.Set;

/**
 * 
 * @author chandru
 *
 */
public class ParkingLot {

	private int maxSlot;
	private Set<Integer> availableSlots;
	private Map<Integer, VehicleDetails> details;

	public ParkingLot() {
		super();
	}

	public int getMaxSlot() {
		return maxSlot;
	}

	public void setMaxSlot(int maxSlot) {
		this.maxSlot = maxSlot;
	}

	public Set<Integer> getAvailableSlots() {
		return availableSlots;
	}

	public void setAvailableSlots(Set<Integer> availableSlots) {
		this.availableSlots = availableSlots;
	}

	public Map<Integer, VehicleDetails> getDetails() {
		return details;
	}

	public void setDetails(Map<Integer, VehicleDetails> details) {
		this.details = details;
	}

	@Override
	public String toString() {
		return "ParkingLot [maxSlot=" + maxSlot + ", availableSlots=" + availableSlots + " details=" + details + "]";
	}
}