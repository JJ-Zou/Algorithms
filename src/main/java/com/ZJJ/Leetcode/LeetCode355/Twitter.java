package com.ZJJ.Leetcode.LeetCode355;

import java.util.*;

public class Twitter {
    class Tweet {
        int userId;
        int tweetId;

        Tweet(int userId, int tweetId) {
            this.userId = userId;
            this.tweetId = tweetId;
        }
    }

    private Map<Integer, Set<Integer>> followMap;
    private Deque<Tweet> tweetQueue;

    /**
     * Initialize your data structure here.
     */
    public Twitter() {
        followMap = new HashMap<>();
        tweetQueue = new ArrayDeque<>();
    }

    /**
     * Compose a new tweet.
     */
    public void postTweet(int userId, int tweetId) {
        if (!followMap.containsKey(userId)) {
            followMap.put(userId, new HashSet<>());
            followMap.get(userId).add(userId);
        }
        tweetQueue.offerFirst(new Tweet(userId, tweetId));
    }

    /**
     * Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent.
     */
    public List<Integer> getNewsFeed(int userId) {
        Set<Integer> followeeSet = followMap.get(userId);
        List<Integer> list = new LinkedList<>();
        if (followeeSet == null || followeeSet.isEmpty() || tweetQueue.size() == 0) {
            return list;
        }
        for (Tweet t : tweetQueue) {
            if (followeeSet.contains(t.userId)) {
                list.add(t.tweetId);
            }
            if (list.size() == 10) {
                return list;
            }
        }
        return list;
    }

    /**
     * Follower follows a followee. If the operation is invalid, it should be a no-op.
     */
    public void follow(int followerId, int followeeId) {
        if (!followMap.containsKey(followerId)) {
            followMap.put(followerId, new HashSet<>());
            followMap.get(followerId).add(followerId);
        }
        followMap.get(followerId).add(followeeId);
    }

    /**
     * Follower unfollows a followee. If the operation is invalid, it should be a no-op.
     */
    public void unfollow(int followerId, int followeeId) {
        if (followerId == followeeId) {
            return;
        }
        if (followMap.containsKey(followerId)) {
            followMap.get(followerId).remove(followeeId);
        }
    }
}
