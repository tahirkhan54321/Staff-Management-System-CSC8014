package staff.system.driver;

import staff.system.staff.AbstractStaff;
import staff.system.Staff;
import staff.system.staff.StaffID;
import staff.system.supporting.Module;
import staff.system.supporting.Name;
import staff.system.smartcard.SmartCard;
import staff.system.staff.Lecturer;
import staff.system.staff.Researcher;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;


public class StaffManager {

	//you can add attributes and other methods if needed. 
	//you can throw an exception if needed
	private static Set<Module> allModules = new HashSet<>();
	private static Set<Name> allStudents = new HashSet<>();
	private static Map<StaffID, AbstractStaff> allStaff = new HashMap<>();

	/**
	 *This method should allow modules information to be read from a pre-defined data file and stored in a set of modules
	 * @param path the path to the file to be read
	 * @return the set of modules read in from the file
	 * @throws IOException when the file is not found
	 */
	public Set<Module> readInModules(String path) throws IOException {
		int counter = 0;
		Set<Module> readModules = new HashSet<>();
		try {
			Scanner scanner = new Scanner(Paths.get(path));
			while (scanner.hasNext()) {
				String line = scanner.nextLine();
				String partsOfModule[] = line.split(", ");

				String moduleCode = partsOfModule[0];
				String moduleName = partsOfModule[1];
				int semester = Integer.valueOf(partsOfModule[2]);
				int credits = Integer.valueOf(partsOfModule[3]);
				Module module = new Module(moduleCode, moduleName, semester, credits);

				readModules.add(module);
				counter++;
			}

			if (readModules.isEmpty()) {
				readModules = Collections.EMPTY_SET;
			} else {
				allModules.addAll(readModules);
			}
			System.out.println(counter + " modules have been added to the set.");

		} catch (IOException e) {
			System.out.println("Filepath does not exist: " + path);
		}
		return readModules;
	}

	/**
	 * This method should allow students information to be read from a pre-defined data file and stored in a set of names.
	 * @param path the path to the file to be read
	 * @return the set of students read in from the file
	 * @throws IOException when the file is not found
	 */
	public Set<Name> readInStudents(String path) throws IOException {
		Set<Name> readStudents = new HashSet<>();
		try {
			Scanner scanner = new Scanner(Paths.get(path));

			while (scanner.hasNext()) {
				String line = scanner.nextLine();
				String partsOfName[] = line.split(" ");

				String firstName = partsOfName[0];
				String secondName = partsOfName[1];

				Name name = new Name(firstName, secondName);
				readStudents.add(name);
			}

			if (readStudents.isEmpty()) {
				readStudents = Collections.EMPTY_SET;
			} else {
				allStudents.addAll(readStudents);
			}
		} catch (IOException e) {
			System.out.println("Filepath does not exist: " + path);
		}
		System.out.println(readStudents.size() + " students have been added to the set.");
		return readStudents;
	}

