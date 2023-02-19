package staff.system.staff;

import staff.system.Module;
import staff.system.Name;

import java.util.Date;
import java.util.Set;

public class Lecturer extends AbstractStaff {

    private Set<Module> modules;

    public Lecturer(Name name, Date dateOfBirth, String staffEmploymentStatus) {
        //Java will call the StaffID, SmartCard parts of the AbstractStaff constructor if I don't define them here
        super(name, dateOfBirth, staffEmploymentStatus);
        this.modules = null;
    }

    public Set<Module> getModules() {
        return modules;
    }

    public void setModules(Set<Module> modules) {
        this.modules = modules;
    }

    @Override
    public String getStaffType() {
        return AbstractStaff.LECTURER;
    }

    public String list() {
        String allModules = "";
        System.out.println("Here are all the modules assigned to this Lecturer (" + this.getName() + "): ");
        for (Module module: modules) {
            allModules = allModules + module.toString() + "\n";
        }
        return allModules;
    }

    public boolean enoughModules() {
        boolean isEnough = false;
        int totalCredits = 0;
        for (Module module : this.modules) {
                totalCredits += module.getCredits();
            }
        if (totalCredits >= 40) {
            isEnough = true;
        }
        return isEnough;
    }

}
