package staff.system.supporting;

public class Module {

    private final String moduleCode;
    private final String moduleName;
    private final int semester;
    private final int credits;

    /**
     * constructor for module
     * @param moduleCode the module code
     * @param moduleName the module name
     * @param semester which semeseter the module runs in
     * @param credits the number of credits the module has
     */
    public Module(String moduleCode, String moduleName, int semester, int credits) {
        this.moduleCode = moduleCode;
        this.moduleName = moduleName;
        this.semester = semester;
        this.credits = credits;
    }

    /**
     * getter for module code
     * @return module code
     */
    public String getModuleCode() {
        return moduleCode;
    }

    /**
     * getter for module name
     * @return module name
     */
    public String getModuleName() {
        return moduleName;
    }

    /**
     * getter for semester
     * @return semester number
     */
    public int getSemester() {
        return semester;
    }

    /**
     * getter for credits
     * @return number of credits for this module
     */
    public int getCredits() {
        return credits;
    }

    /**
     * toString
     * @return a string representation of all the module details
     */
    @Override
    public String toString() {
        return "Module Name: " + moduleName + ", Module Code: " + moduleCode
                + ", Semester: " + semester + ", Credits: " + credits;
    }
}
