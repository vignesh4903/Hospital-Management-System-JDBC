package com.hospital.dao;

import java.util.List;

import com.hospital.entity.Appointment;
import com.hospital.entity.Patient;
import com.hospital.exception.PatientNumberNotFoundException;

public interface IHospitalService {

	Patient getPatientById(int id);
	
	Appointment getAppointmentById(int appointmentId);
	
	List<Appointment> getAppointmentsForPatient(int patientId) throws PatientNumberNotFoundException;
	
	List<Appointment> getAppointmentsForDoctor(int doctorId);
	
	boolean scheduleAppointment(Appointment appointment);
	
	boolean updateAppointment(Appointment appointment);
	
	boolean cancelAppointment(int appointmentId);
}
