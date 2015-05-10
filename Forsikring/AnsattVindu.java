import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

public class AnsattVindu extends JPanel
{
	private Register register;
	private Huvudvindu window;
	private Lytterklasse lytter;
	private RadioLytter radiolytter;
	private ChangeLytter changeLytter;
	private final int DATA_FIELD_LENGTH = 20;
	public static final int SØK_KUNDE = 1, SØK_BIL = 21, SØK_BÅT = 22, SØK_HUS = 23, SØK_HYTTE = 24;
	private int søkFor;
	private JTextField kundNr, kundPersonnr, kundNavn, kundTelefon, kundAdresse, kundPostnr, kundPostby, typeForsikring;
	private JTextArea informationTop, visForsikringInfo;
	private JButton søkKnapp, oppdaterKnapp, statKnapp, visForsikringKnapp, skjulForsikringKnapp, deletaForsikringKnapp;
	private JLabel søkEtterLabel, kundNrLabel, kundPersonnrLabel, kundNavnLabel, kundTelefonLabel, kundAdresseLabel, kundPostnrLabel, kundPostbyLabel;
	private JPanel visForsikring, visForsikringFlow, searchGrid, grid, katGrid, border, flow;
	private TModel tableModel, tableModel21, tableModel22, tableModel23, tableModel24, tableModel3;
	private ButtonGroup kategoriKnapper;
	private JTable table, table21, table22, table23, table24, table3;
	private JTabbedPane tabbedPane, tabbedPane2, tabbedPane3;
	private JRadioButton bilKat, båtKat, husKat, hytKat;

