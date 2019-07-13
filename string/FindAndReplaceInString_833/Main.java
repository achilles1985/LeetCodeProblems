package string.FindAndReplaceInString_833;

public class Main {

    public static void main(String[] args) {
        Solution s = new Solution();
        String str;
        StringBuilder sb = new StringBuilder();

        System.out.println(s.findReplaceString2("abcd", new int[] {0, 2}, new String[] {"a", "cd"}, new String[] {"eee", "ffff"})); // eeebffff
        System.out.println(s.findReplaceString("abcd", new int[] {0, 2}, new String[] {"ab", "ec"}, new String[] {"eee", "ffff"})); // eeecd
    }
}
