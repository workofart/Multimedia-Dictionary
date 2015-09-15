import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringTokenizer;

/**
 * The Query class will have a main method to run the multimedia dictionary.
 * To run this program: java Query inputFile
 * 
 * Commands in the dictionary:
 * define "word" - displays definition associated with the word
 * delete "word" - removes the word from the dictionary
 * add "word" "definition" - adds the word with the associated definition into the dictionary
 * list "prefix" - lists all the words that starts with the "prefix"
 * next "word" - prints the word from the dictionary that alphabetically follows this "word"
 * previous "word" - prints the word from the dictionary that alphabetically precedes this "word"
 * end - terminate program
 * 
 * @Name Hanxiang Pan
 * @StudentNumber 250608428
 *
 */
public class Query { 

	/**
	 * This method is the main driver method for testing the multimedia dictionary.
	 * To run this program, enter: java Query inputFile
	 * 
	 */
	public static void main(String[] args) {
		StringReader keyboard = new StringReader();
		String inputFile = args[0];

		// Insert the words and definition from the input file
		OrderedDictionary multimediaDict = new OrderedDictionary();
		try {
			BufferedReader in = new BufferedReader(new FileReader(inputFile));
			String word = in.readLine();
			String definition;
			// while the file has not reached the end, add words and definitions
			while (word != null) {
				definition = in.readLine();
				
				// check if the word is a multimedia type or not
				if (definition.contains(".wav") || definition.contains(".mid")) {
					multimediaDict.insert(word.toLowerCase(), definition, 2);
				} else if (definition.contains(".jpg") || definition.contains(".gif")) {
					multimediaDict.insert(word.toLowerCase(), definition, 3);
					
					// else the word is a normal text word
				} else
					multimediaDict.insert(word.toLowerCase(), definition, 1);
				word = in.readLine();
				
			} // end while loop
		} catch (Exception e) {
			e.printStackTrace();
		}

		String line = keyboard.read("Please enter a command: \n");
		StringTokenizer test;

		// loop until the user enters "end"
		while (!line.equalsIgnoreCase("end")) {
			test = new StringTokenizer(line);

			// user entered two tokens
			if (test.countTokens() == 2) {

				// store two tokens in strings variables for later use
				String firstToken = test.nextToken();
				String secondToken = test.nextToken();

				// if the command is to define the word
				if (firstToken.equalsIgnoreCase("define")) {

					// if the type of the word is a sound file, play the sound
					if ((multimediaDict.findType(secondToken)) == 2) {
						try {
							SoundPlayer player = new SoundPlayer();
							player.play(multimediaDict.findWord(secondToken));
						} catch (MultimediaException e) {
							System.out.println("The file isn't found or processed.");
						}
					} // end if word is a sound file

					// if the word type is a picture file, display the picture
					else if ((multimediaDict.findType(secondToken)) == 3) {
						try {
							PictureViewer viewer = new PictureViewer();
							viewer.show(multimediaDict.findWord(secondToken));
						} catch (MultimediaException e) {
							System.out.println("The file isn't found or processed.");
						}
					} // end if word is a picture file

					// else the word type is a normal word with text definition
					else if ((multimediaDict.findType(secondToken) == 1)) {
						System.out.println("Definition: " + multimediaDict.findWord(secondToken));
					}
					// else the word is not in the dictionary
					else 
						System.out.println("The word isn't in the dictionary. Please try again.");
				} // end case of defining the word

				// if the command is to delete the word
				else if (firstToken.equalsIgnoreCase("delete")) {
					try {
						multimediaDict.remove(secondToken);
					} catch (DictionaryException e) {
						System.out.println("The word is not in the dictionary.");
					}
				} // end case of deleting the word

				// if the command is to find the next word
				else if (firstToken.equalsIgnoreCase("next")) {
					if (multimediaDict.successor(secondToken).equalsIgnoreCase(""))
						System.out.println(secondToken + " is the last word in the dictionary.");
					else
						System.out.println("The next word is: " + multimediaDict.successor(secondToken));
				} // end case of finding the next word

				// if the command is to find the previous word
				else if (firstToken.equalsIgnoreCase("previous")) {
					if (multimediaDict.predecessor(secondToken).equalsIgnoreCase(""))
						System.out.println(secondToken + " is the first word in the dictionary.");
					else
						System.out.println("The previous word is: " + multimediaDict.predecessor(secondToken));
				} // end case of finding the previous word

				// if the command is to list the words with the given prefix
				else if (firstToken.equalsIgnoreCase("list")) {
					// set two pointers one at the beginning and one at the end of the BST
					String pointerStart = "aaaaaa";
					String pointerEnd = "zzzzzz";
					try {
						multimediaDict.insert(pointerStart, pointerStart, 1);
						multimediaDict.insert(pointerEnd, pointerEnd, 1);
					} catch (DictionaryException e) {
						System.out.println(pointerStart + " or " + pointerEnd + "already exists in the dictionary");
					}
					// while the starting pointer hasn't reached the ending pointer 
					// traverse down the BST using the successor method
					while (!pointerStart.equalsIgnoreCase(pointerEnd)) {
						pointerStart = multimediaDict.successor(pointerStart);

						// if the pointerStart contains the prefix, output the word
						if (pointerStart.startsWith(secondToken)) {
							System.out.println(pointerStart);
						}

						// if the pointerStart reaches the pointerEnd, which means reached the end of the BST
						if (pointerStart.equalsIgnoreCase(pointerEnd)){

							// remove the two pointers to avoid making changes
							// to the original dictionary, and end command
							try {
								multimediaDict.remove("aaaaaa");
								multimediaDict.remove("zzzzzz");
								break;
							} catch (DictionaryException e) {
								System.out.println("aaaaaa or zzzzzz doesn't exist in the dictionary." );
							}	

						} // end if statement

					} // end while loop

				} // end case of listing words with the given prefix

				// else, the user entered an invalid command
				else
					System.out.println("You have entered an"
							+ " invalid command.");
				// prompt the user to enter another command
				line = keyboard.read("Enter next command: ");
				continue;
			} // end case of user entering two tokens

			// case of user entering three or more tokens
			else if (test.countTokens() >= 3) {
				String firstToken = test.nextToken();
				String secondToken = test.nextToken();
				String moreTokens = ""; // set to empty for now

				// while there are more definitions
				while (test.hasMoreTokens()) {
					// append the definition tokens to the third token
					moreTokens += test.nextToken() + " ";
				}

				// if the command is to add a new word with definition
				if (firstToken.equalsIgnoreCase("add")) {
					try {
						// check if there are three tokens entered by the user
						multimediaDict.insert(secondToken, moreTokens, 1);
					} catch (DictionaryException e) {
						System.out.println(secondToken + " is already in the dictionary.");
					}
				} // end case of adding a new word with definition

				// else, the user entered an invalid three-token command, alert
				else {
					System.out.println("You have entered an invalid command.");
				}

				// prompt the user to enter another command
				line = keyboard.read("Enter next command: ");
				continue;
			} // end case of three tokens

			// else, the user entered an invalid three-token command, alert
			else {
				System.out.println("You have entered an invalid command.");

				// prompt the user to enter another command
				line = keyboard.read("Enter next command: ");
				continue;
			} // end else

		} // end while loop (prompting user to input)

	} // end main method

} // end Query class
