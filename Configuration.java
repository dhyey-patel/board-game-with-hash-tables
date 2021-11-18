/**
 * @author Dhyey Patel
 *
 *  Configuration is a class used to store the board configuration and it's integer score
 *  The constructor of the class will simply assign the configuration to a string variable and the int score to an int variable
 *  There are two methods that are used to get the configuration and the score
 */

public class Configuration {
	private String config;
	private int score;
	
	// Constructor of the class used to initilize the two variables in the class
	public Configuration(String config, int score) {
		this.config = config;
		this.score = score;
	}
	
	// Public method that is used to get the string configuration
	public String getStringConfiguration() {
		return config;
	}
	
	// Public method that is used to get the score of the string configuration 
	public int getScore() {
		return score;
	}
}
