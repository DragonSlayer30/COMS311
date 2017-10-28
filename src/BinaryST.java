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
				System.out.println("Adding : " + binaryElemt);
				parent.add(binaryElemt);
				/*
				BinaryST tempParent = parent.searchParent(binaryElemt);
				BinaryST childBST = new BinaryST();
				childBST.value = binaryElemt;
				if(tempParent.value == null) { 
					tempParent.value = binaryElemt; 
					continue; 
				}
				if(binaryElemt.compareTo(tempParent.value) < 0) tempParent.leftChild = childBST;
				else tempParent.rightChild = childBST;
				 */
			}
		}
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
		return heightOfTree;
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
				System.out.println("adding element as left child of " + tempParent.value);
				if(tempParent.leftChild != null) {
					if(leftOrRight(binaryElemt, tempParent.leftChild.value) > 0) {
						System.out.println("moving left child " + tempParent.leftChild.value + " as leftchild");
						childBST.leftChild = tempParent.leftChild;
					}
					else {
						System.out.println("moving left child " + tempParent.leftChild.value + " as rightchild");
						childBST.rightChild = tempParent.leftChild;
					}
				}
				tempParent.leftChild = childBST;
				parent.distinctSizeValue = parent.distinctSizeValue + 1;
				if(tempHeightTracker == heightOfTree) heightOfTree++;
				break;
			default:
				System.out.println("adding element as right child of " + tempParent.value);
				if(tempParent.rightChild != null) {
					System.out.println("moving right child " + tempParent.rightChild.value);
					if(leftOrRight(binaryElemt, tempParent.rightChild.value) > 0) {
						System.out.println("moving left child " + tempParent.rightChild.value + " as leftchild");
						childBST.leftChild = tempParent.rightChild;
					}
					else {
						System.out.println("moving right child " + tempParent.rightChild.value + " as rightchild");
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
		while(!(st.leftChild == null && st.rightChild == null)) {
			tempHeightTracker++;
			switch (leftOrRight(s, st.value)) {
			case 0: return st;
			case 1: 
				if(st.rightChild != null) {
					switch (leftOrRight(s, st.rightChild.value)) {
					case 0:
						return st.rightChild;
					default:
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
		System.out.println("Height Counter : " + tempHeightTracker);
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
					System.out.println("Frequency of " + s + " " + st.frequency);
					if(st.frequency == 1) {
						rearrangeNode(parentNode, parentNode.leftChild, parentNode.rightChild);
						System.out.println("Dist prev : " + this.distinctSizeValue);
						this.distinctSizeValue = this.distinctSizeValue - 1;
						System.out.println("Dist after : " + this.distinctSizeValue);
						this.size = this.size - 1;
					}
					else {
						reduceFrequency(s, st);
					}
					//this.size = this.size - 1;
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
			return true;
		}
		return false;
	}

	public String[] inOrder()
	{	
		printInorder(this);
		return null;
	}

	public void printInorder(BinaryST st) {
		if(st == null) return;
		printInorder(st.leftChild);
		for (int i = 0; i < st.frequency; i++) {
			System.out.print(st.value + ",");
		}
		printInorder(st.rightChild);
	}

	public String[] preOrder()
	{	
		printPreorder(this);
		System.out.println("");
		return null;
	}

	public void printPreorder(BinaryST st) {
		if(st == null) return;
		for (int i = 0; i < st.frequency; i++) {
			System.out.print(st.value + ",");
		}
		printPreorder(st.leftChild);
		printPreorder(st.rightChild);
	}

	public int rankOf(String s)
	{
		return 0;
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
			System.out.println("Left Most : " + leftMostBST.value);
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
}