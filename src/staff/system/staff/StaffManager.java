package staff.system.staff;

import staff.system.Module;
import staff.system.Name;
import staff.system.smartcard.SmartCard;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;


public class StaffManager {

	//you can add attributes and other methods if needed. 
	//you can throw an exception if needed
	private Set<Module> allModules = new HashSet<>(); // todo: should this be a singleton? Should it be static?
	private Set<Name> allStudents = new HashSet<>(); // todo: should this be a singleton? Should it be static?
	private Map<StaffID, AbstractStaff> allStaff = new HashMap<>(); // todo: should this be a singleton? I do need to be able to add/remove from this. Should it be static?

	public Set<Module> readInModules(String path) throws IOException {
		int counter = 0;
		Set<Module> readModules = new HashSet<>();
		Scanner scanner = new Scanner(Paths.get(path));

		while (scanner.hasNext()) {
			String line = scanner.nextLine();
			String partsOfModule[] = line.split(",");

			String moduleCode = partsOfModule[0];
			String moduleName = partsOfModule[1]; //todo: remove the space?
			int semester = Integer.valueOf(partsOfModule[2]);
			int credits = Integer.valueOf(partsOfModule[3]);
			Module module = new Module(moduleCode, moduleName, semester, credits);

			readModules.add(module);
			counter++;
		}
		allModules.addAll(readModules);

		System.out.println(counter + " modules have been added to the set.");
		return readModules;
	}


	public Set<Name> readInStudents(String path) throws IOException {
		int counter = 0;
		Set<Name> readStudents = new HashSet<>();
		Scanner scanner = new Scanner(Paths.get(path));

		while (scanner.hasNext()) {
			String line = scanner.nextLine();
			String partsOfName[] = line.split(" ");

			String firstName = partsOfName[0];
			String secondName = partsOfName[1];

			Name name = new Name(firstName, secondName);
			readStudents.add(name);
			counter++;
		}
		allStudents.addAll(readStudents);

		System.out.println(counter + " students have been added to the set.");
		//add your code here. Do NOT change the method signature
		return readStudents;
	}


	public int noOfStaff(String type) {
		int counter = 0;
		Iterator<Map.Entry<StaffID, AbstractStaff>> iterator = allStaff.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<StaffID, AbstractStaff> entry = iterator.next();
			if (entry.getValue().getStaffType().equals(type)) {
				counter++;
			}
		}
		//add your code here. Do NOT change the method signature
		return counter;
	}


	public boolean addData(StaffID id, Set<Module> modules, Set<Name> students) {
		/*
		todo: add conditions for sets of modules and sets of names - use enough modules, enough students
			also make sure that we're adding data rather than setting data - I don't think this is within scope.
			We're adding module or student data to the staff rather than adding modules or adding students
		 */
		boolean modulesExist = false;
		boolean studentsExist = false;
		String staffType = allStaff.get(id).getStaffType();
		//note that a set cannot contain a null value so this should work.

		if (!allStaff.containsKey(id)) {
			throw new IllegalArgumentException("Staff ID does not exist: " + id);
		}
		if(allModules.containsAll(modules)) {
			modulesExist = true;
		}
		if (allStudents.containsAll(students)) {
			studentsExist = true;
		}
		if (!(allModules.containsAll(modules) || allStudents.containsAll(students))) {
			throw new IllegalArgumentException("Modules and Students parameters don't exist in the list.");
		}
		if (!(staffType.equalsIgnoreCase("Lecturer") || staffType.equalsIgnoreCase("Researcher"))) {
			throw new IllegalArgumentException("Staff type isn't Lecturer or Researcher for ID: " + id);
		}

		if (staffType.equalsIgnoreCase("Lecturer") && modulesExist) {
			Lecturer lecturer = (Lecturer) allStaff.get(id);
			lecturer.setModules(modules);
			allStaff.put(id, lecturer);
			return true;
		}
		if (staffType.equalsIgnoreCase("Researcher") && studentsExist) {
				Researcher researcher = (Researcher) allStaff.get(id);
				researcher.setStudents(students);
				allStaff.put(id, researcher);
				return true;
		}

		/* todo: I need to:
			1) cover cases when type is inputted incorrectly - this shouldn't be possible if we're using an existing StaffID
			2) cover cases where sets do not contain all - see what +modules and +students
			3) add validation for get(id)
			4) make sure the return types make sense
		 */
		//add your code here. Do NOT change the method signature
		return false;
	}


	public Staff employStaff(String firstName, String lastName, Date dob, String staffType, String employmentStatus) throws InstantiationException {
		Name name = new Name(firstName, lastName);
		Staff thisStaff = null;
		SmartCard smartCard = new SmartCard(name, dob);
		smartCard.setExpiryDate(employmentStatus);

		if (staffType.equalsIgnoreCase("Lecturer")) {
			Lecturer lecturer = new Lecturer(name, dob, staffType, employmentStatus);
			lecturer.assignSmartCard(smartCard);
			allStaff.put(lecturer.getStaffID(), lecturer);
			thisStaff = (Staff) lecturer;
		} else if (staffType.equalsIgnoreCase("Researcher")) {
			Researcher researcher = new Researcher(name, dob, staffType, employmentStatus);
			allStaff.put(researcher.getStaffID(), researcher);
			researcher.assignSmartCard(smartCard);
			thisStaff = (Staff) researcher;
		} else {
			throw new IllegalArgumentException("The staffType is not Lecturer or Researcher: " + staffType);
		}
		//add your code here. Do NOT change the method signature
		return thisStaff;
	}


	public Collection<Staff> getAllStaff() {
		Collection<Staff> everyStaffObj = null;
		Iterator<Map.Entry<StaffID, AbstractStaff>> iterator = allStaff.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<StaffID, AbstractStaff> entry = iterator.next();
			everyStaffObj.add(entry.getValue());
			}
		//add your code here. Do NOT change the method signature
		return everyStaffObj;
	}


	public void terminateStaff(StaffID id) {
		if (allStaff.containsKey(id)) {
			allStaff.remove(id);
			System.out.println(id + " has been removed.");
		} else {
			throw new IllegalArgumentException(id + " id does not exist");
		}
		//add your code here. Do NOT change the method signature
	}





}
