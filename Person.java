/**
 * An object class for the person object. It is intended for the LockUI, Access,
 * and Log classes.
 * 
 * @author Liam McGuckin
 *
 */
public class Person {

    /**
     * A string global variable that holds the person's name.
     */
    private String name;

    /**
     * A boolean global variable that holds the allowance.
     */
    private boolean allowed;

    /**
     * A constructor method that accepts a name and the allowance.
     * 
     * @param name    A String variable that holds the name of the Person.
     * @param allowed A boolean variable that holds the allowance.
     */
    public Person(String name, boolean allowed) {
        this.name = name;
        this.allowed = allowed;
    }

    /**
     * A constructor method that accepts a name.
     * 
     * @param name A String variable that holds the name of the Person.
     */
    public Person(String name) {
        this.name = name;
    }

    /**
     * A getter method that gets the name of the Person.
     * 
     * @return Returns a String variable with the name of the person.
     */
    public String getName() {
        return this.name;
    }

    /**
     * A getter method that gets the allowance of the Person.
     * 
     * @return Returns a boolean that holds the allowance of the Person.
     */
    public boolean isAllowed() {
        return this.allowed;
    }

    /**
     * A setter method for the name that the Person holds.
     * 
     * @param name A String variable that holds the name of the person.
     */
    public void changeName(String name) {
        this.name = name;
    }

    /**
     * A setter method for the access of the person.
     * 
     * @param allowed A boolean variable that holds the allowance of the person.
     */
    public void changeAccess(boolean allowed) {
        this.allowed = allowed;
    }

    /**
     * An equals method for the Person class that takes in a Person and compares
     * them to this person.
     * 
     * @param p A Person variable that is the one we are comparing to this
     *          class.
     * @return A boolean that holds the similarity.
     */
    public boolean equals(Person p) {
        if (this.name.equals(p.getName()) && this.allowed == p.isAllowed()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * A to string method that creates a String from a person class.
     * 
     * @return A String value that is properly formatted and holds the name and
     *         the allowance.
     */
    public String toString() {
        return String.format("%s %b", this.name, this.allowed);
    }

    /**
     * An isNull method that tells the user if a Person is empty.
     * 
     * @return A boolean value that tells the user if the Person is null.
     */
    public boolean isNull() {
        return name.isEmpty();
    }
}
