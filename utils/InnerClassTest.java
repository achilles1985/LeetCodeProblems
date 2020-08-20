package utils;

/**
 * Inner non-static class can access to outer class's static and non-static methods and fields.
 * Inner non-static cannot have static fields or methods.
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

    private void printOuterNonStatic() {
        new InnerNonStatic().innerNonStatic(); //can access Inner class method only via its instance

        new InnerStatic().innerNonStatic(); //can access Inner class method only via its instance
        new InnerStatic().innerStatic();
    }

    private static void printOuterStatic() {
        new InnerStatic().innerStatic();
        new InnerStatic().innerNonStatic();
    }

    private static class InnerStatic {
        int age1;
        static int age2;

        InnerStatic() {
            this.age1 = 25;
        }

        void innerNonStatic() {
            System.out.println(name2);
            new InnerClassTest().printOuterNonStatic();
        }

        static void innerStatic() {
            System.out.println("From printStatic1");
            printOuterStatic();
        }
    }

    private class InnerNonStatic {
        int age2;
        //static int age3; cannot have static fields

        void innerNonStatic() {
            System.out.println(name1);
            printOuterNonStatic();
            printOuterStatic();
        }

        //static void m2() {} cannot have static methods
    }

    public static void main(String[] args) {
        new InnerClassTest().new InnerNonStatic().innerNonStatic();
        int a1 = InnerStatic.age2;
        InnerStatic.innerStatic();

        int age1 = new InnerStatic().age1;
    }
}
