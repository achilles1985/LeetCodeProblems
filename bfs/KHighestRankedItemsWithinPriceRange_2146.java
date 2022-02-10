package bfs;

import java.util.*;

/**M
 * You are given a 0-indexed 2D integer array grid of size m x n that represents a map of the items in a shop.
 * The integers in the grid represent the following:
 *     0 represents a wall that you cannot pass through.
 *     1 represents an empty cell that you can freely move to and from.
 *     All other positive integers represent the price of an item in that cell. You may also freely move to and from these item cells.
 * It takes 1 step to travel between adjacent grid cells.
 * You are also given integer arrays pricing and start where pricing = [low, high] and start = [row, col] indicates that you start at the position (row, col) and are interested only in items with a price in the range of [low, high] (inclusive). You are further given an integer k.
 * You are interested in the positions of the k highest-ranked items whose prices are within the given price range.
 * The rank is determined by the first of these criteria that is different:
 *     Distance, defined as the length of the shortest path from the start (shorter distance has a higher rank).
 *     Price (lower price has a higher rank, but it must be in the price range).
 *     The row number (smaller row number has a higher rank).
 *     The column number (smaller column number has a higher rank).
 *
 * Return the k highest-ranked items within the price range sorted by their rank (highest to lowest).
 * If there are fewer than k reachable items within the price range, return all of them.
 *
 * Example 1:
 * Input: grid = [[1,2,0,1],[1,3,0,1],[0,2,5,1]], pricing = [2,5], start = [0,0], k = 3
 * Output: [[0,1],[1,1],[2,1]]
 * Explanation: You start at (0,0).
 * With a price range of [2,5], we can take items from (0,1), (1,1), (2,1) and (2,2).
 * The ranks of these items are:
 * - (0,1) with distance 1
 * - (1,1) with distance 2
 * - (2,1) with distance 3
 * - (2,2) with distance 4
 * Thus, the 3 highest ranked items in the price range are (0,1), (1,1), and (2,1).
 *
 * Example 2:
 * Input: grid = [[1,2,0,1],[1,3,3,1],[0,2,5,1]], pricing = [2,3], start = [2,3], k = 2
 * Output: [[2,1],[1,2]]
 * Explanation: You start at (2,3).
 * With a price range of [2,3], we can take items from (0,1), (1,1), (1,2) and (2,1).
 * The ranks of these items are:
 * - (2,1) with distance 2, price 2
 * - (1,2) with distance 2, price 3
 * - (1,1) with distance 3
 * - (0,1) with distance 4
 * Thus, the 2 highest ranked items in the price range are (2,1) and (1,2).
 *
 * Example 3:
 * Input: grid = [[1,1,1],[0,0,1],[2,3,4]], pricing = [2,3], start = [0,0], k = 3
 * Output: [[2,1],[2,0]]
 * Explanation: You start at (0,0).
 * With a price range of [2,3], we can take items from (2,0) and (2,1).
 * The ranks of these items are:
 * - (2,1) with distance 5
 * - (2,0) with distance 6
 * Thus, the 2 highest ranked items in the price range are (2,1) and (2,0).
 * Note that k = 3 but there are only 2 reachable items within the price range.
 *
 * Constraints:
 *     m == grid.length
 *     n == grid[i].length
 *     1 <= m, n <= 105
 *     1 <= m * n <= 105
 *     0 <= grid[i][j] <= 105
 *     pricing.length == 2
 *     2 <= low <= high <= 105
 *     start.length == 2
 *     0 <= row <= m - 1
 *     0 <= col <= n - 1
 *     grid[row][col] > 0
 *     1 <= k <= m * n
 */
public class KHighestRankedItemsWithinPriceRange_2146 {

    public static void main(String[] args) {
        KHighestRankedItemsWithinPriceRange_2146 s = new KHighestRankedItemsWithinPriceRange_2146();

        System.out.println(s.highestRankedKItems(new int[][]{{1,0,1},{3,5,2},{1,0,1}}, new int[]{2,5}, new int[]{1,1}, 9)); //[[1,1],[1,2],[1,0]]
        System.out.println(s.highestRankedKItems(new int[][]{{0,2,0}}, new int[]{2,2}, new int[]{0,1}, 1)); //[[0,1]]
        System.out.println(s.highestRankedKItems(new int[][]{{1,2,0,1},{1,3,0,1},{0,2,5,1}}, new int[]{2,5}, new int[]{0,0}, 3)); //[[0,1],[1,1],[2,1]]
        System.out.println(s.highestRankedKItems(new int[][]{{1,1,1},{0,0,1},{2,3,4}}, new int[]{2,3}, new int[]{0,0}, 3)); //[[2,1],[2,0]]
    }

    // O(n*m*log(k)) - time, O(m*n + k) - space
    public List<List<Integer>> highestRankedKItems(int[][] grid, int[] pricing, int[] start, int k) {
        int rows = grid.length;
        int cols = grid[0].length;
        int[][] dists = new int[][]{{0,1},{1,0},{0,-1},{-1,0}};
        boolean[][] seen = new boolean[rows][cols];
        Queue<Item> maxHeap = new PriorityQueue<>(
                Comparator.comparing(Item::getDistance)
                        .thenComparing(Item::getPrice)
                        .thenComparing(Item::getRow)
                        .thenComparing(Item::getCol)
                        .reversed());
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{start[0], start[1], 0, grid[start[0]][start[1]]});
        seen[start[0]][start[1]] = true;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int[] poll = queue.poll();
                int x = poll[0];
                int y = poll[1];
                int distance = poll[2];
                int price = poll[3];
                if (price >= pricing[0] && price <= pricing[1]) {
                    maxHeap.add(new Item(distance, price, x, y));
                    if (maxHeap.size() > k) {
                        maxHeap.poll();
                    }
                }
                for (int[] dist: dists) {
                    int newX = x + dist[0];
                    int newY = y + dist[1];
                    if (newX >= 0 && newX < rows && newY >= 0 && newY < cols && !seen[newX][newY] && grid[newX][newY] != 0) {
                        queue.add(new int[]{newX, newY, distance + 1, grid[newX][newY]});
                        seen[newX][newY] = true;
                    }
                }
            }
        }
        List<List<Integer>> res = new ArrayList<>();
        while (!maxHeap.isEmpty()) {
            Item item = maxHeap.poll();
            res.add(Arrays.asList(item.row, item.col));
        }
        Collections.reverse(res);

        return res;
    }

    private static class Item {
        int distance;
        int price;
        int row;
        int col;

        public Item(int distance, int price, int row, int col) {
            this.distance = distance;
            this.price = price;
            this.row = row;
            this.col = col;
        }

        public int getDistance() {
            return distance;
        }

        public int getPrice() {
            return price;
        }

        public int getRow() {
            return row;
        }

        public int getCol() {
            return col;
        }
    }
}
