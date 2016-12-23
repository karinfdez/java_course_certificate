package com.teamtreehouse.techdegree.overboard.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Board {
    private String topic;
    private List<Question> questions;
    private List<Answer> answers;
    private List<User> users;

    public Board(String topic) {
        this.topic = topic;
        questions = new ArrayList<>();
        answers = new ArrayList<>();
        users = new ArrayList<>();
    }

    public User createUser(String userName) {
        User user = new User(this, userName);
        users.add(user);
        return user;
    }

    public List<Question> getQuestions() {
        return Collections.unmodifiableList(questions);
    }

    public List<Answer> getAnswers() {
        return Collections.unmodifiableList(answers);
    }

    public List<User> getUsers() {
        return Collections.unmodifiableList(users);
    }

    protected void addQuestion(Question question) {
        questions.add(question);
    }

    protected void addAnswer(Answer answer) {
        answers.add(answer);
    }
}
