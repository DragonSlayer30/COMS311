import java.util.ArrayList;
import java.util.HashSet;

public class TestAssignment {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestAssignment assignment = new TestAssignment();;
		assignment.testWarWithArray();
		//assignment.testWarWithHash();
		//assignment.testBinaryST();
	}
	
	private void testBinaryST() {
		// TODO Auto-generated method stub
		//String[] arrayStrings = {"ABC", "BCD", "CDE", "DEF", "EFG", "FGH", "GHI", "ABC", "BCD", "ABB", "AAA"};
		//String[] arrayStrings = {"Y", "Z", "X", "A","B","C","U"};
		String[] arrayStrings = {"U", "B", "Y", "C", "X", "Z", "A", "D", "P", "E", "A", "I", "U"};
		//String[] arrayStrings = {"U", "B", "Y", "C", "X"};
		//String[] arrayStrings = {"A", "A", "B", "B", "B"};
		HashSet<String> distinct = new HashSet<String>();
		for (String string : arrayStrings) {
			distinct.add(string);
		}
		BinaryST binaryST = new BinaryST(arrayStrings);
		for (String string : binaryST.inOrder()) {
			System.out.print(string);
		}
		System.out.println("");
		for (String string : binaryST.preOrder()) {
			System.out.print(string);
		}
		System.out.println("");
		System.out.println("Height of tree : " + binaryST.height());
		System.out.println("rank : " + binaryST.rankOf("U"));
		System.out.println("Removing : " + binaryST.remove("A"));
		System.out.println("Removing : " + binaryST.remove("A"));
		for (String string : binaryST.preOrder()) {
			System.out.print(string);
		}
		System.out.println("");
		System.out.println("rank : " + binaryST.rankOf("U"));
	}

	public void testWarWithHash() {
		String[] warWithArray = {"ABC", "BCD", "CDE", "DEF", "EFG", "FGH", "GHI"};
		ArrayList<String> correctAns = new ArrayList<String>();
		correctAns.add("ABCDEF");
		correctAns.add("BCDEFG");
		correctAns.add("CDEFGH");
		correctAns.add("DEFGHI");
		long initTime = System.currentTimeMillis();
		WarWithHash warWithHash = new WarWithHash(warWithArray, warWithArray[0].length());
		warWithHash.debug = false;
		boolean answerCheck = true;
		ArrayList<String> list = warWithHash.compute2k();
		System.out.println("Time taken for hash : " + (System.currentTimeMillis() - initTime));
		System.out.println("Size of answer : " + list.size());
		if(correctAns.size() != list.size()) System.out.println("Wrong size answer");
		for (String string2k : list) {
			//System.out.println("Answer generated : " + string2k);
			
			if(!correctAns.contains(string2k)) {
				System.out.println("Wrong answer : " + string2k);
				answerCheck = false;
				//break;
			}
			
		}
		if(answerCheck) System.out.println("Correct answer");
		else System.out.println("wrong answer");
	}
	
	public void testWarWithArray() {
				//String[] warWithArray = {"ABC", "BCD", "CDE", "DEF", "EFG", "FGH", "GHI"};
				//String[] warWithArray = {"AAA","BBB"};
				String[] warWithArray = {"AD","AC", "CC", "BC", "CA"};
				ArrayList<String> correctAns = new ArrayList<String>();
				/*
				correctAns.add("ABCDEF");
				correctAns.add("BCDEFG");
				correctAns.add("CDEFGH");
				correctAns.add("DEFGHI");
				correctAns.add("DEFGHI");
				*/
				/*
				correctAns.add("AAAAAA");
				correctAns.add("BBBBBB");
				*/
				
				correctAns.add("ACAD");
				correctAns.add("ACAC");
				correctAns.add("CCAC");
				correctAns.add("CACA");
				correctAns.add("ACCA");
				correctAns.add("ACCC");
				correctAns.add("CACC");
				correctAns.add("CCAD");
				correctAns.add("CCCA");
				correctAns.add("CCCC");
				long initTime = System.currentTimeMillis();
				WarWithArray withArray = new WarWithArray(warWithArray, warWithArray[0].length());
				withArray.debug = false;
				boolean answerCheck = true;
				ArrayList<String> list = withArray.compute2k();
				System.out.println("Time taken for Array : " + (System.currentTimeMillis() - initTime));
				System.out.println("Size of answer : " + list.size() + " IT should be : " + correctAns.size());
				if(correctAns.size() != list.size()) System.out.println("Wrong size answer");
				for (String string2k : list) {
					System.out.println("Answer generated : " + string2k);
					
					if(!correctAns.contains(string2k)) {
						System.out.println("Wrong answer : " + string2k);
						answerCheck = false;
						//break;
					}
					
				}
				if(answerCheck) System.out.println("Correct answer");
				else System.out.println("wrong answer");
			}
	
		public String[] generateStrings(int len) {
			String[] ans = new String[len];
			String s = "ABCDEF";
			for (int i = 0; i <= s.length() - 3; i++) {
			    String substr = s.substring(i, i + 3);
			    if (substr.matches("[a-zA-Z]+")) { System.out.println(substr); }
			}
			return ans;
		}
		
	}