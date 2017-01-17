package standAlone.boundary;


import standAlone.control.ControlloreLinguaAmministratore;
import standAlone.control.ControlloreRimuoviDatiUtente;
import standAlone.utils.StampaStringhe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

public class BoundaryRimuoviDatiUtente {

    //Pannelli
    public JPanel pannelloRimuoviDati;
    public JPanel panelTitolo = new JPanel();
    public JPanel panelUser = new JPanel();
    public JPanel panelButton = new JPanel();
    public JPanel panelVisualButton= new JPanel();
    public JPanel panelTextArea = new JPanel();

    //Label
    public JLabel titolo = new JLabel();

    //Area di testo
    JTextArea utenti_lista = new JTextArea();
    JScrollPane lista_scroll = new JScrollPane(utenti_lista,
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);


    //Bottone
    public JButton bVisualizza;
    public JButton bRimuovi;
    public JButton bIndietro;


    // Campi e loro etichette
    public JLabel rimuoviLabel = new JLabel();
    public JTextField rimuoviF = new JTextField();

    // Ascoltatore di bottone e relative azioni
    private RimuoviDatiAA ascoltatoreEtAzioneRimuoviDati;
    private VisualizzaAA ascoltatoreEtAzioneVisualizza;
    private tornaIndietroAA ascoltatoreEtAzioneIndietro;

    private ControlloreLinguaAmministratore cl;

    public BoundaryRimuoviDatiUtente()
    {
        this.cl = new ControlloreLinguaAmministratore();
        ResourceBundle bundle = this.cl.getBundleFromProp();

        int border = 5;

        pannelloRimuoviDati = new JPanel();

        pannelloRimuoviDati.setSize(BoundaryAvvio.Confine.getWidth(), BoundaryAvvio.Confine.getHeight());
        BoundaryAvvio.Confine.add(pannelloRimuoviDati);
        pannelloRimuoviDati.setLayout(null);

        panelTextArea.setLayout(new BorderLayout());
        panelTextArea.setSize(BoundaryAvvio.Confine.getWidth()*8/9, BoundaryAvvio.Confine.getHeight()/6);
        panelTextArea.setLocation(50, 50);

        utenti_lista.setEditable(false);
        utenti_lista.setLineWrap(true);
        utenti_lista.setWrapStyleWord(true);
        panelTextArea.add(lista_scroll,BorderLayout.CENTER);


        panelTitolo.setLayout(null);
        panelTitolo.setSize(BoundaryAvvio.Confine.getWidth(), 45);
        panelTitolo.setLocation(5, 5);
        panelTitolo.add(titolo);

        titolo.setFont(new Font("Verdana", Font.BOLD, 20));
        titolo.setLocation(border, border);
        titolo.setSize(panelTitolo.getWidth(), 35);
        titolo.setHorizontalAlignment(JLabel.CENTER);
        titolo.setVerticalAlignment(JLabel.CENTER);
        titolo.setText(bundle.getString("boundaryRimuoviDati_utente_da_rimuovere"));

        pannelloRimuoviDati.add(panelTitolo);


        // Creazione etichette campi con relativi nomi

        rimuoviLabel.setFont(new Font("Verdana", Font.BOLD, 15));
        rimuoviLabel.setLocation(150, 50);
        rimuoviLabel.setSize(panelTitolo.getWidth()/2, 30);
        rimuoviLabel.setText(bundle.getString("index_nomeUtente"));


        rimuoviF = new JTextField("", 60);
        rimuoviF.setFont(new Font("Verdana", 0, 15));
        rimuoviF.setLocation(300,50);
        rimuoviF.setSize(panelTitolo.getWidth()/2, 30);

        // Creazione bottone
        bVisualizza = new JButton(bundle.getString("boundaryRimuoviUtente_visualizza_utenti"));
        bVisualizza.setLocation(330,0);
        bVisualizza.setSize(panelTitolo.getWidth()/4, 50);
        bVisualizza.setFont(new Font("Arial", 0, 20));

        bRimuovi = new JButton(bundle.getString("boundaryRimuoviDati_rimuovi_dati"));
        bRimuovi.setLocation(150,10);
        bRimuovi.setSize(panelTitolo.getWidth()/3, 50);
        bRimuovi.setFont(new Font("Arial", 0, 20));

        bIndietro = new JButton(bundle.getString("boundaryProfilo_indietro"));
        bIndietro.setLocation(300+panelTitolo.getWidth()/4, 10);
        bIndietro.setSize(panelTitolo.getWidth()/4, 50);
        bIndietro.setFont(new Font("Arial", 0, 20));
        bIndietro.setToolTipText(bundle.getString("boundaryAmministrazione_schermata_prec"));

        panelUser.setLayout(null);
        panelUser.setSize(BoundaryAvvio.Confine.getWidth()*90/91, BoundaryAvvio.Confine.getHeight()/5);
        panelUser.setLocation(5, 300);
        panelUser.setBackground(new Color(190, 190, 190));
        panelUser.add(rimuoviLabel);
        panelUser.add(rimuoviF);

        panelVisualButton.setLayout(null);
        panelVisualButton.setSize(BoundaryAvvio.Confine.getWidth(), BoundaryAvvio.Confine.getHeight()/6);
        panelVisualButton.setLocation(5, 190);
        panelVisualButton.add(bVisualizza);

        panelButton.setLayout(null);
        panelButton.setSize(BoundaryAvvio.Confine.getWidth(), BoundaryAvvio.Confine.getHeight()/8);
        panelButton.setLocation(5, 480);
        panelButton.add(bRimuovi);



        pannelloRimuoviDati.add(panelUser);
        pannelloRimuoviDati.add(panelButton);
        pannelloRimuoviDati.add(panelVisualButton);
        panelButton.add(bIndietro);
        pannelloRimuoviDati.add(panelTextArea);



        // Ascoltatore di bottone e relativa azione
        ascoltatoreEtAzioneVisualizza = new VisualizzaAA();
        ascoltatoreEtAzioneRimuoviDati = new RimuoviDatiAA();
        ascoltatoreEtAzioneIndietro = new tornaIndietroAA();



        // Associazione di bottone a relativo ascoltatore
        bVisualizza.addActionListener(ascoltatoreEtAzioneVisualizza);
        bRimuovi.addActionListener(ascoltatoreEtAzioneRimuoviDati);
        bIndietro.addActionListener(ascoltatoreEtAzioneIndietro);
    }
    // Fine costruttore

    //Ascoltatore

    private class RimuoviDatiAA implements ActionListener
    {
        public void actionPerformed(ActionEvent arg0)
        {
            try
            {
                pannelloRimuoviDati.setVisible(false);
                ControlloreRimuoviDatiUtente crd = new ControlloreRimuoviDatiUtente();
                crd.rimuovi(rimuoviF.getText());
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    private class VisualizzaAA implements ActionListener
    {
        public void actionPerformed(ActionEvent arg0)
        {
            try
            {
                StampaStringhe vu = new StampaStringhe(utenti_lista);
                vu.visualizzaUtenti();
                vu.visualizzaUtentiRimossiMaConDati();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }


    private class tornaIndietroAA implements ActionListener
    {
        public void actionPerformed(ActionEvent arg0)
        {
            try
            {
                pannelloRimuoviDati.setVisible(false);
                new BoundaryAmministrazione();

            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }


}
