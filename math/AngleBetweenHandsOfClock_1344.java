package math;

/** M
 * Given two numbers, hour and minutes, return the smaller angle (in degrees) formed between the hour and the minute hand.
 * Answers within 10-5 of the actual value will be accepted as correct.
 *
 * Example 1:
 * Input: hour = 12, minutes = 30
 * Output: 165
 *
 * Example 2:
 * Input: hour = 3, minutes = 30
 * Output: 75
 *
 * Example 3:
 * Input: hour = 3, minutes = 15
 * Output: 7.5
 *
 * Constraints:
 *     1 <= hour <= 12
 *     0 <= minutes <= 59
 */
/*
    1. The tricky part is determining how the minute hand affects the position of the hour hand.
    2. Mat.min(diff, 360-diff)
 */
public class AngleBetweenHandsOfClock_1344 {

    public static void main(String[] args) {
        AngleBetweenHandsOfClock_1344 s = new AngleBetweenHandsOfClock_1344();
        System.out.println(s.angleClock(3,15)); //7.5
        System.out.println(s.angleClock(1,57)); //76.5
        System.out.println(s.angleClock(1,30)); //135.0

        s.addDigits(38);
    }

    public int addDigits(int num) {
        int sum = num;
        while (sum/10 > 0) {
            num = sum;
            sum = 0;
            while(num > 0) {
                sum += num%10;
                num = num/10;
            }
        }
        return sum;
    }

    public double angleClock(int hour, int minutes) {
        int anglePerMinute = 360/60;
        int anglePerHour = 360/12;

        double minuteAngle = minutes*anglePerMinute;
        double hourAngle = (hour % 12 + minutes/60.0) * anglePerHour;
        double diff = Math.abs(hourAngle - minuteAngle);

        return Math.min(diff, 360 - diff);
    }
}
