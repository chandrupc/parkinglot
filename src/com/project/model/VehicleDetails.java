package com.project.model;

/**
 * 
 * @author chandru
 *
 */
public class VehicleDetails {

	private String vehicleNumber;
	private String vehicleColor;

	public VehicleDetails() {
		super();		
	}

	public VehicleDetails(String vehicleNumber, String vehicleColor) {
		super();
		this.vehicleNumber = vehicleNumber;
		this.vehicleColor = vehicleColor;
	}

	public String getVehicleNumber() {
		return vehicleNumber;
	}

	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}

	public String getVehicleColor() {
		return vehicleColor;
	}

	public void setVehicleColor(String vehicleColor) {
		this.vehicleColor = vehicleColor;
	}

	@Override
	public String toString() {
		return "VehicleDetails [vehicleNumber=" + vehicleNumber + ", vehicleColor=" + vehicleColor + "]";
	}

}