	public AnsattVindu(Huvudvindu win) //    , Ansatt ansatt
	{
		/*
		Denne klassen må kunne se 3 faner:
		1. Kunder (og åpne nytt vindu med mer info)
		2. Forsikringer (og åpne vindu med mer info)
		3. Skademeldinger (og åpne vindu med mer info)
		*/
		window = win;
		register = window.getRegister();
		lytter = new Lytterklasse();
		radiolytter = new RadioLytter();
		changeLytter = new ChangeLytter();

		//avkrysningsbokser
		kategoriKnapper = new ButtonGroup();
		bilKat = new JRadioButton("Bil", true);
		båtKat = new JRadioButton("Båt", false);
		husKat = new JRadioButton("Hus", false);
		hytKat = new JRadioButton("Hytte", false);
		bilKat.addItemListener(radiolytter);
		båtKat.addItemListener(radiolytter);
		husKat.addItemListener(radiolytter);
		hytKat.addItemListener(radiolytter);
		kategoriKnapper.add(bilKat);
		kategoriKnapper.add(båtKat);
		kategoriKnapper.add(husKat);
		kategoriKnapper.add(hytKat);

		//paneler & layoutmanagere
		setLayout(new BorderLayout());

		visForsikring = new JPanel(new BorderLayout());
		visForsikringFlow = new JPanel( new FlowLayout());


		border = new JPanel(new BorderLayout());
		grid = new JPanel(new GridLayout(2,1));
		katGrid = new JPanel(new GridLayout(1,4));
		GridLayout gridlayout = new GridLayout(17,1);
		gridlayout.setVgap(10);
		searchGrid = new JPanel(gridlayout);
		flow = new JPanel(new FlowLayout());

		//infoormations felt
		informationTop = new JTextArea("Velkommen tilbake");

		informationTop.setLineWrap(true);
		informationTop.setWrapStyleWord(true);
		visForsikringInfo = new JTextArea();
		visForsikringInfo.setEditable(false);
		visForsikringInfo.setVisible(false);
		informationTop.setEditable(false);
		//felt for kunde
		kundNr = new JTextField(DATA_FIELD_LENGTH);
		kundPersonnr = new JTextField(DATA_FIELD_LENGTH);
		kundNavn = new JTextField(DATA_FIELD_LENGTH);
		kundTelefon = new JTextField(DATA_FIELD_LENGTH);
		kundAdresse = new JTextField(DATA_FIELD_LENGTH);
		kundPostnr = new JTextField(DATA_FIELD_LENGTH);
		kundPostby = new JTextField(DATA_FIELD_LENGTH);

		søkEtterLabel = new JLabel("Søk etter:");

		kundNrLabel = new JLabel("Kundenummer:");
		kundPersonnrLabel = new JLabel("Personnummer:");
		kundNavnLabel = new JLabel("Navn:");
		kundTelefonLabel = new JLabel("Telefonnummer:");
		kundAdresseLabel = new JLabel("Adresse:");
		kundPostnrLabel = new JLabel("Postnr:");
		kundPostbyLabel = new JLabel("Poststed:");
		søkKnapp = new JButton("Søk");
		oppdaterKnapp = new JButton("Oppdater");
		statKnapp = new JButton("Statistikk");

		visForsikringKnapp = new JButton("Åpne forsikring");
		skjulForsikringKnapp = new JButton("Lukk forsikring");
		skjulForsikringKnapp.setVisible(false);
		deletaForsikringKnapp = new JButton("Sletta forsikring");

		søkKnapp.addActionListener(lytter);
		oppdaterKnapp.addActionListener(lytter);
		statKnapp.addActionListener(lytter);
		kundNr.addActionListener(lytter);
		kundPersonnr.addActionListener(lytter);
		kundNavn.addActionListener(lytter);
		kundTelefon.addActionListener(lytter);
		kundAdresse.addActionListener(lytter);
		kundPostnr.addActionListener(lytter);
		kundPostby.addActionListener(lytter);
		visForsikringKnapp.addActionListener(lytter);
		skjulForsikringKnapp.addActionListener(lytter);
		deletaForsikringKnapp.addActionListener(lytter);

		grid.add(oppdaterKnapp);
		searchGrid.add(kundNrLabel);
		searchGrid.add(kundNr);
		searchGrid.add(kundPersonnrLabel);
		searchGrid.add(kundPersonnr);
		searchGrid.add(kundNavnLabel);
		searchGrid.add(kundNavn);
		searchGrid.add(kundTelefonLabel);
		searchGrid.add(kundTelefon);
		searchGrid.add(kundAdresseLabel);
		searchGrid.add(kundAdresse);
		searchGrid.add(kundPostnrLabel);
		searchGrid.add(kundPostnr);
		searchGrid.add(kundPostbyLabel);
		searchGrid.add(kundPostby);
		searchGrid.add(søkKnapp);
		searchGrid.add(statKnapp);
		visForsikringFlow.add(visForsikringKnapp);
		visForsikringFlow.add(skjulForsikringKnapp);
		visForsikringFlow.add(deletaForsikringKnapp);
		visForsikring.add(visForsikringFlow, BorderLayout.LINE_END);
		visForsikring.add(visForsikringInfo, BorderLayout.CENTER);
		border.add(grid, BorderLayout.PAGE_START);
		border.add(katGrid, BorderLayout.LINE_START);
		border.add(searchGrid, BorderLayout.PAGE_END);//LINE_START
		flow.add(border);
		add(informationTop, BorderLayout.PAGE_START);
		add(new JScrollPane(flow, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER), BorderLayout.LINE_START);

		tableModel = new TModel(register.getKunder());
		table = new JTable(tableModel);
		//legg til en fane her med forsikringer
		tableModel21 = new TModel(register.getBiler());
		table21 = new JTable(tableModel21);
		tableModel22 = new TModel(register.getBåter());
		table22 = new JTable(tableModel22);
		tableModel23 = new TModel(register.getHus());
		table23 = new JTable(tableModel23);
		tableModel24 = new TModel(register.getHytter());
		table24 = new JTable(tableModel24);
		//tableModel3 = new TModel(register.getSkader());
		//table3 = new JTable(tableModel3);

		tabbedPane2 = new JTabbedPane();
		tabbedPane2.addTab("Bil", null, (new JScrollPane(table21)), "Liste over biler");
		tabbedPane2.setMnemonicAt(0, KeyEvent.VK_1);
		tabbedPane2.addTab("Båt", null, (new JScrollPane(table22)), "Liste over båter");
		tabbedPane2.setMnemonicAt(1, KeyEvent.VK_2);
		tabbedPane2.addTab("Hus", null, (new JScrollPane(table23)), "Liste over hus");
		tabbedPane2.setMnemonicAt(2, KeyEvent.VK_3);
		tabbedPane2.addTab("Hytte", null, (new JScrollPane(table24)), "Liste over hytter");
		tabbedPane2.setMnemonicAt(3, KeyEvent.VK_4);
		tabbedPane2.addChangeListener(changeLytter);


		tabbedPane = new JTabbedPane();
		tabbedPane.addTab("Kunder", null, (new JScrollPane(table)), "Liste over kunder");
		tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
		tabbedPane.addTab("Forsikringer", null, (new JScrollPane(tabbedPane2)), "Liste over forsikringer");
		tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
		//tabbedPane.addTab("Skademeldinger", null, (new JScrollPane(table3)), "Liste over kunder");
		//tabbedPane.setMnemonicAt(0, KeyEvent.VK_3);
		tabbedPane.addChangeListener(changeLytter);




		add(new JScrollPane(tabbedPane, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER), BorderLayout.CENTER);






		//submitKnapp = new JButton("Submit");
		//bilForsikring = new JButton("Bil");
		//båtForsikring = new JButton("Hus");
		//fritidForsikring = new JButton("Fritid");



	}

