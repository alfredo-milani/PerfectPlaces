
package standAlone.boundary;

import standAlone.control.ControlloreLinguaAmministratore;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;


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

    private ControlloreLinguaAmministratore cl;



    public BoundaryGestioneFaq()
    {
        this.cl = new ControlloreLinguaAmministratore();
        ResourceBundle bundle = this.cl.getBundleFromProp();

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
        titolo.setText(bundle.getString("posta_azioni"));

        pannelloAmministrazione.add(panelTitolo);

        panelButton.setLayout(null);
        panelButton.setSize(BoundaryAvvio.Confine.getWidth(),400);
        panelButton.setLocation(5, 30);

        bRispondi = new JButton(bundle.getString("boundaryFaq_rispondi_faq"));
        bRispondi.setLocation(panelButton.getWidth()/2 - panelButton.getWidth()/6,80);
        bRispondi.setSize(panelButton.getWidth()/3,50);
        bRispondi.setFont(new Font("Arial", 0, 20));
        bRispondi.setToolTipText(bundle.getString("boundaryFaq_domande_utenti"));

        bRimuoviDati = new JButton(bundle.getString("boundaryFaq_rimuovi"));
        bRimuoviDati.setLocation(panelButton.getWidth()/2 - panelButton.getWidth()/6,160);
        bRimuoviDati.setSize(panelButton.getWidth()/3,50);
        bRimuoviDati.setFont(new Font("Arial", 0, 20));
        bRimuoviDati.setToolTipText(bundle.getString("boundaryFaq_inappropriate"));

        bIndietro = new JButton(bundle.getString("visualizzaPosta_indietro"));
        bIndietro.setLocation(panelButton.getWidth()/2 - panelButton.getWidth()/6,300);
        bIndietro.setSize(panelButton.getWidth()/3, 50);
        bIndietro.setFont(new Font("Arial", 0, 20));
        bIndietro.setToolTipText(bundle.getString("boundaryAmministrazione_schermata_prec"));

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

