package staff.system.staff;

import staff.system.supporting.Name;
import staff.system.supporting.Module;

import java.util.*;

class Researcher extends AbstractStaff {

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
        this.students = Collections.emptySet();
    }

    /**
     * getter for a clone of the students set
     * @return clone of students assigned to the researcher
     */
    public Set<Name> getStudents() { return new HashSet<>(students); }

    /**
     * setter for students assigned to researcher. Cannot assign more than 10 students.
     * @param students the students set to be assigned
     */
    public void setStudents(Set<Name> students) throws IllegalArgumentException {
        int noOfStudents = students.size();
        try {
            if (noOfStudents > 10) {
                throw new IllegalArgumentException("Cannot set more than 10 students to this researcher.");
            } else {
                this.students = students;
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }
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
    public String listString() {
        String allStudentNames = "";
        System.out.println("Here are all the students assigned to this Researcher (" + this.getName() + "): ");
        for (Name name : students) {
            allStudentNames = allStudentNames + name.toString() + "\n";
        }
        return allStudentNames;
    }

    /**
     * lists a set of all students associated with this researcher
     * @return defensive copy of set of students
     */
    public Set<Name> listStudents() {
        return new HashSet<>(students);
    }

    /**
     * gives an empty set because there are no modules associated with a researcher
     * @return empty set
     * @throws IllegalAccessException if used on a researcher object
     */
    public Set<Module> listModules() throws IllegalAccessException {
        try {
            throw new IllegalAccessException("Cannot list modules for a researcher.");
        } catch (IllegalAccessException e) {
            System.out.println(e);
        }
        return Collections.emptySet();
    }

    /**
     * whether the researcher has enough students assigned to them
     * @return true if 10 students, false otherwise
     */
    public boolean isEnough() {
        boolean isEnough = false;
        if (students.size() == 10) {
            isEnough = true;
        }
        return isEnough;
    }




}
