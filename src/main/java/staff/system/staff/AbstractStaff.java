package staff.system.staff;

import staff.system.supporting.Name;
import staff.system.smartcard.SmartCard;

import java.util.Date;

public abstract class AbstractStaff implements Staff {

    private final Name name;
    private final Date dateOfBirth;
    private final StaffID staffID;
    private SmartCard smartCard;
    private final String staffEmploymentStatus;
    static final String LECTURER = "Lecturer";
    static final String RESEARCHER = "Researcher";
    private static final String PERMANENT = "Permanent";
    private static final String CONTRACT = "Contract";

    /**
     * Constructor for an abstract staff member
     * @param name name of staff member
     * @param dateOfBirth date of birth of staff member
     * @param staffEmploymentStatus permanent/contract
     */
    AbstractStaff(Name name, Date dateOfBirth, String staffEmploymentStatus) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.staffID = StaffID.getInstance();
        this.staffEmploymentStatus = staffEmploymentStatus;
        this.smartCard = null;
    }

    /**
     * Factory method to create a lecturer/researcher
     * @param staffType lecturer/researcher
     * @param name name of staff member
     * @param dateOfBirth date of birth of staff member
     * @param staffEmploymentStatus permanent/contract
     * @return an AbstractStaff object, effectively a lecturer or researcher
     */
    public static AbstractStaff getInstance(String staffType, Name name, Date dateOfBirth,
                                            String staffEmploymentStatus) throws IllegalArgumentException {
        try {
            if (!(staffEmploymentStatus.equalsIgnoreCase(PERMANENT) || staffEmploymentStatus.equalsIgnoreCase(CONTRACT))) {
                throw new IllegalArgumentException("Staff Employment Status is not valid");
            }
            if(!(staffType.equalsIgnoreCase(LECTURER) || staffType.equalsIgnoreCase(RESEARCHER))) {
                throw new IllegalArgumentException("Staff Type is not valid");
            }
        }
        catch (IllegalArgumentException e) {
            System.out.println(e);
        }

        AbstractStaff staffObject = null;
        if (staffType.equalsIgnoreCase(LECTURER)) {
            staffObject = new Lecturer(name, dateOfBirth, staffEmploymentStatus);
        } else if (staffType.equalsIgnoreCase(RESEARCHER)) {
            staffObject = new Researcher(name, dateOfBirth, staffEmploymentStatus);
        }
        return staffObject;
    }

    /**
     * assigns a smartCard to an Abstract Staff object if one doesn't already exist for it.
     * Must be invoked for all staff members we want to assign a smartCard to
     * @param sc the smartCard we attempt to assign
     */
    public final void assignSmartCard(SmartCard sc) throws IllegalStateException {
        try {
            if (smartCard != null) {
                throw new IllegalStateException("The staff member has a smartcard already assigned to it");
            } else {
                smartCard = sc;
            }
        } catch (IllegalStateException e) {
            System.out.println(e);
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
    public final StaffID getStaffID() { return this.staffID; } // should be immutable due to getInstance of Staff ID

    /**
     * getter for smartCard
     * @return smartCard
     */
    @Override
    public final SmartCard getSmartCard() { return smartCard; } // should be immutable due to getInstance of SmartCard

    /**
     * getter for staffEmploymentStatus
     * @return permanent/contract
     */
    @Override
    public final String getStaffEmploymentStatus() { return staffEmploymentStatus; }

}
