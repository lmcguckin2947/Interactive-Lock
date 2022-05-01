import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * This file interacts with the acccess.txt file and houses all of the methods
 * that are needed for the UI to run properly. A user will need to download the
 * access.txt file in order to run this class.
 * 
 * @author Liam
 *
 */
public class Access {

    /**
     * This File variable houses the access.txt file that is needed to run this
     * program. Therefore, a user will need to have a correctly formatted access
     * file in order to run this program. It is formatted by name allowed.
     */
    private static File accessFile = new File("access.txt");

    /**
     * This method passes the accessFile onto the LockUI.
     * 
     * @return Returns a File object that is passed onto the Lock UI file.
     */
    protected static File passFile() {
        return accessFile;
    }

    /**
     * This method works with option 21 in the Lock UI class to add a person to
     * the access list. It creates a temporary file called "temp.txt" and
     * changes its name to "access.txt".
     * 
     * @param person A Person object that is the person that is to be added to
     *               the list.
     * @return A boolean variable that houses whether or not the person already
     *         exists in a file.
     */
    public static boolean addPerson(Person person) {
        // tells if the person exits in the file
        boolean exists = false;
        try {
            File temp = new File("temp.txt");
            PrintWriter pw = new PrintWriter(temp);
            Scanner sc = new Scanner(accessFile);

            // writing the existing file to the temporary file
            while (sc.hasNext()) {
                pw.println(sc.next() + " " + sc.next());
            }

            // tells if the person already exists or not
            if (!findPerson(person)) {
                pw.println(person.toString());
            } else {
                System.out.println(
                        "This person already exists in the access file");
                exists = false;
            }
            pw.close();
            temp.renameTo(accessFile);
        } catch (FileNotFoundException e) {
            System.out.println("Please create an access file!");
        }
        return exists;
    }

    /**
     * This method works with option22 in the Lock UI class. The method writes
     * to a temporary file ("temp.txt") with every line except those that match
     * the person that we are removing. Then it overwrites the access file with
     * the temporary file.
     * 
     * @param person A Person object that we are removing from the access list.
     */
    public static void removePerson(Person person) {
        try {
            // creating the new file
            File tempFile = new File("tempAccess");
            PrintWriter temp = new PrintWriter(tempFile);
            Scanner sc = new Scanner(accessFile);
            // writing to the new file
            while (sc.hasNextLine()) {
                String name = sc.next();
                // checking to see if the line in the code is equal to the
                // person
                if (!name.equals(person.getName())) {
                    temp.println(name + sc.nextLine());
                } else {
                    sc.nextLine();
                }
            }
            temp.println(person);
            tempFile.renameTo(accessFile);
            temp.close();
        } catch (FileNotFoundException e) {
            System.out.println("Please create an access file.");
        }

    }

    /**
     * This method works with option24 in the LockUI class. This method imports
     * a list and adds it to the access.txt file.
     * 
     * @param filename A String object that holds the name of the file to
     *                 import.
     */
    protected static void importList(String filename) {
        try {
            Scanner sc = new Scanner(new File(filename));
            while (sc.hasNext()) {
                addPerson(new Person(sc.next(), sc.nextBoolean()));
            }
        } catch (FileNotFoundException e) {
            System.out.println(
                    "Either there was an error with the filename or the access file. Please check both.");
        }

    }

    /**
     * This method searches through the list and checks to see if a person has
     * the clearance to open the lock.
     * 
     * @param p A Person object that we are checking to find their allowance.
     * @return A boolean variable that is true if they can enter and false if
     *         they cannot.
     */
    public static boolean checkAccess(Person p) {
        try {
            Scanner sc = new Scanner(accessFile);
            while (sc.hasNext()) {
                if (sc.next().equals(p.getName())) {
                    if (sc.next().equals("true")) {
                        return true;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Please create an access file!");
        }
        return false;
    }

    /**
     * This method changes the access of a person by calling the remove method,
     * the setter for changing access, and add person methods.
     * 
     * @param p     A Person object of the person who's access we are changing.
     * @param block A boolean variable saying what type of access we want them
     *              to have.
     */
    protected void changeAccess(Person p, boolean block) {
        removePerson(p);
        p.changeAccess(block);
        addPerson(p);
    }

    /**
     * This method searches through the list to find a certain person. This
     * method's time complexity is O(n) because it has to read each each element
     * of the list if the person is at the end of the list.
     * 
     * @param person A Person object that we are trying to find.
     * @return A boolean variable that says whether we have found the person or
     *         not.
     * @throws FileNotFoundException The program throws an exception if the
     *                               access file cannot be found.
     */
    public static boolean findPerson(Person person)
            throws FileNotFoundException {
        Scanner sc = new Scanner(accessFile);
        if (sc.hasNext()) {
            String data = sc.next();
            if (person.equals(new Person(data))) {
                return true;
            }
        }
        return false;
    }

}
