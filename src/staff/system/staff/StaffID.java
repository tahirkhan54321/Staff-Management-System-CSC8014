package staff.system.staff;

import java.util.*;

public class StaffID {

    private final char letterComponent;
    private final int numberComponent;
    private final String strRep;
    private static final Map<String, StaffID> ALL_IDS = new HashMap<>();

    /**
     * private constructor for StaffID
     * @param letterComponent random letter
     * @param numberComponent random number
     * @param strRep string representation of letter and number together
     */
    private StaffID(char letterComponent, int numberComponent, String strRep) {
        this.letterComponent = letterComponent;
        this.numberComponent = numberComponent;
        this.strRep = strRep;
    }

    /**
     * Factory to ensure uniqueness
     * @return unique staffID object
     */
    public static StaffID getInstance() {
        Random random = new Random();
        char letterComponent = (char) (random.nextInt(26) + 'a');
        int numberComponent = random.nextInt(1000);
        String strRep = letterComponent + String.format("%03d",numberComponent);

        StaffID staffID = ALL_IDS.get(strRep);
        if (staffID == null) {
            staffID = new StaffID(letterComponent, numberComponent, strRep); //unique instance if it's not already in hashmap
            ALL_IDS.put(strRep, staffID);
        } else {
            throw new IllegalArgumentException("ID has already been taken, please try again:  " + staffID);
        }
        return staffID;
    }

    /**
     * getter for the letter component of the staffID
     * @return character
     */
    public char getLetterComponent() {
        return letterComponent;
    }

    /**
     * getter for number component of the staffID
     * @return 3 digit number in string format
     */
    public String getNumberComponent() {
        return String.format("%03d", numberComponent);
    }

    /**
     * toString
     * @return string representation of the staffID
     */
    @Override
    public String toString() {
        return strRep;
    }

}
