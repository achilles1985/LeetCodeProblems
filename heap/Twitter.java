package heap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/** M
 Design a simplified version of Twitter where users can post tweets, follow/unfollow another user and is able to see the 10 most recent tweets in the user's news feed. Your design should support the following methods:

 postTweet(userId, tweetId): Compose a new tweet.
 getNewsFeed(userId): Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent.
 follow(followerId, followeeId): Follower follows a followee.
 unfollow(followerId, followeeId): Follower unfollows a followee.
 */
public class Twitter {
    private static final int FEED_SIZE = 3;
    private static int count;

    Map<Integer, List<Integer>> followee;
    Map<Integer, List<Twit>> twitsByUser;

    /**
     * Initialize your data structure here.
     */
    public Twitter() {
        count = 0;
        followee = new HashMap<>();
        twitsByUser = new HashMap<>();
    }

    /**
     * Compose a new tweet.
     */
    public void postTweet(int userId, int tweetId) {
        twitsByUser.computeIfAbsent(userId, u -> new ArrayList<>()).add(new Twit(tweetId));
    }

    /**
     * Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by
     * users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent.
     */
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> followees = followee.getOrDefault(userId, new ArrayList<>());
        List<Twit> followesTwits = findTwitsFor(followees);
        List<Twit> userTwits = twitsByUser.getOrDefault(userId, new ArrayList<>());

        List<Twit> allTwits = new ArrayList<>();
        allTwits.addAll(userTwits);
        allTwits.addAll(followesTwits);

        Queue<Twit> heap = new PriorityQueue<>();
        for (Twit twit: allTwits) {
            heap.add(twit);
            if (heap.size() > FEED_SIZE) {
                heap.poll();
            }
        }

        List<Integer> feedIds = new ArrayList<>(10);
        while (!heap.isEmpty()) {
            feedIds.add(heap.poll().id);
        }

        return feedIds;
    }

    private List<Twit> findTwitsFor(List<Integer> followees) {
        if (followees == null || followees.isEmpty()) {
            return Collections.emptyList();
        }
        List<Twit> twits = new ArrayList<>();
        for(int followeId: followees) {
            List<Twit> followTwits = twitsByUser.getOrDefault(followeId, new ArrayList<>());
            twits.addAll(followTwits);
        }

        return twits;
    }

    /**
     * Follower follows a followee. If the operation is invalid, it should be a no-op.
     */
    public void follow(int followerId, int followeeId) {
        followee.computeIfAbsent(followerId, f -> new ArrayList<>()).add(followeeId);
    }

    /**
     * Follower unfollows a followee. If the operation is invalid, it should be a no-op.
     */
    public void unfollow(int followerId, int followeeId) {
        List<Integer> followee = this.followee.getOrDefault(followerId, new ArrayList<>());
        followee.remove(new Integer(followeeId));
    }

    public static void main(String[] args) {
        Twitter twitter = new Twitter();

        twitter.postTweet(1, 1);
        twitter.postTweet(1, 2);
        twitter.postTweet(1, 3);
        twitter.postTweet(1, 4);
        List<Integer> feeds1 = twitter.getNewsFeed(1); // 4,3,2

        twitter.follow(1, 2);
        twitter.postTweet(2, 6);
        twitter.postTweet(2, 7);
        twitter.postTweet(2, 8);
        twitter.postTweet(2, 9);

        List<Integer> feeds2 = twitter.getNewsFeed(1); // 9,8,7

        twitter.unfollow(1, 2);

        List<Integer> feeds3 = twitter.getNewsFeed(1); // 4,3,2
    }

    private static class Twit implements Comparable<Twit> {
        int id;
        int date;

        public Twit(int id) {
            this.id = id;
            this.date = count++;
        }

        @Override
        public int compareTo(Twit o) {
            return date - o.date;
        }
    }
}
