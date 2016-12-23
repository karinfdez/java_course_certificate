package com.teamtreehouse.techdegree.overboard.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class Question extends Post {
    private User author;
    private List<Answer> answers;
    private String text;
    private Set<User> upVoters;
    private Set<User> downVoters;

    public Question(User author, String text) {
        super(author, text);
        answers = new ArrayList<>();
    }

    protected void addAnswer(Answer answer) {
        answers.add(answer);
    }

    public List<Answer> getAnswers() {
        return Collections.unmodifiableList(answers);
    }


}
