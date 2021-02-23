package com.zjj.leetcode.LeetCode355;

import java.util.*;

public class Twitter {
    private Map<Integer, Deque<Integer>> tweetMap;
    private Map<Integer, Set<Integer>> followMap;
    private Map<Integer, Integer> tweetSortMap;
    private int tweetCount;

    /**
     * Initialize your data structure here.
     */
    public Twitter() {
        tweetMap = new HashMap<>();
        followMap = new HashMap<>();
        tweetSortMap = new HashMap<>();
        tweetCount = 0;
    }

    /**
     * Compose a new tweet.
     */
    public void postTweet(int userId, int tweetId) {
        tweetCount++;
        tweetSortMap.put(tweetId, tweetCount);
        if (!tweetMap.containsKey(userId)) {
            tweetMap.put(userId, new ArrayDeque<>());
            tweetMap.get(userId).addFirst(tweetId);
        } else if (tweetMap.get(userId).size() < 10) {
            tweetMap.get(userId).addFirst(tweetId);
        } else {
            tweetMap.get(userId).pollLast();
            tweetMap.get(userId).addFirst(tweetId);
        }
    }

    /**
     * Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent.
     */
    public List<Integer> getNewsFeed(int userId) {
        Queue<Integer> queue = new PriorityQueue<>((o1, o2) -> tweetSortMap.get(o1) - tweetSortMap.get(o2));
        Iterator<Integer> iterator;
        int num = 10;
        if (tweetMap.containsKey(userId)) {
            iterator = tweetMap.get(userId).iterator();
            while (num > 0 && iterator.hasNext()) {
                queue.add(iterator.next());
                num--;
            }
        }
        Set<Integer> users = followMap.get(userId);
        if (users != null) {
            for (int user : users) {
                if (tweetMap.containsKey(user)) {
                    iterator = tweetMap.get(user).iterator();
                    num = 10;
                    while (num > 0 && iterator.hasNext()) {
                        queue.add(iterator.next());
                        if (queue.size() > 10) {
                            queue.poll();
                        }
                        num--;
                    }
                }
            }
        }
        Deque<Integer> res = new ArrayDeque<>();
        while (!queue.isEmpty()) {
            res.addFirst(queue.poll());
        }
        return new ArrayList<>(res);
    }

    /**
     * Follower follows a followee. If the operation is invalid, it should be a no-op.
     */
    public void follow(int followerId, int followeeId) {
        if (followerId == followeeId) {
            return;
        }
        if (!followMap.containsKey((followerId))) {
            followMap.put(followerId, new HashSet<>());
        }
        followMap.get(followerId).add(followeeId);
    }

    /**
     * Follower unfollows a followee. If the operation is invalid, it should be a no-op.
     */
    public void unfollow(int followerId, int followeeId) {
        if (followMap.containsKey(followerId)) {
            followMap.get(followerId).remove(followeeId);
        }
    }
}

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */