import com.teamtreehouse.ast.Inspector;
import com.teamtreehouse.ast.predicates.Constructors;
import com.teamtreehouse.ast.rules.Sourcery;
import org.junit.ClassRule;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class JarTest {

    @ClassRule
    public static Sourcery sourcery = new Sourcery();

    @Test
    public void classNamedJarExists() throws Exception {
        try {
            sourcery.inspectorFor("Jar");
        } catch (IllegalArgumentException iae) {
            fail("New class named Jar was created");
        }
    }

    @Test
    public void jarClassAcceptsItemNameAndMaximumNumberInConstructor() throws Exception {
        Inspector inspector = sourcery.inspectorFor("Jar");

        assertTrue("Contains constructor that accepts item name and maximum number",
                inspector.hasConstructorMatching(Constructors.acceptingParametersOfTypeNamed("String", "int")));
    }

}