	public void søk()
	{
		søkKunde();
	}

	public void statistikk()
	{
		JOptionPane.showMessageDialog(null, "Legg inn statistikk her");
	}

	public KundeReg søkKunde()
	{
		KundeReg res = register.getKunder();

		String kn = kundNavn.getText();
		String pn = kundPersonnr.getText();
		String tlf = kundTelefon.getText();
		String adr = kundAdresse.getText();
		String pnr = kundPostnr.getText();
		String pb = kundPostby.getText();

		if(!kn.isEmpty() )
			res = register.getKundeViaNavn(res, kn);
		if(!pn.isEmpty() )
			res = register.getKundeViaNummer(res, pn);
		if(!tlf.isEmpty() )
			res = register.getKundeViaTelefon(res, tlf);
		if(!adr.isEmpty() )
			res = register.getKundeViaAdresse(res, adr);
		if(!pnr.isEmpty() )
			res = register.getKundeViaPostnr(res, pnr);
		if(!pb.isEmpty() )
			res = register.getKundeViaBy(res, pb);

		tableModel = new TModel(res);
		table.setModel(tableModel);
		tableModel.setTableCellEditor(table);
		return res;
	}

	/*public SkadeReg søkSkader()
	{
		SkadeReg res = register.getSkader();

		String kn = kundNavn.getText();
		String pn = kundPersonnr.getText();
		String tlf = kundTelefon.getText();
		String adr = kundAdresse.getText();
		String pnr = kundPostnr.getText();
		String pb = kundPostby.getText();

		if(!kn.isEmpty() )
			res = register.getKundeViaNavn(res, kn);
		if(!pn.isEmpty() )
			res = register.getKundeViaNummer(res, pn);
		if(!tlf.isEmpty() )
			res = register.getKundeViaTelefon(res, tlf);
		if(!adr.isEmpty() )
			res = register.getKundeViaAdresse(res, adr);
		if(!pnr.isEmpty() )
			res = register.getKundeViaPostnr(res, pnr);
		if(!pb.isEmpty() )
			res = register.getKundeViaBy(res, pb);

		tableModel = new TModel(res);
		table.setModel(tableModel);
		tableModel.setTableCellEditor(table);
		return res;
	}*/

	/*public BilForsikringReg søkBil()
	{
		BilForsikringsReg res = register.getBiler();

		String fnr = forsNr.getText();
		Calendar start = inngått.getText();
		Calendar slutt = utgått.getText();
		String knr = kundNr.getText();
		int fbl = fbeløp.getText();
		String eir = eier.getText():
		String rnr = regnr.getText();
		String tp = type.getText();
		String md = modell.getText();
		int rår = bilregår.getText();
		int åkl = kmlengde.getText();

		if(!fnr.isEmpty() )
			res = register.getBilViaForsNr(res, fnr);
		if(!knr.isEmpty() )
			res = register.getBilViaKundeNr(res, knr);
		if(!fbl.isEmpty() )
			res = register.getBilViaBeløp(res, fbl);
		if(!eir.isEmpty() )
			res = register.getBilViaEier(res, eir);
		if(!rnr.isEmpty() )
			res = register.getBilViaRegNr(res, rnr);
		if(!tp.isEmpty() )
			res = register.getBilViaType(res, tp);
		if(!md.isEmpty() )
			res = register.getBilViaModell(res, md);
		if(!rår.isEmpty() )
			rår = register.getBilViaRegÅr(res, rår);
		if(!åkl.isEmpty() )
			åkl = register.getBilViaKM(res, åkl);

		tableModel21 = new TModel(res);
		table2.setModel(tableModel21);
		tableModel21.setTableCellEditor(table2);
		return res;
	}*/

	public void kundePanel()
	{
		grid.removeAll();
		grid.add(oppdaterKnapp);
		grid.revalidate();
		grid.repaint();
	}

	public void forsikringPanel()
	{
		//grid.removeAll();
		//grid.add(oppdaterKnapp);
		grid.add(søkEtterLabel);
		katGrid.add(bilKat);
		katGrid.add(båtKat);
		katGrid.add(husKat);
		katGrid.add(hytKat);
		grid.revalidate();
		grid.repaint();
		katGrid.revalidate();
		katGrid.repaint();
	}

