package clinic;

public class Doctor {
	
	//Identification parameters for the doctor
	private String firstName;
	private String lastName;
	private String idNumber;
	private int age;
	private int maxTime = 7*60; //in minutes
	
	//Doctor statistics for a day
	private int timeWorked = 0;
	private int billedAmmount = 0;
	private int patientsConsulted = 0;
	
	private boolean canWork = true;
	
	//Constructor for doctor
	public Doctor(String firstName, String lastName, int age, String idNumber)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.idNumber = idNumber;
	}
	
	public void printSummary()
	{
		System.out.println(firstName +" "+lastName+" - "+idNumber+": "+patientsConsulted+" patients, "+timeWorked+" minutes, "+billedAmmount+" RON");
	}
	
	public int getTimeWorked()
	{
		return timeWorked;
	}
	public void increaseTimeWorked(int ammount)
	{
	    timeWorked+=ammount;	
	}
	public int getMaxTime()
	{
		return maxTime;
	}
	public void increaseBilling(int ammount)
	{
		billedAmmount+=ammount;
	}
	public void incrementPatients()
	{
		patientsConsulted++;
	}
	
	public String toString()
	{
		return(firstName+", "+lastName+", "+age+" years, ID: "+idNumber);
	}
	public boolean canWork()
	{
		return canWork;
	}
	public void setCanWork(boolean val)
	{
		canWork = val;
	}
	public int getPatientCount()
	{
		return patientsConsulted;
	}

}
