import java.io.Serializable;
import java.text.DateFormat;
import java.util.Calendar;

public abstract class Forsikring1 implements Serializable
{
	/*
	Gjør denne forsikring1 til superklasse for andre forsikringer
	*/

	private static final long serialVersionUID = 42L;
	public static final int TYPE_BIL = 1, TYPE_BÅT = 2, TYPE_HUS = 3, TYPE_HYTTE = 4,
			bilKat = 20000, båtKat = 30000, husKat = 40000, hytKat = 50000;
	private static int nyNr, bilnr = 20000, båtnr = 30000, husnr = 40000, hytnr = 50000;
	//private int forsikringNr;
	private Calendar inngått;
	private Calendar avslutta;
	private Kunde kunde;
	private String kundepersonnr;
	//private Ansatt ansatt;
	//private String medName;
	//private String category;
	//private int group;
	protected String forsikringsinfo, ja = "Ja", nei = "Nei";
	protected String eiernavn, registreringsnr, type, modell;
	//protected int forsikringsbeløp;
	//private int registreringsår, kjørelengde, prisPrKm, hjelpenr, kategori;
	private int hjelpenr, kategori;
	private boolean aktiv;

	public Forsikring1(String kunpnr, String info, int kat)
	{
		kundepersonnr = kunpnr;
		forsikringsinfo = info;
		kategori = kat;
		setForsikringsNr(kat);	//denne er usikker
		aktiv = true;
		inngått = Calendar.getInstance();
	}

	public void setForsikringsNr(int kat)
	{
		if(kat == TYPE_BIL)
		{
			hjelpenr = bilnr;
			bilnr++;
		}
		else if(kat == TYPE_BÅT)
		{
			hjelpenr = båtnr;
			båtnr++;
		}
		else if(kat == TYPE_HUS)
		{
			hjelpenr = husnr;
			husnr++;
		}
		else if(kat == TYPE_HYTTE)
		{
			hjelpenr = hytnr;
			hytnr++;
		}
	}

	public String getKundeNr()
	{
		return kundepersonnr;
	}

	public int getForsikringsNr()
	{
		return hjelpenr;
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

	public String getForsikringsInfo()
	{
		return forsikringsinfo;
	}

	public void setForsikringsInfo(String info)
	{
		forsikringsinfo = info;
	}

	public boolean getAktiv()
	{
		return aktiv;
	}

	public String getErAktiv()
	{
		if(aktiv)
			return ja;
		else
			return nei;
	}

	public void setAktiv(boolean ok)
	{
		aktiv = ok;
	}


}
class BilForsikring1 extends Forsikring1
{
	private int forsikringNr;
	private Kunde kunde;
	private String kundepersonnr;
	private Ansatt ansatt;
	private String medName;
	private String category;
	private int group;
	protected String forsikringsinfo, ja = "Ja", nei = "Nei";
	protected String eiernavn, registreringsnr, type, modell;
	protected int forsikringsbeløp;
	private int registreringsår, kjørelengde, prisPrKm, hjelpenr, kategori;
	private static int nestenr = 20000;

	public BilForsikring1(String kunpnr, String info, int kat, String eier, String regnr, String merke, String mod, int regår, int kmårlig, int beløp)
	{
		super(kunpnr, info, kat);
		eiernavn = eier;
		registreringsnr = regnr;
		type = merke;
		modell = mod;
		registreringsår = regår;
		kjørelengde = kmårlig;
		forsikringsbeløp = beløp;
		hjelpenr = nestenr;
		nestenr++;
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

	public int getForsikringsID()
	{
		return hjelpenr;
	}

	public int getForsikringsNr()
	{
		return hjelpenr;
	}

	public int getBonus()
	{
		return 25;
	}


	public static int getNrNå()
	{
		return nestenr;
	}

	public static void setNrNå(int nr)
	{
		if(nr>nestenr)
			nestenr = nr;
	}

	public String toString()
	{
		DateFormat df = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.MEDIUM);
		String info = "\nForsikringsnr: " + getForsikringsNr() + "\nKundenr: " + getKundeNr() + "\nForsikringsbeløp: "
			+ getForsikringsbeløp() + "\nEier: " + getEiernavn() + "\nRegistreringsnr: " + getRegistreringsnr()
			+ "\nType: " + getType() + "\nModell: " + getModell() + "\nRegistreringsår: " + getRegistreringsår()
			+ "\nÅrlig kjørelengde: " + getKjørelengde();
		if(erAvslutta())
			info += "\nAvsluttet: " + df.format(getAvslutta().getTime());
		info += "\nForsikringsinfo:\n" + getForsikringsinfo() + "\n----------------------------------------";;
		return info;
	}


}//slutt på superklasse Forsikring


class BåtForsikring1 extends Forsikring1 implements Serializable
{
	static final long serialVersionUID = 42L;
	private String eiernavn, registreringsnr, båttype, modell, båtfornr, motortype;
	private int forsikringsbeløp, årsmodell, båtlengde, motorstyrke, hjelpenr;
	private static int nestenr = 30000;
	private boolean aktiv;

