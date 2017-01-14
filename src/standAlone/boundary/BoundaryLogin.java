package standAlone.boundary;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import standAlone.control.ControlloreLoginAmministratore;

public class BoundaryLogin extends JFrame
{
    private static final long	serialVersionUID	= 1L;


    //Pannelli
    public JPanel  pannelloLogin;
    public JPanel panelTitolo = new JPanel();
    public JPanel panelCredenzialiLogin = new JPanel();
    public JPanel panelCredenzialiPsw = new JPanel();
    public JPanel panelButtonLogin = new JPanel();

    //Label
    public JLabel titolo = new JLabel();

    //Bottone
    public JButton bLogin;

    // Campi e loro etichette
    public JLabel     loginLabel = new JLabel();
    public JTextField loginF = new JTextField();
    public JLabel     passwordLabel = new JLabel();
    public JPasswordField passwordF= new JPasswordField();

    // Ascoltatore di bottone e relativa azioni
    private LoginAA   ascoltatoreEtAzioneLogin;



    public BoundaryLogin()
    {
        int border = 5;

        pannelloLogin = new JPanel();

        pannelloLogin.setSize(BoundaryAvvio.Confine.getWidth(), BoundaryAvvio.Confine.getHeight());
        BoundaryAvvio.Confine.add(pannelloLogin);
        pannelloLogin.setLayout(null);


        panelTitolo.setLayout(null);
        panelTitolo.setSize(BoundaryAvvio.Confine.getWidth(), 45);
        panelTitolo.setLocation(5, 5);
        panelTitolo.add(titolo);

        titolo.setFont(new Font("Verdana", Font.BOLD, 20));
        titolo.setLocation(border, border);
        titolo.setSize(panelTitolo.getWidth(), 35);
        titolo.setHorizontalAlignment(JLabel.CENTER);
        titolo.setVerticalAlignment(JLabel.CENTER);
        titolo.setText("Inserire le proprie credenziali: Login e Password");

        pannelloLogin.add(panelTitolo);

        loginLabel.setFont(new Font("Verdana", Font.BOLD, 15));
        loginLabel.setLocation(150, 50);
        loginLabel.setSize(panelTitolo.getWidth()/2, 30);
        loginLabel.setText("Login: ");


        loginF = new JTextField("", 60);
        loginF.setFont(new Font("Verdana", 0, 15));
        loginF.setLocation(300,50);
        loginF.setSize(panelTitolo.getWidth()/2, 30);



        passwordLabel.setFont(new Font("Verdana", Font.BOLD, 15));
        passwordLabel.setLocation(150, 50);
        passwordLabel.setSize(panelTitolo.getWidth()/2, 30);
        passwordLabel.setText("Password: ");

        passwordF = new JPasswordField("", 60);
        passwordF.setLocation(300,50);
        passwordF.setSize(panelTitolo.getWidth()/2, 30);
        passwordF.setFont(new Font("Verdana", 0, 15));

        // Creazione bottone
        bLogin = new JButton("Login");
        bLogin.setLocation(300,50);
        bLogin.setSize(panelTitolo.getWidth()/4, 50);
        bLogin.setFont(new Font("Arial", 0, 20));

        panelCredenzialiLogin.setLayout(null);
        panelCredenzialiLogin.setSize(BoundaryAvvio.Confine.getWidth(), BoundaryAvvio.Confine.getHeight()/5);
        panelCredenzialiLogin.setLocation(5, 70);
        panelCredenzialiLogin.setBackground(new Color(190, 190, 190));
        panelCredenzialiLogin.add(loginLabel);
        panelCredenzialiLogin.add(loginF);

        panelCredenzialiPsw.setLayout(null);
        panelCredenzialiPsw.setSize(BoundaryAvvio.Confine.getWidth(), BoundaryAvvio.Confine.getHeight()/5);
        panelCredenzialiPsw.setLocation(5, 200);
        panelCredenzialiPsw.setBackground(new Color(190, 190, 190));
        panelCredenzialiPsw.add(passwordLabel);
        panelCredenzialiPsw.add(passwordF);

        panelButtonLogin.setLayout(null);
        panelButtonLogin.setSize(BoundaryAvvio.Confine.getWidth(), BoundaryAvvio.Confine.getHeight()/5);
        panelButtonLogin.setLocation(5, 330);
        panelButtonLogin.add(bLogin);


        pannelloLogin.add(panelCredenzialiLogin);
        pannelloLogin.add(panelCredenzialiPsw);
        pannelloLogin.add(panelButtonLogin);



        // Ascoltatore di bottone e relativa azione
        ascoltatoreEtAzioneLogin = new LoginAA();


        // Associazione di bottone  a relativo ascoltatore
        bLogin.addActionListener(ascoltatoreEtAzioneLogin);
        passwordF.addActionListener(ascoltatoreEtAzioneLogin);

        loginF.requestFocusInWindow();

    }
    // Fine costruttore

    // Ascoltatore

    private class LoginAA implements ActionListener
    {
        public void actionPerformed(ActionEvent arg0)
        {
            try
            {
                pannelloLogin.setVisible(false);
                ControlloreLoginAmministratore cla = new ControlloreLoginAmministratore();
                cla.login(loginF.getText(), passwordF.getPassword());

            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}