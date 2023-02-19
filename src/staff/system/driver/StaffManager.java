package staff.system.driver;

import staff.system.staff.AbstractStaff;
import staff.system.Staff;
import staff.system.staff.StaffID;
import staff.system.supporting.Module;
import staff.system.supporting.Name;
import staff.system.smartcard.SmartCard;
import staff.system.staff.lecturer.Lecturer;
import staff.system.staff.researcher.Researcher;

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
		return readModules;
	}


	public Set<Name> readInStudents(String path) throws IOException {
		Set<Name> readStudents = new HashSet<>();
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

		System.out.println(readStudents.size() + " students have been added to the set.");
		return readStudents;
	}


	public int noOfStaff(String type) {
		if(!(type.equalsIgnoreCase(AbstractStaff.LECTURER) || type.equalsIgnoreCase(AbstractStaff.RESEARCHER))) {
			throw new IllegalArgumentException(type + " is not a valid staff type");
		}
		int counter = 0;
		Iterator<Map.Entry<StaffID, AbstractStaff>> iterator = allStaff.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<StaffID, AbstractStaff> entry = iterator.next();
			if (entry.getValue().getStaffType().equalsIgnoreCase(type)) {
				counter++;
			}
		}
		return counter;
	}


	public boolean addData(StaffID id, Set<Module> modules, Set<Name> students) {

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

		return false;
	}


	public Staff employStaff(String firstName, String lastName, Date dob, String staffType, String employmentStatus) throws InstantiationException {
		Name name = new Name(firstName, lastName);
		SmartCard smartCard = new SmartCard(name, dob, employmentStatus);

		AbstractStaff thisStaff = AbstractStaff.getInstance(staffType, name, dob, employmentStatus);
		thisStaff.assignSmartCard(smartCard);
		allStaff.put(thisStaff.getStaffID(), thisStaff);

//		note that validation for staffType and employmentStatus exists in getInstance, validation for dob is in smartCard
		return thisStaff;
	}


	public Collection<Staff> getAllStaff() {
		Collection<Staff> everyStaffObj = null;
		Iterator<Map.Entry<StaffID, AbstractStaff>> iterator = allStaff.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<StaffID, AbstractStaff> entry = iterator.next();
			everyStaffObj.add(entry.getValue());
			}
		return everyStaffObj;
	}


	public void terminateStaff(StaffID id) {
		if (allStaff.containsKey(id)) {
			allStaff.remove(id);
			System.out.println(id + " has been removed.");
		} else {
			throw new IllegalArgumentException(id + " id does not exist");
		}
	}


}
