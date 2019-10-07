package string;

import java.util.ArrayList;
import java.util.List;

/**
 * Write a program that outputs the string representation of numbers from 1 to n.
 * But for multiples of three it should output “Fizz” instead of the number and for the multiples of five output
 * “Buzz”. For numbers which are multiples of both three and five output “FizzBuzz”.
 *
 * Example:
 * n = 15,
 *
 * Return:
 * [
 *     "1",
 *     "2",
 *     "Fizz",
 *     "4",
 *     "Buzz",
 *     "Fizz",
 *     "7",
 *     "8",
 *     "Fizz",
 *     "Buzz",
 *     "11",
 *     "Fizz",
 *     "13",
 *     "14",
 *     "FizzBuzz"
 * ]
 */
public class FizzBuzz_412 {

    public static void main(String[] args) {
        FizzBuzz_412 s = new FizzBuzz_412();
        System.out.println(s.fizzBuzz(15));
    }

    // O(n) - time, O(1) - space
    public List<String> fizzBuzz(int n) {
        List<String> results = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (i%3 == 0 && i%5 == 0) {
                results.add("FizzBuzz");
            } else if (i%3 == 0) {
                results.add("Fizz");
            } else if (i%5 == 0) {
                results.add("Buzz");
            } else {
                results.add(String.valueOf(i));
            }
        }
        return results;
    }
}
