package staff.system.staff;

import staff.system.Name;
import staff.system.smartcard.SmartCard;

public abstract class AbstractStaff implements Staff {

    private Name name;
    private StaffID staffID;
    private SmartCard smartCard;
    private String staffType; //todo: Do I need this at all or can I just use instanceof in a get method?
    // todo: Should this even be in the constructor? And similarly for Res/Lec?
    private String staffEmploymentStatus;

    AbstractStaff(Name name, StaffID staffID, SmartCard smartCard, String staffType, String staffEmploymentStatus) {
        this.name = name;
        this.staffID = StaffID.getInstance(); //todo: should I do getInstance here?
        this.smartCard = smartCard; //todo: should I do getInstance here?
        this.staffType = staffType;
        this.staffEmploymentStatus = staffEmploymentStatus;
    }


    public Name getName() { return this.name; }

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
