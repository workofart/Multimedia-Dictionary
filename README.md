# Multimedia-Dictionary
Multimedia Dictionary implements a dictionary that can read custom phrases (text, pictures, and sounds)



This application was developed for an data structure and algorithms course at Western. It imports Swing to display images and play founds.
<br/>

# Install

To install, simply pull the master branch to download the application. Then, use the command `javac Query.java` to compile the program.

# Run

After the program is compiled, use the command `java Query inputFile` to run. Here, the `inputFile` is formatted as follows:

1. First line contains the key word
2. Second line contains the definition of the first keyword defined in the first line
3. Third line contains another keyword with its definition in the fourth line.

**Note** - sound files and pictures are supported as the *definition*


**Example**
<br/>
	1. computer<br/>
	2. An electronic machine frequently used by Computer 	Science students.<br/>
	3. homework<br/>
	4. Very enjoyable work that students need to complete 	outside the classroom.<br/>
	5. roar<br/>
	6. roar.wav<br/>
	7. flower<br/>
	8. flower.jpg

# Commands
- `define keyword`
  - if definition is **text**, the text definition is displayed
  - if definition is an **audio** file, then that file is played
  - if definition is given by an **image**, the image is displayed on the screen
- `list prefix`
  - prints out all the keywords in the dictionary starting with the specified prefix, can be one or more letters
- `next keyword`
  - displays the **next** keyword of the specified keyword
- `previous keyword`
  - displays the **previous** keyword of the specified keyword
- `delete keyword`
	- deletes the specified keyword with the associated definition
- `end`
	- ends the session, terminates the program

# Documentation
The entire program is documented in Javadoc format embedded in the java files
