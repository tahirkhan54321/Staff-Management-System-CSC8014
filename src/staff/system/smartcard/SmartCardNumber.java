package staff.system.smartcard;

import java.util.*;

public class SmartCardNumber {

    private final String initials;
    private final int randomTwoDigitInteger; //todo: should this be a random 2 digit int at this stage?
    private final int currentYear; //todo: should this be the current year at this stage?
    private final String strRep;
    private static final Map<String, SmartCardNumber> ALL_SMART_CARD_NUMBERS = new HashMap<>();

    private SmartCardNumber(String initials, int randomTwoDigitInteger, int currentYear, String strRep) {
        this.initials = initials;
        this.randomTwoDigitInteger = randomTwoDigitInteger;
        this.currentYear = currentYear;
        this.strRep = strRep;
    }

    public static SmartCardNumber getInstance(String initials, int randomTwoDigitInteger, int currentYear) {
        /*todo: could I create a while loop which iterates a two digit number, puts it in the strRep then
            tries to put that in the hashmap. If it fails then try again.
         */
        String strRep = initials + "-" + String.format("%02d",randomTwoDigitInteger) + "-" + currentYear;
        SmartCardNumber smartCardNumber = ALL_SMART_CARD_NUMBERS.get(strRep);
        if (smartCardNumber == null) {
            smartCardNumber = new SmartCardNumber(initials, randomTwoDigitInteger, currentYear, strRep);
            ALL_SMART_CARD_NUMBERS.put(strRep, smartCardNumber);
        } else {
            throw new IllegalArgumentException("Smartcard Number is not unique: " + smartCardNumber);
        }
        return smartCardNumber;
    }

    @Override
    public String toString() { return strRep; }

//    public SmartCardNumber(String firstComponent) {
//        //TODO: does this need a max set size?
//        this.firstComponent = firstComponent;
//        boolean endLoop = false;
//        while (!endLoop) {
//            Random random = new Random();
//            Calendar rightNow = Calendar.getInstance();
//            this.secondComponent = random.nextInt(100);
//            this.thirdComponent = rightNow.get(Calendar.YEAR);
//            String compositeSmartCardNumber = this.firstComponent + "-" + String.format("%02d",this.secondComponent) + "-"
//                    + this.thirdComponent;
//            if (!allSmartCardNumbers.add(compositeSmartCardNumber)) {
//                //go back to beginning of while loop
//            } else {
//                allSmartCardNumbers.add(compositeSmartCardNumber);
//                endLoop = true;
//            }
//        }
//    }



}
