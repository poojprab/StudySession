# StudySession
A program that creates a study guide or a study session given a list of questions+answers and command line arguments.
### Created in CS3500 at Northeastern University

# How to play: 
- The user can ask the program to either:
  - generate a study guide by giving the specified path to a markdown file to be read into question+answer format in the command line arguments
  - start a study session by providing no command line arguments
      - By doing so, the user will be able to enter a terminal program in which questions are presented to the user and the correct answer will be given upon request
      - The user will first be asked to give a path to a study guide file that must have already been created, if invalid, will display appropriate message
   
# Skills Acquired: 
- Made use of the file-walker built in functions to create the study guide
- Made use of rejex and split commands to read through the files
- Implemented the use of SOLID principles:
    - S: Every class is used for one purpose only
    - O: The Readable/Writable Interfaces can be extended by other sublcasses but not modified
    - L and I: All subclasses make use of superclass specified methods and none change the semantic meaning of the method
    - D: No classes depend on another class for functionality
