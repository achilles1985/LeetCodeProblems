package string;

/*
Questions:
    1. if number of consecutive characters less then limit?
    2. If limit is 0?
 */
public class CharacterCounting {

    public static void main(String[] args) {
        CharacterCounting s = new CharacterCounting();
        System.out.println(s.removeRedundant("aabbbcccaaa", 2)); // aabbccaa
        System.out.println(s.removeRedundant("aabbbcccaaa", 1)); //abca
        System.out.println(s.removeRedundant("aabbbcccaaa", 3)); //aabbbcccaaa
        System.out.println(s.removeRedundant("aabbbcccaaa", 5)); //"aabbbcccaaa"
    }

/*    private String removeRedundant2(String input, int limit) {
        char[] arr = input.toCharArray();
        int anchor = 0, write = 0;
        for (int i = 0; i < arr.length; i++) {
            int diff = i - anchor;
            if (arr[i] == arr[anchor] && diff == limit) {
                write = i;
            } else if (arr[i] != arr[anchor] && diff > limit) {
                arr[write++] = arr[i];
            } else if (arr[i] != arr[anchor] && diff <= limit) {
                anchor = i;
                write = anchor;
            }
        }
        return new String(arr);
    }*/

    // O(n) - time, space
    private String removeRedundant(String input, int limit) {
        StringBuilder sb = new StringBuilder();
        sb.append(input.charAt(0));
        int count = 1;
        for (int i = 1; i < input.length(); i++) {
            if (input.charAt(i) == input.charAt(i - 1) && count < limit) {
                sb.append(input.charAt(i));
                count++;
            } else if (input.charAt(i) == input.charAt(i - 1)) {
                continue;
            } else {
                sb.append(input.charAt(i));
                count = 1;
            }
        }

        return sb.toString();
    }

}
