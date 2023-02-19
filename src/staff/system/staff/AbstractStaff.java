package staff.system.staff;

import staff.system.Name;
import staff.system.smartcard.SmartCard;

import java.util.Date;

public abstract class AbstractStaff implements Staff {

    private Name name;
    private Date dateOfBirth;
    private StaffID staffID;
    private SmartCard smartCard;
    private String staffEmploymentStatus;
    public static final String LECTURER = "Lecturer";
    public static final String RESEARCHER = "Researcher";
    public static final String PERMANENT = "Permanent";
    public static final String CONTRACT = "Contract";

    AbstractStaff(Name name, Date dateOfBirth, String staffEmploymentStatus) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.staffID = StaffID.getInstance();
        this.staffEmploymentStatus = staffEmploymentStatus;
    }

    public static AbstractStaff getInstance(String staffType, Name name, Date dateOfBirth, String staffEmploymentStatus) {
        if (!(staffEmploymentStatus.equalsIgnoreCase(PERMANENT) || staffEmploymentStatus.equalsIgnoreCase(CONTRACT))) {
            throw new IllegalArgumentException("Staff Employment Status is not valid: " + staffEmploymentStatus);
        }
        if (staffType.equalsIgnoreCase(LECTURER)) {
            return new Lecturer(name, dateOfBirth, staffEmploymentStatus);
        } else if (staffType.equalsIgnoreCase(RESEARCHER)) {
            return new Researcher(name, dateOfBirth, staffEmploymentStatus);
        } else {
            throw new IllegalArgumentException("Staff Type is not valid: " + staffType);
        }
    }

    public final void assignSmartCard(SmartCard smartCard) {
        if (this.smartCard != null) {
            throw new IllegalStateException("The staff member has a smartcard already assigned to it: " + this.getSmartCard());
        } else {
            this.smartCard = smartCard;
        }
    }

    public final Name getName() { return name; }

    @Override
    public final StaffID getStaffID() {
        return staffID;
    }

    @Override
    public final SmartCard getSmartCard() {
        return smartCard;
    }

    @Override
    public abstract String getStaffType();

    @Override
    public final String getStaffEmploymentStatus() {
        return staffEmploymentStatus;
    }

    public abstract String list();
}
