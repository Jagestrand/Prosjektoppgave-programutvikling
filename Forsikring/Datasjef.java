import java.io.Serializable;
import java.util.*;
import javax.swing.*;

public class BåtForsikringsReg implements Serializable
{
	private static final long serialVersionUID = 42L;

	private TreeSet<BåtForsikring> list;//listen
	private int nrNå;

	public BåtForsikringsReg()
	{
		list = new TreeSet<>(new ForsikringCollator() );//Oppretter forsikringslisten
	}

	public void add(BåtForsikring båt)
	{
		list.add(båt);
	}

	public boolean isEmpty()
	{
		return list.isEmpty();
	}

	public Iterator<BåtForsikring> iterator()
	{
		return list.iterator();
	}

	public boolean contains(BåtForsikring in)
	{
		return list.contains(in);
	}

	public int size()
	{
		return list.size();
	}

	public BåtForsikringsReg finnBåter(String kriterie)
	{
		BåtForsikringsReg søktBåtListe = new BåtForsikringsReg();
		søktBåtListe = finnBåtViaKundeNr(kriterie);
		if(søktBåtListe == null)
		{
			søktBåtListe = finnBåtViaEier(kriterie);
			if(søktBåtListe == null)
			{
				søktBåtListe = finnBåtViaRegNr(kriterie);
				if(søktBåtListe == null)
				{
					søktBåtListe = finnBåtViaType(kriterie);
					if(søktBåtListe == null)
					{
						søktBåtListe = finnBåtViaModell(kriterie);
						if(søktBåtListe == null)
						{
							søktBåtListe = finnBåtViaMotorType(kriterie);
							if(søktBåtListe == null)
							{
								return null;
							}
						}
					}
				}
			}
		}
		return søktBåtListe;
	}

	public BåtForsikringsReg finnBåter(int kriterie)
	{
		BåtForsikringsReg søktBåtListe = new BåtForsikringsReg();
		søktBåtListe = finnBåtViaNr(kriterie);
		if(søktBåtListe == null)
		{
			søktBåtListe = finnBåtViaÅr(kriterie);
			if(søktBåtListe == null)
			{
				søktBåtListe = finnBåtViaLengde(kriterie);
				if(søktBåtListe == null)
				{
					søktBåtListe = finnBåtViaBeløp(kriterie);
					if(søktBåtListe == null)
					{
						søktBåtListe = finnBåtViaStyrke(kriterie);
						if(søktBåtListe == null)
						{
							return null;
						}
					}
				}
			}
		}
		return søktBåtListe;
	}

