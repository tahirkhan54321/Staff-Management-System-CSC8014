package staff.system.staff;

import staff.system.Name;
import staff.system.smartcard.SmartCard;

import java.util.Date;

public abstract class AbstractStaff implements Staff {

    private Name name;
    private Date dateOfBirth;
    private StaffID staffID;
    private SmartCard smartCard;
    private String staffType;
    private String staffEmploymentStatus;

    AbstractStaff(Name name, Date dateOfBirth, String staffType, String staffEmploymentStatus) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.staffID = StaffID.getInstance();
        this.staffEmploymentStatus = staffEmploymentStatus;
        //objects created before the exception is thrown will not be initialized if the constructor fails.
        if (!(staffType.equalsIgnoreCase("lecturer") || staffType.equalsIgnoreCase("researcher"))) {
            throw new IllegalArgumentException(staffType + " is not a valid Staff Type");
        } else {
            this.staffType = staffType;
        }
    }

    //todo: need to ensure SmartCard is assigned in the StaffManager class
    public void assignSmartCard(SmartCard smartCard) {
        if (this.smartCard != null) {
            throw new IllegalStateException("The staff member has a smartcard already assigned to it: " + this.getSmartCard());
        } else {
            this.smartCard = smartCard;
        }
    }

    public Name getName() { return name; }

    @Override
    public StaffID getStaffID() {
        return staffID;
    }

    @Override
    public SmartCard getSmartCard() {
        return smartCard;
    }

    @Override
    public String getStaffType() {
        return staffType;
    }

    @Override
    public String getStaffEmploymentStatus() {
        return staffEmploymentStatus;
    }



}
