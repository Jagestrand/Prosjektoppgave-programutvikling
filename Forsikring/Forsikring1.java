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
			bilKat = 30000, båtKat = 40000, husKat = 50000, hytKat = 60000;
	private static int nyNr, bilnr = 30000, båtnr = 40000, husnr = 50000, hytnr = 60000;
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
	private static int nestenr;
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
	public static final int TYPE_BIL = 1, TYPE_BÅT = 2, TYPE_HUS = 3, TYPE_HYTTE = 4, TYPE_INNBO1 = 5, TYPE_INNBO2 = 6;
	private static int nyNr, bilnr = 30000, båtnr = 40000, husnr = 50000, hytnr = 60000;
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
	private static int nestenr = 30000;

	public BilForsikring1(String kunpnr, String info, int kat, String eier, String regnr, String merke, String mod, int regår, int kmårlig, int fobeløp)
	{
		super(kunpnr, info, kat);
		eiernavn = eier;
		registreringsnr = regnr;
		type = merke;
		modell = mod;
		registreringsår = regår;
		kjørelengde = kmårlig;
		forsikringsbeløp = fobeløp;
		hjelpenr = nestenr;
		nestenr++;
	}
	public int billTypeBeløp()
	{
		if(type.equals("Volvo"))
			return 1000;
		else if(type.equals("Audi"))
			return 1000;
		else if(type.equals("BMW"))
			return 1000;
		else if(type.equals("Chevrolet"))
			return 1000;
		else if(type.equals("Chrysler"))
			return 1000;
		else if(type.equals("Ford"))
			return 1000;
		else if(type.equals("Lexus"))
			return 1000;
		else if(type.equals("Maserati"))
			return 1000;
		else if(type.equals("Mercedes-Benz"))
			return 1000;
		else if(type.equals("Porsche"))
			return 1000;
		else
			return 1000;
	}
	public int regÅrBeløp()
	{	
		int beløp = 0;
		if(registreringsår == 1993 )
		{
			if(getBonus() == 0)
				beløp = 25356;
			else if(getBonus() == 10)	
				beløp = 23850;
			else if(getBonus() == 20)
				beløp = 23172;
			else if(getBonus() == 30)
				beløp = 21000;
			else if(getBonus() == 40)
				beløp = 18816;
			else if(getBonus() == 50)
				beløp = 16644;
			else if(getBonus() == 60)
				beløp = 14460;
			else if(getBonus() == 70)
				beløp = 12264;
			else
				beløp = 11196;
		}
		else if(registreringsår == 1994 )
		{
			if(getBonus() == 0)
				beløp = 24956;
			else if(getBonus() == 10)	
				beløp = 23450;
			else if(getBonus() == 20)
				beløp = 22772;
			else if(getBonus() == 30)
				beløp = 20600;
			else if(getBonus() == 40)
				beløp = 18416;
			else if(getBonus() == 50)
				beløp = 16244;
			else if(getBonus() == 60)
				beløp = 14060;
			else if(getBonus() == 70)
				beløp = 11864;
			else
				beløp = 10796;
		}
		else if(registreringsår == 1995 )
		{
			if(getBonus() == 0)
				beløp = 24556;
			else if(getBonus() == 10)	
				beløp = 23050;
			else if(getBonus() == 20)
				beløp = 22372;
			else if(getBonus() == 30)
				beløp = 20200;
			else if(getBonus() == 40)
				beløp = 18016;
			else if(getBonus() == 50)
				beløp = 15844;
			else if(getBonus() == 60)
				beløp = 13660;
			else if(getBonus() == 70)
				beløp = 11464;
			else
				beløp = 10396;
		}
		else if(registreringsår == 1996 )
		{
			if(getBonus() == 0)
				beløp = 24156;
			else if(getBonus() == 10)	
				beløp = 22650;
			else if(getBonus() == 20)
				beløp = 21972;
			else if(getBonus() == 30)
				beløp = 19800;
			else if(getBonus() == 40)
				beløp = 17616;
			else if(getBonus() == 50)
				beløp = 15444;
			else if(getBonus() == 60)
				beløp = 13260;
			else if(getBonus() == 70)
				beløp = 11064;
			else
				beløp = 9996;
		}
		else if(registreringsår == 1997 )
		{
			if(getBonus() == 0)
				beløp = 23756;
			else if(getBonus() == 10)	
				beløp = 22250;
			else if(getBonus() == 20)
				beløp = 21572;
			else if(getBonus() == 30)
				beløp = 19400;
			else if(getBonus() == 40)
				beløp = 17216;
			else if(getBonus() == 50)
				beløp = 15044;
			else if(getBonus() == 60)
				beløp = 12860;
			else if(getBonus() == 70)
				beløp = 10664;
			else
				beløp = 9596;
		}
		else if(registreringsår == 1998 )
		{
			if(getBonus() == 0)
				beløp = 23356;
			else if(getBonus() == 10)	
				beløp = 21850;
			else if(getBonus() == 20)
				beløp = 21172;
			else if(getBonus() == 30)
				beløp = 19000;
			else if(getBonus() == 40)
				beløp = 16816;
			else if(getBonus() == 50)
				beløp = 14644;
			else if(getBonus() == 60)
				beløp = 12460;
			else if(getBonus() == 70)
				beløp = 10264;
			else
				beløp = 9196;
		}
		else if(registreringsår == 1999 )
		{
			if(getBonus() == 0)
				beløp = 22956;
			else if(getBonus() == 10)	
				beløp = 21450;
			else if(getBonus() == 20)
				beløp = 20772;
			else if(getBonus() == 30)
				beløp = 18600;
			else if(getBonus() == 40)
				beløp = 16416;
			else if(getBonus() == 50)
				beløp = 14244;
			else if(getBonus() == 60)
				beløp = 12060;
			else if(getBonus() == 70)
				beløp = 9864;
			else
				beløp = 8796;
		}
		else if(registreringsår == 2000 )
		{
			if(getBonus() == 0)
				beløp = 22556;
			else if(getBonus() == 10)	
				beløp = 21050;
			else if(getBonus() == 20)
				beløp = 20372;
			else if(getBonus() == 30)
				beløp = 18200;
			else if(getBonus() == 40)
				beløp = 16016;
			else if(getBonus() == 50)
				beløp = 13844;
			else if(getBonus() == 60)
				beløp = 11660;
			else if(getBonus() == 70)
				beløp = 9464;
			else
				beløp = 8396;
		}
		else if(registreringsår == 2001 )
		{
			if(getBonus() == 0)
				beløp = 22156;
			else if(getBonus() == 10)	
				beløp = 20650;
			else if(getBonus() == 20)
				beløp = 19972;
			else if(getBonus() == 30)
				beløp = 17800;
			else if(getBonus() == 40)
				beløp = 15616;
			else if(getBonus() == 50)
				beløp = 13444;
			else if(getBonus() == 60)
				beløp = 11260;
			else if(getBonus() == 70)
				beløp = 9064;
			else
				beløp = 7996;
		}
		else if(registreringsår == 2002 )
		{
			if(getBonus() == 0)
				beløp = 21756;
			else if(getBonus() == 10)	
				beløp = 20650;
			else if(getBonus() == 20)
				beløp = 19572;
			else if(getBonus() == 30)
				beløp = 17400;
			else if(getBonus() == 40)
				beløp = 15216;
			else if(getBonus() == 50)
				beløp = 13044;
			else if(getBonus() == 60)
				beløp = 10860;
			else if(getBonus() == 70)
				beløp = 8664;
			else
				beløp = 7596;
		}
		else if(registreringsår == 2003 )
		{
			if(getBonus() == 0)
				beløp = 21356;
			else if(getBonus() == 10)	
				beløp = 20250;
			else if(getBonus() == 20)
				beløp = 19172;
			else if(getBonus() == 30)
				beløp = 17000;
			else if(getBonus() == 40)
				beløp = 14816;
			else if(getBonus() == 50)
				beløp = 12644;
			else if(getBonus() == 60)
				beløp = 10460;
			else if(getBonus() == 70)
				beløp = 8264;
			else
				beløp = 7196;
		}
		else if(registreringsår == 2004 )
		{
			if(getBonus() == 0)
				beløp = 20956;
			else if(getBonus() == 10)	
				beløp = 19850;
			else if(getBonus() == 20)
				beløp = 18772;
			else if(getBonus() == 30)
				beløp = 16600;
			else if(getBonus() == 40)
				beløp = 14416;
			else if(getBonus() == 50)
				beløp = 12244;
			else if(getBonus() == 60)
				beløp = 10060;
			else if(getBonus() == 70)
				beløp = 7864;
			else
				beløp = 6796;
		}
		else if(registreringsår == 2005 )
		{
			if(getBonus() == 0)
				beløp = 20556;
			else if(getBonus() == 10)	
				beløp = 19450;
			else if(getBonus() == 20)
				beløp = 18372;
			else if(getBonus() == 30)
				beløp = 16200;
			else if(getBonus() == 40)
				beløp = 14016;
			else if(getBonus() == 50)
				beløp = 11844;
			else if(getBonus() == 60)
				beløp = 9660;
			else if(getBonus() == 70)
				beløp = 7464;
			else
				beløp = 6396;
		}
		else if(registreringsår == 2006 )
		{
			if(getBonus() == 0)
				beløp = 20156;
			else if(getBonus() == 10)	
				beløp = 19050;
			else if(getBonus() == 20)
				beløp = 17972;
			else if(getBonus() == 30)
				beløp = 15800;
			else if(getBonus() == 40)
				beløp = 13616;
			else if(getBonus() == 50)
				beløp = 11444;
			else if(getBonus() == 60)
				beløp = 9260;
			else if(getBonus() == 70)
				beløp = 7064;
			else
				beløp = 5996;
		}
		else if(registreringsår == 2007 )
		{
			if(getBonus() == 0)
				beløp = 19756;
			else if(getBonus() == 10)	
				beløp = 18650;
			else if(getBonus() == 20)
				beløp = 17572;
			else if(getBonus() == 30)
				beløp = 15400;
			else if(getBonus() == 40)
				beløp = 13216;
			else if(getBonus() == 50)
				beløp = 11044;
			else if(getBonus() == 60)
				beløp = 8860;
			else if(getBonus() == 70)
				beløp = 6664;
			else
				beløp = 5596;
		}
		else if(registreringsår == 2008 )
		{
			if(getBonus() == 0)
				beløp = 19356;
			else if(getBonus() == 10)	
				beløp = 18250;
			else if(getBonus() == 20)
				beløp = 17172;
			else if(getBonus() == 30)
				beløp = 1500;
			else if(getBonus() == 40)
				beløp = 12816;
			else if(getBonus() == 50)
				beløp = 10644;
			else if(getBonus() == 60)
				beløp = 8460;
			else if(getBonus() == 70)
				beløp = 6264;
			else
				beløp = 5196;
		}
		else if(registreringsår == 2009 )
		{
			if(getBonus() == 0)
				beløp = 18956;
			else if(getBonus() == 10)	
				beløp = 17750;
			else if(getBonus() == 20)
				beløp = 16772;
			else if(getBonus() == 30)
				beløp = 14600;
			else if(getBonus() == 40)
				beløp = 12416;
			else if(getBonus() == 50)
				beløp = 10244;
			else if(getBonus() == 60)
				beløp = 8060;
			else if(getBonus() == 70)
				beløp = 5864;
			else
				beløp = 4796;
		}
		else if(registreringsår == 2010 )
		{
			if(getBonus() == 0)
				beløp = 18556;
			else if(getBonus() == 10)	
				beløp = 17350;
			else if(getBonus() == 20)
				beløp = 16372;
			else if(getBonus() == 30)
				beløp = 14200;
			else if(getBonus() == 40)
				beløp = 12016;
			else if(getBonus() == 50)
				beløp = 9844;
			else if(getBonus() == 60)
				beløp = 7660;
			else if(getBonus() == 70)
				beløp = 5464;
			else
				beløp = 4396;
		}
		else if(registreringsår == 2011 )
		{
			if(getBonus() == 0)
				beløp = 18156;
			else if(getBonus() == 10)	
				beløp = 16950;
			else if(getBonus() == 20)
				beløp = 15972;
			else if(getBonus() == 30)
				beløp = 13800;
			else if(getBonus() == 40)
				beløp = 11616;
			else if(getBonus() == 50)
				beløp = 9444;
			else if(getBonus() == 60)
				beløp = 7260;
			else if(getBonus() == 70)
				beløp = 5064;
			else
				beløp = 4196;
		}
		else if(registreringsår == 2012 )
		{
			if(getBonus() == 0)
				beløp = 17956;
			else if(getBonus() == 10)	
				beløp = 16750;
			else if(getBonus() == 20)
				beløp = 15772;
			else if(getBonus() == 30)
				beløp = 13600;
			else if(getBonus() == 40)
				beløp = 11416;
			else if(getBonus() == 50)
				beløp = 9244;
			else if(getBonus() == 60)
				beløp = 7060;
			else if(getBonus() == 70)
				beløp = 4864;
			else
				beløp = 3996;
		}
		else if(registreringsår == 2013 )
		{
			if(getBonus() == 0)
				beløp = 17956;
			else if(getBonus() == 10)	
				beløp = 16750;
			else if(getBonus() == 20)
				beløp = 15772;
			else if(getBonus() == 30)
				beløp = 13600;
			else if(getBonus() == 40)
				beløp = 11416;
			else if(getBonus() == 50)
				beløp = 9244;
			else if(getBonus() == 60)
				beløp = 7060;
			else if(getBonus() == 70)
				beløp = 4864;
			else
				beløp = 3996;
		}
		else if(registreringsår == 2014 )
		{
			if(getBonus() == 0)
				beløp = 17556;
			else if(getBonus() == 10)	
				beløp = 16350;
			else if(getBonus() == 20)
				beløp = 15372;
			else if(getBonus() == 30)
				beløp = 13200;
			else if(getBonus() == 40)
				beløp = 11016;
			else if(getBonus() == 50)
				beløp = 8844;
			else if(getBonus() == 60)
				beløp = 6660;
			else if(getBonus() == 70)
				beløp = 4464;
			else
				beløp = 3596;
		}
		else if(registreringsår == 2015 )
		{
			if(getBonus() == 0)
				beløp = 17156;
			else if(getBonus() == 10)	
				beløp = 15950;
			else if(getBonus() == 20)
				beløp = 14972;
			else if(getBonus() == 30)
				beløp = 12800;
			else if(getBonus() == 40)
				beløp = 10616;
			else if(getBonus() == 50)
				beløp = 8444;
			else if(getBonus() == 60)
				beløp = 6260;
			else if(getBonus() == 70)
				beløp = 4064;
			else
				beløp = 3196;
		}
		return beløp;
	}
	
	public int sumBeløpBil() 
	{ 
	    int beløp = regÅrBeløp() +billTypeBeløp();
	    	return beløp;
	}

	public int getForsikringsbeløp()
	{	
		forsikringsbeløp = sumBeløpBil();
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
		return 20;
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
		info += "\nForsikringsinfo:\n" + getForsikringsinfo();
		return info;
	}


}

