import java.io.Serializable;
import java.util.*;

public abstract class Person implements Serializable
{
	private static final long serialVersionUID = 42L;
	protected String fornavn, etternavn, personNr, telefonNr;

	protected Person(String fNavn, String eNavn, String persNr, String tlfNr)
	{
		fornavn = fNavn;
		etternavn = eNavn;
		personNr = persNr;
		telefonNr = tlfNr;
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
}	//slutt på abstract Person

class Kunde extends Person implements Serializable
{
	static final long serialVersionUID = 42L;
	private String adresse, passord;

	public Kunde(String fNavn, String eNavn, String persNr, String tlfNr, String adr, String pord)
	{
		super(fNavn, eNavn, persNr, tlfNr);
		adresse = adr;
		passord = pord;
	}

	public String getAdresse()
	{
		return adresse;
	}

	public void setAdresse(String adr)
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

	public String toString()
	{
		String info = "Fornavn: " + getFornavn() + "\nEtternavn: " + getEtternavn() + "\nPersonnummer: " + getPersonNr() + "\nTelefon: " + getTelefonNr() + "\nAdresse: " + getAdresse();
		return info;
	}
}	//slutt på Kunde

class Ansatt extends Person implements Serializable
{
	static final long serialVersionUID = 42L;
	private String adresse, passord, ansattnr;
	private int hjelpenr;
	private static int nestenr = 11000;

	public Ansatt(String fNavn, String eNavn, String persNr, String tlfNr, String adr, String pord)
	{
		super(fNavn, eNavn, persNr, tlfNr);
		adresse = adr;
		passord = pord;
		hjelpenr = nestenr;
		nestenr++;

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
		ansattnr = Integer.toString(hjelpenr);
		return ansattnr;
	}

	public String toString()
	{
		StringBuilder info = new StringBuilder(80);
		info.append("Fornavn: " + getFornavn() + "\nEtternavn: " + getEtternavn() + "\nPersonnummer: " + getPersonNr() + "\nAnsattnummer: " + getAnsattNr() + "\nTelefon: " + getTelefonNr() + "\nAvdeling: " + getAvdeling() );
		return info.toString();
	}
}//Slutt på ansatt
