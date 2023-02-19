package staff.system.staff.researcher;

import staff.system.supporting.Name;
import staff.system.staff.AbstractStaff;

import java.util.Date;
import java.util.Set;

public class Researcher extends AbstractStaff {

    private Set<Name> students;

    public Researcher(Name name, Date dateOfBirth, String staffEmploymentStatus) {
        //Java will call the StaffID, SmartCard parts of the AbstractStaff constructor if I don't define them here
        super(name, dateOfBirth, staffEmploymentStatus);
        this.students = null;
    }

    public void setStudents(Set<Name> students) {
        this.students = students;
    }

    @Override
    public String getStaffType() {
        return AbstractStaff.RESEARCHER;
    }

    public String list() {
        String allStudentNames = "";
        System.out.println("Here are all the students assigned to this Researcher (" + this.getName() + "): ");
        for (Name name : students) {
            allStudentNames = allStudentNames + name.toString() + "\n";
        }
        return allStudentNames;
    }

    public boolean isEnough() {
        boolean isEnough = false;
        if (students.size() >= 10) {
            isEnough = true;
        }
        return isEnough;
    }


}
