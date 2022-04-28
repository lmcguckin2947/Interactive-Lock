import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.File;

/**
 * The User Interface for the Lock that integrates the Access Class, and the Log
 * class. This contains the main method and is the core of the project. The User
 * must download the access.txt file and the log.txt file in order for it to
 * work.
 * 
 * @author Liam
 *
 */
public class LockUI {

    /**
     * A File object that holds the log file. This file contains the logs of
     * whomever trys to access the lock. A user must download the file named
     * "log.txt".
     */
    private static File logFile;

    /**
     * A File object that holds the access file. This file contains the names
     * and allowance of the people who are allowed to unlock the lock. The user
     * must download the "access.txt" file.
     */
    private static File accessFile;

    /**
     * This is a String object that holds the options for the first menu. This
     * is implemented in the menu method.
     */
    private static String menu1 = "\nWelcome!\n"
            + " 1) Open the door\n"
            + " 2) View or change access\n"
            + " 3) View or change logs\n"
            + " 4) Exit\n"
            + "Please select an option (1-4): ";

    /**
     * This is a String object that holds the options for the other menus. This
     * is implemented in the menu method.
     */
    private static String menu2 = "\n 1) Add person to list\n"
            + " 2) Change Access In List\n"
            + " 3) View list\n"
            + " 4) Import list\n"
            + " 5) Exit\n"
            + "Please select an option (1-5): ";

