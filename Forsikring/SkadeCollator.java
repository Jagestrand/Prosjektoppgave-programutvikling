import java.io.Serializable;
import java.util.Comparator;


public class SkadeCollator implements Comparator<Skademelding>, Serializable
{
	private static final long serialVersionUID = 42L;

	public int compare(Skademelding a, Skademelding b)
	{
		return a.getSkadeNr() - b.getSkadeNr();
	}

}
