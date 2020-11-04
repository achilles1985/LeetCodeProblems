package design;

/**M
Design a Phone Directory which supports the following operations:
    get: Provide a number which is not assigned to anyone.
    check: Check if a number is available or not.
    release: Recycle or release a number.
 */
/*
Mistakes: Clearly understand the task, then proceed with the solution.
 */
public class PhoneDirectory2 {

    /** Initialize your data structure here
     @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
    int[] next;
    int pos;
    public PhoneDirectory2(int maxNumbers) {
        next = new int[maxNumbers];
        for (int i=0; i<maxNumbers; ++i){
            next[i] = (i+1)%maxNumbers;
        }
        pos=0;
    }

    /** Provide a number which is not assigned to anyone.
     @return - Return an available number. Return -1 if none is available. */
    public int get() {
        if (next[pos]==-1) return -1;
        int ret = pos;
        pos = next[pos];
        next[ret]=-1;
        return ret;
    }

    /** Check if a number is available or not. */
    public boolean check(int number) {
        return next[number]!=-1;
    }

    /** Recycle or release a number. */
    public void release(int number) {
        if (next[number]!=-1) return;
        next[number] = pos;
        pos = number;
    }

    public static void main(String[] args) {
        PhoneDirectory2 directory = new PhoneDirectory2(5);
        // It can return any available phone number. Here we assume it returns 0.
        System.out.println(directory.get());
        // Assume it returns 1.
        System.out.println(directory.get());
        // The number 2 is available, so return true.
        System.out.println(directory.check(2)); //true
        // It returns 2, the only number that is left.
        System.out.println(directory.get());
        // The number 2 is no longer available, so return false.
        System.out.println(directory.check(2));

        // Release number 2 back to the pool.
        directory.release(2);
        // Number 2 is available again, return true.
        System.out.println(directory.check(2));
    }
}
