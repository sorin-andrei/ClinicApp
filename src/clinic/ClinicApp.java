package clinic;

import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;

public class ClinicApp {
	
	public static void main(String[] args) throws IOException
	{
		/* a) Generate a list of doctors. */
		DoctorList doctorListInstance = DoctorList.getInstance();
		ArrayList<Doctor> dList = doctorListInstance.getList();
		
		/* b) Generate a list of patients. */
		PatientList patientListInstance = PatientList.getInstance();
		ArrayList<Patient> pList = patientListInstance.getList();
		
		/* c) Print the list of doctors and the list of patients on the console. 
		 * Doctors */		 
		System.out.println("-- The Doctors --");
		for(Doctor d: dList)
			System.out.println(d.toString());
		System.out.println();
				
		/* Patients */
		System.out.println("-- The Patients --");
		for(Patient p: pList)
			System.out.println(p.toString());
	    System.out.println();
		
		/* (extra) Print a summary of all patients based on their age group. */
		System.out.println("-- Patient Summary --");
		patientListInstance.printPatientSummary();
		System.out.println();
		
		/* d) Store the list of patients in a file on disk. */
	    FileWriter writer = new FileWriter("src\\clinic\\patientlist");
		for(Patient p: pList)
		 writer.write(p.toString()+"\n");
		writer.close();
		
		/* e) Store the list of doctors in a file on disk. */
		writer = new FileWriter("src\\clinic\\doctorlist");
		for(Doctor d: dList)
		 writer.write(d.toString()+"\n");
		writer.close();
		
		/* f) Simulate the system */
		
		/* Sort the patient list
		 * Priority is based on age category */
		patientListInstance.sortPatients();
		
		// Problem specifies 4 consulting rooms
		int roomCount = 4;
			
		//Setup the consulting rooms
		ArrayList<ArrayList<Doctor>> rooms = new ArrayList<ArrayList<Doctor>>();
		
		//Create the rooms
		for(int i=0; i<roomCount;i++)
		{
			if(i>dList.size())
				break; // Don't create empty rooms
			rooms.add(new ArrayList<Doctor>());
		}
		
		//Assign doctors to rooms
		while(doctorListInstance.hasNext())
		for(ArrayList<Doctor> room : rooms)
		{
			if(!doctorListInstance.hasNext())
				break;
			room.add(doctorListInstance.getNextDoctor());
		}
		
	    /* We are ready to start the simulation */
		for(ArrayList<Doctor> room : rooms)
		{
			//For each room, we iterate the doctors that were assigned to it
			int doctorIndex=0;
			int timePassed = 0;
			/* 7 to 19 is 12 hours 
			 * We calculate the time passed in minutes 
			 * */
			while(timePassed<=12*60) 
			{
				Doctor d;
				try 
				{
				d = room.get(doctorIndex);
				}
				catch(IndexOutOfBoundsException e) // If we hit index out of bounds, that means that all doctors from the room have worked
				{
				break;	
				}
				/* A doctor handles all the patients they can, until their time runs out */
				while(d.canWork())
				{
					/* Assign next patient */
					Patient p = patientListInstance.getNextPatient();
					
					if(d.getTimeWorked() + p.getTimeAllocated() > d.getMaxTime()) // If the doctor doesn't have time, cancel the operation
					{
						d.setCanWork(false); //The doctor's work time is over 
						patientListInstance.revertPatient(); // If we break the loop without taking back the patient, he will be unfairly skipped
						break;
					}
					if(p.isConsulted())
						break;
					
					if(timePassed + p.getTimeAllocated() > 12*60)  // We "leave" the room when the schedule is over
					break;
					
					/* Doctor handles the patient.
					 * This is where the actual consultation takes place.
					 * We update the stats for each doctor.
					 */
					d.increaseTimeWorked(p.getTimeAllocated());
					d.increaseBilling(p.getFee());
					d.incrementPatients();
					
					p.setConsulted();
					timePassed+=p.getTimeAllocated(); //Advance time
				}
				doctorIndex++;	// We stay in the room, the next doctor is assigned
			}
		}
		
		/* Print a summary of the doctors, the number of patients consulted and the total amount billed */
		System.out.println("-- Summary --");
		for(Doctor d : dList)
			d.printSummary();
		System.out.println();
		
		System.out.println("-- Unconsulted patients -- ");
		for(Patient p: pList)
			if(!p.isConsulted())
				System.out.println(p.toString());		
	}	
}
