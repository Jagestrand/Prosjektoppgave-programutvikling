//Klassen sorterer forsikringer på forsikringsnummeret

import java.io.Serializable;
import java.util.Comparator;


public abstract class ForsikringCollator implements Comparator<Forsikring>, Serializable
{
	private static final long serialVersionUID = 42L;
}

class BilForsikringCollator implements Comparator<BilForsikring>, Serializable
{
	public int compare(BilForsikring a, BilForsikring b)
	{
		return a.getForsikringsID() - b.getForsikringsID();
	}
}

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
