/*Skrevet av Even Nerheim, s199184, sist redigert 18.05.2015
Klassen håndterer hytteforsikringer, setter dem i register og søker i registeret
*/

import java.io.Serializable;
import java.util.*;
import javax.swing.*;

public class HytteForsikringsReg implements Serializable
{
	private static final long serialVersionUID = 42L;

	private TreeSet<HytteForsikring> list;//listen
	private int nrNå;

	public HytteForsikringsReg()
	{
		list = new TreeSet<>(new ForsikringCollator() );//Oppretter forsikringslisten
	}

	public void add(HytteForsikring hyt)		//legger til ny hytte
	{
		list.add(hyt);
	}

	public boolean isEmpty()
	{
		return list.isEmpty();
	}

	public Iterator<HytteForsikring> iterator()
	{
		return list.iterator();
	}

	public boolean contains(HytteForsikring in)
	{
		return list.contains(in);
	}

	public int size()
	{
		return list.size();
	}

	public HytteForsikringsReg finnHytter(String kriterie)		//finner hytter via kriteriet gitt
	{
		HytteForsikringsReg søktHytteListe = new HytteForsikringsReg();
		søktHytteListe = finnHytteViaKundeNr(kriterie);
		if(søktHytteListe == null)
		{
			søktHytteListe = finnHytteViaAdresse(kriterie);
			if(søktHytteListe == null)
			{
				søktHytteListe = finnHytteViaStandard(kriterie);
				if(søktHytteListe == null)
				{
					søktHytteListe = finnHytteViaType(kriterie);
					if(søktHytteListe == null)
					{
						søktHytteListe = finnHytteViaMateriale(kriterie);
						if(søktHytteListe == null)
						{
							return null;
						}
					}
				}
			}
		}
		return søktHytteListe;
	}

	public HytteForsikringsReg finnHytter(int kriterie)
	{
		HytteForsikringsReg søktHytteListe = new HytteForsikringsReg();
		søktHytteListe = finnHytteViaNr(kriterie);
		if(søktHytteListe == null)
		{
			søktHytteListe = finnHytteViaBeløpB(kriterie);
			if(søktHytteListe == null)
			{
				søktHytteListe = finnHytteViaBeløpI(kriterie);
				if(søktHytteListe == null)
				{
					søktHytteListe = finnHytteViaÅr(kriterie);
					if(søktHytteListe == null)
					{
						søktHytteListe = finnHytteViaStørrelse(kriterie);
						if(søktHytteListe == null)
						{
							return null;
						}
					}
				}
			}
		}
		return søktHytteListe;
	}

	public HytteForsikringsReg finnHytteViaKundeNr(String nr)		//finner hytte via kudenr
	{
		Iterator<HytteForsikring> theIterator = iterator();
		HytteForsikring hytte;
		HytteForsikringsReg søktHytteReg = new HytteForsikringsReg();
		try{
			while(theIterator.hasNext())
			{
				hytte = theIterator.next();
				if(hytte.getKundeNr().matches(nr))
				{
					søktHytteReg.add(hytte);
					return søktHytteReg;
				}
			}
			return søktHytteReg;
		}
		catch(NoSuchElementException nsee)
		{
			JOptionPane.showMessageDialog(null, "Feil i AnsattReg (findDoctorByPersonNr): No Such Element Exception.",
											"FEIL", JOptionPane.ERROR_MESSAGE);
		}
		catch(NullPointerException npe)
		{
			JOptionPane.showMessageDialog(null, "Det skjedde en NullPointerException i ForsikringsReg1 findDoctorByPersonNr.", "FEIL", JOptionPane.ERROR_MESSAGE);
		}
		return null;
	}

	public HytteForsikringsReg finnHytteViaAdresse(String adr)		//via adresse
	{
		Iterator<HytteForsikring> theIterator = iterator();
		HytteForsikring hytte;
		HytteForsikringsReg søktHytteReg = new HytteForsikringsReg();
		try{
			while(theIterator.hasNext())
			{
				hytte = theIterator.next();
				if(hytte.getAdresse().matches(adr))
				{
					søktHytteReg.add(hytte);
					return søktHytteReg;
				}
			}
		}
		catch(NoSuchElementException nsee)
		{
			JOptionPane.showMessageDialog(null, "Feil i AnsattReg (findDoctorByPersonNr): No Such Element Exception.",
											"FEIL", JOptionPane.ERROR_MESSAGE);
		}
		catch(NullPointerException npe)
		{
			JOptionPane.showMessageDialog(null, "Det skjedde en NullPointerException i ForsikringsReg1 findDoctorByPersonNr.", "FEIL", JOptionPane.ERROR_MESSAGE);
		}
		return null;
	}

