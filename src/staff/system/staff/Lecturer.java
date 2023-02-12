package staff.system.staff;

import staff.system.Module;
import staff.system.smartcard.SmartCard;

import java.util.Set;

public class Lecturer extends AbstractStaff {

    private Set<Module> modules;

    public Lecturer(StaffID staffID, SmartCard smartCard, String staffType, String staffEmploymentStatus,
                      Set<Module> modules) {
        super(staffID, smartCard, staffType, staffEmploymentStatus);
        this.modules = modules;
    }

    public String getModules() {
        String allModuleInfo = "";
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
}
