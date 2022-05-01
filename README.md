# Interactive Lock 🔒
  I was assigned a project in my CSE 271 (Object Oriented Programming) class where I was instructed to create a problem and solve it. I thought of a few options, but I eventually came up with an interactive lock to a door. The following is a bigger description of the program:

## Objective:
  This project aims to create an interactive lock that will let a user into a room if they have the correct credentials. This project is the admin side of the Lock. This is because you can add, remove, and import a list of people to allow or deny access. They are also entitled to access logs if they are on a specified list. 
**Table of Contents:**
- [Operation](https://github.com/lmcguckin2947/Interactive-Lock/blob/main/README.md#general-usage-and-operation)
- [UML](https://github.com/lmcguckin2947/Interactive-Lock/blob/main/README.md#uml)
- [Class By Class Description](https://github.com/lmcguckin2947/Interactive-Lock/blob/main/README.md#class-by-class-description)
  1. [Log](https://github.com/lmcguckin2947/Interactive-Lock/blob/main/README.md#log)
  2. [Access](https://github.com/lmcguckin2947/Interactive-Lock/blob/main/README.md#access)
  3. [LockUI](https://github.com/lmcguckin2947/Interactive-Lock/blob/main/README.md#lockui)
  4. [Person](https://github.com/lmcguckin2947/Interactive-Lock/blob/main/README.md#person)

## General Usage and Operation:
  The program starts with the downloading process. Before continuing, a user must download the LockUI.Java, Person.Java, Log.Java, Access.Java, access.txt, and the log.txt files. The LockUI class is the main point of contact between the user and the program. The Person class creates an object of a person who is on either the list or the log, is is used throughout the rest of the programs. The Access class works with the access.txt file and can add, remove, check and do more to the list. The Log class works with the log.txt file and can add, remove, check and do more to the list. The access.txt file holds the people who have access to unlock the Lock, and the log.txt file holds a log of those who have tried accessing the Lock.
  
  The next step is choosing from a menu. The menu includes: trying to unlock the door, changing access to a person on the access list, or accessing the log. 
  
  If the person were to choose to try to unlock the door, they are asked their name in the format first name last name with no spaces. It goes through the access.txt list one by one. Therefore, the time complexity is O(n). If the person does not have access to unlock the Lock, they are told they are denied access and spit back out into the primary menu. If they have access, they are told they are granted access and spit back out into the primary menu. Each time someone tries to access the Lock, their name and their granted access is recorded along with their name. 
  
  If a person chooses to modify the access list, the program will prompt them with another menu. Its options are to: add a person to the access.txt list, change the access of somebody from the list, view the list, and import an access list. The add Person option uses the create a new person using the createPerson method, and prompts the user for the required information. The change method reads and writes to a temporary file "temp.txt" line by line and skips over the person we are changing; then, using the add method, we add a person using the add method. The view prints the access.txt file ten lines at a time, and the person is asked if they would like to continue. The import option imports a list of people similar to the access.txt list and uses the add method to add line by line. Finally, the exit option takes the user back to the primary menu. 
  
  If a person were to choose to interact with the log.txt list, the user would prompt them with the same menu as the access modifier option. However, instead of being able to add a person to the log list directly from the console, for security reasons, this method adds people to the access array list that allows people to view the logs from the console. You must run this method first before you would like to run the view method. The change access method changes the person's availability to view the logs. The view method prints the records in chronological order at ten lines per iteration. The person is allowed to continue running the logs or not. To view the logs, you have to submit your name before accessing to see if you can view the logs. If you don't have access, you are spat back out into the modify log menu. The exit option brings you back to the primary menu. 
  
  To finish the program, you have to either end the program from the console or choose the exit option (option 4 in the primary menu).
  
## UML:

![UML pt1](https://github.com/lmcguckin2947/Interactive-Lock/blob/main/CSE%20271%20Homework%20%20%235.jpg)
![UML pt2](https://github.com/lmcguckin2947/Interactive-Lock/blob/main/CSE%20271%20Homework%20%20%235%20(1).jpg)

## Class By Class Description:
### Log
This file interacts with the log.txt file and houses all of the methods that are needed for the UI to run properly. A user will need to download the log.txt file in order to run this class.

**AccessList** - This Person array list holds the people who are able to read the log. This array is empty by design, so you will have to add people to the array in order to view it.

**LogFile** - This File variable houses the log.txt file that is needed to run this program. Therefore, a user will need to have a correctly formatted access file in order to run this program. It is formatted by name allowed.

**addPerson** - This method adds a person to the accessList. Its parameters are: person which is a person object that we are adding to the list.

**removePerson** - This method removes a person from the accessList. Its parameters are: person which is a Person object that that we are removing from the list.

**importLog** - This method adds the new file to the existing log.txt file. Its parameters are: filename which is a String variable that holds the filename of the list that we are importing.

**addLog** - A method that adds a line to the log. This is run after someone tries to access the door. Its parameters are: line which is a String variable that contains the line that goes into thelog.txt file.

**addLog** - This method overrides the addLog(String line) with the line broken up between the String name and the allowance boolean. Its parameters are: p which is a person object that holds the Person that we are adding, and allowed which is a boolean variable that holds the allowance of the person. This method can throw FileNotFoundException when the log.txt file cannot be found.

**canView** - This method searches through the accessList method to find a person. If they are found in the list, then they can view the log through the lock. Because its worse case scenario is that it searches through each element, its time complexity is O(n). Its parameters are: p which is a Person that we are trying to see can access the log. It returns A boolean that tells if the person can access the log.

**passFile** - This method passes the logFile onto the LockUI. Returns a File object that is passed onto the Lock UI file.

### Access
This file interacts with the acccess.txt file and houses all of the methods that are needed for the UI to run properly. A user will need to download the access.txt file in order to run this class.

**accessFile** - This File variable houses the access.txt file that is needed to run this program. Therefore, a user will need to have a correctly formatted access file in order to run this program. It is formatted by name allowed.

**passFile** - This method passes the accessFile onto the LockUI. Returns a File object that is passed onto the Lock UI file.

**addPerson** - This method works with option 21 in the Lock UI class to add a person to the access list. It creates a temporary file called "temp.txt" and changes its name to "access.txt". Its parameter variable is person which is a Person object that is the person that is to be added to the list. Returns a boolean variable that houses whether or not the person already exists in a file.

**removePerson** - This method works with option22 in the Lock UI class. The method writes to a temporary file ("temp.txt") with every line except those that match the person that we are removing. Then it overwrites the access file with the temporary file. Its parameter variable is person which is a Person object that we are removing from the access list.

**importList** - This method works with option24 in the LockUI class. This method imports a list and adds it to the access.txt file. Its parameter variable is filename which is a String object that holds the name of the file toimport.

**checkAccess** - This method searches through the list and checks to see if a person has the clearance to open the lock. Its parameter variable is p which is a Person object that we are checking to find their allowance. Returns a boolean variable that is true if they can enter and false if they cannot.

**changeAccess** - This method changes the access of a person by calling the remove method, the setter for changing access, and add person methods. Its parameter variables are p, which is a The person who's access we are changing, and block which is a boolean variable saying what type of access we want them to have.

**findPerson** - This method searches through the list to find a certain person. This method's time complexity is O(n) because it has to read each each element of the list if the person is at the end of the list. Its parameter variable is person which is a Person object that we are trying to find. It returns a boolean variable that says whether we have found the person or not. It can throw a FileNotFoundException, and throws the exception if the access file cannot be found.

### LockUI
The user interface for the Lock that integrates the Access Class, and the Log class. This contains the main method and is the core of the project. The user must download the access.txt file and the log.txt file in order for it to work.

**logFile** - A File object that holds the log file. This file contains the logs of whomever trys to access the lock. A user must download the file named "log.txt".

**accessFile** - A File object that holds the access file. This file contains the names
and allowance of the people who are allowed to unlock the lock. The user
must download the "access.txt" file.

**menu1** - This is a String object that holds the options for the first menu. This
is implemented in the menu method.

**menu2** - This is a String object that holds the options for the other menus. This
is implemented in the menu method.

**main** - This is the main method. This is where the first menu gets implemented. The parameter variable is args which is an Array of Strings that is necessary for the main method. It throws FileNotFoundException when the access file or the the log file.

**menu** - This method prints a menu for the person to interact with. This method is to be ran at the beginning of main, option12, and the option13 methods. Its parameter variable is menu which is a String variable that holds the display for the menu as defined in menu1 and menu2. Another parameter variable is max which is an int variable that holds the maximum number of options. Returns an int variable that holds the choice chosen.

**option11** - This method gets all the necessary information to finding out whether a person should be allowed access to the room. It throws FileNotFoundException if the access file or log file cannot be found.

**option12** - This method runs the options for interacting with the access.txt file. For example, a person can add a person to the file, change access to the file, etc. It throws FileNotFoundException if the program cannot find access.txt or log.txt

**option13** - This method runs the menu and the options for the log file through the log class. It throws FileNotFoundException if the program cannot find log.txt.

**option21** - This method adds a person to the access.txt file. It thows FileNotFoundException if the access.txt file cannot be found.

**option22** - This method changes somebody's credentials in the access.txt file.

**option23** - This method prints the access.txt file in increments of 10. It throws FileNotFoundException if the program cannot find the access.txt file.

**option24** - This method imports another access file and adds it to the access.txt file. It throws FileNotFoundException if the program cannot find the access.txt file or the new file.

**option31** - This method adds a person to the array of people who can view the logs. You must add yourself before viewing the logs.This throws FileNotFoundException when the program cannot find the log.txt.

**option32** - This method removes a person from the viewing array from the log class.

**option33** - This method reads and prints ten iterations at a time from the log.txt file. This throws FileNotFoundException if the program cannot find the log.txt file.

**option34** - This method imports a log file and adds it to the log.txt file. This throws FileNotFoundException if log.txt is not found.

**createAPerson** - This method prompts the user all the necessary information into creating a Person object. This method will make the name all caps. It returns a Person object that holds the new Person.

### Person
An object class for the person object. It is intended for the LockUI, Access, and Log classes.

**name** - A string global variable that holds the person's name.

**allowed** - A boolean global variable that holds the allowance.

**Person** - A constructor method that accepts a name and the allowance. Its parameter is name which is a String variable that holds the name of the Person. Another parameter is allowed which is a boolean variable that holds the allowance.

**Person** - A constructor method that accepts a name. Its parameter is name which is a String variable that holds the name of the Person.

**getName** - A getter method that gets the name of the Person. This method returns a String variable with the name of the person.

**isAllowed** - A getter method that gets the allowance of the Person. This method returns a boolean that holds the allowance of the Person.

**changeName** - A setter method for the access of the person. Its parameter is allowed which is a boolean variable that holds the allowance of the person.

**equals** - An equals method for the Person class that takes in a Person and compares them to this person. Its parameter is p which is a Person variable that is the one we are comparing to this class. This method returns a boolean that holds the similarity.

**toString** - A to string method that creates a String from a person class. This method returns a String value that is properly formatted and holds the name and the allowance.

**isNull** - An isNull method that tells the user if a Person is empty. This method returns a boolean value that tells the user if the Person is null.
