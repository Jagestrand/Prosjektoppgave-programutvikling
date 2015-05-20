import java.util.*;
import java.io.Serializable;

import javax.swing.JOptionPane;

public class AnsattReg implements Serializable
{
	private static final long serialVersionUID = 42L;
	private TreeSet<Ansatt> ansliste;
	private int nrNå;
	private Ansatt første;

	public AnsattReg()
	{
		ansliste = new TreeSet<Ansatt>(InitCollator());		//setter ansatte i register i rekkefølge
	}

	public void add(Ansatt watson)		//legger til ny ansatt
	{
		ansliste.add(watson);
	}

	public void remove(Ansatt watson)	//fjerner ansatt
	{
		ansliste.remove(watson);
	}

	public boolean contains(Ansatt ans)		//ser etter ansatt
	{
		return ansliste.contains(ans);
	}

	public boolean isEmpty()			//hvis ansattregisteret er tomt
	{
		return ansliste.isEmpty();
	}

	public int size()
	{
		return ansliste.size();
	}

	public Iterator<Ansatt> iterator()
	{
		return ansliste.iterator();
	}

	public AnsattReg finnAnsatte(String kriterie)		//finner ansatt via kriteriet gitt
	{
		AnsattReg søktAnsListe = new AnsattReg();
		søktAnsListe = finnAnsattAvPersonNr(kriterie);
		if(søktAnsListe == null)
		{
			søktAnsListe = finnAnsattAvName(kriterie);
			if(søktAnsListe == null)
			{
				søktAnsListe = finnAnsattAvTelephone(kriterie);
				if(søktAnsListe == null)
				{
					søktAnsListe = finnAnsattAvAvdeling(kriterie);
					if(søktAnsListe == null)
					{
						return null;
					}
				}
			}
		}
		return søktAnsListe;
	}

	public AnsattReg finnAnsattAvPersonNr(String personNr)		//finenr ansatt via personnummeret
	{
		Iterator<Ansatt> theIterator = iterator();
		Ansatt ans;
		AnsattReg søktAnsReg = new AnsattReg();
		try{
			while(theIterator.hasNext())
			{
				ans = theIterator.next();
				if(ans.getPersonNr().matches(personNr))
				{
					søktAnsReg.add(ans);
					return søktAnsReg;
				}
			}
		}
		catch(NoSuchElementException nsee)
		{
			JOptionPane.showMessageDialog(null, "Feil i AnsattReg (finnAnsattAvPersonNr): No Such Element Exception.",
											"FEIL", JOptionPane.ERROR_MESSAGE);
		}
		catch(NullPointerException npe)
		{
			JOptionPane.showMessageDialog(null, "Det skjedde en NullPointerException i AnsattReg finnAnsattAvPersonNr.", "FEIL", JOptionPane.ERROR_MESSAGE);
		}
		return null;
	}

	public AnsattReg finnAnsattAvName(String name)			//via navn
	{
		Iterator<Ansatt> theIterator = iterator();
		Ansatt ans;
		AnsattReg søktAnsReg = new AnsattReg();
		try{
			while(theIterator.hasNext())
			{
				ans = theIterator.next();
				if(ans.getFornavn().equals(name) || ans.getEtternavn().equals(name))
				{
					søktAnsReg.add(ans);
				}
			}
			return søktAnsReg;
		}
		catch(NoSuchElementException nsee){
			JOptionPane.showMessageDialog(null, "Feil i DoctorReg (finnAnsattAvName): No Such Element Exception.",
					"FEIL", JOptionPane.ERROR_MESSAGE);
		}
		return null;
	}

	public AnsattReg finnAnsattAvTelephone(String telephone)	//via tlfnummer
	{
		Iterator<Ansatt> theIterator = iterator();
		Ansatt ans;
		AnsattReg søktAnsReg = new AnsattReg();
		try{
			while(theIterator.hasNext())
			{
				ans = theIterator.next();
				if(ans.getTelefonNr().matches(telephone))
				{
					søktAnsReg.add(ans);
				}
			}
			return søktAnsReg;
		}
		catch(NoSuchElementException nsee){
			JOptionPane.showMessageDialog(null, "Feil i AnsattReg (finnAnsattAvTelephone): No Such Element Exception.",
					"FEIL", JOptionPane.ERROR_MESSAGE);
		}
		return null;
	}

	public AnsattReg finnAnsattAvAvdeling(String avdeling)	//via avdeling
	{
		Iterator<Ansatt> theIterator = iterator();
		Ansatt ans;
		AnsattReg søktAnsReg = new AnsattReg();
		try{
			while(theIterator.hasNext())
			{
				ans = theIterator.next();
				if(ans.getAvdeling().matches(avdeling))
				{
					søktAnsReg.add(ans);
				}
			}
			return søktAnsReg;
		}
		catch(NoSuchElementException nsee){
			JOptionPane.showMessageDialog(null, "Feil i AnsattReg (finnAnsattAvAvdeling): No Such Element Exception.",
					"FEIL", JOptionPane.ERROR_MESSAGE);
		}
		return null;
	}

	public AnsattReg finnAnsattAvAnsattNr(String ansattnr)	//via ansattnr
	{
		Iterator<Ansatt> theIterator = iterator();
		Ansatt ans;
		AnsattReg søktAnsReg = new AnsattReg();
		try{
			while(theIterator.hasNext())
			{
				ans = theIterator.next();
				if(ans.getAnsattNr().matches(ansattnr))
				{
					søktAnsReg.add(ans);
				}
			}
			return søktAnsReg;
		}
		catch(NoSuchElementException nsee){
			JOptionPane.showMessageDialog(null, "Feil i AnsattReg (finnAnsattAvAnsattNr): No Such Element Exception.",
					"FEIL", JOptionPane.ERROR_MESSAGE);
		}
		return null;
	}

	public Comparator<Person> InitCollator()		//setter opp ny sammenligner og rekkefølgesetter
	{ // Initialiserer kollator
		Comparator<Person> collator;
		return collator = new PersonCollator();
	}

	public void lagreNåNr()		//til skriving/lagring av fil
	{
		nrNå = Ansatt.getNrNå();
	}

	public void setNåNr()		//til skriving/lagring av fil
	{
		Ansatt.setNrNå(nrNå);
	}

	public String toString()
	{
		StringBuilder info = new StringBuilder();
		Iterator<Ansatt> iter = iterator();
		while(iter.hasNext() )
		{
			info.append(iter.next().toString() + "\n-----------------------------------\n\n");
		}
		return info.toString();
	}
} // End of class
