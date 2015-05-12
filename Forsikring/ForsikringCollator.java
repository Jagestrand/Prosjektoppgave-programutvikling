//Klassen sorterer forsikringer på forsikringsnummeret

import java.io.Serializable;
import java.util.Comparator;
import java.util.*;
import java.text.*;
import javax.swing.*;


class ForsikringCollator implements Comparator<Forsikring1>, Serializable
{
	private static final long serialVersionUID = 42L;

	private String regex = "<\0<0<1<2<3<4<5<6<7<8<9" +
                  "<A,a<B,b<C,c<D,d<E,e<F,f<G,g<H,h<I,i<J,j" +
                 "<K,k<L,l<M,m<N,n<O,o<P,p<Q,q<R,r<S,s<T,t" +
                 "<U,u<V,v<W,w<X,x<Y,y<Z,z<Æ,æ<Ø,ø<Å=AA,å=aa;AA,aa";

	private transient RuleBasedCollator collator;

	public ForsikringCollator()
	{
		try{
			collator = new RuleBasedCollator(regex);
		}
	    catch (ParseException pe)
	    {
	    	JOptionPane.showMessageDialog(null, "En feil ved oppretting av kollator har oppstått.", "FEIL", JOptionPane.ERROR_MESSAGE);
	    }
	  }


	public int compare(Forsikring1 a, Forsikring1 b)
	{
		if(collator == null)
		{
			try
			{
				collator = new RuleBasedCollator(regex);
			}
			catch (ParseException e)
			{
				JOptionPane.showMessageDialog(null, "En feil har oppstått ved oppretting av kollator. Dette er en kritisk feil.\n" +
										"Lukk programmet og slett data mappe for ditt eget beste.", "FEIL", JOptionPane.ERROR_MESSAGE);
			}
		}
		String første = String.valueOf(a.getForsikringsNr());
		String andre = String.valueOf(b.getForsikringsNr());
		int sammenlignNr = collator.compare( første, andre );
		if(sammenlignNr == 0)
			sammenlignNr++;
		return sammenlignNr;
	}
}
/*
class BilForsikringCollator implements Comparator<BilForsikring>, Serializable
{
	public int compare(BilForsikring a, BilForsikring b)
	{
		return a.getForsikringsID() - b.getForsikringsID();
	}
}*/



class BåtForsikringCollator implements Comparator<BåtForsikring>, Serializable
{
	public int compare(BåtForsikring a, BåtForsikring b)
	{
		return a.getForsikringsID() - b.getForsikringsID();
	}
}

class HusForsikringCollator implements Comparator<HusForsikring>, Serializable
{
	public int compare(HusForsikring a, HusForsikring b)
	{
		return a.getForsikringsID() - b.getForsikringsID();
	}
}

class HytteForsikringCollator implements Comparator<HytteForsikring>, Serializable
{
	public int compare(HytteForsikring a, HytteForsikring b)
	{
		return a.getForsikringsID() - b.getForsikringsID();
	}
}