    /**
     * This is the main method. This is where the first menu gets implemented.
     * 
     * @param args An Array of Strings that is necessary for the main method.
     * @throws FileNotFoundException This method is thrown when the access file
     *                               or the the log file.
     */
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        boolean exit = true;
        System.out.print(
                "Imagine that you have encountered a door with an interactive lock.");
        // this initializes the access and log files.
        accessFile = Access.passFile();
        logFile = Log.passFile();
        do {
            // this runs the method and runs the corresponding method with.
            int ans = menu(menu1, 4);
            if (ans == 1) {
                option11();
            } else if (ans == 2) {
                option12();
            } else if (ans == 3) {
                option13();
            } else if (ans == 4) {
                exit = false;
            }
        } while (exit);
        System.out.println("Goodbye.");
        sc.close();
    }

    /**
     * This method prints a menu for the person to interact with. This method is
     * to be ran at the beginning of main, option12, and the option13 methods.
     * 
     * @param menu A String variable that holds the display for the menu as
     *             defined in menu1 and menu2.
     * @param max  An int variable that holds the maximum number of options.
     * @return An int variable that holds the choice chosen.
     */
    private static int menu(String menu, int max) {
        Scanner in = new Scanner(System.in);
        boolean correctInt = true;
        int ans = 0;
        do {
            try {
                System.out.printf(menu);
                ans = in.nextInt();
                if (ans > max || ans < 1) {
                    throw new InputMismatchException();
                }
                correctInt = false;
            } catch (InputMismatchException ex) {
                System.out
                        .println("Incorrect Input! Input an integer 1 through "
                                + max + ".");
            }
        } while (correctInt);
        return ans;
    }

    /**
     * This method gets all the necessary information to finding out whether a
     * person should be allowed access to the room.
     * 
     * @throws FileNotFoundException This is thrown if the access file or log
     *                               file cannot be found.
     */
    private static void option11() throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        // Asking for name and making it uppercase
        System.out.println("Name (FirstLast): ");
        String name = sc.next().toUpperCase();
        // creating a person out of the name
        Person p = new Person(name);
        // checks access and then adds it to the log
        if (Access.checkAccess(p)) {
            System.out.println("Welcome, " + name + " stay a while :)");
            Log.addLog(p, true);
        } else {
            System.out.println("ACCESS DENIED! Try again.");
            Log.addLog(p, false);
        }
    }

    /**
     * This method runs the options for interacting with the access.txt file.
     * For example, a person can add a person to the file, change access to the
     * file, etc.
     * 
     * @throws FileNotFoundException This is thrown if the program cannot find
     *                               access.txt or log.txt
     */
    private static void option12() throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        int ans;
        boolean exit = true;
        do {
            // This runs the second menu and runs the different options.
            ans = menu(menu2, 5);
            if (ans == 1) {
                option21();
            } else if (ans == 2) {
                option22();
            } else if (ans == 3) {
                option23();
            } else if (ans == 4) {
                option24();
            } else if (ans == 5) {
                exit = false;
            }
        } while (exit);
    }

    /**
     * This method adds a person to the access.txt file.
     * 
     * @throws FileNotFoundException This is thrown if the access.txt file
     *                               cannot be found.
     */
    private static void option21() throws FileNotFoundException {
        boolean exists = Access.addPerson(createAPerson());
        if (exists) {
            System.out.println("Person Added Successfully");
        }
    }

    /**
     * This method changes somebody's credentials in the access.txt file.
     */
    private static void option22() {
        Scanner sc = new Scanner(System.in);
        Person p = createAPerson();
        Access.removePerson(p);
        System.out.println("Access Changed Successfully");
    }

    /**
     * This method prints the access.txt file in increments of 10.
     * 
     * @throws FileNotFoundException This is thrown if the program cannot find
     *                               the access.txt file.
     */
    private static void option23() throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        boolean cont = true;
        Scanner file = new Scanner(accessFile);
        int i = 1;
        int max = 10;
        do {
            // reads the file line by line
            while (file.hasNextLine() && i <= max) {
                System.out.println(i++ + ") " + file.nextLine());
            }
            max += 10;
            String ans;
            // asks the person if they would like to continue for another 10
            // iterations
            if (file.hasNextLine()) {
                do {
                    System.out.println("Would you like to continue (y/n)");
                    ans = sc.next().toLowerCase();
                } while (!ans.equals("y") && !ans.equals("n"));
                cont = ans.equals("y");
            } else {
                cont = false;
            }
        } while (cont);
    }

    /**
     * This method imports another access file and adds it to the access.txt
     * file.
     * 
     * @throws FileNotFoundException This is thrown if the program cannot find
     *                               the access.txt file or the new file.
     */
    private static void option24() throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        System.out.println(
                "What is the name of the file you would like to import?\nEnter it here:");
        Access.importList(sc.next());
    }

    /**
     * This method runs the menu and the options for the log file through the
     * log log class.
     * 
     * @throws FileNotFoundException if the program cannot find log.txt.
     */
    private static void option13() throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        int ans;
        boolean exit = true;
        do {
            // This utilizes the second menu
            ans = menu(menu2, 5);
            if (ans == 1) {
                option31();
            } else if (ans == 2) {
                option32();
            } else if (ans == 3) {
                option33();
            } else if (ans == 4) {
                option34();
            } else if (ans == 5) {
                exit = false;
            }
        } while (exit);
    }

    /**
     * This method adds a person to the array of people who can view the logs.
     * You must add yourself before viewing the logs.
     * 
     * @throws FileNotFoundException This is thrown when the program cannot find
     *                               the log.txt.
     */
    private static void option31() throws FileNotFoundException {
        Log.addPerson(createAPerson());
        System.out.println("Person Added Successfully");
    }

    /**
     * This method removes a person from the viewing array from the log class.
     */
    private static void option32() {
        Scanner sc = new Scanner(System.in);
        Person p = createAPerson();
        Log.removePerson(p);
        System.out.println("Person Removed Successfully");
    }

    /**
     * This method reads and prints ten iterations at a time from the log.txt
     * file.
     * 
     * @throws FileNotFoundException This throws if the program cannot find the
     *                               log.txt file..
     */
    private static void option33() throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        boolean cont = true;
        Scanner file = new Scanner(logFile);
        int i = 1;
        int max = 10;
        System.out.println("What is your name (FirstLast):");
        // runs if the person is on the view access list
        if (Log.canView(new Person(sc.next()))) {
            do {
                // prints the first 10 lines
                while (file.hasNextLine() && i <= max) {
                    System.out.println(i++ + ") " + file.nextLine());
                }
                max += 10;
                String ans;
                // asks the person if they would like to see the next 10 lines
                if (file.hasNext()) {
                    do {
                        System.out.println("Would you like to continue (y/n)");
                        ans = sc.next().toLowerCase();
                    } while (!ans.equals("y") || !ans.equals("n"));
                    cont = ans.equals("y");
                } else {
                    cont = false;
                }
            } while (cont);
        }
    }

    /**
     * This method imports a log file and adds it to the log.txt file.
     * 
     * @throws FileNotFoundException if log.txt is not found.
     */
    private static void option34() throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        System.out.println("What is your name (FirstLast): ");
        if (Log.canView(new Person(sc.next()))) {
            System.out.println(
                    "What is the name of the file you would like to"
                            + "import?\nEnter it here:");
            Log.importLog(sc.next());
        } else {
            System.out.println("You do not have access to edit the logs.");
        }
    }

    /**
     * This method prompts the user all the necessary information into creating
     * a Person object. This method will make the name all caps.
     * 
     * @return A Person object that holds the new Person.
     */
    private static Person createAPerson() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Print the person's name (FirstLast): ");
        Person p = new Person(sc.next().toUpperCase());
        
        String ans = "";
        do {
            System.out.println("Are they allowed (y/n): ");
            ans = sc.next().toLowerCase();
        } while (!ans.equals("y") && !ans.equals("n"));
        p.changeAccess(ans.equals("y"));
        
        return p;
    }

}
