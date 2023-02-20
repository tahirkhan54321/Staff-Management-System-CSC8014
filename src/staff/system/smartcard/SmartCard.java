package staff.system.smartcard;

import staff.system.supporting.Name;

import java.util.Calendar;
import java.util.Date;

public class SmartCard {

    private final Name name;
    private final Date dateOfBirth;
    private SmartCardNumber smartCardNumber;
    private final Date dateOfIssue;
    private Date expiryDate;
    private String employmentStatus;
    public static final String PERMANENT = "Permanent";
    public static final String CONTRACT = "Contract";

    /**
     * constructor for the smartcard
     * @param name name of staff member
     * @param dateOfBirth date of birth of the staff member
     * @param employmentStatus permanent/contract
     * @throws InstantiationException if smartCardNumber cannot be assigned
     * @throws IllegalArgumentException if the staff's age requirements are not met 22-67
     */
    public SmartCard(Name name, Date dateOfBirth, String employmentStatus) throws InstantiationException, IllegalArgumentException {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        String initials = String.valueOf(this.name.getFirstName().charAt(0) + this.name.getLastName().charAt(0));
        this.smartCardNumber = smartCardNumber.getInstance(initials);
        this.dateOfIssue = Calendar.getInstance().getTime();
        this.employmentStatus = employmentStatus;
        this.expiryDate = setExpiryDate();

        //objects created before the exception is thrown will not be initialized if the constructor fails.

        //local copies for comparison without mutating constructor's date
        Calendar dobCopy = Calendar.getInstance();
        dobCopy.setTime(this.dateOfBirth);
        Calendar today = Calendar.getInstance();
        int age = today.get(Calendar.YEAR) - dobCopy.get(Calendar.YEAR);
        if(today.get(Calendar.DAY_OF_YEAR) < dobCopy.get(Calendar.DAY_OF_YEAR)) {
            age--;
        }
        if (age < 22) {
            throw new IllegalArgumentException("The Staff's age is below 22: " + age);
        }
        if (age > 67) {
            throw new IllegalArgumentException("The Staff's age is above 67: " + age);
        }
    }

    /**
     * getter for clone of name
     * @return name assigned to smartcard
     */
    public Name getName() { return new Name(name.getFirstName(), name.getLastName()); }

    /**
     * getter for clone of date of birth
     * @return date of birth
     */
    public Date getDateOfBirth() {
        return (Date) dateOfBirth.clone();
    }

    /**
     * getter for smartCardNumber
     * @return smartCardNumber
     */
    public SmartCardNumber getSmartCardNumber() { return smartCardNumber; } //should be immutable due to getInstance method

    /**
     * getter for date of issue
     * @return date of issue
     */
    public Date getDateOfIssue() {
        return (Date) dateOfIssue.clone();
    }

    /**
     * getter for clone of expiry date
     * @return expiry date
     */
    public Date getExpiryDate() { return (Date) expiryDate.clone(); }

    /**
     * setter for the expiry date conditional upon whether the staff member for this card is permanent or contract
     * @return expiryDate
     */
    private Date setExpiryDate() {
        Calendar temporaryDate = null;
        temporaryDate.setTime(dateOfIssue);
        if (this.employmentStatus.equalsIgnoreCase(PERMANENT)) {
            temporaryDate.add(Calendar.YEAR,10);
            expiryDate = temporaryDate.getTime();
        } else if (this.employmentStatus.equalsIgnoreCase(CONTRACT)) {
            temporaryDate.add(Calendar.YEAR, 2);
            expiryDate = temporaryDate.getTime();
        } else {
            throw new IllegalArgumentException("The employment status does not exist: " + employmentStatus);
        }
        return expiryDate;
    }

}
