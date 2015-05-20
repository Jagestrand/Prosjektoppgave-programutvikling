/*Skrevet av Even Nerheim, s199184, sist redigert 18.05.2015
Klassen håndterer husforsikringer, setter dem i register og søker i registeret
*/

import java.io.Serializable;
import java.util.*;
import javax.swing.*;

public class HusForsikringsReg implements Serializable
{
	private static final long serialVersionUID = 42L;

	private TreeSet<HusForsikring> list;//listen
	private int nrNå;

	public HusForsikringsReg()
	{
		list = new TreeSet<>(new ForsikringCollator() );//Oppretter forsikringslisten
	}

	public boolean add(HusForsikring pre)
	{
		return list.add(pre);
	}

	public boolean isEmpty()
	{
		return list.isEmpty();
	}

	public Iterator<HusForsikring> iterator()
	{
		return list.iterator();
	}

	public boolean contains(HusForsikring in)
	{
		return list.contains(in);
	}

	public int size()
	{
		return list.size();
	}

	public HusForsikringsReg finnHus(String kriterie)
	{
		HusForsikringsReg søktHusListe = new HusForsikringsReg();
		søktHusListe = finnHusViaKundeNr(kriterie);
		if(søktHusListe == null)
		{
			søktHusListe = finnHusViaAdresse(kriterie);
			if(søktHusListe == null)
			{
				søktHusListe = finnHusViaStandard(kriterie);
				if(søktHusListe == null)
				{
					søktHusListe = finnHusViaType(kriterie);
					if(søktHusListe == null)
					{
						søktHusListe = finnHusViaMateriale(kriterie);
						if(søktHusListe == null)
						{
							return null;
						}
					}
				}
			}
		}
		return søktHusListe;
	}

	public HusForsikringsReg finnHus(int kriterie)
	{
		HusForsikringsReg søktHusListe = new HusForsikringsReg();
		søktHusListe = finnHusViaNr(kriterie);
		if(søktHusListe == null)
		{
			søktHusListe = finnHusViaBeløpB(kriterie);
			if(søktHusListe == null)
			{
				søktHusListe = finnHusViaBeløpI(kriterie);
				if(søktHusListe == null)
				{
					søktHusListe = finnHusViaÅr(kriterie);
					if(søktHusListe == null)
					{
						søktHusListe = finnHusViaStørrelse(kriterie);
						if(søktHusListe == null)
						{
							return null;
						}
					}
				}
			}
		}
		return søktHusListe;
	}

	public HusForsikringsReg finnHusViaKundeNr(String nr)
	{
		Iterator<HusForsikring> theIterator = iterator();
		HusForsikring hus;
		HusForsikringsReg søktHusReg = new HusForsikringsReg();
		try{
			while(theIterator.hasNext())
			{
				hus = theIterator.next();
				if(hus.getKundeNr().matches(nr))
				{
					søktHusReg.add(hus);
					return søktHusReg;
				}
			}
			return søktHusReg;
		}
		catch(NoSuchElementException nsee)
		{
			//JOptionPane.showMessageDialog(null, "Feil i HusForsikringsReg (findDoctorByPersonNr): No Such Element Exception.",
			//								"FEIL", JOptionPane.ERROR_MESSAGE);
		}
		catch(NullPointerException npe)
		{
			//JOptionPane.showMessageDialog(null, "Det skjedde en NullPointerException i ForsikringsReg1 findDoctorByPersonNr.", "FEIL", JOptionPane.ERROR_MESSAGE);
		}
		return null;
	}

	public HusForsikringsReg finnHusViaAdresse(String adr)
	{
		Iterator<HusForsikring> theIterator = iterator();
		HusForsikring hus;
		HusForsikringsReg søktHusReg = new HusForsikringsReg();
		try{
			while(theIterator.hasNext())
			{
				hus = theIterator.next();
				if(hus.getAdresse().matches(adr))
				{
					søktHusReg.add(hus);
					return søktHusReg;
				}
			}
		}
		catch(NoSuchElementException nsee)
		{
			//JOptionPane.showMessageDialog(null, "Feil i AnsattReg (findDoctorByPersonNr): No Such Element Exception.", "FEIL", JOptionPane.ERROR_MESSAGE);
		}
		catch(NullPointerException npe)
		{
			//JOptionPane.showMessageDialog(null, "Det skjedde en NullPointerException i ForsikringsReg1 findDoctorByPersonNr.", "FEIL", JOptionPane.ERROR_MESSAGE);
		}
		return null;
	}

	public HusForsikringsReg finnHusViaStandard(String stand)
	{
		Iterator<HusForsikring> theIterator = iterator();
		HusForsikring hus;
		HusForsikringsReg søktHusReg = new HusForsikringsReg();
		try{
			while(theIterator.hasNext())
			{
				hus = theIterator.next();
				if(hus.getStandard().matches(stand))
				{
					søktHusReg.add(hus);
					return søktHusReg;
				}
			}
			return søktHusReg;
		}
		catch(NoSuchElementException nsee)
		{
			//JOptionPane.showMessageDialog(null, "Feil i AnsattReg (findDoctorByPersonNr): No Such Element Exception.", "FEIL", JOptionPane.ERROR_MESSAGE);
		}
		catch(NullPointerException npe)
		{
			//JOptionPane.showMessageDialog(null, "Det skjedde en NullPointerException i ForsikringsReg1 findDoctorByPersonNr.", "FEIL", JOptionPane.ERROR_MESSAGE);
		}
		return null;
	}

