/**
 * @author Dhyey Patel
 *
 *HashDictionary is a class used to store the configurations of the game in a hast table
 *This class implements the DictionaryADT 
 */
import java.lang.Math;
public class HashDictionary implements DictionaryADT {
	private Node[] table;
	private int size;
	
	// The constructor of the class is used to create an array which represents the hash table
	public HashDictionary (int size) {
		this.size = size;
		table = new Node[size];
		for (int i=0;i<size;i++) {
			table[i]=null;
		}
	}

	// This method is a public method used to put an item into the hash table
	public int put(Configuration data) throws DictionaryException {
		// First use the hash function to create a position in the table
		int position = createPosition(data.getStringConfiguration());
		Node node = new Node(data), nextNode, previousNode;
		// If there is no collision create a node with the configuration and set that position in the table to the node
		if(table[position] == null) {
			table[position] = node;
			return 0;
		}
		// If there is a collision, check if the data is already in table and if not add it
		else {
			nextNode = table[position];
			// If the data is already in the table then throw the DictionaryExemption 
			if (nextNode.getData().getStringConfiguration().equals(data.getStringConfiguration())) {
				throw new DictionaryException("Configuration is already in the Dictionary");
			}
			// Go to the end of the linked list, while checking if the data is already there
			while (nextNode.getNext() != null) {
				nextNode = nextNode.getNext();
				if (nextNode.getData().getStringConfiguration().equals(data.getStringConfiguration())) {
					throw new DictionaryException("Configuration is already in the Dictionary");
				}
			}
			// add the node with the given data to the position
			nextNode.setNext(node);
			previousNode = nextNode;
			nextNode = previousNode.getNext();
			nextNode.setPrevious(previousNode);
			return 1;
		}	
	}

	// This method is a public method used to remove the given string configuration
	public void remove(String config) throws DictionaryException {		
		// Calculate the position that the string configuration would be located using the hash function 
		int position = createPosition(config);
		Node node;
		boolean check = true;
		// If there are no nodes at the position then throw Dictionary exception
		if (table[position] == null) {
			throw new DictionaryException("Configuration is not in the Dictionary");
		}
		else {
			node = table[position];
			if (node == null) {
				throw new DictionaryException("Configuration is not in the Dictionary");
			}
			else if (node.getNext() == null) {
				table[position] = null;
			}
			else {
				if (node.getData().getStringConfiguration().equals(config)) {
					check = false;
				}
				// Check all the nodes in the linked list to see which node the string is located in 
				// If the node is not found, then throw DictionaryExeption 
				while (check) {
					node = node.getNext();
					if (node == null) {
						throw new DictionaryException("Configuration is not in the Dictionary");
					}
					if (node.getData().getStringConfiguration().equals(config)) {
						check = false;
					}
				}
				// When the node is found figure out where in the linked list it is (beginning, middle, end)
				// After that remove the node with the appropriate commands
				if (node.getPrevious()==null) {
					table[position] = node.getNext();
					node = node.getNext();
					node.setPrevious(null);
				}
				else if (node.getNext() == null) {
					node = node.getPrevious();
					node.setNext(null);
				}
				else {
					node = node.getPrevious();
					node.setNext(node.getNext().getNext());
				}
			}
		}
	}

	// This is a public method to get the score associated with the given configuration 
	public int getScore(String config) {
		// Calculate the position that the string configuration would be located using the hash function 
		int position = createPosition(config);
		boolean check = true;
		Node node = table[position];
		// If there are no nodes in the position then return -1
		if (node == null) {
			return -1;
		}
		// If there is only one node return the score in that node
		else if (node.getNext() == null) {
			return (node.getData().getScore());
		}
		// If there are multiple nodes find the node with the string configuration and return the score
		else {
			if (node.getData().getStringConfiguration().equals(config)) {
				check = false;
			}
			while (check) {
				node = node.getNext();
				if (node == null) {
					return -1;
				}
				if (node.getData().getStringConfiguration().equals(config)) {
					check = false;
				}
			}
			return (node.getData().getScore());
		}
	}
	
	// This is a private method that is used to create the position in the hash table using a hash function 
	private int createPosition (String s) {
		int x = 37;
		double hashCode=0;
		for (int i=0; i<s.length(); i++) {
			hashCode = (double) (hashCode+ (int)(s.charAt(s.length()-(i+1)))*(Math.pow(x,i)));
		}
		return (int)(hashCode % size);
	}
}