class BåtForsikring1 extends Forsikring1 implements Serializable
{
	static final long serialVersionUID = 42L;
	private String eiernavn, registreringsnr, båttype, modell, båtfornr, motortype, alder;
	private int forsikringsbeløp, årsmodell, båtlengde, motorstyrke, hjelpenr;
	private static int nestenr = 40000;
	private boolean aktiv;

	public BåtForsikring1(String kunpnr, int beløp, String info, String eier, String regnr, String typ, String mod, int modellår, int lengde, String motortyp, int styrke, int kat, String al)
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
		alder = al;
	}
	
	public int regÅrBeløp()
	{
		if(årsmodell == 1993)
			return 2650;
		else if(årsmodell == 1994)
			return 2550;
		else if(årsmodell == 1995)
			return 2450;
		else if(årsmodell == 1996)
			return 2350;
		else if(årsmodell == 1997)
			return 2250;
		else if(årsmodell == 1998)
			return 2150;
		else if(årsmodell == 1999)
			return 2050;
		else if(årsmodell == 2000)
			return 1950;
		else if(årsmodell == 2001)
			return 1850;
		else if(årsmodell == 2002)
			return 1750;
		else if(årsmodell == 2003)
			return 1650;
		else if(årsmodell == 2004)
			return 1550;
		else if(årsmodell == 2005)
			return 1450;
		else if(årsmodell == 2006)
			return 1350;
		else if(årsmodell == 2007)
			return 1250;
		else if(årsmodell == 2008)
			return 1150;
		else if(årsmodell == 2009)
			return 1050;
		else if(årsmodell == 2010)
			return 950;
		else if(årsmodell == 2011)
			return 850;
		else if(årsmodell == 2012)
			return 750;
		else if(årsmodell == 2013)
			return 650;
		else if(årsmodell == 2014)
			return 550;
		else
			return 450;
	}
	
	public int båtTypeBeløp()
	{
		if(båttype.equals("Seilbåt"))
			return 1500;
		else if(båttype.equals("Motorbåt med innenbordsmotor"))
			return 1500;
		else if(båttype.equals("Motorbåt med utenbordsmotor"))
			return 1500;
		else
			return 500;
	}
	
	public int alderBeløp()
	{
		if(alder.equals("Båtfører < 23 år"))
			return 3000;
		else if(alder.equals("Båtførere mellom 23-25 år"))
			return 2500;
		else
			return 2000;
	}
	
	public int sumBeløpBåt() 
	{ 
	    int beløp = regÅrBeløp() + båtTypeBeløp() + alderBeløp();
	    	return beløp;
	}
	
	public int getForsikringsbeløp()
	{
		forsikringsbeløp = sumBeløpBåt();
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

	public int getForsikringsnr()
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
		String info = "\nForsikringsnr: " + getForsikringsnr() + super.toString() + "\nÅrsmodell: " + getÅrsmodell() + "\nBåtlengde(fot): " + getBåtlengde() + "\nMotortype: " + getMotortype() + "\nMotorstyrke(HK): " + getMotorstyrke();
		if(erAvslutta())
			info += "\nAvsluttet: " + df.format(getAvslutta().getTime());
		info += "\nForsikringsinfo:\n" + getForsikringsinfo();
		return info;
	}
}//slutt på klasse BåtForsikring


