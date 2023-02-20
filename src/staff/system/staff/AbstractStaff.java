package staff.system.staff;

import staff.system.Staff;
import staff.system.supporting.Name;
import staff.system.smartcard.SmartCard;

import java.util.Date;

public abstract class AbstractStaff implements Staff {

    private final Name name;
    private final Date dateOfBirth;
    private final StaffID staffID;
    private SmartCard smartCard;
    private final String staffEmploymentStatus;
    public static final String LECTURER = "Lecturer";
    public static final String RESEARCHER = "Researcher";
    public static final String PERMANENT = "Permanent";
    public static final String CONTRACT = "Contract";

    /**
     * Constructor for an abstract staff member
     * @param name name of staff member
     * @param dateOfBirth date of birth of staff member
     * @param staffEmploymentStatus permanent/contract
     */
    protected AbstractStaff(Name name, Date dateOfBirth, String staffEmploymentStatus) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.staffID = StaffID.getInstance();
        this.staffEmploymentStatus = staffEmploymentStatus;
    }

    /**
     * Factory method to create a lecturer/researcher
     * @param staffType lecturer/researcher
     * @param name name of staff member
     * @param dateOfBirth date of birth of staff member
     * @param staffEmploymentStatus permanent/contract
     * @return an AbstractStaff object, effectively a lecturer or researcher
     */
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

    /**
     * assigns a smartCard to an Abstract Staff object if one doesn't already exist for it.
     * Must be invoked for all staff members we want to assign a smartCard to
     * @param smartCard the smartCard we attempt to assign
     */
    public final void assignSmartCard(SmartCard smartCard) {
        if (this.smartCard != null) {
            throw new IllegalStateException("The staff member has a smartcard already assigned to it: " + this.getSmartCard());
        } else {
            this.smartCard = smartCard;
        }
    }

    /**
     * getter for name
     * @return clone of name
     */
    public final Name getName() { return new Name(name.getFirstName(), name.getLastName()); }

    /**
     * getter for staffID
     * @return staffID
     */
    @Override
    public final StaffID getStaffID() { return staffID; } // should be immutable due to getInstance

    /**
     * getter for smartCard
     * @return smartCard
     */
    @Override
    public final SmartCard getSmartCard() { return smartCard; } // should be immutable due to getInstance

    /**
     * getter for staffType
     * @return lecturer/researcher
     */
    @Override
    public abstract String getStaffType();

    /**
     * getter for staffEmploymentStatus
     * @return permanent/contract
     */
    @Override
    public final String getStaffEmploymentStatus() { return staffEmploymentStatus; }

    /**
     * abstract method for researcher/lecturer to implement in regard to enough students/modules respectively
     * @return true/false
     */
    public abstract boolean isEnough();

    /**
     * abstract method for researcher/lecturer to implement in regard to students/modules sets respectively
     * @return set of students or set of modules
     */
    public abstract String list();
}
