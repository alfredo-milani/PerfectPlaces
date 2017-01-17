package standAlone.boundary;

import constants.Constants;
import standAlone.control.ControlloreLinguaAmministratore;
import standAlone.control.ControlloreLoginAmministratore;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

public class BoundaryLogin extends JFrame
{
    private static final long	serialVersionUID	= 1L;


    //Pannelli
    public JPanel  pannelloLogin;
    public JPanel panelTitolo = new JPanel();
    public JPanel panelCredenzialiLogin = new JPanel();
    public JPanel panelCredenzialiPsw = new JPanel();
    public JPanel panelButtonLogin = new JPanel();
    public JPanel panelBox = new JPanel();

    //Label
    public JLabel titolo = new JLabel();
    private JLabel boxLabel;

    private BoxSelectItem ascoltatoreBox;

    //Bottone
    public JButton bLogin;


    // Campi e loro etichette
    public JLabel     loginLabel = new JLabel();
    public JTextField loginF = new JTextField();
    public JLabel     passwordLabel = new JLabel();
    public JPasswordField passwordF= new JPasswordField();

    private JComboBox<String> box;

    // Ascoltatore di bottone e relativa azioni
    private LoginAA   ascoltatoreEtAzioneLogin;

    private ControlloreLinguaAmministratore cl;



    public BoundaryLogin()
    {
        this.boxLabel = new JLabel();
        this.box = new JComboBox<>(Constants.LANGS);

        this.cl = new ControlloreLinguaAmministratore();
        ResourceBundle bundle = this.cl.getBundleFromProp();

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
        titolo.setText(bundle.getString("boundaryLogin_credenziali"));

        // Composizione box selettore lingua
        this.panelBox.setLayout(new FlowLayout());
        this.panelBox.setSize(BoundaryAvvio.Confine.getWidth(), BoundaryAvvio.Confine.getHeight()/12);
        this.panelBox.setLocation(0, 480);
        this.panelBox.add(boxLabel);
        this.panelBox.add(box);

        this.boxLabel.setFont(new Font("Verdana", Font.BOLD, 15));
        this.boxLabel.setLocation(450, 20);
        this.boxLabel.setSize(panelTitolo.getWidth()/2, 30);
        this.boxLabel.setText(bundle.getString("modificaProfiloUtente_linguaCorrente"));

        pannelloLogin.add(panelTitolo);

        loginLabel.setFont(new Font("Verdana", Font.BOLD, 15));
        loginLabel.setLocation(150, 50);
        loginLabel.setSize(panelTitolo.getWidth()/2, 30);
        loginLabel.setText(bundle.getString("index_nomeUtente"));


        loginF = new JTextField("", 60);
        loginF.setFont(new Font("Verdana", 0, 15));
        loginF.setLocation(300,50);
        loginF.setSize(panelTitolo.getWidth()/2, 30);



        passwordLabel.setFont(new Font("Verdana", Font.BOLD, 15));
        passwordLabel.setLocation(150, 50);
        passwordLabel.setSize(panelTitolo.getWidth()/2, 30);
        passwordLabel.setText(bundle.getString("index_password"));

        passwordF = new JPasswordField("", 60);
        passwordF.setLocation(300,50);
        passwordF.setSize(panelTitolo.getWidth()/2, 30);
        passwordF.setFont(new Font("Verdana", 0, 15));

        // Creazione bottone
        bLogin = new JButton(bundle.getString("index_accedi"));
        bLogin.setLocation(320,50);
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
        pannelloLogin.add(panelBox);


        // Ascoltatore di bottone e relativa azione
        ascoltatoreEtAzioneLogin = new LoginAA();
        ascoltatoreBox = new BoxSelectItem(this.getClass());

        // Associazione di bottone  a relativo ascoltatore
        bLogin.addActionListener(ascoltatoreEtAzioneLogin);
        passwordF.addActionListener(ascoltatoreEtAzioneLogin);
        this.box.addActionListener(ascoltatoreBox);

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

    public class BoxSelectItem implements ActionListener {
        // Reflection
        private Class<?> aClass;

        BoxSelectItem(Class<?> aClass) {
            this.aClass = aClass;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JComboBox combo = (JComboBox) e.getSource();

            if (combo.getSelectedIndex() != -1) {
                String selectedLang = combo
                        .getSelectedItem()
                        .toString();

                System.setProperty(Constants.LINGUA_KEY, selectedLang);

                pannelloLogin.setVisible(false);

                try {
                    this.aClass
                            .newInstance();
                } catch (IllegalAccessException |
                        InstantiationException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

}