package standAlone_UseCase.boundary;

import standAlone_UseCase.control.ControlloreLoginAmministratore;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class ConfineAmministrazione {
	
	// Pannelli
	public JPanel pannelloAmministrazione;	
	public JPanel panelTitolo = new JPanel();
	public JPanel panelButton = new JPanel();
	
	// Label
	public JLabel titolo = new JLabel();
	
	//Bottoni
	public JButton bRimuovi;
	public JButton bNomina;
	public JButton bIndietro;
	
	// Ascoltatore di bottone e relativa azioni
	private RimuoviAA ascoltatoreEtAzioneRimuovi;
	private NominaAA ascoltatoreEtAzioneNomina;
    private TornaIndietro ascoltatoreTornaIndietro;

	
	public ConfineAmministrazione() 
	{
		int border = 5;
		
		pannelloAmministrazione = new JPanel();
		
		pannelloAmministrazione.setSize(ConfineAvvio.Confine.getWidth(), ConfineAvvio.Confine.getHeight());	
		ConfineAvvio.Confine.add(pannelloAmministrazione);
		pannelloAmministrazione.setLayout(null);
		
        panelTitolo.setLayout(null);
        panelTitolo.setSize(ConfineAvvio.Confine.getWidth(), 45);
        panelTitolo.setLocation(5, 5);  
        panelTitolo.add(titolo);
        
        titolo.setFont(new Font("Verdana", Font.BOLD, 20));
        titolo.setLocation(border, border);
        titolo.setSize(panelTitolo.getWidth(), 35);
        titolo.setHorizontalAlignment(JLabel.CENTER);
        titolo.setVerticalAlignment(JLabel.CENTER);
        titolo.setText("Benvenuto! Scegli cosa fare!");
        
        pannelloAmministrazione.add(panelTitolo);	
        
		panelButton.setLayout(null);
		panelButton.setSize(ConfineAvvio.Confine.getWidth(),400);
		panelButton.setLocation(5, 30); 
		
		bRimuovi = new JButton("Rimuovi Utente");
		bRimuovi.setLocation(panelButton.getWidth()/2 - panelButton.getWidth()/6,80);
		bRimuovi.setSize(panelButton.getWidth()/3,50);
		bRimuovi.setFont(new Font("Arial", 0, 20));
		bRimuovi.setToolTipText("Rimuovi Utente"); 
		
		bNomina = new JButton("Nomina Amministratore");
		bNomina.setLocation(panelButton.getWidth()/2 - panelButton.getWidth()/6,160);
		bNomina.setSize(panelButton.getWidth()/3,50);
		bNomina.setFont(new Font("Arial", 0, 20));
		bNomina.setToolTipText("Nomina un nuovo amministratore");

        bIndietro = new JButton("Indietro");
        bIndietro.setLocation(panelButton.getWidth()/2 - panelButton.getWidth()/6,300);
		bIndietro.setSize(panelButton.getWidth()/3, 50);
        bIndietro.setFont(new Font("Arial", 0, 20));
        bIndietro.setToolTipText("Torna alla schermata precedente");

		panelButton.add(bRimuovi);
		panelButton.add(bNomina);
        panelButton.add(bIndietro);
        	
		pannelloAmministrazione.add(panelButton);
		
	  	
					
		// Ascoltatore di bottone e relativa azione
		ascoltatoreEtAzioneRimuovi = new RimuoviAA();
		ascoltatoreEtAzioneNomina = new NominaAA();
        ascoltatoreTornaIndietro = new TornaIndietro();


		// Associazione di bottone a relativo ascoltatore
		bRimuovi.addActionListener(ascoltatoreEtAzioneRimuovi);
		bNomina.addActionListener(ascoltatoreEtAzioneNomina);
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
				new ConfineRimuoviUtente();
				
			} 
			catch (Exception e) 
			{				
				e.printStackTrace();					
			}
		}
	}		
	
	private class NominaAA implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0)
		{				
			try 
			{
				pannelloAmministrazione.setVisible(false);
				new ConfineNominaAmministratore();
				
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
                new ConfineLogin();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
