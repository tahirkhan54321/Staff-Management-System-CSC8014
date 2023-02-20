package staff.system.supporting;

public class Name {

    private final String firstName;
    private final String lastName;

    /**
     * constructor for names of staff or students
     * @param firstName first name
     * @param lastName last name
     */
    public Name(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * getter for first name
     * @return first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * getter for last name
     * @return last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * toString
     * @return string representation of the full name
     */
    public String toString() {
        return this.firstName + " " + this.lastName;
    }
}
