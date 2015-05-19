import javax.swing.*;
import javax.swing.event.*;

import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.util.*;

public class AnsattVindu extends JPanel
{
	private Register register;
	private Ansatt ansatt;
	private Huvudvindu window;
	private Lytterklasse lytter;
	private RadioLytter radiolytter;
	private ChangeLytter changeLytter;
	private final int DATA_FIELD_LENGTH = 20;
	public static final int SØK_KUNDE = 1, SØK_BIL = 21, SØK_BÅT = 22, SØK_HUS = 23, SØK_HYTTE = 24, SØK_SKADE = 3;
	private int søkFor;
	private String innlogget;
	private JTextField kundNr, kundPersonnr, kundNavn, kundTelefon, kundAdresse, kundPostnr, kundPostby, typeForsikring,
				bilforsikringsnr, eier, forsikringBeløp, bilregNr, bilType, bilModell, bilRegÅr, bilKM,
				båtforsikringsnr, båtregNr, båtType, båtModell, båtLengde, båtÅr, båtMotor, båtHK,
				husforsikringsnr, totalbeløp, adresse, byggeÅr, husType, husMateriale, standard, KM, byggBeløp, innboBeløp,
				hytteforsikringsnr, hytteType, hytteMateriale, skademeldingsNr, skadeType, skadeAdresse, utbetaltBeløp, skadeStatus;
	private JFormattedTextField skadeDato, skadeFørDato, skadeEtterDato;
	private JTextArea informationTop, visForsikringInfo;
	private JButton søkKnapp, oppdaterKnapp, statKnapp, visForsikringKnapp, deletaForsikringKnapp, lagreKnapp;
	private JLabel søkEtterLabel, kundNrLabel, kundPersonnrLabel, kundNavnLabel, kundTelefonLabel, kundAdresseLabel, kundPostnrLabel, kundPostbyLabel,
				bilforsikringsnrLabel, eierLabel, forsikringBeløpLabel, bilregNrLabel, bilTypeLabel, bilModellLabel, bilRegÅrLabel, bilKMLabel,
				båtforsikringsnrLabel, båtregNrLabel, båtTypeLabel, båtModellLabel, båtLengdeLabel, båtÅrLabel, båtMotorLabel, båtHKLabel,
				husforsikringsnrLabel, totalbeløpLabel, adresseLabel, byggeÅrLabel, husTypeLabel, husMaterialeLabel, standardLabel, KMLabel, byggBeløpLabel, innboBeløpLabel,
				hytteforsikringsnrLabel, hytteTypeLabel, hytteMaterialeLabel, skademeldingsNrLabel, skadeDatoLabel, skadeFørDatoLabel, skadeEtterDatoLabel,
				skadeTypeLabel, skadeAdresseLabel, utbetaltBeløpLabel, skadeStatusLabel;
	private JPanel visForsikring, visForsikringFlow, searchGrid, grid, katGrid, border, flow;
	private TModel tableModel, tableModel21, tableModel22, tableModel23, tableModel24, tableModel3;
	private ButtonGroup kategoriKnapper;
	private JTable table, table21, table22, table23, table24, table3;
	private JTabbedPane tabbedPane, tabbedPane2, tabbedPane3;
	private JRadioButton bilKat, båtKat, husKat, hytKat;

