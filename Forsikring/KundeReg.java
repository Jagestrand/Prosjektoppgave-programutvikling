import java.util.*;
import java.io.Serializable;

import javax.swing.JOptionPane;

public class KundeReg implements Serializable
{
	static final long serialVersionUID = 42L;
	private TreeSet<Kunde> kundeListe;
	private int nrNå;

	public KundeReg()
	{
		kundeListe = new TreeSet<Kunde>(InitCollator());
	}

	public void add(Kunde holmes)
	{
		kundeListe.add(holmes);
	}

	public void remove(Kunde holmes)
	{
		kundeListe.remove(holmes);
	}

	public boolean contains(Kunde patient)
	{
		return kundeListe.contains(patient);
	}

	public boolean isEmpty()
	{
		return kundeListe.isEmpty();
	}

	public int size()
	{
		return kundeListe.size();
	}

	public Iterator<Kunde> iterator()
	{
		return kundeListe.iterator();
	}

	public KundeReg finnKunder(String kriterie)
	{
		KundeReg søktKundeListe = new KundeReg();

		søktKundeListe = finnKundeViaNr(kriterie);
		if(søktKundeListe == null || søktKundeListe.isEmpty() )
		{
			søktKundeListe = finnKundeViaNavn(kriterie, ""); // Søker kun på helt navn, ikke delt fornavn og etternavn.
			if(søktKundeListe == null || søktKundeListe.isEmpty())
			{
				søktKundeListe = finnKundeViaTelefon(kriterie);
				if(søktKundeListe == null || søktKundeListe.isEmpty())
				{
					søktKundeListe = finnKundeViaAdresse(kriterie);
					if(søktKundeListe == null || søktKundeListe.isEmpty())
					{
						søktKundeListe = finnKundeViaPostnr(kriterie);
						if(søktKundeListe == null || søktKundeListe.isEmpty())
						{
							søktKundeListe = finnKundeViaBy(kriterie);
						}
					}
				}
			}
		}
		return søktKundeListe;
	}

	public KundeReg finnKundeViaNr(String personNr)
	{
		if(personNr.isEmpty())
			return null;
		Iterator<Kunde> theIterator = iterator();
		Kunde pat;
		KundeReg søktKundeReg = new KundeReg();
		try{
			while(theIterator.hasNext())
			{
				pat = theIterator.next();
				//if(pat.getPersonNr().matches(personNr + ".*"))
				if(pat.getPersonNr().matches(personNr))
				{
					søktKundeReg.add(pat);
					return søktKundeReg;
				}
			}
		}
		catch(NoSuchElementException nsee)
		{
			JOptionPane.showMessageDialog(null, "Feil i KundeReg: finnKundeViaNr fikk NoSuchElementException.",
										"FEIL", JOptionPane.ERROR_MESSAGE);
		}
		return null;
	}

	public KundeReg finnKundeViaKundenr(String kundeNr)
	{
		if(kundeNr.isEmpty())
			return null;
		Iterator<Kunde> theIterator = iterator();
		Kunde kun;
		KundeReg søktKundeReg = new KundeReg();
		try{
			while(theIterator.hasNext())
			{
				kun = theIterator.next();
				//if(pat.getPersonNr().matches(personNr + ".*"))
				if(kun.getKundeNr().matches(kundeNr))
				{
					søktKundeReg.add(kun);
					return søktKundeReg;
				}
			}
		}
		catch(NoSuchElementException nsee)
		{
			JOptionPane.showMessageDialog(null, "Feil i KundeReg: finnKundeViaNr fikk NoSuchElementException.",
										"FEIL", JOptionPane.ERROR_MESSAGE);
		}
		return null;
	}

	public KundeReg finnKundeViaNavn(String firstname, String lastname)
	{
		if(firstname.isEmpty() && lastname.isEmpty())
			return null;
		Iterator<Kunde> theIterator = iterator();
		Kunde pat;
		KundeReg søktKundeReg = new KundeReg();
		try{
			while(theIterator.hasNext())
			{
				pat = theIterator.next();
				if(((pat.getFornavn().toLowerCase() + pat.getEtternavn()).toLowerCase().matches((firstname.toLowerCase() + ".*") + (lastname.toLowerCase() + ".*"))))
				{
					søktKundeReg.add(pat);
				}
				else if(pat.getFornavn().toLowerCase().equals(firstname.toLowerCase() )
						|| pat.getEtternavn().toLowerCase().equals(firstname.toLowerCase() ) )
					søktKundeReg.add(pat);
			}
			return søktKundeReg;
		}
		catch(NoSuchElementException nsee)
		{
			JOptionPane.showMessageDialog(null, "Feil i KundeReg: finnKundeViaNavn fikk NoSuchElementException.",
					"FEIL", JOptionPane.ERROR_MESSAGE);
		}
		return null;
	}

