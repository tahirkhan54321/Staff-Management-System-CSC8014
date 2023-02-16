package staff.system.staff;

import staff.system.Module;
import staff.system.Name;
import staff.system.smartcard.SmartCard;

import java.util.Set;

public class Lecturer extends AbstractStaff {

    private Set<Module> modules;

    public Lecturer(Name name, StaffID staffID, SmartCard smartCard, String staffType, String staffEmploymentStatus,
                    Set<Module> modules) { //todo: should modules even be in the parameter list or should it be an empty set?
        super(name, staffID, smartCard, staffType, staffEmploymentStatus);
        this.modules = modules;
    }

    public String getModules() {
        String allModuleInfo = "";
        System.out.println("Here are all the modules assigned to this Lecturer (" + this.getName() + "): ");
        for (Module module : this.modules) {
            allModuleInfo = allModuleInfo + module.toString() + "\n";
        }
        return allModuleInfo;
    }

    public boolean enoughModules() {
        boolean isEnough = false;
        int semesterOneCredits = 0;
        int semesterTwoCredits = 0;
        for (Module module : this.modules) {
            if (module.getSemester() == 1) {
                semesterOneCredits += module.getCredits();
            } else if (module.getSemester() == 2) {
                semesterTwoCredits += module.getCredits();
            }
        }
        if (semesterOneCredits == 40 && semesterTwoCredits == 40) {
            isEnough = true;
        }
        return isEnough;
    }

    public void setModules(Set<Module> modules) {
        this.modules = modules;
    }
}
