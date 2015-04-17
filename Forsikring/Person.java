import java.io.Serializable;
import java.util.*;

public abstract class Person implements Serializable
{
	private static final long serialVersionUID = 42L;
	protected String fornavn, etternavn, personNr, passord, telefonNr;

	protected Person(String fNavn, String eNavn, String persNr, String tlfNr, String pord)
	{
		fornavn = fNavn;
		etternavn = eNavn;
		personNr = persNr;
		telefonNr = tlfNr;
		passord = pord;
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
	private String adresse;

	public Kunde(String fNavn, String eNavn, String persNr, String tlfNr, String adr, String pord)
	{
		super(fNavn, eNavn, persNr, tlfNr, pord);
		adresse = adr;
	}

	public String getAdresse()
	{
		return adresse;
	}

	public void setAdresse(String adr)
	{
		adresse = adr;
	}

	public String toString()
	{
		return "Fornavn: " + getFornavn() + "\nEtternavn: " + getEtternavn() + "\nAdresse: " + getAdresse() + "\nTelefon: " + getTelefonNr();
	}
}	//slutt på Kunde

class Ansatt extends Person implements Serializable
{
	static final long serialVersionUID = 42L;
	private String adresse;

	public Ansatt(String fNavn, String eNavn, String persNr, String tlfNr, String adr, String pord)
	{
		super(fNavn, eNavn, persNr, tlfNr, pord);
		adresse = adr;
	}

	public String getAdresse()
	{
		return adresse;
	}

	public void setAdresse(String adr)
	{
		adresse = adr;
	}

	public String toString()
	{
		return "Fornavn: " + getFornavn() + "Etternavn: " + getEtternavn() + "Personnummer: " + getPersonNr() + "Telefon: " + getTelefonNr() + "Adresse: " + getAdresse();
	}
}
