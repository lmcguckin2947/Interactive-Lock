import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * This file interacts with the log.txt file and houses all of the methods that
 * are needed for the UI to run properly. A user will need to download the
 * log.txt file in order to run this class.
 * 
 * @author Liam
 *
 */
public class Log {

    /**
     * This Person array list holds the people who are able to read the log.
     * This array is empty by design, so you will have to add people to the
     * array in order to view it.
     */
    private static Person[] accessList = new Person[0];

    /**
     * This File variable houses the log.txt file that is needed to run this
     * program. Therefore, a user will need to have a correctly formatted access
     * file in order to run this program. It is formatted by name allowed.
     */
    private static File logFile = new File("log.txt");

    /**
     * This method adds a person to the accessList.
     * 
     * @param person A person object that we are adding to the list.
     */
    protected static void addPerson(Person person) {
        Person[] temp = new Person[accessList.length + 1];
        for (int i = 0; i < accessList.length; i++) {
            temp[i] = accessList[i];
        }
        temp[accessList.length] = person;
        accessList = temp;
    }

    /**
     * This method removes a person from the accessList.
     * 
     * @param person A Person object that that we are removing.
     */
    protected static void removePerson(Person person) {
        Person[] temp = new Person[accessList.length - 1];
        for (int i = 0; i < accessList.length; i++) {
            if (!accessList[i].equals(person)) {
                temp[i] = accessList[i];
            }
        }
        accessList = temp;
    }

    /**
     * This method adds the new file to the existing log.txt file.
     * 
     * @param filename A String variable that holds the filename of the list
     *                 that we are importing.
     */
    protected static void importLog(String filename) {
        try {
            Scanner im = new Scanner(new File(filename));
            while (im.hasNext()) {
                addLog(im.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Cannot find file. Try Again.");
        }
    }

    /**
     * A method that adds a line to the log. This is run after someone tries to
     * access the door.
     * 
     * @param line A String variable that contains the line that goes into the
     *             log.txt file.
     */
    static void addLog(String line) {
        try {
            File temp = new File("temp.txt");
            PrintWriter pw = new PrintWriter(temp);
            Scanner sc = new Scanner(logFile);
            // prints the existing file to the temp file.
            while (sc.hasNext()) {
                pw.println(sc.next() + " " + sc.next());
            }
            // then it writes the line to the file
            pw.println(line);
            pw.close();
            // then it overwrites the log.txt file with the temp file.
            temp.renameTo(logFile);
        } catch (FileNotFoundException e) {
            System.out.println("Please create an access file!");
        }
    }

    /**
     * This method overrides the addLog(String line) with the line broken up
     * between the String name and the allowance boolean.
     * 
     * @param p       A person object that holds the Person that we are adding
     * @param allowed A boolean variable that holds the allowance of the person.
     * @throws FileNotFoundException The program throws this exception when the
     *                               log.txt file cannot be found.
     */
    public static void addLog(Person p, boolean allowed)
            throws FileNotFoundException {
        try {
            File temp = new File("temp.txt");
            PrintWriter pw = new PrintWriter(temp);
            Scanner sc = new Scanner(logFile);
            while (sc.hasNext()) {
                pw.println(sc.next() + " " + sc.next());
            }
            pw.printf("%s %b", p.getName(), allowed);
            pw.close();
            temp.renameTo(logFile);
        } catch (FileNotFoundException e) {
            System.out.println("Please create an access file!");
        }
    }

    /**
     * This method searches through the accessList method to find a person. If
     * they are found in the list, then they can view the log through the lock.
     * Because its worse case scenario is that it searches through each element,
     * its time complexity is O(n).
     * 
     * @param p A Person class that we are trying to see can access the log.
     * @return A boolean that tells if the person can access the log.
     */
    protected static boolean canView(Person p) {
        boolean match = false;
        for (int i = 0; i < accessList.length; i++) {
            if (accessList[i].equals(p)) {
                match = true;
            }
        }
        return match;
    }

    /**
     * This method passes the logFile onto the LockUI.
     * 
     * @return Returns a File object that is passed onto the Lock UI file.
     */
    protected static File passFile() {
        return logFile;
    }
}
