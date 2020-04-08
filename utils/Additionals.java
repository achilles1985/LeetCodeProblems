package utils;

public class Additionals {

    public static void main(String[] args) {
        System.out.println(maskify("4556-3646-0793-5616"));
        System.out.println(maskify("4556364607935616"));
        System.out.println(maskify("64607935616"));
        System.out.println(maskify("A1234567BCDEFG89HI"));
        System.out.println(maskify("ABCD-EFGH-IJKLM-NOPQ"));

        System.out.println(getOrdinalEn(1));
        System.out.println(getOrdinalEn(11));
        System.out.println(getOrdinalEn(23));
        System.out.println(getOrdinalEn(44));
    }

    public static String getOrdinalEn(int number) {
        if (number == 0) {
            return "0";
        }
        int lastTwoDigit = number % 100;
        int lastOneDigit = number % 10;
        if(lastOneDigit == 1 && lastTwoDigit != 11) {
            return number + "st";
        }
        else if(lastOneDigit == 2 && lastTwoDigit != 12) {
            return number + "nd";
        }
        else if(lastOneDigit == 3 && lastTwoDigit != 13) {
            return number + "rd";
        } else {
            return number + "th";
        }
    }

    public static String maskify(String creditCardNumber) {
        if (creditCardNumber.length() <= 6) {
            return creditCardNumber;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(creditCardNumber.charAt(0));
        for (int i = 1; i < creditCardNumber.length() - 4; i++) {
            char symbol = creditCardNumber.charAt(i);
            if (Character.isDigit(symbol)) {
                sb.append('#');
            } else {
                sb.append(symbol);
            }
        }
        sb.append(creditCardNumber.substring(creditCardNumber.length()-4));
        return sb.toString();
    }
}
