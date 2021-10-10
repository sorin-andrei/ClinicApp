package clinic;

import java.util.ArrayList;
import java.util.Collections;

import clinic.Patient.Category;
import clinic.Patient.Reason;

public class PatientList{
	
	//Create the distributions in order to make sure we have each of one age group and reason
	private static int[] reasonDistribution = RandomUtilities.generateDistribution(100, 3);
	private static int[] ageDistribution = RandomUtilities.generateDistribution(100, 4);
	
	private int nextPatient = 0;
	
	//Patient List
	private ArrayList<Patient> pList = new ArrayList<Patient>();
	
	//Singleton instance of the patient list
	private static PatientList singletonInstance = null;
		
	/*
	 * Print the patient summary
	 */
	public void printPatientSummary()
	{
		System.out.println("Children(0-1): "+ageDistribution[0]+" patients");
		System.out.println("Pupil(1-7): "+ageDistribution[1]+" patients");
		System.out.println("Student(7-18): "+ageDistribution[2]+" patients");
		System.out.println("Adult(>18): "+ageDistribution[3]+" patients");
	}
	
	/*
	 * Generate the list of patients
	 */
	private ArrayList<Patient> generatePatientList()
	{
		//Create the empty patient list
		ArrayList<Patient> patientList = new ArrayList<Patient>();
		
		//Setup the age categories and reasons for visit
		ArrayList<Category> category = new ArrayList<Category>();
		ArrayList<Reason> reason = new ArrayList<Reason>();
		
		//Fill in the age categories
	    for(int i=0; i<ageDistribution.length; i++)
	    	for(int j=0; j<ageDistribution[i];j++)
	    	category.add(Category.values()[i]);
	    	
	    //Fill in the reasons
	    for(int i=0;i<reasonDistribution.length;i++)
	    	for(int j=0; j<reasonDistribution[i];j++)
	    		reason.add(Reason.values()[i]);
	    
	    //Shufle the age groups and reasons
	    Collections.shuffle(category);
	    Collections.shuffle(reason);
	    
		//Generate 100 patients
		for(int i=0;i<100;i++)
		{
			//Generate names
			String firstName = RandomUtilities.generateRandomString(5);
			String lastName = RandomUtilities.generateRandomString(4);
			
			//Assign age categories and reasons
		    Category ageCategory = category.get(i);
			Reason visitReason = reason.get(i);
			
			//Create a new patient
			Patient p = new Patient(firstName,lastName,ageCategory,visitReason);
			
			//Set their age
			switch(ageCategory.ordinal()) 
			{
			case 0:
				p.setAge(RandomUtilities.generateRandomInt(0, 1));
				break;
			case 1:
				p.setAge(RandomUtilities.generateRandomInt(1, 7));
				break;
			case 2:
				p.setAge(RandomUtilities.generateRandomInt(7, 18));
				break;
			case 3:
				p.setAge(RandomUtilities.generateRandomInt(19,100));
				break;
			}
			
			//Add patient to list
			patientList.add(p);
		}
		return patientList;
	}
	
	/* Singleton uses private constructor */
	private PatientList()
	{
		pList = generatePatientList();
	}
	
	/* The unique instance of the patient list */
	public static PatientList getInstance()
	{
		if(singletonInstance == null)
		singletonInstance = new PatientList();
		return singletonInstance;
	}
	
	public ArrayList<Patient> getList()
	{
		return pList;
	}
	
	public Patient getNextPatient()
	{	
		if(nextPatient >= pList.size())
			nextPatient=0;
		/* 
		 * We cycle through patients
		 * When the last patient is assigned, the list is over
		 */
		Patient result = pList.get(nextPatient);
		nextPatient++;
		
		return result;
	}
	public void revertPatient()
	{
		nextPatient--;
	}
	public void sortPatients()
	{
		Collections.sort(pList, Patient.PatientAgeCategoryComparator);
	}
}
