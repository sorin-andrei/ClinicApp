package clinic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class RandomUtilities {
	
	// General parameters
	private static String alphabet = "ABCDEFGHIJKLMONPQRSTUVWXYZ";
	
	//Utility function to build random strings
	public static String generateRandomString(int capacity)
	{
		StringBuilder result = new StringBuilder(capacity);
		for(int i=0; i<capacity; i++)
			result.append(generateRandomChar());
		
		return result.toString();
	}
		
	//Utility function to generate a random letter
	public static char generateRandomChar()
	{
		int randomIndex = ThreadLocalRandom.current().nextInt(alphabet.length());
		char randomChar = alphabet.charAt(randomIndex);
		return randomChar;
	}
		
	//Utility function to generate random number
	public static int generateRandomInt(int min, int max)
	{
		return ThreadLocalRandom.current().nextInt(min, max + 1);
	}
	
	/*
	 * Utility function to generate a random distribution of numbers
	 * We need this to make sure we have at least a patient in each age category and reason
	 * My approach is not perfect but it can get the job done for now
	 */
	public static int[] generateDistribution(int number, int size)
	{
		int[] distribution = new int[size];
		int temp = number;
		for(int i=0;i<size-1;i++)
		{
			//We keep substracting random numbers from 100
			int randomNumber = generateRandomInt(1,temp/2);
			temp = temp - randomNumber;
			distribution[i] = randomNumber;
		}
		distribution[size-1] = temp;
		
		//For extra randomness I shuffle the distribution
		shuffleInt(distribution);
		
		return distribution;
	}
	
    /*
     * Utility function to generate a series of unique numbers
     * Returns an array of [capacity] unique numbers between [min] and [max]
     */
	public static int[] generateUniqueNumbers(int min, int max, int capacity)
	{
		int[] res = ThreadLocalRandom.current().ints(min, max).distinct().limit(capacity).toArray();
	    return res;
	}
	
	//Utility function to shuffle an int array
	public static void shuffleInt(int[] array) 
	{	  
		List<Integer> list = new ArrayList<>();
		  for (int i : array) 
		    list.add(i);
		  
		  Collections.shuffle(list);

		  for (int i = 0; i < list.size(); i++) 
		    array[i] = list.get(i);    
	}
	
}
