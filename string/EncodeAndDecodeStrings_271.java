package string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EncodeAndDecodeStrings_271 {

    // Encodes a list of strings to a single string.
    // O(n) - time, O(1) - space
    public String encode(List<String> strs) {
        if (strs == null || strs.isEmpty()) {
            return Character.toString((char)258);
        }
        StringBuilder sb = new StringBuilder();
        String d = Character.toString((char)257);
        for (String str: strs) {
            sb.append(str).append(d);
        }
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }

    // Decodes a single string to a list of strings.
    // O(n) - time, O(1) - space
    public List<String> decode(String s) {
        String d = Character.toString((char)258);
        if (s.equals(d)) {
            return new ArrayList<>();
        }
        d = Character.toString((char)257);
        return Arrays.asList(s.split(d));
    }

    public static void main(String[] args) {
        EncodeAndDecodeStrings_271 s = new EncodeAndDecodeStrings_271();
        System.out.println(s.decode(s.encode(Arrays.asList("", "")))); //["", ""]
        System.out.println(s.decode(s.encode(new ArrayList<>()))); // []
        String encoded = s.encode(Arrays.asList("abc", "def", "ghi"));
        System.out.println(s.decode(encoded));
    }
}
