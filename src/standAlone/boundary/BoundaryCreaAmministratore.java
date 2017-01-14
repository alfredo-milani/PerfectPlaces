package standAlone.boundary;

import standAlone.control.ControlloreCreaAmministratore;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class BoundaryCreaAmministratore {


	//Pannelli
	public JPanel pannelloCreazione;
	public JPanel panelTitolo = new JPanel();
	public JPanel panelUsername = new JPanel();
	public JPanel panelPassword = new JPanel();
	public JPanel panelButtonCrea = new JPanel();

	//Label
	public JLabel titolo = new JLabel();

	//Bottoni
	public JButton bNomina;
	public JButton bIndietro;

	// Campi e loro etichette
	public JLabel usernameLabel = new JLabel();
	public JTextField usernameF = new JTextField();
	public JLabel passwordLabel = new JLabel();
	public JPasswordField passwordF = new JPasswordField();

	// Ascoltatore di bottone e relativa azioni
	private CreaAA ascoltatoreEtAzioneCrea;
	private tornaIndietroAA ascoltatoreEtAzioneIndietro;


	public BoundaryCreaAmministratore()
	{
		int border = 5;

		pannelloCreazione = new JPanel();

		pannelloCreazione.setSize(BoundaryAvvio.Confine.getWidth(), BoundaryAvvio.Confine.getHeight());
		BoundaryAvvio.Confine.add(pannelloCreazione);
		pannelloCreazione.setLayout(null);


		panelTitolo.setLayout(null);
		panelTitolo.setSize(BoundaryAvvio.Confine.getWidth(), 45);
		panelTitolo.setLocation(5, 5);
		panelTitolo.add(titolo);

		titolo.setFont(new Font("Verdana", Font.BOLD, 20));
		titolo.setLocation(border, border);
		titolo.setSize(panelTitolo.getWidth(), 35);
		titolo.setHorizontalAlignment(JLabel.CENTER);
		titolo.setVerticalAlignment(JLabel.CENTER);
		titolo.setText("Completa i campi indicati");

		pannelloCreazione.add(panelTitolo);


		// Creazione etichette campi con relativi nomi

		usernameLabel.setFont(new Font("Verdana", Font.BOLD, 15));
		usernameLabel.setLocation(150, 50);
		usernameLabel.setSize(panelTitolo.getWidth()/2, 30);
		usernameLabel.setText("Username: ");


		usernameF = new JTextField("", 60);
		usernameF.setFont(new Font("Verdana", 0, 15));
		usernameF.setLocation(300,50);
		usernameF.setSize(panelTitolo.getWidth()/2, 30);

		passwordLabel.setFont(new Font("Verdana", Font.BOLD, 15));
		passwordLabel.setLocation(150, 50);
		passwordLabel.setSize(panelTitolo.getWidth()/2, 30);
		passwordLabel.setText("Password: ");


		passwordF = new JPasswordField("", 60);
		passwordF.setLocation(300,50);
		passwordF.setSize(panelTitolo.getWidth()/2, 30);
		passwordF.setFont(new Font("Verdana", 0, 15));

		// Creazione bottoni
		bNomina = new JButton("Crea Amministratore");
		bNomina.setLocation(150,50);
		bNomina.setSize(panelTitolo.getWidth()/3, 50);
		bNomina.setFont(new Font("Arial", 0, 20));

		bIndietro = new JButton("Indietro");
		bIndietro.setLocation(300+panelTitolo.getWidth()/4, 50);
		bIndietro.setSize(panelTitolo.getWidth()/4, 50);
		bIndietro.setFont(new Font("Arial", 0, 20));
		bIndietro.setToolTipText("Torna alla schermata precedente");

		panelUsername.setLayout(null);
		panelUsername.setSize(BoundaryAvvio.Confine.getWidth(), BoundaryAvvio.Confine.getHeight()/5);
		panelUsername.setLocation(5, 70);
		panelUsername.setBackground(new Color(190, 190, 190));
		panelUsername.add(usernameLabel);
		panelUsername.add(usernameF);

		panelPassword.setLayout(null);
		panelPassword.setSize(BoundaryAvvio.Confine.getWidth(), BoundaryAvvio.Confine.getHeight()/5);
		panelPassword.setLocation(5, 200);
		panelPassword.setBackground(new Color(190, 190, 190));
		panelPassword.add(passwordLabel);
		panelPassword.add(passwordF);

		panelButtonCrea.setLayout(null);
		panelButtonCrea.setSize(BoundaryAvvio.Confine.getWidth(), BoundaryAvvio.Confine.getHeight()/5);
		panelButtonCrea.setLocation(5, 430);

		panelButtonCrea.add(bNomina);
		panelButtonCrea.add(bIndietro);


		pannelloCreazione.add(panelUsername);
		pannelloCreazione.add(panelPassword);
		pannelloCreazione.add(panelButtonCrea);



		// Ascoltatori dei bottoni e relativa azione
		ascoltatoreEtAzioneCrea = new CreaAA();
		ascoltatoreEtAzioneIndietro = new tornaIndietroAA();


		// Associazione di bottone a relativo ascoltatore
		bNomina.addActionListener(ascoltatoreEtAzioneCrea);
		bIndietro.addActionListener(ascoltatoreEtAzioneIndietro);

	}
	// Fine costruttore


	// Ascoltatore

	private class CreaAA implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0)
		{
			try
			{
				pannelloCreazione.setVisible(false);
				ControlloreCreaAmministratore cna = new ControlloreCreaAmministratore();
				cna.creaAmministratore(usernameF.getText(), passwordF.getPassword());

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
				pannelloCreazione.setVisible(false);
				new BoundaryAmministrazione();

			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}
}