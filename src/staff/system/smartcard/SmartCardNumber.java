package staff.system.smartcard;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class SmartCardNumber {

    private String firstComponent;
    private int secondComponent;
    private int thirdComponent;
    private static Set<String> allSmartCardNumbers = new HashSet<>();

    public SmartCardNumber(String firstComponent) {
        //TODO: does this need a max set size?
        boolean endLoop = false;
        while (!endLoop) {
            Random random = new Random();
            Calendar rightNow = Calendar.getInstance();
            this.firstComponent = firstComponent;
            this.secondComponent = random.nextInt(100);
            this.thirdComponent = rightNow.get(Calendar.YEAR);
            String compositeSmartCardNumber = this.firstComponent + "-" + String.format("%02d",this.secondComponent) + "-"
                    + this.thirdComponent;
            if (!allSmartCardNumbers.add(compositeSmartCardNumber)) {
                //go back to beginning of while loop
            } else {
                allSmartCardNumbers.add(compositeSmartCardNumber);
                endLoop = true;
            }
        }
    }

    //TODO create factory for getInstance


}
