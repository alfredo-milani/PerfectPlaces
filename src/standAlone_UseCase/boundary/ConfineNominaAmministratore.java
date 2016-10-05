package standAlone_UseCase.boundary;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import standAlone_UseCase.control.ControlloreNominaAmministratore;

public class ConfineNominaAmministratore {


	//Pannelli
	public JPanel pannelloNomina;
	public JPanel panelTitolo = new JPanel();
	public JPanel panelUser = new JPanel();
	public JPanel panelButtonNomina = new JPanel();

	//Label
	public JLabel titolo = new JLabel();

	//Bottone
	public JButton bNomina;

	// Campi e loro etichette
	public JLabel nominaLabel = new JLabel();
	public JTextField nominaF = new JTextField();

	// Ascoltatore di bottone e relativa azioni
	private NominaAA ascoltatoreEtAzioneNomina;


	public ConfineNominaAmministratore()
	{
		int border = 5;

		pannelloNomina = new JPanel();

		pannelloNomina.setSize(ConfineAvvio.Confine.getWidth(), ConfineAvvio.Confine.getHeight());
		ConfineAvvio.Confine.add(pannelloNomina);
		pannelloNomina.setLayout(null);


		panelTitolo.setLayout(null);
		panelTitolo.setSize(ConfineAvvio.Confine.getWidth(), 45);
		panelTitolo.setLocation(5, 5);
		panelTitolo.add(titolo);

		titolo.setFont(new Font("Verdana", Font.BOLD, 20));
		titolo.setLocation(border, border);
		titolo.setSize(panelTitolo.getWidth(), 35);
		titolo.setHorizontalAlignment(JLabel.CENTER);
		titolo.setVerticalAlignment(JLabel.CENTER);
		titolo.setText("Indica l'username dell'utente da nominare come Amministratore!");

		pannelloNomina.add(panelTitolo);


		// Creazione etichette campi con relativi nomi

		nominaLabel.setFont(new Font("Verdana", Font.BOLD, 15));
		nominaLabel.setLocation(150, 50);
		nominaLabel.setSize(panelTitolo.getWidth()/2, 30);
		nominaLabel.setText("Username: ");


		nominaF = new JTextField("", 60);
		nominaF.setFont(new Font("Verdana", 0, 15));
		nominaF.setLocation(300,50);
		nominaF.setSize(panelTitolo.getWidth()/2, 30);

		// Creazione bottone
		bNomina = new JButton("Nomina Amministratore");
		bNomina.setLocation(300,50);
		bNomina.setSize(panelTitolo.getWidth()/3, 50);
		bNomina.setFont(new Font("Arial", 0, 20));

		panelUser.setLayout(null);
		panelUser.setSize(ConfineAvvio.Confine.getWidth(), ConfineAvvio.Confine.getHeight()/5);
		panelUser.setLocation(5, 70);
		panelUser.setBackground(new Color(190, 190, 190));
		panelUser.add(nominaLabel);
		panelUser.add(nominaF);

		panelButtonNomina.setLayout(null);
		panelButtonNomina.setSize(ConfineAvvio.Confine.getWidth(), ConfineAvvio.Confine.getHeight()/5);
		panelButtonNomina.setLocation(5, 330);

		panelButtonNomina.add(bNomina);


		pannelloNomina.add(panelUser);
		pannelloNomina.add(panelButtonNomina);



		// Ascoltatore di bottone e relativa azione
		ascoltatoreEtAzioneNomina = new NominaAA();


		// Associazione di bottone a relativo ascoltatore
		bNomina.addActionListener(ascoltatoreEtAzioneNomina);

	}
	// Fine costruttore


	// Ascoltatore

	private class NominaAA implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0)
		{
			try
			{
				pannelloNomina.setVisible(false);
				ControlloreNominaAmministratore cna = new ControlloreNominaAmministratore();
				cna.nomina(nominaF.getText());

			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}
}