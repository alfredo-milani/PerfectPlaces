package testJUnit4.ricercaLocazioneTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)
@Suite.SuiteClasses(value = {RicercaTest.class,RicercaPerLocazioneTest.class})
public class RicercaLocazioneSuite {
}
