import java.io.Serializable;
import java.text.DateFormat;
import java.util.Calendar;



public abstract class Forsikring implements Serializable
{
	private static final long serialVersionUID = 42L;
	public static final int TYPE_BIL = 1, TYPE_BÅT = 2, TYPE_HUS = 3, TYPE_HYTTE = 4;// TYPE_INNBO1 = 5, TYPE_INNBO2 = 6;
	private static int nyNr;
	private int forsikringNr;
	private Calendar inngått;
	private Calendar avslutta;
	private Kunde kunde;
	private Ansatt ansatt;
	private String medName;
	private String category;
	private int group;
	private String forsikringsinfo;

	protected Forsikring(Kunde kun, String info)
	{
		kunde = kun;
		forsikringsinfo = info;
		inngått = Calendar.getInstance();
	}

	/*public Forsikring(Kunde pat, Ansatt doc, String med, String dos, int gro, String cat, String use)
	{
		forsikringNr = ++newNr;
		inngått = Calendar.getInstance();
		patient = pat;
		doctor = doc;
		medName = med;
		dosage = dos;
		group = gro;
		category = cat;
		useage = use;
	}*/

	public void setAvslutta()
	{
		avslutta = Calendar.getInstance();
	}

	public Kunde getKunde()
	{
		return kunde;
	}

	public String getKundenr()
	{
		return kunde.getKundeNr();
	}

	public Ansatt getAnsatt()
	{
		return ansatt;
	}

	public String getForsikringsinfo()
	{
		return forsikringsinfo;
	}

	public Calendar getInngått()
	{
		return inngått;
	}

	public Calendar getAvslutta()
	{
		return avslutta;
	}

	public boolean erAvslutta()
	{
		if(avslutta == null)
			return false;
		return true;
	}

	/*public String toString()
	{
		DateFormat df = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.MEDIUM);
		String out = "\nReseptnummer: " + prescriptionNr + "\nSkrevet ut: " + df.format(printed.getTime() ) +
		"\nPasient: " + patient.getFornavn() + " " + patient.getEtternavn() +
		"\nLege: " + doctor.getFornavn() + " " + doctor.getEtternavn() +
		//"\nMedikament navn: " + medName + "\nGruppe: " + ForsikringsReg.getGroupString(group) + "\nKategori: " + category +
		"\nDosering: " + dosage + "\nAnvisning: " + useage + "\nResept hentet: ";
		if(recived != null)
		out += df.format(recived.getTime() );
		else
		out += "Ikke hentet";
		return out;
	}*/
}

abstract class Fartøy extends Forsikring implements Serializable
{
	static final long serialVersionUID = 42L;
	protected String eiernavn, registreringsnr, type, modell;
	protected int forsikringsbeløp;

	protected Fartøy(Kunde kun, int beløp, String info, String eier, String regnr, String typ, String mod)
	{
		super(kun, info);
		forsikringsbeløp = beløp;
		eiernavn = eier;
		registreringsnr = regnr;
		type = typ;
		modell = mod;
	}

	public int getForsikringsbeløp()
	{
		return forsikringsbeløp;
	}

	public String getEiernavn()
	{
		return eiernavn;
	}

	public void setEiernavn(String eier)
	{
		eiernavn = eier;
	}

	public String getRegistreringsnr()
	{
		return registreringsnr;
	}

	public void setRegistreringsnr(String regnr)
	{
		registreringsnr = regnr;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String typ)
	{
		type = typ;
	}

	public String getModell()
	{
		return modell;
	}

	public void setModell(String mod)
	{
		modell = mod;
	}

	public String toString()
	{
		String info = "\nKundenr: " + getKundenr() + "\nForsikringsbeløp: " + getForsikringsbeløp() + "\nEier: " + getEiernavn() + "\nRegistreringsnr: " + getRegistreringsnr() + "\nType: " + getType() + "\nModell: " + getModell();
		return info;
	}
}

abstract class Bolig extends Forsikring implements Serializable
{
	static final long serialVersionUID = 42L;
	private String adresse, boligtype, byggemateriale, standard;
	private int byggeår, kvadratmeter, beløpBygg, beløpInn;

	protected Bolig(Kunde kun, int beløpb, int beløpi, String info, String adr, String type, String mat, String stand, int år, int meter)
	{
		super(kun, info);
		adresse = adr;
		boligtype = type;
		byggemateriale = mat;
		standard = stand;
		byggeår = år;
		kvadratmeter = meter;
		beløpBygg = beløpb;
		beløpInn = beløpi;
	}

	public String getAdresse()
	{
		return adresse;
	}

	public String getBoligtype()
	{
		return boligtype;
	}

	public String getMateriale()
	{
		return byggemateriale;
	}

	public String getStandard()
	{
		return standard;
	}

	public int getByggeår()
	{
		return byggeår;
	}

	public int getKvadratmeter()
	{
		return kvadratmeter;
	}

	public int getBeløpBygg()
	{
		return beløpBygg;
	}

	public int getBeløpInnbo()
	{
		return beløpInn;
	}

	public String toString()
	{
		String info = "\nKundenr: " + getKundenr() + "\nAdresse: " + getAdresse() + "\nByggemateriale: " + getMateriale() + "\nStandard: " + getStandard() + "\nByggeår: " + getByggeår() + "\nAntall kvadratmeter: " + getKvadratmeter() + "\nForsikringsbeløp: \nBygning: " + getBeløpBygg() + "\nInnbo: " + getBeløpInnbo();
		return info;
	}

}



class BilForsikring extends Fartøy implements Serializable
{
	static final long serialVersionUID = 42L;
	private String eiernavn, registreringsnr, biltype, modell, bilfornr;
	private int registreringsår, kjørelengde, prisPrKm, hjelpenr;
	private static int nestenr = 1;

	public BilForsikring(Kunde kun, int beløp, String info, String eier, String regnr, String typ, String mod, int regår, int lengde, int prkm)
	{
		super(kun, beløp, info, eier, regnr, typ, mod);
		registreringsår = regår;
		kjørelengde = lengde;
		prisPrKm = prkm;
		hjelpenr = nestenr;
		nestenr++;
	}

	public int getRegistreringsår()
	{
		return registreringsår;
	}

	public void setRegistreringsår(int regår)
	{
		registreringsår = regår;
	}

	public int getKjørelengde()
	{
		return kjørelengde;
	}

	public void setKjørelengde(int lengde)
	{
		kjørelengde = lengde;
	}

	public int getPrisPrKm()
	{
		return prisPrKm;
	}

	public void setPrisPrKm(int prkm)
	{
		prisPrKm = prkm;
	}

	public String getForsikringsnr()
	{
		bilfornr = Integer.toString(hjelpenr);
		return "BI" + bilfornr;
	}

	public String toString()
	{
		DateFormat df = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.MEDIUM);
		String info = "\nForsikringsnr: " + getForsikringsnr() + super.toString() + "\nRegistreringsår: " + getRegistreringsår() + "\nÅrlig kjørelengde: " + getKjørelengde() + "\nPris per KM: " + getPrisPrKm();
		if(erAvslutta())
			info += "\nAvsluttet: " + df.format(getAvslutta().getTime());
		info += "\nForsikringsinfo:\n" + getForsikringsinfo();
		return info;
	}
}

class BåtForsikring extends Fartøy implements Serializable
{
	<samme som på bil>
}

class HusForsikring extends Bolig implements Serializable
{
	<hus-greier>
}

class HytteForsikring extends Bolig implements Serializable
{
	<samme som hus>
}
