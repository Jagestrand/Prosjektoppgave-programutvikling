import java.io.Serializable;
import java.util.*;
import javax.swing.*;

public class HytteForsikringsReg implements Serializable
{
	private static final long serialVersionUID = 42L;

	private TreeSet<HytteForsikring1> list;//listen
	private int nrNå;

	public HytteForsikringsReg()
	{
		list = new TreeSet<>(new ForsikringCollator() );//Oppretter forsikringslisten
	}

	public void add(HytteForsikring1 hyt)
	{
		list.add(hyt);
	}

	public boolean isEmpty()
	{
		return list.isEmpty();
	}

	public Iterator<HytteForsikring1> iterator()
	{
		return list.iterator();
	}

	public boolean contains(HytteForsikring1 in)
	{
		return list.contains(in);
	}

	public int size()
	{
		return list.size();
	}

	public HytteForsikringsReg finnHytter(String kriterie)
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

	public HytteForsikringsReg finnHytteViaKundeNr(String nr)
	{
		Iterator<HytteForsikring1> theIterator = iterator();
		HytteForsikring1 hytte;
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

	public HytteForsikringsReg finnHytteViaAdresse(String adr)
	{
		Iterator<HytteForsikring1> theIterator = iterator();
		HytteForsikring1 hytte;
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

	public HytteForsikringsReg finnHytteViaStandard(String stand)
	{
		Iterator<HytteForsikring1> theIterator = iterator();
		HytteForsikring1 hytte;
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

	public HytteForsikringsReg finnHytteViaType(String typ)
	{
		Iterator<HytteForsikring1> theIterator = iterator();
		HytteForsikring1 hytte;
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

	public HytteForsikringsReg finnHytteViaMateriale(String m)
	{
		Iterator<HytteForsikring1> theIterator = iterator();
		HytteForsikring1 hytte;
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

	public HytteForsikringsReg finnHytteViaNr(int nr)
	{
		Iterator<HytteForsikring1> theIterator = iterator();
		HytteForsikring1 hytte;
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

	public HytteForsikringsReg finnHytteViaBeløpB(int b)
	{
		Iterator<HytteForsikring1> theIterator = iterator();
		HytteForsikring1 hytte;
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

	public HytteForsikringsReg finnHytteViaBeløpI(int i)
	{
		Iterator<HytteForsikring1> theIterator = iterator();
		HytteForsikring1 hytte;
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

	public HytteForsikringsReg finnHytteViaÅr(int år)
	{
		Iterator<HytteForsikring1> theIterator = iterator();
		HytteForsikring1 hytte;
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
		Iterator<HytteForsikring1> theIterator = iterator();
		HytteForsikring1 hytte;
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

	/*public static int getGroupInt(String in)//metode gjør om en String til int for medikamentgruppene
	{
		if(in.equals("Bil") || in.equals("BIL") || in.equals("bil") )
			return Forsikring.TYPE_BIL;
		else if(in.equals("Båt") || in.equals("BÅT") || in.equals("båt") )
			return Forsikring.TYPE_BÅT;
		else if(in.equals("Hus") || in.equals("HUS") || in.equals("hus") )
			return Forsikring.TYPE_HUS;
		else if(in.equals("Hytte") || in.equals("HYTTE") || in.equals("hytte") )
			return Forsikring.TYPE_HYTTE;
		JOptionPane.showMessageDialog(null, "Ugyldig forsikringstype");
		return -1;
	}

	public static String getGroupString(int in)//metode gjør om en int til String for medikamentgruppene
	{
		if(in == Forsikring.TYPE_BIL)
			return "BIL";
		else if(in == Forsikring.TYPE_BÅT)
			return "BÅT";
		else if(in == Forsikring.TYPE_HUS)
			return "HUS";
		else if(in == Forsikring.TYPE_HYTTE)
			return "HYTTE";
		return "Error";
	}*/

	public void lagreNrNå()//nødvendig for skriving/lagring til fil
	{
		nrNå = HytteForsikring1.getNrNå();
	}

	public void setNåNr()//nødvendig for skriving/lagring til fil
	{
		HytteForsikring1.setNrNå(nrNå);
	}

	public String toString()
	{
		StringBuilder res = new StringBuilder();
		Iterator<HytteForsikring1> ite = list.iterator();
		while(ite.hasNext() )
			res.append(ite.next().toString() );
			res.append("\n");
		return res.toString();
	}
}
