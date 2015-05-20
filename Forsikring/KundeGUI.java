import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.NoSuchElementException;


public class KundeGUI extends JPanel
{
	private static final long serialVersionUID = 42L;
	private Register register;
	private Kunde kunde;
	private Huvudvindu vindu;
	private Lytterklasse lytter;
	private String printNavn, printEtternavn, printTelefon, printAdresse, printPrnmr, printPoststed, printPostNmr;
	private JButton regSkademelding, regForsikring, deaktiverF, oppdaterKnapp;
	private JLabel inloggadSom, navnLabel, etternavnLabel, telefonLabel, adresseLabel, postStedLabel, postNmrLabel, personnrLabel;
	private JPanel border, borderLeft, borderRight, flow, flowLeft, flowRight,registerGrid, registerGridLeft, registerGridRight;
	private JTabbedPane tabbedPane2;
	private JTable table, table21, table22, table23, table24, table25;
	private TModel tableModel, tableModel21, tableModel22, tableModel23, tableModel24, tableModel25;

	public KundeGUI(Huvudvindu v, Kunde kunn)
	{
		vindu = v;
		kunde = kunn;
		register = vindu.getRegister();

		//Oppretter lyttere
		lytter = new Lytterklasse();

		//Setter layout
		setLayout(new BorderLayout() );
		border = new JPanel(new BorderLayout());
		GridLayout gridlayout = new GridLayout(12, 1);

		//oppretter JPaneler med ulike LayoutManagere
		registerGrid = new JPanel(gridlayout);
		flow = new JPanel( new FlowLayout());
		//Panes til WEST
		borderLeft = new JPanel(new BorderLayout());
		GridLayout gridlayoutLeft = new GridLayout(12, 1);
		registerGridLeft = new JPanel(gridlayoutLeft);
		flowLeft = new JPanel( new FlowLayout());
		//Panels til EAST
		borderRight = new JPanel(new BorderLayout());
		GridLayout gridlayoutRight = new GridLayout(12, 1);

		registerGridRight = new JPanel(gridlayoutRight);
		flowRight = new JPanel( new FlowLayout());

		//Finner kundens personinformation
		printNavn = kunde.getFornavn();
		printEtternavn = kunde.getEtternavn();
		printTelefon = kunde.getTelefonNr();
		printAdresse = kunde.getAdresse();
		printPoststed = kunde.getPoststed();
		printPostNmr = kunde.getPostnr();
		printPrnmr = kunde.getPersonNr();

		//Setter informationen till en Jlabel for visning
		inloggadSom = new JLabel("Kund information: ");
		navnLabel = new JLabel("Navn: " + " " + printNavn);
		etternavnLabel = new JLabel("Etternavn: " + " " + printEtternavn);
		telefonLabel = new JLabel("Telefon: " + " " + printTelefon);
		adresseLabel = new JLabel("Adresse: " + " " + printAdresse);
		postStedLabel = new JLabel("Poststed: " + " " + printPoststed);
		postNmrLabel = new JLabel("Postnummer: " + " " + printPostNmr);
		personnrLabel = new JLabel("Personnummer:" + " " + printPrnmr);

		//Setter fontstyle och ferg på den printade teksten
		inloggadSom.setFont(new Font("Serif", Font.BOLD, 14));
		inloggadSom.setForeground(Color.RED);
		navnLabel.setFont(new Font("Serif", Font.ITALIC, 14));
		etternavnLabel.setFont(new Font("Serif", Font.ITALIC, 14));
		telefonLabel.setFont(new Font("Serif", Font.ITALIC, 14));
		adresseLabel.setFont(new Font("Serif", Font.ITALIC, 14));
		postStedLabel.setFont(new Font("Serif", Font.ITALIC, 14));
		postNmrLabel.setFont(new Font("Serif", Font.ITALIC, 14));
		personnrLabel.setFont(new Font("Serif", Font.ITALIC, 14));

		//legger elementer til i GUI
		registerGridRight.add(inloggadSom);
		registerGridRight.add(navnLabel);
		registerGridRight.add(etternavnLabel);
		registerGridRight.add(telefonLabel);
		registerGridRight.add(adresseLabel);
		registerGridRight.add(postStedLabel);
		registerGridRight.add(postNmrLabel);
		registerGridRight.add(personnrLabel);

		//Skapar knapparna till panelen på venster sida
		deaktiverF = new JButton ("Deaktiver forsikring");
		regForsikring = new JButton("Registrere forsikring");
		regSkademelding = new JButton("Registrere skademeldinger");

		//Legger in knapparna till panelen på venster sida
		registerGridLeft.add(regForsikring);
		registerGridLeft.add(deaktiverF);
		registerGridLeft.add(regSkademelding);

		//Legger til elementer i hovedpanelet
		border.add(registerGrid, BorderLayout.LINE_START);
		flow.add(registerGrid);
		add( new JScrollPane(flow, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER)
		,BorderLayout.NORTH);
		add(new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER), BorderLayout.CENTER);
		borderLeft.add(registerGridLeft, BorderLayout.WEST);
		flowLeft.add(borderLeft);
		add( new JScrollPane(flowLeft, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER)
		,BorderLayout.WEST);
		borderRight.add(registerGridRight, BorderLayout.EAST);
		flowRight.add(borderRight);
		add( new JScrollPane(flowRight, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER)
		,BorderLayout.EAST);

