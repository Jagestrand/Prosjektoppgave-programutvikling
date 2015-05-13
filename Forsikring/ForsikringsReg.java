/*
Denne klassen er bare til for å skille mellom forsikringene
*/

import java.io.Serializable;
import java.util.*;
import javax.swing.*;

public class ForsikringsReg implements Serializable
{
	private static final long serialVersionUID = 42L;
	private Forsikring1 f;
	private ForsikringsReg1 forReg;
	private BilForsikringsReg bilReg;
	private BåtForsikringsReg båtReg;
	private HusForsikringsReg husReg;
	private HytteForsikringsReg hytReg;

	public ForsikringsReg()
	{
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
