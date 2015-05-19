import java.util.*;
import java.text.DateFormat;
import javax.swing.*;
import javax.swing.table.*;
import java.text.*;

public class TModel extends AbstractTableModel
{
	private String[] navn;
	private Object[][] data;
	private final String[] forNavn = {"Forsikringsnr", "Inngått", "Avsluttet", "Kundenr", "Forsikringsbeløp", "Aktiv"};
	private final String[] båtNavn = {"Forsikringsnr", "Inngått", "Avsluttet", "Kundenr", "Forsikringsbeløp", "Eier", "Registreringsnr", "Type", "Modell", "Lengde(fot)", "Årsmodell", "Motortype", "Motorstyrke(HK)", "Aktiv"};//kolonnenavn for tabellen
	private final String[] bilNavn = {"Forsikringsnr", "Inngått", "Avsluttet", "Kundenr", "Forsikringsbeløp", "Eier", "Registreringsnr", "Type", "Modell", "Registreringsår", "Årlig kjørelengde(km)", "Pris pr km", "Bonus", "Aktiv"};
	private final String[] husNavn = {"Forsikringsnr", "Inngått", "Avsluttet", "Kundenr", "Forsikringsbeløp(total)", "Adresse", "Byggeår", "Boligtype", "Byggemateriale", "Standard", "Kvadratmeter", "Forsikringsbeløp(bygning)", "Forsikringsbeløp(innbo)", "Aktiv"};
	private final String[] hytteNavn = {"Forsikringsnr", "Inngått", "Avsluttet", "Kundenr", "Forsikringsbeløp(total)", "Adresse", "Byggeår", "Boligtype", "Byggemateriale", "Standard", "Kvadratmeter", "Forsikringsbeløp(bygning)", "Forsikringsbeløp(innbo)", "Aktiv"};
	private final String[] ansNavn = {"Ansattnummer", "Ansatt", "Avsluttet", "Personnummer", "Fornavn", "Etternavn", "Telefon", "Ansatt ved", "Status"};//kollonnenavn for tabellen
	private final String[] kunNavn = {"Kundenr", "Kundestart", "Kundeslutt", "Personnummer", "Fornavn", "Etternavn", "Telefon", "Adresse", "Postnr", "Poststed", "Status", "Totalkunde"};//kolonnenavn for tabellen
	private final String[] skaNavn = {"Skadenr", "Kundenr", "Skadedato", "Skadetype", "Erstatning takst", "Erstatning utbetalt", "Status", "Kundestatus", "Kategori"};
	public static final int ANSATT_NR = 0, ANSATTID = 1, AVSLUTTID = 2,
							KUNDE_NR = 0, KUNDESTART = 1, KUNDESLUTT = 2, PERSON_NR = 3, FIRSTNAME = 4, LASTNAME = 5, PHONE = 6, ADR = 7, POST_NR = 8, POST_STED = 9, STATUS = 10,
							FORSIKRINGS_NR = 0, INNGÅTT = 1, AVSLUTTET = 2, KUNDENS_NR = 3, FORSIKRINGSBELØP = 4, EIER = 5, REG_NR = 6, TYPE = 7, MODELL = 8, BÅTLENGDE = 9, ÅRSMODELL = 10, MOTORTYPE = 11, HESTEKREFT = 12, AKTIV = 13,
																																							REG_ÅR = 9, ÅRLIG_KJØR = 10, PRIS_KM = 11, BONUS = 12,
																						FORSIKRINGSBELØPTOT = 4, ADRESSE = 5, BYGG_ÅR = 6, BOLIG_TYPE = 7, BYGGEMATERIALE = 8, STANDARD = 9, KVADRATMETER = 10, BYGNINGBELØP = 11, INNBOBELØP = 12,
							SKADE_NR = 0, KUNDENR = 1, SKADE_DATO = 2, SKADE_TYPE = 3, TAKST = 4, UTBETALT = 5, SKADE_STATUS = 6, KUNDE_STATUS = 7, KATEGORI = 8;
																								//nummer på kolonnene
	private int searchFor;
	private boolean editable;
	private Ansatt[] ans;
	private Kunde[] kun;
	private Forsikring[] fors;
	private BilForsikring[] bil;//arrayer for objecter i tabellen
	private BåtForsikring[] båt;
	private HusForsikring[] hus;
	private HytteForsikring[] hytte;
	private Skademelding[] ska;

	public TModel()//oppretter en modell for en tom tabell
	{
		navn = new String[0];
		data = new Object[0][0];
		editable = false;
	}

