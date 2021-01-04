package string;

import java.util.HashMap;
import java.util.Map;

/**
 * H
 * Convert a non-negative integer to its english words representation. Given input is guaranteed to be less than 231 - 1.
 * <p>
 * Example 1:
 * Input: 123
 * Output: "One Hundred Twenty Three"
 * <p>
 * Example 2:
 * Input: 12345
 * Output: "Twelve Thousand Three Hundred Forty Five"
 * <p>
 * Example 3:
 * Input: 1234567
 * Output: "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
 * <p>
 * Example 4:
 * Input: 1234567891
 * Output: "One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven Thousand Eight Hundred Ninety One"
 */
/*
    Questions:
    1. max number? positive?
 */
public class IntegerToEnglishWords_273 {

    public static void main(String[] args) {
        IntegerToEnglishWords_273 s = new IntegerToEnglishWords_273();
        System.out.println(s.numberToWords(12346789));

        System.out.println(s.numberToWords2(40345));
        System.out.println(s.numberToWords2(1_123_456_789));
        System.out.println(s.numberToWords2(32));
        System.out.println(s.numberToWords2(123));
        System.out.println(s.numberToWords2(123_456));
        System.out.println(s.numberToWords(123_456_789));
    }

    // O(d) - time, O(d/3) - space
    public String numberToWords(int num) {
        Map<Integer, String> map = buildNumbersMap();
        return numberToWords(num, map).trim();
    }

    private String numberToWords(int num, Map<Integer, String> map) {
        if (num == 0) {
            return "Zero";
        }
        StringBuilder result = new StringBuilder();
        int base;
        // billion
        base = num / 1_000_000_000;
        num = num % 1_000_000_000;
        if (base > 0) {
            result.append(numberToWords(base, map));
            result.append(" Billion");
        }
        // million
        base = num / 1_000_000;
        num = num % 1_000_000;
        if (base > 0) {
            result.append(numberToWords(base, map));
            result.append(" Million");
        }
        // thousand
        base = num / 1000;
        num = num % 1000;
        if (base > 0) {
            result.append(numberToWords(base, map));
            result.append(" Thousand");
        }
        // hunderd
        base = num / 100;
        num = num % 100;
        if (base > 0) {
            result.append(numberToWords(base, map));
            result.append(" Hundred");
        }
        // tens
        if (num >= 20) {
            base = num / 10;
            num = num % 10;
            result.append(" ");
            result.append(map.get(base * 10));
        }
        // ones
        if (num > 0) {
            result.append(" ");
            result.append(map.get(num));
        }
        return result.toString();
    }

    public String numberToWords2(int num) {
        Map<Integer, String> map = buildNumbersMap();
        Map<Integer, String> words = buildWordMap();
        if (num == 0) {
            return "Zero";
        }
        StringBuilder sb = new StringBuilder();
        splitter(sb, num, 0, map, words);

        return sb.toString();
    }

    private void splitter(StringBuilder sb, int num, int index, Map<Integer, String> map, Map<Integer, String> words) {
        if (num > 0) {
            splitter(sb, num/1000, index + 1, map, words);
        }
        int curNumber = num%1000;
        if (curNumber > 0) {
            parser(sb, curNumber, map, words);
            sb.append(words.getOrDefault(index, ""));
        }
    }

    private void parser(StringBuilder sb, int number, Map<Integer, String> map, Map<Integer, String> words) {
        if (number == 0) {
            return;
        }
        if (sb.length() != 0) {
            sb.append(" ");
        }
        if (number < 20) {
            sb.append(map.get(number));
        } else if (number < 100) {
            sb.append(map.get(number/10 * 10));
            parser(sb, number%10, map, words);
        } else if (number < 1000) {
            sb.append(map.get(number/100));
            sb.append(" Hundred");
            parser(sb,number%100, map, words);
        }
    }

    private Map<Integer, String> buildWordMap() {
        Map<Integer, String> words = new HashMap<>();
        words.put(1, " Thousand");
        words.put(2, " Million");
        words.put(3, " Billion");
        return words;
    }

    private Map<Integer, String> buildNumbersMap() {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "One");
        map.put(2, "Two");
        map.put(3, "Three");
        map.put(4, "Four");
        map.put(5, "Five");
        map.put(6, "Six");
        map.put(7, "Seven");
        map.put(8, "Eight");
        map.put(9, "Nine");
        map.put(10, "Ten");
        map.put(11, "Eleven");
        map.put(12, "Twelve");
        map.put(13, "Thirteen");
        map.put(14, "Fourteen");
        map.put(15, "Fifteen");
        map.put(16, "Sixteen");
        map.put(17, "Seventeen");
        map.put(18, "Eighteen");
        map.put(19, "Nineteen");
        map.put(20, "Twenty");
        map.put(30, "Thirty");
        map.put(40, "Forty");
        map.put(50, "Fifty");
        map.put(60, "Sixty");
        map.put(70, "Seventy");
        map.put(80, "Eighty");
        map.put(90, "Ninety");

        return map;
    }
}
