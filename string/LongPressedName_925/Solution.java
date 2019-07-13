package string.LongPressedName_925;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    // O(n + m) - time, where m, n - the lengths of 'name' nad 'typed', O(m + n) - space
    public boolean isLongPressedName(String name, String typed) {
        List<Group> nameGroups = groupify(name);
        List<Group> typedGroups = groupify(typed);

        if (nameGroups.size() != typedGroups.size()) {
            return false;
        }
        for (int i = 0; i < nameGroups.size(); i++) {
            if (nameGroups.get(i).getSymbol() != typedGroups.get(i).getSymbol()) {
                return false;
            }
            if (nameGroups.get(i).getCounter() > typedGroups.get(i).getCounter()) {
                return false;
            }
        }

        return true;
    }

    private List<Group> groupify(String input) {
        char[] chars = input.toCharArray();
        List<Group> groups = new ArrayList<>();

        int counter = 1;
        for (int i = 0; i < chars.length; i++) {
            if (i == chars.length-1 || chars[i] != chars[i+1]) {
                groups.add(new Group(chars[i], counter));
                counter = 1;
                continue;
            }
            counter++;
        }

        return groups;
    }

    private static class Group {
        char symbol;
        int counter;

        public Group(char symbol, int counter) {
            this.symbol = symbol;
            this.counter = counter;
        }

        public char getSymbol() {
            return symbol;
        }

        public int getCounter() {
            return counter;
        }
    }

}
