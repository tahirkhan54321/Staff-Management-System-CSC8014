package staff.system.staff;

import java.util.*;

public class StaffID {

    // use a counter for the number component and increment it
    // https://stackoverflow.com/questions/43200496/how-to-generate-employee-id
    // if we hit 999 then increment the letter component and set counter to 000 again
    private final char letterComponent;
    private final int numberComponent;
    private final String strRep;
    private static final Map<String, StaffID> ALL_IDS = new HashMap<>();
    //private static final int MAX_SET_SIZE = 10 ^ 3 * 26;

    private StaffID(char letterComponent, int numberComponent, String strRep) {
        this.letterComponent = letterComponent;
        this.numberComponent = numberComponent;
        this.strRep = strRep;
    }

    public static StaffID getInstance() { //todo: get rid of parameters and generate random components
        Random random = new Random();
        char letterComponent = (char) (random.nextInt(26) + 'a');
        int numberComponent = random.nextInt(1000);
        String strRep = letterComponent + String.format("%03d", numberComponent); //generate once

        StaffID staffID = ALL_IDS.get(strRep);
        if (staffID == null) {
            staffID = new StaffID(letterComponent, numberComponent, strRep); //unique instance if it's not already in hashmap
            ALL_IDS.put(strRep, staffID);
        } else {
            throw new IllegalArgumentException("ID has already been taken: " + staffID);
        }
        /*
        todo: if it is in the hashmap then we return the StaffID object which is already in the Hashmap
            How do we want to deal with this? What does it mean for the rest of the program?
            Should I just find out in testing? I don't think I understand the implications of this yet
            Should this be dealt with at the point where getInstance is called?
            Will the illegalargumentexception suffice? How will this work in practice?
                    */
        return staffID;
    }

    public char getLetterComponent() {
        return letterComponent;
    }

    public String getNumberComponent() {
        return String.format("%03d", numberComponent);
    }

    @Override
    public String toString() {
        return strRep;
    }

//    public StaffID() throws InstantiationException {
//        if (numberCounter == 999 && letterCounter == 'z') {
//            System.out.println("We have run out of unique IDs to assign");
//            throw new InstantiationException("We have run out of unique IDs to assign");
//            // TODO: should I do this or should i use assert condition (2.1, 7)
//            //  https://stackoverflow.com/questions/77127/when-to-throw-an-exception
//        }
//        else if (numberCounter == 999) {
//            numberCounter = 000;
//            letterCounter++;
//        } else {
//            numberCounter++;
//        }
//        this.letterComponent = letterCounter;
//        this.numberComponent = numberCounter;
//    }

//    public StaffID() {
//        boolean endLoop = false;
//        while (!endLoop) {
//            if (allIds.size() == MAX_SET_SIZE) {
//                System.out.println("The set of possible IDs is full. Cannot allocate a new Staff ID");
//                endLoop = true;
//            }
//            Random random = new Random();
//            this.letterComponent = (char) (random.nextInt(26) + 'a');
//            /*
//            this way of getting a random letter of the alphabet comes from stack overflow:
//            https://stackoverflow.com/questions/2626835/is-there-functionality-to-generate-a-random-character-in-java
//            Original Author - Stack Overflow, "dogbane"
//            Modifying Author – Tahir Khan
//             */
//            random = new Random();
//            this.numberComponent = random.nextInt(1000);
//            String compositeID = this.letterComponent + String.format("%03d", this.numberComponent);
//            if (!allIds.add(compositeID)) {
//                //go back to beginning of while loop
//            } else {
//                allIds.add(compositeID);
//                endLoop = true;
//            }
//        }
//    }



}
