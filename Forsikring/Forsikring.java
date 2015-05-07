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

	public String getForsikringsInfo()
	{
		return forsikringsinfo;
	}

	public void setForsikringsInfo(String info)
	{
		forsikringsinfo = info;
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
}//slutt på superklasse Forsikring

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
}//slutt på subklasse Fartøy

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

	public String toString()
	{
		String info = "\nKundenr: " + getKundenr() + "\nAdresse: " + getAdresse() + "\nByggemateriale: " + getMateriale() + "\nStandard: " + getStandard() + "\nByggeår: " + getByggeår() + "\nAntall kvadratmeter: " + getKvadratmeter() + "\nForsikringsbeløp: \nBygning: " + getBeløpBygg() + "\nInnbo: " + getBeløpInnbo();
		return info;
	}

}//slutt på subklasse Bolig


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

	public int getForsikringsID()
	{
		return hjelpenr;
	}

	public String getForsikringsNr()
	{
		bilfornr = Integer.toString(hjelpenr);
		return "BI" + bilfornr;
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
		String info = "\nForsikringsnr: " + getForsikringsNr() + super.toString() + "\nRegistreringsår: " + getRegistreringsår() + "\nÅrlig kjørelengde: " + getKjørelengde() + "\nPris per KM: " + getPrisPrKm();
		if(erAvslutta())
			info += "\nAvsluttet: " + df.format(getAvslutta().getTime());
		info += "\nForsikringsinfo:\n" + getForsikringsinfo();
		return info;
	}
}//slutt på klasse BilForsikring

class BåtForsikring extends Fartøy implements Serializable
{
	static final long serialVersionUID = 42L;
	private String eiernavn, registreringsnr, biltype, modell, båtfornr, motortype;
	private int årsmodell, båtlengde, motorstyrke, hjelpenr;
	private static int nestenr = 1;

	public BåtForsikring(Kunde kun, int beløp, String info, String eier, String regnr, String typ, String mod, int modellår, int lengde, String motortyp, int styrke)
	{
		super(kun, beløp, info, eier, regnr, typ, mod);
		årsmodell = modellår;
		båtlengde = lengde;
		motortype = motortyp;
		motorstyrke = styrke;
		hjelpenr = nestenr;
		nestenr++;
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

	public String getForsikringsnr()
	{
		båtfornr = Integer.toString(hjelpenr);
		return "BA" + båtfornr;
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
		String info = "\nForsikringsnr: " + getForsikringsnr() + super.toString() + "\nÅrsmodell: " + getÅrsmodell() + "\nBåtlengde(fot): " + getBåtlengde() + "\nMotortype: " + getMotortype() + "\nMotorstyrke(HK): " + getMotorstyrke();
		if(erAvslutta())
			info += "\nAvsluttet: " + df.format(getAvslutta().getTime());
		info += "\nForsikringsinfo:\n" + getForsikringsinfo();
		return info;
	}
}//slutt på klasse BåtForsikring


class HusForsikring extends Bolig implements Serializable
{
	static final long serialVersionUID = 42L;
	private String husfornr;
	private int hjelpenr;
	private static int nestenr = 1;

	public HusForsikring(Kunde kun, int beløpb, int beløpi, String info, String adr, String type, String mat, String stand, int år, int meter)
	{
		super(kun, beløpb, beløpi, info, adr, type, mat, stand, år, meter);
		hjelpenr = nestenr;
		nestenr++;
	}

	public int getForsikringsID()
	{
		return hjelpenr;
	}

	public String getForsikringsnr()
	{
		husfornr = Integer.toString(hjelpenr);
		return "HU" + husfornr;
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
		String info = "\nForsikringsnr: " + getForsikringsnr() + super.toString();
		if(erAvslutta())
			info += "\nAvsluttet: " + df.format(getAvslutta().getTime());
		info += "\nForsikringsinfo:\n" + getForsikringsinfo();
		return info;
	}
}//slutt på klasse HusForsikring


class HytteForsikring extends Bolig implements Serializable
{
	static final long serialVersionUID = 42L;
	private String hyttefornr;
	private int hjelpenr;
	private static int nestenr = 1;

	public HytteForsikring(Kunde kun, int beløpb, int beløpi, String info, String adr, String type, String mat, String stand, int år, int meter)
	{
		super(kun, beløpb, beløpi, info, adr, type, mat, stand, år, meter);
		hjelpenr = nestenr;
		nestenr++;
	}

	public int getForsikringsID()
	{
		return hjelpenr;
	}

	public String getForsikringsnr()
	{
		hyttefornr = Integer.toString(hjelpenr);
		return "HY" + hyttefornr;
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
		String info = "\nForsikringsnr: " + getForsikringsnr() + super.toString();
		if(erAvslutta())
			info += "\nAvsluttet: " + df.format(getAvslutta().getTime());
		info += "\nForsikringsinfo:\n" + getForsikringsinfo();
		return info;
	}
}//slutt på klasse HytteForsikring
