package testJUnit4.DecoratorTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by maria
 */
@RunWith(value = Suite.class)
@Suite.SuiteClasses(value = {DecoratorTestAlb.class, DecoratorTestBeB.class})
public class SuiteDecorator {
}
