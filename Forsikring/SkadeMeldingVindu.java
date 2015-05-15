import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import javax.swing.*;


public class Skademelding extends JFrame
 {
    private JPanel cardPanel, infoPanel;
    private JScrollPane scrollPane;
    private JPanel panel;
    private JButton lukkVindu,registrer, lukkVinduBåt, lukkVinduHus, lukkVinduFritid, registrerBåt, registrerHus,registrerFritid;
    private CardLayout sm;


    // Her legges JTextfield feltene inn (Bil, Båt, Hus, Fritid)
    private JTextField  skadeDatoBil, skadeStedBil, skadeNrBil, taksbelopBil, skadeTypeBil,beskrivSkadeBil, navnBil, adresseBil, telefonBil, epostBil, postBil,
						skadeDatoBåt, skadeStedBåt,skadeNrBåt,taksbelopBåt, skadeTypeBåt, beskrivBåt,navnBåt, adresseBåt, telefonBåt, epostBåt, postBåt,
    					skadeDatoHus, skadeNrHus, skadeTypeHus, beskrivHus, taksbelopHus,
    					skadeDatoFritid, skadeNrFritid, skadeTypeFritid, beskrivFritid, taksbelopFritid;


    //private JLabel informationTop;
    private int DATA_FIELD_LENGTH = 35;
    private JLabel  skadeDatoBilLabel,skadeStedBilLabel, skadeNrBilLabel,taksbelopBilLabel, skadeTypeBilLabel, beskrivSkadeBilLabel,
    				navnBilLabel, adresseBilLabel, telefonBilLabel, epostBilLabel, postBilLabel,
    				skadeDatoBåtLabel, skadeStedBåtLabel,skadeNrBåtLabel,taksbelopBåtLabel, skadeTypeBåtLabel, beskrivBåtLabel,
    			    navnBåtLabel, adresseBåtLabel, telefonBåtLabel, epostBåtLabel, postBåtLabel,
    				skadeDatoHusLabel, skadeNrHusLabel, skadeTypeHusLabel,  beskrivHusLabel, taksbelopHusLabel,
    				skadeDatoFritidLabel, skadeNrFritidLabel, skadeTypeFritidLabel, beskrivFritidLabel, taksbelopFritidLabel;



    public Skademelding()
    {
    	//Vinduets size.
    	setTitle("Fyll ut skadeskjema");
    	setSize(900,600 );
    	setLocationRelativeTo(null);
        cardPanel = new JPanel(new BorderLayout());



		//Cardlayout for og swappa mellom de ulike forsikringerne.
        CardLayout sm = new CardLayout();
        cardPanel.setLayout(sm);


        //Panels for de ulike forsikringsalternativene.
        JPanel jp1 = new JPanel(new GridLayout(20,1));
        JPanel jp2 = new JPanel(new GridLayout(20,1));
        JPanel jp3 = new JPanel(new GridLayout(20,1));
        JPanel jp4 = new JPanel(new GridLayout(20,1));



    	//Labels for bil.
    	skadeDatoBilLabel = new JLabel("Skade dato:");
		skadeDatoBil = new JTextField(DATA_FIELD_LENGTH);
		skadeNrBilLabel = new JLabel ("Skade nummer:");
		skadeNrBil = new JTextField(DATA_FIELD_LENGTH);
		skadeTypeBilLabel = new JLabel ("Type skade:");
		skadeTypeBil = new JTextField(DATA_FIELD_LENGTH);
		skadeStedBilLabel = new JLabel("Skade sted:");
		skadeStedBil= new JTextField(DATA_FIELD_LENGTH);
		beskrivSkadeBilLabel = new JLabel("Beskriv skaden:");
		beskrivSkadeBil = new JTextField(DATA_FIELD_LENGTH);
    	taksbelopBilLabel = new JLabel("Takseringsbelop av skaden:");
		taksbelopBil = new JTextField(DATA_FIELD_LENGTH);
		navnBilLabel = new JLabel(" Vitne navn:     *Vennligst fyll inn eventuell Vitne");
		navnBil = new JTextField(DATA_FIELD_LENGTH);
		adresseBilLabel = new JLabel("Vitne adresse:");
		adresseBil = new JTextField(DATA_FIELD_LENGTH);
		telefonBilLabel = new JLabel("Vitne telefon:");
		telefonBil = new JTextField(DATA_FIELD_LENGTH);
		epostBilLabel = new JLabel("Vitne e-post:");
		epostBil = new JTextField(DATA_FIELD_LENGTH);
		postBilLabel = new JLabel("Vitne postnummer og post sted:");
		postBil = new JTextField(DATA_FIELD_LENGTH);



		jp1.add(skadeDatoBilLabel);
		jp1.add(skadeDatoBil);
		jp1.add(skadeNrBilLabel);
		jp1.add(skadeNrBil);
		jp1.add(skadeStedBilLabel);
		jp1.add(skadeStedBil);
		jp1.add(beskrivSkadeBilLabel);
		jp1.add(beskrivSkadeBil);
		jp1.add(taksbelopBilLabel);
		jp1.add(taksbelopBil);
		jp1.add(navnBilLabel);
		jp1.add(navnBil);
		jp1.add(adresseBilLabel);
		jp1.add(adresseBil);
		jp1.add(telefonBilLabel);
		jp1.add(telefonBil);
		jp1.add(epostBilLabel);
		jp1.add(epostBil);
		jp1.add(postBilLabel);
		jp1.add(postBil);



    	//Båt
		skadeDatoBåtLabel = new JLabel("Skade dato:");
		skadeDatoBåt = new JTextField(DATA_FIELD_LENGTH);
		skadeNrBåtLabel = new JLabel ("Skade nummer:");
		skadeNrBåt = new JTextField(DATA_FIELD_LENGTH);
		skadeStedBåtLabel = new JLabel("Skade sted:");
		skadeStedBåt = new JTextField(DATA_FIELD_LENGTH);
		beskrivBåtLabel = new JLabel("Beskriv skaden:");
		beskrivBåt = new JTextField(DATA_FIELD_LENGTH);
    	taksbelopBåtLabel = new JLabel("Takseringsbelop av skaden:");
		taksbelopBåt = new JTextField(DATA_FIELD_LENGTH);
		navnBåtLabel = new JLabel("Vitne navn:     *Vennligst fyll inn eventuell Vitne");
		navnBåt = new JTextField(DATA_FIELD_LENGTH);
		adresseBåtLabel = new JLabel("Vitne adresse:");
		adresseBåt = new JTextField(DATA_FIELD_LENGTH);
		telefonBåtLabel = new JLabel("Vitne telefon:");
		telefonBåt = new JTextField(DATA_FIELD_LENGTH);
		epostBåtLabel = new JLabel("Vitne e-post:");
		epostBåt = new JTextField(DATA_FIELD_LENGTH);
		postBåtLabel = new JLabel("Vitne postnummer og Sted:");
		postBåt = new JTextField(DATA_FIELD_LENGTH);


		jp2.add(skadeDatoBåtLabel);
		jp2.add(skadeDatoBåt);
		jp2.add(skadeNrBåtLabel);
		jp2.add(skadeNrBåt);
		jp2.add(skadeStedBåtLabel);
		jp2.add(skadeStedBåt);
		jp2.add(beskrivBåtLabel);
		jp2.add(beskrivBåt);
		jp2.add(taksbelopBåtLabel);
		jp2.add(taksbelopBåt);
		jp2.add(navnBåtLabel);
		jp2.add(navnBåt);
		jp2.add(adresseBåtLabel);
		jp2.add(adresseBåt);
		jp2.add(telefonBåtLabel);
		jp2.add(telefonBåt);
		jp2.add(epostBåtLabel);
		jp2.add(epostBåt);
		jp2.add(postBåtLabel);
		jp2.add(postBåt);


    	//Hus
		skadeDatoHusLabel = new JLabel("Skade dato:");
		skadeDatoHus = new JTextField(DATA_FIELD_LENGTH);
		skadeNrHusLabel = new JLabel("Skade nr:");
		skadeNrHus = new JTextField(DATA_FIELD_LENGTH);
		skadeTypeHusLabel = new JLabel("Type skade:");
		skadeTypeHus = new JTextField(DATA_FIELD_LENGTH);
		beskrivHusLabel = new JLabel("Beskriv skaden:");
		beskrivHus = new JTextField(DATA_FIELD_LENGTH);
		taksbelopHusLabel = new JLabel("Takseringsbelop av skaden:");
		taksbelopHus = new JTextField(DATA_FIELD_LENGTH);


		jp3.add(skadeDatoHusLabel);
		jp3.add(skadeDatoHus);
		jp3.add(skadeNrHusLabel);
		jp3.add(skadeNrHus);
		jp3.add(skadeTypeHusLabel);
		jp3.add(skadeTypeHus);
		jp3.add(beskrivHusLabel);
		jp3.add(beskrivHus);
		jp3.add(taksbelopHusLabel);
		jp3.add(taksbelopHus);


        //Fritid
		skadeDatoFritidLabel = new JLabel("Skade dato:");
		skadeDatoFritid = new JTextField(DATA_FIELD_LENGTH);
		skadeNrFritidLabel = new JLabel("Skade nr:");
		skadeNrFritid = new JTextField(DATA_FIELD_LENGTH);
		skadeTypeFritidLabel = new JLabel("Type skade:");
		skadeTypeFritid = new JTextField(DATA_FIELD_LENGTH);
		beskrivFritidLabel = new JLabel("Beskriv skaden:");
		beskrivFritid = new JTextField(DATA_FIELD_LENGTH);
		taksbelopFritidLabel = new JLabel("Takseringsbelop av skaden:");
		taksbelopFritid = new JTextField(DATA_FIELD_LENGTH);


		jp4.add(skadeDatoFritidLabel);
		jp4.add(skadeDatoFritid);
		jp4.add(skadeNrFritidLabel);
		jp4.add(skadeNrFritid);
		jp4.add(skadeTypeFritidLabel);
		jp4.add(skadeTypeFritid);
		jp4.add(beskrivFritidLabel);
		jp4.add(beskrivFritid);
		jp4.add(taksbelopFritidLabel);
		jp4.add(taksbelopFritid);



        cardPanel.add(jp1, "1");
        cardPanel.add(jp2, "2");
        cardPanel.add(jp3, "3");
        cardPanel.add(jp4, "4");


        JPanel buttonPanelBott = new JPanel();
        JButton bilKnapp = new JButton("Bil");
        JButton båtKnapp = new JButton("Båt");
        JButton husKnapp = new JButton("Hus");
        JButton fritidKnapp = new JButton("Fritid");
        JButton registrer = new JButton("Registrer");
        JButton lukkVindu = new JButton("Lukk");
        buttonPanelBott.add(bilKnapp);
        buttonPanelBott.add(båtKnapp);
        buttonPanelBott.add(husKnapp);
        buttonPanelBott.add(fritidKnapp);
        buttonPanelBott.add(lukkVindu);
        buttonPanelBott.add(registrer);



        bilKnapp.addActionListener(new ActionListener()
        {
        	public void actionPerformed(ActionEvent arg0)
        	{
        		CardLayout sm = (CardLayout) cardPanel.getLayout();
                sm.show(cardPanel, "1");
            }
        });


        båtKnapp.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
            	CardLayout sm = (CardLayout) cardPanel.getLayout();
                sm.show(cardPanel, "2");
            }
        });


        husKnapp.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
            	CardLayout sm = (CardLayout) cardPanel.getLayout();
                sm.show(cardPanel, "3");
            }
        });


        fritidKnapp.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
            	CardLayout sm = (CardLayout) cardPanel.getLayout();
                sm.show(cardPanel, "4");
            }
        });



        lukkVindu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
            	if(e.getSource() == lukkVindu)
    				dispose();

            }
        });


        getContentPane().add(cardPanel, BorderLayout.EAST);
        //getContentPane().add(infoPanel, BorderLayout.EAST);
        getContentPane().add(buttonPanelBott, BorderLayout.SOUTH);
    }


	private JTextField JTextField(int dATA_FIELD_LENGTH2) {
		// TODO Auto-generated method stub
		return null;
	}

 }
