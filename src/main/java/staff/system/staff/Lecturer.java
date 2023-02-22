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
      * setter for modules set
      * @param modules a list of modules to be set
      */
    public void setModules(Set<Module> modules) {
        this.modules = modules;
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
    public String list() {
        String allModules = "";
        System.out.println("Here are all the modules assigned to this Lecturer (" + this.getName() + "): ");
        for (Module module: modules) {
            allModules = allModules + module.toString() + "\n";
        }
        return allModules;
    }

     /**
      * is the lecturer teaching enough modules?
      * @return true if the lecturer has 40 credits or more assigned to them, false otherwise
      */
    public boolean isEnough() {
        boolean isEnough = false;
        int totalCredits = 0;
        for (Module module : modules) {
                totalCredits += module.getCredits();
            }
        if (totalCredits >= 40) {
            isEnough = true;
        }
        return isEnough;
    }

}
