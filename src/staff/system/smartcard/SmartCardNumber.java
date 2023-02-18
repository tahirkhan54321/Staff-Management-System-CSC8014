package staff.system.smartcard;

import java.util.*;

public class SmartCardNumber {

    private final String initials;
    private final int randomFiveDigitInteger;
    private final int yearOfIssuance;
    private final String strRep;
    private static final Map<String, SmartCardNumber> ALL_SMART_CARD_NUMBERS = new HashMap<>();

    private SmartCardNumber(String initials, int randomFiveDigitInteger, int yearOfIssuance, String strRep) {
        this.initials = initials;
        this.randomFiveDigitInteger = randomFiveDigitInteger;
        this.yearOfIssuance = yearOfIssuance;
        this.strRep = strRep;
    }

    public static SmartCardNumber getInstance(String initials) throws InstantiationException {

        Random random = new Random();
        Calendar today = Calendar.getInstance(); //default with no parameters in constructor gets today's date

        initials = initials.toUpperCase();
        int randomFourDigitInteger = random.nextInt(10000);
        int yearOfIssuance = today.get(Calendar.YEAR);
        String strRep = "";
//
//        boolean keyAlreadyExists = true;
//        int counter = 0;
//
//        while (keyAlreadyExists && counter < 10000) { //regenerate SmartCardNumber if it already exists in ALL_SMART_CARD_NUMBERS
//            randomFourDigitInteger = random.nextInt(10000);
//            strRep = initials + "-" + String.format("%05d",randomFourDigitInteger) + "-" + yearOfIssuance;
//            keyAlreadyExists = ALL_SMART_CARD_NUMBERS.containsKey(strRep);
//            counter++;
//        }
//
//        if (counter >= 10000) { //todo: should this throw an exception?
//            throw new InterruptedException("The SmartCard could not be generated after 10,000 tries.");
//        }

        SmartCardNumber smartCardNumber = ALL_SMART_CARD_NUMBERS.get(strRep);
        if (smartCardNumber == null) {
            smartCardNumber = new SmartCardNumber(initials, randomFourDigitInteger, yearOfIssuance, strRep);
            ALL_SMART_CARD_NUMBERS.put(strRep, smartCardNumber);
        } else {
            throw new InstantiationException("SmartCard Number is not unique: " + smartCardNumber);
        }
        return smartCardNumber;
    }

    @Override
    public String toString() { return strRep; }

}
