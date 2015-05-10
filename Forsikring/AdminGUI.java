/*Skrevet av Rebwar Eliassi, s183736. Sist endret 16.04.2015
Klassen definerer brukergrensesnittet for administrator, klassen utvider JPanel med felter og knapper for søkefunksjoner
brukeren kan bruke til å søke seg frem til øsnket data. Panelet har også funskjoner for å endre data der dette er tillat
*/
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.*;
import java.awt.*;
import java.awt.event.*;

public class AdminGUI extends JPanel
{
	public static final int SØK_FORSIKRING = 1, SØK_KUNDE = 2, SØK_ANSATT = 3, DATAFELT_LENGDE = 20, PERS_NR = 11, POST = 4;
	private int søkFor;
	private Huvudvindu vindu;
	//private AnsattProfil profil;
	private JLabel søkEtterLabel, FornavnLabel, EtternavnLabel, PersNrLabel, ansattNummerLabel, ansattAvdelingLabel, kundeNrLabel, kundeAdresseLabel, kundePostnrLabel, kundePoststedLabel;
	private JTextArea info, visAnsattInfo;
	private JTextField Fornavn, Etternavn, PersNr, ansattNummer, ansattAvdeling, kundeNr, kundeAdresse, kundePostnr, kundePoststed;
	private ButtonGroup gruppeKnapper;
	private JRadioButton ans, kun;
	private JButton søkeKnapp, nyAns, slettAns, lagre, oppdater, visAnsattKnapp, statButton;
	private RadioLytter radioLytter;
	private Lytterklasse lytter;
	private ChangeLytter change;
	private JPanel hasLicChoose, grid, searchGrid, bord, licChoose, flow, visAnsatte, visAnsatteFlow;
	private Register register;
	private TModel tableModel, tableModel2;
	private JTable table, table2;
	private JTabbedPane tabbedPane;


