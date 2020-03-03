package binarySearch;

/** H
 There are two sorted arrays nums1 and nums2 of size m and n respectively.
 Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
 You may assume nums1 and nums2 cannot be both empty.

 Example 1:
 nums1 = [1, 3]
 nums2 = [2]

 The median is 2.0

 Example 2:
 nums1 = [1, 2]
 nums2 = [3, 4]
 The median is (2 + 3)/2 = 2.5
 */
public class MedianOfTwoSortedArrays_4 {

    public static void main(String[] args) {
        MedianOfTwoSortedArrays_4 s = new MedianOfTwoSortedArrays_4();
        System.out.println(s.findMedianSortedArrays(new int[]{1,3}, new int[]{2})); //2.0
        System.out.println(s.findMedianSortedArrays(new int[]{1,2}, new int[]{3,4})); //2.5
    }

    // O(n+m) - time, O(n+m) - space
    public double findMedianSortedArrays(int[] a, int[] b) {
        if (a == null || a.length == 0) {
            return findMedianOf(b);
        }
        if (b == null || b.length == 0) {
            return findMedianOf(a);
        }
        int size = a.length + b.length;
        int[] merged = new int[size];
        int i = 0;
        int j = 0;
        int k = 0;
        while (i < a.length && j < b.length) {
            if (a[i] < b[j]) {
                merged[k++] = a[i++];
            } else {
                merged[k++] = b[j++];
            }
        }
        while (i < a.length) {
            merged[k++] = a[i++];
        }
        while (j < b.length) {
            merged[k++] = b[j++];
        }

        return findMedianOf(merged);
    }

    private double findMedianOf(int[] merged) {
        int size = merged.length;
        return (size & 1) == 1 ? (double) merged[size/2] : (double) (merged[size/2] + merged[size/2 - 1])/2;
    }

    // O(log(min(n,m))) - time, O(1) - space. Needs to be understanded.
    public double findMedianSortedArrays2(int input1[], int input2[]) {
        //if input1 length is greater than switch them so that input1 is smaller than input2.
        if (input1.length > input2.length) {
            return findMedianSortedArrays2(input2, input1);
        }
        int x = input1.length;
        int y = input2.length;

        int low = 0;
        int high = x;
        while (low <= high) {
            int partitionX = (low + high)/2;
            int partitionY = (x + y + 1)/2 - partitionX;

            //if partitionX is 0 it means nothing is there on left side. Use -INF for maxLeftX
            //if partitionX is length of input then there is nothing on right side. Use +INF for minRightX
            int maxLeftX = (partitionX == 0) ? Integer.MIN_VALUE : input1[partitionX - 1];
            int minRightX = (partitionX == x) ? Integer.MAX_VALUE : input1[partitionX];

            int maxLeftY = (partitionY == 0) ? Integer.MIN_VALUE : input2[partitionY - 1];
            int minRightY = (partitionY == y) ? Integer.MAX_VALUE : input2[partitionY];

            if (maxLeftX <= minRightY && maxLeftY <= minRightX) {
                //We have partitioned array at correct place
                // Now get max of left elements and min of right elements to get the median in case of even length combined array size
                // or get max of left for odd length combined array size.
                if ((x + y) % 2 == 0) {
                    return ((double)Math.max(maxLeftX, maxLeftY) + Math.min(minRightX, minRightY))/2;
                } else {
                    return (double)Math.max(maxLeftX, maxLeftY);
                }
            } else if (maxLeftX > minRightY) { //we are too far on right side for partitionX. Go on left side.
                high = partitionX - 1;
            } else { //we are too far on left side for partitionX. Go on right side.
                low = partitionX + 1;
            }
        }

        //Only we we can come here is if input arrays were not sorted. Throw in that scenario.
        throw new IllegalArgumentException();
    }
}
