/**
 * @author Dhyey Patel
 *
 *  Dictionary Exception is a very simple class that is used to throw a runtime exception
 *  This exception is thrown when configuration is in or not in the dictionary depending on the situation
 *  This class extends the RuntimeException and calls it in the constructor to throw the exception
 */
public class DictionaryException extends RuntimeException {
	public DictionaryException (String message)  {
		super (message);
	}
}
