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
    public void reputationGoesUpIfQuestionUpvoted() throws Exception {
        Question question = alice.askQuestion("What is a String?");
        bob.upVote(question);
        assertEquals("Reputation doesn't goes up by 5",5,alice.getReputation());
    }

    @Test
    public void reputationGoesUpIfAnswerUpvoted() throws Exception {
        Question question = alice.askQuestion("What is a String?");
        Post bobAnswer=bob.answerQuestion(question,"A sequence of characters");
        alice.upVote(bobAnswer);
        assertEquals("Answer's reputation doesn't goes up by 10",10,bob.getReputation());
    }

    @Test
    public void acceptedAnswerGivesAnswererReputationPoints() throws Exception {
        Question question = alice.askQuestion("What is a String?");
        Answer bobAnswer=bob.answerQuestion(question,"A sequence of characters");
        alice.acceptAnswer(bobAnswer);
        assertEquals("Answer's reputation doesn't goes up by 15",15,bob.getReputation());
    }

}

   // Write a test that proves that having an answer accepted gives the answerer a 15 point reputation boost