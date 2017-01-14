package standAlone.boundary;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class BoundarySuccesso {
	
	
	//Pannelli
	public JPanel pannelloSuccesso;
	public JPanel panelTitolo = new JPanel();
	public JPanel panelButtonHome = new JPanel();
	
	//Label
	public JLabel titolo = new JLabel();
	
	//Bottone
	public JButton bHome; 
	
	// Ascoltatore di bottone e relativa azioni
	private HomeAA ascoltatoreEtAzioneHome;
	

	
	public BoundarySuccesso()
	{
		int border = 5;
		
		pannelloSuccesso = new JPanel();
		
		pannelloSuccesso.setSize(BoundaryAvvio.Confine.getWidth(), BoundaryAvvio.Confine.getHeight());
		BoundaryAvvio.Confine.add(pannelloSuccesso);
		pannelloSuccesso.setLayout(null);
		
        panelTitolo.setLayout(null);
        panelTitolo.setSize(BoundaryAvvio.Confine.getWidth(), 45);
        panelTitolo.setLocation(5, 5);  
        panelTitolo.add(titolo);
        
        titolo.setFont(new Font("Verdana", Font.BOLD, 20));
        titolo.setLocation(border, border);
        titolo.setSize(panelTitolo.getWidth(), 35);
        titolo.setHorizontalAlignment(JLabel.CENTER);
        titolo.setVerticalAlignment(JLabel.CENTER);
        titolo.setText("Operazione eseguita con successo!");
        
        pannelloSuccesso.add(panelTitolo);	
        
		panelButtonHome.setLayout(null);
		panelButtonHome.setSize(BoundaryAvvio.Confine.getWidth(), 150);
		panelButtonHome.setLocation(5, 30); 
		
		
		
		bHome = new JButton("Torna alla Home");
		bHome.setLocation(300,100);
		bHome.setSize(panelTitolo.getWidth()/4,50);
		bHome.setFont(new Font("Arial", 0, 20));
		bHome.setToolTipText("Torna alla Home"); 
		
		panelButtonHome.add(bHome);
        	
		pannelloSuccesso.add(panelButtonHome);
		
	  	
					
		// Ascoltatore di bottone e relativa azione
		ascoltatoreEtAzioneHome = new HomeAA();
		
		
		// Associazione di bottone a relativo ascoltatore
		bHome.addActionListener(ascoltatoreEtAzioneHome);
		
	}
	// Fine costruttore
	
	
	//Ascoltatore
	private class HomeAA implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0)
		{				
			try 
			{
				pannelloSuccesso.setVisible(false);
				new BoundaryAmministrazione();
				
			} 
			catch (Exception e) 
			{				
				e.printStackTrace();					
			}
		}
	}		
}