	public TModel(KundeReg reg)//oppretter en model for en kundetabell
	{
		if(reg == null)
		{
			navn = kunNavn;
			data = new Object[0][0];
			return;
		}
		navn = kunNavn;
		int length = reg.size(), width = navn.length;
		data = new Object[length][width];
		kun = new Kunde[length];
		Iterator<Kunde> iter= reg.iterator();
		Kunde temp;
		DateFormat df = DateFormat.getDateInstance();
		for(int i = 0; i < length; i++)
		{
			temp = iter.next();
			int j = 0;
			data[i][j++] = temp.getKundeNr();
			data[i][j++] = df.format(temp.getStarta().getTime() );
			data[i][j++] = temp.getAvslutta() == null ? "" : df.format(temp.getAvslutta().getTime() );
			data[i][j++] = temp.getPersonNr();
			data[i][j++] = temp.getFornavn();
			data[i][j++] = temp.getEtternavn();
			data[i][j++] = temp.getTelefonNr();
			data[i][j++] = temp.getAdresse();
			data[i][j++] = temp.getPostnr();
			data[i][j++] = temp.getPoststed();
			data[i][j++] = temp.getErAktiv();
			data[i][j++] = temp.getTotalkundeStatus();
			kun[i] = temp;
		}
		editable = false;
		searchFor = AnsattVindu.SØK_KUNDE;
	}
	public TModel(AnsattReg reg)//oppretter en model for en ansatttabell
	{
		if(reg == null)
		{
			navn = ansNavn;
			data = new Object[0][0];
			return;
		}
		navn = ansNavn;
		int length = reg.size(), width = navn.length;
		data = new Object[length][width];
		ans = new Ansatt[length];
		Iterator<Ansatt> iter= reg.iterator();
		Ansatt temp;
		DateFormat df = DateFormat.getDateInstance();
		for(int i = 0; i < length; i++)
		{
			temp = iter.next();
			int j = 0;
			data[i][j++] = temp.getAnsattNr();
			data[i][j++] = df.format(temp.getStarta().getTime() );
			data[i][j++] = temp.getAvslutta() == null ? "" : df.format(temp.getAvslutta().getTime() );
			data[i][j++] = temp.getPersonNr();
			data[i][j++] = temp.getFornavn();
			data[i][j++] = temp.getEtternavn();
			data[i][j++] = temp.getTelefonNr();
			data[i][j++] = temp.getAvdeling();
			data[i][j++] = temp.getErAktiv();
			ans[i] = temp;
		}
		editable = false;
		searchFor = AdminGUI.SØK_ANSATT;
	}

	public TModel(BilForsikringsReg reg)//oppretter en model for en bilforsikringstabell
	{
		if(reg == null)
		{
			navn = bilNavn;
			data = new Object[0][0];
			return;
		}
		navn = bilNavn;
		int length = reg.size(), width = navn.length;
		data = new Object[length][width];
		bil = new BilForsikring[length];
		Iterator<BilForsikring> iter= reg.iterator();
		BilForsikring temp;
		DateFormat df = DateFormat.getDateInstance();
		for(int i = 0; i < length; i++)
		{
			temp = iter.next();
			int j = 0;
			data[i][j++] = temp.getForsikringsNr();
			data[i][j++] = df.format(temp.getInngått().getTime() );
			data[i][j++] = temp.getAvslutta() == null ? "" : df.format(temp.getAvslutta().getTime() );
			data[i][j++] = temp.getKundeNr();
			data[i][j++] = temp.getForsikringsbeløp();
			data[i][j++] = temp.getEiernavn();
			data[i][j++] = temp.getRegistreringsnr();
			data[i][j++] = temp.getType();
			data[i][j++] = temp.getModell();
			data[i][j++] = temp.getRegistreringsår();
			data[i][j++] = temp.getKjørelengde();
			data[i][j++] = temp.getPrisPrKm();
			data[i][j++] = temp.getBonus();
			data[i][j++] = temp.getErAktiv();
			bil[i] = temp;
		}
		editable = false;
		searchFor = AnsattVindu.SØK_BIL;
	}

