package utils;

public class ExceptionsTest {

    public static void main(String[] args) {
        ExceptionsTest s = new ExceptionsTest();
        //s.throwsFirst();
        s.handleException();
    }

    private void throwsFirst() {
        try {
            consumeMethod();
        } catch (Exception e) {
            System.out.println("Inside catch");
            e.printStackTrace();
        } finally {
            System.out.println("Inside finally");
        }
    }

    private void handleException() {
        try {
            throwsIllegalArgumentException();
        } catch (NullPointerException e) {
            System.out.println("Inside catch");
            e.printStackTrace();
        } finally {
            System.out.println("Inside finally");
        }
    }

    private void consumeMethod() {
        throw new RuntimeException();
    }

    private void throwsIllegalArgumentException() {
        throw new IllegalArgumentException();
    }

}
