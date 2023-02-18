package staff.system.staff;

import java.util.*;

public class StaffID {

    private final char letterComponent;
    private final int numberComponent;
    private final String strRep;
    private static final Map<String, StaffID> ALL_IDS = new HashMap<>();

    private StaffID(char letterComponent, int numberComponent, String strRep) {
        this.letterComponent = letterComponent;
        this.numberComponent = numberComponent;
        this.strRep = strRep;
    }

    public static StaffID getInstance() {
        Random random = new Random();
        char letterComponent = (char) (random.nextInt(26) + 'a');
        int numberComponent = random.nextInt(1000);
        String strRep = null;

//            //todo: not sure if any of this is necessary seeing as the size of the university staff list is likely to be small
//            boolean keyAlreadyExists = true;
//            int counter = 0;
//
//            while (keyAlreadyExists && counter < 1000) { //regenerate Staff ID if it already exists in ALL_IDS
//                letterComponent = (char) (random.nextInt(26) + 'a');
//                numberComponent = random.nextInt(1000);
//                strRep = letterComponent + String.format("%03d", numberComponent);
//                keyAlreadyExists = ALL_IDS.containsKey(strRep);
//                counter++;
//            }
//
//            if (counter >= 1000) {
//                throw new InterruptedException("The staff ID could not be generated after 1000 tries.");
//            }

        StaffID staffID = ALL_IDS.get(strRep);
        if (staffID == null) {
            staffID = new StaffID(letterComponent, numberComponent, strRep); //unique instance if it's not already in hashmap
            ALL_IDS.put(strRep, staffID);
        } else {
            throw new IllegalArgumentException("ID has already been taken, please try again:  " + staffID);
        }
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