	public AnsattVindu(Huvudvindu win, Ansatt anna) //    , Ansatt ansatt
	{
		/*
		Fiks det med forandre søkefelter etter tabber
		Denne klassen må kunne se 3 faner:
		1. Kunder (og åpne nytt vindu med mer info)!! X
		2. Forsikringer (og åpne vindu med mer info)!!
		3. Skademeldinger (og åpne vindu med mer info)!!
		*/
		window = win;
		ansatt = anna;
		register = window.getRegister();
		lytter = new Lytterklasse();
		radiolytter = new RadioLytter();
		changeLytter = new ChangeLytter();

		//avkrysningsbokser
		kategoriKnapper = new ButtonGroup();
		bilKat = new JRadioButton("Bil", false);
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
		GridLayout gridlayout = new GridLayout(22,1);
		gridlayout.setVgap(5);
		searchGrid = new JPanel(gridlayout);
		flow = new JPanel(new FlowLayout());

		//infoormations felt
		innlogget = ansatt.getFornavn() + " " + ansatt.getEtternavn();
		informationTop = new JTextArea("Velkommen " + innlogget);

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

		visForsikringKnapp = new JButton("Se kundens forsikringer");
		deletaForsikringKnapp = new JButton("Opphev forsikring");
		//seKundesForsikringer = new JButton();

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
		deletaForsikringKnapp.addActionListener(lytter);

		//felt for bil
		bilforsikringsnr = new JTextField(DATA_FIELD_LENGTH);
		eier = new JTextField(DATA_FIELD_LENGTH);
		forsikringBeløp = new JTextField(DATA_FIELD_LENGTH);
		bilregNr = new JTextField(DATA_FIELD_LENGTH);
		bilType = new JTextField(DATA_FIELD_LENGTH);
		bilModell = new JTextField(DATA_FIELD_LENGTH);
		bilRegÅr = new JTextField(DATA_FIELD_LENGTH);
		bilKM = new JTextField(DATA_FIELD_LENGTH);

		bilforsikringsnrLabel = new JLabel("Forsikringsnr:");
		eierLabel = new JLabel("Eier:");
		forsikringBeløpLabel = new JLabel("Forsikringsbeløp:");
		bilregNrLabel = new JLabel("Registreringsnr:");
		bilTypeLabel = new JLabel("Type:");
		bilModellLabel = new JLabel("Modell:");
		bilRegÅrLabel = new JLabel("Registreringsår:");
		bilKMLabel = new JLabel("Kjørelengde:");
		
		lagreKnapp = new JButton("Updatera bonus");

		bilforsikringsnr.addActionListener(lytter);
		eier.addActionListener(lytter);
		forsikringBeløp.addActionListener(lytter);
		bilregNr.addActionListener(lytter);
		bilType.addActionListener(lytter);
		bilModell.addActionListener(lytter);
		bilRegÅr.addActionListener(lytter);
		bilKM.addActionListener(lytter);
		lagreKnapp.addActionListener(lytter);

		//felt for båt
		båtforsikringsnr = new JTextField(DATA_FIELD_LENGTH);
		båtregNr = new JTextField(DATA_FIELD_LENGTH);
		båtType = new JTextField(DATA_FIELD_LENGTH);
		båtModell = new JTextField(DATA_FIELD_LENGTH);
		båtLengde = new JTextField(DATA_FIELD_LENGTH);
		båtÅr = new JTextField(DATA_FIELD_LENGTH);
		båtMotor = new JTextField(DATA_FIELD_LENGTH);
		båtHK = new JTextField(DATA_FIELD_LENGTH);

		båtforsikringsnrLabel = new JLabel("Forsikringsnr:");
		båtregNrLabel = new JLabel("Registreringsnr:");
		båtTypeLabel = new JLabel("Type:");
		båtModellLabel = new JLabel("Modell:");
		båtLengdeLabel = new JLabel("Lengde(fot):");
		båtÅrLabel = new JLabel("Årsmodell:");
		båtMotorLabel = new JLabel("Motortype:");
		båtHKLabel = new JLabel("Motorstyrke(HK):");

		båtforsikringsnr.addActionListener(lytter);
		båtregNr.addActionListener(lytter);
		båtType.addActionListener(lytter);
		båtModell.addActionListener(lytter);
		båtLengde.addActionListener(lytter);
		båtÅr.addActionListener(lytter);
		båtMotor.addActionListener(lytter);
		båtHK.addActionListener(lytter);

		//felt for hus
		husforsikringsnr = new JTextField(DATA_FIELD_LENGTH);
		totalbeløp = new JTextField(DATA_FIELD_LENGTH);
		adresse = new JTextField(DATA_FIELD_LENGTH);
		byggeÅr = new JTextField(DATA_FIELD_LENGTH);
		husType = new JTextField(DATA_FIELD_LENGTH);
		husMateriale = new JTextField(DATA_FIELD_LENGTH);
		standard = new JTextField(DATA_FIELD_LENGTH);
		KM = new JTextField(DATA_FIELD_LENGTH);
		byggBeløp = new JTextField(DATA_FIELD_LENGTH);
		innboBeløp = new JTextField(DATA_FIELD_LENGTH);

		husforsikringsnrLabel = new JLabel("Forsikringsnr:");
		totalbeløpLabel = new JLabel("Forsikringsbeløp(total):");
		adresseLabel = new JLabel("Adresse:");
		byggeÅrLabel = new JLabel("Byggeår:");
		husTypeLabel = new JLabel("Boligtype:");
		husMaterialeLabel = new JLabel("Byggemateriale");
		standardLabel = new JLabel("Standard:");
		KMLabel = new JLabel("Kvadratmeter:");
		byggBeløpLabel = new JLabel("Forsikringsbeløp(bygg):");
		innboBeløpLabel = new JLabel("Forsikringsbeløp(innbo):");

		husforsikringsnr.addActionListener(lytter);
		totalbeløp.addActionListener(lytter);
		adresse.addActionListener(lytter);
		byggeÅr.addActionListener(lytter);
		husType.addActionListener(lytter);
		husMateriale.addActionListener(lytter);
		standard.addActionListener(lytter);
		KM.addActionListener(lytter);
		byggBeløp.addActionListener(lytter);
		innboBeløp.addActionListener(lytter);

		//felt for hytte
		hytteforsikringsnr = new JTextField(DATA_FIELD_LENGTH);
		hytteType = new JTextField(DATA_FIELD_LENGTH);
		hytteMateriale = new JTextField(DATA_FIELD_LENGTH);
		hytteforsikringsnrLabel = new JLabel("Forsikringsnr:");
		hytteTypeLabel = new JLabel("Boligtype:");
		hytteMaterialeLabel = new JLabel("Byggemateriale:");
		hytteforsikringsnr.addActionListener(lytter);
		hytteType.addActionListener(lytter);
		hytteMateriale.addActionListener(lytter);


		//felt for skademeldinger

		JFormattedTextField dateField = new JFormattedTextField(new SimpleDateFormat("dd/MM/yyyy"));
		dateField.setValue(new java.util.Date());


		skademeldingsNr = new JTextField(DATA_FIELD_LENGTH);
		skadeDato = new JFormattedTextField(new SimpleDateFormat("dd/MM/yyyy"));
		skadeFørDato = new JFormattedTextField(new SimpleDateFormat("dd/MM/yyyy"));
		skadeEtterDato = new JFormattedTextField(new SimpleDateFormat("dd/MM/yyyy"));
		skadeType = new JTextField(DATA_FIELD_LENGTH);
		skadeAdresse = new JTextField(DATA_FIELD_LENGTH);
		utbetaltBeløp = new JTextField(DATA_FIELD_LENGTH);
		skadeStatus = new JTextField(DATA_FIELD_LENGTH);
		skademeldingsNrLabel = new JLabel("Skademeldingsnr:");
		skadeDatoLabel = new JLabel("Dato:");
		skadeFørDatoLabel = new JLabel("Før dato:");
		skadeEtterDatoLabel = new JLabel("Etter dato:");
		skadeTypeLabel = new JLabel("Type skade:");
		skadeAdresseLabel = new JLabel("Skadested:");
		utbetaltBeløpLabel = new JLabel("Erstatningsbeløp:");
		skadeStatusLabel = new JLabel("Skademelding status:");

		skademeldingsNr.addActionListener(lytter);
		skadeDato.addActionListener(lytter);
		skadeFørDato.addActionListener(lytter);
		skadeEtterDato.addActionListener(lytter);
		skadeType.addActionListener(lytter);
		utbetaltBeløp.addActionListener(lytter);
		skadeStatus.addActionListener(lytter);


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
		//visForsikringFlow.add(skjulForsikringKnapp);
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
		tableModel21 = new TModel(register.getBiler());	//HER
		table21 = new JTable(tableModel21);
		tableModel22 = new TModel(register.getBåter());
		table22 = new JTable(tableModel22);
		tableModel23 = new TModel(register.getHus());
		table23 = new JTable(tableModel23);
		tableModel24 = new TModel(register.getHytter());
		table24 = new JTable(tableModel24);
		tableModel3 = new TModel(register.getSkademeldinger());
		table3 = new JTable(tableModel3);

		tabbedPane2 = new JTabbedPane();
		tabbedPane2.addTab("Bil", null, (new JScrollPane(table21, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED)), "Liste over biler");
		tabbedPane2.setMnemonicAt(0, KeyEvent.VK_1);
		tabbedPane2.addTab("Båt", null, (new JScrollPane(table22, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED)), "Liste over båter");
		tabbedPane2.setMnemonicAt(1, KeyEvent.VK_2);
		tabbedPane2.addTab("Hus", null, (new JScrollPane(table23, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED)), "Liste over hus");
		tabbedPane2.setMnemonicAt(2, KeyEvent.VK_3);
		tabbedPane2.addTab("Hytte", null, (new JScrollPane(table24, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED)), "Liste over hytter");
		tabbedPane2.setMnemonicAt(3, KeyEvent.VK_4);
		tabbedPane2.addChangeListener(changeLytter);


		tabbedPane = new JTabbedPane();
		tabbedPane.addTab("Kunder", null, (new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED)), "Liste over kunder");
		tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
		tabbedPane.addTab("Forsikringer", null, (new JScrollPane(tabbedPane2)), "Liste over forsikringer");
		tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
		tabbedPane.addTab("Skademeldinger", null, (new JScrollPane(table3, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED)), "Liste over skademeldinger");
		tabbedPane.setMnemonicAt(0, KeyEvent.VK_3);
		tabbedPane.addChangeListener(changeLytter);


