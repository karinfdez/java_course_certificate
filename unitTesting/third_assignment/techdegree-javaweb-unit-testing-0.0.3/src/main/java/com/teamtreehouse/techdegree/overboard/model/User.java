package com.teamtreehouse.techdegree.overboard.model;

import com.teamtreehouse.techdegree.overboard.exc.AnswerAcceptanceException;
import com.teamtreehouse.techdegree.overboard.exc.VotingException;

import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

public class User {
    private final Board board;
    private String name;


    protected User(Board board, String userName) {
        this.board = board;
        name = userName;
    }

    // This is the main entry point for creating questions
    public Question askQuestion(String text) {
        Question question = new Question(this, text);
        board.addQuestion(question);
        return question;
    }

    // This is the main entry point for creating answers
    public Answer answerQuestion(Question question, String text) {
        Answer answer = new Answer(question, this, text);
        board.addAnswer(answer);
        return answer;
    }

    public void acceptAnswer(Answer answer) {
        User questioner = answer.getQuestion().getAuthor();
        if (!questioner.equals(this)) {
            String message = String.format("Only %s can accept this answer as it is their question",
                    questioner.getName());
            throw new AnswerAcceptanceException(message);
        }
        answer.setAccepted(true);
    }

    public boolean upVote(Post post) {
        if (post.getAuthor().equals(this)) {
            throw new VotingException("You cannot vote for yourself!");
        }
        return post.addUpVoter(this);
    }

    public boolean downVote(Post post) {
        return post.addDownVoter(this);
    }

    public int getReputation() {
        int reputation = 0;
        // Up-voted questions get you 5 points, currently down-voting of questions affects nothing.
        for (Question question : getQuestions()) {
            reputation += (question.getUpVotes() * 5);
        }
        // Up-voted answers get you 10 points, down-voting costs 1 point
        for (Answer answer : getAnswers()) {
            reputation += (answer.getUpVotes() * 10);
            reputation -= answer.getDownVotes();
            if (answer.isAccepted()) {
                reputation += 15;
            }
        }
        return reputation;
    }

    public List<Question> getQuestions() {
        return board.getQuestions().stream()
                .filter(question -> question.getAuthor().equals(this))
                .collect(collectingAndThen(toList(), Collections::unmodifiableList));
    }

    public List<Answer> getAnswers() {
        return board.getAnswers().stream()
                .filter(answer -> answer.getAuthor().equals(this))
                .collect(collectingAndThen(toList(), Collections::unmodifiableList));
    }

    public String getName() {
        return name;
    }
}