class HusForsikring1 extends Forsikring1 implements Serializable
{
	static final long serialVersionUID = 42L;
	private String husfornr, adresse, boligtype, byggemateriale, standard;
	private int hjelpenr, beløpBygg, beløpInn, byggeår, kvadratmeter;
	private static int nestenr = 50000;
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
	
	public int byggeårBeløp()
	{
		if(byggeår >= 2010 && byggeår < 2016)
			return 200; 
		else if(byggeår >= 2000 && byggeår < 2009)
			return 220;
		else if(byggeår >= 1990 && byggeår < 1999)
			return 240; 
		else if(byggeår >= 1980 && byggeår < 1989)
			return 260;
		else if(byggeår >= 1970 && byggeår < 1979)
			return 280; 
		else if(byggeår >= 1960 && byggeår < 1969)
			return 300;
		else if(byggeår >= 1950 && byggeår < 1959)
			return 320; 
		else if(byggeår >= 1940 && byggeår < 1949)
			return 340;
		else if(byggeår >= 1930 && byggeår < 1939)
			return 360;
		else if(byggeår >= 1920 && byggeår < 1929)
			return 380; 
		else if(byggeår >= 1910 && byggeår < 1919)
			return 400;
		else if(byggeår >= 1900 && byggeår < 1909)
			return 420;
		else
			return 440;
	}

