import java.io.Serializable;
import java.util.*;

public abstract class Person implements Serializable
{
	private static final long serialVersionUID = 42L;
	protected String fornavn, etternavn, personNr, telefonNr;
	protected static String ja = "Aktiv", nei = "Deaktivert";
	private Calendar starta, avslutta;
	protected BilForsikringsReg billiste;
	protected BåtForsikringsReg båtliste;
	protected HusForsikringsReg husliste;
	protected HytteForsikringsReg hytteliste;
	protected SkademeldingReg skadeliste;
	protected boolean aktiv;

	protected Person(String fNavn, String eNavn, String persNr, String tlfNr)
	{
		fornavn = fNavn;
		etternavn = eNavn;
		personNr = persNr;
		telefonNr = tlfNr;
		billiste = new BilForsikringsReg();
		båtliste = new BåtForsikringsReg();
		husliste = new HusForsikringsReg();
		hytteliste = new HytteForsikringsReg();
		skadeliste = new SkademeldingReg();
		starta = Calendar.getInstance();
		//aktiv = true;
	}

	public Calendar getStarta()
	{
		return starta;
	}

	public Calendar getAvslutta()
	{
		return avslutta;
	}

	public void setAvslutta(Calendar avslutt)
	{
		avslutta = avslutt;
	}

	public String getFornavn()
	{
		return fornavn;
	}
	public String getEtternavn()
	{
		return etternavn;
	}
	public String getPersonNr()
	{
		return personNr;
	}
	public String getTelefonNr()
	{
		return telefonNr;
	}

	public void setFornavn(String fNavn)
	{
		fornavn = fNavn;
	}
	public void setEtternavn(String eNavn)
	{
		etternavn = eNavn;
	}
	public void setPersonNr(String persNr)
	{
		personNr = persNr;
	}
	public void setTelefonNr(String tlfNr)
	{
		telefonNr = tlfNr;
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
}	//slutt på abstract Person

class Kunde extends Person implements Serializable
{
	static final long serialVersionUID = 42L;
	private String adresse, postnr, poststed, passord, kundenr;
	private int hjelpenr;
	private static int nestenr = 0;
	private static String kundekat = "A";

	public Kunde(String fNavn, String eNavn, String persNr, String tlfNr, String adr, String ponr, String psted, String pord)
	{
		super(fNavn, eNavn, persNr, tlfNr);
		adresse = adr;
		postnr = ponr;
		poststed = psted;
		passord = pord;
		hjelpenr = ++nestenr;
		aktiv = true;
	}

	public Kunde(String fNavn, String eNavn, String persNr, String tlfNr)
	{
		super(fNavn, eNavn, persNr, tlfNr);
	}

	public String getAdresse()
	{
		return adresse;
	}

	public void setAdresse(String adr)
	{
		adresse = adr;
	}

	public String getPostnr()
	{
		return postnr;
	}

	public void setPostnr(String ponr)
	{
		postnr = ponr;
	}

	public String getPoststed()
	{
		return poststed;
	}

	public void setPoststed(String psted)
	{
		poststed = psted;
	}

	public void setPassord(String pord)
	{
		passord = pord;
	}

	public String getPassord()
	{
		return passord;
	}

	public String getKundeNr()
	{
		return kundekat + Integer.toString(hjelpenr);
	}

	public String getKundeKat()
	{
		return kundekat;
	}

	public boolean erTotalkunde()
	{
		if(billiste.size() + båtliste.size() + husliste.size() + hytteliste.size() >= 3)
			return true;
		else
			return false;
	}

	public String getTotalkundeStatus()
	{
		if(erTotalkunde())
			return "Ja";
		else
			return "Nei";
	}

	public BilForsikringsReg getBiler()
	{
		return billiste;
	}
	public BåtForsikringsReg getBåter()
	{
		return båtliste;
	}
	public HusForsikringsReg getHus()
	{
		return husliste;
	}
	public HytteForsikringsReg getHytter()
	{
		return hytteliste;
	}
	public SkademeldingReg getSkademeldinger()
	{
		return skadeliste;
	}

	public Iterator<BilForsikring> iterator()
	{
		return billiste.iterator();
	}

	public Iterator<BåtForsikring> iteratorb()
	{
		return båtliste.iterator();
	}

	public Iterator<HusForsikring> iteratorh()
	{
		return husliste.iterator();
	}

	public Iterator<HytteForsikring> iteratory()
	{
		return hytteliste.iterator();
	}

	public void nyForsikring(BilForsikring forsikr)
	{
		billiste.add(forsikr);
	}

	public void nyForsikring(BåtForsikring forsikr)
	{
		båtliste.add(forsikr);
	}

	public void nyForsikring(HusForsikring forsikr)
	{
		husliste.add(forsikr);
	}

	public void nyForsikring(HytteForsikring forsikr)
	{
		hytteliste.add(forsikr);
	}
	public void nySkademelding(Skademelding skad)
	{
		skadeliste.add(skad);
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
		String info = "Kundenr: " + getKundeNr() + "\nFornavn: " + getFornavn() + "\nEtternavn: " + getEtternavn() + "\nPersonnummer: " + getPersonNr() + "\nTelefon: " + getTelefonNr() + "\nAdresse: " + getAdresse() + "\nPostnr: " + getPostnr() + "\nPoststed: " + getPoststed();
		return info;
	}
}	//slutt på Kunde

class Ansatt extends Person implements Serializable
{
	static final long serialVersionUID = 42L;
	private String adresse, passord, ansattnr;
	private int id;

	private static int nesteid = 10999;
	//private AnsattReg ans;

	public Ansatt(String fNavn, String eNavn, String persNr, String tlfNr, String adr, String pord)
	{
		super(fNavn, eNavn, persNr, tlfNr);
		adresse = adr;
		passord = pord;
		id = ++nesteid;
		aktiv = true;

	}

	public String getAvdeling()
	{
		return adresse;
	}

	public void setAvdeling(String adr)
	{
		adresse = adr;
	}

	public void setPassord(String pord)
	{
		passord = pord;
	}

	public String getPassord()
	{
		return passord;
	}

	public String getAnsattNr()
	{
		//ansattnr = Integer.toString(hjelpenr);
		//return ansattnr;
		return Integer.toString(id);
	}

	public static int getNrNå()
	{
		return nesteid;
		//Integer.toString(nesteid);
	}

	public static void setNrNå(int nr)
	{
		if(nr>nesteid)
			nesteid = nr;
	}

	public String toString()
	{
		StringBuilder info = new StringBuilder(80);
		info.append("Fornavn: " + getFornavn() + "\nEtternavn: " + getEtternavn() + "\nPersonnummer: " + getPersonNr() + "\nAnsattnummer: " + getAnsattNr() + "\nTelefon: " + getTelefonNr() + "\nAvdeling: " + adresse );
		return info.toString();
	}
}//Slutt på ansatt
