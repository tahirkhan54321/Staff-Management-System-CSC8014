package staff.system.staff;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class StaffID {

    // use a counter for the number component and increment it
    // https://stackoverflow.com/questions/43200496/how-to-generate-employee-id
    // if we hit 999 then increment the letter component and set counter to 000 again
    private char letterComponent;
    private int numberComponent;
    private static Set<String> allIds = new HashSet<>();
    private static final int MAX_SET_SIZE = 10 ^ 3 * 26;


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

    public StaffID() {
        boolean endLoop = false;
        while (!endLoop) {
            if (allIds.size() == MAX_SET_SIZE) {
                System.out.println("The set of possible IDs is full. Cannot allocate a new Staff ID");
                endLoop = true;
            }
            Random random = new Random();
            this.letterComponent = (char) (random.nextInt(26) + 'a');
            /*
            this way of getting a random letter of the alphabet comes from stack overflow:
            https://stackoverflow.com/questions/2626835/is-there-functionality-to-generate-a-random-character-in-java
            Original Author - Stack Overflow, "dogbane"
            Modifying Author – Tahir Khan
             */
            random = new Random();
            this.numberComponent = random.nextInt(1000);
            String compositeID = this.letterComponent + String.format("%03d", this.numberComponent);
            if (!allIds.add(compositeID)) {
                //go back to beginning of while loop
            } else {
                allIds.add(compositeID);
                endLoop = true;
            }
        }
    }

    //TODO create factory for getInstance

    public char getLetterComponent() {
        return letterComponent;
    }

    public String getNumberComponent() {
        return String.format("%03d", numberComponent);
    }

    @Override
    public String toString() {
        return letterComponent + String.format("%03d", numberComponent);
    }
}