	public BåtForsikring1(String kunpnr, int beløp, String info, String eier, String regnr, String typ, String mod, int modellår, int lengde, String motortyp, int styrke, int kat)
	{
		super(kunpnr, info, kat);
		forsikringsbeløp = beløp;
		eiernavn = eier;
		registreringsnr = regnr;
		båttype = typ;
		modell = mod;
		årsmodell = modellår;
		båtlengde = lengde;
		motortype = motortyp;
		motorstyrke = styrke;
		hjelpenr = nestenr;
		nestenr++;
		aktiv = true;
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

	public int getÅrsmodell()
	{
		return årsmodell;
	}

	public void setÅrsmodell(int modellår)
	{
		årsmodell = modellår;
	}

	public int getBåtlengde()
	{
		return båtlengde;
	}

	public void setBåtlengde(int lengde)
	{
		båtlengde = lengde;
	}

	public String getMotortype()
	{
		return motortype;
	}

	public void setMotortype(String motortyp)
	{
		motortype = motortyp;
	}

	public int getMotorstyrke()
	{
		return motorstyrke;
	}

	public void setMotorstyrke(int styrke)
	{
		motorstyrke = styrke;
	}

	public int getForsikringsID()
	{
		return hjelpenr;
	}

	public int getForsikringsNr()
	{
		return hjelpenr;
	}

	public boolean getAktiv()
	{
		return aktiv;
	}

	public String getErAktiv()
	{
		if(aktiv)
			return ja;
		else
			return nei;
	}

	public void setAktiv(boolean ok)
	{
		aktiv = ok;
	}

	public static int getNrNå()
	{
		return nestenr;
	}

	public static void setNrNå(int nr)
	{
		if(nr>nestenr)
			nestenr = nr;
	}

	public String toString()
	{
		DateFormat df = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.MEDIUM);
		String info = "\nForsikringsnr: " + getForsikringsNr() + "\nÅrsmodell: " + getÅrsmodell() + "\nBåtlengde(fot): " + getBåtlengde() + "\nMotortype: " + getMotortype() + "\nMotorstyrke(HK): " + getMotorstyrke();
		if(erAvslutta())
			info += "\nAvsluttet: " + df.format(getAvslutta().getTime());
		info += "\nForsikringsinfo:\n" + getForsikringsinfo() + "\n----------------------------------------";
		return info;
	}
}//slutt på klasse BåtForsikring


class HusForsikring1 extends Forsikring1 implements Serializable
{
	static final long serialVersionUID = 42L;
	private String husfornr, adresse, boligtype, byggemateriale, standard;
	private int hjelpenr, beløpBygg, beløpInn, byggeår, kvadratmeter;
	private static int nestenr = 40000;
	private boolean aktiv;

	public HusForsikring1(String kunpnr, int beløpb, int beløpi, String info, String adr, String type, String mat, String stand, int år, int meter, int kat)
	{
		super(kunpnr, info, kat);
		beløpBygg = beløpb;
		beløpInn = beløpi;
		adresse = adr;
		boligtype = type;
		byggemateriale = mat;
		standard = stand;
		byggeår = år;
		kvadratmeter = meter;
		hjelpenr = nestenr;
		nestenr++;
		aktiv = true;
	}

	public int getForsikringsID()
	{
		return hjelpenr;
	}

