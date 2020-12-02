package string.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/** M
 * Design an algorithm to encode a list of strings to a string.
 * The encoded string is then sent over the network and is decoded back to the original list of strings.
 *
 * Machine 1 (sender) has the function:
 * string encode(vector<string> strs) {
 *   // ... your code
 *   return encoded_string;
 * }
 *
 * Machine 2 (receiver) has the function:
 * vector<string> decode(string s) {
 *   //... your code
 *   return strs;
 * }
 *
 * So Machine 1 does:
 * string encoded_string = encode(strs);
 *
 * and Machine 2 does:
 * vector<string> strs2 = decode(encoded_string);
 *
 * strs2 in Machine 2 should be the same as strs in Machine 1.
 * Implement the encode and decode methods.
 *
 * Note:
 *     The string may contain any possible characters out of 256 valid ascii characters.
 *     Your algorithm should be generalized enough to work on any possible characters.
 *     Do not use class member/global/static variables to store states. Your encode and decode algorithms should be stateless.
 *     Do not rely on any library method such as eval or serialize methods. You should implement your own encode/decode algorithm.
 */
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

    // Encoding used in HTTP v1.1 (https://leetcode.com/problems/encode-and-decode-strings/solution/)
}
