package testJUnit4;

import org.junit.runner.RunWith;
import testJUnit4.gestionePrenotazioneTest.PrenotazioneTestSuite;
import testJUnit4.gestioneRecensioniTest.RecensioniTestSuite;
import testJUnit4.ricercaLocazioneTest.RicercaLocazioneSuite;


@RunWith(org.junit.runners.Suite.class)
@org.junit.runners.Suite.SuiteClasses(value = {RicercaLocazioneSuite.class, PrenotazioneTestSuite.class, RecensioniTestSuite.class})
public class SuiteTestRicercaPrenotazioniRecensioni {
}
