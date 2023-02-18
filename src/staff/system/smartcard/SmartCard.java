package staff.system.smartcard;

import staff.system.Name;

import java.util.Calendar;
import java.util.Date;

public class SmartCard {

    private Name name;
    private Date dateOfBirth;
    private SmartCardNumber smartCardNumber;
    private Date dateOfIssue;
    private Date expiryDate;


    public SmartCard(Name name, Date dateOfBirth) throws InstantiationException {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        String initials = String.valueOf(this.name.getFirstName().charAt(0) + this.name.getLastName().charAt(0));
        this.smartCardNumber = smartCardNumber.getInstance(initials);
        this.dateOfIssue = Calendar.getInstance().getTime();
        this.expiryDate = Calendar.getInstance().getTime();

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


    public void setExpiryDate(String staffType) { //todo: double check if this should even have an input parameter. Spec would suggest that it shouldn't
        Calendar temporaryDate = null;
        temporaryDate.setTime(dateOfIssue);
        if (staffType.equalsIgnoreCase("permanent")) {
            temporaryDate.add(Calendar.YEAR,10);
            expiryDate = temporaryDate.getTime();
        } else if (staffType.equalsIgnoreCase("contract")) {
            temporaryDate.add(Calendar.YEAR, 2);
            expiryDate = temporaryDate.getTime();
        } else {
            throw new IllegalArgumentException("The staffType does not exist: " + staffType);
        }
    }

}
