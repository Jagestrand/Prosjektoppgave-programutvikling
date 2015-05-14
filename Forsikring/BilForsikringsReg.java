import java.io.Serializable;
import java.util.*;
import javax.swing.*;

public class BilForsikringsReg implements Serializable
{
	private static final long serialVersionUID = 42L;
	private TreeSet<Forsikring1> list;//listen
	private int nrNå;

	public BilForsikringsReg()
	{
		list = new TreeSet<>(new ForsikringCollator());//Oppretter forsikringslisten
	}

	public boolean add(Forsikring1 pre)
	{
		return list.add(pre);		//HER
	}

	public boolean isEmpty()
	{
		return list.isEmpty();
	}

	public Iterator<Forsikring1> iterator()
	{
		return list.iterator();
	}

	public boolean contains(Forsikring1 in)
	{
		return list.contains(in);
	}

	public int size()
	{
		return list.size();
	}

	public BilForsikringsReg finnBilViaRegNr(String regNr)
	{
		if(regNr.isEmpty())
			return null;
		Iterator<Forsikring1> theIterator = iterator();
		Forsikring1 bil;
		BilForsikringsReg søktBilReg = new BilForsikringsReg();
		try{
			while(theIterator.hasNext())
			{
				bil = theIterator.next();
				if(bil.getRegistreringsnr().matches(regNr))
				{
					søktBilReg.add(bil);
					return søktBilReg;
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

	public Comparator<Forsikring1> InitCollator()
	{
		Comparator<Forsikring1> collator;
		return collator = new ForsikringCollator();
	}

	public void lagreNrNå()//nødvendig for skriving/lagring til fil
	{
		nrNå = Forsikring1.getNrNå();
	}

	public void setNåNr()//nødvendig for skriving/lagring til fil
	{
		Forsikring1.setNrNå(nrNå);
	}

	public String toString()
	{
		StringBuilder res = new StringBuilder();
		Iterator<Forsikring1> ite = list.iterator();
		while(ite.hasNext() )
			res.append(ite.next().toString() );
			res.append("\n");
		return res.toString();
	}
}
