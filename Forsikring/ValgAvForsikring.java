
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

import javax.swing.*;

public class ValgAvForsikring extends JFrame
{
    private JPanel cardPanel, buttonPanel, jp1, jp2, jp3, jp4;
    private Lytterklasse lytter;
	private Kunde kunde;
	private Huvudvindu vindu;
	private Register register;
    private JButton lukkVindu, registrer, lukkVinduBåt, lukkVinduHus, lukkVinduFritid, registrerBåt, registrerHus, registrerFritid, bilKnapp, båtKnapp, husKnapp, fritidKnapp;
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
  	      "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014","2015" };
    private String[] regÅrBåt = { "Velg byggeår","1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000",
    	      "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014","2015" };
    private String[] båtMerke = {"Velg","Seilbåt", "Motorbåt med innenbordsmotor", "Motorbåt med utenbordsmotor", "Robåt, Kano" };
    private String[] båtfører = { "Velg","Båtfører < 23 år", "Båtførere mellom 23-25 år","Alle båtførere > 25 år" };
    private String[] boligType = { "Velg byggningstype","Enebolig","Tomannsbolig","Rekkehus", "Tremannsbolig", "Firemannsbolig" };
    private String[] byggemateriale = { "Velg byggemateriale","Mur","Brannfast","Tre", "Laftet tømmer"};
    private String[] byggeMaterialeFritid = { "Velg byggemateriale","Mur","Brannfast","Tre", "Laftet tømmer"};
    private String[] boligTypeFritid = { "Velg byggningstype","Enebolig","Tomannsbolig","Rekkehus", "Tremannsbolig", "Firemannsbolig" };
    private String[] s = { "Velg", "Normal standard", "Bedre standard", "Høy standard"};
    private String[] s1 = { "Velg", "Normal standard", "Bedre standard", "Høy standard"};
    private String[] innbo = { "Velg forsikringssum", "150000","300000","500000", "750000", "10000000", "1500000", "2000000", "3000000"};
    private String[] innboFritid = { "Velg forsikringssum", "150000","300000","500000", "750000", "1000000", "1500000", "2000000", "3000000"};
    private JComboBox bil, reg, båtReg, båt, båtKapten, bolig, materiale, standard1, forsikringssum, materialeFritid, typeFritid, standard, innbo1;


    public ValgAvForsikring(Huvudvindu vin, Kunde kunn)
    {
		vindu = vin;
		register = vin.getRegister();
		kunde = kunn;
		lytter = new Lytterklasse();
    	//Vinduets size.
    	setTitle("Tegning av forsikringer");
    	setSize(600,600 );
    	setLocationRelativeTo(null);
        cardPanel = new JPanel(new BorderLayout());

        //Informationen som visas i vinduets topp.
     	informationTop = new JTextArea("");
     	informationTop.setLineWrap(true);
     	informationTop.setWrapStyleWord(true);
     	informationTop.setEditable(false);

		//Cardlayout for og swappa mellan de olika forsikringarna.
        CardLayout cl = new CardLayout();
        cardPanel.setLayout(cl);

        //Panels for de olika forsikringsalternativen.
        jp1 = new JPanel(new GridLayout(14,1));
        jp2 = new JPanel(new GridLayout(14,1));
        jp3 = new JPanel(new GridLayout(14,1));
        jp4 = new JPanel(new GridLayout(14,1));

		//Bil
    	//JComboBox<String> bil = new JComboBox<>(bilmerke);
    	bil = new JComboBox<>(bilmerke);
    	//JComboBox<String> reg = new JComboBox<>(regÅr);
    	reg = new JComboBox<>(regÅr);

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

		navnBil.addActionListener(lytter);
		fakturaadresseBil.addActionListener(lytter);
		eierPersonNr.addActionListener(lytter);
		regNmr.addActionListener(lytter);
		bilModell.addActionListener(lytter);
		kmÅr.addActionListener(lytter);
     	bil.addActionListener(lytter);
    	reg.addActionListener(lytter);

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
    	//bil.getSelectedItem();
    	//Båt
    	båt = new JComboBox<>(båtMerke);
    	båtReg = new JComboBox<>(regÅrBåt);
    	båtKapten = new JComboBox<>(båtfører);

    	navnBåtLabel = new JLabel("Navn:");
    	fakturaadresseBåtLabel = new JLabel("Fakturaadresse:");
    	eierPersonNrBåtLabel = new JLabel("Fødselsdato(DD-MM-YY-XXXXX):");
    	regNmrBåtLabel = new JLabel("Produksjonsår:");
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
    	bolig = new JComboBox<>(boligType);
    	materiale = new JComboBox<>(byggemateriale);
    	forsikringssum = new JComboBox<>(innbo);
    	standard1 = new JComboBox<>(s1);

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
        materialeFritid = new JComboBox<>(byggeMaterialeFritid);
        typeFritid = new JComboBox<>(boligTypeFritid);
        standard = new JComboBox<>(s);
        innbo1 = new JComboBox<>(innboFritid);

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

        buttonPanel = new JPanel();
        bilKnapp = new JButton("Bil");
        båtKnapp = new JButton("Båt");
        husKnapp = new JButton("Hus");
        fritidKnapp = new JButton("Fritid");
        registrer = new JButton("Registrer");
        lukkVindu = new JButton("Lukk");
        registrer.addActionListener(lytter);
        bilKnapp.addActionListener(lytter);
        båtKnapp.addActionListener(lytter);
        husKnapp.addActionListener(lytter);
        fritidKnapp.addActionListener(lytter);
        lukkVindu.addActionListener(lytter);

        buttonPanel.add(lukkVindu);
        buttonPanel.add(bilKnapp);
        buttonPanel.add(båtKnapp);
        buttonPanel.add(husKnapp);
        buttonPanel.add(fritidKnapp);
        buttonPanel.add(registrer);

        /*bilKnapp.addActionListener(new ActionListener()
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



        lukkVindu.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
            	if(e.getSource() == lukkVindu)
    				dispose();

            }
        });


        /*registrer.addActionListener(new ActionListener()
        {
			public void actionPerformed(ActionEvent e)
			{
				if(e.getSource() == registrer || e.getSource() == navnBil || e.getSource() == fakturaadresseBil
				 || e.getSource() == eierPersonNr || e.getSource() == bil || e.getSource() == reg
				 || e.getSource() == bilModell || e.getSource() == regNmr || e.getSource() == kmÅr)
					nyForsikringBil();
			}
		});*/


     	getContentPane().add(informationTop, BorderLayout.NORTH);
        getContentPane().add(cardPanel, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);

	}

	public void slettFelter()
	{
		navnBil.setText("");
		fakturaadresseBil.setText("");
		eierPersonNr.setText("");
		regNmr.setText("");
		bilModell.setText("");
		kmÅr.setText("");
		navnBåt.setText("");
		fakturaadresseBåt.setText("");
		personNrBåt.setText("");
		regNmrBåt.setText("");
		båtFot.setText("");
		båtMotor.setText("");
		husNavn.setText("");
		fakturaadresseHus.setText("");
		husAdresse.setText("");
		husByggeÅr.setText("");
		byggMaterial.setText("");
		m2.setText("");
    	fritidNavn.setText("");
    	fakturaadresseFritid.setText("");
    	fritidAdresse.setText("");
    	fritidByggeÅr.setText("");
    	m2fritid.setText("");

    	bil.setSelectedIndex(0);
    	reg.setSelectedIndex(0);
    	båtReg.setSelectedIndex(0);
    	båt.setSelectedIndex(0);
    	båtKapten.setSelectedIndex(0);
    	bolig.setSelectedIndex(0);
    	materiale.setSelectedIndex(0);
    	standard1.setSelectedIndex(0);
    	forsikringssum.setSelectedIndex(0);
	}



	public void nyForsikringBil()
	{
		try{
			String eiernavn, adresse, personnr, modell,regnr,kå, regexPattern, registrår, merke, kundepersnr;
			int regår, kmårlig;
			regexPattern = "(0[1-9]|[12][0-9]|3[01])(0[1-9]|1[012])(\\d{7})";
			eiernavn = navnBil.getText();
			adresse = fakturaadresseBil.getText();
			personnr = eierPersonNr.getText();
			modell = bilModell.getText();
			regnr = regNmr.getText();
			kå = kmÅr.getText();
			kmårlig = Integer.valueOf(kmÅr.getText());
			registrår = reg.getSelectedItem().toString();
			Object obj = reg.getSelectedItem();
			regår = Integer.valueOf( registrår);
			//reg2 = Integer.valueOf(reg1);
			merke = String.valueOf(bil.getSelectedItem());
			/*eiernavn = "Hans";
			adresse = "Hansgata";
			personnr = "01010199887";
			modell = "Golf";
			regnr = "AA11111";
			kå = "h";
			kmårlig = 11;
			reg1 = "H";
			regår = 1999;
			merke = "Merce";*/
			String forsikringsinfo = "Hallo";
			int beløp = 100;
			int kategori = 1;
			kundepersnr = kunde.getKundeNr();

			if(eiernavn.equals("") || adresse.equals("") || modell.equals("") || personnr.equals("") || regnr.equals("") || kå.equals("") || merke.equals("") || registrår.equals("") )
				informationTop.setText("Alle feltene må fylles ut");
			else if(!personnr.matches(regexPattern) )
				informationTop.setText("Personnummeret er ikke gyldig");
			else
			{
				BilForsikring1 ennybil = new BilForsikring1(kundepersnr, forsikringsinfo, kategori, eiernavn, regnr, merke, modell, regår, kmårlig, beløp);
				register.nyBil(ennybil);
				JOptionPane.showMessageDialog(null, "Bil registrert");
			}

		}
		catch(NullPointerException npe)
		{
			JOptionPane.showMessageDialog(null, "NullPointerException i nyForsikringBil i ValgAvForsikring", "ERROR", JOptionPane.ERROR_MESSAGE);
			return;
		}
		catch(NumberFormatException nfe)
		{
			JOptionPane.showMessageDialog(null, "NumberFormatException i nyForsikringBil i ValgAvForsikring", "ERROR", JOptionPane.ERROR_MESSAGE);
			return;
		}
	}
	public void nyForsikringBåt()
	{
		try{
			String eiernavn, adresse, personnr, regnr, regexPattern, båttype,
				kundenr, forsikringsinfo, regåret, båtmodell, motortype, yngstebåtfører;
			int båtlengde, båtregistreringsår, beløp, kategori, motorstyrke;
			regexPattern = "(0[1-9]|[12][0-9]|3[01])(0[1-9]|1[012])(\\d{7})";
			eiernavn = navnBåt.getText();
			adresse = fakturaadresseBåt.getText();
			personnr = personNrBåt.getText();
			regnr = regNmrBåt.getText();
			båtlengde = Integer.valueOf(båtFot.getText());
			motorstyrke = Integer.valueOf(båtMotor.getText());
			regåret = båtReg.getSelectedItem().toString();
			båtregistreringsår = Integer.valueOf(regåret);
			yngstebåtfører = båtKapten.getSelectedItem().toString();
			//yngstebåtfører = String.valueOf(båtKapten.getSelectedItem());
			båttype = String.valueOf(båt.getSelectedItem());
			forsikringsinfo = "Hallo";
			beløp = 100;
			kategori = 2;
			kundenr = kunde.getKundeNr();
			båtmodell = "Nimbus";
			motortype = "Bensin";

			if(eiernavn.equals("") || adresse.equals("") || personnr.equals("") || regnr.equals("") || båtlengde == 0 || motorstyrke == 0 || båtregistreringsår == 0 || yngstebåtfører.equals("Velg")
					|| båttype.equals("") )
				informationTop.setText("Alle feltene må fylles ut");
			else if(!personnr.matches(regexPattern) )
				informationTop.setText("Personnummeret er ikke gyldig");
			else
			{
				BåtForsikring1 båt = new BåtForsikring1(kundenr, beløp, forsikringsinfo, eiernavn, regnr, båttype , båtmodell, båtregistreringsår, båtlengde, motortype, motorstyrke, kategori);
				register.nyBåt(båt);
				JOptionPane.showMessageDialog(null, "Båt registrert");
			}
		}
		catch(NullPointerException npe)
		{
			JOptionPane.showMessageDialog(null, "NullPointerException i nyForsikringBåt i ValgAvForsikring", "ERROR", JOptionPane.ERROR_MESSAGE);
			return;
		}
		catch(NumberFormatException nfe)
		{
			JOptionPane.showMessageDialog(null, "NumberFormatException i nyForsikringBåt i ValgAvForsikring", "ERROR", JOptionPane.ERROR_MESSAGE);
			return;
		}
	}

	public void nyForsikringHus()
	{
		try{
			String eiernavn, adresse, husadresse, boligtype, byggemateriale,
					standard, regexPattern, kundenr, husår, husm2, verdiinne;
			int byggeår, husareal, innboverdi;
			regexPattern = "(0[1-9]|[12][0-9]|3[01])(0[1-9]|1[012])(\\d{7})";
			eiernavn = husNavn.getText();
			adresse = fakturaadresseHus.getText();
			husadresse = husAdresse.getText();
			husår = husByggeÅr.getText().toString();
			byggeår = Integer.valueOf(husår);
			husm2 = m2.getText().toString();
			husareal = Integer.valueOf(husm2);
			boligtype = String.valueOf(bolig.getSelectedItem());
			byggemateriale = String.valueOf(materiale.getSelectedItem());
			verdiinne = forsikringssum.getSelectedItem().toString();
			innboverdi = Integer.valueOf(verdiinne);
			standard = String.valueOf(standard1.getSelectedItem());
			String forsikringsinfo = "Hallo";
			int beløpbygg = 100;
			int kategori = 3;
			kundenr = kunde.getKundeNr();

			if(eiernavn.equals("") || husadresse.equals("") || boligtype.equals("Velg bygningstype") || byggemateriale.equals("Velg byggemateriale") || standard.equals("Velg") || husår.equals("") || husm2.equals("") || verdiinne.equals("Velg forsikringssum") )
				informationTop.setText("Alle feltene må fylles ut");
			else
			{
				HusForsikring1 hus = new HusForsikring1(kundenr, beløpbygg, innboverdi, forsikringsinfo, husadresse,
						boligtype, byggemateriale, standard, byggeår, husareal, kategori);
				register.nyttHus(hus);
				JOptionPane.showMessageDialog(null, "Hus registrert" );
			}
		}
		catch(NullPointerException npe)
		{
			JOptionPane.showMessageDialog(null, "NullPointerException i nyForsikringHus i ValgAvForsikring", "ERROR", JOptionPane.ERROR_MESSAGE);
			return;
		}
		catch(NumberFormatException nfe)
		{
			JOptionPane.showMessageDialog(null, "NumberFormatException i nyForsikringBHus i ValgAvForsikring", "ERROR", JOptionPane.ERROR_MESSAGE);
			return;
		}
	}

	public void nyForsikringHytte()
	{
		try{
			String eiernavn, adresse, hytteadresse, boligtype, byggemateriale,
					standarden, regexPattern, kundenr, hytteår, hyttem2, verdiinne;
			int byggeår, hytteareal, innboverdi;
			regexPattern = "(0[1-9]|[12][0-9]|3[01])(0[1-9]|1[012])(\\d{7})";
			eiernavn = fritidNavn.getText();
			adresse = fakturaadresseHus.getText();
			hytteadresse = fritidAdresse.getText();
			hytteår = fritidByggeÅr.getText().toString();
			byggeår = Integer.valueOf(hytteår);
			hyttem2 = m2fritid.getText().toString();
			hytteareal = Integer.valueOf(hyttem2);
			boligtype = String.valueOf(typeFritid.getSelectedItem());
			byggemateriale = String.valueOf(materialeFritid.getSelectedItem());
			verdiinne = innbo1.getSelectedItem().toString();
			innboverdi = Integer.valueOf(verdiinne);
			standarden = String.valueOf(standard.getSelectedItem());
			String forsikringsinfo = "Hallo";
			int beløpbygg = 100;
			int kategori = 4;
			kundenr = kunde.getKundeNr();

			if(eiernavn.equals("") || hytteadresse.equals("") || boligtype.equals("Velg bygningstype") || byggemateriale.equals("Velg byggemateriale")
			|| standard.equals("Velg") || hytteår.equals("") || hyttem2.equals("") || verdiinne.equals("Velg forsikringssum") )
				informationTop.setText("Alle feltene må fylles ut");
			else
			{
				HytteForsikring1 hyt = new HytteForsikring1(kundenr, beløpbygg, innboverdi, forsikringsinfo, hytteadresse,
						boligtype, byggemateriale, standarden, byggeår, hytteareal, kategori);
				register.nyHytte(hyt);
				JOptionPane.showMessageDialog(null, "Hytte registrert" );
			}
		}
		catch(NullPointerException npe)
		{
			JOptionPane.showMessageDialog(null, "NullPointerException i nyForsikringHus i ValgAvForsikring", "ERROR", JOptionPane.ERROR_MESSAGE);
			return;
		}
		catch(NumberFormatException nfe)
		{
			JOptionPane.showMessageDialog(null, "NumberFormatException i nyForsikringBHus i ValgAvForsikring", "ERROR", JOptionPane.ERROR_MESSAGE);
			return;
		}
	}

	private class Lytterklasse implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource() == registrer )
			{
				if(jp1.isVisible() == true)
					nyForsikringBil();
				else if(jp2.isVisible() == true)
					nyForsikringBåt();
				else if(jp3.isVisible() == true)
					nyForsikringHus();
				else if(jp4.isVisible() == true)
					nyForsikringHytte();
			}

			else if(e.getSource() == bilKnapp)
			{
				cl = (CardLayout) cardPanel.getLayout();
                cl.show(cardPanel, "1");
           		informationTop.setText("Bilforsikring");
                informationTop.revalidate();
                informationTop.repaint();
			}
			else if(e.getSource() == båtKnapp)
            {
            	CardLayout cl = (CardLayout) cardPanel.getLayout();
                cl.show(cardPanel, "2");
           		//informationTop.setText("");
           		informationTop.setText("Båtforsikring");
                informationTop.revalidate();
                informationTop.repaint();
            }
            else if(e.getSource() == husKnapp)
            {
            	CardLayout cl = (CardLayout) cardPanel.getLayout();
                cl.show(cardPanel, "3");
           		informationTop.removeAll();
           		informationTop.setText("Husforsikring");
                informationTop.revalidate();
                informationTop.repaint();
            }
            else if(e.getSource() == fritidKnapp)
            {
            	CardLayout cl = (CardLayout) cardPanel.getLayout();
                cl.show(cardPanel, "4");
           		informationTop.setText("Fritidforsikring");
                informationTop.revalidate();
                informationTop.repaint();
            }
            else if(e.getSource() == lukkVindu)
            	dispose();
		}
	}

 }