	public int byggematerialeBeløp()
	{
		if(byggemateriale.equals("Mur"))
			return 200;
		else if(byggemateriale.equals("Brannfast"))
			return 100;
		else if(byggemateriale.equals("Tre"))
			return 200;
		else
			return 500;
	}
	
	public int standardBeløp()
	{
		if(standard.equals("Normal standard"))
			return 220;
		else if(standard.equals("Bedre standard"))
			return 420;
		else
			return 620; 
	}
	
	public int boligtypeBeløp()
	{
		if(boligtype.equals("Enebolig"))
			return 300;
		else if(boligtype.equals("Tomannsbolig"))
			return 200;
		else if(boligtype.equals("Rekkehus"))
			return 200; 
		else if(boligtype.equals("Tremannsbolig"))
			return 100; 
		else 
			return 50; 
	} 
	
	public int kvadratmeterBeløp()
	{
		if(kvadratmeter <= 24)
			return 250; 
		else if(kvadratmeter >= 25 && kvadratmeter < 49)
			return 750;
		else if(kvadratmeter >= 50 && kvadratmeter < 74)
			return 1250; 
		else if(kvadratmeter >= 75 && kvadratmeter < 99)
			return 1750;
		else if(kvadratmeter >= 100 && kvadratmeter < 124)
			return 2250;
		else if(kvadratmeter >= 125 && kvadratmeter < 149)
			return 2750;
		else if(kvadratmeter >= 150 && kvadratmeter < 174)
			return 3250;
		else if(kvadratmeter >= 175 && kvadratmeter < 199)
			return 3750;
		else if(kvadratmeter >= 200 && kvadratmeter < 224)
			return 3250; 
		else if(kvadratmeter >= 225 && kvadratmeter < 249)
			return 3750;
		else if(kvadratmeter >= 250 && kvadratmeter < 274)
			return 4250;
		else if(kvadratmeter >= 275 && kvadratmeter < 299)
			return 4750;
		else if(kvadratmeter >= 300 && kvadratmeter < 324)
			return 5250;
		else if(kvadratmeter >= 325 && kvadratmeter < 349)
			return 5750;
		else if(kvadratmeter >= 350 && kvadratmeter < 374)
			return 6250;
		else if(kvadratmeter >= 375 && kvadratmeter < 399)
			return 6750;
		else if(kvadratmeter >= 400 && kvadratmeter < 424)
			return 7250;
		else if(kvadratmeter >= 425 && kvadratmeter < 449)
			return 7750;
		else if(kvadratmeter >= 450 && kvadratmeter < 499)
			return 8250;
		else if(kvadratmeter >= 500 && kvadratmeter < 549)
			return 8750;
		else if(kvadratmeter >= 550 && kvadratmeter < 574)
			return 9250;
		else if(kvadratmeter >= 575 && kvadratmeter < 600)
			return 9750;
		else
			return 12000;
	}
	
