package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ArrayListTest {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        // add and update at index
        list.add(0, 6);
        list.set(1,22);
        System.out.println(list);

        // subList()
        List<Integer> sublist = list.subList(2, 4);
        System.out.println(sublist);

        sublist.add(2);
        sublist.add(2);
        System.out.println(sublist);
        // Any sublist update, results in origin list update
        System.out.println(list);

        // remove at index
        sublist.remove(2);
        System.out.println(sublist);

        list.addAll(Arrays.asList(10,11,12));
        System.out.println(list);
        // Add several at specified position
        list.addAll(1, Arrays.asList(13,14));
        System.out.println(list);

        list.remove(new Integer(22));
        System.out.println(list);

        // remove all specified elements
        list.removeAll(Arrays.asList(2, 3));
        System.out.println(list);

        // asList() returns unmodifiable List
        List<Integer> l1 = Arrays.asList(1, 2, 3);
        l1.set(0, 4);
        System.out.println(l1);

        // first index of specified element
        int item = l1.indexOf(2);
        System.out.println(item);

    }
}