	public AdminGUI(Huvudvindu v)
	{
		vindu = v;
		register = vindu.getRegister();
		lytter = new Lytterklasse();
		radioLytter = new RadioLytter();
		change = new ChangeLytter();

		//avkrysningsboks

		gruppeKnapper = new ButtonGroup();
		ans = new JRadioButton("Ansatte", true);
		kun = new JRadioButton("Kunder", false);
		ans.addItemListener(radioLytter);
		kun.addItemListener(radioLytter);
		gruppeKnapper.add(ans);
		gruppeKnapper.add(kun);

		//Setter layout:
		setLayout(new BorderLayout() );
		//oppretter JPaneler med ulike LayoutManagere
		visAnsatte = new JPanel(new BorderLayout() ); //show prescription var skrevet tidligere her

		grid = new JPanel(new GridLayout(4,1));
		GridLayout gridlayout = new GridLayout( 19, 1);
		gridlayout.setVgap(10);
		searchGrid = new JPanel(gridlayout);
		bord = new JPanel(new BorderLayout() );
		flow = new JPanel(new FlowLayout() );
		visAnsatteFlow = new JPanel(new FlowLayout() ); //show prescription var skrevet tidligere

		info = new JTextArea(""); //Info tekst

		info.setEditable(false);
		info.setLineWrap(true);
		info.setWrapStyleWord(true);
		visAnsattInfo = new JTextArea();
		visAnsattInfo.setEditable(false);
		visAnsattInfo.setVisible(false);

		søkEtterLabel = new JLabel("Søk etter:");

		Fornavn = new JTextField(DATAFELT_LENGDE);
		Etternavn = new JTextField(DATAFELT_LENGDE);
		PersNr = new JTextField(PERS_NR);
		//Søking i ansatte
		ansattNummer = new JTextField(DATAFELT_LENGDE);
		ansattAvdeling = new JTextField(DATAFELT_LENGDE);
		FornavnLabel = new JLabel("Fornavn:");// Ansatts Navn?((legge til tooltip på disse
		EtternavnLabel = new JLabel("Etternavn:");
		PersNrLabel = new JLabel("Personnummer:");
		ansattNummerLabel = new JLabel("Ansattnummer:");
		ansattAvdelingLabel = new JLabel("Avdeling:");

		//Søking i kunder
		kundeNr = new JTextField(DATAFELT_LENGDE);
		kundeAdresse = new JTextField(DATAFELT_LENGDE);
		kundePostnr = new JTextField(POST);
		kundePoststed = new JTextField(DATAFELT_LENGDE);
		kundeNrLabel = new JLabel("Kundenr:");
		kundeAdresseLabel = new JLabel("Adresse:");
		kundePostnrLabel = new JLabel("Postnr:");
		kundePoststedLabel = new JLabel("Poststed:");

		søkeKnapp = new JButton("Søk");
		nyAns = new JButton("Legg til en ny ansatt");
		slettAns = new JButton("Slett ansatt");
		lagre = new JButton("Lagre endringer");
		oppdater = new JButton("Oppdater");
		visAnsattKnapp = new JButton("Vis ansattinfo");
		statButton = new JButton("Vis statistikk");
		//Lyttere:

		søkeKnapp.addActionListener(lytter);
		Fornavn.addActionListener(lytter);
		Etternavn.addActionListener(lytter);
		PersNr.addActionListener(lytter);
		ansattNummer.addActionListener(lytter);
		ansattAvdeling.addActionListener(lytter);
		kundeNr.addActionListener(lytter);
		kundeAdresse.addActionListener(lytter);
		kundePostnr.addActionListener(lytter);
		kundePoststed.addActionListener(lytter);
		nyAns.addActionListener(lytter);
		slettAns.addActionListener(lytter);
		lagre.addActionListener(lytter);
		oppdater.addActionListener(lytter);
		visAnsattKnapp.addActionListener(lytter);
		statButton.addActionListener(lytter);
		//legger elementer til i GUI

		grid.add(oppdater);
		grid.add(søkEtterLabel);
		grid.add(ans);
		grid.add(kun);
		searchGrid.add(ansattNummerLabel);
		searchGrid.add(ansattNummer);
		searchGrid.add(PersNrLabel);
		searchGrid.add(PersNr);
		searchGrid.add(FornavnLabel);
		searchGrid.add(Fornavn);
		searchGrid.add(EtternavnLabel);
		searchGrid.add(Etternavn);
		searchGrid.add(ansattAvdelingLabel);
		searchGrid.add(ansattAvdeling);
		searchGrid.add(søkeKnapp);
		searchGrid.add(nyAns);
		searchGrid.add(slettAns);
		searchGrid.add(statButton);

		bord.add(grid, BorderLayout.PAGE_START);
		bord.add(searchGrid, BorderLayout.LINE_START);
		flow.add(bord);


		add(info, BorderLayout.PAGE_START);
		add(new JScrollPane(flow, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER), BorderLayout.LINE_START);
		//oppretter tabellen for visning og redigering
		tableModel = new TModel(register.getAnsatte());
		table = new JTable(tableModel);


		//Start på tabbed

		tableModel2 = new TModel(register.getKunder());
		table2 = new JTable(tableModel2);

		tabbedPane = new JTabbedPane();
		tabbedPane.addTab("Ansatte", null, (new JScrollPane(table)), "Liste over ansatte");
		tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
		tabbedPane.addTab("Kunder", null, (new JScrollPane(table2)), "Liste over kunder");
		tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
    	tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		tabbedPane.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
		tabbedPane.addChangeListener(change);


		add(new JScrollPane(tabbedPane, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER), BorderLayout.CENTER);  //CENTER
		//add(new JScrollPane(visAnsatte), BorderLayout.PAGE_END);

		//Slutt på tabbed


		//legger til elementer i hovedpanelet

		//add(new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER), BorderLayout.CENTER);
		//add(new JScrollPane(visAnsatte), BorderLayout.PAGE_END);



		MouseListener mouseListener = new MouseAdapter()
		{
			public void mouseClicked(MouseEvent mouseEvent)
			{
		        JTable theTable = (JTable) mouseEvent.getSource();
		        if (mouseEvent.getClickCount() == 2)
		        {
		        	int rad = theTable.getSelectedRow();
		        	if (rad >= 0)
		          	{
						if(theTable.getValueAt(rad, TModel.ANSATT_NR) != null)
						{
							Ansatt anna = register.getAnsattViaAnsattNr( (String)theTable.getValueAt(rad, TModel.ANSATT_NR) );
							AnsattProfil vin = new AnsattProfil(anna);
						}
						else
						{
							Kunde kunne = register.getKundeViaKundeNr( (String)theTable.getValueAt(rad, TModel.KUNDE_NR) );
							KundeProfil vinn = new KundeProfil(kunne);
						}
		          	}
		        }
		      }
		};
		table.addMouseListener(mouseListener);
	}

