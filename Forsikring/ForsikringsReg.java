/*Skrevet av Even Nerheim, s199184, sist redigert 16.05.2015
Denne klassen er bare til for å skille mellom forsikringene
*/

import java.io.Serializable;
import java.util.*;
import javax.swing.*;

public class ForsikringsReg implements Serializable
{
	private static final long serialVersionUID = 42L;
	private Forsikring1 f;
	private BilForsikringsReg bil;
	private BåtForsikringsReg båt;
	private HusForsikringsReg hus;
	private HytteForsikringsReg hyt;

	public ForsikringsReg()
	{
		bil = new BilForsikringsReg();
		båt = new BåtForsikringsReg();
		hus = new HusForsikringsReg();
		hyt = new HytteForsikringsReg();
	}

	public int size()
	{
		return bil.size() + båt.size() + hus.size() + hyt.size();
	}

	public int sjekkForsikringsNr(int nr)
	{
		if(nr >= f.bilKat)
		{
			if(nr >= f.båtKat)
			{
				if(nr >= f.husKat)
				{
					if(nr >= f.hytKat)
					{
						if(nr > f.hytKat)
						{
							return 0;
						}
					}
					else
						return f.TYPE_HYTTE;
				}
				else
					return f.TYPE_HUS;
			}
			else
				return f.TYPE_BÅT;
		}
		else
			return f.TYPE_BIL;

		return 0;
	}


}
