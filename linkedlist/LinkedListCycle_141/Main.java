package linkedlist.LinkedListCycle_141;

public class Main {

    public static void main(String[] args) {
        ListNodeVisit n1 = new ListNodeVisit(3);
        ListNodeVisit n2 = new ListNodeVisit(2);
        ListNodeVisit n3 = new ListNodeVisit(0);
        ListNodeVisit n4 = new ListNodeVisit(-4);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        //n4.next = n2;

        Solution s = new Solution();
        System.out.println("Has cycle: " + s.hasCycle(n1));
        System.out.println("Has cycle: " + s.hasCycle22(n1));
    }
}
