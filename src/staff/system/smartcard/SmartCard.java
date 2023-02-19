package staff.system.smartcard;

import staff.system.supporting.Name;
import staff.system.staff.AbstractStaff;


import java.util.Calendar;
import java.util.Date;

public class SmartCard implements Cloneable {

    private final Name name;
    private final Date dateOfBirth;
    private SmartCardNumber smartCardNumber;
    private final Date dateOfIssue;
    private Date expiryDate;


    public SmartCard(Name name, Date dateOfBirth, String employmentStatus) throws InstantiationException {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        String initials = String.valueOf(this.name.getFirstName().charAt(0) + this.name.getLastName().charAt(0));
        this.smartCardNumber = smartCardNumber.getInstance(initials);
        this.dateOfIssue = Calendar.getInstance().getTime();
        this.expiryDate = setExpiryDate(employmentStatus);

        //objects created before the exception is thrown will not be initialized if the constructor fails.

        //local copies for comparison without mutating constructor's dates
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

    public Name getName() {
        return name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public SmartCardNumber getSmartCardNumber() {
        return smartCardNumber;
    }

    public Date getDateOfIssue() {
        return dateOfIssue;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    private Date setExpiryDate(String employmentStatus) {
        Calendar temporaryDate = null;
        temporaryDate.setTime(dateOfIssue);
        if (employmentStatus.equalsIgnoreCase(AbstractStaff.PERMANENT)) {
            temporaryDate.add(Calendar.YEAR,10);
            expiryDate = temporaryDate.getTime();
        } else if (employmentStatus.equalsIgnoreCase(AbstractStaff.CONTRACT)) {
            temporaryDate.add(Calendar.YEAR, 2);
            expiryDate = temporaryDate.getTime();
        } else {
            throw new IllegalArgumentException("The employment status does not exist: " + employmentStatus);
        }
        return expiryDate;
    }

    public Object clone() throws CloneNotSupportedException {
        SmartCard clone = (SmartCard) super.clone();

        return clone;
    }

}