	public int beløpInnBeløp()
	{
		if(beløpInn == 150000 )
			return 150;
		else if(beløpInn == 300000)
			return 300;
		else if(beløpInn == 500000)
			return 450;
		else if(beløpInn == 750000)
			return 600;
		else if(beløpInn == 10000000)
			return 750; 
		else if(beløpInn == 1500000)
			return 900;
		else if(beløpInn == 2000000)
			return 1050;
		else 
			return 1200; 
	}
	
	public int sumBeløpHus()
	{
	    int beløp = byggeårBeløp() + byggematerialeBeløp() + standardBeløp() + boligtypeBeløp() + kvadratmeterBeløp() + beløpInnBeløp();
	    	return beløp;
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
		beløpBygg = sumBeløpHus();
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
		String info = "\nForsikringsnr: " + getForsikringsnr() + super.toString();
		if(erAvslutta())
			info += "\nAvsluttet: " + df.format(getAvslutta().getTime());
		info += "\nForsikringsinfo:\n" + getForsikringsinfo();
		return info;
	}
}//slutt på klasse HusForsikring


class HytteForsikring1 extends Forsikring1 implements Serializable
{
	static final long serialVersionUID = 42L;
	private String hyttefornr, adresse, boligtype, byggemateriale, standard;
	private int hjelpenr, beløpBygg, beløpInn, byggeår, kvadratmeter;
	private static int nestenr = 60000;
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
	
