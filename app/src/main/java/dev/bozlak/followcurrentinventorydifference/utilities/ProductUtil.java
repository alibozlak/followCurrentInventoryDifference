package dev.bozlak.followcurrentinventorydifference.utilities;

public class ProductUtil {
    public static boolean isProductCodeValid(String productCode){
        if (productCode.length() != 7){
            return false;
        }
        if (!isProductCodeNumeric(productCode)){
            return false;
        }
        if (!isCorrectProductGroup(productCode.substring(0,2))){
            return false;
        }
        return true;
    }

    /**
     * Given productCode or productShortCode (shortCode) check if it is numeric
     * @param productCode
     * @return boolean
     */
    private static boolean isProductCodeNumeric(String productCode){
        for (byte i = 0; i < productCode.length(); i++){
            byte iThChar = (byte) productCode.charAt(i);
            if (iThChar < 48 || iThChar > 57){
                return false;
            }
        }
        return true;
    }

    private static boolean isCorrectProductGroup(String groupCode){
        byte firstChar = (byte) groupCode.charAt(0);
        byte secondChar = (byte) groupCode.charAt(1);
        if (firstChar == 48 && secondChar != 48){
            return true;
        }
        if (firstChar == 49 && secondChar != 56){
            return true;
        }
        if (firstChar == 57 && (secondChar == 56 || secondChar == 57)){
            return true;
        }
        return false;
    }

    public static boolean isShortCodeValid(String shortCode){
        int shortCodeLength = shortCode.length();
        if (shortCodeLength == 2 || shortCodeLength == 3){
            if (isProductCodeNumeric(shortCode)){
                return true;
            }
        }
        return false;
    }
}
