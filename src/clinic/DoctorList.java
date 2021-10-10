package clinic;

import java.util.ArrayList;

public class DoctorList {
	
	//Doctor list
	private ArrayList<Doctor> dList = new ArrayList<Doctor>(); 
	
	//Singleton instance of the doctor list
	private static DoctorList singletonInstance = null;
	
	private boolean hasNext = true;
		
	private int nextDoctor = 0;
	
	/*
	 * Generate the list of doctors
	 */
	private ArrayList<Doctor> generateDoctorList()
	{
		// List parameters
		ArrayList<Doctor> doctorList = new ArrayList<Doctor>();
		int maxDoctors = 12;
		int doctorListSize = RandomUtilities.generateRandomInt(8,maxDoctors);
			
		//Generate unique numbers for idNumber
		int[] uniqueNumbers = RandomUtilities.generateUniqueNumbers(0,9999,doctorListSize);
		
		//Fill in the list of doctors
		for(int i=0; i<doctorListSize;i++)
		{
			/*Setup variables for each doctor*/

			//Generate names
			String firstName = RandomUtilities.generateRandomString(3);
			String lastName = RandomUtilities.generateRandomString(2);
			
			//Generate random age
			int age = RandomUtilities.generateRandomInt(30,65);
		
			//Assign unique id number
			int nr = uniqueNumbers[i];
			String idNumber = String.format("%04d", nr);
			doctorList.add(new Doctor(firstName,lastName,age,idNumber));
		}	
		return doctorList;
	}
    
	/*For singleton we use a private constructor*/
	private DoctorList()
	{
		dList = generateDoctorList();
	}
	
	/* The unique instance of the doctor list */
	public static DoctorList getInstance()
	{
		if(singletonInstance == null)
		singletonInstance = new DoctorList();
		return singletonInstance;
	}
	
	public ArrayList<Doctor> getList()
	{
		return dList;
	}
	
	public Doctor getNextDoctor()
	{
		/* 
		 * We cycle through doctors
		 * When the last doctor is assigned, the list is over
		 */
		Doctor result = dList.get(nextDoctor);
		nextDoctor++;
		
		if(nextDoctor >= dList.size())
		{
		 nextDoctor = dList.size()-1;
		 hasNext = false;
		}
		
		return result;
	}
	public int getDoctorIndex()
	{
		return nextDoctor;
	}
	public boolean hasNext()
	{
		return hasNext;
	}

}
