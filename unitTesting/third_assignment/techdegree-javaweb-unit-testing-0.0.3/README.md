## Welcome to Stack Overboard

This is a WIP (Work In Progress) but the basic idea is this:

```java
// A board is a topic area on a bulletin board
Board board = new Board("Java");

// Create new users from the board
User alice = board.createUser("alice");
User bob = board.createUser("bob");
User charles = board.createUser("charles");

// Users create questions
Question question = alice.askQuestion("What is a String?");
// They can be upvoted
bob.upVote(question);

// Users can also answer questions
Answer answer = bob.answerQuestion(question, "It is a series of characters, strung together...");
// Answers can be upvoted
alice.upVote(answer);

// and downvoted
charles.downVote(answer);
charles.downVote(answer);

// and accepted by the person who asked the question
alice.accept(answer);

// There is then reputation
System.out.println("Alice: " + alice.getReputation()); // Alice's question got upvoted so this prints Alice: 5
System.out.println("Bob: " + bob.getReputation()); // Bob's answer got upvoted (10) and his answer was accepted (15)
                                                   // so this prints Bob: 25
```

## TODO
[ ] TESTS!
[ ] Use a data store!
[ ] Build the website surrounding this awesome model
