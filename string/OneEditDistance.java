package string;

/** E
 Given two strings S and T, determine if they are both one edit distance apart.

 Example
 pale, ple -) true
 pales, pale -) true
 pale, bale -) true
 pale, bae -) false
 */
public class OneEditDistance {

    public static void main(String[] args) {
        OneEditDistance s = new OneEditDistance();
        System.out.println(s.isOneEditDistance("pale", "ple")); //true
        System.out.println(s.isOneEditDistance("pales", "pale")); //true
        System.out.println(s.isOneEditDistance("pale", "bale")); //true
        System.out.println(s.isOneEditDistance("pale", "bae")); //false
    }

    // O(n) - time, O(1) - space
    public boolean isOneEditDistance(String s1, String s2) {
        if (s1.length() == s2.length()) {
            return oneEditDistance(s1, s2);
        } else if (s1.length() + 1 == s2.length()) {
             return oneInsertDistance(s1, s2);
        } else if (s1.length() - 1 == s2.length()) {
            return oneInsertDistance(s2, s1);
        }
        return false;
    }

    private boolean oneEditDistance(String s1, String s2) {
        int counter = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                counter++;
            }
        }
        return counter <= 1;
    }

    private boolean oneInsertDistance(String s1, String s2) {
        int i = 0;
        int j = 0 ;
        while (i < s1.length() && j < s2.length()) {
            if (s1.charAt(i) == s2.charAt(j)) {
                i++;
            } else {
                if (i != j) {
                    return false;
                }
            }
            j++;
        }
        return true;
    }
}
