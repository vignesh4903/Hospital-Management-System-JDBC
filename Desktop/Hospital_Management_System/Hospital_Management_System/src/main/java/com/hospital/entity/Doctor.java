package com.hospital.entity;

public class Doctor {

	private int doctorId;
	private String firstName;
	private String lastName;
	private String Specialization;
	private String contactNumber;
	
	public Doctor() {}

	public Doctor(int doctorId, String firstName, String lastName, String specialization, String contactNumber) {
		super();
		this.doctorId = doctorId;
		this.firstName = firstName;
		this.lastName = lastName;
		Specialization = specialization;
		this.contactNumber = contactNumber;
	}

	public int getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getSpecialization() {
		return Specialization;
	}

	public void setSpecialization(String specialization) {
		Specialization = specialization;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	};
	
	
}
