package linkedlist.LinkedListCycle_141;

public class ListNodeVisit {
    public int val;
    public ListNodeVisit next;
    public boolean visited;

    public ListNodeVisit(int val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return "LinkedNodeVisit{" +
                "val=" + val +
                ", next=" + next +
                ", visited=" + visited +
                '}';
    }
}
