/*Skrevet av Even Nerheim, s199184, sist rediger 20.05.2015
Register for bilforsikringer, legger til biler, søker i registeret
*/

import java.io.Serializable;
import java.util.*;
import javax.swing.*;

public class BilForsikringsReg implements Serializable
{
	private static final long serialVersionUID = 42L;

	private TreeSet<BilForsikring> list;//listen
	private int nrNå;

	public BilForsikringsReg()
	{
		list = new TreeSet<>(new ForsikringCollator());//Oppretter forsikringslisten
	}

	public void add(BilForsikring pre)	//public boolean add(Forsikring pre)
	{
		list.add(pre);		//return list.add(pre);
	}

	public boolean isEmpty()
	{
		return list.isEmpty();
	}

	public Iterator<BilForsikring> iterator()
	{
		return list.iterator();
	}

	public boolean contains(BilForsikring in)
	{
		return list.contains(in);
	}

	public int size()
	{
		return list.size();
	}

	public BilForsikringsReg finnBiler(String kriterie)
	{
		BilForsikringsReg søktBilListe = new BilForsikringsReg();
		søktBilListe = finnBilViaKundeNr(kriterie);
		if(søktBilListe == null)
		{
			søktBilListe = finnBilViaEier(kriterie);
			if(søktBilListe == null)
			{
				søktBilListe = finnBilViaRegNr(kriterie);
				if(søktBilListe == null)
				{
					søktBilListe = finnBilViaType(kriterie);
					if(søktBilListe == null)
					{
						søktBilListe = finnBilViaModell(kriterie);
						if(søktBilListe == null)
						{
							return null;
						}
					}
				}
			}
		}
		return søktBilListe;
	}

	public BilForsikringsReg finnBiler(int kriterie)
	{
		BilForsikringsReg søktBilListe = new BilForsikringsReg();
		søktBilListe = finnBilViaNr(kriterie);
		if(søktBilListe == null)
		{
			søktBilListe = finnBilViaÅr(kriterie);
			if(søktBilListe == null)
			{
				søktBilListe = finnBilViaKjøring(kriterie);
				if(søktBilListe == null)
				{
					return null;
				}
			}
		}
		return søktBilListe;
	}

	public BilForsikringsReg finnBilViaKundeNr(String nr)
	{
		Iterator<BilForsikring> theIterator = iterator();
		BilForsikring bil;
		BilForsikringsReg søktBilReg = new BilForsikringsReg();
		try{
			while(theIterator.hasNext())
			{
				bil = theIterator.next();
				if(bil.getKundeNr().matches(nr))
				{
					søktBilReg.add(bil);
					return søktBilReg;
				}
			}
			return søktBilReg;
		}
		catch(NoSuchElementException nsee)
		{
			JOptionPane.showMessageDialog(null, "Feil i AnsattReg (findDoctorByPersonNr): No Such Element Exception.",
											"FEIL", JOptionPane.ERROR_MESSAGE);
		}
		catch(NullPointerException npe)
		{
			JOptionPane.showMessageDialog(null, "Det skjedde en NullPointerException i BilForsikringsReg findDoctorByPersonNr.", "FEIL", JOptionPane.ERROR_MESSAGE);
		}
		return null;
	}

	public BilForsikringsReg finnBilViaEier(String eier)
	{
		Iterator<BilForsikring> theIterator = iterator();
		BilForsikring bil;
		BilForsikringsReg søktBilReg = new BilForsikringsReg();
		try{
			while(theIterator.hasNext())
			{
				bil = theIterator.next();
				if(bil.getEiernavn().matches(eier))
				{
					søktBilReg.add(bil);
					return søktBilReg;
				}
			}
			return søktBilReg;
		}
		catch(NoSuchElementException nsee)
		{
			JOptionPane.showMessageDialog(null, "Feil i AnsattReg (findDoctorByPersonNr): No Such Element Exception.",
											"FEIL", JOptionPane.ERROR_MESSAGE);
		}
		catch(NullPointerException npe)
		{
			JOptionPane.showMessageDialog(null, "Det skjedde en NullPointerException i BilForsikringsReg findDoctorByPersonNr.", "FEIL", JOptionPane.ERROR_MESSAGE);
		}
		return null;
	}

	public BilForsikringsReg finnBilViaType(String type)
	{
		Iterator<BilForsikring> theIterator = iterator();
		BilForsikring bil;
		BilForsikringsReg søktBilReg = new BilForsikringsReg();
		try{
			while(theIterator.hasNext())
			{
				bil = theIterator.next();
				if(bil.getType().matches(type))
				{
					søktBilReg.add(bil);
					return søktBilReg;
				}
			}
			return søktBilReg;
		}
		catch(NoSuchElementException nsee)
		{
			JOptionPane.showMessageDialog(null, "Feil i AnsattReg (findDoctorByPersonNr): No Such Element Exception.",
											"FEIL", JOptionPane.ERROR_MESSAGE);
		}
		catch(NullPointerException npe)
		{
			JOptionPane.showMessageDialog(null, "Det skjedde en NullPointerException i BilForsikringsReg findDoctorByPersonNr.", "FEIL", JOptionPane.ERROR_MESSAGE);
		}
		return null;
	}