	public TModel(BåtForsikringsReg reg)//oppretter en model for en båtforsikringstabell
	{
		if(reg == null)
		{
			navn = båtNavn;
			data = new Object[0][0];
			return;
		}
		navn = båtNavn;
		int length = reg.size(), width = navn.length;
		data = new Object[length][width];
		båt = new BåtForsikring[length];
		Iterator<BåtForsikring> iter= reg.iterator();
		BåtForsikring temp;
		DateFormat df = DateFormat.getDateInstance();
		for(int i = 0; i < length; i++)
		{
			temp = iter.next();
			int j = 0;
			data[i][j++] = temp.getForsikringsNr();
			data[i][j++] = df.format(temp.getInngått().getTime() );
			data[i][j++] = temp.getAvslutta() == null ? "" : df.format(temp.getAvslutta().getTime() );
			data[i][j++] = temp.getKundeNr();
			data[i][j++] = temp.getForsikringsbeløp();
			data[i][j++] = temp.getEiernavn();
			data[i][j++] = temp.getRegistreringsnr();
			data[i][j++] = temp.getType();
			data[i][j++] = temp.getModell();
			data[i][j++] = temp.getBåtlengde();
			data[i][j++] = temp.getÅrsmodell();
			data[i][j++] = temp.getMotortype();
			data[i][j++] = temp.getMotorstyrke();
			data[i][j++] = temp.getErAktiv();
			båt[i] = temp;
		}
		editable = false;
		searchFor = AnsattVindu.SØK_BÅT;
	}

	public TModel(HusForsikringsReg reg)//oppretter en model for en husforsikringstabell
	{
		if(reg == null)
		{
			navn = husNavn;
			data = new Object[0][0];
			return;
		}
		navn = husNavn;
		int length = reg.size(), width = navn.length;
		data = new Object[length][width];
		hus = new HusForsikring[length];
		Iterator<HusForsikring> iter = reg.iterator();
		HusForsikring temp;
		DateFormat df = DateFormat.getDateInstance();
		for(int i = 0; i < length; i++)
		{
			temp = iter.next();
			int j = 0;
			data[i][j++] = temp.getForsikringsnr();
			data[i][j++] = df.format(temp.getInngått().getTime() );
			data[i][j++] = temp.getAvslutta() == null ? "" : df.format(temp.getAvslutta().getTime() );
			data[i][j++] = temp.getKundeNr();
			data[i][j++] = temp.getBeløpTotal();
			data[i][j++] = temp.getAdresse();
			data[i][j++] = temp.getByggeår();
			data[i][j++] = temp.getBoligtype();
			data[i][j++] = temp.getMateriale();
			data[i][j++] = temp.getStandard();
			data[i][j++] = temp.getKvadratmeter();
			data[i][j++] = temp.getBeløpBygg();
			data[i][j++] = temp.getBeløpInnbo();
			data[i][j++] = temp.getErAktiv();
			hus[i] = temp;
		}
		editable = false;
		searchFor = AnsattVindu.SØK_HUS;
	}

	public TModel(HytteForsikringsReg reg)//oppretter en model for en hytteforsikringstabell
	{
		if(reg == null)
		{
			navn = hytteNavn;
			data = new Object[0][0];
			return;
		}
		navn = hytteNavn;
		int length = reg.size(), width = navn.length;
		data = new Object[length][width];
		hytte = new HytteForsikring[length];
		Iterator<HytteForsikring> iter= reg.iterator();
		HytteForsikring temp;
		DateFormat df = DateFormat.getDateInstance();
		for(int i = 0; i < length; i++)
		{
			temp = iter.next();
			int j = 0;
			data[i][j++] = temp.getForsikringsnr();
			data[i][j++] = df.format(temp.getInngått().getTime() );
			data[i][j++] = temp.getAvslutta() == null ? "" : df.format(temp.getAvslutta().getTime() );
			data[i][j++] = temp.getKundeNr();
			data[i][j++] = temp.getBeløpTotal();
			data[i][j++] = temp.getAdresse();
			data[i][j++] = temp.getByggeår();
			data[i][j++] = temp.getBoligtype();
			data[i][j++] = temp.getMateriale();
			data[i][j++] = temp.getStandard();
			data[i][j++] = temp.getKvadratmeter();
			data[i][j++] = temp.getBeløpBygg();
			data[i][j++] = temp.getBeløpInnbo();
			data[i][j++] = temp.getErAktiv();
			hytte[i] = temp;
		}
		editable = false;
		searchFor = AnsattVindu.SØK_HYTTE;
	}

