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
    public static final String LECTURER = "Lecturer";
    public static final String RESEARCHER = "Researcher";

    AbstractStaff(Name name, Date dateOfBirth, String staffType, String staffEmploymentStatus) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.staffID = StaffID.getInstance();
        this.staffEmploymentStatus = staffEmploymentStatus;
        //objects created before the exception is thrown will not be initialized if the constructor fails.
        //Todo: the below is probably redundant if we're using a getInstance
        if (!(staffType.equalsIgnoreCase("lecturer") || staffType.equalsIgnoreCase("researcher"))) {
            throw new IllegalArgumentException(staffType + " is not a valid Staff Type");
        } else {
            this.staffType = staffType;
        }
    }

    public static AbstractStaff getInstance(Name name, Date dateOfBirth, String staffType, String staffEmploymentStatus) {
        if staffType.equalsIgnoreCase(LECTURER) {
            return new Lecturer(name, dateOfBirth, staffType, staffEmploymentStatus)
        }
    }

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