	public int byggematerialeBeløp()
	{
	if (byggemateriale.equals("Mur"))
		return 250;
	else if(byggemateriale.equals("Brannfast"))
		return 150;
	else if(byggemateriale.equals("Tre"))
		return 250;
	else
		return 550;
	}
	
	public int boligtypeBeløp()
	{
		if(boligtype.equals("Enebolig"))
			return 400;
		else if(boligtype.equals("Tomannsbolig"))
			return 300;
		else if(boligtype.equals("Rekkehus"))
			return 300; 
		else if(boligtype.equals("Tremannsbolig"))
			return 200; 
		else 
			return 100; 
	} 
	
	public int standardBeløp()
	{
		if(standard.equals("Normal standard"))
			return 240;
		else if(standard.equals("Bedre standard"))
			return 440;
		else
			return 640; 
	}
	
	public int kvadratmeterBeløp()
	{
		if(kvadratmeter <= 24)
			return 250; 
		else if(kvadratmeter >= 25 && kvadratmeter < 49)
			return 750;
		else if(kvadratmeter >= 50 && kvadratmeter < 74)
			return 1250; 
		else if(kvadratmeter >= 75 && kvadratmeter < 99)
			return 1750;
		else if(kvadratmeter >= 100 && kvadratmeter < 124)
			return 2250;
		else if(kvadratmeter >= 125 && kvadratmeter < 149)
			return 2750;
		else if(kvadratmeter >= 150 && kvadratmeter < 174)
			return 3250;
		else if(kvadratmeter >= 175 && kvadratmeter < 199)
			return 3750;
		else if(kvadratmeter >= 200 && kvadratmeter < 224)
			return 3250; 
		else if(kvadratmeter >= 225 && kvadratmeter < 249)
			return 3750;
		else if(kvadratmeter >= 250 && kvadratmeter < 274)
			return 4250;
		else if(kvadratmeter >= 275 && kvadratmeter < 299)
			return 4750;
		else if(kvadratmeter >= 300 && kvadratmeter < 324)
			return 5250;
		else if(kvadratmeter >= 325 && kvadratmeter < 349)
			return 5750;
		else if(kvadratmeter >= 350 && kvadratmeter < 374)
			return 6250;
		else if(kvadratmeter >= 375 && kvadratmeter < 399)
			return 6750;
		else if(kvadratmeter >= 400 && kvadratmeter < 424)
			return 7250;
		else if(kvadratmeter >= 425 && kvadratmeter < 449)
			return 7750;
		else if(kvadratmeter >= 450 && kvadratmeter < 499)
			return 8250;
		else if(kvadratmeter >= 500 && kvadratmeter < 549)
			return 8750;
		else if(kvadratmeter >= 550 && kvadratmeter < 574)
			return 9250;
		else if(kvadratmeter >= 575 && kvadratmeter < 600)
			return 9750;
		else
			return 12000;
	}
	
