import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import javax.swing.*;
import java.text.*;

public class SkadeMeldingVindu extends JFrame
 {
    private JPanel cardPanel, infoPanel,  jp1, jp2, jp3, jp4;
    private JScrollPane scrollPane;
    private JPanel panel;
    private Huvudvindu vindu;
    private Kunde kunde;
    private JTextArea informationTop;
    private JFormattedTextField skadeDatoBil;
    private JButton bilknapp, båtknapp, husknapp, fritidknapp, lukkVindu,registrer, lukkVinduBåt, lukkVinduHus, lukkVinduFritid, registrerBåt, registrerHus,registrerFritid;
    private CardLayout sm;


    // Her legges JTextfield feltene inn (Bil, Båt, Hus, Fritid)
    private JTextField  skadeStedBil, taksbelopBil, skadeTypeBil,beskrivSkadeBil, navnBil, telefonBil,
						skadeDatoBåt, skadeStedBåt,taksbelopBåt, adresseBåt, skadeTypeBåt, beskrivBåt,
    					skadeDatoHus, adresseHus, skadeTypeHus, beskrivHus, taksbelopHus,
    					skadeDatoFritid, adresseFritid, skadeTypeFritid, beskrivFritid, taksbelopFritid;


    //private JLabel informationTop;
    private int DATA_FIELD_LENGTH = 15;
    private JLabel  skadeDatoBilLabel, skadeStedBilLabel, taksbelopBilLabel, skadeTypeBilLabel,
   				    beskrivSkadeBilLabel,navnBilLabel, telefonBilLabel,
    				skadeDatoBåtLabel, skadeStedBåtLabel,taksbelopBåtLabel,
    				adresseBåtLabel, skadeTypeBåtLabel, beskrivBåtLabel,
    				skadeDatoHusLabel, adresseHusLabel, skadeTypeHusLabel,beskrivHusLabel, taksbelopHusLabel,
    				skadeDatoFritidLabel, adresseFritidLabel, skadeTypeFritidLabel, beskrivFritidLabel, taksbelopFritidLabel;



    public SkadeMeldingVindu(Huvudvindu vind, Kunde kunn)
    {
    	//Vinduets size.
    	setTitle("Fyll ut skadeskjema");
    	setSize(800,500 );
    	setLocationRelativeTo(null);
        cardPanel = new JPanel(new BorderLayout());
		vindu = vind;
		kunde = kunn;


		//Cardlayout for og swappa mellom de ulike forsikringerne.
        CardLayout sm = new CardLayout();
        cardPanel.setLayout(sm);


        //Panels for de ulike forsikringsalternativene.
        JPanel jp1 = new JPanel(new GridLayout(10,2));
        JPanel jp2 = new JPanel(new GridLayout(10,2));
        JPanel jp3 = new JPanel(new GridLayout(10,2));
        JPanel jp4 = new JPanel(new GridLayout(10,2));



    	//Labels for bil.
    	skadeDatoBilLabel = new JLabel("Skade dato:");
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




    	//Båt
		skadeDatoBåtLabel = new JLabel("Skade dato:");
		skadeDatoBåt = new JTextField(DATA_FIELD_LENGTH);
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
		skadeDatoHusLabel = new JLabel("Skade dato:");
		skadeDatoHus = new JTextField(DATA_FIELD_LENGTH);
		adresseHusLabel = new JLabel("Adresse:");
		adresseHus = new JTextField(DATA_FIELD_LENGTH);
		skadeTypeHusLabel = new JLabel("Type skade:");
		skadeTypeHus = new JTextField(DATA_FIELD_LENGTH);
		beskrivHusLabel = new JLabel("Beskriv skaden:");
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
		skadeDatoFritidLabel = new JLabel("Skade dato:");
		skadeDatoFritid = new JTextField(DATA_FIELD_LENGTH);
		adresseFritidLabel = new JLabel("Adresse:");
		adresseFritid = new JTextField(DATA_FIELD_LENGTH);
		skadeTypeFritidLabel = new JLabel("Type skade:");
		skadeTypeFritid = new JTextField(DATA_FIELD_LENGTH);
		beskrivFritidLabel = new JLabel("Beskriv skaden:");
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



	public void nySkademeldingBil()
	{
		try{
			String kundenr, sted, taks, beskriv, skadetype, navn, telefon, regexPattern;
			kundenr = kunde.getKundeNr();
			Calendar dato;
			regexPattern = "(0[1-9]|[12][0-9]|3[01])(0[1-9]|1[012])(\\d{7})";
			sted = skadeStedBil.getText();
			taks = taksbelopBil.getText();
			int takst = Integer.valueOf(taks);
			beskriv = beskrivSkadeBil.getText();
			skadetype = taksbelopBil.getText();
			navn = navnBil.getText();
			telefon = telefonBil.getText();

			if(dato.equals("") || sted.equals("")|| taks.equals("") || beskriv.equals("") || navn.equals("") || telefon.equals("") )
				informationTop.setText("Alle feltene må fylles ut");
			//else if(telefon.matches(regexPattern) )
			//	informationTop.setText("Telefon nummer er ikke gyldig");
			else
			{
				Skademelding skadebil = new Skademelding(kundenr, dato, sted, skadetype, takst, beskriv, navn, telefon, null);
				register.nySkademelding(skadebil);
				JOptionPane.showMessageDialog(null, "Skademelding registrert");
			}

		}
		catch(NullPointerException npe)
		{
			JOptionPane.showMessageDialog(null, "NullPointerException i nySkademeldingBil i SkadeMeldingVindu", "ERROR", JOptionPane.ERROR_MESSAGE);
			return;
		}
		catch(NumberFormatException nfe)
		{
			JOptionPane.showMessageDialog(null, "NumberFormatException i nySkademeldingBil i SkadeMeldingVindu", "ERROR", JOptionPane.ERROR_MESSAGE);
			return;
		}
	}

	public void nySkademeldingBåt()
		{
			try{
				String kundenr, adresse, sted, type, taks, regexPattern;
				kundenr = kunde.getKundeNr();
				Calendar dato;
				regexPattern = "(0[1-9]|[12][0-9]|3[01])(0[1-9]|1[012])(\\d{7})";
				sted = skadeStedBåt.getText();
				type = skadeTypeBåt.getText();
				taks = taksbelopBåt.getText();
				int takst = Integer.valueOf(taks);
				String beskriv = beskrivBåt.getText();

				if(dato.equals("") || sted.equals("")|| type.equals("") || taks.equals("") || beskriv.equals(""))
					informationTop.setText("Alle feltene må fylles ut");
				//else if(!telefon.matches(regexPattern) )
				//	informationTop.setText("Telefon nummer er ikke gyldig");
				else
				{
					Skademelding skadebåt = new Skademelding(kundenr, dato, sted, type, takst, beskriv);
					register.nySkademelding(skadebåt);
					JOptionPane.showMessageDialog(null, "Skademelding registrert");
				}

			}

			catch(NullPointerException npe)
			{
				JOptionPane.showMessageDialog(null, "NullPointerException i nySkademeldingBil i SkadeMeldingVindu", "ERROR", JOptionPane.ERROR_MESSAGE);
				return;
			}
			catch(NumberFormatException nfe)
			{
				JOptionPane.showMessageDialog(null, "NumberFormatException i nySkademeldingBil i SkadeMeldingVindu", "ERROR", JOptionPane.ERROR_MESSAGE);
				return;
			}
	}

	public void nySkademeldingHus()
		{
			try{
				String type, beskriv, taks, navn, regexPattern, kundenr, adresse;
				kundenr = kunde.getKundeNr();
				Calendar dato = Calendar.getInstance();
				regexPattern = "(0[1-9]|[12][0-9]|3[01])(0[1-9]|1[012])(\\d{7})";
				adresse = adresseHus.getText();
				type = skadeTypeHus.getText();
				taks = taksbelopHus.getText();
				int takst = Integer.valueOf(taks);
				beskriv = beskrivHus.getText();



				if(dato.equals("") || adresse.equals("")|| type.equals("") || taks.equals("") || beskriv.equals(""))
					informationTop.setText("Alle feltene må fylles ut");
				//else if(telefon.matches(regexPattern) )
				//	informationTop.setText("Telefon nummer er ikke gyldig");
				else
				{
					Skademelding skadehus = new Skademelding(kundenr, dato, adresse, type, takst, beskriv);
					register.nySkademelding(skadehus);
					JOptionPane.showMessageDialog(null, "Skademelding registrert");
				}

			}
			catch(NullPointerException npe)
			{
				JOptionPane.showMessageDialog(null, "NullPointerException i nySkademeldingBil i SkadeMeldingVindu", "ERROR", JOptionPane.ERROR_MESSAGE);
				return;
			}
			catch(NumberFormatException nfe)
			{
				JOptionPane.showMessageDialog(null, "NumberFormatException i nySkademeldingBil i SkadeMeldingVindu", "ERROR", JOptionPane.ERROR_MESSAGE);
				return;
			}
	}


	public void nySkademeldingFritid()
		{
			try{
				String type, beskriv, taks, navn, regexPattern, kundenr, adresse;
				regexPattern = "(0[1-9]|[12][0-9]|3[01])(0[1-9]|1[012])(\\d{7})";
				kundenr = kunde.getKundeNr();
				Calendar dato;
				adresse = adresseFritid.getText();
				type = skadeTypeFritid.getText();
				taks = taksbelopFritid.getText();
				int takst = Integer.valueOf(taks);
				beskriv = beskrivFritid.getText();


				if(dato.equals("") || adresse.equals("") || type.equals("") || taks.equals("") || beskriv.equals(""))
					informationTop.setText("Alle feltene må fylles ut");
				//else if(telefon.matches(regexPattern) )
				//	informationTop.setText("Telefon nummer er ikke gyldig");
				else
				{
					Skademelding skadefritid = new Skademelding(kundenr, dato, adresse, type, takst, beskriv);
					register.nySkademelding(skadefritid);
					JOptionPane.showMessageDialog(null, "Skademelding registrert");
				}

			}
			catch(NullPointerException npe)
			{
				JOptionPane.showMessageDialog(null, "NullPointerException i nySkademeldingBil i SkadeMeldingVindu", "ERROR", JOptionPane.ERROR_MESSAGE);
				return;
			}
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
           		informationTop.setText("Skademelding Bil");
                informationTop.revalidate();
                informationTop.repaint();
			}

			else if(e.getSource() == båtKnapp)
            {
            	CardLayout sm = (CardLayout) cardPanel.getLayout();
                sm.show(cardPanel, "2");
           		//informationTop.setText("");
           		informationTop.setText("Skademelding Båt");
                informationTop.revalidate();
                informationTop.repaint();
            }

            else if(e.getSource() == husKnapp)
            {
            	CardLayout sm = (CardLayout) cardPanel.getLayout();
                sm.show(cardPanel, "3");
           		informationTop.removeAll();
           		informationTop.setText("Skademelding Hus");
                informationTop.revalidate();
                informationTop.repaint();
            }

            else if(e.getSource() == fritidKnapp)
            {
            	CardLayout sm = (CardLayout) cardPanel.getLayout();
                sm.show(cardPanel, "4");
           		informationTop.setText("Skademelding Fritid");
                informationTop.revalidate();
                informationTop.repaint();
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
