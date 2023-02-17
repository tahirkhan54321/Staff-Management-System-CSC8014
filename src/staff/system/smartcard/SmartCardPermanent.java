package staff.system.smartcard;

import staff.system.Name;

import java.util.Calendar;

public class SmartCardPermanent extends SmartCard {

    private SmartCardNumber smartCardNumber;
    private Calendar dateOfIssue;
    private Calendar expiryDate;

    public SmartCardPermanent(Name name, Calendar dateOfBirth) {
        super(name, dateOfBirth);
        String initials = String.valueOf(this.name.getFirstName().charAt(0) + this.name.getLastName().charAt(0));
        this.smartCardNumber = smartCardNumber.getInstance(initials);
        this.dateOfIssue = Calendar.getInstance();
    }
}
