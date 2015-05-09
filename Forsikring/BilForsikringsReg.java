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
		list = new TreeSet<>(new BilForsikringCollator() );//Oppretter forsikringslisten
	}

	public boolean add(BilForsikring pre)
	{
		return list.add(pre);
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
		nrNå = BilForsikring.getNrNå();
	}

	public void setCurrentNumber()//nødvendig for skriving/lagring til fil
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