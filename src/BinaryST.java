import java.util.HashMap;
import java.util.HashSet;

// LEAVE THIS FILE IN THE DEFAULT PACKAGE
//  (i.e., DO NOT add 'package cs311.pa1;' or similar)

// DO NOT MODIFY THE EXISTING METHOD SIGNATURES
//  (you may, however, add additional methods and fields)

// DO NOT INCLUDE LIBRARIES OUTSIDE OF THE JAVA STANDARD LIBRARY
//  (i.e., you may include java.util.ArrayList etc. here, but not junit, apache commons, google guava, etc.)


public class BinaryST
{

	private String value;
	private int frequency;
	private BinaryST parent = this;
	private BinaryST leftChild;
	private BinaryST rightChild;
	private int size = 0;
	private int distinctSizeValue = 0;
	private int heightOfTree = 0;
	private int tempHeightTracker = 0;
	private int index = 0;
	private int leftSubtree = 0;
	private int rightSubtree = 0;
	private int rankTracker = 0;

	public BinaryST getRightChild() {
		return rightChild;
	}
	public void setRightChild(BinaryST rightChild) {
		this.rightChild = rightChild;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public int getFrequency() {
		return frequency;
	}
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}
	public BinaryST getParent() {
		return parent;
	}
	public void setParent(BinaryST parent) {
		this.parent = parent;
	}
	public BinaryST getChild() {
		return leftChild;
	}
	public void setChild(BinaryST child) {
		this.leftChild = child;
	}



	public BinaryST()
	{
		this.value = null;
		this.setFrequency(0);
		this.parent = null;
		this.leftChild = null;
	}

	public BinaryST(String[] s)
	{
		if(s != null || s.length != 0 ) {
			for (String binaryElemt : s) {
				parent.add(binaryElemt);
			}
		}
		updateLeftRightSubtree(parent);
	}

	public int distinctSize()
	{
		return distinctSizeValue;
	}

	public int size()
	{
		return size;
	}

	public int height()
	{
		return calculateHeightOfTree(this);
	}

	public void add(String binaryElemt)
	{
		BinaryST tempParent = parent.searchParent(binaryElemt);
		BinaryST childBST = new BinaryST();
		childBST.value = binaryElemt;
		childBST.frequency = 1;
		if(tempParent.value == null) { 
			tempParent.value = binaryElemt;
			tempParent.frequency = 1;
			tempParent.size = 1;
			tempParent.distinctSizeValue = 1;
			tempParent.heightOfTree = 1;
		}
		else {
			parent.size = parent.size + 1;
			switch (leftOrRight(tempParent.value, binaryElemt)) {
			case 0:
				tempParent.frequency = tempParent.frequency + 1;	
				break;
			case 1:		
				if(tempParent.leftChild != null) {
					if(leftOrRight(binaryElemt, tempParent.leftChild.value) > 0) {
						childBST.leftChild = tempParent.leftChild;
					}
					else {
						childBST.rightChild = tempParent.leftChild;
					}
				}
				tempParent.leftChild = childBST;
				parent.distinctSizeValue = parent.distinctSizeValue + 1;
				if(tempHeightTracker == heightOfTree) heightOfTree = heightOfTree + 1;;
				break;
			default:
				if(tempParent.rightChild != null) {
					if(leftOrRight(binaryElemt, tempParent.rightChild.value) > 0) {
						childBST.leftChild = tempParent.rightChild;
					}
					else {
						childBST.rightChild = tempParent.rightChild;
					}
				}
				tempParent.rightChild = childBST;
				parent.distinctSizeValue = parent.distinctSizeValue + 1;
				if(tempHeightTracker == heightOfTree) heightOfTree++;
				break;
			} 
		}
	}

	public boolean search(String s)
	{	
		return searchParent(s).value.compareTo(s) == 0;
	}

	public BinaryST searchParent(String s)
	{	
		BinaryST st = this;
		tempHeightTracker = 0;
		rankTracker = 0;
		while(!(st.leftChild == null && st.rightChild == null)) {
			tempHeightTracker++;
			switch (leftOrRight(s, st.value)) {
			case 0: return st;
			case 1: 
				if(st.rightChild != null) {
					switch (leftOrRight(s, st.rightChild.value)) {
					case 0:
						rankTracker = rankTracker + st.frequency + st.leftSubtree;
						System.out.println("Rank track : " + rankTracker);
						return st.rightChild;
					default:
						rankTracker = rankTracker + st.frequency + st.leftSubtree;
						System.out.println("Rank Default : " + rankTracker);
						st = st.rightChild;
					} 
				}
				else return st;
				break;
			default:
				if(st.leftChild != null) {
					switch (leftOrRight(s, st.leftChild.value)) {
					case 0:
						return st.leftChild;
					default:
						st = st.leftChild;
						break;
					}
				}
				else return st;
				break;
			}
		}
		return st;
	}
	
	public int frequency(String s)
	{
		BinaryST temp = searchParent(s);
		if(temp.value.compareTo(s) == 0) return temp.frequency;
		return 0;
	}

