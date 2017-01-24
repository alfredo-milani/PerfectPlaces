package standAlone.boundary;

import standAlone.control.ControlloreLinguaAmministratore;
import standAlone.control.ControlloreRispondiFaq;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;


public class BoundaryRispostaFaq {


        ControlloreRispondiFaq crf = new ControlloreRispondiFaq();

        //Pannelli
        private JPanel pannelloFaqRisposta;
        private JPanel panelTitolo = new JPanel();
        private JPanel panelRisposta = new JPanel();
        private JPanel panelButtons = new JPanel();
        private JPanel panelBox = new JPanel();
        private JPanel panelEtichetta = new JPanel();


        //Label
        private JLabel titolo = new JLabel();

        //Text area
        JTextArea ris_lista = new JTextArea();
        JScrollPane lista_scroll = new JScrollPane(ris_lista,
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        private JLabel rispondiLabel = new JLabel();


        //Bottone

        private JButton bRispondi;
        private JButton bIndietro;

        //Combo box
        private JComboBox box;
        private String[] domande; //array di domande senza risposta
        private String domanda; // domada a cui si vuole rispondere



        // Ascoltatore di bottone e relative azioni
        private RispondiAA ascoltatoreEtAzioneRispondi;
        private tornaIndietroAA ascoltatoreEtAzioneIndietro;

        private ControlloreLinguaAmministratore cl;

        public BoundaryRispostaFaq()
        {
            this.cl = new ControlloreLinguaAmministratore();
            ResourceBundle bundle = this.cl.getBundleFromProp();

            int border = 5;
            //pannello esterno che contiene tutto
            pannelloFaqRisposta = new JPanel();

            pannelloFaqRisposta.setSize(BoundaryAvvio.Confine.getWidth(), BoundaryAvvio.Confine.getHeight());
            BoundaryAvvio.Confine.add(pannelloFaqRisposta);
            pannelloFaqRisposta.setLayout(null);

            panelBox.setLayout(new FlowLayout());
            panelBox.setSize(BoundaryAvvio.Confine.getWidth(), BoundaryAvvio.Confine.getHeight()/5);
            panelBox.setLocation(5, 50);

            this.costrusciComboBox();

            panelEtichetta.setLayout(null);
            panelEtichetta.setSize(BoundaryAvvio.Confine.getWidth(), BoundaryAvvio.Confine.getHeight()/10);
            panelEtichetta.setLocation(5, 280);
            panelEtichetta.add(rispondiLabel);


            panelTitolo.setLayout(null);
            panelTitolo.setSize(BoundaryAvvio.Confine.getWidth(), 45);
            panelTitolo.setLocation(5, 5);
            panelTitolo.add(titolo);

            titolo.setFont(new Font("Verdana", Font.BOLD, 20));
            titolo.setLocation(border, border);
            titolo.setSize(panelTitolo.getWidth(), 35);
            titolo.setHorizontalAlignment(JLabel.CENTER);
            titolo.setVerticalAlignment(JLabel.CENTER);
            titolo.setText(bundle.getString("boundaryFaq_rispondi_domande_utente"));




            // Creazione etichette campi con relativi nomi

            rispondiLabel.setFont(new Font("Verdana", Font.BOLD, 15));
            rispondiLabel.setLocation(75,30);
            rispondiLabel.setSize(panelTitolo.getWidth()/2, 30);
            rispondiLabel.setText(bundle.getString("boundaryFaq_inserisci_risposta"));



            bRispondi = new JButton(bundle.getString("visualizzaPosta_rispondi"));
            bRispondi.setLocation(150,50);
            bRispondi.setSize(panelTitolo.getWidth()/4, 50);
            bRispondi.setFont(new Font("Arial", 0, 20));

            bIndietro = new JButton(bundle.getString("visualizzaPosta_indietro"));
            bIndietro.setLocation(300+panelTitolo.getWidth()/4, 50);
            bIndietro.setSize(panelTitolo.getWidth()/4, 50);
            bIndietro.setFont(new Font("Arial", 0, 20));
            bIndietro.setToolTipText(bundle.getString("boundaryAmministrazione_schermata_prec"));

            panelRisposta.setLayout(new BorderLayout());
            panelRisposta.setSize(BoundaryAvvio.Confine.getWidth()*5/6, BoundaryAvvio.Confine.getHeight()/6);
            panelRisposta.setLocation(80, 350);
            panelRisposta.setBackground(new Color(190, 190, 190));

            ris_lista.setSize(panelTitolo.getWidth()/2, 50);
            ris_lista.setEditable(true);
            ris_lista.setLineWrap(true);
            ris_lista.setWrapStyleWord(true);
            panelRisposta.add(lista_scroll,BorderLayout.CENTER);



            panelButtons.setLayout(null);
            panelButtons.setSize(BoundaryAvvio.Confine.getWidth(), BoundaryAvvio.Confine.getHeight()/5);
            panelButtons.setLocation(5, 420);
            panelButtons.add(bRispondi);
            panelButtons.add(bIndietro);



            //aggiunta pannelli a quello piuù esterno
            pannelloFaqRisposta.add(panelRisposta);
            pannelloFaqRisposta.add(panelButtons);
            pannelloFaqRisposta.add(panelBox);
            pannelloFaqRisposta.add(panelTitolo);
            pannelloFaqRisposta.add(panelEtichetta);




            // Ascoltatore di bottone e relativa azione
            ascoltatoreEtAzioneRispondi = new RispondiAA();
            ascoltatoreEtAzioneIndietro = new tornaIndietroAA();



            // Associazione di bottone a relativo ascoltatore
            bRispondi.addActionListener(ascoltatoreEtAzioneRispondi);
            bIndietro.addActionListener(ascoltatoreEtAzioneIndietro);

        }
        // Fine costruttore

        //metodo privato chiamato dalla BoundaryRispostaFaq stessa, a cui viene delegata la corretta costruzione della ComboBox, usando opportuni controlli

        private void costrusciComboBox(){

            domande = crf.visualizzaDomandeSenzaRisposta();
            if (domande==null || domande.length==0){
                pannelloFaqRisposta.setVisible(false);
                new BoundaryFallimento("Al momento non sono presenti domande a cui rispondere");
                return;
            }
            box = new JComboBox(domande);
            //aggiunge la box al pannello di competenza
            panelBox.add(box);

            //aggiunge l'ascoltatore relativo
            box.addActionListener(e -> {
                        int index;
                        index = box.getSelectedIndex();
                        System.out.println("Risposta scritta: " + ris_lista.getText());
                        domanda= domande[index];


                    }

            );

        }

        //Ascoltatore

        private class RispondiAA implements ActionListener
        {
            public void actionPerformed(ActionEvent arg0)
            {
                try
                {
                    pannelloFaqRisposta.setVisible(false);
                    crf.rispondiComeSuperUtente(domanda, ris_lista.getText());

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
                    pannelloFaqRisposta.setVisible(false);
                    new BoundaryAmministrazione();

                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }

}
