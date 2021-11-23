package linkedlist.easy;

import linkedlist.utils.ListNode;

/** E
 * Given the heads of two singly linked-lists headA and headB, return the node at which the two lists intersect.
 * If the two linked lists have no intersection at all, return null.
 * For example, the following two linked lists begin to intersect at node c1:
 * The test cases are generated such that there are no cycles anywhere in the entire linked structure.
 * Note that the linked lists must retain their original structure after the function returns.
 *
 * Custom Judge:
 * The inputs to the judge are given as follows (your program is not given these inputs):
 *     intersectVal - The value of the node where the intersection occurs. This is 0 if there is no intersected node.
 *     listA - The first linked list.
 *     listB - The second linked list.
 *     skipA - The number of nodes to skip ahead in listA (starting from the head) to get to the intersected node.
 *     skipB - The number of nodes to skip ahead in listB (starting from the head) to get to the intersected node.
 * The judge will then create the linked structure based on these inputs and pass the two heads, headA and headB to your program.
 * If you correctly return the intersected node, then your solution will be accepted.
 */
public class IntersectionOfTwoLinkedLists_160 {

    public static void main(String[] args) {
        IntersectionOfTwoLinkedLists_160 s = new IntersectionOfTwoLinkedLists_160();
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        ListNode node6 = new ListNode(6);

        ListNode head1 = new ListNode(1);
        head1.next = new ListNode(2);
        head1.next.next = new ListNode(3);
        head1.next.next.next = node4;
        head1.next.next.next.next = node5;
        head1.next.next.next.next.next = node6;

        ListNode head2 = new ListNode(10);
        head2.next = new ListNode(11);
        head2.next.next = node4;
        head2.next.next.next = node5;
        head2.next.next.next.next = node6;

        System.out.println(s.getIntersectionNode(head1, head2));
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode pA = headA;
        ListNode pB = headB;
        while (pA != pB) {
            pA = pA == null ? headB : pA.next;
            pB = pB == null ? headA : pB.next;
        }
        return pA;
        // Note: In the case lists do not intersect, the pointers for A and B
        // will still line up in the 2nd iteration, just that here won't be
        // a common node down the list and both will reach their respective ends
        // at the same time. So pA will be NULL in that case.
    }
}
