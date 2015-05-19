import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import javax.swing.*;
import java.text.*;
import java.util.*;
import java.io.File;
import java.io.IOException;

public class SkadeMeldingVindu extends JFrame
 {
    private JPanel cardPanel, p,  jp1, jp2, jp3, jp4, bildGrid, buttonPanelBott;
    private JScrollPane scrollPane;
    private JPanel panel;
    private Huvudvindu vindu;
    private Kunde kunde;
    private JButton b1, b2, b3;
    private JLabel lab, lab2;
    private Lytterklasse Lytter;
    private Register register;
    private JTextArea informationTop;
    private JFormattedTextField skadeDatoBil, skadeDatoBåt, skadeDatoHus, skadeDatoFritid;
    private JButton bilKnapp, båtKnapp, husKnapp, fritidKnapp, lukkVindu,registrer, lukkVinduBåt, lukkVinduHus, lukkVinduFritid, registrerBil, registrerBåt, registrerHus,registrerFritid;
    private CardLayout sm;


    // Her legges JTextfield feltene inn (Bil, Båt, Hus, Fritid)
    private JTextField   skadeStedBil, taksbelopBil, skadeTypeBil,beskrivSkadeBil, navnBil, telefonBil,
						 skadeStedBåt,taksbelopBåt, adresseBåt, skadeTypeBåt, beskrivBåt,
    					 adresseHus, skadeTypeHus, beskrivHus, taksbelopHus,
    					 adresseFritid, skadeTypeFritid, beskrivFritid, taksbelopFritid;


    //private JLabel informationTop;
    private int DATA_FIELD_LENGTH = 15, BIL_KAT = 1, BÅT_KAT = 2, HUS_KAT = 3, FRITID_KAT = 4;
    private String vnn = " ", vnrr = " ";
    private JLabel  beskrivSkadeLabel, skadeDatoBilLabel, skadeStedBilLabel, taksbelopBilLabel, skadeTypeBilLabel,
   				    beskrivSkadeBilLabel,navnBilLabel, telefonBilLabel,
    				skadeDatoBåtLabel, skadeStedBåtLabel,taksbelopBåtLabel,
    				adresseBåtLabel, skadeTypeBåtLabel, beskrivBåtLabel,
    				skadeDatoHusLabel, adresseHusLabel, skadeTypeHusLabel,beskrivHusLabel, taksbelopHusLabel,
    				skadeDatoFritidLabel, adresseFritidLabel, skadeTypeFritidLabel, beskrivFritidLabel, taksbelopFritidLabel;



    public SkadeMeldingVindu(Huvudvindu vind, Kunde kunn)
    {
    	//Vinduets size.
    	setTitle("Fyll ut skadeskjema");
    	setSize(600,300 );
		vindu = vind;
		kunde = kunn;
		Lytter = new Lytterklasse();
    	register = vind.getRegister();
    	setLocationRelativeTo(null);
        cardPanel = new JPanel(new BorderLayout());
		p = new JPanel();

		b1 = new JButton("Skadeskjema");
		b2 = new JButton("Knapp");
		b3 = new JButton("Knapp2");
		b2.setVisible(false);
		b3.setVisible(false);
		lab = new JLabel ("Skadeskjema for bil ved ulykker");


		//Cardlayout for og swappa mellom de ulike forsikringerne.
        CardLayout sm = new CardLayout();
        cardPanel.setLayout(sm);

        //Panels for de ulike forsikringsalternativene.
        jp1 = new JPanel(new GridLayout(12,1));
        jp2 = new JPanel(new GridLayout(9,1));
        jp3 = new JPanel(new GridLayout(9,1));
        jp4 = new JPanel(new GridLayout(9,1));
        bildGrid = new JPanel(new GridLayout(1,4));

        bildGrid.add(b1);
        bildGrid.add(b2);
        bildGrid.add(b3);



    	//Labels for bil.
    	skadeDatoBilLabel = new JLabel("Skade dato (dd/MM/yyyy):");
		skadeDatoBil = new JFormattedTextField(new SimpleDateFormat("dd/MM/yyyy"));
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
		telefonBilLabel = new JLabel("Vitne telefon:");
		telefonBil = new JTextField(DATA_FIELD_LENGTH);

		jp1.add(skadeDatoBilLabel);
		jp1.add(skadeDatoBil);
		jp1.add(skadeStedBilLabel);
		jp1.add(skadeStedBil);
		jp1.add(beskrivSkadeBilLabel);
		jp1.add(beskrivSkadeBil);
		jp1.add(taksbelopBilLabel);
		jp1.add(taksbelopBil);
		jp1.add(navnBilLabel);
		jp1.add(navnBil);
		jp1.add(telefonBilLabel);
		jp1.add(telefonBil);
		jp1.add(lab);
		jp1.add(bildGrid);


    	//Båt
		skadeDatoBåtLabel = new JLabel("Skade dato: (dd/MM/yyyy)");
		skadeDatoBåt = new JFormattedTextField(new SimpleDateFormat("dd/MM/yyyy"));
		skadeStedBåtLabel = new JLabel("Skade sted:");
		skadeStedBåt = new JTextField(DATA_FIELD_LENGTH);
		skadeTypeBåtLabel = new JLabel("Type skade:");
		skadeTypeBåt = new JTextField(DATA_FIELD_LENGTH);
		beskrivBåtLabel = new JLabel("Beskriv skaden:");
		beskrivBåt = new JTextField(DATA_FIELD_LENGTH);
    	taksbelopBåtLabel = new JLabel("Takseringsbelop av skaden:");
		taksbelopBåt = new JTextField(DATA_FIELD_LENGTH);

		jp2.add(skadeDatoBåtLabel);
		jp2.add(skadeDatoBåt);
		jp2.add(skadeStedBåtLabel);
		jp2.add(skadeStedBåt);
		jp2.add(skadeTypeBåtLabel);
		jp2.add(skadeTypeBåt);
		jp2.add(beskrivBåtLabel);
		jp2.add(beskrivBåt);
		jp2.add(taksbelopBåtLabel);
		jp2.add(taksbelopBåt);

    	//Hus
		skadeDatoHusLabel = new JLabel("Skade dato (dd/MM/yyyy):");
		skadeDatoHus = new JFormattedTextField(new SimpleDateFormat("dd/MM/yyyy"));
		adresseHusLabel = new JLabel("Adresse:");
		adresseHus = new JTextField(DATA_FIELD_LENGTH);
		skadeTypeHusLabel = new JLabel("Type skade:");
		skadeTypeHus = new JTextField(DATA_FIELD_LENGTH);
		beskrivHusLabel = new JLabel("Beskriv skaden kortfattet:");
		beskrivHus = new JTextField(DATA_FIELD_LENGTH);
		taksbelopHusLabel = new JLabel("Takseringsbelop av skaden:");
		taksbelopHus = new JTextField(DATA_FIELD_LENGTH);


		jp3.add(skadeDatoHusLabel);
		jp3.add(skadeDatoHus);
		jp3.add(adresseHusLabel);
		jp3.add(adresseHus);
		jp3.add(skadeTypeHusLabel);
		jp3.add(skadeTypeHus);
		jp3.add(beskrivHusLabel);
		jp3.add(beskrivHus);
		jp3.add(taksbelopHusLabel);
		jp3.add(taksbelopHus);


        //Fritid
		skadeDatoFritidLabel = new JLabel("Skade dato (dd/MM/yyyy):");
		skadeDatoFritid = new JFormattedTextField(new SimpleDateFormat("dd/MM/yyyy"));
		adresseFritidLabel = new JLabel("Adresse:");
		adresseFritid = new JTextField(DATA_FIELD_LENGTH);
		skadeTypeFritidLabel = new JLabel("Type skade:");
		skadeTypeFritid = new JTextField(DATA_FIELD_LENGTH);
		beskrivFritidLabel = new JLabel("Beskriv skaden kortfattet:");
		beskrivFritid = new JTextField(DATA_FIELD_LENGTH);
		taksbelopFritidLabel = new JLabel("Takseringsbelop av skaden:");
		taksbelopFritid = new JTextField(DATA_FIELD_LENGTH);


		jp4.add(skadeDatoFritidLabel);
		jp4.add(skadeDatoFritid);
		jp4.add(adresseFritidLabel);
		jp4.add(adresseFritid);
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


         buttonPanelBott = new JPanel();
         bilKnapp = new JButton("Bil");
         båtKnapp = new JButton("Båt");
         husKnapp = new JButton("Hus");
         fritidKnapp = new JButton("Fritid");
         registrer = new JButton("Registrer");
         lukkVindu = new JButton("Lukk");
        buttonPanelBott.add(bilKnapp);
        buttonPanelBott.add(båtKnapp);
        buttonPanelBott.add(husKnapp);
        buttonPanelBott.add(fritidKnapp);
        buttonPanelBott.add(lukkVindu);
        buttonPanelBott.add(registrer);

        bilKnapp.addActionListener(Lytter);
        båtKnapp.addActionListener(Lytter);
        husKnapp.addActionListener(Lytter);
        fritidKnapp.addActionListener(Lytter);
        lukkVindu.addActionListener(Lytter);
        registrer.addActionListener(Lytter);

        getContentPane().add(cardPanel, BorderLayout.LINE_START);
        getContentPane().add(p, BorderLayout.CENTER);
        getContentPane().add(buttonPanelBott, BorderLayout.PAGE_END);
    }


	public void slettFelter()
	{
		skadeDatoBil.setText("");
		skadeStedBil.setText("");
		taksbelopBil.setText("");
		skadeTypeBil.setText("");
		beskrivSkadeBil.setText("");
		navnBil.setText("");
		telefonBil.setText("");
		skadeDatoBåt.setText("");
		skadeStedBil.setText("");
		taksbelopBåt.setText("");
		skadeTypeBåt.setText("");
		beskrivBåt.setText("");
		skadeDatoHus.setText("");
		skadeTypeHus.setText("");
		beskrivHus.setText("");
		taksbelopHus.setText("");
		skadeDatoFritid.setText("");
		skadeTypeFritid.setText("");
		beskrivFritid.setText("");
		taksbelopFritid.setText("");
	}

	public void visFeilmelding(String melding)
	{
		JOptionPane.showMessageDialog(null, melding, "FEIL", JOptionPane.ERROR_MESSAGE);
	}

	public void nySkademeldingBil ()
	{
		try{
			String kundenr, sted, taks, beskriv, skadetype, navn, telefon, registernr;
			kundenr = kunde.getKundeNr();
			Date date = (Date) skadeDatoBil.getValue();
			Calendar dato = Calendar.getInstance();
			dato.setTime(date);
			sted = skadeStedBil.getText();
			taks = taksbelopBil.getText();
			int takst = Integer.valueOf(taks);
			beskriv = beskrivSkadeBil.getText();
			skadetype = taksbelopBil.getText();
			navn = navnBil.getText();
			telefon = telefonBil.getText();

			if(dato == null || sted.equals("")|| taks.equals("") || beskriv.equals("") || navn.equals("") || telefon.equals("") )
				//informationTop.setText("Alle feltene må fylles ut");
				visFeilmelding("Alle feltene må fylles ut");
			else
			{
				Skademelding skadebil = new Skademelding(kunde, dato, sted, skadetype, takst, beskriv, navn, telefon, BIL_KAT);
				register.nySkade(skadebil);
				JOptionPane.showMessageDialog(null, "Skademelding registrert");
			}

		}
		/*catch(NullPointerException npe)
		{
			JOptionPane.showMessageDialog(null, "NullPointerException i nySkademeldingBil i SkadeMeldingVindu", "ERROR", JOptionPane.ERROR_MESSAGE);
			return;
		}*/
		catch(NumberFormatException nfe)
		{
			JOptionPane.showMessageDialog(null, "NumberFormatException i nySkademeldingBil i SkadeMeldingVindu", "ERROR", JOptionPane.ERROR_MESSAGE);
			return;
		}
	}

	public void nySkademeldingBåt()
	{
		try{
			String kundenr, adresse, sted, type, taks;
			kundenr = kunde.getKundeNr();
			Date date = (Date) skadeDatoBåt.getValue();
			Calendar dato = Calendar.getInstance();
			dato.setTime(date);
			sted = skadeStedBåt.getText();
			type = skadeTypeBåt.getText();
			taks = taksbelopBåt.getText();
			int takst = Integer.valueOf(taks);
			String beskriv = beskrivBåt.getText();

			if(dato == null || sted.equals("")|| type.equals("") || taks.equals("") || beskriv.equals(""))
				informationTop.setText("Alle feltene må fylles ut");
			else
			{
				Skademelding skadebåt = new Skademelding(kunde, dato, sted, type, takst, beskriv, null, null, BÅT_KAT);
				register.nySkade(skadebåt);
				JOptionPane.showMessageDialog(null, "Skademelding registrert");
			}

		}

		/*catch(NullPointerException npe)
		{
			JOptionPane.showMessageDialog(null, "NullPointerException i nySkademeldingBil i SkadeMeldingVindu", "ERROR", JOptionPane.ERROR_MESSAGE);
			return;
		}*/
		catch(NumberFormatException nfe)
		{
			JOptionPane.showMessageDialog(null, "NumberFormatException i nySkademeldingBil i SkadeMeldingVindu", "ERROR", JOptionPane.ERROR_MESSAGE);
			return;
		}
	}

	public void nySkademeldingHus()
	{
		try{
			String type, beskriv, taks, navn, kundenr, adresse;
			kundenr = kunde.getKundeNr();
			Date date = (Date) skadeDatoHus.getValue();
			Calendar dato = Calendar.getInstance();
			dato.setTime(date);
			adresse = adresseHus.getText();
			type = skadeTypeHus.getText();
			taks = taksbelopHus.getText();
			int takst = Integer.valueOf(taks);
			beskriv = beskrivHus.getText();

			if(dato == null || adresse.equals("")|| type.equals("") || taks.equals("") || beskriv.equals(""))
				informationTop.setText("Alle feltene må fylles ut");
			else
			{
				Skademelding skadehus = new Skademelding(kunde, dato, adresse, type, takst, beskriv, null, null, HUS_KAT);
				register.nySkade(skadehus);
				JOptionPane.showMessageDialog(null, "Skademelding registrert");
			}
		}
		/*catch(NullPointerException npe)
		{
			JOptionPane.showMessageDialog(null, "NullPointerException i nySkademeldingBil i SkadeMeldingVindu", "ERROR", JOptionPane.ERROR_MESSAGE);
			return;
		}*/
		catch(NumberFormatException nfe)
		{
			JOptionPane.showMessageDialog(null, "NumberFormatException i nySkademeldingBil i SkadeMeldingVindu", "ERROR", JOptionPane.ERROR_MESSAGE);
			return;
		}
	}


	public void nySkademeldingFritid()
	{
		try{
			String type, beskriv, taks, navn, kundenr, adresse;
			kundenr = kunde.getKundeNr();
			Date date = (Date) skadeDatoFritid.getValue();
			Calendar dato = Calendar.getInstance();
			dato.setTime(date);
			adresse = adresseFritid.getText();
			type = skadeTypeFritid.getText();
			taks = taksbelopFritid.getText();
			int takst = Integer.valueOf(taks);
			beskriv = beskrivFritid.getText();

			if(dato == null || adresse.equals("") || type.equals("") || taks.equals("") || beskriv.equals(""))
				informationTop.setText("Alle feltene må fylles ut");
			else
			{
				Skademelding skadefritid = new Skademelding(kunde, dato, adresse, type, takst, beskriv, null, null, FRITID_KAT);
				register.nySkade(skadefritid);
				JOptionPane.showMessageDialog(null, "Skademelding registrert");
			}
		}
		/*catch(NullPointerException npe)
		{
			JOptionPane.showMessageDialog(null, "NullPointerException i nySkademeldingBil i SkadeMeldingVindu", "ERROR", JOptionPane.ERROR_MESSAGE);
			return;
		}*/
		catch(NumberFormatException nfe)
		{
			JOptionPane.showMessageDialog(null, "NumberFormatException i nySkademeldingBil i SkadeMeldingVindu", "ERROR", JOptionPane.ERROR_MESSAGE);
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
					nySkademeldingBil();
				else if(jp2.isVisible() == true)
					nySkademeldingBåt();
				else if(jp3.isVisible() == true)
					nySkademeldingHus();
				else if(jp4.isVisible() == true)
					nySkademeldingFritid();
			}

			else if(e.getSource() == bilKnapp)
			{
				sm = (CardLayout) cardPanel.getLayout();
                sm.show(cardPanel, "1");
           		/*informationTop.setText("Skademelding Bil");
                informationTop.revalidate();
                informationTop.repaint();*/
			}

			else if(e.getSource() == båtKnapp)
            {
            	CardLayout sm = (CardLayout) cardPanel.getLayout();
                sm.show(cardPanel, "2");
           		/*informationTop.setText("Skademelding Båt");
                informationTop.revalidate();
                informationTop.repaint();*/
            }

            else if(e.getSource() == husKnapp)
            {
            	CardLayout sm = (CardLayout) cardPanel.getLayout();
                sm.show(cardPanel, "3");
           		/*informationTop.setText("Skademelding Hus");
                informationTop.revalidate();
                informationTop.repaint();*/
            }

            else if(e.getSource() == fritidKnapp)
            {
            	CardLayout sm = (CardLayout) cardPanel.getLayout();
                sm.show(cardPanel, "4");
           		/*informationTop.setText("Skademelding Fritid");
                informationTop.revalidate();
                informationTop.repaint();*/
            }
            else if(e.getSource() == lukkVindu)
            	dispose();
		}
	}

	private JTextField JTextField(int dATA_FIELD_LENGTH2) {
		// TODO Auto-generated method stub
		return null;
	}

 }