	/**
	 * This method returns the number of staff of the specified type that are currently employed.
	 * @param type staff type, researcher or lecturer
	 * @return number of researchers or lecturers
	 * @throws IllegalArgumentException if the type argument is not a researcher or a lecturer
	 */
	public int noOfStaff(String type) throws IllegalArgumentException {
		int counter = 0;
		try {
			if (!(type.equalsIgnoreCase(AbstractStaff.LECTURER) || type.equalsIgnoreCase(AbstractStaff.RESEARCHER))) {
				throw new IllegalArgumentException(type + " is not a valid staff type");
			}
		/*
		this way of iterating through a hashmap was taken from GeeksforGeeks:
            https://www.geeksforgeeks.org/how-to-iterate-hashmap-in-java/
            Original Author - GeeksforGeeks, "piyushkr2022"
            Modifying Author – Tahir Khan
		 */
			Iterator<Map.Entry<StaffID, AbstractStaff>> iterator = allStaff.entrySet().iterator();
			while (iterator.hasNext()) {
				Map.Entry<StaffID, AbstractStaff> entry = iterator.next();
				if (entry.getValue().getStaffType().equalsIgnoreCase(type)) {
					counter++;
				}
			}
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		return counter;
	}

	/**
	 * This method adds either a set of modules or a set of students to the staff depending on their type.
	 * @param id the staff member's id
	 * @param modules the modules which are to be assigned to a lecturer
	 * @param students the students which are to be assigned to a researcher
	 * @return true if assignment was successful, false if unsuccessful
	 * @throws IllegalArgumentException if staff id does not exist, if allModules and allStudents don't contain the arguments,
	 * if staff type is invalid
	 */
	public boolean addData(StaffID id, Set<Module> modules, Set<Name> students) throws IllegalArgumentException {

		boolean modulesExist = false;
		boolean studentsExist = false;
		String staffType = allStaff.get(id).getStaffType();
		//note that a set cannot contain a null value so this should work.

		try {
			if (!allStaff.containsKey(id)) {
				throw new IllegalArgumentException("Staff ID does not exist: " + id);
			}
			if (allModules.containsAll(modules)) {
				modulesExist = true;
			}
			if (allStudents.containsAll(students)) {
				studentsExist = true;
			}
			if (!(allModules.containsAll(modules) || allStudents.containsAll(students))) {
				throw new IllegalArgumentException("Modules and Students parameters don't exist in the list.");
			}
			if (!(staffType.equalsIgnoreCase(AbstractStaff.LECTURER) || staffType.equalsIgnoreCase(AbstractStaff.RESEARCHER))) {
				throw new IllegalArgumentException("Staff type isn't Lecturer or Researcher for ID: " + id);
			}

			if (staffType.equalsIgnoreCase(AbstractStaff.LECTURER) && modulesExist) {
				Lecturer lecturer = (Lecturer) allStaff.get(id);
				lecturer.setModules(modules);
				allStaff.put(id, lecturer);
				return true;
			}
			if (staffType.equalsIgnoreCase(AbstractStaff.RESEARCHER) && studentsExist) {
				Researcher researcher = (Researcher) allStaff.get(id);
				researcher.setStudents(students);
				allStaff.put(id, researcher);
				return true;
			}
		}
		catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
			}
		return false;
	}

	/**
	 * This method registers a new staff onto the system and allocates a smart card and a staff ID
	 * @param firstName first name of staff member
	 * @param lastName last name of staff member
	 * @param dob date of birth of staff member
	 * @param staffType researcher or lecturer
	 * @param employmentStatus permanent or contract
	 * @return the abstract staff object instance created
	 * @throws IllegalStateException if smartcard cannot be assigned
	 * @throws IllegalArgumentException if employmentStatus and/or staffType are not valid arguments
	 * @throws InstantiationException if smartcard number is not unique
	 */
	public Staff employStaff(String firstName, String lastName, Date dob,
							 String staffType, String employmentStatus) throws IllegalStateException, IllegalArgumentException, InstantiationException {
		try {
			Name name = new Name(firstName, lastName);
			SmartCard smartCard = new SmartCard(name, dob, employmentStatus);

			AbstractStaff thisStaff = AbstractStaff.getInstance(staffType, name, dob, employmentStatus);
			thisStaff.assignSmartCard(smartCard);
			allStaff.put(thisStaff.getStaffID(), thisStaff);
//		note that validation for staffType and employmentStatus exists in getInstance, validation for dob is in smartCard
			return thisStaff;
		} catch (IllegalStateException e) {
			System.out.println(e.getMessage());
			return null; //not sure how to avoid returning null
		} catch (IllegalArgumentException | InstantiationException f) {
			System.out.println(f.getMessage());
			return null; //not sure how to avoid returning null
		}
	}

	/**
	 * This method returns all staff that are employed by the university
	 * @return all staff in a collection
	 */
	public Collection<Staff> getAllStaff() {
		Collection<Staff> everyStaffObj = null;
		/*
		this way of iterating through a hashmap was taken from GeeksforGeeks:
        https://www.geeksforgeeks.org/how-to-iterate-hashmap-in-java/
        Original Author - GeeksforGeeks, "piyushkr2022"
        Modifying Author – Tahir Khan
		*/
		Iterator<Map.Entry<StaffID, AbstractStaff>> iterator = allStaff.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<StaffID, AbstractStaff> entry = iterator.next();
			everyStaffObj.add(entry.getValue());
			}
		return everyStaffObj;
	}

	/**
	 * This method removes the staff record associated with the given staff id. In effect, the staff is leaving the University.
	 * @param id the StaffID of the member to be terminated
	 * @throws IllegalArgumentException if the id is not in the allStaff hashmap
	 */
	public void terminateStaff(StaffID id) throws IllegalArgumentException {
		try {
			if (allStaff.containsKey(id)) {
				allStaff.remove(id);
				System.out.println(id + " has been removed.");
			} else {
				throw new IllegalArgumentException(id + " id does not exist");
			}
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}


}