	public TModel(SkademeldingReg reg)//oppretter en model for en skademeldingstabell
	{
		if(reg == null)
		{
			navn = skaNavn;
			data = new Object[0][0];
			return;
		}
		navn = skaNavn;
		int length = reg.size(), width = navn.length;
		data = new Object[length][width];
		ska = new Skademelding[length];
		Iterator<Skademelding> iter= reg.iterator();
		Skademelding temp;
		DateFormat df = DateFormat.getDateInstance();
		Locale norsk = new Locale("no", "NO");
		NumberFormat nf = NumberFormat.getCurrencyInstance(norsk);
		for(int i = 0; i < length; i++)
		{
			temp = iter.next();
			int j = 0;
			data[i][j++] = temp.getSkadeNr();
			data[i][j++] = temp.getKundeNr();
			data[i][j++] = df.format(temp.getDato().getTime() );
			data[i][j++] = temp.getSkadeType();
			data[i][j++] = nf.format(temp.getTakst());
			data[i][j++] = temp.getUtbetalt() == 0 ? "" : nf.format(temp.getUtbetalt() );
			data[i][j++] = temp.getStatus();
			data[i][j++] = temp.getKundeAktiv();
			data[i][j++] = temp.getKategori();
			ska[i] = temp;
		}
		editable = false;
		searchFor = AnsattVindu.SØK_SKADE;
	}

	public int getSearchFor()
	{
		return searchFor;
	}

	public Ansatt[] getAnsatte()
	{
		return ans;
	}

	public Kunde[] getKunder()
	{
		return kun;
	}

	public Forsikring[] getForsikringer()
	{
		return fors;
	}

	public Skademelding[] getSkademeldinger()
	{
		return ska;
	}

	public String getColumnName(int c)
	{
		return navn[c];
	}
	public int getRowCount()
	{
		return data.length;
	}
	public int getColumnCount()
	{
		return navn.length;
	}
	public Object getValueAt(int r, int c)
	{
		return data[r][c];
	}
    /*
    public Class<?> getColumnClass(int c)
    {
		return data[0][c].getClass();
	}
	*/
	public boolean isCellEditable(int r, int c)
	{
		return false;
	}

	public void setValueAt(Object value, int r, int c)
	{
		data[r][c] = value;
	}
	public void setCellEditable(boolean b)
	{
		editable = false;
	}
	public void saveChanges()//metode lagrer dataen i tabellen slik den står
	{
		if(searchFor == AdminGUI.SØK_ANSATT)
		{
			for(int r = 0; r < getRowCount(); r++)
				for(int c = 0; c < getColumnCount(); c++)
				{
					if(c == FIRSTNAME)
						ans[r].setFornavn( (String)getValueAt(r, c) );
					else if(c == LASTNAME)
						ans[r].setEtternavn( (String)getValueAt(r, c) );
					else if(c == PHONE)
						ans[r].setTelefonNr( (String)getValueAt(r, c) );
					else if(c == ADR)
						ans[r].setAvdeling( (String)getValueAt(r, c) );
				}
		}//end of if(søk ansatt)
		else if(searchFor == AdminGUI.SØK_KUNDE)
		{
			for(int r = 0; r < getRowCount(); r++)
				for(int c = 0; c < getColumnCount(); c++)
				{
					if(c == FIRSTNAME)
						kun[r].setFornavn( (String)getValueAt(r, c) );
					else if(c == LASTNAME)
						kun[r].setEtternavn( (String)getValueAt(r, c) );
					else if(c == PHONE)
						kun[r].setTelefonNr( (String)getValueAt(r, c) );
					else if(c == ADR)
						kun[r].setAdresse( (String)getValueAt(r, c) );
					else if(c == POST_NR)
						kun[r].setPostnr( (String)getValueAt(r, c) );
					else if(c == POST_STED)
						kun[r].setPoststed( (String)getValueAt(r, c) );
				}
		}//end of if(søk kunde)
	}

	public void setTableCellEditor(JTable table)//setter JTextField som editor for String felter som kan endres
	{
		for(int i = 0; i < table.getRowCount(); i++)
		{
			TableCellEditor textEdit = new DefaultCellEditor(new JTextField() );
			TableColumnModel columnModel = table.getColumnModel();
			columnModel.getColumn(FIRSTNAME).setCellEditor(textEdit);
			columnModel.getColumn(LASTNAME).setCellEditor(textEdit);
			columnModel.getColumn(PHONE).setCellEditor(textEdit);
			columnModel.getColumn(ADR).setCellEditor(textEdit);
			if( table.getRowCount() == 9 )
			{
				columnModel.getColumn(POST_NR).setCellEditor(textEdit);
				columnModel.getColumn(POST_STED).setCellEditor(textEdit);
			}
			/*
			else if( table.getRowCount() == 12 )
			{
				<forsikringsfeltene>
			}*/
		}//end of for(row)
	}//end of method
}//end of class