	public KundeReg finnKundeViaNavn(String navn)
	{
		Iterator<Kunde> theIterator = iterator();
		Kunde kun;
		KundeReg søktKundeReg = new KundeReg();
		try{
			while(theIterator.hasNext())
			{
				kun = theIterator.next();
				if(kun.getFornavn().equals(navn) || kun.getEtternavn().equals(navn))
				{
					søktKundeReg.add(kun);
				}
			}
			return søktKundeReg;
		}
		catch(NoSuchElementException nsee){
			JOptionPane.showMessageDialog(null, "Feil i KundeReg (finnKundeViaNavn(1)): No Such Element Exception.",
					"FEIL", JOptionPane.ERROR_MESSAGE);
		}
		return null;
	}



	public KundeReg finnKundeViaTelefon(String tlf)
	{
		if(tlf.isEmpty())
			return null;
		Iterator<Kunde> theIterator = iterator();
		Kunde pat;
		KundeReg søktKundeReg = new KundeReg();
		try{
			while(theIterator.hasNext())
			{
				pat = theIterator.next();
				if(pat.getTelefonNr().matches(tlf + ".*"))
				{
					søktKundeReg.add(pat);
				}
			}
			return søktKundeReg;
		}
		catch(NoSuchElementException nsee)
		{
			JOptionPane.showMessageDialog(null, "Feil i KundeReg: findPatientByTelephone fikk NoSuchElementException.",
					"FEIL", JOptionPane.ERROR_MESSAGE);
		}
		return null;
	}

	public KundeReg finnKundeViaAdresse(String address)
	{
		if(address.isEmpty())
			return null;
		Iterator<Kunde> theIterator = iterator();
		Kunde pat;
		KundeReg søktKundeReg = new KundeReg();
		try{
			while(theIterator.hasNext())
			{
				pat = theIterator.next();
				if(pat.getAdresse().toLowerCase().matches(address.toLowerCase() + ".*"))
				{
					søktKundeReg.add(pat);
				}
			}
			return søktKundeReg;
		}
		catch(NoSuchElementException nsee)
		{
			JOptionPane.showMessageDialog(null, "Feil i KundeReg: findPatientByAddress fikk NoSuchElementException.",
					"FEIL", JOptionPane.ERROR_MESSAGE);
		}
		return null;
	}

	public KundeReg finnKundeViaPostnr(String post)
	{
		if(post.isEmpty())
			return null;
		Iterator<Kunde> theIterator = iterator();
		Kunde kun;
		KundeReg søktKundeReg = new KundeReg();
		try{
			while(theIterator.hasNext())
			{
				kun = theIterator.next();
				if(kun.getPostnr().toLowerCase().matches(post.toLowerCase() + ".*"))
				{
					søktKundeReg.add(kun);
				}
			}
			return søktKundeReg;
		}
		catch(NoSuchElementException nsee)
		{
			JOptionPane.showMessageDialog(null, "Feil i KundeReg: finnKundeViaPostnr fikk NoSuchElementException.",
					"FEIL", JOptionPane.ERROR_MESSAGE);
		}
		return null;
	}

	public KundeReg finnKundeViaBy(String by)
	{
		if(by.isEmpty())
			return null;
		Iterator<Kunde> theIterator = iterator();
		Kunde kun;
		KundeReg søktKundeReg = new KundeReg();
		try{
			while(theIterator.hasNext())
			{
				kun = theIterator.next();
				if(kun.getPoststed().toLowerCase().matches(by.toLowerCase() + ".*"))
				{
					søktKundeReg.add(kun);
				}
			}
			return søktKundeReg;
		}
		catch(NoSuchElementException nsee)
		{
			JOptionPane.showMessageDialog(null, "Feil i KundeReg: findPatientByAddress fikk NoSuchElementException.",
					"FEIL", JOptionPane.ERROR_MESSAGE);
		}
		return null;
	}


	public Comparator<Person> InitCollator()
	{ // Initialiserer kollatoren til lista.
		Comparator<Person> collator;
		return collator = new PersonCollator();
	}

	public void lagreNrNå()
	{
		nrNå = Kunde.getNrNå();
	}

	public void setNåNr()
	{
		Kunde.setNrNå(nrNå);
	}

	public String toString()
	{
		Kunde pat = null;
		Iterator<Kunde> iter = iterator();
		StringBuilder info = new StringBuilder(1000);
		while(iter.hasNext())
		{
			pat = iter.next();
			info.append(pat.toString() + "\n-----------------------------------\n\n");
		}
		return info.toString();
	}
}