	public HytteForsikringsReg finnHytteViaStandard(String stand)		//via hyttestanbdard
	{
		Iterator<HytteForsikring> theIterator = iterator();
		HytteForsikring hytte;
		HytteForsikringsReg søktHytteReg = new HytteForsikringsReg();
		try{
			while(theIterator.hasNext())
			{
				hytte = theIterator.next();
				if(hytte.getStandard().matches(stand))
				{
					søktHytteReg.add(hytte);
					return søktHytteReg;
				}
			}
			return søktHytteReg;
		}
		catch(NoSuchElementException nsee)
		{
			JOptionPane.showMessageDialog(null, "Feil i AnsattReg (findDoctorByPersonNr): No Such Element Exception.",
											"FEIL", JOptionPane.ERROR_MESSAGE);
		}
		catch(NullPointerException npe)
		{
			JOptionPane.showMessageDialog(null, "Det skjedde en NullPointerException i ForsikringsReg1 findDoctorByPersonNr.", "FEIL", JOptionPane.ERROR_MESSAGE);
		}
		return null;
	}

	public HytteForsikringsReg finnHytteViaType(String typ)			//via boligtype
	{
		Iterator<HytteForsikring> theIterator = iterator();
		HytteForsikring hytte;
		HytteForsikringsReg søktHytteReg = new HytteForsikringsReg();
		try{
			while(theIterator.hasNext())
			{
				hytte = theIterator.next();
				if(hytte.getBoligtype().matches(typ))
				{
					søktHytteReg.add(hytte);
					return søktHytteReg;
				}
			}
			return søktHytteReg;
		}
		catch(NoSuchElementException nsee)
		{
			JOptionPane.showMessageDialog(null, "Feil i AnsattReg (findDoctorByPersonNr): No Such Element Exception.",
											"FEIL", JOptionPane.ERROR_MESSAGE);
		}
		catch(NullPointerException npe)
		{
			JOptionPane.showMessageDialog(null, "Det skjedde en NullPointerException i ForsikringsReg1 findDoctorByPersonNr.", "FEIL", JOptionPane.ERROR_MESSAGE);
		}
		return null;
	}

	public HytteForsikringsReg finnHytteViaMateriale(String m)		//via byggemateriale
	{
		Iterator<HytteForsikring> theIterator = iterator();
		HytteForsikring hytte;
		HytteForsikringsReg søktHusReg = new HytteForsikringsReg();
		try{
			while(theIterator.hasNext())
			{
				hytte = theIterator.next();
				if(hytte.getMateriale().matches(m))
				{
					søktHusReg.add(hytte);
					return søktHusReg;
				}
			}
			return søktHusReg;
		}
		catch(NoSuchElementException nsee)
		{
			JOptionPane.showMessageDialog(null, "Feil i AnsattReg (findDoctorByPersonNr): No Such Element Exception.",
											"FEIL", JOptionPane.ERROR_MESSAGE);
		}
		catch(NullPointerException npe)
		{
			JOptionPane.showMessageDialog(null, "Det skjedde en NullPointerException i ForsikringsReg1 findDoctorByPersonNr.", "FEIL", JOptionPane.ERROR_MESSAGE);
		}
		return null;
	}

	public HytteForsikringsReg finnHytteViaNr(int nr)		//via forsikringsnr
	{
		Iterator<HytteForsikring> theIterator = iterator();
		HytteForsikring hytte;
		HytteForsikringsReg søktHytteReg = new HytteForsikringsReg();
		try{
			while(theIterator.hasNext())
			{
				hytte = theIterator.next();
				if(hytte.getForsikringsNr() == nr)
				{
					søktHytteReg.add(hytte);
					return søktHytteReg;
				}
			}
		}
		catch(NoSuchElementException nsee)
		{
			JOptionPane.showMessageDialog(null, "Feil i AnsattReg (findDoctorByPersonNr): No Such Element Exception.",
											"FEIL", JOptionPane.ERROR_MESSAGE);
		}
		catch(NullPointerException npe)
		{
			JOptionPane.showMessageDialog(null, "Det skjedde en NullPointerException i ForsikringsReg1 findDoctorByPersonNr.", "FEIL", JOptionPane.ERROR_MESSAGE);
		}
		return null;
	}

