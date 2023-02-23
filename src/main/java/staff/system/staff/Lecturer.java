package staff.system.staff;

import staff.system.supporting.Module;
import staff.system.supporting.Name;

import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

class Lecturer extends AbstractStaff {

    private Set<Module> modules;

     /**
      * Constructor for Lecturer
      * @param name the name of the lecturer
      * @param dateOfBirth the date of birth of the lecturer
      * @param staffEmploymentStatus permanent/contract
      */
    public Lecturer(Name name, Date dateOfBirth, String staffEmploymentStatus) {
        //Java will call the StaffID, SmartCard parts of the AbstractStaff constructor if I don't define them here
        super(name, dateOfBirth, staffEmploymentStatus);
        this.modules = Collections.emptySet();
    }

     /**
      * getter
      * @return clone of modules set
      */
    public Set<Module> getModules() { return new HashSet<>(modules); }

     /**
      * setter for modules set. Cannot set more than 40 credits' worth of modules
      * @param modules a list of modules to be set
      */
    public void setModules(Set<Module> modules) throws IllegalArgumentException {
        int totalCredits = 0;
        try {
            for (Module module : modules) {
                totalCredits += module.getCredits();
            }
            if (totalCredits >= 40) {
                throw new IllegalArgumentException("Modules list cannot be set, too many credits.");
            } else {
                this.modules = modules;
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }
    }

     /**
      * getter
      * @return this object is a lecturer
      */
    @Override
    public String getStaffType() {
        return AbstractStaff.LECTURER;
    }

     /**
      * listing all modules assigned to a lecturer in string format
      * @return string of all modules assigned to a lecturer
      */
    public String listString() {
        String allModules = "";
        System.out.println("Here are all the modules assigned to this Lecturer (" + this.getName() + "): ");
        for (Module module: modules) {
            allModules = allModules + module.toString() + "\n";
        }
        return allModules;
    }

    /**
     * lists a set of all modules associated with this lecturer
     * @return defensive copy of set of modules
     */
    public Set<Module> listModules() {
        return new HashSet<>(modules);
    }

    /**
     * gives an empty set because there are no students associated with a lecturer
     * @return empty set
     * @throws IllegalAccessException if used on a lecturer object
     */
    public Set<Name> listStudents() throws IllegalAccessException {
        try {
            throw new IllegalAccessException("Cannot list students for a lecturer.");
        } catch (IllegalAccessException e) {
            System.out.println(e);
        }
        return Collections.emptySet();
    }

     /**
      * is the lecturer teaching enough modules?
      * @return true if the lecturer has 40 credits assigned to them, false otherwise
      */
    public boolean isEnough() {
        boolean isEnough = false;
        int totalCredits = 0;
        for (Module module : modules) {
                totalCredits += module.getCredits();
            }
        if (totalCredits == 40) {
            isEnough = true;
        }
        return isEnough;
    }

}
