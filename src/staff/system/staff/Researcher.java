package staff.system.staff;

import staff.system.supporting.Name;
import staff.system.staff.AbstractStaff;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Researcher extends AbstractStaff {

    private Set<Name> students;

    /**
     * constructor for researcher
     * @param name name of researcher
     * @param dateOfBirth date of birth of researcher
     * @param staffEmploymentStatus permanent/contract
     */
    public Researcher(Name name, Date dateOfBirth, String staffEmploymentStatus) {
        //Java will call the StaffID, SmartCard parts of the AbstractStaff constructor if I don't define them here
        super(name, dateOfBirth, staffEmploymentStatus);
        this.students = null;
    }

    /**
     * getter for a clone of the students set
     * @return clone of students assigned to the researcher
     */
    public Set<Name> getStudents() { return new HashSet<>(students); }

    /**
     * setter for students assigned to researcher
     * @param students the students set to be assigned
     */
    public void setStudents(Set<Name> students) {
        this.students = students;
    }

    /**
     * getter for the staffType
     * @return researcher
     */
    @Override
    public String getStaffType() {
        return AbstractStaff.RESEARCHER;
    }

    /**
     * lists all the students assigned to the researcher as a string
     * @return string of student names
     */
    public String list() {
        String allStudentNames = "";
        System.out.println("Here are all the students assigned to this Researcher (" + this.getName() + "): ");
        for (Name name : students) {
            allStudentNames = allStudentNames + name.toString() + "\n";
        }
        return allStudentNames;
    }

    /**
     * whether the researcher has enough students assigned to them
     * @return true if >10 students, false otherwise
     */
    public boolean isEnough() {
        boolean isEnough = false;
        if (students.size() >= 10) {
            isEnough = true;
        }
        return isEnough;
    }


}
