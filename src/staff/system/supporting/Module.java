package staff.system.supporting;

public class Module {

    private final String moduleCode;
    private final String moduleName;
    private final int semester;
    private final int credits;

    public Module(String moduleCode, String moduleName, int semester, int credits) {
        this.moduleCode = moduleCode;
        this.moduleName = moduleName;
        this.semester = semester;
        this.credits = credits;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public String getModuleName() {
        return moduleName;
    }

    public int getSemester() {
        return semester;
    }

    public int getCredits() {
        return credits;
    }

    @Override
    public String toString() {
        return "Module Name: " + moduleName + ", Module Code: " + moduleCode
                + ", Semester: " + semester + ", Credits: " + credits;
    }
}
