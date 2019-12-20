package design;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**E
 * Design a logger system that receive stream of messages along with its timestamps, each message should be printed
 * if and only if it is not printed in the last 10 seconds.
 *
 * Given a message and a timestamp (in seconds granularity), return true if the message should be printed in the
 * given timestamp, otherwise returns false.
 *
 * It is possible that several messages arrive roughly at the same time.
 */
public class LoggerRateLimiter_359 {

    private final Map<String, Integer> messages;
    /** Initialize your data structure here. */
    public LoggerRateLimiter_359() {
        messages = new HashMap<>();
    }

    /** Returns true if the message should be printed in the given timestamp, otherwise returns false.
     If this method returns false, the message will not be printed.
     The timestamp is in seconds granularity. */
    // O(1) - time, O(n) - space
    public boolean shouldPrintMessage(int timestamp, String message) {
        if (!messages.containsKey(message)) {
            messages.put(message, timestamp);
            return true;
        } else if (timestamp - messages.get(message) >= 10) {
            messages.put(message, timestamp);
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        LoggerRateLimiter_359 logger = new LoggerRateLimiter_359();
        // logging string "foo" at timestamp 1
        System.out.println(logger.shouldPrintMessage(1, "foo")); // true;

        // logging string "bar" at timestamp 2
        System.out.println(logger.shouldPrintMessage(2,"bar")); //true;

        // logging string "foo" at timestamp 3
        System.out.println(logger.shouldPrintMessage(3,"foo")); //false;

        // logging string "bar" at timestamp 8
        System.out.println(logger.shouldPrintMessage(8,"bar")); // false;

        // logging string "foo" at timestamp 10
        System.out.println(logger.shouldPrintMessage(10,"foo")); // false;

        // logging string "foo" at timestamp 11
        System.out.println(logger.shouldPrintMessage(11,"foo")); // true;
    }
}
