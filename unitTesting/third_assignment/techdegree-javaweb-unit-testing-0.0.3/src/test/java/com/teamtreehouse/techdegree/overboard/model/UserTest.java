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
    private User charlie;
    private Question question;
    private Answer bobAnswer;
    private Post bobPost;

    @Before
    public void setUp() throws Exception {

        Board board = new Board("Java");
        alice = board.createUser("alice");
        bob = board.createUser("bob");
        charlie = board.createUser("charlie");
        question = alice.askQuestion("What is a String?");
        bobAnswer=bob.answerQuestion(question,"A sequence of characters");
        bobPost=bob.answerQuestion(question,"A sequence of characters");

    }

    //Write a test to ensure that the questioner’s reputation goes up by 5 points if their question is upvoted.
    @Test

    public void reputationGoesUpIfQuestionUpvoted() throws Exception {

        bob.upVote(question);

        assertEquals("Reputation doesn't goes up by 5",5,alice.getReputation());
    }

    //Write a test to assert that the answerer’s reputation goes up by 10 points if their answer is upvoted.

    @Test
    public void reputationGoesUpIfAnswerUpvoted() throws Exception {

        alice.upVote(bobPost);

        assertEquals("Answer's reputation doesn't goes up by 10",10,bob.getReputation());
    }

    //Write a test that proves that having an answer accepted gives the answerer a 15 point reputation boost

    @Test
    public void acceptedAnswerGivesAnswererReputationPoints() throws Exception {

        alice.acceptAnswer(bobAnswer);

        assertEquals("Answer's reputation doesn't goes up by 15",15,bob.getReputation());
    }

    //Using a test, ensure that voting either up or down is not allowed on questions or answers by the original author,
    // you know to avoid gaming the system. Ensure the proper exceptions are being thrown.

    @Test(expected= VotingException.class)
    public void VotingMadeByAuthorThrowsException() throws Exception {

        bob.upVote(bobPost);
        bob.downVote(bobPost);
        alice.upVote(question);
        alice.downVote(question);
    }

    //Write a test to make sure that only the original questioner can accept an answer.
    // Ensure the intended messaging is being sent to back to the caller.

    @Test(expected= AnswerAcceptanceException.class)
    public void originalQuestionerCanAcceptAnswer() throws Exception {

        bob.acceptAnswer(bobAnswer);
    }

    //Reviewing the User.getReputation method may expose some code that is not requested to
    // be tested in the Meets Project instructions. Write the missing test(EXTRA CREDIT).

    @Test
    public void downVotingQuestionsGetNoPoints() throws Exception {

        bob.upVote(question);
        charlie.downVote(question);

        assertEquals(5,alice.getReputation());
    }
}
