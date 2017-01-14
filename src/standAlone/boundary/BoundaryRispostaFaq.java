package standAlone.boundary;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


import standAlone.control.ControlloreRispondiFaq;


/**
 * Created by maria on 11/01/17.
 */
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

        public BoundaryRispostaFaq()
        {
            int border = 5;
            //pannello esterno che contiene tutto
            pannelloFaqRisposta = new JPanel();

            pannelloFaqRisposta.setSize(BoundaryAvvio.Confine.getWidth(), BoundaryAvvio.Confine.getHeight());
            BoundaryAvvio.Confine.add(pannelloFaqRisposta);
            pannelloFaqRisposta.setLayout(null);

            panelBox.setLayout(new FlowLayout());
            panelBox.setSize(BoundaryAvvio.Confine.getWidth(), BoundaryAvvio.Confine.getHeight()/5);
            panelBox.setLocation(5, 50);

            domande = crf.visualizzaDomandeSenzaRisposta();


            box = new JComboBox(domande);

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
            titolo.setText("Rispondi alle domande degli utenti");




            // Creazione etichette campi con relativi nomi

            rispondiLabel.setFont(new Font("Verdana", Font.BOLD, 15));
            rispondiLabel.setLocation(75,30);
            rispondiLabel.setSize(panelTitolo.getWidth()/2, 30);
            rispondiLabel.setText("Inserisci una risposta per la domanda selezionata : ");



            bRispondi = new JButton("Rispondi");
            bRispondi.setLocation(150,50);
            bRispondi.setSize(panelTitolo.getWidth()/4, 50);
            bRispondi.setFont(new Font("Arial", 0, 20));

            bIndietro = new JButton("Indietro");
            bIndietro.setLocation(300+panelTitolo.getWidth()/4, 50);
            bIndietro.setSize(panelTitolo.getWidth()/4, 50);
            bIndietro.setFont(new Font("Arial", 0, 20));
            bIndietro.setToolTipText("Torna alla schermata precedente");

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

            panelBox.add(box);

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
            box.addActionListener(e -> {
                        int index;
                        index = box.getSelectedIndex();
                        System.out.println("Risposta scritta: " + ris_lista.getText());
                        domanda= domande[index];


            }

            );
        }
        // Fine costruttore

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
