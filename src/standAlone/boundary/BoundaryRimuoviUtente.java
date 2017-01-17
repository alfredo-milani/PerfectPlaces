package standAlone.boundary;

import constants.Constants;
import entity.Utente;
import standAlone.control.ControlloreProfiloAmministratore;
import standAlone.control.ControlloreRimuoviUtente;
import standAlone.utils.StampaStringhe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;

public class BoundaryRimuoviUtente {
	
	//Pannelli
	public JPanel pannelloRimuovi;
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
	private RimuoviAA ascoltatoreEtAzioneRimuovi;
	private VisualizzaAA ascoltatoreEtAzioneVisualizza;
    private tornaIndietroAA ascoltatoreEtAzioneIndietro;
	
	public BoundaryRimuoviUtente()
	{
        ControlloreProfiloAmministratore cp =
                new ControlloreProfiloAmministratore();
        Utente utente = cp.ottieniUtente(System.getProperty(Constants.USER_KEY));

        Locale langLocale;
        if (utente != null) {
            langLocale = utente.getLingua();
        } else {
            langLocale = Locale.getDefault();
        }

        ResourceBundle bundle = ResourceBundle
                .getBundle(Constants.PACKAGE_LANGUAGE, langLocale);

		int border = 5;
		
		pannelloRimuovi = new JPanel();
		
		pannelloRimuovi.setSize(BoundaryAvvio.Confine.getWidth(), BoundaryAvvio.Confine.getHeight());
		BoundaryAvvio.Confine.add(pannelloRimuovi);
		pannelloRimuovi.setLayout(null);

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
        titolo.setText(bundle.getString("boundaryRimuoviUtente"));
        
        pannelloRimuovi.add(panelTitolo);
        

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

		bRimuovi = new JButton(bundle.getString("boundaryGestioneUtenti_rimuovi_utente"));
		bRimuovi.setLocation(200,10);
		bRimuovi.setSize(panelTitolo.getWidth()/4, 50);
		bRimuovi.setFont(new Font("Arial", 0, 20));

        bIndietro = new JButton(bundle.getString("boundaryProfilo_indietro"));
        bIndietro.setLocation(300+panelTitolo.getWidth()/4, 10);
        bIndietro.setSize(panelTitolo.getWidth()/4, 50);
        bIndietro.setFont(new Font("Arial", 0, 20));
        bIndietro.setToolTipText(bundle.getString("boundaryAmministrazione_schermata_prec"));

		panelUser.setLayout(null);
		panelUser.setSize(BoundaryAvvio.Confine.getWidth(), BoundaryAvvio.Confine.getHeight()/5);
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


				
		pannelloRimuovi.add(panelUser);
		pannelloRimuovi.add(panelButton);
		pannelloRimuovi.add(panelVisualButton);
        panelButton.add(bIndietro);
		pannelloRimuovi.add(panelTextArea);

	  	
					
		// Ascoltatore di bottone e relativa azione
		ascoltatoreEtAzioneVisualizza = new VisualizzaAA();
		ascoltatoreEtAzioneRimuovi = new RimuoviAA();
        ascoltatoreEtAzioneIndietro = new tornaIndietroAA();


		
		// Associazione di bottone a relativo ascoltatore
		bVisualizza.addActionListener(ascoltatoreEtAzioneVisualizza);
		bRimuovi.addActionListener(ascoltatoreEtAzioneRimuovi);
        bIndietro.addActionListener(ascoltatoreEtAzioneIndietro);
	}
	// Fine costruttore
	
	//Ascoltatore
	
	private class RimuoviAA implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0)
		{				
			try 
			{
				pannelloRimuovi.setVisible(false);
				ControlloreRimuoviUtente cru = new ControlloreRimuoviUtente();
				cru.rimuovi(rimuoviF.getText());
				
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
                pannelloRimuovi.setVisible(false);
                new BoundaryAmministrazione();

            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

}

