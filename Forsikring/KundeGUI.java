import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.image.*;
import java.awt.*;
import java.awt.event.*;


public class KundeGUI extends JPanel
{
	private Register register;
	private Kunde kunde;
	private Huvudvindu vindu;
	private Lytterklasse lytter;
	private int DATA_FIELD_LENGTH = 20;
	private String printNavn, printEtternavn, printTelefon, printAdresse, printPrnmr, printPoststed, printPostNmr;
	private JTextField navn, telefon, adresse, personnr;
	private JTextArea informationTop, visForsikringInfo;
	private JButton visSkademelding, regSkademelding, regForsikring, båtKnapp, bilKnapp, husKnapp, fritidKnapp;
	private JLabel inloggadSom, navnLabel, etternavnLabel, telefonLabel, adresseLabel, postStedLabel, postNmrLabel, personnrLabel;
	private JPanel border, borderLeft, borderRight, flow, flowLeft, flowRight,registerGrid, registerGridLeft, registerGridRight;
	private JTable table;
	private MineForsikringer mineforsikringer;
	
	public KundeGUI(Huvudvindu v, Kunde kunn)
	{
		vindu = v;
		kunde = kunn;
		register = vindu.getRegister();
		lytter = new Lytterklasse();
		mineforsikringer = new MineForsikringer(kunde.getBiler(),kunde.getBåter(),kunde.getHus(),kunde.getHytter());
		
		setLayout(new BorderLayout() );
		border = new JPanel(new BorderLayout());
		GridLayout gridlayout = new GridLayout(12, 1);

		//gridlayout.setVgap(10);
		/*BackgroundPanel panel = new BackgroundPanel(duke, BackgroundPanel.ACTUAL, 1.0f, 0.5f);
		GradientPaint paint = new GradientPaint(0, 0, Color.BLUE, 600, 0, Color.RED);
			panel.setPaint(paint);*/
		registerGrid = new JPanel(gridlayout);
		flow = new JPanel( new FlowLayout());	
		
		//Panes vänster
		borderLeft = new JPanel(new BorderLayout());
		GridLayout gridlayoutLeft = new GridLayout(12, 1);
		
		registerGridLeft = new JPanel(gridlayoutLeft);
		flowLeft = new JPanel( new FlowLayout());
		
		//Panels höger
		borderRight = new JPanel(new BorderLayout());
		GridLayout gridlayoutRight = new GridLayout(12, 1);
		
		registerGridRight = new JPanel(gridlayoutRight);
		flowRight = new JPanel( new FlowLayout());
		
		//Kund info högerpanel
		printNavn = kunde.getFornavn();
		printEtternavn = kunde.getEtternavn();
		printTelefon = kunde.getTelefonNr();
		printAdresse = kunde.getAdresse();
		printPoststed = kunde.getPoststed();
		printPostNmr = kunde.getPostnr();
		printPrnmr = kunde.getPersonNr();
		
		inloggadSom = new JLabel("Kund information: ");
		navnLabel = new JLabel("Navn: " + " " + printNavn);
		etternavnLabel = new JLabel("Etternavn: " + " " + printEtternavn);
		telefonLabel = new JLabel("Telefon: " + " " + printTelefon);
		adresseLabel = new JLabel("Adresse: " + " " + printAdresse);
		postStedLabel = new JLabel("Poststed: " + " " + printPoststed);
		postNmrLabel = new JLabel("Postnummer: " + " " + printPostNmr);
		personnrLabel = new JLabel("Personnummer:" + " " + printPrnmr);
		
		inloggadSom.setFont(new Font("Serif", Font.BOLD, 14));
		inloggadSom.setForeground(Color.RED);
		navnLabel.setFont(new Font("Serif", Font.ITALIC, 14));
		etternavnLabel.setFont(new Font("Serif", Font.ITALIC, 14));
		telefonLabel.setFont(new Font("Serif", Font.ITALIC, 14));
		adresseLabel.setFont(new Font("Serif", Font.ITALIC, 14));
		postStedLabel.setFont(new Font("Serif", Font.ITALIC, 14));
		postNmrLabel.setFont(new Font("Serif", Font.ITALIC, 14));
		personnrLabel.setFont(new Font("Serif", Font.ITALIC, 14));
		
		//knappar på vänstergrid
		
		//båtKnapp = new JButton("Mine båtforsikringer");
		//bilKnapp = new JButton("Mine bilforsikringer");
		//husKnapp = new JButton("Mine husforsikringer");
		//fritidKnapp = new JButton("Mine hytteforsikringer");*/
		regForsikring = new JButton("Registrere forsikringer");
		registerGridRight.add(regForsikring);
	//	registerGridLeft.add(båtKnapp);
	//	registerGridLeft.add(bilKnapp);
	//	registerGridLeft.add(husKnapp);
	//	registerGridLeft.add(fritidKnapp);
	//		regForsikring.addActionListener(lytter);
		
		regSkademelding = new JButton("Registrere skademeldinger");
		//visSkademelding = new JButton("Se skademeldinger");

		registerGridRight.add(regSkademelding);
		//registerGridRight.add(visSkademelding);

	//	regSkademelding.addActionListener(lytter);
	//	visSkademelding.addActionListener(lytter);

		registerGridRight.add(inloggadSom);
		registerGridRight.add(navnLabel);
		registerGridRight.add(etternavnLabel);
		registerGridRight.add(telefonLabel);
		registerGridRight.add(adresseLabel);
		registerGridRight.add(postStedLabel);
		registerGridRight.add(postNmrLabel);
		registerGridRight.add(personnrLabel);
		
		registerGridLeft.add(mineforsikringer);
		
		border.add(registerGrid, BorderLayout.LINE_START);
		flow.add(registerGrid);

		add( new JScrollPane(flow, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER)
		,BorderLayout.NORTH);
		//legger til elementer i hovedpanelet
		add(new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER), BorderLayout.CENTER);
		
		borderLeft.add(registerGridLeft, BorderLayout.WEST);
		flowLeft.add(borderLeft);
		
		add( new JScrollPane(flowLeft, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER)
		,BorderLayout.WEST);
		//legger til elementer i hovedpanelet
		
		borderRight.add(registerGridRight, BorderLayout.EAST);
		flowRight.add(borderRight);
		
		add( new JScrollPane(flowRight, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER)
		,BorderLayout.EAST);
		//legger til elementer i hovedpanelet
		

		regForsikring.addActionListener (lytter);
	//	regForsikring.addActionListener (new Action2());
	//	visSkademelding = new JButton("Se skademeldinger");
	//	registerGrid.add(visSkademelding);
		regSkademelding.addActionListener(lytter);
	//	visSkademelding.addActionListener(lytter);


	}

	public void nyForsikring()
	{
		ValgAvForsikring cl = new ValgAvForsikring(vindu, kunde);
		cl.setVisible(true);
	}

	public void nySkademelding()
	{
		SkadeMeldingVindu smv = new SkadeMeldingVindu(vindu, kunde);
		smv.setVisible(true);
	}

	/*public void seSkademelding()
	{
		JOptionPane.showMessageDialog(vind, kunn);
	}
	*/



	private class Lytterklasse implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource() == regForsikring)
				nyForsikring();
			else if(e.getSource() == regSkademelding)
				nySkademelding();
		//	else if(e.getSource() == visSkademelding)
		//		seSkademelding();
		}
	}
}

