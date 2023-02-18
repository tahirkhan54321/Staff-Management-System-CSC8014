package staff.system.staff;

import staff.system.Name;
import staff.system.smartcard.SmartCard;

import java.util.Date;
import java.util.Set;

public class Researcher extends AbstractStaff {

    private Set<Name> students;

    public Researcher(Name name, Date dateOfBirth, String staffType, String staffEmploymentStatus) {
        //Java will call the StaffID, SmartCard parts of the AbstractStaff constructor if I don't define them here
        super(name, dateOfBirth, staffType, staffEmploymentStatus);
        this.students = null;
    }

    public void setStudents(Set<Name> students) {
        this.students = students;
    }

    public String listStudentNames() {
        String allStudentNames = "";
        System.out.println("Here are all the students assigned to this Researcher (" + this.getName() + "): ");
        for (Name name : this.students) {
            allStudentNames = allStudentNames + name.toString() + "\n";
        }
        return allStudentNames;
    }

    public boolean enoughStudents() {
        boolean isEnough = false;
        if (students.size() >= 10) {
            isEnough = true;
        }
        return isEnough;
    }


}
