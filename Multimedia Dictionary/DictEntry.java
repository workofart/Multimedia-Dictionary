/**
 * The DictEntry class represents a data item in the dictionary. Each data item
 * consists of a word, definition and a definition type
 * 
 * @Name Hanxiang Pan
 * @StudentNumber 250608428
 */
public class DictEntry {

	private String word; // the word of text string
	private String definition; // the definition of the corresponding word
	private int type; // the type of the word, it can be 1 (text), 2 (audio), 3
					  // (image)

	/**
	 * DictEntry constructor which returns a new DictEntry with the specified
	 * word, definition, and type. If type is 1, then text defining the word is
	 * stored in the definition string. If type is 2 or 3, then definition
	 * stores the name of the corresponding multimedia file.
	 * 
	 * @param word - key of every DictEntry
	 * @param definition - the definition of a specific word
	 * @param type - 1, 2 or 3 representing text, audio file or an image file
	 */
	public DictEntry(String word, String definition, int type) {
		this.word = word;
		this.definition = definition;
		this.type = type;
	} // end DictEntry constructor

	/**
	 * word method will return the word contained in the DictEntry
	 * 
	 * @return the word in the DictEntry
	 */
	public String word() {
		return word;
	} // end word method

	/**
	 * definition method will return the definition of a given data item
	 * 
	 * @return the definition in the DictEntry
	 */
	public String definition() {
		return definition;
	} // end definition method

	/**
	 * type method will return the type of a data item
	 * 
	 * @return the type in the DictEntry
	 */
	public int type() {
		return type;
	} // end type method

} // end DictEntry class