	private class RadioLytter implements ItemListener
	{
		public void itemStateChanged(ItemEvent e)
		{
			if(bilKat.isSelected())
			{
				søkFor = SØK_BIL;
				/*
				tabbedPane.setSelectedIndex(0);
				searchGrid.removeAll();
				searchGrid.add(kundNrLabel);
				searchGrid.add(kundNr);
				searchGrid.add(kundPersonnrLabel);
				searchGrid.add(kundPersonnr);
				searchGrid.add(kundNavnLabel);
				searchGrid.add(kundNavn);
				searchGrid.add(kundTelefonLabel);
				searchGrid.add(kundTelefon);
				searchGrid.add(kundAdresseLabel);
				searchGrid.add(kundAdresse);
				searchGrid.add(kundPostnrLabel);
				searchGrid.add(kundPostnr);
				searchGrid.add(kundPostbyLabel);
				searchGrid.add(kundPostby);
				searchGrid.add(søkKnapp);
				searchGrid.add(statKnapp);
				searchGrid.revalidate();
				searchGrid.repaint();
				søkBil();*/
			}
			else if(båtKat.isSelected())
			{
				søkFor = SØK_BÅT;
				/*
				tabbedPane.setSelectedIndex(0);
				searchGrid.removeAll();
				searchGrid.add(kundNrLabel);
				searchGrid.add(kundNr);
				searchGrid.add(kundPersonnrLabel);
				searchGrid.add(kundPersonnr);
				searchGrid.add(kundNavnLabel);
				searchGrid.add(kundNavn);
				searchGrid.add(kundTelefonLabel);
				searchGrid.add(kundTelefon);
				searchGrid.add(kundAdresseLabel);
				searchGrid.add(kundAdresse);
				searchGrid.add(kundPostnrLabel);
				searchGrid.add(kundPostnr);
				searchGrid.add(kundPostbyLabel);
				searchGrid.add(kundPostby);
				searchGrid.add(søkKnapp);
				searchGrid.add(statKnapp);
				searchGrid.revalidate();
				searchGrid.repaint();
				søkBåt();*/
			}
			if(husKat.isSelected())
			{
				søkFor = SØK_HUS;
				/*
				tabbedPane.setSelectedIndex(0);
				searchGrid.removeAll();
				searchGrid.add(kundNrLabel);
				searchGrid.add(kundNr);
				searchGrid.add(kundPersonnrLabel);
				searchGrid.add(kundPersonnr);
				searchGrid.add(kundNavnLabel);
				searchGrid.add(kundNavn);
				searchGrid.add(kundTelefonLabel);
				searchGrid.add(kundTelefon);
				searchGrid.add(kundAdresseLabel);
				searchGrid.add(kundAdresse);
				searchGrid.add(kundPostnrLabel);
				searchGrid.add(kundPostnr);
				searchGrid.add(kundPostbyLabel);
				searchGrid.add(kundPostby);
				searchGrid.add(søkKnapp);
				searchGrid.add(statKnapp);
				searchGrid.revalidate();
				searchGrid.repaint();
				søkHus();*/
			}
			if(hytKat.isSelected())
			{
				søkFor = SØK_HYTTE;
				/*
				tabbedPane.setSelectedIndex(0);
				searchGrid.removeAll();
				searchGrid.add(kundNrLabel);
				searchGrid.add(kundNr);
				searchGrid.add(kundPersonnrLabel);
				searchGrid.add(kundPersonnr);
				searchGrid.add(kundNavnLabel);
				searchGrid.add(kundNavn);
				searchGrid.add(kundTelefonLabel);
				searchGrid.add(kundTelefon);
				searchGrid.add(kundAdresseLabel);
				searchGrid.add(kundAdresse);
				searchGrid.add(kundPostnrLabel);
				searchGrid.add(kundPostnr);
				searchGrid.add(kundPostbyLabel);
				searchGrid.add(kundPostby);
				searchGrid.add(søkKnapp);
				searchGrid.add(statKnapp);
				searchGrid.revalidate();
				searchGrid.repaint();
				søkHytte();*/
			}
		}
	}

	private class ChangeLytter implements ChangeListener
	{
		public void stateChanged(ChangeEvent e)
		{
			if(tabbedPane.getSelectedIndex() == 0)
				kundePanel();
			else if(tabbedPane.getSelectedIndex() == 1)
				forsikringPanel();
			else if(tabbedPane2.getSelectedIndex() == 0)
				bilKat.setSelected(true);
			else if(tabbedPane2.getSelectedIndex() == 1)
				båtKat.setSelected(true);
			else if(tabbedPane2.getSelectedIndex() == 2)
				husKat.setSelected(true);
			else if(tabbedPane2.getSelectedIndex() == 3)
				hytKat.setSelected(true);
		}
	}

	private class Lytterklasse implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if( e.getSource() == søkKnapp || e.getSource() == kundNavn || e.getSource() == kundTelefon
			|| e.getSource() == kundAdresse || e.getSource() == kundPersonnr || e.getSource() == kundPostnr
			|| e.getSource() == kundPostby )
				søk();
			else if(e.getSource() == oppdaterKnapp)
				søk();
			else if(e.getSource() == statKnapp)
				statistikk();
		}
	}
}
