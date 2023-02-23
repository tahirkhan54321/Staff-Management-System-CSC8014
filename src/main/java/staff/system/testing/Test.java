package staff.system.testing;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.function.Executable;
import staff.system.smartcard.SmartCard;
import staff.system.staff.AbstractStaff;
import staff.system.supporting.Name;
import staff.system.supporting.Module;
import staff.system.staff.StaffManager;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Test {

    public static void main(String[] args) throws ParseException, InstantiationException, IOException {

        /*
        Initialising all useful variables
         */
        Name nameValid = new Name("Tom", "Smith");

        SimpleDateFormat genericDateOfBirth = new SimpleDateFormat("yyyy-MM-dd");
        Date dateOfBirthValid = genericDateOfBirth.parse("1990-07-26");
        Date dateOfBirthTooOld = genericDateOfBirth.parse("1900-07-26");
        Date dateOfBirthTooYoung = genericDateOfBirth.parse("2010-07-26");

        Module moduleValid = new Module("CSC8011", "Introduction to Software Development", 1, 10);

        Set<Module> modulesSetValid = new HashSet<>();
        modulesSetValid.add(moduleValid);

        Set<Module> modulesSetInvalid = null;

        /*
        Testing the AbstractStaff objects with valid/invalid arguments
         */
        AbstractStaff lecturerPermanentValid = AbstractStaff.getInstance("lecturer",
                nameValid, dateOfBirthValid, "permanent");
        Assertions.assertNotNull(lecturerPermanentValid);

        AbstractStaff researcherPermanentValid = AbstractStaff.getInstance("researcher",
                nameValid, dateOfBirthValid, "permanent");
        Assertions.assertNotNull(researcherPermanentValid);

        AbstractStaff lecturerContractValid = AbstractStaff.getInstance("lecturer",
                nameValid, dateOfBirthValid, "contract");
        Assertions.assertNotNull(lecturerContractValid);

        AbstractStaff researcherContractValid = AbstractStaff.getInstance("researcher",
                nameValid, dateOfBirthValid, "contract");
        Assertions.assertNotNull(researcherContractValid);

        try {
            AbstractStaff abstractStaffBadStaffType = AbstractStaff.getInstance("abstract",
                    nameValid, dateOfBirthValid, "contract");
        }
        catch (IllegalArgumentException t) {
            Assertions.assertThrows(IllegalArgumentException.class, (Executable) t);
        }

        try {
            AbstractStaff abstractStaffBadStaffEmploymentStatus = AbstractStaff.getInstance("researcher",
                    nameValid, dateOfBirthValid, "neither");
        } catch (IllegalArgumentException t) {
            Assertions.assertThrows(IllegalArgumentException.class, (Executable) t);
        }

        try {
            AbstractStaff abstractStaffTooOld = AbstractStaff.getInstance("researcher",
                    nameValid, dateOfBirthTooOld, "contract");
        } catch (IllegalArgumentException t) {
            Assertions.assertThrows(IllegalArgumentException.class, (Executable) t);
        }

        try {
        AbstractStaff abstractStaffTooYoung = AbstractStaff.getInstance("researcher",
                nameValid, dateOfBirthTooYoung, "contract");
        } catch (IllegalArgumentException t) {
            Assertions.assertThrows(IllegalArgumentException.class, (Executable) t);
        }

        /*
        Testing smartCard instantiation
         */
        SmartCard smartCardValid = new SmartCard(nameValid, dateOfBirthValid, "permanent");
        Assertions.assertNotNull(smartCardValid);

        try {
            SmartCard smartCardTooOld = new SmartCard(nameValid, dateOfBirthTooOld, "permanent");
        } catch (IllegalArgumentException t) {
                Assertions.assertThrows(IllegalArgumentException.class, (Executable) t);
        }

        try {
            SmartCard smartCardTooYoung = new SmartCard(nameValid, dateOfBirthTooYoung, "permanent");
        } catch (IllegalArgumentException t) {
            Assertions.assertThrows(IllegalArgumentException.class, (Executable) t);
        }

        /*
        Testing readInModules from StaffManager
         */
        StaffManager staffManager = StaffManager.getInstance();
        Assertions.assertNotNull(staffManager.readInModules("modules.txt"));

        try {
            staffManager.readInModules("doesNotExist.txt");
        } catch (IOException t) {
        Assertions.assertThrows(IOException.class, (Executable) t);
        }

        /*
        Testing readInStudents from StaffManager
         */
        staffManager.readInStudents("students.txt");

        try {
            staffManager.readInStudents("doesNotExist.txt");
        } catch (IOException t) {
            Assertions.assertThrows(IOException.class, (Executable) t);
        }

        /*
        Testing noOfStaff from StaffManager
         */
        staffManager.employStaff(nameValid.getFirstName(), nameValid.getLastName(), dateOfBirthValid, "lecturer",
                "permanent");
        staffManager.employStaff(nameValid.getFirstName(), nameValid.getLastName(), dateOfBirthValid, "researcher",
                "permanent");
        staffManager.employStaff(nameValid.getFirstName(), nameValid.getLastName(), dateOfBirthValid, "lecturer",
                "contract");
        Assertions.assertEquals(staffManager.noOfStaff("lecturer"), 2);
        Assertions.assertEquals(staffManager.noOfStaff("researcher"), 1);
        try {
            staffManager.noOfStaff("doesNotExist");
        } catch (IllegalArgumentException t) {
            Assertions.assertThrows(IllegalArgumentException.class, (Executable) t);
        }

        /*
        Testing addData from StaffManager
         */
        //lecturerPermanentValid.addData(lecturerPermanentValid.getStaffID());

        /*
        Testing employStaff from StaffManager
         */
        Assertions.assertNotNull(staffManager.employStaff(nameValid.getFirstName(), nameValid.getLastName(), dateOfBirthValid,
                "lecturer", "permanent"));

        try {
            staffManager.employStaff(nameValid.getFirstName(), nameValid.getLastName(), dateOfBirthTooOld,
                    "lecturer", "permanent");
        } catch (IllegalArgumentException t) {
            Assertions.assertThrows(IllegalArgumentException.class, (Executable) t);
        }

        try {
            staffManager.employStaff(nameValid.getFirstName(), nameValid.getLastName(), dateOfBirthTooYoung,
                    "lecturer", "permanent");
        } catch (IllegalArgumentException t) {
            Assertions.assertThrows(IllegalArgumentException.class, (Executable) t);
        }

        try {
            staffManager.employStaff(nameValid.getFirstName(), nameValid.getLastName(), dateOfBirthValid,
                    "doesNotExist", "permanent");
        } catch (IllegalArgumentException t) {
            Assertions.assertThrows(IllegalArgumentException.class, (Executable) t);
        }

        try {
            staffManager.employStaff(nameValid.getFirstName(), nameValid.getLastName(), dateOfBirthValid,
                    "lecturer", "doesNotExist");
        } catch (IllegalArgumentException t) {
            Assertions.assertThrows(IllegalArgumentException.class, (Executable) t);
        }

        /*
        Testing getAllStaff from StaffManager
         */
        Assertions.assertFalse(staffManager.getAllStaff().isEmpty());
        //System.out.println(staffManager.toString());


        /*
        Testing terminateStaff from StaffManager
        I don't know how to get the randomly generated ids of the staff in the StaffManager hashmap. StaffID class cannot be imported
         */

    }
}