	public int beløpInnBeløp()
	{
		if(beløpInn == 150000 )
			return 200;
		else if(beløpInn == 300000)
			return 350;
		else if(beløpInn == 500000)
			return 500;
		else if(beløpInn == 750000)
			return 650;
		else if(beløpInn == 10000000)
			return 800; 
		else if(beløpInn == 1500000)
			return 950;
		else if(beløpInn == 2000000)
			return 1100;
		else 
			return 1250; 
	}
	
	public int byggeårBeløp()
	{
		if(byggeår >= 2010 && byggeår < 2016)
			return 200; 
		else if(byggeår >= 2000 && byggeår < 2009)
			return 220;
		else if(byggeår >= 1990 && byggeår < 1999)
			return 240; 
		else if(byggeår >= 1980 && byggeår < 1989)
			return 260;
		else if(byggeår >= 1970 && byggeår < 1979)
			return 280; 
		else if(byggeår >= 1960 && byggeår < 1969)
			return 300;
		else if(byggeår >= 1950 && byggeår < 1959)
			return 320; 
		else if(byggeår >= 1940 && byggeår < 1949)
			return 340;
		else if(byggeår >= 1930 && byggeår < 1939)
			return 360;
		else if(byggeår >= 1920 && byggeår < 1929)
			return 380; 
		else if(byggeår >= 1910 && byggeår < 1919)
			return 400;
		else if(byggeår >= 1900 && byggeår < 1909)
			return 420;
		else
			return 440;
	}
	
	public int sumBeløpHytte() 
	{
	    int beløp = standardBeløp() + boligtypeBeløp() + byggematerialeBeløp() + kvadratmeterBeløp() + beløpInnBeløp() + byggeårBeløp();
	    	return beløp;
	}
	
	public int getBeløpBygg()
	{ 	
		beløpBygg = sumBeløpHytte();
		return beløpBygg;
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
		String info = "\nForsikringsnr: " + getForsikringsnr() + super.toString();
		if(erAvslutta())
			info += "\nAvsluttet: " + df.format(getAvslutta().getTime());
		info += "\nForsikringsinfo:\n" + getForsikringsinfo();
		return info;
	}
}//slutt på klasse HytteForsikring
