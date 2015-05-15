import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Skademelding
{
	private static final long serialVersionUID = 42L;
	private String kundenr, skadeadresse, skadetype, beskrivelse, status;
	private String vitneNavn, vitneTlfNr;
	private int hjelpenr, takstbeløp, utbetaltbeløp;
	private boolean godkjent;
	private Calendar dato;
	private Register reg;
	private File bilrapport;
	private static int nestenr = 80000;

	public Skademelding(String kunr, Calendar date, String adresse, String type, int takstsum, String info)
	{
		hjelpenr = nestenr;
		nestenr++;
		kundenr = kunr;
		dato = date;
		skadeadresse = adresse;
		skadetype = type;
		takstbeløp = takstsum;
		beskrivelse = info;
		//godkjent;
		status = "";
	}

	public Skademelding(String kunr, Calendar date, String adresse, String type, int takstsum, String info, String vnavn, String vnr, File rapport)
	{
		hjelpenr = nestenr;
		nestenr++;
		kundenr = kunr;
		dato = date;
		skadeadresse = adresse;
		skadetype = type;
		takstbeløp = takstsum;
		beskrivelse = info;
		vitneNavn = vnavn;
		vitneTlfNr = vnr;
		bilrapport = rapport;
		//lagreRapport(bilrapport);
		//godkjent;
		status = "";

	}

	public int getSkadeNr()
	{
		return hjelpenr;
	}

	public String getKundeNr()
	{
		return kundenr;
	}

	public Kunde getKunde()
	{
		return reg.getKundeViaKundeNr(kundenr);
	}

	public Calendar getDato()
	{
		return dato;
	}

	public String getSkadeAdresse()
	{
		return skadeadresse;
	}

	public String getSkadeType()
	{
		return skadetype;
	}

	public int getTakst()
	{
		return takstbeløp;
	}

	public long getUtbetalt()
	{
		return utbetaltbeløp;
	}

	public String getStatus()
	{
		if(status.equals(""))
			return "Ikke behandlet";
		else if(status.equals("Ja"))
			return "Godkjent";
		else
			return "Avslått";
	}

	public boolean getGodkjent()
	{
		return godkjent;
	}

	public String getVitneNavn()
	{
		return vitneNavn;
	}

	public String getVitneTlfNr()
	{
		return vitneTlfNr;
	}

	public void setDato(Calendar date)
	{
		dato = date;
	}

	public void setUtbetalt(int kr)
	{
		utbetaltbeløp = kr;
	}

	public void setStatus(String ok)
	{
		status = ok;
	}

	public void setGodkjent(boolean ok)
	{
		godkjent = ok;
	}

	public void setVitneNavn(String vnavn)
	{
		vitneNavn = vnavn;
	}

	public void setVitneTlfNr(String vnr)
	{
		vitneTlfNr = vnr;
	}

	public File getRapport()
	{
	return bilrapport;
	}

	public String ikkeGodkjentEnnå()
	{
		return "Erstatningsbeløp er ikke godkjent ennå";
	}

	public static int getNrNå()
	{
		return nestenr;
		//Integer.toString(nesteid);
	}

	public static void setNrNå(int nr)
	{
		if(nr>nestenr)
			nestenr = nr;
	}

	public String toString()
	{
		String info = "Skadenr: " + getSkadeNr() + "\nKunde: " + getKundeNr() + "\nDato for hendelse: " + getDato()
			+ "\nSted: " + getSkadeAdresse() + "\nType: " + getSkadeType() + "\nTakseringsbeløp: " + getTakst()
			+ "\nUtbetalt erstatning: ";
		if(!(getUtbetalt() == 0))
			info += getUtbetalt();
		else
			info += ikkeGodkjentEnnå();
		return info;
	}
}//slutt på klassen Skademelding
