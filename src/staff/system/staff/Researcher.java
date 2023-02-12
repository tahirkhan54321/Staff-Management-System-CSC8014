package staff.system.staff;

import staff.system.Module;
import staff.system.Name;
import staff.system.smartcard.SmartCard;

import java.util.Set;

public class Researcher extends AbstractStaff {

    private Set<Name> students;

    public Researcher(StaffID staffID, SmartCard smartCard, String staffType, String staffEmploymentStatus,
                      Set<Name> students) {
        super(staffID, smartCard, staffType, staffEmploymentStatus);
        this.students = students;
    }

    public String listStudentNames() {
        String allStudentNames = "";
        for (Name name : this.students) {
            allStudentNames = allStudentNames + name.toString() + "\n";
        }
        return allStudentNames;
    }

    public boolean enoughStudents() {
        boolean isEnough = false;
        if (this.students.size() == 10) {
            isEnough = true;
        }
        return isEnough;
    }

}
