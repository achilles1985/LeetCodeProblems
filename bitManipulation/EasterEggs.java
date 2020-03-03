package bitManipulation;

import java.util.ArrayList;
import java.util.List;

/**M
 * Brother Thomas do not believe in miracles. Instead he likes eating much food. :)
 * To celebrate the Easter he boiled 20 eggs, then put them in a straight line before him on the table, preparing to
 * have his breakfast.
 * Suddenly some of the eggs, which were initially white, become red!!! (surely it was a kind of friendly joke from
 * Providence, to convince the monk that miracles can really happen)
 * Moreover, puzzled Thomas have discovered that whenever he touches any of the eggs, it instantly changes its color,
 * toggling from red to white or back. In addition each egg being touched changes the color of several other eggs.
 * Learning out which changes in colors are performed by touching each of the eggs, brother Tomas is interested, how
 * he can turn them all back to white.
 * For example, suppose he has the following sequence of eggs on the table:
 *
 * Red Red White White White Red
 * And he discovered the following rules (eggs are numbered from the left, starting with 0):
 *
 * 0-th egg toggles itself and 5-th
 * 1-st egg toggles itself and 2-nd and 4-th
 * 2-nd egg toggles itself and 1-st and 5-th
 * 3-th egg toggles itself and 0-th and 1-st
 * 4-th egg toggles itself and 0-th and 1-st
 * 5-th egg toggles itself and 4-th
 * So touching 1-st, 2-nd and 4-th eggs will turn them all to white.
 *
 * Input data will describe 20 eggs with values 1 for red or 0 for white.
 * Next 20 lines will contain the number of the egg being touched and then, after a colon, numbers of the eggs being
 * toggled by touching the given one.
 * Answer should contain numbers of the eggs, which, if touched, will turn all eggs to white.
 *
 * Example (with only 6 eggs instead of 20 for simplicity):
 * input data:
 * 1 0 1 0 1 1
 * 0: 0 5
 * 1: 1 4
 * 2: 2 4
 * 3: 2 3 5
 * 4: 1 4
 * 5: 2 3 5
 *
 * answer:
 * 0 2
 */
/*
    1. Max number of eggs is 20.
    2. Number of eggs is equals to the number of rows in changes matrix.
 */
public class EasterEggs {

    public static void main(String[] args) {
        EasterEggs s = new EasterEggs();
        System.out.println(s.changeColor(new int[]{1,0,1,0,1,1}, new int[][]{{0,5},{1,4},{2,4},{2,3,5},{1,4},{2,3,5}}));
    }

    public List<Integer> changeColor(int[] eggs, int[][] clicks) {
        int size = eggs.length;
        List<Integer> results = new ArrayList<>();
        for (int set = 0; set < (1 << size); set++) {
            int[] localEggs = eggs.clone();
            for (int bit = 0; bit < size; bit++) {
                if (contains(set, bit)) {
                    for (int bit2: clicks[bit]) {
                        localEggs[bit2] = 1 - localEggs[bit2];
                    }
                }
            }
            boolean ok = true;
            for (int i = 0; i < size; i++) {
                ok &= (localEggs[i] == 0);
            }
            if (ok) {
                for (int bit = 0; bit < size; bit++) {
                    if (contains(set, bit)) {
                        results.add(bit);
                    }
                }
                return results;
            }
        }
        return results;
    }

    private boolean contains(int set, int bit) {
        int i = 1 << bit;
        boolean b = (set & i) > 0;
        return b;
    }

    private int findComplement(int input, int numberOfBits) {
        int temp = 1 << numberOfBits;
        return input ^ (temp - 1);
    }

    private int convertToBinary(int[] eggs) {
        int result = 0;
        for (int i = 0; i < eggs.length; i++) {
            if (eggs[i] == 1) {
                result += Math.pow(2, eggs.length-i-1);
            }
        }
        return result;
    }
}
