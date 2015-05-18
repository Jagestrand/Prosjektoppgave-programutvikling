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
			JOptionPane.showMessageDialog(null, "Feil i AnsattReg (findDoctorByPersonNr): No Such Element Exception.",
											"FEIL", JOptionPane.ERROR_MESSAGE);
		}
		catch(NullPointerException npe)
		{
			JOptionPane.showMessageDialog(null, "Det skjedde en NullPointerException i ForsikringsReg1 findDoctorByPersonNr.", "FEIL", JOptionPane.ERROR_MESSAGE);
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
			JOptionPane.showMessageDialog(null, "Feil i AnsattReg (findDoctorByPersonNr): No Such Element Exception.",
											"FEIL", JOptionPane.ERROR_MESSAGE);
		}
		catch(NullPointerException npe)
		{
			JOptionPane.showMessageDialog(null, "Det skjedde en NullPointerException i ForsikringsReg1 findDoctorByPersonNr.", "FEIL", JOptionPane.ERROR_MESSAGE);
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
			JOptionPane.showMessageDialog(null, "Feil i AnsattReg (findDoctorByPersonNr): No Such Element Exception.",
											"FEIL", JOptionPane.ERROR_MESSAGE);
		}
		catch(NullPointerException npe)
		{
			JOptionPane.showMessageDialog(null, "Det skjedde en NullPointerException i ForsikringsReg1 findDoctorByPersonNr.", "FEIL", JOptionPane.ERROR_MESSAGE);
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
			JOptionPane.showMessageDialog(null, "Feil i AnsattReg (findDoctorByPersonNr): No Such Element Exception.",
											"FEIL", JOptionPane.ERROR_MESSAGE);
		}
		catch(NullPointerException npe)
		{
			JOptionPane.showMessageDialog(null, "Det skjedde en NullPointerException i ForsikringsReg1 findDoctorByPersonNr.", "FEIL", JOptionPane.ERROR_MESSAGE);
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
			JOptionPane.showMessageDialog(null, "Feil i AnsattReg (findDoctorByPersonNr): No Such Element Exception.",
											"FEIL", JOptionPane.ERROR_MESSAGE);
		}
		catch(NullPointerException npe)
		{
			JOptionPane.showMessageDialog(null, "Det skjedde en NullPointerException i ForsikringsReg1 findDoctorByPersonNr.", "FEIL", JOptionPane.ERROR_MESSAGE);
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
		nrNå = HusForsikring.getNrNå();
	}

	public void setCurrentNumber()//nødvendig for skriving/lagring til fil
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
