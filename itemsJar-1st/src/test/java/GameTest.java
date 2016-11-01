import com.teamtreehouse.teachingassistant.mocks.MockRandom;
import com.teamtreehouse.teachingassistant.mocks.Utilities;
import com.teamtreehouse.teachingassistant.rules.Consoler;
import com.teamtreehouse.teachingassistant.rules.ExpectationsRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
public class GameTest {

    @Rule
    public Consoler consoler = new Consoler();

    @Rule
    public ExpectationsRule er = new ExpectationsRule();

    @Before
    public void setUp() throws Exception {
        consoler.setOutOfInputMessage("It looks like you've added more prompts than " +
                "we specified for this project.");
    }

    private void mockRandomFill(int returnVal) throws Exception {
        Utilities.prepareForTest(getClass(), "Jar");
        MockRandom.mockNextInt(returnVal);
    }

    private void setupGame(String itemType, int max, int randomFillAmount) throws Exception {
        mockRandomFill(randomFillAmount);
        consoler.addExpectedPrompt("What type of item");
        consoler.provideInput(itemType);
        consoler.addExpectedPrompt("maximum amount of " + itemType);
        consoler.provideInput(String.valueOf(max));
    }

    @Test
    public void fillingProtectsAgainstZero() throws Exception {
        setupGame("highlander", 1, 0);
        // Guess
        consoler.addExpectedPrompt("How many highlander");
        consoler.provideInput("1");
        consoler.addExpectedPrompt("1 attempt");
        consoler.ready();

        Game.main(null);

        consoler.assertExpected("Did you increment the Random value you got by 1");
    }



    @Test
    @ExpectationsRule.MeetsOnly
    public void gameCompletesSuccessfully() throws Exception {
        setupGame("marbles", 100, 41);
        consoler.addExpectedPrompt("How many marbles");
        consoler.addExpectedPrompt("1 and 100");
        consoler.provideInput("41");
        consoler.provideInput("43");
        consoler.provideInput("42");
        consoler.addExpectedPrompt("3 attempts");
        consoler.ready();

        Game.main(null);

        consoler.assertExpected("Ensure your prompts match what was defined in the instructions");
    }

    @Test
    @ExpectationsRule.ExceedsOnly
    public void gameCompletesExceedingExpectations() throws Exception {
        setupGame("marbles", 100, 41);
        consoler.addExpectedPrompt("How many marbles");
        consoler.addExpectedPrompt("1 and 100");
        consoler.provideInput("41");
        consoler.addExpectedPrompt("too low");
        consoler.provideInput("43");
        consoler.addExpectedPrompt("too high");
        consoler.provideInput("42");
        consoler.addExpectedPrompt("3 attempts");
        consoler.ready();

        Game.main(null);

        consoler.assertExpected("Ensure your prompts match what was defined by the exceeds requirements");
    }

    @Test
    @ExpectationsRule.ExceedsOnly
    public void whenGuessingMoreThanMaximumThereIsAWarning() throws Exception {
        setupGame("lives", 9, 0);
        consoler.addExpectedPrompt("How many lives");
        // Konami code user
        consoler.provideInput("30");
        // Expect warning
        consoler.addExpectedPrompt("between 1 and 9");
        consoler.provideInput("1");
        // Attempt not counted
        consoler.addExpectedPrompt("1 attempt");
        consoler.ready();

        Game.main(null);

        consoler.assertExpected("Make sure to warn if the guesser goes over the maximum and do not count it as a try");
    }
}