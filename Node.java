/**
 * @author Dhyey Patel
 *
 *  Node is a class that is used to create a double linked list
 *  Node holds only data items of class Configuration
 */

public class Node {
	private Configuration dataItem;
	private Node next, previous;
	
	// The constructor of this class assigns the given Configuration to dataItem
	public Node(Configuration data) {
		dataItem = data;
		next = null;
		previous = null;
	}
	
	// This is a public method used to get the Configuration data stored in the class
	public Configuration getData() {
		return dataItem;
	}
	
	// This is a public method used to get the next Node linked to it 
	public Node getNext() {
		return next;
	}
	
	// This is a public method used to set the next Node linked to it 
	public void setNext(Node next) {
		this.next = next;
	}
	
	// This is a public method used to get the previous Node linked to it
	public Node getPrevious() {
		return previous;
	}
	
	// This is a public method used to set the previous Node linked to it
	public void setPrevious(Node previous) {
		this.previous = previous;
	}
}
