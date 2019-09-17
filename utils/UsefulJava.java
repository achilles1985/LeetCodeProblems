package utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UsefulJava {

    public static void main(String[] args) {
        // computeIfAbsent vs PutIfAbsent
        //computeIfAbsent takes a mapping function, that is called to obtain the value if the key is missing.
        String[] words = new String[]{"malok", "malo", "kalo", "malok", "malok", "malo"};
        Map<String, List<String>> map = new HashMap<>();
        for (String world: words) {
            map.computeIfAbsent(world, w -> new ArrayList<>()).add(world); // If no value for a given key, compute it by function provided and return. Otherwise, return the value.
        }
        System.out.println(map);

        //putIfAbsent takes the value directly.
        Map<String, String> map2 = new HashMap<>();

        for (int i = 0; i < words.length; i++) {
            map2.putIfAbsent(words[i], words[i]+i); // If value for given key exists, return it, otherwise return newly inserted value (words[i]+i)
        }
        System.out.println(map2);
    }
}