		//Oppretter tabellen for visning
		tableModel21 = new TModel(kunde.getBiler());
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

		add(new JScrollPane(tabbedPane2, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER), BorderLayout.CENTER);

		//Binder lytter till knappar
		regForsikring.addActionListener (lytter);
		regSkademelding.addActionListener(lytter);
		deaktiverF.addActionListener(lytter);
	}

	//Kallar på registrerings GUI
	public void nyForsikring()
	{
		ValgAvForsikring cl = new ValgAvForsikring(vindu, kunde);
		cl.setVisible(true);
	}

	public void nySkademeldingBil()
	{
		try
		{
			int row = table21.getSelectedRow();
			if(row == -1)
				return;

          	BilForsikring bill = register.getBilViaNr( (int)tableModel21.getValueAt(row, TModel.FORSIKRINGS_NR) );
          	new SkadeMeldingVindu(vindu, kunde, bill, null, null, null);
        }
        catch(NoSuchElementException nsee)
        {
			JOptionPane.showMessageDialog(null, "NoSuchElementException i nySkademeldingBil-metoden", "FEIL", JOptionPane.ERROR_MESSAGE);
		}
	}
	public void nySkademeldingBåt()
	{
		try
		{
			int row = table22.getSelectedRow();
			if(row == -1)
				return;

          	BåtForsikring bått = register.getBåtViaNr( (int)tableModel22.getValueAt(row, TModel.FORSIKRINGS_NR) );
          	new SkadeMeldingVindu(vindu, kunde, null, bått, null, null);
        }
        catch(NoSuchElementException nsee)
        {
			JOptionPane.showMessageDialog(null, "NoSuchElementException i nySkademeldingBåt-metoden", "FEIL", JOptionPane.ERROR_MESSAGE);
		}
	}
	public void nySkademeldingHus()
	{
		try
		{
			int row = table23.getSelectedRow();
			if(row == -1)
				return;

          	HusForsikring huss = register.getHusViaNr( (int)tableModel23.getValueAt(row, TModel.FORSIKRINGS_NR) );
          	new SkadeMeldingVindu(vindu, kunde, null, null, huss, null);
        }
        catch(NoSuchElementException nsee)
        {
			JOptionPane.showMessageDialog(null, "NoSuchElementException i nySkademeldingHus-metoden", "FEIL", JOptionPane.ERROR_MESSAGE);
		}
	}
	public void nySkademeldingHytte()
	{
		try
		{
			int row = table24.getSelectedRow();
			if(row == -1)
				return;

          	HytteForsikring hyttt = register.getHytteViaNr( (int)tableModel24.getValueAt(row, TModel.FORSIKRINGS_NR) );
          	new SkadeMeldingVindu(vindu, kunde, null, null, null, hyttt);
        }
        catch(NoSuchElementException nsee)
        {
			JOptionPane.showMessageDialog(null, "NoSuchElementException i nySkademeldingBil-metoden", "FEIL", JOptionPane.ERROR_MESSAGE);
		}
	}

	//Metode for og deaktivera forsikring
	public void deaktiverF()
	{
		try
		{
			String melding = "Er du sikker på at du vil deaktivere forsikringen?";
			String tittel = "Validering";

			int row = table21.getSelectedRow();
			if(row == -1)
				return;

			if(!(register.getBilViaNr( (int)tableModel21.getValueAt(row, TModel.FORSIKRINGS_NR) ) == null))
			{
				BilForsikring forsikring = register.getBilViaNr( (int)tableModel21.getValueAt(row, TModel.FORSIKRINGS_NR) );
				if(!forsikring.getAktiv())
					return;
				int svar = JOptionPane.showConfirmDialog(null, melding, tittel, JOptionPane.YES_NO_OPTION);
				if(svar == JOptionPane.YES_OPTION)
				{
					register.avsluttForsikring(forsikring);
					return;
				}
			}
			row = table22.getSelectedRow();
			if(row == -1)
				return;

			else if(!(register.getBåtViaNr( (int)tableModel22.getValueAt(row, TModel.FORSIKRINGS_NR) ) == null))
			{
				BåtForsikring forsikring = register.getBåtViaNr( (int)tableModel22.getValueAt(row, TModel.FORSIKRINGS_NR) );
				if(!forsikring.getAktiv())
					return;
				int svar = JOptionPane.showConfirmDialog(null, melding, tittel, JOptionPane.YES_NO_OPTION);
				if(svar == JOptionPane.YES_OPTION)
				{
					register.avsluttForsikring(forsikring);
					return;
				}
			}
			row = table23.getSelectedRow();
			if(row == -1)
				return;

			else if(!(register.getHusViaNr( (int)tableModel23.getValueAt(row, TModel.FORSIKRINGS_NR) ) == null))
			{
				HusForsikring forsikring = register.getHusViaNr( (int)tableModel23.getValueAt(row, TModel.FORSIKRINGS_NR) );
				if(!forsikring.getAktiv())
					return;
				int svar = JOptionPane.showConfirmDialog(null, melding, tittel, JOptionPane.YES_NO_OPTION);
				if(svar == JOptionPane.YES_OPTION)
				{
					register.avsluttForsikring(forsikring);
					return;
				}
			}
			row = table24.getSelectedRow();
			if(row == -1)
				return;

			else if(!(register.getHytteViaNr( (int)tableModel24.getValueAt(row, TModel.FORSIKRINGS_NR) ) == null))
			{
				HytteForsikring forsikring = register.getHytteViaNr( (int)tableModel24.getValueAt(row, TModel.FORSIKRINGS_NR) );
				if(!forsikring.getAktiv())
					return;
				int svar = JOptionPane.showConfirmDialog(null, melding, tittel, JOptionPane.YES_NO_OPTION);
				if(svar == JOptionPane.YES_OPTION)
				{
					register.avsluttForsikring(forsikring);
					return;
				}
			}

		}
		catch(NoSuchElementException nsee)
		{
			JOptionPane.showMessageDialog(null, "NoSuchElementException i deaktiverBruker-metoden", "FEIL", JOptionPane.ERROR_MESSAGE);
		}
	}

	//LytterKlasse for alla knappar
	private class Lytterklasse implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource() == regForsikring)
				nyForsikring();
			else if(e.getSource() == regSkademelding)
			{
				if(tabbedPane2.getSelectedIndex() == 0)
					nySkademeldingBil();
				else if(tabbedPane2.getSelectedIndex() == 1)
					nySkademeldingBåt();
				else if(tabbedPane2.getSelectedIndex() == 2)
					nySkademeldingHus();
				else if(tabbedPane2.getSelectedIndex() == 3)
					nySkademeldingHytte();
			}
			else if(e.getSource() == deaktiverF)
				deaktiverF();
			/*else if(e.getSource() == visSkademelding)
			{
				if(tabbedPane2.getSelectedIndex() == 0)
					seSkademeldingBil();
				else if(tabbedPane2.getSelectedIndex() == 1)
					seSkademeldingBåt();
				else if(tabbedPane2.getSelectedIndex() == 2)
					seSkademeldingHus();
				else if(tabbedPane2.getSelectedIndex() == 3)
					seSkademeldingHytte();
			}*/
		}
	}
}

