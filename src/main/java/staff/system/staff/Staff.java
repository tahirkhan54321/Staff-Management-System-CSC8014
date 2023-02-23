package staff.system.staff;

import staff.system.smartcard.SmartCard;
import staff.system.supporting.Name;
import staff.system.supporting.Module;
import staff.system.supporting.Name;

import java.util.Set;

/**
 * staff.system.staff.Staff - interface to university staff.
 *
 * @author Rouaa Yassin Kassab
 * Copyright (C) 2023 Newcastle University, UK
 */ 

public interface Staff {

	//DO NOT remove or change the signature of any method. 
	//You can add more methods (e.g. setter methods) if your solution requires that
	
	/**
	 * Returns the staff ID. 
	 * All staff must have an ID
	 * @return the staff.system.staff.StaffID object
	 */
	StaffID getStaffID();

	/**
	 * Returns the smart card. 
	 * All staff must have a smart card
	 * @return the SmartCard object
	 */
	SmartCard getSmartCard();

	/**
	 * Returns the staff.system.staff.Staff employment status.
	 * a staff.system.staff.Staff can be either on Permanent or fixed contract
	 * @return a string (Permanent or fixed)
	 */
	String getStaffEmploymentStatus();
	
	/**
	 * Returns the staff.system.staff.Staff type.
	 * a staff.system.staff.Staff can be either a Lecturer or a Researcher
	 * @return a string (Lecturer or Researcher)
	 */
    String getStaffType();

	/**
	 * a method which lists the modules which are assigned to lecturer or lists the student names which are assigned to a researcher
	 * depending on staffType
	 * @return a string representation of the list
	 */
	String listString();

	Set<Module> listModules() throws IllegalAccessException;

	Set<Name> listStudents() throws IllegalAccessException;

	/**
	 * a method which tells us if a lecturer has been assigned enough modules (40 credits in total or more) or
	 * if a researcher has been assigned enough students (10 or more)
	 * @return true/false
	 */
	boolean isEnough();



    
}
