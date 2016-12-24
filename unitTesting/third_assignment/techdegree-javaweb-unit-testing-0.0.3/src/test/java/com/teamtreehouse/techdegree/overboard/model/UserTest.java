package com.teamtreehouse.techdegree.overboard.model;

import com.teamtreehouse.techdegree.overboard.exc.AnswerAcceptanceException;
import com.teamtreehouse.techdegree.overboard.exc.VotingException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by karinfernandez on 12/22/16.
 */
public class UserTest {
    private User alice;
    private User bob;
    private Question question;
    private Answer bobAnswer;
    private Post bobPost;

    @Before
    public void setUp() throws Exception {

        Board board = new Board("Java");
        alice = board.createUser("alice");
        bob = board.createUser("bob");
        question = alice.askQuestion("What is a String?");
        bobAnswer=bob.answerQuestion(question,"A sequence of characters");
        bobPost=bob.answerQuestion(question,"A sequence of characters");

    }

    @Test
    public void reputationGoesUpIfQuestionUpvoted() throws Exception {
        bob.upVote(question);
        assertEquals("Reputation doesn't goes up by 5",5,alice.getReputation());
    }

    @Test
    public void reputationGoesUpIfAnswerUpvoted() throws Exception {
        alice.upVote(bobPost);
        assertEquals("Answer's reputation doesn't goes up by 10",10,bob.getReputation());
    }

    @Test
    public void acceptedAnswerGivesAnswererReputationPoints() throws Exception {
        alice.acceptAnswer(bobAnswer);
        assertEquals("Answer's reputation doesn't goes up by 15",15,bob.getReputation());
    }

    @Test(expected= VotingException.class)
    public void VotingMadeByAuthorThrowsException() throws Exception {
        bob.upVote(bobPost);
        bob.downVote(bobPost);
        alice.upVote(question);
        alice.downVote(question);
    }

    @Test(expected= AnswerAcceptanceException.class)
    public void originalQuestionerCanAcceptAnswer() throws Exception {
        bob.acceptAnswer(bobAnswer);
    }
}
