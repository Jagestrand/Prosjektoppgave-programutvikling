

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.*;

 class ValgAvForsikring extends JFrame 
 {
    private JPanel cardPanel;
    private JButton lukkVindu,registrer, lukkVinduBåt, lukkVinduHus, lukkVinduFritid, registrerBåt, registrerHus,registrerFritid;
    private CardLayout cl;
    private JTextField navnBil, fakturaadresseBil, eierPersonNr, regNmr, bilModell, kmÅr,
    					navnBåt, fakturaadresseBåt, personNrBåt, regNmrBåt, båtFot, båtMotor,
    					husNavn, fakturaadresseHus, husAdresse, husByggeÅr, byggMaterial, m2,
    					fritidNavn, fakturaadresseFritid, fritidAdresse, fritidByggeÅr, m2fritid;
    private JTextArea informationTop;
    private int DATA_FIELD_LENGTH = 20;
    private JLabel navnBilLabel, fakturaadresseBilLabel, eierPersonNrLabel, bilMerkeLabel, regLabel, regNmrLabel, bilModellLabel, kmÅrLabel, båtførerLabel,
    				navnBåtLabel, fakturaadresseBåtLabel, eierPersonNrBåtLabel, regNmrBåtLabel, båtModellLabel,båtFotLabel,båtRegLabel,båtMotorLabel,
    				navnHusLabel, husAdresseLabel,fakturaadresseHusLabel, husByggeÅrLabel,boligtypeLabel, byggmaterialeLabel, m2Label, innboLabel,standardHusLabel,
    				fritidNavnLabel, fakturaadresseFritidLabel, fritidAdresseLabel,fritidByggMaterialeLabel,fritidByggeÅrLabel,boligTypeFritidLabel,standardLabel,m2FritidLabel,innboFritidLabel;
    private String[] bilmerke = {"Velg bilmerke", "Audi", "BMW", "Chevrolet",
    	      "Chrysler", "Ford", "Lexus", "Maserati", "Mercedes-Benz", "Porsche", "Saab", "Volvo" };
    private String[] regÅr = {"Velg registreringsår", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000",
  	      "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014" };
    private String[] regÅrBåt = { "Velg byggeår","1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000",
    	      "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014" };
    private String[] båtMerke = {"Velg","Seilbåt", "Motorbåt med innenbordsmotor", "Motorbåt med utenbordsmotor", "Robåt, Kano" };
    private String[] båtfører = { "Velg","Båtfører < 23 år", "Båtførere mellom 23-25 år","Alle båtførere > 25 år" };
    private String[] boligType = { "Velg byggningstype","Enebolig","Tomannsbolig","Rekkehus", "Tremannsbolig", "Firemannsbolig" };
    private String[] byggemateriale = { "Velg byggemateriale","Mur","Brannfast","Tre", "Laftet tømmer"};
    private String[] byggeMaterialeFritid = { "Velg byggemateriale","Mur","Brannfast","Tre", "Laftet tømmer"};
    private String[] boligTypeFritid = { "Velg byggningstype","Enebolig","Tomannsbolig","Rekkehus", "Tremannsbolig", "Firemannsbolig" };
    private String[] s = { "Velg", "Normal standard", "Bedre standard", "Høy standard"};
    private String[] s1 = { "Velg", "Normal standard", "Bedre standard", "Høy standard"};
    private String[] innbo = { "Velg forsikringssum", "150 000","300 000","500 000", "750 000", "1 000 0000", "1 500 000", "2 000 000", "3 000 000"};
    private String[] innboFritid = { "Velg forsikringssum", "150 000","300 000","500 000", "750 000", "1 000 0000", "1 500 000", "2 000 000", "3 000 000"};
    
    public ValgAvForsikring() 
    {
    	//Vinduets size. 
    	setTitle("Tegning av forsikringer");
    	setSize(600,600 );
    	setLocationRelativeTo(null);
        cardPanel = new JPanel(new BorderLayout());
        
        //Informationen som visas i vinduets topp. 
     	informationTop = new JTextArea("Velkommen tilbake");
     	informationTop.setLineWrap(true);
     	informationTop.setWrapStyleWord(true);
     	informationTop.setEditable(false);
		
		//Cardlayout for og swappa mellan de olika forsikringarna.
        CardLayout cl = new CardLayout();
        cardPanel.setLayout(cl);
        
        //Panels for de olika forsikringsalternativen.
        JPanel jp1 = new JPanel(new GridLayout(14,1));
        JPanel jp2 = new JPanel(new GridLayout(14,1));
        JPanel jp3 = new JPanel(new GridLayout(14,1));
        JPanel jp4 = new JPanel(new GridLayout(14,1));
        
		//Bil
    	JComboBox<String> bil = new JComboBox<>(bilmerke);
    	JComboBox<String> reg = new JComboBox<>(regÅr);
    	
    	navnBilLabel = new JLabel("Navn:");
    	fakturaadresseBilLabel = new JLabel("Fakturaadresse:");
    	eierPersonNrLabel = new JLabel("Fødselsdato(DD-MM-YY-XXXXX):");
		bilMerkeLabel = new JLabel("Bilmerke:");
		regLabel = new JLabel("Registreringsår (første gang):");
		bilModellLabel = new JLabel("Modell:");
		regNmrLabel = new JLabel("Registreringsnummer:");
		kmÅrLabel = new JLabel("Kjørelengde pr.år i antall km:");
		
		navnBil = new JTextField(DATA_FIELD_LENGTH);
		fakturaadresseBil = new JTextField(DATA_FIELD_LENGTH);
    	eierPersonNr = new JTextField(DATA_FIELD_LENGTH);
		regNmr = new JTextField(DATA_FIELD_LENGTH);
		bilModell= new JTextField(DATA_FIELD_LENGTH);
		kmÅr = new JTextField(DATA_FIELD_LENGTH);
		
		jp1.add(navnBilLabel);
		jp1.add(navnBil);
		jp1.add(fakturaadresseBilLabel);
		jp1.add(fakturaadresseBil);
        jp1.add(eierPersonNrLabel);
        jp1.add(eierPersonNr);
        jp1.add(bilMerkeLabel);
    	jp1.add(bil);
    	jp1.add(regLabel);
    	jp1.add(reg);
    	jp1.add(bilModellLabel);
    	jp1.add(bilModell);
    	jp1.add(regNmrLabel);
    	jp1.add(regNmr);
    	jp1.add(kmÅrLabel);
    	jp1.add(kmÅr);
    	
    	//Båt
    	JComboBox<String> båt = new JComboBox<>(båtMerke);
    	JComboBox<String> båtReg = new JComboBox<>(regÅrBåt);
    	JComboBox<String> båtKapten = new JComboBox<>(båtfører);
    	
    	navnBåtLabel = new JLabel("Navn:");
    	fakturaadresseBåtLabel = new JLabel("Fakturaadresse:");
    	eierPersonNrBåtLabel = new JLabel("Fødselsdato(DD-MM-YY-XXXXX):");
    	regNmrBåtLabel = new JLabel("Registreringsår (første gang):");
    	båtModellLabel = new JLabel("Båttype:");
    	båtFotLabel = new JLabel("Skrolengde i fot:");
    	båtRegLabel = new JLabel("Registreringsnummer:");
    	båtMotorLabel = new JLabel("Motorstyrke HK:");
    	båtførerLabel = new JLabel("Yngste båtførers alder");
    	
    	navnBåt = new JTextField(DATA_FIELD_LENGTH);
    	fakturaadresseBåt = new JTextField(DATA_FIELD_LENGTH);
    	personNrBåt = new JTextField(DATA_FIELD_LENGTH);
    	regNmrBåt = new JTextField(DATA_FIELD_LENGTH);
    	båtFot = new JTextField(DATA_FIELD_LENGTH);
    	båtMotor = new JTextField(DATA_FIELD_LENGTH);
    	
    	jp2.add(navnBåtLabel);
    	jp2.add(navnBåt);
    	jp2.add(fakturaadresseBåtLabel);
    	jp2.add(fakturaadresseBåt);
    	jp2.add(eierPersonNrBåtLabel);
    	jp2.add(personNrBåt);
    	jp2.add(båtRegLabel);
    	jp2.add(regNmrBåt);
    	jp2.add(regNmrBåtLabel);
    	jp2.add(båtReg);
    	jp2.add(båtModellLabel);
    	jp2.add(båt);
    	jp2.add(båtførerLabel);
    	jp2.add(båtKapten);
    	jp2.add(båtFotLabel);
    	jp2.add(båtFot);
    	jp2.add(båtMotorLabel);
    	jp2.add(båtMotor);
    	
    	//Hus
    	JComboBox<String> bolig = new JComboBox<>(boligType);
    	JComboBox<String> materiale = new JComboBox<>(byggemateriale);
    	JComboBox<String> forsikringssum = new JComboBox<>(innbo);
    	JComboBox<String> standard1 = new JComboBox<>(s1);
    	
    	navnHusLabel = new JLabel("Navn:");
    	fakturaadresseHusLabel = new JLabel("Fakturaadresse:");
    	husAdresseLabel = new JLabel("Boligens adresse:");
    	husByggeÅrLabel = new JLabel("Byggeår:");
    	boligtypeLabel = new JLabel("Boligtype:");
    	byggmaterialeLabel = new JLabel("Byggemateriale:");
    	standardHusLabel = new JLabel("Standard:");
    	m2Label = new JLabel("Areal:");
    	innboLabel = new JLabel("Forsikringssum innbo:");
    	
    	husNavn = new JTextField(DATA_FIELD_LENGTH);
    	fakturaadresseHus = new JTextField(DATA_FIELD_LENGTH);
    	husAdresse = new JTextField(DATA_FIELD_LENGTH);
    	husByggeÅr = new JTextField(DATA_FIELD_LENGTH);
    	m2 = new JTextField(DATA_FIELD_LENGTH);

    	jp3.add(navnHusLabel);
    	jp3.add(husNavn);
    	jp3.add(fakturaadresseHusLabel);
    	jp3.add(fakturaadresseHus);
    	jp3.add(husAdresseLabel);
    	jp3.add(husAdresse);
    	jp3.add(husByggeÅrLabel);
    	jp3.add(husByggeÅr);
    	jp3.add(boligtypeLabel);
    	jp3.add(bolig);
    	jp3.add(byggmaterialeLabel);
    	jp3.add(materiale);
    	jp3.add(standardHusLabel);
    	jp3.add(standard1);
    	jp3.add(m2Label);
    	jp3.add(m2);
    	jp3.add(innboLabel);
    	jp3.add(forsikringssum);
    	
        cardPanel.add(jp1, "1");
        cardPanel.add(jp2, "2");
        cardPanel.add(jp3, "3");
        cardPanel.add(jp4, "4");
        
        //Fritid
        JComboBox<String> materialeFritid = new JComboBox<>(byggeMaterialeFritid);
        JComboBox<String> typeFritid = new JComboBox<>(boligTypeFritid);
        JComboBox<String> standard = new JComboBox<>(s);
        JComboBox<String> innbo1 = new JComboBox<>(innboFritid);
        
        fritidNavnLabel = new JLabel("Navn:");
        fakturaadresseFritidLabel = new JLabel("Fakturaadresse:");
        fritidAdresseLabel = new JLabel("Boligens adresse:");
    	fritidByggMaterialeLabel = new JLabel("Byggemateriale:");
    	boligTypeFritidLabel = new JLabel("Boligtype:");
    	fritidByggeÅrLabel = new JLabel("Byggeår:");
    	standardLabel = new JLabel("Standard:");
    	m2FritidLabel = new JLabel("Areal");
    	innboFritidLabel = new JLabel("Forsikringssum innbo:");
    	
    	fritidNavn = new JTextField(DATA_FIELD_LENGTH);
    	fakturaadresseFritid = new JTextField(DATA_FIELD_LENGTH);
    	fritidAdresse = new JTextField(DATA_FIELD_LENGTH);
    	fritidByggeÅr = new JTextField(DATA_FIELD_LENGTH);
    	m2fritid = new JTextField(DATA_FIELD_LENGTH);

    	jp4.add(fritidNavnLabel);
    	jp4.add(fritidNavn);
    	jp4.add(fakturaadresseFritidLabel);
    	jp4.add(fakturaadresseFritid);
    	jp4.add(fritidAdresseLabel);
    	jp4.add(fritidAdresse);
    	jp4.add(fritidByggMaterialeLabel);
    	jp4.add(materialeFritid);
    	jp4.add(boligTypeFritidLabel);
    	jp4.add(typeFritid);
    	jp4.add(standardLabel);
    	jp4.add(standard);
    	jp4.add(innboFritidLabel);
    	jp4.add(innbo1);
    	jp4.add(fritidByggeÅrLabel);
    	jp4.add(fritidByggeÅr);
    	jp4.add(m2FritidLabel);
    	jp4.add(m2fritid);
    	
        JPanel buttonPanel = new JPanel();
        JButton bilKnapp = new JButton("Bil");
        JButton båtKnapp = new JButton("Båt");
        JButton husKnapp = new JButton("Hus");
        JButton fritidKnapp = new JButton("Fritid");
        JButton registrer = new JButton("Registrer");
        JButton lukkVindu = new JButton("Lukk");
        
        buttonPanel.add(lukkVindu);
        buttonPanel.add(bilKnapp);
        buttonPanel.add(båtKnapp);
        buttonPanel.add(husKnapp);
        buttonPanel.add(fritidKnapp);
        buttonPanel.add(registrer);
        
        bilKnapp.addActionListener(new ActionListener() 
        {
        	public void actionPerformed(ActionEvent arg0) 
        	{
        		CardLayout cl = (CardLayout) cardPanel.getLayout();
                cl.show(cardPanel, "1");
            }
        });
        
        båtKnapp.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent arg0)
            {
            	CardLayout cl = (CardLayout) cardPanel.getLayout();
                cl.show(cardPanel, "2");
            }
        });
        
        husKnapp.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent arg0) 
            {
            	CardLayout cl = (CardLayout) cardPanel.getLayout();
                cl.show(cardPanel, "3");
            }
        });
        
        fritidKnapp.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent arg0) 
            {
            	CardLayout cl = (CardLayout) cardPanel.getLayout();
                cl.show(cardPanel, "4");
            }
        });
        


        lukkVindu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
            	if(e.getSource() == lukkVindu)
    				dispose();
    		
            }
        });

     	getContentPane().add(informationTop, BorderLayout.NORTH);
        getContentPane().add(cardPanel, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);

    }

	private JTextField JTextField(int dATA_FIELD_LENGTH2) {
		// TODO Auto-generated method stub
		return null;
	}
	public void nyForsikringBil()
	{

		String nb, fb, epn, b, r, bm,rn,kå, regexPattern, ansnr;
		regexPattern = "(0[1-9]|[12][0-9]|3[01])(0[1-9]|1[012])(\\d{7})";
		nb = navnBil.getText();
		fb = fakturaadresseBil.getText();
		epn = eierPersonNr.getText();
		b = bil.getSelectedItem();
		r = reg.getText();
		bm = bilModell.getText();
		rn = regNmr.getText();
		kå = kmÅr.getText();
		
		if(nb.equals("") || fb.equals("") || epn.equals("") || bm.equals("") || rn.equals("") || kå.equals("") )
			informationTop.setText("Alle feltene må fylles ut");
		else if(!epn.matches(regexPattern) )
			informationTop.setText("Personnummeret er ikke gyldig");
		else
		{
			Ansatt res = new Ansatt(nb, fb, epn, bm,rn,kå);
			ansnr = res.getAnsattNr();							//Denne er ny
			//Ansatt ret = new Ansatt(ansnr, fn, ln, pn, t, ea, p);
			reg.nyAnsatt(res);
			//reg.nyAnsatt(ret);
			info.setText("Ansatt lagret:\n" + res.toString() );
		}
	}

	private class Listener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource() == addAns || e.getSource() == fName || e.getSource() == lName
			|| e.getSource() == pNr || e.getSource() == pass1 || e.getSource() == pass2
			 || e.getSource() == phone || e.getSource() == empAt)
				nyAnsatt();
		}
	}
    
 }
