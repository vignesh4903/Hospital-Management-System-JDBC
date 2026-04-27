package com.hospital.main;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

import com.hospital.dao.IHospitalService;
import com.hospital.dao.PatientDao;
import com.hospital.entity.Appointment;
import com.hospital.exception.PatientNumberNotFoundException;

public class MainModule {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		IHospitalService service = new PatientDao();
		while(true) {
			System.out.println("\n===== HOSPITAL MANAGEMENT SYSTEM =====");
            System.out.println("1. Get Appointment by ID");
            System.out.println("2. Get Appointments for Patient");
            System.out.println("3. Get Appointments for Doctor");
            System.out.println("4. Schedule Appointment");
            System.out.println("5. Update Appointment");
            System.out.println("6. Cancel Appointment");
            System.out.println("7. Exit");

            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            
            switch(choice) {
            	case 1:
            		System.out.println("Enter Appointment ID: ");
            		int aid = sc.nextInt();
            		
            		Appointment ap = service.getAppointmentById(aid);
            		
            		if(ap!=null) {
            			System.out.println(ap);
            		}else {
            			System.out.println("Appointment not found");
            		}
            		break;
            		
            	case 2:
            		
            		System.out.println("Enter Patient ID: ");
            		int pid = sc.nextInt();
            		
            		try {
            			List<Appointment> list1 = service.getAppointmentsForPatient(pid);
            			for(Appointment a : list1) {
            				System.out.println(a);
            			}
            		}catch(PatientNumberNotFoundException e) {
            			System.out.println(e.getMessage());
            		}
            		break;
            		
            	case 3:
            		System.out.print("Enter Doctor ID: ");
                    int did = sc.nextInt();

                    List<Appointment> list2 = service.getAppointmentsForDoctor(did);

                    if (list2.isEmpty()) {
                        System.out.println("No appointments found");
                    } else {
                        for (Appointment a : list2) {
                            System.out.println(a);
                        }
                    }
                    break;
            		
            	case 4:
            		System.out.print("Enter Appointment ID: ");
                    int id = sc.nextInt();

                    System.out.print("Enter Patient ID: ");
                    int patientId = sc.nextInt();

                    System.out.print("Enter Doctor ID: ");
                    int doctorId = sc.nextInt();

                    System.out.print("Enter Date (yyyy-mm-dd): ");
                    String date = sc.next();

                    sc.nextLine(); 

                    System.out.print("Enter Description: ");
                    String desc = sc.nextLine();

                    Appointment newAp = new Appointment(
                            id,
                            patientId,
                            doctorId,
                            Date.valueOf(date),
                            desc
                    );

                    boolean added = service.scheduleAppointment(newAp);

                    if (added) {
                        System.out.println("Appointment scheduled successfully");
                    } else {
                        System.out.println("Failed to schedule appointment");
                    }
                    break;
                    
            	case 5:
            		
            		System.out.print("Enter Appointment ID to update: ");
                    int upId = sc.nextInt();

                    sc.nextLine();
                    System.out.print("Enter new description: ");
                    String newDesc = sc.nextLine();

                    Appointment updateAp = new Appointment();
                    updateAp.setAppointmentId(upId);
                    updateAp.setDescription(newDesc);

                    boolean updated = service.updateAppointment(updateAp);

                    if (updated) {
                        System.out.println("Appointment updated successfully");
                    } else {
                        System.out.println("Update failed");
                    }
                    break;

                    
            	case 6:
            		
            		System.out.print("Enter Appointment ID to delete: ");
                    int delId = sc.nextInt();

                    boolean deleted = service.cancelAppointment(delId);

                    if (deleted) {
                        System.out.println("Appointment cancelled successfully");
                    } else {
                        System.out.println("Delete failed");
                    }
                    break;

            	case 7:
                    System.out.println("Exiting...");
                    sc.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice");
            }
		}
	}
}
