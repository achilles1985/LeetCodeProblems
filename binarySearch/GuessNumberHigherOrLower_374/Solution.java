package binarySearch.GuessNumberHigherOrLower_374;

/**
 * 374. Guess Number Higher or Lower
 * https://leetcode.com/problems/guess-number-higher-or-lower/
 * We are playing the Guess Game. The game is as follows:
 * I pick a number from 1 to n. You have to guess which number I picked.
 * Every time you guess wrong, I'll tell you whether the number is higher or lower.
 * You call a pre-defined API guess(int num) which returns 3 possible results (-1, 1, or 0):
 * -1 : My number is lower
 * 1 : My number is higher
 * 0 : Congrats! You got it!
 * Example:
 * n = 10, I pick 6.
 * Return 6.
 */

/* The guess API is defined in the parent class GuessGame.
   @param num, your guess
   @return -1 if my number is lower, 1 if my number is higher, otherwise return 0
      int guess(int num); */
public class Solution {

    public int guessNumber(int i) {
        return guessNumber(i, 0, 200);
    }

    public int guessNumber(int number, int start, int end) {
        if (start > end) {
            return -2;
        }
        int mid = (end + start)/2;
        if (guess(mid) == 0) {
            return 0;
        }
        else if (guess(mid) < 0) {
            return guessNumber(number, mid + 1, end);
        } else {
            return guessNumber(number, start, mid - 1);
        }
    }


    private int guess(int number) {
        if (number < 10) {
            return -1;
        } else if (number > 10) {
            return 1;
        }
        return 0;
    }
}
