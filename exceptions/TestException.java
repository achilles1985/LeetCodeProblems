package exceptions;

public class TestException {

    public static void main(String[] args) {
        System.out.println(test1());
    }

    private static String test1() {
        try {
            System.out.println("Inside try");
            if (true) {
                throw new RuntimeException("Some Error");
            }
        } catch (Exception e) {
            System.out.println("Inside catch");
            throw e;
        }
        return "Success";
    }
}
