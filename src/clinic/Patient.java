package clinic;

import java.util.Comparator;

public class Patient{
	
	//Identification parameters for the patient
	private String firstName;
	private String lastName;
	private int age;
	
	//Status
	private boolean isConsulted = false;
	
	//Patient billing information
	private int fee;
	private int timeAllocated;
	
	//Reasons to visit the clinic
	public enum Reason
	{
		CONSULTATION, TREATMENT, PRESCRIPTION
	}
	
	//Age category
	public enum Category
	{
		CHILDREN,PUPIL,STUDENT,ADULT
	}
	
	// Java is funny
	private Reason reason;
	private Category category;
	
	//Constructor for patient
	public Patient(String firstName, String lastName, Category category, Reason reason)
	{
		//Initialize identification values
		this.firstName = firstName;
		this.lastName = lastName;
		this.category = category;
		this.reason = reason;
		
		//Set time and fee
		/*
		 * Determine the time and cost of the consultation
		 */
		switch(reason.ordinal())
		{
		case 0:
			fee=50;
			timeAllocated=30;
			break;
		case 1:
			fee=35;
			timeAllocated=40;
			break;
		case 2:
			fee=20;
			timeAllocated=20;
			break;
		}
	}
	
	public void setConsulted()
	{
		isConsulted = true;
	}
	
	public int getFee()
	{
		return fee;
	}
	
	public int getTimeAllocated()
	{
		return timeAllocated;
	}
	
	public boolean isConsulted()
	{
		return isConsulted;
	}
	
	public String toString()
	{
		return(firstName+", "+lastName+", "+age+" years"+", "+reason.toString().toLowerCase());
	}
	
	public void setReason(Reason newReason)
	{
		reason = newReason;
	}
	
	public void setAge(int newAge)
	{
		age=newAge;
	}
	
	public static Comparator<Patient> PatientAgeCategoryComparator = new Comparator<Patient>()
			{
		public int compare(Patient p1, Patient p2)
		{
			return p1.category.compareTo(p2.category);
		}
		
			};
}
