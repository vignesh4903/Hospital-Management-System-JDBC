package com.hospital.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.hospital.entity.Appointment;
import com.hospital.entity.Patient;
import com.hospital.exception.PatientNumberNotFoundException;
import com.hospital.util.DBConnUtil;

public class PatientDao implements IHospitalService {

	@Override
	public Patient getPatientById(int id) {
		
		Patient patient = null;
		
		try {
			Connection con = DBConnUtil.getConnection();
			String query = "SELECT * FROM Patient WHERE patientId = ?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
			   patient = new Patient(rs.getInt("patientId"),
					                 rs.getString("firstName"),
					                 rs.getString("lastName"),
					                 rs.getDate("dateOfBirth"),
					                 rs.getString("gender"),
					                 rs.getString("contactNumber"),
					                 rs.getString("address"));
			}
			rs.close();
			ps.close();
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return patient;
	}
	
	@Override
	public Appointment getAppointmentById(int appointmentId) {
		
		Appointment appointment = null;
		try {
			
			Connection con = DBConnUtil.getConnection();
			String query = "SELECT * FROM Appointment WHERE appointmentId = ?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1,appointmentId);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				appointment = new Appointment(rs.getInt("appointmentId"),
											  rs.getInt("patientId"),
											  rs.getInt("doctorId"),
											  rs.getDate("appointmentDate"),
											  rs.getString("description"));
			}
			
			rs.close();
			ps.close();
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return appointment;
	}
	
	@Override
	public List<Appointment> getAppointmentsForPatient(int patientId) throws PatientNumberNotFoundException{
		
		List<Appointment> list = new ArrayList<>();
		try {
			
			Connection con = DBConnUtil.getConnection();
			String query = "SELECT * FROM Appointment WHERE patientId= ?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1,patientId);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				
				Appointment ap = new Appointment(rs.getInt("appointmentId"),
												 rs.getInt("patientId"),
												 rs.getInt("doctorId"),
												 rs.getDate("appointmentDate"),
												 rs.getString("description"));
				
				list.add(ap);
			}
			rs.close();
			ps.close();
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		if(list.isEmpty()) {
			throw new PatientNumberNotFoundException("No appointments found for Patient ID: " + patientId);
		}
		return list;
	}
	
	@Override
	public List<Appointment> getAppointmentsForDoctor(int doctorId){
		List<Appointment> list = new ArrayList<>();
		try {
			
			Connection con = DBConnUtil.getConnection();
			String query = "SELECT * FROM Appointment WHERE doctorId= ?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1,doctorId);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				
				Appointment ap = new Appointment(rs.getInt("appointmentId"),
												 rs.getInt("patientId"),
												 rs.getInt("doctorId"),
												 rs.getDate("appointmentDate"),
												 rs.getString("description"));
				
				list.add(ap);
			}
			rs.close();
			ps.close();
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public boolean scheduleAppointment(Appointment ap) {
		
		boolean status = false;
		try {
			Connection con = DBConnUtil.getConnection();
			String query = "INSERT INTO Appointment(appointmentid,patientId,doctorId,appointmentDate,description)values(?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, ap.getAppointmentId());
			ps.setInt(2, ap.getPatientId());
			ps.setInt(3, ap.getDoctorId());
			ps.setDate(4, ap.getAppointmentDate());
			ps.setString(5, ap.getDescription());
			int rows = ps.executeUpdate();
			if(rows>0) {
				status = true;
			}
			ps.close();
			con.close();
		}catch(Exception e ) {
			e.printStackTrace();
		}
		return status;
	}
	
	@Override
	public boolean updateAppointment(Appointment ap) {
		
		boolean status = false;
		try {
			Connection con = DBConnUtil.getConnection();
			String query = "UPDATE Appointment SET description = ? WHERE appointmentId = ?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, ap.getDescription());
			ps.setInt(2, ap.getAppointmentId());
			
			int rows = ps.executeUpdate();
			if(rows>0) {
				status = true;
			}
			ps.close();
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return status;
	}
	
	@Override
	public boolean cancelAppointment(int appointmentId) {
		
		boolean status = false;
		try {
			Connection con = DBConnUtil.getConnection();
			String query = "DELETE FROM Appointment WHERE appointmentId = ?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, appointmentId);
			int rows = ps.executeUpdate();
			if(rows>0) {
				status = true;
			}
			ps.close();
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return status;
	}
}
