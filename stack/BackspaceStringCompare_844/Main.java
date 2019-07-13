package stack.BackspaceStringCompare_844;

public class Main {

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println("ab#c == ad#c " + s.backspaceCompare("ab#c", "ad#c"));
        System.out.println("ab## == c#d# " + s.backspaceCompare("ab##", "c#d#"));
        System.out.println("a##c == #a#c " + s.backspaceCompare("a##c", "#a#c"));
        System.out.println("a#c == b " + s.backspaceCompare("a#c", "b"));
    }
}