	public int getForsikringsnr()
	{
		return hjelpenr;
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

	public int getBeløpTotal()
	{
		return beløpBygg + beløpInn;
	}

	public void setBeløpBygg(int beløpb)
	{
		beløpBygg = beløpb;
	}

	public void setBeløpInnbo(int beløpi)
	{
		beløpInn = beløpi;
	}

	public void setByggeår(int år)
	{
		byggeår = år;
	}

	public void setAdresse(String adr)
	{
		adresse = adr;
	}

	public void setBoligtype(String type)
	{
		boligtype = type;
	}

	public void setByggemateriale(String mat)
	{
		byggemateriale = mat;
	}

	public void setStandard(String stand)
	{
		standard = stand;
	}

	public void setKvadratmeter(int meter)
	{
		kvadratmeter = meter;
	}

	public boolean getAktiv()
	{
		return aktiv;
	}

	public String getErAktiv()
	{
		if(aktiv)
			return ja;
		else
			return nei;
	}

	public void setAktiv(boolean ok)
	{
		aktiv = ok;
	}

	public static int getNrNå()
	{
		return nestenr;
	}

	public static void setNrNå(int nr)
	{
		if(nr>nestenr)
			nestenr = nr;
	}

	public String toString()
	{
		DateFormat df = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.MEDIUM);
		String info = "\nForsikringsnr: " + getForsikringsnr() + "\nAdresse: " + getAdresse() + "\nBoligtype: " + getBoligtype() +
			"\nByggemateriale: " + getMateriale() + "\nStandard: " + getStandard() + "\nByggeår: " + getByggeår() +
			"\nArealstørrelse: " + getKvadratmeter() + "\nForsikringsbeløp bygg: " + getBeløpBygg() + "\nForsikringsbeløp innbo: " + getBeløpInnbo();
		if(erAvslutta())
			info += "\nAvsluttet: " + df.format(getAvslutta().getTime());
		info += "\nForsikringsinfo:\n" + getForsikringsinfo() + "\n----------------------------------------";;
		return info;
	}
}//slutt på klasse HusForsikring


class HytteForsikring1 extends Forsikring1 implements Serializable
{
	static final long serialVersionUID = 42L;
	private String hyttefornr, adresse, boligtype, byggemateriale, standard;
	private int hjelpenr, beløpBygg, beløpInn, byggeår, kvadratmeter;
	private static int nestenr = 50000;
	private boolean aktiv;

	public HytteForsikring1(String kunpnr, int beløpb, int beløpi, String info, String adr, String type, String mat, String stand, int år, int meter, int kat)
	{
		super(kunpnr, info, kat);
		beløpBygg = beløpb;
		beløpInn = beløpi;
		adresse = adr;
		boligtype = type;
		byggemateriale = mat;
		standard = stand;
		byggeår = år;
		kvadratmeter = meter;
		hjelpenr = nestenr;
		nestenr++;
		aktiv = true;
	}

	public int getForsikringsID()
	{
		return hjelpenr;
	}

	public int getForsikringsnr()
	{
		return hjelpenr;
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

	public int getBeløpTotal()
	{
		return beløpBygg + beløpInn;
	}

	public void setBeløpBygg(int beløpb)
	{
		beløpBygg = beløpb;
	}

	public void setBeløpInnbo(int beløpi)
	{
		beløpInn = beløpi;
	}

	public void setByggeår(int år)
	{
		byggeår = år;
	}

	public void setAdresse(String adr)
	{
		adresse = adr;
	}

	public void setBoligtype(String type)
	{
		boligtype = type;
	}

	public void setByggemateriale(String mat)
	{
		byggemateriale = mat;
	}

	public void setStandard(String stand)
	{
		standard = stand;
	}

	public void setKvadratmeter(int meter)
	{
		kvadratmeter = meter;
	}

	public boolean getAktiv()
	{
		return aktiv;
	}

	public String getErAktiv()
	{
		if(aktiv)
			return ja;
		else
			return nei;
	}

	public void setAktiv(boolean ok)
	{
		aktiv = ok;
	}

	public static int getNrNå()
	{
		return nestenr;
	}

	public static void setNrNå(int nr)
	{
		if(nr>nestenr)
			nestenr = nr;
	}

	public String toString()
	{
		DateFormat df = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.MEDIUM);
		String info = "\nForsikringsnr: " + getForsikringsnr() + "\nAdresse: " + getAdresse() + "\nBoligtype: " + getBoligtype() +
			"\nByggemateriale: " + getMateriale() + "\nStandard: " + getStandard() + "\nByggeår: " + getByggeår() +
			"\nArealstørrelse: " + getKvadratmeter() + "\nForsikringsbeløp bygg: " + getBeløpBygg() + "\nForsikringsbeløp innbo: " + getBeløpInnbo();
		if(erAvslutta())
			info += "\nAvsluttet: " + df.format(getAvslutta().getTime());
		info += "\nForsikringsinfo:\n" + getForsikringsinfo() + "\n----------------------------------------";;
		return info;
	}
}//slutt på klasse HytteForsikring
