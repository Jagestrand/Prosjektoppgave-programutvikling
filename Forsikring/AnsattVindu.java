/*Skrevet av Even Nerheim, s199184, sist redigert 20.05.2015
GUI for ansatte ved forsikringsselskapet. Her kan ansatte se en oversikt over registrerte kunder,
forskjellige forsikringer og skademeldinger, i tillegg til å kunne godkjenne eller avslå erstatningskrav
og oppsigelser
*/
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
	//skrivefelt for søking av kunder, forsikringer og skademeldinger:
	private JTextField kundNr, kundPersonnr, kundNavn, kundTelefon, kundAdresse, kundPostnr, kundPostby, typeForsikring,
				bilforsikringsnr, eier, forsikringBeløp, bilregNr, bilType, bilModell, bilRegÅr, bilKM,
				båtforsikringsnr, båtregNr, båtType, båtModell, båtLengde, båtÅr, båtMotor, båtHK,
				husforsikringsnr, totalbeløp, adresse, byggeÅr, husType, husMateriale, standard, KM, byggBeløp, innboBeløp,
				hytteforsikringsnr, hytteType, hytteMateriale, skademeldingsNr, skadeType, skadeAdresse, utbetaltBeløp, skadeStatus;
	//felter for søking av dato, ikke i bruk på dette tidspunkt
	//private JFormattedTextField skadeDato, førDato, etterDato;
	private JTextArea informationTop, visForsikringInfo;
	private JButton søkKnapp, minInfoKnapp, oppdaterKnapp, statKnapp, seKundesForsikringer, visForsikringKnapp, deletaForsikringKnapp, lagreKnapp, godkjennKnapp, avslåKnapp;
	private JLabel søkEtterLabel, kundNrLabel, kundPersonnrLabel, kundNavnLabel, kundTelefonLabel, kundAdresseLabel, kundPostnrLabel, kundPostbyLabel,
				bilforsikringsnrLabel, eierLabel, forsikringBeløpLabel, bilregNrLabel, bilTypeLabel, bilModellLabel, bilRegÅrLabel, bilKMLabel,
				båtforsikringsnrLabel, båtregNrLabel, båtTypeLabel, båtModellLabel, båtLengdeLabel, båtÅrLabel, båtMotorLabel, båtHKLabel,
				husforsikringsnrLabel, totalbeløpLabel, adresseLabel, byggeÅrLabel, husTypeLabel, husMaterialeLabel, standardLabel, KMLabel, byggBeløpLabel, innboBeløpLabel,
				hytteforsikringsnrLabel, hytteTypeLabel, hytteMaterialeLabel, skademeldingsNrLabel, skadeDatoLabel, førDatoLabel, etterDatoLabel,
				skadeTypeLabel, skadeAdresseLabel, utbetaltBeløpLabel, skadeStatusLabel;
	private JPanel visForsikring, visForsikringFlow, searchGrid, grid, katGrid, border, flow;
	private TModel tableModel, tableModel21, tableModel22, tableModel23, tableModel24, tableModel3;
	private ButtonGroup kategoriKnapper;
	//tabellene
	private JTable table, table21, table22, table23, table24, table3;
	private JTabbedPane tabbedPane, tabbedPane2, tabbedPane3;
	private JRadioButton bilKat, båtKat, husKat, hytKat;

	public AnsattVindu(Huvudvindu win, Ansatt anna)
	{

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
		GridLayout gridd = new GridLayout(3,1);
		gridd.setVgap(5);
		grid = new JPanel(gridd);
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

		//felt for kundesøk og behandling
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
		minInfoKnapp = new JButton("Min brukerinfo");
		oppdaterKnapp = new JButton("Oppdater");
		statKnapp = new JButton("Statistikk");

		visForsikringKnapp = new JButton("Se kundens forsikringer");
		deletaForsikringKnapp = new JButton("Opphev forsikring");

		godkjennKnapp = new JButton("Godkjenn takstbeløp");
		avslåKnapp = new JButton("Avslå takstbeløp");
		seKundesForsikringer = new JButton("Se kundes forsikringer");

		søkKnapp.addActionListener(lytter);
		minInfoKnapp.addActionListener(lytter);
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
		godkjennKnapp.addActionListener(lytter);
		avslåKnapp.addActionListener(lytter);
		seKundesForsikringer.addActionListener(lytter);

		//felt for bilsøk og behandling
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

		//felt for båtsøk
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

		//felt for hussøk
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

		//felt for hyttesøk
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
		//skadeDato = new JFormattedTextField(new SimpleDateFormat("dd.MM.yyyy"));
		//førDato = new JFormattedTextField(new SimpleDateFormat("dd.MM.yyyy"));
		//etterDato = new JFormattedTextField(new SimpleDateFormat("dd.MM.yyyy"));
		skadeType = new JTextField(DATA_FIELD_LENGTH);
		skadeAdresse = new JTextField(DATA_FIELD_LENGTH);
		utbetaltBeløp = new JTextField(DATA_FIELD_LENGTH);
		skadeStatus = new JTextField(DATA_FIELD_LENGTH);
		skademeldingsNrLabel = new JLabel("Skademeldingsnr:");
		skadeDatoLabel = new JLabel("Dato:");
		førDatoLabel = new JLabel("Før dato:");
		etterDatoLabel = new JLabel("Etter dato:");
		skadeTypeLabel = new JLabel("Type skade:");
		skadeAdresseLabel = new JLabel("Skadested:");
		utbetaltBeløpLabel = new JLabel("Erstatningsbeløp:");
		skadeStatusLabel = new JLabel("Skademelding status:");

		skademeldingsNr.addActionListener(lytter);
		//skadeDato.addActionListener(lytter);
		//førDato.addActionListener(lytter);
		//etterDato.addActionListener(lytter);
		skadeType.addActionListener(lytter);
		utbetaltBeløp.addActionListener(lytter);
		skadeStatus.addActionListener(lytter);


		//legger inn feltene på første side for kundsøk
		grid.add(minInfoKnapp);
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
		//searchGrid.add(førDatoLabel);
		//searchGrid.add(førDato);
		//searchGrid.add(etterDatoLabel);
		//searchGrid.add(etterDato);
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

		//oppretter tabellene med informasjon
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

		//setter opp tabber:

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

		//lytter til dobbelklikk på kunder, forsikringer og skademeldinger for å åpne informasjonsvindu
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
							BilForsikring bilfor = register.getBilViaNr( (int)theTable.getValueAt(rad, TModel.FORSIKRINGS_NR) );
							ForsikringsProfil vinn = new ForsikringsProfil(bilfor);
						}
						else if(tabbedPane2.getSelectedIndex() == 1)
						{
							BåtForsikring båtfor = register.getBåtViaNr( (int)theTable.getValueAt(rad, TModel.FORSIKRINGS_NR) );
							//ForsikringsProfil vinn = new ForsikringsProfil(båtfor);
						}
						else if(tabbedPane2.getSelectedIndex() == 2)
						{
							HusForsikring husfor = register.getHusViaNr( (int)theTable.getValueAt(rad, TModel.FORSIKRINGS_NR) );
							//ForsikringsProfil vinn = new ForsikringsProfil(husfor);
						}
						else if(tabbedPane2.getSelectedIndex() == 3)
						{
							HytteForsikring hytfor = register.getHytteViaNr( (int)theTable.getValueAt(rad, TModel.FORSIKRINGS_NR) );
							//ForsikringsProfil vinn = new ForsikringsProfil(hytfor);
						}
		          	}
		        }
		      }
		};
		//setter tabellene responsive til klikking
		table.addMouseListener(mouseListener);
		table21.addMouseListener(mouseListener);
		table22.addMouseListener(mouseListener);
		table23.addMouseListener(mouseListener);
		table24.addMouseListener(mouseListener);
		table3.addMouseListener(mouseListener);

	}

	public void søk()		//kategoriserer søk
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

	public void statistikk()		//statistikk, ikke i bruk
	{
		JOptionPane.showMessageDialog(null, "Bedriften gjør det bra");
	}

	public KundeReg søkKunde()			//for søking i kunderegister
	{
		KundeReg res = register.getKunder();

		String knr = kundNr.getText();
		String kn = kundNavn.getText();
		String pn = kundPersonnr.getText();
		String tlf = kundTelefon.getText();
		String adr = kundAdresse.getText();
		String pnr = kundPostnr.getText();
		String pb = kundPostby.getText();
		/*												denne er ikke i bruk, men ligger
		Date date = (Date) førDato.getValue();			klar for videreutvikling for
		Calendar før = Calendar.getInstance();			søking etter dato
		Date date2 = (Date) etterDato.getValue();
		Calendar etter = Calendar.getInstance();
		if(!(date == null))
		{
			før.setTime(date);
			førDato.setValue(null);
			res = register.getKundeFørDato(res, før);
		}
		if(!(date2 == null))
		{
			etter.setTime(date2);
			etterDato.setValue(null);
			res = register.getKundeEtterDato(res, etter);
		}
		if(!(før == null))
			res = register.getKundeFørDato(res, før);
		if(!(etter == null))
			res = register.getKundeEtterDato(res, etter);
		*/
		if(!knr.isEmpty() )
			res = register.getKundeViaKundeNr(res, knr);
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

	public SkademeldingReg søkSkade()			//søking i skaderegister
	{
		SkademeldingReg res = register.getSkademeldinger();
		String snr = skademeldingsNr.getText();
		String knr = kundNr.getText();
		String st = skadeType.getText();
		String sa = skadeAdresse.getText();
		String ub = utbetaltBeløp.getText();
		String ss = skadeStatus.getText();
		/*												denne er ikke i bruk, men ligger
		Date date = (Date) førDato.getValue();			klar for videreutvikling for
		Calendar før = Calendar.getInstance();			søking etter dato
		Date date2 = (Date) etterDato.getValue();
		Calendar etter = Calendar.getInstance();
		if(!(date == null))
		{
			før.setTime(date);
			førDato.setValue("");
		}
		if(!(date2 == null))
		{
			etter.setTime(date2);
			etterDato.setValue("");
		}
		if(!(før == null))
			res = register.getSkadeFørDato(res, før);
		if(!(etter == null))
			res = register.getSkadeEtterDato(res, etter);
		*/
		if(!snr.isEmpty() )
			res = register.getSkadeViaNr(res, snr);
		if(!knr.isEmpty() )
			res = register.getSkadeViaKundeNr(res, knr);
		if(!st.isEmpty() )
			res = register.getSkadeViaType(res, st);
		if(!sa.isEmpty() )
			res = register.getSkadeViaAdresse(res, sa);
		if(!ub.isEmpty() )
			res = register.getSkadeViaErstatning(res, ub);
		if(!ss.isEmpty() )
			res = register.getSkadeViaStatus(res, ss);

		tableModel3 = new TModel(res);
		table3.setModel(tableModel3);
		tableModel3.setTableCellEditor(table3);
		return res;
	}

	public BilForsikringsReg søkBil()		//søking i bilforsikringsregister
	{
		BilForsikringsReg res = register.getBiler();

		String fnr = bilforsikringsnr.getText();
		String knr = kundNr.getText();
		String rnr = bilregNr.getText();
		String tp = bilType.getText();
		String md = bilModell.getText();
		String årr = bilRegÅr.getText();
		String km = bilKM.getText();
		/*												denne er ikke i bruk, men ligger
		Date date = (Date) førDato.getValue();			klar for videreutvikling for
		Calendar før = Calendar.getInstance();			søking etter dato
		Date date2 = (Date) etterDato.getValue();
		Calendar etter = Calendar.getInstance();
		if(!(date == null))
		{
			før.setTime(date);
			førDato.setValue(null);
		}
		if(!(date2 == null))
		{
			etter.setTime(date2);
			etterDato.setValue(null);
		}

		if(!(før == null))
			res = register.getBilFørDato(res, før);
		if(!(etter == null))
			res = register.getBilEtterDato(res, etter);
		*/
		if(!fnr.isEmpty() )
			res = register.getBilViaNr(res, fnr);
		if(!knr.isEmpty() )
			res = register.getBilViaKundeNr(res, knr);
		if(!rnr.isEmpty() )
			res = register.getBilViaRegNr(res, rnr);
		if(!tp.isEmpty() )
			res = register.getBilViaType(res, tp);
		if(!md.isEmpty() )
			res = register.getBilViaModell(res, md);
		if(!årr.isEmpty() )
			res = register.getBilViaRegÅr(res, årr);

		tableModel21 = new TModel(res);
		table21.setModel(tableModel21);
		tableModel21.setTableCellEditor(table21);
		return res;
	}

	public BåtForsikringsReg søkBåt()		//søking i båtforsikringsregister
	{
		BåtForsikringsReg res = register.getBåter();

		String fnr = båtforsikringsnr.getText();
		String knr = kundNr.getText();
		String rnr = båtregNr.getText();
		String tp = båtType.getText();
		String md = båtModell.getText();
		String bårrr = båtÅr.getText();
		String bm = båtMotor.getText();
		/*												denne er ikke i bruk, men ligger
		Date date = (Date) førDato.getValue();			klar for videreutvikling for
		Calendar før = Calendar.getInstance();			søking etter dato
		Date date2 = (Date) etterDato.getValue();
		Calendar etter = Calendar.getInstance();
		if(!(date == null))
		{
			før.setTime(date);
			førDato.setValue(null);
		}
		if(!(date2 == null))
		{
			etter.setTime(date2);
			etterDato.setValue(null);
		}

		if(!(før == null))
			res = register.getBåtFørDato(res, før);
		if(!(etter == null))
			res = register.getBåtEtterDato(res, etter);
		*/
		if(!fnr.isEmpty() )
			res = register.getBåtViaNr(res, fnr);
		if(!knr.isEmpty() )
			res = register.getBåtViaKundeNr(res, knr);
		if(!rnr.isEmpty() )
			res = register.getBåtViaRegNr(res, rnr);
		if(!tp.isEmpty() )
			res = register.getBåtViaType(res, tp);
		if(!md.isEmpty() )
			res = register.getBåtViaModell(res, md);
		if(!bårrr.isEmpty() )
			res = register.getBåtViaRegÅr(res, bårrr);
		if(!bm.isEmpty() )
			res = register.getBåtViaMotor(res, bm);

		tableModel22 = new TModel(res);
		table22.setModel(tableModel22);
		tableModel22.setTableCellEditor(table22);
		return res;
	}

	public HusForsikringsReg søkHus()		//søking i husforsikringsregister
	{
		HusForsikringsReg res = register.getHus();

		String fnr = husforsikringsnr.getText();
		String knr = kundNr.getText();
		String tot = totalbeløp.getText();
		String adr = adresse.getText();
		String bår = byggeÅr.getText();
		String tp = husType.getText();
		String md = husMateriale.getText();
		String bygg = byggBeløp.getText();
		String inn = innboBeløp.getText();
		/*												denne er ikke i bruk, men ligger
		Date date = (Date) førDato.getValue();			klar for videreutvikling for
		Calendar før = Calendar.getInstance();			søking etter dato
		Date date2 = (Date) etterDato.getValue();
		Calendar etter = Calendar.getInstance();
		if(!(date == null))
		{
			før.setTime(date);
			førDato.setValue(null);
		}
		if(!(date2 == null))
		{
			etter.setTime(date2);
			etterDato.setValue(null);
		}
		if(!(før == null))
			res = register.getHusFørDato(res, før);
		if(!(etter == null))
			res = register.getHusEtterDato(res, etter);
		*/
		if(!fnr.isEmpty() )
			res = register.getHusViaNr(res, fnr);
		if(!knr.isEmpty() )
			res = register.getHusViaKundeNr(res, knr);
		if(!tot.isEmpty() )
			res = register.getHusViaBeløpT(res, tot);
		if(!adr.isEmpty() )
			res = register.getHusViaAdresse(res, adr);
		if(!bår.isEmpty() )
			res = register.getHusViaÅr(res, bår);
		if(!tp.isEmpty() )
			res = register.getHusViaType(res, tp);
		if(!md.isEmpty() )
			res = register.getHusViaMateriale(res, md);
		if(!bygg.isEmpty() )
			res = register.getHusViaBeløpB(res, bygg);
		if(!inn.isEmpty() )
			res = register.getHusViaBeløpI(res, inn);


		tableModel23 = new TModel(res);
		table23.setModel(tableModel23);
		tableModel23.setTableCellEditor(table23);
		return res;
	}

	public HytteForsikringsReg søkHytte()		//søking i hytteforsikringsregister
	{
		HytteForsikringsReg res = register.getHytter();

		String fnr = hytteforsikringsnr.getText();
		String knr = kundNr.getText();
		String tot = totalbeløp.getText();
		String adr = adresse.getText();
		String bår = byggeÅr.getText();
		String tp = hytteType.getText();
		String md = hytteMateriale.getText();
		String bygg = byggBeløp.getText();
		String inn = innboBeløp.getText();
		/*												denne er ikke i bruk, men ligger
		Date date = (Date) førDato.getValue();			klar for videreutvikling for
		Calendar før = Calendar.getInstance();			søking etter dato
		Date date2 = (Date) etterDato.getValue();
		Calendar etter = Calendar.getInstance();
		if(!(date == null))
		{
			før.setTime(date);
			førDato.setValue(null);
		}
		if(!(date2 == null))
		{
			etter.setTime(date2);
			etterDato.setValue(null);
		}
		if(!(før == null))
			res = register.getHytteFørDato(res, før);
		if(!(etter == null))
			res = register.getHytteEtterDato(res, etter);
		*/
		if(!fnr.isEmpty() )
			res = register.getHytteViaNr(res, fnr);
		if(!knr.isEmpty() )
			res = register.getHytteViaKundeNr(res, knr);
		if(!tot.isEmpty() )
			res = register.getHytteViaBeløpT(res, tot);
		if(!adr.isEmpty() )
			res = register.getHytteViaAdresse(res, adr);
		if(!bår.isEmpty() )
			res = register.getHytteViaÅr(res, bår);
		if(!tp.isEmpty() )
			res = register.getHytteViaType(res, tp);
		if(!md.isEmpty() )
			res = register.getHytteViaMateriale(res, md);
		if(!bygg.isEmpty() )
			res = register.getHytteViaBeløpB(res, bygg);
		if(!inn.isEmpty() )
			res = register.getHytteViaBeløpI(res, inn);

		tableModel24 = new TModel(res);
		table24.setModel(tableModel24);
		tableModel24.setTableCellEditor(table24);
		return res;
	}

	public void kundePanel()			//setter opp panel for kundesøk på venstre side
	{
		grid.removeAll();
		grid.add(minInfoKnapp);
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
		//searchGrid.add(førDatoLabel);
		//searchGrid.add(førDato);
		//searchGrid.add(etterDatoLabel);
		//searchGrid.add(etterDato);
		searchGrid.add(søkKnapp);
		searchGrid.add(statKnapp);
		byttTab();
	}

	public void forsikringPanel()		//setter opp panel for forsikringssøk på venstre side
	{
		grid.add(søkEtterLabel);
		katGrid.add(bilKat);
		katGrid.add(båtKat);
		katGrid.add(husKat);
		katGrid.add(hytKat);;
		searchGrid.removeAll();
		byttTab();
	}

	public void skademeldingPanel()		//setter opp panel for skademeldingssøk på vesntre side
	{
		grid.removeAll();
		grid.add(minInfoKnapp);
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
		//searchGrid.add(førDatoLabel);
		//searchGrid.add(førDato);
		//searchGrid.add(etterDatoLabel);
		//searchGrid.add(etterDato);
		searchGrid.add(utbetaltBeløpLabel);
		searchGrid.add(utbetaltBeløp);
		searchGrid.add(søkKnapp);
		searchGrid.add(statKnapp);
		searchGrid.add(godkjennKnapp);
		searchGrid.add(avslåKnapp);
		byttTab();
	}

	public void byttTab()			//bytter tab og panel på venstre side
	{
		grid.revalidate();
		grid.repaint();
		katGrid.revalidate();
		katGrid.repaint();
		searchGrid.revalidate();
		searchGrid.repaint();
	}
	public void endraBonus()		//forandrer bilbonus
	{
		try
		{
			int row = table21.getSelectedRow();
			if(row == -1)
				return;

			BilForsikring pelle = register.getBilViaNr( (int) tableModel21.getValueAt(row, TModel.FORSIKRINGS_NR) );

			String melding = "Er du sikker på at du vil endra bonus?";
			String tittel = "Bonus";

			int svar = JOptionPane.showConfirmDialog(null, melding, tittel, JOptionPane.YES_NO_OPTION);
			if(svar == JOptionPane.YES_OPTION)
			{
				String[] bonusProcent = { "0", "10", "20", "30", "40", "50","60","70","75" };
			    int bonusen = Integer.valueOf((String) JOptionPane.showInputDialog(null, "Endra bonus",
			        "Bonus", JOptionPane.QUESTION_MESSAGE, null,
			        bonusProcent,
			        bonusProcent[1]));
				register.endreBilBonus(pelle, bonusen);
			    return;
			}
		}
		catch(NoSuchElementException nsee)
		{
			JOptionPane.showMessageDialog(null, "NoSuchElementException i bonusmetoden", "FEIL", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void godkjennSkade()			//godkjenner takstbeløp
	{
		try{
			int row = table3.getSelectedRow();
			if(row == -1)
				return;

			Skademelding skade = register.getSkadeViaNr( (int)tableModel3.getValueAt(row, TModel.SKADE_NR));
			if(!skade.getGodkjent() || skade.getGodkjent())
				return;
			String melding = "Du er i ferd med å godkjenne dette takstbeløpet: " + skade.getTakst() + "kr.\nEr dette riktig?";
			String tittel = "Godkjenning av skadeerstatning";

			int svar = JOptionPane.showConfirmDialog(null, melding, tittel, JOptionPane.YES_NO_OPTION);
			if(svar == JOptionPane.YES_OPTION)
			{
				register.godkjennTakst(skade);
				søk();
				return;
			}
		}
		catch(NoSuchElementException nsee)
		{
			JOptionPane.showMessageDialog(null, "NoSuchElementException i godkjennSkade-metoden", "FEIL", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void avslåSkade()		//avslåt takstbeløp og godkjenner egetvalgt beløp
	{
		try{
			int row = table3.getSelectedRow();
			if(row == -1)
				return;

			Skademelding skade = register.getSkadeViaNr( (int)tableModel3.getValueAt(row, TModel.SKADE_NR));
			if(!skade.getGodkjent() || skade.getGodkjent())
				return;
			String melding = "Ønsker du å avslå dette takstbeløpet: " + skade.getTakst() + "kr?";
			String tittel = "Godkjenning av skadeerstatning";

			int svar = JOptionPane.showConfirmDialog(null, melding, tittel, JOptionPane.YES_NO_OPTION);
			if(svar == JOptionPane.YES_OPTION)
			{
				int nytt = Integer.valueOf(JOptionPane.showInputDialog(null, "Skriv inn godkjent erstatningssum:", "Erstatning"));
				register.avslåTakst(skade, nytt);
				søk();
				return;
			}
		}
		catch(NoSuchElementException nsee)
		{
			JOptionPane.showMessageDialog(null, "NoSuchElementException i avslåSkade-metoden", "FEIL", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void visMinInfo()			//viser ansattes informasjon i vindu
	{
		AnsattProfil aa = new AnsattProfil(ansatt);
	}

	public void tømFelter()				//tømmer alle felter
	{
		kundNr.setText("");
		kundPersonnr.setText("");
		kundNavn.setText("");
		kundTelefon.setText("");
		kundAdresse.setText("");
		kundPostnr.setText("");
		kundPostby.setText("");
		bilforsikringsnr.setText("");
		eier.setText("");
		forsikringBeløp.setText("");
		bilregNr.setText("");
		bilType.setText("");
		bilModell.setText("");
		bilRegÅr.setText("");
		bilKM.setText("");
		båtforsikringsnr.setText("");
		båtregNr.setText("");
		båtType.setText("");
		båtModell.setText("");
		båtLengde.setText("");
		båtÅr.setText("");
		båtMotor.setText("");
		båtHK.setText("");
		husforsikringsnr.setText("");
		totalbeløp.setText("");
		adresse.setText("");
		byggeÅr.setText("");
		husType.setText("");
		husMateriale.setText("");
		standard.setText("");
		KM.setText("");
		byggBeløp.setText("");
		innboBeløp.setText("");
		hytteforsikringsnr.setText("");
		hytteType.setText("");
		hytteMateriale.setText("");
		skademeldingsNr.setText("");
		skadeType.setText("");
		skadeAdresse.setText("");
		utbetaltBeløp.setText("");
		skadeStatus.setText("");
		//skadeDato.setText("");
		//førDato.setText("");
		//etterDato.setText("");
	}


	private class RadioLytter implements ItemListener	//bytter panel for forsikringssøk etter kategori
	{
		public void itemStateChanged(ItemEvent e)
		{
			if(bilKat.isSelected())					//bilforsikring
			{
				søkFor = SØK_BIL;
				tabbedPane2.setSelectedIndex(0);
				searchGrid.removeAll();
				searchGrid.add(bilforsikringsnrLabel);
				searchGrid.add(bilforsikringsnr);
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
				//searchGrid.add(førDatoLabel);
				//searchGrid.add(førDato);
				//searchGrid.add(etterDatoLabel);
				//searchGrid.add(etterDato);
				searchGrid.add(søkKnapp);
				searchGrid.add(lagreKnapp);
				searchGrid.add(statKnapp);
				byttTab();
				søkBil();
			}
			else if(båtKat.isSelected())			//båtforsikring
			{
				søkFor = SØK_BÅT;
				tabbedPane2.setSelectedIndex(1);
				searchGrid.removeAll();
				searchGrid.add(båtforsikringsnrLabel);
				searchGrid.add(båtforsikringsnr);
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
				//searchGrid.add(førDatoLabel);
				//searchGrid.add(førDato);
				//searchGrid.add(etterDatoLabel);
				//searchGrid.add(etterDato);
				searchGrid.add(søkKnapp);
				searchGrid.add(statKnapp);
				byttTab();
				søkBåt();
			}
			if(husKat.isSelected())			//husforsikring
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
				searchGrid.add(byggBeløpLabel);
				searchGrid.add(byggBeløp);
				searchGrid.add(innboBeløpLabel);
				searchGrid.add(innboBeløp);
				//searchGrid.add(førDatoLabel);
				//searchGrid.add(førDato);
				//searchGrid.add(etterDatoLabel);
				//searchGrid.add(etterDato);
				searchGrid.add(søkKnapp);
				searchGrid.add(statKnapp);
				byttTab();
				søkHus();
			}
			if(hytKat.isSelected())		//hytteforsikring
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
				searchGrid.add(byggBeløpLabel);
				searchGrid.add(byggBeløp);
				searchGrid.add(innboBeløpLabel);
				searchGrid.add(innboBeløp);
				//searchGrid.add(førDatoLabel);
				//searchGrid.add(førDato);
				//searchGrid.add(etterDatoLabel);
				//searchGrid.add(etterDato);
				searchGrid.add(søkKnapp);
				searchGrid.add(statKnapp);
				byttTab();
				søkHytte();
			}
		}
	}

	private class ChangeLytter implements ChangeListener	//bytter panel når tab velges
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

	private class Lytterklasse implements ActionListener	//lytter til knapper og utfylling av søkefelter
	{
		public void actionPerformed(ActionEvent e)
		{
			if( e.getSource() == søkKnapp || e.getSource() == kundNr || e.getSource() == kundNavn
			|| e.getSource() == kundTelefon || e.getSource() == kundAdresse || e.getSource() == kundPersonnr
			|| e.getSource() == kundPostnr || e.getSource() == kundPostby)
			{
				if(tabbedPane.getSelectedIndex() == 0)
					søkKunde();
			}
			else if(e.getSource() == søkKnapp || e.getSource() == skademeldingsNr || e.getSource() == kundNr
			 || e.getSource() == skadeType || e.getSource() == skadeAdresse || e.getSource() == skadeStatus
			 || e.getSource() == utbetaltBeløp)
			 {
				 if(tabbedPane.getSelectedIndex() == 2)
				 	søkSkade();
			 }
			else if(e.getSource() == søkKnapp || e.getSource() == bilforsikringsnr || e.getSource() == eier
			|| e.getSource() == forsikringBeløp || e.getSource() == bilregNr || e.getSource() == bilType
			|| e.getSource() == bilModell || e.getSource() == bilRegÅr)
			{
				if(tabbedPane2.getSelectedIndex() == 0)
					søkBil();
			}
			else if(e.getSource() == søkKnapp || e.getSource() == båtforsikringsnr || e.getSource() == eier
			|| e.getSource() == forsikringBeløp || e.getSource() == båtregNr || e.getSource() == båtType
			|| e.getSource() == båtModell || e.getSource() == båtÅr || e.getSource() == båtMotor)
			{
				if(tabbedPane2.getSelectedIndex() == 1)
					søkBåt();
			}
			else if(e.getSource() == søkKnapp || e.getSource() == husforsikringsnr || e.getSource() == totalbeløp
			|| e.getSource() == adresse || e.getSource() == byggeÅr || e.getSource() == husType
			|| e.getSource() == husMateriale || e.getSource() == byggBeløp || e.getSource() == innboBeløp)
			{
				if(tabbedPane2.getSelectedIndex() == 2)
					søkHus();
			}
			else if(e.getSource() == søkKnapp || e.getSource() == hytteforsikringsnr || e.getSource() == totalbeløp
			|| e.getSource() == adresse || e.getSource() == byggeÅr || e.getSource() == hytteType
			|| e.getSource() == hytteMateriale || e.getSource() == byggBeløp
			|| e.getSource() == innboBeløp)
			{
				if(tabbedPane2.getSelectedIndex() == 3)
					søkHytte();
			}
			else if(e.getSource() == godkjennKnapp)
				godkjennSkade();
			else if(e.getSource() == avslåKnapp)
				avslåSkade();
			else if(e.getSource() == lagreKnapp)
				endraBonus();
			else if(e.getSource() == minInfoKnapp)
				visMinInfo();
			else if(e.getSource() == oppdaterKnapp)
				søk();
			else if(e.getSource() == statKnapp)
				statistikk();
		}
	}
}
