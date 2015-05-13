import java.io.Serializable;
import java.util.*;
import javax.swing.*;

public class ForsikringsReg1 implements Serializable
{
	private static final long serialVersionUID = 42L;

	private TreeSet<BilForsikring1> list;//listen
	private int nrNå;

	public ForsikringsReg1()
	{
		list = new TreeSet<>(new ForsikringCollator());//Oppretter forsikringslisten
	}

	public void add(BilForsikring1 pre)	//public boolean add(Forsikring1 pre)
	{
		list.add(pre);		//return list.add(pre);
	}

	public boolean isEmpty()
	{
		return list.isEmpty();
	}

	public Iterator<BilForsikring1> iterator()
	{
		return list.iterator();
	}

	public boolean contains(BilForsikring1 in)
	{
		return list.contains(in);
	}

	public int size()
	{
		return list.size();
	}

	public ForsikringsReg1 finnBiler(String kriterie)
	{
		ForsikringsReg1 søktBilListe = new ForsikringsReg1();
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

	public ForsikringsReg1 finnBiler(int kriterie)
	{
		ForsikringsReg1 søktBilListe = new ForsikringsReg1();
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

	public ForsikringsReg1 finnBilViaKundeNr(String nr)
	{
		Iterator<BilForsikring1> theIterator = iterator();
		BilForsikring1 bil;
		ForsikringsReg1 søktBilReg = new ForsikringsReg1();
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
			JOptionPane.showMessageDialog(null, "Det skjedde en NullPointerException i ForsikringsReg1 findDoctorByPersonNr.", "FEIL", JOptionPane.ERROR_MESSAGE);
		}
		return null;
	}

	public ForsikringsReg1 finnBilViaEier(String eier)
	{
		Iterator<BilForsikring1> theIterator = iterator();
		BilForsikring1 bil;
		ForsikringsReg1 søktBilReg = new ForsikringsReg1();
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
			JOptionPane.showMessageDialog(null, "Det skjedde en NullPointerException i ForsikringsReg1 findDoctorByPersonNr.", "FEIL", JOptionPane.ERROR_MESSAGE);
		}
		return null;
	}

	public ForsikringsReg1 finnBilViaType(String type)
	{
		Iterator<BilForsikring1> theIterator = iterator();
		BilForsikring1 bil;
		ForsikringsReg1 søktBilReg = new ForsikringsReg1();
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
			JOptionPane.showMessageDialog(null, "Det skjedde en NullPointerException i ForsikringsReg1 findDoctorByPersonNr.", "FEIL", JOptionPane.ERROR_MESSAGE);
		}
		return null;
	}

	public ForsikringsReg1 finnBilViaModell(String m)
	{
		Iterator<BilForsikring1> theIterator = iterator();
		BilForsikring1 bil;
		ForsikringsReg1 søktBilReg = new ForsikringsReg1();
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
			JOptionPane.showMessageDialog(null, "Det skjedde en NullPointerException i ForsikringsReg1 findDoctorByPersonNr.", "FEIL", JOptionPane.ERROR_MESSAGE);
		}
		return null;
	}

	public ForsikringsReg1 finnBilViaRegNr(String nr)
	{
		Iterator<BilForsikring1> theIterator = iterator();
		BilForsikring1 bil;
		ForsikringsReg1 søktBilReg = new ForsikringsReg1();
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
			JOptionPane.showMessageDialog(null, "Det skjedde en NullPointerException i ForsikringsReg1 findDoctorByPersonNr.", "FEIL", JOptionPane.ERROR_MESSAGE);
		}
		return null;
	}

	public ForsikringsReg1 finnBilViaNr(int nr)
	{
		Iterator<BilForsikring1> theIterator = iterator();
		BilForsikring1 bil;
		ForsikringsReg1 søktBilReg = new ForsikringsReg1();
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
			JOptionPane.showMessageDialog(null, "Det skjedde en NullPointerException i ForsikringsReg1 findDoctorByPersonNr.", "FEIL", JOptionPane.ERROR_MESSAGE);
		}
		return null;
	}

	public ForsikringsReg1 finnBilViaÅr(int år)
	{
		Iterator<BilForsikring1> theIterator = iterator();
		BilForsikring1 bil;
		ForsikringsReg1 søktBilReg = new ForsikringsReg1();
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
			JOptionPane.showMessageDialog(null, "Det skjedde en NullPointerException i ForsikringsReg1 findDoctorByPersonNr.", "FEIL", JOptionPane.ERROR_MESSAGE);
		}
		return null;
	}

	public ForsikringsReg1 finnBilViaKjøring(int km)
	{
		Iterator<BilForsikring1> theIterator = iterator();
		BilForsikring1 bil;
		ForsikringsReg1 søktBilReg = new ForsikringsReg1();
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
			JOptionPane.showMessageDialog(null, "Det skjedde en NullPointerException i ForsikringsReg1 findDoctorByPersonNr.", "FEIL", JOptionPane.ERROR_MESSAGE);
		}
		return null;
	}


	public static int getGroupInt(String in)//metode gjør om en String til int for medikamentgruppene
	{
		if(in.equals("Bil") || in.equals("BIL") || in.equals("bil") )
			return BilForsikring1.TYPE_BIL;
		else if(in.equals("Båt") || in.equals("BÅT") || in.equals("båt") )
			return BilForsikring1.TYPE_BÅT;
		else if(in.equals("Hus") || in.equals("HUS") || in.equals("hus") )
			return BilForsikring1.TYPE_HUS;
		else if(in.equals("Hytte") || in.equals("HYTTE") || in.equals("hytte") )
			return BilForsikring1.TYPE_HYTTE;
		JOptionPane.showMessageDialog(null, "Ugyldig forsikringstype");
		return -1;
	}

	public static String getGroupString(int in)//metode gjør om en int til String for medikamentgruppene
	{
		if(in == BilForsikring1.TYPE_BIL)
			return "BIL";
		else if(in == BilForsikring1.TYPE_BÅT)
			return "BÅT";
		else if(in == BilForsikring1.TYPE_HUS)
			return "HUS";
		else if(in == BilForsikring1.TYPE_HYTTE)
			return "HYTTE";
		return "Error";
	}

	public Comparator<Forsikring1> InitCollator()
	{
		Comparator<Forsikring1> collator;
		return collator = new ForsikringCollator();
	}

	public void lagreNåNr()//nødvendig for skriving/lagring til fil
	{
		nrNå = BilForsikring1.getNrNå();
	}

	public void setNåNr()//nødvendig for skriving/lagring til fil
	{
		BilForsikring1.setNrNå(nrNå);
	}

	public String toString()
	{
		StringBuilder res = new StringBuilder();
		Iterator<BilForsikring1> ite = list.iterator();
		while(ite.hasNext() )
			res.append(ite.next().toString() );
			res.append("\n");
		return res.toString();
	}
}
