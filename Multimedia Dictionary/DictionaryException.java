/**
 * The DictionaryException class will implement the exceptions that will be
 * thrown by the insert and remove methods of OrderedDictionary
 * 
 * @Name Hanxiang Pan
 * @StudentNumber 250608428
 * 
 */
public class DictionaryException extends Exception {

	/**
	 * The DictionaryException is used by the insert and remove methods of
	 * OrderedDictionary.
	 * 
	 * @param message - the message associated with this exception
	 */
	public DictionaryException(String message) {
		super(message);
	}
}
