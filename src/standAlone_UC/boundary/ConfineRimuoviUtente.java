package standAlone_UC.boundary;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import standAlone_UC.control.ControlloreRimuoviUtente;

public class ConfineRimuoviUtente {
	
	//Pannelli
	public JPanel pannelloRimuovi;
	public JPanel panelTitolo = new JPanel();
	public JPanel panelUser = new JPanel();
	public JPanel panelButton = new JPanel();
	
	//Label
	public JLabel titolo = new JLabel();
	
	//Bottone
	public JButton bRimuovi;
    public JButton bIndietro;

	// Campi e loro etichette
	public JLabel rimuoviLabel = new JLabel();
	public JTextField rimuoviF = new JTextField();
	
	// Ascoltatore di bottone e relativa azioni
	private RimuoviAA ascoltatoreEtAzioneRimuovi;
	
	public ConfineRimuoviUtente()  
	{
		int border = 5;
		
		pannelloRimuovi = new JPanel();
		
		pannelloRimuovi.setSize(ConfineAvvio.Confine.getWidth(), ConfineAvvio.Confine.getHeight());	
		ConfineAvvio.Confine.add(pannelloRimuovi);
		pannelloRimuovi.setLayout(null);
		
		        
        panelTitolo.setLayout(null);
        panelTitolo.setSize(ConfineAvvio.Confine.getWidth(), 45);
        panelTitolo.setLocation(5, 5);  
        panelTitolo.add(titolo);
        
        titolo.setFont(new Font("Verdana", Font.BOLD, 20));
        titolo.setLocation(border, border);
        titolo.setSize(panelTitolo.getWidth(), 35);
        titolo.setHorizontalAlignment(JLabel.CENTER);
        titolo.setVerticalAlignment(JLabel.CENTER);
        titolo.setText("Indica l'username dell'getUtente da rimuovere!");
        
        pannelloRimuovi.add(panelTitolo);
        

		// Creazione etichette campi con relativi nomi
		
        rimuoviLabel.setFont(new Font("Verdana", Font.BOLD, 15));
        rimuoviLabel.setLocation(150, 50);
        rimuoviLabel.setSize(panelTitolo.getWidth()/2, 30);
        rimuoviLabel.setText("Username: ");
		
        
		rimuoviF = new JTextField("", 60); 
		rimuoviF.setFont(new Font("Verdana", 0, 15));
		rimuoviF.setLocation(300,50);
		rimuoviF.setSize(panelTitolo.getWidth()/2, 30);
			
		// Creazione bottone
		bRimuovi = new JButton("Rimuovi Utente");
		bRimuovi.setLocation(300,50);
		bRimuovi.setSize(panelTitolo.getWidth()/4, 50);
		bRimuovi.setFont(new Font("Arial", 0, 20));

        bIndietro = new JButton("Indietro");
        bIndietro.setLocation(300, 100);
        bIndietro.setSize(panelButton.getWidth()/3, 50);
        bIndietro.setFont(new Font("Arial", 0, 20));
        bIndietro.setToolTipText("Torna alla schermata precedente");

		panelUser.setLayout(null);
		panelUser.setSize(ConfineAvvio.Confine.getWidth(), ConfineAvvio.Confine.getHeight()/5);
		panelUser.setLocation(5, 70); 
		panelUser.setBackground(new Color(190, 190, 190));
		panelUser.add(rimuoviLabel);
		panelUser.add(rimuoviF);
		
		panelButton.setLayout(null);
		panelButton.setSize(ConfineAvvio.Confine.getWidth(), ConfineAvvio.Confine.getHeight()/5);
		panelButton.setLocation(5, 330);
		panelButton.add(bRimuovi);
		
				
		pannelloRimuovi.add(panelUser);
		pannelloRimuovi.add(panelButton);
        panelButton.add(bIndietro);
		
	  	
					
		// Ascoltatore di bottone e relativa azione
		ascoltatoreEtAzioneRimuovi = new RimuoviAA();

		
		// Associazione di bottone a relativo ascoltatore
		bRimuovi.addActionListener(ascoltatoreEtAzioneRimuovi);
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
}

