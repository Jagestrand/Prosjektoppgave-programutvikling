import javax.swing.*;
import javax.swing.border.MatteBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.image.*;
import java.awt.*;
import java.awt.event.*;
import java.util.NoSuchElementException;


public class KundeGUI extends JPanel
{
	private Register register;
	private Kunde kunde;
	private boolean aktiv;
	private Huvudvindu vindu;
	private Lytterklasse lytter;
	private int DATA_FIELD_LENGTH = 20;
	private String printNavn, printEtternavn, printTelefon, printAdresse, printPrnmr, printPoststed, printPostNmr;
	private JTextField navn, telefon, adresse, personnr;
	private JTextArea informationTop, visForsikringInfo;
	private JButton visSkademelding, regSkademelding, regForsikring, deaktiverF, båtKnapp, bilKnapp, husKnapp, fritidKnapp;
	private JLabel inloggadSom, navnLabel, etternavnLabel, telefonLabel, adresseLabel, postStedLabel, postNmrLabel, personnrLabel;
	private JPanel border, borderLeft, borderRight, flow, flowLeft, flowRight,registerGrid, registerGridLeft, registerGridRight;
	private ButtonGroup kategoriKnapper;
	private JRadioButton bilKat, båtKat, husKat, hytKat, skadeKat;
//	private RadioLytter radiolytter;
	private ChangeLytter changeLytter;
	private JTabbedPane tabbedPane, tabbedPane2;
	private JTable table, table21, table22, table23, table24, table25;
	private TModel tableModel, tableModel21, tableModel22, tableModel23, tableModel24, tableModel25;

	public KundeGUI(Huvudvindu v, Kunde kunn)
	{
		vindu = v;
		kunde = kunn;
		register = vindu.getRegister();
		lytter = new Lytterklasse();
		aktiv = true;
		
		setLayout(new BorderLayout() );
		border = new JPanel(new BorderLayout());
		GridLayout gridlayout = new GridLayout(12, 1);
		
		//Top
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
		deaktiverF = new JButton ("Deaktiver forsikring");
		regForsikring = new JButton("Registrere forsikring");
		regSkademelding = new JButton("Registrere skademeldinger");

		registerGridLeft.add(regForsikring);
		registerGridLeft.add(deaktiverF);
		
		deaktiverF.addActionListener(lytter);
	//	registerGridLeft.add(båtKnapp);
	//	registerGridLeft.add(bilKnapp);
	//	registerGridLeft.add(husKnapp);
	//	registerGridLeft.add(fritidKnapp);
	//		regForsikring.addActionListener(lytter);
		
		//visSkademelding = new JButton("Se skademeldinger");

		registerGridLeft.add(regSkademelding);
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
		
		
		tableModel21 = new TModel(kunde.getBiler());	//HER
		table21 = new JTable(tableModel21);
		tableModel22 = new TModel(kunde.getBåter());
		table22 = new JTable(tableModel22);
		tableModel23 = new TModel(kunde.getHus());
		table23 = new JTable(tableModel23);
		tableModel24 = new TModel(kunde.getHytter());
		table24 = new JTable(tableModel24);
		tableModel25 = new TModel(kunde.getSkademeldinger());
		table25 = new JTable(tableModel25);

		
		tabbedPane2 = new JTabbedPane();
		tabbedPane2.addTab("Bil", null, (new JScrollPane(table21, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED)), "Liste over biler");
		tabbedPane2.setMnemonicAt(0, KeyEvent.VK_1);
		tabbedPane2.addTab("Båt", null, (new JScrollPane(table22, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED)), "Liste over båter");
		tabbedPane2.setMnemonicAt(1, KeyEvent.VK_2);
		tabbedPane2.addTab("Hus", null, (new JScrollPane(table23, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED)), "Liste over hus");
		tabbedPane2.setMnemonicAt(2, KeyEvent.VK_3);
		tabbedPane2.addTab("Hytte", null, (new JScrollPane(table24, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED)), "Liste over hytter");
		tabbedPane2.setMnemonicAt(3, KeyEvent.VK_4);
		tabbedPane2.addTab("Skademelding", null, (new JScrollPane(table25, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED)), "Liste over skader");
		tabbedPane2.setMnemonicAt(3, KeyEvent.VK_4);
		tabbedPane2.addChangeListener(changeLytter);
		
		regForsikring.addActionListener (lytter);

		regSkademelding.addActionListener(lytter);


		add(new JScrollPane(tabbedPane2, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER), BorderLayout.CENTER);
	}

	public void nyForsikring()
	{
		ValgAvForsikring cl = new ValgAvForsikring(vindu, kunde);
		cl.setVisible(true);
	}

	/*public void nySkademelding()
	{
		SkadeMeldingVindu smv = new SkadeMeldingVindu(vindu, kunde);
		smv.setVisible(true);
	}*/

	/*public void seSkademelding()
	{
		JOptionPane.showMessageDialog(vind, kunn);
	}
	*/
	public void deaktiverF()
	{
		try
		{
			int row = table21.getSelectedRow();
			if(row == -1)
				return;

			BilForsikring bilReg = register.getBilViaRegNr( (String)tableModel.getValueAt(row, TModel.PERSON_NR) );
			if(!bilReg.getAktiv())
				return;
			String melding = "Er du sikker på at du vil deaktivere forsikringen?";
			String tittel = "Validering";

			int svar = JOptionPane.showConfirmDialog(null, melding, tittel, JOptionPane.YES_NO_OPTION);
			if(svar == JOptionPane.YES_OPTION)
			{
				register.deaktiverF(bilReg);
				
				return;
			}
		}
		catch(NoSuchElementException nsee)
		{
			JOptionPane.showMessageDialog(null, "NoSuchElementException i deaktiverBruker-metoden", "FEIL", JOptionPane.ERROR_MESSAGE);
		}
	}
	private class ChangeLytter implements ChangeListener
	{
		public void stateChanged(ChangeEvent e)
		{
			if(tabbedPane2.getSelectedIndex() == 0)
				bilKat.setSelected(true);
			else if(tabbedPane2.getSelectedIndex() == 1)
				båtKat.setSelected(true);
			else if(tabbedPane2.getSelectedIndex() == 2)
				husKat.setSelected(true);
			else if(tabbedPane2.getSelectedIndex() == 3)
				hytKat.setSelected(true);
			else if(tabbedPane2.getSelectedIndex() == 4)
				skadeKat.setSelected(true);
		}
	}


	private class Lytterklasse implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource() == regForsikring)
				nyForsikring();
			else if(e.getSource() == regSkademelding)
		//		nySkademelding();
		//	else if(e.getSource() == deaktiverF)
				deaktiverF();
		//	else if(e.getSource() == visSkademelding)
		//		seSkademelding();
		}
	}
}

