import com.teamtreehouse.ast.Inspector;
import com.teamtreehouse.ast.predicates.Expressions;
import com.teamtreehouse.ast.rules.Sourcery;
import com.teamtreehouse.teachingassistant.rules.ExpectationsRule;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class PrompterTest {

    @ClassRule
    public static Sourcery sourcery = new Sourcery();

    @Rule
    public ExpectationsRule er = new ExpectationsRule();

    @Test
    @ExpectationsRule.ExceedsOnly
    public void classNamedPrompterExists() throws Exception {
        try {
            sourcery.inspectorFor("Prompter");
        } catch (IllegalArgumentException iae) {
            fail("New class named Prompter was created");
        }
    }

    @Test
    @ExpectationsRule.ExceedsOnly
    public void prompterIsUsed() throws Exception {
        Inspector inspector = sourcery.inspectorFor("Game");
        boolean prompterDeclared = inspector.matchingChain(chain -> chain
                .withExpression(
                        Expressions
                                .containing("Prompter")
                                .and(Expressions.isVariableDeclaration())
                )
        );

        assertTrue("Make sure you use an instance of the Prompter class to to perform all I / O", prompterDeclared);
    }
}
