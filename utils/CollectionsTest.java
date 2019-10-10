package utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CollectionsTest {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        System.out.println(list);

        // swap()
        Collections.swap(list, 1,2);
        System.out.println(list);
        System.out.println(list);

        // min, max
        System.out.println(Collections.min(list));
        System.out.println(Collections.max(list));

        // nCopies()
        List<Integer> list2 = Collections.nCopies(5, 7);
        System.out.println(list2);
    }
}
