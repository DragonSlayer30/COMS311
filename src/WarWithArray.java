// LEAVE THIS FILE IN THE DEFAULT PACKAGE
//  (i.e., DO NOT add 'package cs311.pa1;' or similar)

// DO NOT MODIFY THE EXISTING METHOD SIGNATURES
//  (you may, however, add member fields and additional methods)

// DO NOT INCLUDE LIBRARIES OUTSIDE OF THE JAVA STANDARD LIBRARY
//  (i.e., you may include java.util.ArrayList etc. here, but not junit, apache commons, google guava, etc.)

import java.util.ArrayList;


public class WarWithArray
{
	String[] givenSubStrings;
	int lengthOfStrings = 0;
	boolean debug = true;

	public WarWithArray(String[] s, int k)
	{
		if(s != null && s.length > 0) {
			givenSubStrings = s;
		}
		if(k > 0) lengthOfStrings = k; 
	}


	private String[] sortArray(String[] s) {
		// TODO Auto-generated method stub
		String[] tempArr = new String[s.length];
		
		return tempArr;
	}


	/**
	 * Outer Loop will run n times
	 * 
	 */
	public ArrayList<String> compute2k()
	{
		ArrayList<String> substring2klength = new ArrayList<String>();
		if(givenSubStrings == null || lengthOfStrings == 0) return substring2klength;
		// outer loop
		for (String stringK : givenSubStrings) {
			ArrayList<String> temp = generate2KstringArray(stringK);
			if(!temp.isEmpty()) {
				substring2klength.addAll(temp);
				for (String stringDouble : temp) {
					if(debug) System.out.println("Generated String for " + stringK + " : " + stringDouble);
				}
			}
		}
		
		return substring2klength;
	}


	private String generate2Kstring(String stringK) {
		// TODO Auto-generated method stub
		String generatedString = stringK;
		int stringlength = 1;
		boolean loopCheck = true;
		while(stringlength <= lengthOfStrings && loopCheck) {
			for (String given : givenSubStrings) {
				if(given.startsWith(generatedString.substring(stringlength, generatedString.length()))) {
					generatedString = generatedString.concat(given.substring(lengthOfStrings - 1));
					stringlength++;
					loopCheck = false;
					break;
				}   
			}
			if(loopCheck) break;
			else loopCheck = true;
		}
		if(generatedString.length() != 2*lengthOfStrings) generatedString = "";
		return generatedString;
	}

	/**
	 * @param stringK this is the input string
	 * We generate strings starting with stringk which are present in S
	 * while loop will run k times in worst case scenario
	 * outer for loop will run in total n times
	 * for each outer loop the inner loop will run n times 
	 * @return we will return all the strings starting with stringk
	 */
	private ArrayList<String> generate2KstringArray(String stringK) {
		// TODO Auto-generated method stub
		if(debug) System.out.println("generating for string : " + stringK);
		ArrayList<String> list = new ArrayList<String>();
		list.add(stringK);
		int stringlength = 1;
		ArrayList<String> tempArr = new ArrayList<String>();
		tempArr.add(stringK);
		while(stringlength <= lengthOfStrings) {
			tempArr.clear();
			//outer for loop 
			for (String generatedString : list) {
				// inner for loop
				for (String given : givenSubStrings) {
					if(given.startsWith(generatedString.substring(stringlength, generatedString.length()))) {
						tempArr.add(generatedString.concat(given.substring(lengthOfStrings - 1)));
					}   
				}
			}
			if(tempArr.isEmpty()) {
				list.clear();
				break;
			} 
			stringlength++;
			list.clear();
			list.addAll(tempArr);
		}
		if(debug) {
			for (String string : list) {
				System.out.println("Answer " + (lengthOfStrings + stringlength - 1) + " " + string);
			}
		}
		if(debug) {
			for (String string : list) {
				System.out.println("Strings generated for " + stringK + " : " + string);
			}
		}
		return list;
	}
	
	private ArrayList<String> generate2KstringSorted(String stringK) {
		// TODO Auto-generated method stub
		if(debug) System.out.println("generating for string : " + stringK);
		ArrayList<String> list = new ArrayList<String>();
		list.add(stringK);
		int stringlength = 1;
		ArrayList<String> tempArr = new ArrayList<String>();
		tempArr.add(stringK);
		while(stringlength <= lengthOfStrings) {
			tempArr.clear();
			//outer for loop 
			for (String generatedString : list) {
				// inner for loop
				for (String given : givenSubStrings) {
					if(given.startsWith(generatedString.substring(stringlength, generatedString.length()))) {
						tempArr.add(generatedString.concat(given.substring(lengthOfStrings - 1)));
					}   
				}
			}
			if(tempArr.isEmpty()) {
				list.clear();
				break;
			} 
			stringlength++;
			list.clear();
			list.addAll(tempArr);
		}
		if(debug) {
			for (String string : list) {
				System.out.println("Answer " + (lengthOfStrings + stringlength - 1) + " " + string);
			}
		}
		if(debug) {
			for (String string : list) {
				System.out.println("Strings generated for " + stringK + " : " + string);
			}
		}
		return list;
	}
}

