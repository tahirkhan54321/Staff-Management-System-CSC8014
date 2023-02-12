package staff.system.staff;

import staff.system.smartcard.SmartCard;

public abstract class AbstractStaff implements Staff {

    private StaffID staffID;
    private SmartCard smartCard;
    private String staffType; //todo: Do I need this at all or can I just use instanceof in a get method?
    // todo: Should this even be in the constructor? And similarly for Res/Lec?
    private String staffEmploymentStatus;

    AbstractStaff(StaffID staffID, SmartCard smartCard, String staffType, String staffEmploymentStatus) {
        this.staffID = staffID;
        this.smartCard = smartCard;
        this.staffType = staffType;
        this.staffEmploymentStatus = staffEmploymentStatus;
    }

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