	public int getSøkFor()
	{
		return søkFor;
	}

	public void søk()
	{
		if(tabbedPane.getSelectedIndex() == 1)
			søkKunde();
		else
			søkAnsatt();
	}

	public AnsattReg søkAnsatt()
	{
		AnsattReg res = register.getAnsatte();

		String afn = Fornavn.getText();
		String aen = Etternavn.getText();
		String apn = PersNr.getText();
		String anr = ansattNummer.getText();
		String avd = ansattAvdeling.getText();

		if(!afn.isEmpty() )
			res = register.getAnsattViaNavn(res, afn);
		if(!aen.isEmpty() )
			res = register.getAnsattViaNavn(res, aen);
		if(!apn.isEmpty() )
			res = register.getAnsattViaNr(res, apn);
		if(!anr.isEmpty() )
			res = register.getAnsattViaAnsattNr(res, anr);
		if(!avd.isEmpty() )
			res = register.getAnsattViaAvdeling(res, avd);

		//res = register.getPatientsByGroup(res, la, lb, lc);

		tableModel = new TModel(res);
		table.setModel(tableModel);
		tableModel.setTableCellEditor(table);
		tabbedPane.setSelectedIndex(0);
		return res;
	}

	public KundeReg søkKunde()
	{
		KundeReg res = register.getKunder();

		String kfn = Fornavn.getText();
		String ken = Etternavn.getText();
		String kpn = PersNr.getText();
		String knr = kundeNr.getText();
		String adr = kundeAdresse.getText();
		String pnr = kundePostnr.getText();
		String pst = kundePoststed.getText();

		if(!kfn.isEmpty() )
			res = register.getKundeViaNavn(res, kfn);
		if(!ken.isEmpty() )
			res = register.getKundeViaNavn(res, ken);
		if(!kpn.isEmpty() )
			res = register.getKundeViaNummer(res, kpn);
		if(!knr.isEmpty() )
			res = register.getKundeViaKundeNr(res, knr);
		if(!adr.isEmpty() )
			res = register.getKundeViaAdresse(res, adr);
		if(!pnr.isEmpty() )
			res = register.getKundeViaPostnr(res, pnr);
		if(!pst.isEmpty() )
			res = register.getKundeViaBy(res, pst);

		//res = register.getPatientsByGroup(res, la, lb, lc);

		tableModel2 = new TModel(res);
		table2.setModel(tableModel2);
		tableModel2.setTableCellEditor(table2);
		tabbedPane.setSelectedIndex(1);
		return res;
	}

	public void nyAnsatt() //
	{
		vindu.swapPanel(new NyAnsattGUI(vindu));
	}

