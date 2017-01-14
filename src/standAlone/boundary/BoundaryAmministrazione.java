package standAlone.boundary;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class BoundaryAmministrazione {
	
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
	public JButton bRisposta;
	
	// Ascoltatore di bottone e relativa azioni
	private RimuoviAA ascoltatoreEtAzioneRimuovi;
	private NominaAA ascoltatoreEtAzioneNomina;
    private TornaIndietro ascoltatoreTornaIndietro;
	private RispondiFaqAA ascoltatoreRispostaFaq;

	
	public BoundaryAmministrazione()
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
        titolo.setText("Benvenuto, scegli cosa fare");
        
        pannelloAmministrazione.add(panelTitolo);	
        
		panelButton.setLayout(null);
		panelButton.setSize(BoundaryAvvio.Confine.getWidth(),500);
		panelButton.setLocation(5, 30); 
		
		bRimuovi = new JButton("Rimuovi Utente");
		bRimuovi.setLocation(panelButton.getWidth()/2 - panelButton.getWidth()/6,80);
		bRimuovi.setSize(panelButton.getWidth()/3,50);
		bRimuovi.setFont(new Font("Arial", 0, 20));
		bRimuovi.setToolTipText("Rimuovi Utente"); 
		
		bNomina = new JButton("Crea Amministratore");
		bNomina.setLocation(panelButton.getWidth()/2 - panelButton.getWidth()/6,160);
		bNomina.setSize(panelButton.getWidth()/3,50);
		bNomina.setFont(new Font("Arial", 0, 20));
		bNomina.setToolTipText("Nomina un nuovo amministratore");

        bIndietro = new JButton("Indietro");
        bIndietro.setLocation(panelButton.getWidth()/2 - panelButton.getWidth()/6,380);
		bIndietro.setSize(panelButton.getWidth()/3, 50);
        bIndietro.setFont(new Font("Arial", 0, 20));
        bIndietro.setToolTipText("Torna alla schermata precedente");

		bRisposta = new JButton("Rispondi FAQ");
		bRisposta.setLocation(panelButton.getWidth()/2 - panelButton.getWidth()/6,240);
		bRisposta.setSize(panelButton.getWidth()/3, 50);
		bRisposta.setFont(new Font("Arial", 0, 20));
		bRisposta.setToolTipText("Rispondi FAQ");

		panelButton.add(bRimuovi);
		panelButton.add(bNomina);
        panelButton.add(bIndietro);
		panelButton.add(bRisposta);
        	
		pannelloAmministrazione.add(panelButton);
		
	  	
					
		// Ascoltatore di bottone e relativa azione
		ascoltatoreEtAzioneRimuovi = new RimuoviAA();
		ascoltatoreEtAzioneNomina = new NominaAA();
        ascoltatoreTornaIndietro = new TornaIndietro();
		ascoltatoreRispostaFaq = new RispondiFaqAA();


		// Associazione di bottone a relativo ascoltatore
		bRimuovi.addActionListener(ascoltatoreEtAzioneRimuovi);
		bNomina.addActionListener(ascoltatoreEtAzioneNomina);
        bIndietro.addActionListener(ascoltatoreTornaIndietro);
		bRisposta.addActionListener(ascoltatoreRispostaFaq);

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

	
	private class NominaAA implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0)
		{				
			try 
			{
				pannelloAmministrazione.setVisible(false);
				new BoundaryCreaAmministratore();
				
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
                new BoundaryLogin();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

	private class RispondiFaqAA implements ActionListener
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

}