	public BilForsikringsReg finnBilViaModell(String m)
	{
		Iterator<BilForsikring> theIterator = iterator();
		BilForsikring bil;
		BilForsikringsReg søktBilReg = new BilForsikringsReg();
		try{
			while(theIterator.hasNext())
			{
				bil = theIterator.next();
				if(bil.getModell().matches(m))
				{
					søktBilReg.add(bil);
					return søktBilReg;
				}
			}
			return søktBilReg;
		}
		catch(NoSuchElementException nsee)
		{
			JOptionPane.showMessageDialog(null, "Feil i AnsattReg (findDoctorByPersonNr): No Such Element Exception.",
											"FEIL", JOptionPane.ERROR_MESSAGE);
		}
		catch(NullPointerException npe)
		{
			JOptionPane.showMessageDialog(null, "Det skjedde en NullPointerException i BilForsikringsReg findDoctorByPersonNr.", "FEIL", JOptionPane.ERROR_MESSAGE);
		}
		return null;
	}

	public BilForsikringsReg finnBilViaRegNr(String nr)
	{
		Iterator<BilForsikring> theIterator = iterator();
		BilForsikring bil;
		BilForsikringsReg søktBilReg = new BilForsikringsReg();
		try{
			while(theIterator.hasNext())
			{
				bil = theIterator.next();
				if(bil.getRegistreringsnr().matches(nr))
				{
					søktBilReg.add(bil);
					return søktBilReg;
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
			JOptionPane.showMessageDialog(null, "Det skjedde en NullPointerException i BilForsikringsReg findDoctorByPersonNr.", "FEIL", JOptionPane.ERROR_MESSAGE);
		}
		return null;
	}

	public BilForsikringsReg finnBilViaNr(int nr)
	{
		Iterator<BilForsikring> theIterator = iterator();
		BilForsikring bil;
		BilForsikringsReg søktBilReg = new BilForsikringsReg();
		try{
			while(theIterator.hasNext())
			{
				bil = theIterator.next();
				if(bil.getForsikringsNr() == nr)
				{
					søktBilReg.add(bil);
					return søktBilReg;
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
			JOptionPane.showMessageDialog(null, "Det skjedde en NullPointerException i BilForsikringsReg findDoctorByPersonNr.", "FEIL", JOptionPane.ERROR_MESSAGE);
		}
		return null;
	}

	public BilForsikringsReg finnBilViaÅr(int år)
	{
		Iterator<BilForsikring> theIterator = iterator();
		BilForsikring bil;
		BilForsikringsReg søktBilReg = new BilForsikringsReg();
		try{
			while(theIterator.hasNext())
			{
				bil = theIterator.next();
				if(bil.getRegistreringsår() == år)
				{
					søktBilReg.add(bil);
					return søktBilReg;
				}
			}
			return søktBilReg;
		}
		catch(NoSuchElementException nsee)
		{
			JOptionPane.showMessageDialog(null, "Feil i AnsattReg (findDoctorByPersonNr): No Such Element Exception.",
											"FEIL", JOptionPane.ERROR_MESSAGE);
		}
		catch(NullPointerException npe)
		{
			JOptionPane.showMessageDialog(null, "Det skjedde en NullPointerException i BilForsikringsReg findDoctorByPersonNr.", "FEIL", JOptionPane.ERROR_MESSAGE);
		}
		return null;
	}

	public BilForsikringsReg finnBilViaKjøring(int km)
	{
		Iterator<BilForsikring> theIterator = iterator();
		BilForsikring bil;
		BilForsikringsReg søktBilReg = new BilForsikringsReg();
		try{
			while(theIterator.hasNext())
			{
				bil = theIterator.next();
				if(bil.getKjørelengde() == km)
				{
					søktBilReg.add(bil);
					return søktBilReg;
				}
			}
			return søktBilReg;
		}
		catch(NoSuchElementException nsee)
		{
			JOptionPane.showMessageDialog(null, "Feil i AnsattReg (findDoctorByPersonNr): No Such Element Exception.",
											"FEIL", JOptionPane.ERROR_MESSAGE);
		}
		catch(NullPointerException npe)
		{
			JOptionPane.showMessageDialog(null, "Det skjedde en NullPointerException i BilForsikringsReg findDoctorByPersonNr.", "FEIL", JOptionPane.ERROR_MESSAGE);
		}
		return null;
	}

	public Comparator<Forsikring> InitCollator()
	{
		Comparator<Forsikring> collator;
		return collator = new ForsikringCollator();
	}

	public void lagreNåNr()//nødvendig for skriving/lagring til fil
	{
		nrNå = BilForsikring.getNrNå();
	}

	public void setNåNr()//nødvendig for skriving/lagring til fil
	{
		BilForsikring.setNrNå(nrNå);
	}

	public String toString()
	{
		StringBuilder res = new StringBuilder();
		Iterator<BilForsikring> ite = list.iterator();
		while(ite.hasNext() )
			res.append(ite.next().toString() );
			res.append("\n");
		return res.toString();
	}
}
