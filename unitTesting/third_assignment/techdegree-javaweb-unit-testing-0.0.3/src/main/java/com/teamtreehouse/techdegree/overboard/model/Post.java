package com.teamtreehouse.techdegree.overboard.model;

import java.util.HashSet;
import java.util.Set;

public abstract class Post {
    private final Set<User> upVoters;
    private final Set<User> downVoters;
    private User author;
    private String text;

    public Post(User author, String text) {
        this.author = author;
        this.text = text;
        this.upVoters = new HashSet<>();
        this.downVoters = new HashSet<>();
    }

    public boolean addUpVoter(User user) {
        return upVoters.add(user);
    }

    public boolean addDownVoter(User user) {
        return downVoters.add(user);
    }

    public int getUpVotes() {
        return upVoters.size();
    }

    public int getDownVotes() {
        return downVoters.size();
    }

    public User getAuthor() {
        return author;
    }
}
