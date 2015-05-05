import java.util.*;
import java.text.DateFormat;
import javax.swing.*;
import javax.swing.table.*;

public class TModel extends AbstractTableModel
{
	private String[] navn;
	private Object[][] data;
	private final String[] forNavn = {"Forsikringsnr", "Inngått", "Avsluttet", "Kundenr", "Forsikringsbeløp"};
	private final String[] båtNavn = {"Forsikringsnr", "Inngått", "Avsluttet", "Kundenr", "Forsikringsbeløp", "Eier", "Registreringsnr", "Type", "Modell", "Lengde(fot)", "Årsmodell", "Motortype", "Motorstyrke(HK)"};//kolonnenavn for tabellen
	private final String[] bilNavn = {"Forsikringsnr", "Inngått", "Avsluttet", "Kundenr", "Forsikringsbeløp", "Eier", "Registreringsnr", "Type", "Modell", "Registreringsår", "Årlig kjørelengde(km)", "Pris pr km", "Bonus"};
	private final String[] husNavn = {"Forsikringsnr", "Inngått", "Avsluttet", "Kundenr", "Forsikringsbeløp", "Adresse", "Byggeår", "Boligtype", "Byggemateriale", "Standard", "Kvadratmeter", "Forsikringsbeløp(bygning)", "Forsikringsbeløp(innbo)"};
	private final String[] hytteNavn = {"Forsikringsnr", "Inngått", "Avsluttet", "Kundenr", "Forsikringsbeløp", "Adresse", "Byggeår", "Boligtype", "Byggemateriale", "Standard", "Kvadratmeter", "Forsikringsbeløp(bygning)", "Forsikringsbeløp(innbo)"};
	private final String[] ansNavn = {"Ansattnummer", "Personnummer", "Fornavn", "Etternavn", "Telefon", "Ansatt ved"};//kollonnenavn for tabellen
	private final String[] kunNavn = {"Kundenr", "Personnummer", "Fornavn", "Etternavn", "Telefon", "Adresse", "Postnr", "Poststed"};//kolonnenavn for tabellen
	public static final int ANSATT_NR = 0,
							KUNDE_NR = 0, PERSON_NR = 1, FIRSTNAME = 2, LASTNAME = 3, PHONE = 4, ADR = 5, POST_NR = 6, POST_STED = 7, AKTIV = 8,
							PRESCRIPTION_NR = 0, PRINTED = 1, RECIVED = 2, PATIENT = 3, DOCTOR = 4, MED_NAME = 5, MED_CAT = 6, MED_GROUP = 7;
							//nummer på kolonnene
	//private final String[] skaNames = <kolonner for skademeldinger>
	private int searchFor;
	private boolean editable;
	private Ansatt[] ans;
	private Kunde[] kun;
	/*private Forsikring[] fors;
	private BilForsikring[] bil;//arrayer for objecter i tabellen
	private BåtForsikring[] båt;
	private HusForsikring[] hus;
	private HytteForsikring[] hytte;*/
	public TModel()//oppretter en modell for en tom tabell
	{
		navn = new String[0];
		data = new Object[0][0];
		editable = false;
	}
	public TModel(KundeReg reg)//oppretter en model for en pasient tabell
	{
		navn = kunNavn;
		int length = reg.size(), width = navn.length;
		data = new Object[length][width];
		kun = new Kunde[length];
		Iterator<Kunde> iter= reg.iterator();
		Kunde temp;
		for(int i = 0; i < length; i++)
		{
			temp = iter.next();
			int j = 0;
			data[i][j++] = temp.getKundeNr();
			data[i][j++] = temp.getPersonNr();
			data[i][j++] = temp.getFornavn();
			data[i][j++] = temp.getEtternavn();
			data[i][j++] = temp.getTelefonNr();
			data[i][j++] = temp.getAdresse();
			data[i][j++] = temp.getPostnr();
			data[i][j++] = temp.getPoststed();
			//data[i][j++] = temp.getKundeStatus();
			kun[i] = temp;
		}
		editable = true;
		searchFor = AnsattVindu.SØK_KUNDE;
	}
	public TModel(AnsattReg reg)//oppretter en model for en doktor tabell
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
		for(int i = 0; i < length; i++)
		{
			temp = iter.next();
			int j = 0;
			data[i][j++] = temp.getAnsattNr();
			data[i][j++] = temp.getPersonNr();
			data[i][j++] = temp.getFornavn();
			data[i][j++] = temp.getEtternavn();
			data[i][j++] = temp.getTelefonNr();
			data[i][j++] = temp.getAvdeling();
			ans[i] = temp;
		}
		editable = true;
		searchFor = AdminGUI.SØK_ANSATT;
	}

	//Denne skal være en liste med alle forsikringer
	/*public TModel(ForsikringReg reg)
	{
		if(reg == null)
		{
			navn = forNavn;
			data = new Object[0][0];
			return;
		}
		navn = forNavn;
		int length = reg.size(), width = navn.length;
		data = new Object[length][width];
		fors = new Forsikring[length];
		Iterator<Forsikring> iter= reg.iterator();
		Forsikring temp;
		DateFormat df = DateFormat.getDateInstance();
		for(int i = 0; i < length; i++)
		{
			temp = iter.next();
			int j = 0;
			data[i][j++] = temp.getForsikringsnr();
			data[i][j++] = df.format(temp.getInngått().getTime() );
			data[i][j++] = temp.getAvslutta() == null ? "" : df.format(temp.getAvslutta().getTime() );
			data[i][j++] = temp.getKunde().getKundeNr();
			data[i][j++] = temp.getForsikringsbeløp();
			hus[i] = temp;
		}
		editable = false;
		searchFor = AdminGUI.SEARCH_FORSIKRING;
	}

	public TModel(BilForsikringReg reg)
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
			data[i][j++] = temp.getForsikringsnr();
			data[i][j++] = df.format(temp.getInngått().getTime() );
			data[i][j++] = temp.getAvslutta() == null ? "" : df.format(temp.getAvslutta().getTime() );
			data[i][j++] = temp.getKunde().getKundeNr();
			data[i][j++] = temp.getForsikringsbeløp();
			data[i][j++] = temp.getEiernavn();
			data[i][j++] = temp.getRegistreringsnr();
			data[i][j++] = temp.getType();
			data[i][j++] = temp.getModell();
			data[i][j++] = temp.getRegistreringsår();
			data[i][j++] = temp.getKjørelengde();
			data[i][j++] = temp.getPrisPrKm();
			data[i][j++] = temp.getBonus();
			bil[i] = temp;
		}
		editable = false;
		searchFor = AdminGUI.SEARCH_BIL;
	}

	public TModel(BåtForsikringReg reg)
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
			data[i][j++] = temp.getForsikringsnr();
			data[i][j++] = df.format(temp.getInngått().getTime() );
			data[i][j++] = temp.getAvslutta() == null ? "" : df.format(temp.getAvslutta().getTime() );
			data[i][j++] = temp.getKunde().getKundeNr();
			data[i][j++] = temp.getForsikringsbeløp();
			data[i][j++] = temp.getEiernavn();
			data[i][j++] = temp.getRegistreringsnr();
			data[i][j++] = temp.getType();
			data[i][j++] = temp.getModell();
			data[i][j++] = temp.getLengde();
			data[i][j++] = temp.getÅrsmodell();
			data[i][j++] = temp.getMotortype();
			data[i][j++] = temp.getMotorstyrke();
			båt[i] = temp;
		}
		editable = false;
		searchFor = AdminGUI.SEARCH_BÅT;
	}

	public TModel(HusForsikringReg reg)
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
		Iterator<HusForsikring> iter= reg.iterator();
		HusForsikring temp;
		DateFormat df = DateFormat.getDateInstance();
		for(int i = 0; i < length; i++)
		{
			temp = iter.next();
			int j = 0;
			data[i][j++] = temp.getForsikringsnr();
			data[i][j++] = df.format(temp.getInngått().getTime() );
			data[i][j++] = temp.getAvslutta() == null ? "" : df.format(temp.getAvslutta().getTime() );
			data[i][j++] = temp.getKunde().getKundeNr();
			data[i][j++] = temp.getForsikringsbeløp();
			data[i][j++] = temp.getAdresse();
			data[i][j++] = temp.getByggeår();
			data[i][j++] = temp.getBoligtype();
			data[i][j++] = temp.getMateriale();
			data[i][j++] = temp.getStandard();
			data[i][j++] = temp.getKvadratmeter();
			data[i][j++] = temp.getBeløpBygning();
			data[i][j++] = temp.getBeløpInnbo();
			hus[i] = temp;
		}
		editable = false;
		searchFor = AdminGUI.SEARCH_HUS;
	}

	public TModel(HytteForsikringReg reg)
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
			data[i][j++] = temp.getKunde().getKundeNr();
			data[i][j++] = temp.getForsikringsbeløp();
			data[i][j++] = temp.getAdresse();
			data[i][j++] = temp.getByggeår();
			data[i][j++] = temp.getBoligtype();
			data[i][j++] = temp.getMateriale();
			data[i][j++] = temp.getStandard();
			data[i][j++] = temp.getKvadratmeter();
			data[i][j++] = temp.getBeløpBygning();
			data[i][j++] = temp.getBeløpInnbo();
			hytte[i] = temp;
		}
		editable = false;
		searchFor = AdminGUI.SEARCH_HYTTE;
	}*/

	/*
	public TModel(ForsikringReg reg)//oppretter en tabell for resept tabell
	{
		if(reg == null)
		{
			names = preNames;
			data = new Object[0][0];
			return;
		}
		names = preNames;
		int length = reg.size(), width = names.length;
		data = new Object[length][width];
		pre = new Forsikring[length];
		Iterator<Forsikring> iter= reg.iterator();
		Forsikring temp;
		DateFormat df = DateFormat.getDateInstance();
		for(int i = 0; i < length; i++)
		{
			temp = iter.next();
			int j = 0;
			data[i][j++] = temp.getPrescriptionNr();
			data[i][j++] = df.format(temp.getPrinted().getTime() );
			data[i][j++] = temp.getRecived() == null ? "" : df.format(temp.getRecived().getTime() );
			data[i][j++] = temp.getPatient().getlastName();
			data[i][j++] = temp.getDoctor().getlastName();
			data[i][j++] = temp.getMedName();
			data[i][j++] = temp.getCategory();
			data[i][j++] = PrescriptionReg.getGroupString(temp.getGroup() );
			pre[i] = temp;
		}
		editable = false;
		searchFor = AdminGUI.SEARCH_PRESCRIPTION;
	}*/

	/*
	public TModel(ForsikringReg reg)//oppretter en tabell for resept tabell
	{
		if(reg == null)
		{
			names = preNames;
			data = new Object[0][0];
			return;
		}
		names = preNames;
		int length = reg.size(), width = names.length;
		data = new Object[length][width];
		pre = new Forsikring[length];
		Iterator<Forsikring> iter= reg.iterator();
		Forsikring temp;
		DateFormat df = DateFormat.getDateInstance();
		for(int i = 0; i < length; i++)
		{
			temp = iter.next();
			int j = 0;
			data[i][j++] = temp.getPrescriptionNr();
			data[i][j++] = df.format(temp.getPrinted().getTime() );
			data[i][j++] = temp.getRecived() == null ? "" : df.format(temp.getRecived().getTime() );
			data[i][j++] = temp.getPatient().getlastName();
			data[i][j++] = temp.getDoctor().getlastName();
			data[i][j++] = temp.getMedName();
			data[i][j++] = temp.getCategory();
			data[i][j++] = PrescriptionReg.getGroupString(temp.getGroup() );
			pre[i] = temp;
		}
		editable = false;
		searchFor = AdminGUI.SEARCH_PRESCRIPTION;
	}*/

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

	/*public Forsikring[] getForsikringer()
	{
		return fors;
	}


	public SKademelding[] getSkademeldinger()
	{
		return ska;
	}
	*/

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
    public Class<?> getColumnClass(int c)
    {
		return data[0][c].getClass();
	}
	public boolean isCellEditable(int r, int c)
	{
		if(!editable)
			return false;
		if(c == PERSON_NR)
			return false;
		if(c == FIRSTNAME || c == LASTNAME || c == PHONE || c == ADR || c == POST_NR || c == POST_STED )
			return true;
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
		}//end of if(search ansatt)
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
		}//end of if(search kunde)
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
