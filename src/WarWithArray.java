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
			sortArr(givenSubStrings,0,givenSubStrings.length - 1);
		}
		if(k > 0) lengthOfStrings = k; 
	}

	public void sortArr(String givenArr[], int left, int right)
	{
		if (left < right)
		{
			int mid = (left+right)/2;
			sortArr(givenArr, left, mid);
			sortArr(givenArr , mid+1, right);
			mergeSort(givenArr, left, mid, right);
		}
	}

	public void mergeSort(String givenArray[], int left, int mid, int right) {
		int leftIndex = mid - left + 1;
		int rightIndex = right - mid;

		String leftArr[] = new String [leftIndex];
		String rightArr[] = new String [rightIndex];

		for (int i=0; i<leftIndex; ++i)
			leftArr[i] = givenArray[left + i];
		for (int j=0; j<rightIndex; ++j)
			rightArr[j] = givenArray[mid + 1+ j];


		int i = 0, j = 0;
		int k = left;
		while (i < leftIndex && j < rightIndex)
		{
			if (leftArr[i].compareTo(rightArr[j]) < 0 )
			{
				givenArray[k] = leftArr[i];
				i++;
			}
			else
			{
				givenArray[k] = rightArr[j];
				j++;
			}
			k++;
		}
		while (i < leftIndex)
		{
			givenArray[k] = leftArr[i];
			i++;
			k++;
		}
		while (j < rightIndex)
		{
			givenArray[k] = rightArr[j];
			j++;
			k++;
		}
	}

	public int binarySearch(String givenArr[], int left, int right, String needle)
	{	
		if (right < left || ((right == left) && right == givenArr.length))
			return -1;

		int mid = (left + right)/2;  
		int compare = needle.compareTo(givenArr[mid]);
		if(compare == 0) return mid;
		else if(compare >  0) return binarySearch(givenArr, (mid + 1), right, needle);
		return binarySearch(givenArr, left, (mid -1), needle);
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
			ArrayList<String> temp = generate2kByLoop(stringK);
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

	public ArrayList<String> generate2kByLoop(String stringK) {
		if(debug) System.out.println("generating for string : " + stringK);
		ArrayList<String> list = new ArrayList<String>();
		int k = stringK.length();
		boolean check = true;
		for (String given : givenSubStrings) {
			String combinedString = stringK.concat(given);
			check = true;
			for(int i = 1; i < k ; i++) {
				System.out.println("Combined String " + combinedString + " Substring : " + combinedString.substring(i, i + k));
				System.out.println("Search :" + binarySearch(givenSubStrings, 0, givenSubStrings.length, combinedString.substring(i, i + k)));
				if(binarySearch(givenSubStrings, 0, givenSubStrings.length, combinedString.substring(i, i + k)) < 0) {
					check = false;
					break;
				}
			}
			if(check) {
				System.out.println("Adding : " + combinedString);
				list.add(combinedString);
			}
			else System.out.println("Flag is false");
		}
		return list;
	}
}