	public HusForsikringsReg finnHusViaType(String typ)
	{
		Iterator<HusForsikring> theIterator = iterator();
		HusForsikring hus;
		HusForsikringsReg søktHusReg = new HusForsikringsReg();
		try{
			while(theIterator.hasNext())
			{
				hus = theIterator.next();
				if(hus.getBoligtype().matches(typ))
				{
					søktHusReg.add(hus);
					return søktHusReg;
				}
			}
			return søktHusReg;
		}
		catch(NoSuchElementException nsee)
		{
			//JOptionPane.showMessageDialog(null, "Feil i AnsattReg (findDoctorByPersonNr): No Such Element Exception.", "FEIL", JOptionPane.ERROR_MESSAGE);
		}
		catch(NullPointerException npe)
		{
			//JOptionPane.showMessageDialog(null, "Det skjedde en NullPointerException i ForsikringsReg1 findDoctorByPersonNr.", "FEIL", JOptionPane.ERROR_MESSAGE);
		}
		return null;
	}

	public HusForsikringsReg finnHusViaMateriale(String m)
	{
		Iterator<HusForsikring> theIterator = iterator();
		HusForsikring hus;
		HusForsikringsReg søktHusReg = new HusForsikringsReg();
		try{
			while(theIterator.hasNext())
			{
				hus = theIterator.next();
				if(hus.getMateriale().matches(m))
				{
					søktHusReg.add(hus);
					return søktHusReg;
				}
			}
			return søktHusReg;
		}
		catch(NoSuchElementException nsee)
		{
			//JOptionPane.showMessageDialog(null, "Feil i AnsattReg (findDoctorByPersonNr): No Such Element Exception.", "FEIL", JOptionPane.ERROR_MESSAGE);
		}
		catch(NullPointerException npe)
		{
			//JOptionPane.showMessageDialog(null, "Det skjedde en NullPointerException i ForsikringsReg1 findDoctorByPersonNr.", "FEIL", JOptionPane.ERROR_MESSAGE);
		}
		return null;
	}

	public HusForsikringsReg finnHusViaNr(int nr)
	{
		Iterator<HusForsikring> theIterator = iterator();
		HusForsikring hus;
		HusForsikringsReg søktHusReg = new HusForsikringsReg();
		try{
			while(theIterator.hasNext())
			{
				hus = theIterator.next();
				if(hus.getForsikringsNr() == nr)
				{
					søktHusReg.add(hus);
					return søktHusReg;
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

	public HusForsikringsReg finnHusViaBeløpB(int b)
	{
		Iterator<HusForsikring> theIterator = iterator();
		HusForsikring hus;
		HusForsikringsReg søktHusReg = new HusForsikringsReg();
		try{
			while(theIterator.hasNext())
			{
				hus = theIterator.next();
				if(hus.getBeløpBygg() == b)
				{
					søktHusReg.add(hus);
					return søktHusReg;
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

	public HusForsikringsReg finnHusViaBeløpI(int i)
	{
		Iterator<HusForsikring> theIterator = iterator();
		HusForsikring hus;
		HusForsikringsReg søktHusReg = new HusForsikringsReg();
		try{
			while(theIterator.hasNext())
			{
				hus = theIterator.next();
				if(hus.getBeløpInnbo() == i)
				{
					søktHusReg.add(hus);
					return søktHusReg;
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

	public HusForsikringsReg finnHusViaÅr(int år)
	{
		Iterator<HusForsikring> theIterator = iterator();
		HusForsikring hus;
		HusForsikringsReg søktHusReg = new HusForsikringsReg();
		try{
			while(theIterator.hasNext())
			{
				hus = theIterator.next();
				if(hus.getByggeår() == år)
				{
					søktHusReg.add(hus);
					return søktHusReg;
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

	public HusForsikringsReg finnHusViaStørrelse(int s)
	{
		Iterator<HusForsikring> theIterator = iterator();
		HusForsikring hus;
		HusForsikringsReg søktHusReg = new HusForsikringsReg();
		try{
			while(theIterator.hasNext())
			{
				hus = theIterator.next();
				if(hus.getKvadratmeter() == s)
				{
					søktHusReg.add(hus);
					return søktHusReg;
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

	public void lagreNåNr()//nødvendig for skriving/lagring til fil
	{
		nrNå = HusForsikring.getNrNå();
	}

	public void setNåNr()//nødvendig for skriving/lagring til fil
	{
		HusForsikring.setNrNå(nrNå);
	}

	public String toString()
	{
		StringBuilder res = new StringBuilder();
		Iterator<HusForsikring> ite = list.iterator();
		while(ite.hasNext() )
			res.append(ite.next().toString() );
			res.append("\n");
		return res.toString();
	}
}