	public HytteForsikringsReg finnHytteViaBeløpB(int b)		//Via byggforsikringsbeløp
	{
		Iterator<HytteForsikring> theIterator = iterator();
		HytteForsikring hytte;
		HytteForsikringsReg søktHytteReg = new HytteForsikringsReg();
		try{
			while(theIterator.hasNext())
			{
				hytte = theIterator.next();
				if(hytte.getBeløpBygg() == b)
				{
					søktHytteReg.add(hytte);
					return søktHytteReg;
				}
			}
			return søktHytteReg;
		}
		catch(NoSuchElementException nsee)
		{
			JOptionPane.showMessageDialog(null, "Feil i AnsattReg (findDoctorByPersonNr): No Such Element Exception.",
											"FEIL", JOptionPane.ERROR_MESSAGE);
		}
		catch(NullPointerException npe)
		{
			JOptionPane.showMessageDialog(null, "Det skjedde en NullPointerException i ForsikringsReg1 findDoctorByPersonNr.", "FEIL", JOptionPane.ERROR_MESSAGE);
		}
		return null;
	}

	public HytteForsikringsReg finnHytteViaBeløpI(int i)		//via innboforsikringsbeløp
	{
		Iterator<HytteForsikring> theIterator = iterator();
		HytteForsikring hytte;
		HytteForsikringsReg søktHytteReg = new HytteForsikringsReg();
		try{
			while(theIterator.hasNext())
			{
				hytte = theIterator.next();
				if(hytte.getBeløpInnbo() == i)
				{
					søktHytteReg.add(hytte);
					return søktHytteReg;
				}
			}
			return søktHytteReg;
		}
		catch(NoSuchElementException nsee)
		{
			JOptionPane.showMessageDialog(null, "Feil i AnsattReg (findDoctorByPersonNr): No Such Element Exception.",
											"FEIL", JOptionPane.ERROR_MESSAGE);
		}
		catch(NullPointerException npe)
		{
			JOptionPane.showMessageDialog(null, "Det skjedde en NullPointerException i ForsikringsReg1 findDoctorByPersonNr.", "FEIL", JOptionPane.ERROR_MESSAGE);
		}
		return null;
	}

	public HytteForsikringsReg finnHytteViaÅr(int år)			//via byggeår
	{
		Iterator<HytteForsikring> theIterator = iterator();
		HytteForsikring hytte;
		HytteForsikringsReg søktHytteReg = new HytteForsikringsReg();
		try{
			while(theIterator.hasNext())
			{
				hytte = theIterator.next();
				if(hytte.getByggeår() == år)
				{
					søktHytteReg.add(hytte);
					return søktHytteReg;
				}
			}
			return søktHytteReg;
		}
		catch(NoSuchElementException nsee)
		{
			JOptionPane.showMessageDialog(null, "Feil i AnsattReg (findDoctorByPersonNr): No Such Element Exception.",
											"FEIL", JOptionPane.ERROR_MESSAGE);
		}
		catch(NullPointerException npe)
		{
			JOptionPane.showMessageDialog(null, "Det skjedde en NullPointerException i ForsikringsReg1 findDoctorByPersonNr.", "FEIL", JOptionPane.ERROR_MESSAGE);
		}
		return null;
	}

	public HytteForsikringsReg finnHytteViaStørrelse(int s)
	{
		Iterator<HytteForsikring> theIterator = iterator();
		HytteForsikring hytte;
		HytteForsikringsReg søktHytteReg = new HytteForsikringsReg();
		try{
			while(theIterator.hasNext())
			{
				hytte = theIterator.next();
				if(hytte.getKvadratmeter() == s)
				{
					søktHytteReg.add(hytte);
					return søktHytteReg;
				}
			}
			return søktHytteReg;
		}
		catch(NoSuchElementException nsee)
		{
			JOptionPane.showMessageDialog(null, "Feil i AnsattReg (findDoctorByPersonNr): No Such Element Exception.",
											"FEIL", JOptionPane.ERROR_MESSAGE);
		}
		catch(NullPointerException npe)
		{
			JOptionPane.showMessageDialog(null, "Det skjedde en NullPointerException i ForsikringsReg1 findDoctorByPersonNr.", "FEIL", JOptionPane.ERROR_MESSAGE);
		}
		return null;
	}

	public void lagreNåNr()//nødvendig for skriving/lagring til fil
	{
		nrNå = HytteForsikring.getNrNå();
	}

	public void setNåNr()//nødvendig for skriving/lagring til fil
	{
		HytteForsikring.setNrNå(nrNå);
	}

	public String toString()
	{
		StringBuilder res = new StringBuilder();
		Iterator<HytteForsikring> ite = list.iterator();
		while(ite.hasNext() )
			res.append(ite.next().toString() );
			res.append("\n");
		return res.toString();
	}
}
