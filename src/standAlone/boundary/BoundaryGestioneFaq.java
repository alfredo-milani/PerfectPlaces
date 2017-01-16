
package standAlone.boundary;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class BoundaryGestioneFaq {

    // Pannelli
    public JPanel pannelloAmministrazione;
    public JPanel panelTitolo = new JPanel();
    public JPanel panelButton = new JPanel();

    // Label
    public JLabel titolo = new JLabel();

    //Bottoni
    public JButton bRispondi;
    public JButton bRimuoviDati;
    public JButton bIndietro;

    // Ascoltatore di bottone e relativa azioni
    private RispondiAA ascoltatoreEtAzioneRispondi;
    private RimuoviFaqAA ascoltatoreEtAzioneRimuoviFaq;
    private TornaIndietro ascoltatoreTornaIndietro;



    public BoundaryGestioneFaq()
    {
        int border = 5;

        pannelloAmministrazione = new JPanel();

        pannelloAmministrazione.setSize(BoundaryAvvio.Confine.getWidth(), BoundaryAvvio.Confine.getHeight());
        BoundaryAvvio.Confine.add(pannelloAmministrazione);
        pannelloAmministrazione.setLayout(null);

        panelTitolo.setLayout(null);
        panelTitolo.setSize(BoundaryAvvio.Confine.getWidth(), 45);
        panelTitolo.setLocation(5, 5);
        panelTitolo.add(titolo);

        titolo.setFont(new Font("Verdana", Font.BOLD, 20));
        titolo.setLocation(border, border);
        titolo.setSize(panelTitolo.getWidth(), 35);
        titolo.setHorizontalAlignment(JLabel.CENTER);
        titolo.setVerticalAlignment(JLabel.CENTER);
        titolo.setText("Scegli cosa fare");

        pannelloAmministrazione.add(panelTitolo);

        panelButton.setLayout(null);
        panelButton.setSize(BoundaryAvvio.Confine.getWidth(),400);
        panelButton.setLocation(5, 30);

        bRispondi = new JButton("Rispondi Faq");
        bRispondi.setLocation(panelButton.getWidth()/2 - panelButton.getWidth()/6,80);
        bRispondi.setSize(panelButton.getWidth()/3,50);
        bRispondi.setFont(new Font("Arial", 0, 20));
        bRispondi.setToolTipText("Rispondi alle domande fatte dagli utenti");

        bRimuoviDati = new JButton("Rimuovi Faq");
        bRimuoviDati.setLocation(panelButton.getWidth()/2 - panelButton.getWidth()/6,160);
        bRimuoviDati.setSize(panelButton.getWidth()/3,50);
        bRimuoviDati.setFont(new Font("Arial", 0, 20));
        bRimuoviDati.setToolTipText("Rimuovi le faq inappropriate");

        bIndietro = new JButton("Indietro");
        bIndietro.setLocation(panelButton.getWidth()/2 - panelButton.getWidth()/6,300);
        bIndietro.setSize(panelButton.getWidth()/3, 50);
        bIndietro.setFont(new Font("Arial", 0, 20));
        bIndietro.setToolTipText("Torna alla schermata precedente");

        panelButton.add(bRispondi);
        panelButton.add(bRimuoviDati);
        panelButton.add(bIndietro);

        pannelloAmministrazione.add(panelButton);



        // Ascoltatore di bottone e relativa azione
        ascoltatoreEtAzioneRispondi = new RispondiAA();
        ascoltatoreEtAzioneRimuoviFaq = new RimuoviFaqAA();
        ascoltatoreTornaIndietro = new TornaIndietro();


        // Associazione di bottone a relativo ascoltatore
        bRispondi.addActionListener(ascoltatoreEtAzioneRispondi);
        bRimuoviDati.addActionListener(ascoltatoreEtAzioneRimuoviFaq);
        bIndietro.addActionListener(ascoltatoreTornaIndietro);

    }
    // Fine costruttore

    // Ascoltatori

    private class RispondiAA implements ActionListener
    {
        public void actionPerformed(ActionEvent arg0)
        {
            try
            {
                pannelloAmministrazione.setVisible(false);
                new BoundaryRispostaFaq();

            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    private class RimuoviFaqAA implements ActionListener
    {
        public void actionPerformed(ActionEvent arg0)
        {
            try
            {
                pannelloAmministrazione.setVisible(false);
                new BoundaryRimuoviFaq();

            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    private class TornaIndietro implements ActionListener
    {
        public void actionPerformed(ActionEvent arg0)
        {
            try {
                pannelloAmministrazione.setVisible(false);
                new BoundaryAmministrazione();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

