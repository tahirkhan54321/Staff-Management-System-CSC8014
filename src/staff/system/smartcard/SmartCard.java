package staff.system.smartcard;

import staff.system.Name;

import java.util.Calendar;

public abstract class SmartCard {

    private Name name;
    private Calendar dateOfBirth;
    private SmartCardNumber smartCardNumber;
    private Calendar dateOfIssue;
    private Calendar expiryDate;


    public SmartCard(Name name, Calendar dateOfBirth) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        String initials = String.valueOf(this.name.getFirstName().charAt(0) + this.name.getLastName().charAt(0));
        this.smartCardNumber = smartCardNumber.getInstance(initials);
        this.dateOfIssue = Calendar.getInstance();
    }

    public Name getName() {
        return name;
    }

    public Calendar getDateOfBirth() {
        return dateOfBirth;
    }

    public SmartCardNumber getSmartCardNumber() {
        return smartCardNumber;
    }

    public Calendar getDateOfIssue() {
        return dateOfIssue;
    }

    public Calendar getExpiryDate() {
        return expiryDate;
    }

    private void setExpiryDate(String staffType) { //todo: double check if this should even have an input parameter. Spec would suggest that it shouldn't
        if (staffType == "permanent") {
            this.expiryDate.set(this.dateOfIssue.add(Calendar.YEAR,10));
        } else if (staffType == "contract") {
            this.expiryDate.add(Calendar.YEAR, 2);
        } else {
            throw new IllegalArgumentException("The staffType does not exist: " + staffType);
        }
    }

}
