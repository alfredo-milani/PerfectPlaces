package standAlone.boundary;

import constants.Constants;
import entity.Utente;
import standAlone.control.ControlloreProfiloAmministratore;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;


public class BoundaryLoginErrato extends JFrame {
	
private static final long	serialVersionUID	= 1L;
	

	// Pannelli
	public JPanel pannelloLoginErrato;	
	public JPanel panelTitolo = new JPanel();
	public JPanel panelButtonIndietro = new JPanel();
	
	//Label
	public JLabel titolo = new JLabel();
	
	//Bottone
	public JButton bIndietro; 
	
	// Ascoltatore di bottone e relativa azioni
	private IndietroAA ascoltatoreEtAzioneIndietro;
	

	
	public BoundaryLoginErrato()
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
		
		pannelloLoginErrato = new JPanel();
		
		pannelloLoginErrato.setSize(BoundaryAvvio.Confine.getWidth(), BoundaryAvvio.Confine.getHeight());
		BoundaryAvvio.Confine.add(pannelloLoginErrato);
		pannelloLoginErrato.setLayout(null);
		
        panelTitolo.setLayout(null);
        panelTitolo.setSize(BoundaryAvvio.Confine.getWidth(), 45);
        panelTitolo.setLocation(5, 5);  
        panelTitolo.add(titolo);
        
        titolo.setFont(new Font("Verdana", Font.BOLD, 20));
        titolo.setLocation(border, border);
        titolo.setSize(panelTitolo.getWidth(), 35);
        titolo.setHorizontalAlignment(JLabel.CENTER);
        titolo.setVerticalAlignment(JLabel.CENTER);
        titolo.setText(bundle.getString("boundaryLoginerrato_parametri_errati"));
        
        pannelloLoginErrato.add(panelTitolo);	
        
		panelButtonIndietro.setLayout(null);
		panelButtonIndietro.setSize(BoundaryAvvio.Confine.getWidth(), 150);
		panelButtonIndietro.setLocation(5, 30); 
		
		
		
		bIndietro = new JButton(bundle.getString("visualizzaPosta_indietro"));
		bIndietro.setLocation(300,100);
		bIndietro.setSize(panelTitolo.getWidth()/4,50);
		bIndietro.setFont(new Font("Arial", 0, 20));
		bIndietro.setToolTipText(bundle.getString("visualizzaPosta_indietro"));
		
		panelButtonIndietro.add(bIndietro);
        	
		pannelloLoginErrato.add(panelButtonIndietro);
		
	  	
					
		// Ascoltatore di bottone e relativa azione
		ascoltatoreEtAzioneIndietro = new IndietroAA();
		
		
		// Associazione di bottone a relativo ascoltatore
		bIndietro.addActionListener(ascoltatoreEtAzioneIndietro);
		
	}
	// Fine costruttore
	
	
	//Ascoltatore
	private class IndietroAA implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0)
		{				
			try 
			{
                pannelloLoginErrato.setVisible(false);
                new BoundaryLogin();
				
			} 
			catch (Exception e) 
			{				
				e.printStackTrace();					
			}
		}
	}		
}