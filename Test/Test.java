package Test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        RoundTest.class,
        PlayerTest.class,
        DeckTest.class,
        CardTest.class
})
public class Test {
}