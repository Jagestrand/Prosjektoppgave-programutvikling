/*Skrevet av Magnus Tønsager, s198761. Sist endret 11.05.14
Klassen ineholder en liste over alle reseptene, har metoder for behandling av listen
*/

import java.io.Serializable;
import java.util.*;
import javax.swing.*;

public class ForsikringsReg implements Serializable
{
	private static final long serialVersionUID = 42L;
	private TreeSet<Forsikring> list;//listen
	private int currentNumber;

	public ForsikringsReg()
	{
		list = new TreeSet<>(new ForsikringCollator() );//Oppretter reseptlisten
	}

	public boolean add(Forsikring pre)
	{
		return list.add(pre);
	}

	public boolean isEmpty()
	{
		return list.isEmpty();
	}

	public Iterator<Forsikring> iterator()
	{
		return list.iterator();
	}

	public boolean contains(Forsikring in)
	{
		return list.contains(in);
	}

	public int size()
	{
		return list.size();
	}
	/*
	public static int getGroupInt(String in)//metode gjør om en String til int for medikamentgruppene
	{
		if(in.equals("a") || in.equals("A") )
			return Prescription.GROUP_A;
		else if(in.equals("b") || in.equals("B") )
			return Prescription.GROUP_B;
		else if(in.equals("c") || in.equals("C") )
			return Prescription.GROUP_C;
		JOptionPane.showMessageDialog(null, "Ugyldig gruppe for medikament");
		return -1;
	}*/
	/*
	public static String getGroupString(int in)//metode gjør om en int til String for medikamentgruppene
	{
		if(in == Prescription.GROUP_A)
			return "A";
		else if(in == Prescription.GROUP_B)
			return "B";
		else if(in == Prescription.GROUP_C)
			return "C";
		return "Error";
	}*/

	public void saveCurrentNumber()//nødvendig for skriving/lagring til fil
	{
		currentNumber = Forsikring.getCurrentNumber();
	}

	public void setCurrentNumber()//nødvendig for skriving/lagring til fil
	{
		Forsikring.setCurrentNumber(currentNumber);
	}

	public String toString()
	{
		StringBuilder res = new StringBuilder();
		Iterator<Forsikring> ite = list.iterator();
		while(ite.hasNext() )
			res.append(ite.next().toString() );
			res.append("\n");
		return res.toString();
	}
}
