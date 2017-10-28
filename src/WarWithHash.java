// LEAVE THIS FILE IN THE DEFAULT PACKAGE
//  (i.e., DO NOT add 'package cs311.pa1;' or similar)

// DO NOT MODIFY THE EXISTING METHOD SIGNATURES
//  (you may, however, add member fields and additional methods)

// DO NOT INCLUDE LIBRARIES OUTSIDE OF THE JAVA STANDARD LIBRARY
//  (i.e., you may include java.util.ArrayList etc. here, but not junit, apache commons, google guava, etc.)

import java.util.ArrayList;
import java.util.HashSet;


public class WarWithHash
{
	String[] givenStringsArr;
	HashSet<String> givenHash = new HashSet<String>();
	int lengthOfStrings = 0;
	boolean debug = false;
	
	public WarWithHash(String[] s, int k)
	{
		if(s != null && s.length > 0) {
			givenStringsArr = s;
			for (String string : s) {
				givenHash.add(string);
			}
		}
		if(k > 0) lengthOfStrings = k; 
	}

	public ArrayList<String> compute2k()
	{	
		ArrayList<String> substring2klength = new ArrayList<String>();
		if(givenHash.isEmpty() || lengthOfStrings == 0) return substring2klength;
		for (String stringK : givenHash) {
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
			for (String generatedString : list) {
				for (String given : givenHash) {
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