	public boolean remove(String s)
	{	
		BinaryST st = this;
		BinaryST parentNode = st;
		while(!(st.leftChild == null && st.rightChild == null)) {
			if(st.value != null) {
				switch (leftOrRight(s, st.value)) {
				case 0:
					if(st.frequency == 1) {
						rearrangeNode(parentNode, st.leftChild, st.rightChild);
						this.distinctSizeValue = this.distinctSizeValue - 1;
						this.size = this.size - 1;
					}
					else {
						reduceFrequency(s, st);
					}
					updateLeftRightSubtree(parent);updateLeftRightSubtree(parent);
					return true;
				case 1:
					parentNode = st;
					st = st.rightChild;
					break;
				default:
					parentNode = st;
					st = st.leftChild;
					break;
				}
			}
		}
		if(st.value.compareTo(s) == 0) {
			if(parentNode.leftChild != null) {
				if(st.frequency == 1) {  
					parentNode.leftChild = null; 
					this.distinctSizeValue = this.distinctSizeValue - 1;
					this.size = this.size - 1;
				}
				else {
					st.frequency = st.frequency - 1;
					this.size = this.size - 1;
				}
			}
			else {
				if(st.frequency == 1) {
					parentNode.rightChild = null;
					this.distinctSizeValue = this.distinctSizeValue - 1;
					this.size = this.size - 1;
				}
				else {
					st.frequency = st.frequency - 1;
					this.size = this.size - 1;
				}
			}
			updateLeftRightSubtree(parent);
			return true;
		}
		return false;
	}

	public String[] inOrder()
	{	
		this.index = 0;
		String[] inOrderArr = new String[size];
		returnInorder(this,inOrderArr);
		this.index = 0;
		return inOrderArr;
	}

	public void returnInorder(BinaryST st,String[] oderArray) {
		if(st == null) return;
		returnInorder(st.leftChild, oderArray);
		for (int i = 0; i < st.frequency; i++) {
			oderArray[index] = st.value;
			index = index + 1;
		}
		returnInorder(st.rightChild, oderArray);
	}

	public String[] preOrder()
	{	
		this.index = 0;
		String[] preorderArr = new String[size]; 
		returnPreorder(this, preorderArr);
		this.index = 0;
		return preorderArr;
	}

	public void returnPreorder(BinaryST st, String[] orderArray) {
		if(st == null) return;
		for (int i = 0; i < st.frequency; i++) {
			orderArray[index] = st.value;
			index = index + 1;
		}
		returnPreorder(st.leftChild, orderArray);
		returnPreorder(st.rightChild, orderArray);
	}

	public int rankOf(String s)
	{
		BinaryST st = searchParent(s);
		if(st.value.compareTo(s) == 0) return st.leftSubtree + rankTracker; 
		return st.leftSubtree;
	}

	public int leftOrRight(String s1, String s2) {
		int tempComp = s1.compareTo(s2);
		if(tempComp > 0) tempComp = 1;
		else if(tempComp < 0) tempComp = -1; 
		return tempComp;
	}

	public BinaryST leftMost(BinaryST tree) {
		if(tree.leftChild != null) return leftMost(tree.leftChild);
		else return tree;
	}

	public BinaryST rightMost(BinaryST tree) {
		if(tree.rightChild != null) return leftMost(tree.rightChild);
		else return tree;
	}

	public void rearrangeTree(BinaryST parent, BinaryST left) {

	}

	public void rearrangeNode(BinaryST parent, BinaryST left, BinaryST right) {
		if(left == null && right == null) parent = new BinaryST();
		if(right != null) {
			BinaryST leftMostBST = returnLeftMostByDeleting(right);
			parent.value = leftMostBST.value;
			parent.leftChild = left;
			parent.rightChild = right;
		}
		else {
			if(left != null) parent.rightChild = left;
		}
	}

	public BinaryST returnLeftMostByDeleting(BinaryST st) {
		BinaryST given = st;
		BinaryST parent = st;
		while(given.leftChild != null) {
			parent = given;
			given = given.leftChild;
		}
		parent.leftChild = null;
		System.out.println("Parent " + parent.value);
		return given;
	}
	
	public void reduceFrequency(String s, BinaryST st) {
		st.frequency = st.frequency - 1;
		this.size = this.size - 1;
	}
	
	
	public int calculateHeightOfTree(BinaryST st) {

        if (st == null)
            return 0;
        else
        {
            int leftDepth = calculateHeightOfTree(st.leftChild);
            int rightDepth = calculateHeightOfTree(st.rightChild);
            if (leftDepth > rightDepth)
                return (leftDepth + 1);
             else
                return (rightDepth + 1);
        }
	}
	
	public int binarySearch(String arr[], int low, int high, String key)
    {
       if (high < low)
           return -1;
       int mid = (low + high)/2;  
       if (key.compareTo(arr[mid]) == 0)
           return mid;
       if (key.compareTo(arr[mid]) > 0)
           return binarySearch(arr, (mid + 1), high, key);
       return binarySearch(arr, low, (mid -1), key);
    }
	
	public int updateLeftRightSubtree(BinaryST st) {
		if (st == null)
            return 0;
        else
        {	
            st.leftSubtree = updateLeftRightSubtree(st.leftChild);
            st.rightSubtree = updateLeftRightSubtree(st.rightChild);
            System.out.println("St value : " + st.value + " " + st.leftSubtree + " " + st.rightSubtree);
            return st.leftSubtree + st.frequency + st.rightSubtree;
        }
	}
}