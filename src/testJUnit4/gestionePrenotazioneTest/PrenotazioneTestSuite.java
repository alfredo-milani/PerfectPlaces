package testJUnit4.gestionePrenotazioneTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses(value = {FactoryTest.class, PrenotazionePiùPostiTest.class,
                            PrenotazioneSingoloPostoTest.class})
public class PrenotazioneTestSuite {
}
