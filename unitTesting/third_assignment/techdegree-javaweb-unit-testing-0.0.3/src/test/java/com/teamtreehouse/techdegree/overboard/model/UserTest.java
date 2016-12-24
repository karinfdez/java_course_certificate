package com.teamtreehouse.techdegree.overboard.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by karinfernandez on 12/22/16.
 */
public class UserTest {
    private User alice;
    private User bob;
    private User charles;


    @Before
    public void setUp() throws Exception {

        Board board = new Board("Java");
        alice = board.createUser("alice");
        bob = board.createUser("bob");
        charles = board.createUser("charles");

    }

    @Test
    public void askQuestion() throws Exception {

    }

    @Test
    public void answerQuestion() throws Exception {

    }

    @Test
    public void acceptAnswer() throws Exception {

    }

    @Test
    public void reputationGoesUpIfQuestionUpvoted() throws Exception {
        Question question = alice.askQuestion("What is a String?");
        bob.upVote(question);
        assertEquals("Reputation doesn't goes up by 5",5,alice.getReputation());
    }

    @Test
    public void downVote() throws Exception {

    }

    @Test
    public void getReputation() throws Exception {

    }

    @Test
    public void getQuestions() throws Exception {

    }

    @Test
    public void getAnswers() throws Exception {

    }

    @Test
    public void getName() throws Exception {

    }

}