	public BåtForsikringsReg finnBåtViaKundeNr(String nr)
	{
		Iterator<BåtForsikring> theIterator = iterator();
		BåtForsikring båt;
		BåtForsikringsReg søktBåtReg = new BåtForsikringsReg();
		try{
			while(theIterator.hasNext())
			{
				båt = theIterator.next();
				if(båt.getKundeNr().matches(nr))
				{
					søktBåtReg.add(båt);
					return søktBåtReg;
				}
			}
			return søktBåtReg;
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

	public BåtForsikringsReg finnBåtViaEier(String eier)
	{
		Iterator<BåtForsikring> theIterator = iterator();
		BåtForsikring båt;
		BåtForsikringsReg søktBåtReg = new BåtForsikringsReg();
		try{
			while(theIterator.hasNext())
			{
				båt = theIterator.next();
				if(båt.getEiernavn().matches(eier))
				{
					søktBåtReg.add(båt);
					return søktBåtReg;
				}
			}
			return søktBåtReg;
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

	public BåtForsikringsReg finnBåtViaType(String type)
	{
		Iterator<BåtForsikring> theIterator = iterator();
		BåtForsikring båt;
		BåtForsikringsReg søktBåtReg = new BåtForsikringsReg();
		try{
			while(theIterator.hasNext())
			{
				båt = theIterator.next();
				if(båt.getType().matches(type))
				{
					søktBåtReg.add(båt);
					return søktBåtReg;
				}
			}
			return søktBåtReg;
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

	public BåtForsikringsReg finnBåtViaModell(String m)
	{
		Iterator<BåtForsikring> theIterator = iterator();
		BåtForsikring båt;
		BåtForsikringsReg søktBåtReg = new BåtForsikringsReg();
		try{
			while(theIterator.hasNext())
			{
				båt = theIterator.next();
				if(båt.getModell().matches(m))
				{
					søktBåtReg.add(båt);
					return søktBåtReg;
				}
			}
			return søktBåtReg;
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

	public BåtForsikringsReg finnBåtViaRegNr(String nr)
	{
		Iterator<BåtForsikring> theIterator = iterator();
		BåtForsikring båt;
		BåtForsikringsReg søktBåtReg = new BåtForsikringsReg();
		try{
			while(theIterator.hasNext())
			{
				båt = theIterator.next();
				if(båt.getRegistreringsnr().matches(nr))
				{
					søktBåtReg.add(båt);
					return søktBåtReg;
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

	public BåtForsikringsReg finnBåtViaMotorType(String nr)
	{
		Iterator<BåtForsikring> theIterator = iterator();
		BåtForsikring båt;
		BåtForsikringsReg søktBåtReg = new BåtForsikringsReg();
		try{
			while(theIterator.hasNext())
			{
				båt = theIterator.next();
				if(båt.getMotortype().matches(nr))
				{
					søktBåtReg.add(båt);
					return søktBåtReg;
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

	public BåtForsikringsReg finnBåtViaNr(int nr)
	{
		Iterator<BåtForsikring> theIterator = iterator();
		BåtForsikring båt;
		BåtForsikringsReg søktBåtReg = new BåtForsikringsReg();
		try{
			while(theIterator.hasNext())
			{
				båt = theIterator.next();
				if(båt.getForsikringsNr() == nr)
				{
					søktBåtReg.add(båt);
					return søktBåtReg;
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

	public BåtForsikringsReg finnBåtViaÅr(int år)
	{
		Iterator<BåtForsikring> theIterator = iterator();
		BåtForsikring båt;
		BåtForsikringsReg søktBåtReg = new BåtForsikringsReg();
		try{
			while(theIterator.hasNext())
			{
				båt = theIterator.next();
				if(båt.getÅrsmodell() == år)
				{
					søktBåtReg.add(båt);
					return søktBåtReg;
				}
			}
			return søktBåtReg;
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

	public BåtForsikringsReg finnBåtViaLengde(int km)
	{
		Iterator<BåtForsikring> theIterator = iterator();
		BåtForsikring båt;
		BåtForsikringsReg søktBåtReg = new BåtForsikringsReg();
		try{
			while(theIterator.hasNext())
			{
				båt = theIterator.next();
				if(båt.getBåtlengde() == km)
				{
					søktBåtReg.add(båt);
					return søktBåtReg;
				}
			}
			return søktBåtReg;
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

	public BåtForsikringsReg finnBåtViaBeløp(int b)
	{
		Iterator<BåtForsikring> theIterator = iterator();
		BåtForsikring båt;
		BåtForsikringsReg søktBåtReg = new BåtForsikringsReg();
		try{
			while(theIterator.hasNext())
			{
				båt = theIterator.next();
				if(båt.getForsikringsbeløp() == b)
				{
					søktBåtReg.add(båt);
					return søktBåtReg;
				}
			}
			return søktBåtReg;
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

	public BåtForsikringsReg finnBåtViaStyrke(int s)
	{
		Iterator<BåtForsikring> theIterator = iterator();
		BåtForsikring båt;
		BåtForsikringsReg søktBåtReg = new BåtForsikringsReg();
		try{
			while(theIterator.hasNext())
			{
				båt = theIterator.next();
				if(båt.getMotorstyrke() == s)
				{
					søktBåtReg.add(båt);
					return søktBåtReg;
				}
			}
			return søktBåtReg;
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

	public Comparator<Forsikring> InitCollator()
	{
		Comparator<Forsikring> collator;
		return collator = new ForsikringCollator();
	}

	public void lagreNåNr()//nødvendig for skriving/lagring til fil
	{
		nrNå = BåtForsikring.getNrNå();
	}

	public void setNåNr()//nødvendig for skriving/lagring til fil
	{
		BåtForsikring.setNrNå(nrNå);
	}

	public String toString()
	{
		StringBuilder res = new StringBuilder();
		Iterator<BåtForsikring> ite = list.iterator();
		while(ite.hasNext() )
			res.append(ite.next().toString() );
			res.append("\n");
		return res.toString();
	}
}
