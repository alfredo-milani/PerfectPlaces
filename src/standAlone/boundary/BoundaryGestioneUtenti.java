
package standAlone.boundary;

        import java.awt.Font;
        import java.awt.event.ActionEvent;
        import java.awt.event.ActionListener;

        import javax.swing.JButton;
        import javax.swing.JLabel;
        import javax.swing.JPanel;


public class BoundaryGestioneUtenti {

    // Pannelli
    public JPanel pannelloAmministrazione;
    public JPanel panelTitolo = new JPanel();
    public JPanel panelButton = new JPanel();

    // Label
    public JLabel titolo = new JLabel();

    //Bottoni
    public JButton bRimuovi;
    public JButton bRimuoviDati;
    public JButton bIndietro;

    // Ascoltatore di bottone e relativa azioni
    private RimuoviAA ascoltatoreEtAzioneRimuovi;
    private RimuoviDatiAA ascoltatoreEtAzioneRimuoviDati;
    private TornaIndietro ascoltatoreTornaIndietro;



    public BoundaryGestioneUtenti()
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

        bRimuovi = new JButton("Rimuovi Utente");
        bRimuovi.setLocation(panelButton.getWidth()/2 - panelButton.getWidth()/6,80);
        bRimuovi.setSize(panelButton.getWidth()/3,50);
        bRimuovi.setFont(new Font("Arial", 0, 20));
        bRimuovi.setToolTipText("Rimuovi Utente");

        bRimuoviDati = new JButton("Rimuovi Dati Utente");
        bRimuoviDati.setLocation(panelButton.getWidth()/2 - panelButton.getWidth()/6,160);
        bRimuoviDati.setSize(panelButton.getWidth()/3,50);
        bRimuoviDati.setFont(new Font("Arial", 0, 20));
        bRimuoviDati.setToolTipText("Rimuovi Dati Utente");

        bIndietro = new JButton("Indietro");
        bIndietro.setLocation(panelButton.getWidth()/2 - panelButton.getWidth()/6,300);
        bIndietro.setSize(panelButton.getWidth()/3, 50);
        bIndietro.setFont(new Font("Arial", 0, 20));
        bIndietro.setToolTipText("Torna alla schermata precedente");

        panelButton.add(bRimuovi);
        panelButton.add(bRimuoviDati);
        panelButton.add(bIndietro);

        pannelloAmministrazione.add(panelButton);



        // Ascoltatore di bottone e relativa azione
        ascoltatoreEtAzioneRimuovi = new RimuoviAA();
        ascoltatoreEtAzioneRimuoviDati = new RimuoviDatiAA();
        ascoltatoreTornaIndietro = new TornaIndietro();


        // Associazione di bottone a relativo ascoltatore
        bRimuovi.addActionListener(ascoltatoreEtAzioneRimuovi);
        bRimuoviDati.addActionListener(ascoltatoreEtAzioneRimuoviDati);
        bIndietro.addActionListener(ascoltatoreTornaIndietro);

    }
    // Fine costruttore

    // Ascoltatori

    private class RimuoviAA implements ActionListener
    {
        public void actionPerformed(ActionEvent arg0)
        {
            try
            {
                pannelloAmministrazione.setVisible(false);
                new BoundaryRimuoviUtente();

            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    private class RimuoviDatiAA implements ActionListener
    {
        public void actionPerformed(ActionEvent arg0)
        {
            try
            {
                pannelloAmministrazione.setVisible(false);
                new BoundaryRimuoviDatiUtente();

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
