package utils;

/**
 * Inner non-static class can access to outer class's static and non-static methods and fields.
 *
 * Inner static class can access to outer class's static methods and fields.
 * To access non-static ones, inner class should create an instance of outer class.
 * Inner static class (as well as its fields) can be access from other top level class if it is public or package private.
 * If inner class is private non of its public fields or constructors can be accessed.
 *
 * Outer class can access inner non-static class's methods and fields only through its instance (new InnerNonStatic().print2()).
 * Outer class can access inner static class's methods and fields only through its class (InnerClass.staticField, InnerClass.staticMethod).
 */
public class InnerClassTest {

    private String name1;
    private static String name2;

    private void printOuter() {
        new InnerNonStatic().print2(); //can access Inner class method only via its instance

        new InnerStatic().print1(); //can access Inner class method only via its instance
        new InnerStatic().printStatic1();
    }

    private static void printOuterStatic() {
        new InnerStatic().printStatic1();
        new InnerStatic().print1();
    }

    private static class InnerStatic {
        int age1;
        static int age2;

        public InnerStatic() {
            this.age1 = 25;
        }

        private void print1() {
            System.out.println(name2);
            new InnerClassTest().printOuter();
        }

        private static void printStatic1() {
            System.out.println("From printStatic1");
            printOuterStatic();
        }

    }

    private class InnerNonStatic {
        private int age2;

        private void print2() {
            System.out.println(name1);
            printOuter();
            printOuterStatic();
        }
    }

    public static void main(String[] args) {
        new InnerClassTest().new InnerNonStatic().print2();
        int a1 = InnerStatic.age2;
        InnerStatic.printStatic1();

        int age1 = new InnerStatic().age1;
    }
}