	public void visAnsatt() //<en JOptionPane.showmessagedialog skal poppe opp>
	{
		try
		{
			int row = table.getSelectedRow();
			if(row == -1 )
			{
				JOptionPane.showMessageDialog(null, "Du må velge en ansatt");
				return;
			}
			Ansatt ass = register.getAnsattViaNr( (String)tableModel.getValueAt(row, TModel.PRESCRIPTION_NR) );
			visAnsattInfo.setText(ass.toString());
		}
		catch(NullPointerException npe)
		{
			JOptionPane.showMessageDialog(null, "Feil", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		visAnsattInfo.setVisible(false);
		validate();
	}

	public void visStatistikk()
	{
		JOptionPane.showMessageDialog(null, "Her har du statistikken din");
	}

	public void slettBruker()
	{
		try
		{
			int row = table.getSelectedRow();
			if(row == -1)
				return;
			String melding = "Er du sikker på at du vil slette brukeren?";
			String tittel = "Validering";

    		int svar = JOptionPane.showConfirmDialog(null, melding, tittel, JOptionPane.YES_NO_OPTION);
        	if (svar == JOptionPane.YES_OPTION)
        	{
          		Ansatt anna = register.getAnsattViaNr( (String)tableModel.getValueAt(row, TModel.PERSON_NR) );
				register.slettAnsatt(anna);
				return;
			}
        }
        catch(NoSuchElementException nsee)
        {
			JOptionPane.showMessageDialog(null, "NoSuchElementException i slettBruker-metoden", "FEIL", JOptionPane.ERROR_MESSAGE);
		}
	}

	private class RadioLytter implements ItemListener
	{
		public void itemStateChanged(ItemEvent e)
		{
			if(ans.isSelected())
			{
				søkFor = SØK_ANSATT;
				tabbedPane.setSelectedIndex(0);
				searchGrid.removeAll();
				searchGrid.add(ansattNummerLabel);
				searchGrid.add(ansattNummer);
				searchGrid.add(PersNrLabel);
				searchGrid.add(PersNr);
				searchGrid.add(FornavnLabel);
				searchGrid.add(Fornavn);
				searchGrid.add(EtternavnLabel);
				searchGrid.add(Etternavn);
				searchGrid.add(ansattAvdelingLabel);
				searchGrid.add(ansattAvdeling);
				searchGrid.add(søkeKnapp);
				searchGrid.add(nyAns);
				searchGrid.add(slettAns);
				searchGrid.add(statButton);
				searchGrid.revalidate();
				searchGrid.repaint();
				søkAnsatt();
			}
			else if(kun.isSelected())
			{
				søkFor = SØK_KUNDE;
				tabbedPane.setSelectedIndex(1);
				searchGrid.removeAll();
				searchGrid.add(kundeNrLabel);
				searchGrid.add(kundeNr);
				searchGrid.add(PersNrLabel);
				searchGrid.add(PersNr);
				searchGrid.add(FornavnLabel);
				searchGrid.add(Fornavn);
				searchGrid.add(EtternavnLabel);
				searchGrid.add(Etternavn);
				searchGrid.add(kundeAdresseLabel);
				searchGrid.add(kundeAdresse);
				searchGrid.add(kundePostnrLabel);
				searchGrid.add(kundePostnr);
				searchGrid.add(kundePoststedLabel);
				searchGrid.add(kundePoststed);
				searchGrid.add(søkeKnapp);
				searchGrid.add(statButton);
				searchGrid.revalidate();
				searchGrid.repaint();
				søkKunde();
			}
		}
	}

	private class ChangeLytter implements ChangeListener
	{
		public void stateChanged(ChangeEvent e)
		{
			if(tabbedPane.getSelectedIndex() == 0)
				ans.setSelected(true);
			else if(tabbedPane.getSelectedIndex() == 1)
				kun.setSelected(true);
		}
	}

	private class Lytterklasse implements ActionListener//knappelytter
	{
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource() == søkeKnapp || e.getSource() == Fornavn || e.getSource() == Etternavn
				|| e.getSource() == PersNr)
			{
				if(tabbedPane.getSelectedIndex() == 0)
					søkAnsatt();
				else
					søkKunde();
			}
			else if(e.getSource() == ansattNummer || e.getSource() == ansattAvdeling)
				søkAnsatt();
			else if(e.getSource() == kundeNr || e.getSource() == kundeAdresse || e.getSource() == kundePostnr
				|| e.getSource() == kundePoststed)
				søkKunde();
			else if(e.getSource() == oppdater)
				søk();
			else if(e.getSource() == nyAns)
				nyAnsatt();
			else if(e.getSource() == slettAns)
				slettBruker();
			else if(e.getSource() == statButton)
				visStatistikk();
		}
	}
}
