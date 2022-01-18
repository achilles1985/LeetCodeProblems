package systemdesing;

public class ShortUrl {

    public static void main(String[] args) {
        System.out.println(toBase64(1));
        System.out.println(toBase64(2));
        System.out.println(toBase64(100));
        System.out.println(toBase64(1123789));
        System.out.println(toBase64(1123790));
        System.out.println(toBase64(1123791));
        System.out.println(toBase64(1123792));
        System.out.println(toBase64(1123793));
        System.out.println(toBase64(113379681));
    }

    private static String toBase64(int id) {
        String result = "";
        String range = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        while (id > 0) {
            result += range.charAt(id%62);
            id /= 62;
        }

        return result;
    }
}