		add(new JScrollPane(tabbedPane, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER), BorderLayout.CENTER);


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
						if(tabbedPane.getSelectedIndex() == 0)
						{
							Kunde kunn = register.getKundeViaKundeNr( (String)theTable.getValueAt(rad, TModel.KUNDE_NR) );
							KundeProfil vin = new KundeProfil(kunn);
						}
						else if(tabbedPane.getSelectedIndex() == 2)
						{
							Skademelding skadd = register.getSkadeViaNr( (int)theTable.getValueAt(rad, TModel.SKADE_NR) );
							//SkademeldingsProfil vin = new SkademeldingsProfil(skadd);
						}

						else if(tabbedPane2.getSelectedIndex() == 0)
						{
							BilForsikring1 bilfor = register.getBilViaNr( (int)theTable.getValueAt(rad, TModel.FORSIKRINGS_NR) );
							//ForsikringsProfil vinn = new ForsikringsProfil(bilfor);
						}
						else if(tabbedPane2.getSelectedIndex() == 1)
						{
							BåtForsikring1 båtfor = register.getBåtViaNr( (int)theTable.getValueAt(rad, TModel.FORSIKRINGS_NR) );
							//ForsikringsProfil vinn = new ForsikringsProfil(båtfor);
						}
						else if(tabbedPane2.getSelectedIndex() == 2)
						{
							HusForsikring1 husfor = register.getHusViaNr( (int)theTable.getValueAt(rad, TModel.FORSIKRINGS_NR) );
							//ForsikringsProfil vinn = new ForsikringsProfil(husfor);
						}
						else if(tabbedPane2.getSelectedIndex() == 3)
						{
							HytteForsikring1 hytfor = register.getHytteViaNr( (int)theTable.getValueAt(rad, TModel.FORSIKRINGS_NR) );
							//ForsikringsProfil vinn = new ForsikringsProfil(hytfor);
						}
		          	}
		        }
		      }
		};
		table.addMouseListener(mouseListener);
		table21.addMouseListener(mouseListener);
		table22.addMouseListener(mouseListener);
		table23.addMouseListener(mouseListener);
		table24.addMouseListener(mouseListener);
		table3.addMouseListener(mouseListener);

	}

	public void søk()
	{
		if(tabbedPane.getSelectedIndex() == 0)
			søkKunde();
		else if(tabbedPane2.getSelectedIndex() == 0)
			søkBil();
		else if(tabbedPane2.getSelectedIndex() == 1)
			søkBåt();
		else if(tabbedPane2.getSelectedIndex() == 2)
			søkHus();
		else if(tabbedPane2.getSelectedIndex() == 3)
			søkHytte();
		else if(tabbedPane.getSelectedIndex() == 2)
			søkSkade();
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

	public SkademeldingReg søkSkade()
	{

		SkademeldingReg res = register.getSkademeldinger();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		//dateField.setValue(new java.util.Date());

		String snr = skademeldingsNr.getText();
		int smNr = Integer.valueOf(snr);
		String knr = kundNr.getText();
		String st = skadeType.getText();
		String sa = skadeAdresse.getText();
		String ub = utbetaltBeløp.getText();
		int ubb = Integer.valueOf(ub);
		String ss = skadeStatus.getText();
		Calendar sd = (Calendar) skadeDato.getValue();
		Calendar sfd = (Calendar) skadeFørDato.getValue();
		Calendar sed = (Calendar) skadeEtterDato.getValue();

		if(!snr.isEmpty() )
			res = register.getSkadeViaNr(res, smNr);
		if(!knr.isEmpty() )
			res = register.getSkadeViaKundeNr(res, knr);
		if(!st.isEmpty() )
			res = register.getSkadeViaType(res, st);
		if(!sa.isEmpty() )
			res = register.getSkadeViaAdresse(res, sa);
		if(!ub.isEmpty() )
			res = register.getSkadeViaErstatning(res, ubb);
		if(!ss.isEmpty() )
			res = register.getSkadeViaStatus(res, ss);
		if(!(sd == null) )
			res = register.getSkadeViaDato(res, sd);
		if(!(sfd == null))
			res = register.getSkadeFørDato(res, sfd);
		if(!(sed == null))
			res = register.getSkadeEtterDato(res, sed);

		tableModel3 = new TModel(res);
		table3.setModel(tableModel3);
		tableModel3.setTableCellEditor(table3);
		return res;
	}

	public ForsikringsReg1 søkBil()
	{
		ForsikringsReg1 res = register.getBiler();

		String fnr = bilforsikringsnr.getText();
		int bilnr = Integer.valueOf(fnr);
		//Calendar start = inngått.getText();
		String knr = kundNr.getText();
		//String forb = forsikringBeløp.getText();
		//int fbl = Integer.valueOf(forb);
		String eir = eier.getText();
		String rnr = bilregNr.getText();
		String tp = bilType.getText();
		String md = bilModell.getText();
		String årr = bilRegÅr.getText();
		int rår = Integer.valueOf(årr);
		String km = bilKM.getText();
		int åkl = Integer.valueOf(km);

		if(!fnr.isEmpty() )
			res = register.getBilViaNr(res, bilnr);
		if(!knr.isEmpty() )
			res = register.getBilViaKundeNr(res, knr);
		//if(!forb.isEmpty() )
		//	res = register.getBilViaBeløp(res, fbl);
		if(!eir.isEmpty() )
			res = register.getBilViaEier(res, eir);
		if(!rnr.isEmpty() )
			res = register.getBilViaRegNr(res, rnr);
		if(!tp.isEmpty() )
			res = register.getBilViaType(res, tp);
		if(!md.isEmpty() )
			res = register.getBilViaModell(res, md);
		if(!årr.isEmpty() )
			res = register.getBilViaRegÅr(res, rår);
		if(!km.isEmpty() )
			res = register.getBilViaKjørelengde(res, åkl);

		tableModel21 = new TModel(res);
		table21.setModel(tableModel21);
		tableModel21.setTableCellEditor(table21);
		return res;
	}

	public BåtForsikringsReg søkBåt()
	{
		BåtForsikringsReg res = register.getBåter();

		String fnr = båtforsikringsnr.getText();
		int nr = Integer.valueOf(fnr);
		//Calendar start = inngått.getText();
		String knr = kundNr.getText();
		//String bl = fbeløp.getText();
		//int fbl = Integer.valueOf(bl);
		String eir = eier.getText();
		String rnr = båtregNr.getText();
		String tp = båtType.getText();
		String md = båtModell.getText();
		String bårrr = båtÅr.getText();
		int bår = Integer.valueOf(bårrr);
		String lengd = båtLengde.getText();
		int len = Integer.valueOf(lengd);
		String bm = båtMotor.getText();
		String styrk = båtHK.getText();
		int hk = Integer.valueOf(styrk);

		if(!fnr.isEmpty() )
			res = register.getBåtViaNr(res, nr);
		if(!knr.isEmpty() )
			res = register.getBåtViaKundeNr(res, knr);
		//if(!bl.isEmpty() )
		//	res = register.getBåtViaBeløp(res, fbl);
		if(!eir.isEmpty() )
			res = register.getBåtViaEier(res, eir);
		if(!rnr.isEmpty() )
			res = register.getBåtViaRegNr(res, rnr);
		if(!tp.isEmpty() )
			res = register.getBåtViaType(res, tp);
		if(!md.isEmpty() )
			res = register.getBåtViaModell(res, md);
		if(!bårrr.isEmpty() )
			res = register.getBåtViaRegÅr(res, bår);
		if(!lengd.isEmpty() )
			res = register.getBåtViaLengde(res, len);
		if(!bm.isEmpty() )
			res = register.getBåtViaMotor(res, bm);
		if(!styrk.isEmpty() )
			res = register.getBåtViaStyrke(res, hk);

		tableModel22 = new TModel(res);
		table22.setModel(tableModel22);
		tableModel22.setTableCellEditor(table22);
		return res;
	}

	public HusForsikringsReg søkHus()
	{
		HusForsikringsReg res = register.getHus();

		String fnr = husforsikringsnr.getText();
		int nr = Integer.valueOf(fnr);
		//Calendar start = inngått.getText();
		String knr = kundNr.getText();
		String tot = totalbeløp.getText();
		int total = Integer.valueOf(tot);
		String adr = adresse.getText();
		String bår = byggeÅr.getText();
		int byår = Integer.valueOf(bår);
		String tp = husType.getText();
		String md = husMateriale.getText();
		String st = standard.getText();
		int stan = Integer.valueOf(st);
		String stør = KM.getText();
		int km = Integer.valueOf(stør);
		String bygg = byggBeløp.getText();
		int byg = Integer.valueOf(bygg);
		String inn = innboBeløp.getText();
		int innb = Integer.valueOf(inn);

		if(!fnr.isEmpty() )
			res = register.getHusViaNr(res, nr);
		if(!knr.isEmpty() )
			res = register.getHusViaKundeNr(res, knr);
		if(!tot.isEmpty() )
			res = register.getHusViaBeløpT(res, total);
		if(!adr.isEmpty() )
			res = register.getHusViaAdresse(res, adr);
		if(!bår.isEmpty() )
			res = register.getHusViaÅr(res, byår);
		if(!tp.isEmpty() )
			res = register.getHusViaType(res, tp);
		if(!md.isEmpty() )
			res = register.getHusViaMateriale(res, md);
		if(!st.isEmpty() )
			res = register.getHusViaStandard(res, st);
		if(!stør.isEmpty() )
			res = register.getHusViaStørrelse(res, km);
		if(!bygg.isEmpty() )
			res = register.getHusViaBeløpB(res, byg);
		if(!inn.isEmpty() )
			res = register.getHusViaBeløpI(res, innb);

		tableModel23 = new TModel(res);
		table23.setModel(tableModel23);
		tableModel23.setTableCellEditor(table23);
		return res;
	}

	public HytteForsikringsReg søkHytte()
	{
		HytteForsikringsReg res = register.getHytter();

		String fnr = hytteforsikringsnr.getText();
		int nr = Integer.valueOf(fnr);
		//Calendar start = inngått.getText();
		String knr = kundNr.getText();
		String tot = totalbeløp.getText();
		int total = Integer.valueOf(tot);
		String adr = adresse.getText();
		String bår = byggeÅr.getText();
		int byår = Integer.valueOf(bår);
		String tp = hytteType.getText();
		String md = hytteMateriale.getText();
		String st = standard.getText();
		int stan = Integer.valueOf(st);
		String stør = KM.getText();
		int km = Integer.valueOf(stør);
		String bygg = byggBeløp.getText();
		int byg = Integer.valueOf(bygg);
		String inn = innboBeløp.getText();
		int innb = Integer.valueOf(inn);

		if(!fnr.isEmpty() )
			res = register.getHytteViaNr(res, nr);
		if(!knr.isEmpty() )
			res = register.getHytteViaKundeNr(res, knr);
		if(!tot.isEmpty() )
			res = register.getHytteViaBeløpT(res, total);
		if(!adr.isEmpty() )
			res = register.getHytteViaAdresse(res, adr);
		if(!bår.isEmpty() )
			res = register.getHytteViaÅr(res, byår);
		if(!tp.isEmpty() )
			res = register.getHytteViaType(res, tp);
		if(!md.isEmpty() )
			res = register.getHytteViaMateriale(res, md);
		if(!st.isEmpty() )
			res = register.getHytteViaStandard(res, st);
		if(!stør.isEmpty() )
			res = register.getHytteViaStørrelse(res, km);
		if(!bygg.isEmpty() )
			res = register.getHytteViaBeløpB(res, byg);
		if(!inn.isEmpty() )
			res = register.getHytteViaBeløpI(res, innb);

		tableModel24 = new TModel(res);
		table24.setModel(tableModel24);
		tableModel24.setTableCellEditor(table24);
		return res;
	}


	public void kundePanel()
	{
		grid.removeAll();
		grid.add(oppdaterKnapp);
		katGrid.removeAll();
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
		byttTab();
	}

	public void forsikringPanel()
	{
		grid.add(søkEtterLabel);
		katGrid.add(bilKat);
		katGrid.add(båtKat);
		katGrid.add(husKat);
		katGrid.add(hytKat);;
		if(bilKat.isSelected())
		searchGrid.removeAll();
		byttTab();
	}

	public void skademeldingPanel()
	{
		grid.removeAll();
		grid.add(oppdaterKnapp);
		katGrid.removeAll();
		searchGrid.removeAll();
		searchGrid.add(skademeldingsNrLabel);
		searchGrid.add(skademeldingsNr);
		searchGrid.add(kundNrLabel);
		searchGrid.add(kundNr);
		searchGrid.add(skadeTypeLabel);
		searchGrid.add(skadeType);
		searchGrid.add(skadeAdresseLabel);
		searchGrid.add(skadeAdresse);
		searchGrid.add(skadeStatusLabel);
		searchGrid.add(skadeStatus);
		searchGrid.add(skadeFørDatoLabel);
		searchGrid.add(skadeFørDato);
		searchGrid.add(skadeEtterDatoLabel);
		searchGrid.add(skadeEtterDato);
		searchGrid.add(utbetaltBeløpLabel);
		searchGrid.add(utbetaltBeløp);
		byttTab();
	}

	public void byttTab()
	{
		grid.revalidate();
		grid.repaint();
		katGrid.revalidate();
		katGrid.repaint();
		searchGrid.revalidate();
		searchGrid.repaint();
	}
	public void endraBonus()
	{
		try
		{
			int row = table.getSelectedRow();
			if(row == -1)
				return;

			Kunde anna = register.getKundeViaKundeNr( (String)tableModel.getValueAt(row, TModel.KUNDE_NR) );
			
			String melding = "Er du sikker på at du vil endra bonus?";
			String tittel = "Bonus";

			int svar = JOptionPane.showConfirmDialog(null, melding, tittel, JOptionPane.YES_NO_OPTION);
			if(svar == JOptionPane.YES_OPTION)
			{
				String[] bonusProcent = { "0", "10", "20", "30", "40", "50","60","70","75" };
			    Object j =  JOptionPane.showInputDialog(null, "Endra bonus",
			        "Bonus", JOptionPane.QUESTION_MESSAGE, null, 
			        bonusProcent,
			        bonusProcent[1]); 
			    
			    return;
			}
		}
		catch(NoSuchElementException nsee)
		{
			JOptionPane.showMessageDialog(null, "NoSuchElementException i bonusmetoden", "FEIL", JOptionPane.ERROR_MESSAGE);
		}
	}
	public void setBonus( int b) 
	{

	} 

	//sumBeløpBil()getForsikringsbeløp()


	private class RadioLytter implements ItemListener
	{
		public void itemStateChanged(ItemEvent e)
		{
			if(bilKat.isSelected())
			{
				søkFor = SØK_BIL;
				tabbedPane2.setSelectedIndex(0);
				searchGrid.removeAll();
				searchGrid.add(bilforsikringsnrLabel);
				searchGrid.add(bilforsikringsnr);
				searchGrid.add(eierLabel);
				searchGrid.add(eier);
				searchGrid.add(forsikringBeløpLabel);
				searchGrid.add(forsikringBeløp);
				searchGrid.add(bilregNrLabel);
				searchGrid.add(bilregNr);
				searchGrid.add(bilTypeLabel);
				searchGrid.add(bilType);
				searchGrid.add(bilModellLabel);
				searchGrid.add(bilModell);
				searchGrid.add(bilRegÅrLabel);
				searchGrid.add(bilRegÅr);
				searchGrid.add(bilKMLabel);
				searchGrid.add(bilKM);
				searchGrid.add(søkKnapp);
				searchGrid.add(lagreKnapp);
				searchGrid.add(statKnapp);
				byttTab();
				//søkBil();
			}
			else if(båtKat.isSelected())
			{
				søkFor = SØK_BÅT;
				tabbedPane2.setSelectedIndex(1);
				searchGrid.removeAll();
				searchGrid.add(båtforsikringsnrLabel);
				searchGrid.add(båtforsikringsnr);
				searchGrid.add(eierLabel);
				searchGrid.add(eier);
				searchGrid.add(forsikringBeløpLabel);
				searchGrid.add(forsikringBeløp);
				searchGrid.add(båtregNrLabel);
				searchGrid.add(båtregNr);
				searchGrid.add(båtTypeLabel);
				searchGrid.add(båtType);
				searchGrid.add(båtModellLabel);
				searchGrid.add(båtModell);
				searchGrid.add(båtLengdeLabel);
				searchGrid.add(båtLengde);
				searchGrid.add(båtÅrLabel);
				searchGrid.add(båtÅr);
				searchGrid.add(båtMotorLabel);
				searchGrid.add(båtMotor);
				searchGrid.add(båtHKLabel);
				searchGrid.add(båtHK);
				searchGrid.add(søkKnapp);
				searchGrid.add(statKnapp);
				byttTab();
				//søkBåt();
			}
			if(husKat.isSelected())
			{
				søkFor = SØK_HUS;
				tabbedPane2.setSelectedIndex(2);
				searchGrid.removeAll();
				searchGrid.add(husforsikringsnrLabel);
				searchGrid.add(husforsikringsnr);
				searchGrid.add(totalbeløpLabel);
				searchGrid.add(totalbeløp);
				searchGrid.add(adresseLabel);
				searchGrid.add(adresse);
				searchGrid.add(byggeÅrLabel);
				searchGrid.add(byggeÅr);
				searchGrid.add(husTypeLabel);
				searchGrid.add(husType);
				searchGrid.add(husMaterialeLabel);
				searchGrid.add(husMateriale);
				searchGrid.add(standardLabel);
				searchGrid.add(standard);
				searchGrid.add(KMLabel);
				searchGrid.add(KM);
				searchGrid.add(byggBeløpLabel);
				searchGrid.add(byggBeløp);
				searchGrid.add(innboBeløpLabel);
				searchGrid.add(innboBeløp);
				searchGrid.add(søkKnapp);
				searchGrid.add(statKnapp);
				byttTab();
				//søkHus();
			}
			if(hytKat.isSelected())
			{
				søkFor = SØK_HYTTE;
				tabbedPane2.setSelectedIndex(3);
				searchGrid.removeAll();
				searchGrid.add(hytteforsikringsnrLabel);
				searchGrid.add(hytteforsikringsnr);
				searchGrid.add(totalbeløpLabel);
				searchGrid.add(totalbeløp);
				searchGrid.add(adresseLabel);
				searchGrid.add(adresse);
				searchGrid.add(byggeÅrLabel);
				searchGrid.add(byggeÅr);
				searchGrid.add(hytteTypeLabel);
				searchGrid.add(hytteType);
				searchGrid.add(hytteMaterialeLabel);
				searchGrid.add(hytteMateriale);
				searchGrid.add(standardLabel);
				searchGrid.add(standard);
				searchGrid.add(KMLabel);
				searchGrid.add(KM);
				searchGrid.add(byggBeløpLabel);
				searchGrid.add(byggBeløp);
				searchGrid.add(innboBeløpLabel);
				searchGrid.add(innboBeløp);
				searchGrid.add(søkKnapp);
				searchGrid.add(statKnapp);
				byttTab();
				//søkHytte();
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
			else if(tabbedPane.getSelectedIndex() == 2)
				skademeldingPanel();
			if(tabbedPane2.getSelectedIndex() == 0)
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
			{
				if(tabbedPane.getSelectedIndex() == 0)
					søkKunde();
			}
			else if(e.getSource() == søkKnapp || e.getSource() == bilforsikringsnr || e.getSource() == eier
			|| e.getSource() == forsikringBeløp || e.getSource() == bilregNr || e.getSource() == bilType
			|| e.getSource() == bilModell || e.getSource() == bilRegÅr || e.getSource() == bilKM)
			{
				if(tabbedPane2.getSelectedIndex() == 0)
					//søkBil();
					søkKunde();
			}
			else if(e.getSource() == lagreKnapp)
				endraBonus();
			
			else if(e.getSource() == søkKnapp || e.getSource() == båtforsikringsnr || e.getSource() == eier
			|| e.getSource() == forsikringBeløp || e.getSource() == båtregNr || e.getSource() == båtType
			|| e.getSource() == båtModell || e.getSource() == båtLengde || e.getSource() == båtÅr
			|| e.getSource() == båtMotor || e.getSource() == båtHK)
			{
				if(tabbedPane2.getSelectedIndex() == 1)
					//søkBåt();
					søkKunde();
			}
			else if(e.getSource() == søkKnapp || e.getSource() == husforsikringsnr || e.getSource() == totalbeløp
			|| e.getSource() == adresse || e.getSource() == byggeÅr || e.getSource() == husType
			|| e.getSource() == husMateriale || e.getSource() == standard || e.getSource() == KM
			|| e.getSource() == byggBeløp || e.getSource() == innboBeløp)
			{
				if(tabbedPane2.getSelectedIndex() == 2)
					//søkHus();
					søkKunde();
			}
			else if(e.getSource() == søkKnapp || e.getSource() == hytteforsikringsnr || e.getSource() == totalbeløp
			|| e.getSource() == adresse || e.getSource() == byggeÅr || e.getSource() == hytteType
			|| e.getSource() == hytteMateriale || e.getSource() == standard || e.getSource() == KM
			|| e.getSource() == byggBeløp || e.getSource() == innboBeløp)
			{
				if(tabbedPane2.getSelectedIndex() == 3)
					//søkHytte();
					søkKunde();
			}

			else if(e.getSource() == oppdaterKnapp)
				søk();
			else if(e.getSource() == statKnapp)
				statistikk();
		}
	}
}
