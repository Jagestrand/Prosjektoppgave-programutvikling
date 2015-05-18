import java.util.*;
import java.io.Serializable;
import javax.swing.*;

public class SkademeldingReg implements Serializable
{
	static final long serialVersionUID = 42L;
	private TreeSet<Skademelding> skadeliste;
	private int nrNå;

	public SkademeldingReg()
	{
		skadeliste = new TreeSet<>(new SkadeCollator() );
	}

	public void add(Skademelding ska)
	{
		skadeliste.add(ska);
	}

	public boolean isEmpty()
	{
		return skadeliste.isEmpty();
	}

	public Iterator<Skademelding> iterator()
	{
		return skadeliste.iterator();
	}

	public boolean contains(Skademelding sk)
	{
		return skadeliste.contains(sk);
	}

	public int size()
	{
		return skadeliste.size();
	}

	public SkademeldingReg finnSkader(String kriterie)
	{
		SkademeldingReg søktSkadeListe = new SkademeldingReg();
		søktSkadeListe = finnSkadeViaKundeNr(kriterie);
		if(søktSkadeListe == null)
		{
			søktSkadeListe = finnSkadeViaAdresse(kriterie);
			if(søktSkadeListe == null)
			{
				søktSkadeListe = finnSkadeViaType(kriterie);
				if(søktSkadeListe == null)
				{
					søktSkadeListe = finnSkadeViaStatus(kriterie);
					if(søktSkadeListe == null)
					{
						søktSkadeListe = finnSkadeViaVitne(kriterie);
						if(søktSkadeListe == null)
						{
							return null;
						}
					}
				}
			}
		}
		return søktSkadeListe;
	}

	public SkademeldingReg finnSkader(int kriterie)
	{
		SkademeldingReg søktSkadeListe = new SkademeldingReg();
		søktSkadeListe = finnSkadeViaNr(kriterie);
		if(søktSkadeListe == null)
		{
			søktSkadeListe = finnSkadeViaTakstBeløp(kriterie);
			if(søktSkadeListe == null)
			{
				søktSkadeListe = finnSkadeViaUtbetaltBeløp(kriterie);
				if(søktSkadeListe == null)
				{
					return null;
				}
			}
		}
		return søktSkadeListe;
	}

	public SkademeldingReg finnSkadeViaKundeNr(String knr)
	{
		Iterator<Skademelding> theIterator = iterator();
		Skademelding skade;
		SkademeldingReg søktSkadeReg = new SkademeldingReg();
		try{
			while(theIterator.hasNext())
			{
				skade = theIterator.next();
				if(skade.getKundeNr().matches(knr))
				{
					søktSkadeReg.add(skade);
					return søktSkadeReg;
				}
			}
			return søktSkadeReg;
		}
		catch(NoSuchElementException nsee)
		{
			JOptionPane.showMessageDialog(null, "Feil i SkademeldingReg (finnSkadeViaKundeNr): No Such Element Exception.",
											"FEIL", JOptionPane.ERROR_MESSAGE);
		}
		catch(NullPointerException npe)
		{
			JOptionPane.showMessageDialog(null, "Det skjedde en NullPointerException i Feilmelding finnSkadeViaKundeNr.", "FEIL", JOptionPane.ERROR_MESSAGE);
		}
		return null;
	}

	public SkademeldingReg finnSkadeViaAdresse(String adr)
	{
		Iterator<Skademelding> theIterator = iterator();
		Skademelding skade;
		SkademeldingReg søktSkadeReg = new SkademeldingReg();
		try{
			while(theIterator.hasNext())
			{
				skade = theIterator.next();
				if(skade.getSkadeAdresse().matches(adr))
				{
					søktSkadeReg.add(skade);
					return søktSkadeReg;
				}
			}
			return søktSkadeReg;
		}
		catch(NoSuchElementException nsee)
		{
			JOptionPane.showMessageDialog(null, "Feil i SkademeldingReg (finnSkadeViaAdresse): No Such Element Exception.",
											"FEIL", JOptionPane.ERROR_MESSAGE);
		}
		catch(NullPointerException npe)
		{
			JOptionPane.showMessageDialog(null, "Det skjedde en NullPointerException i Feilmelding finnSkadeViaAdresse.", "FEIL", JOptionPane.ERROR_MESSAGE);
		}
		return null;
	}

	public SkademeldingReg finnSkadeViaType(String typ)
	{
		Iterator<Skademelding> theIterator = iterator();
		Skademelding skade;
		SkademeldingReg søktSkadeReg = new SkademeldingReg();
		try{
			while(theIterator.hasNext())
			{
				skade = theIterator.next();
				if(skade.getSkadeType().matches(typ))
				{
					søktSkadeReg.add(skade);
					return søktSkadeReg;
				}
			}
			return søktSkadeReg;
		}
		catch(NoSuchElementException nsee)
		{
			JOptionPane.showMessageDialog(null, "Feil i SkademeldingReg (finnSkadeViaType): No Such Element Exception.",
											"FEIL", JOptionPane.ERROR_MESSAGE);
		}
		catch(NullPointerException npe)
		{
			JOptionPane.showMessageDialog(null, "Det skjedde en NullPointerException i Feilmelding finnSkadeViaType.", "FEIL", JOptionPane.ERROR_MESSAGE);
		}
		return null;
	}

	public SkademeldingReg finnSkadeViaStatus(String st)
	{
		Iterator<Skademelding> theIterator = iterator();
		Skademelding skade;
		SkademeldingReg søktSkadeReg = new SkademeldingReg();
		try{
			while(theIterator.hasNext())
			{
				skade = theIterator.next();
				if(skade.getStatus().matches(st))
				{
					søktSkadeReg.add(skade);
					return søktSkadeReg;
				}
			}
			return søktSkadeReg;
		}
		catch(NoSuchElementException nsee)
		{
			JOptionPane.showMessageDialog(null, "Feil i SkademeldingReg (finnSkadeViaStatus): No Such Element Exception.",
											"FEIL", JOptionPane.ERROR_MESSAGE);
		}
		catch(NullPointerException npe)
		{
			JOptionPane.showMessageDialog(null, "Det skjedde en NullPointerException i Feilmelding finnSkadeViaStatus.", "FEIL", JOptionPane.ERROR_MESSAGE);
		}
		return null;
	}

	public SkademeldingReg finnSkadeViaVitne(String vit)
	{
		Iterator<Skademelding> theIterator = iterator();
		Skademelding skade;
		SkademeldingReg søktSkadeReg = new SkademeldingReg();
		try{
			while(theIterator.hasNext())
			{
				skade = theIterator.next();
				if(skade.getVitneNavn().toLowerCase().matches(vit.toLowerCase()))
				{
					søktSkadeReg.add(skade);
				}
				else if(skade.getVitneTlfNr().equals(vit))
				{
					søktSkadeReg.add(skade);
				}

			}
			return søktSkadeReg;
		}
		catch(NoSuchElementException nsee)
		{
			JOptionPane.showMessageDialog(null, "Feil i SkademeldingReg (finnSkadeViaVitne): No Such Element Exception.",
											"FEIL", JOptionPane.ERROR_MESSAGE);
		}
		catch(NullPointerException npe)
		{
			JOptionPane.showMessageDialog(null, "Det skjedde en NullPointerException i Feilmelding finnSkadeViaVitne.", "FEIL", JOptionPane.ERROR_MESSAGE);
		}
		return null;
	}

	public SkademeldingReg finnSkadeViaNr(int nr)
	{
		Iterator<Skademelding> theIterator = iterator();
		Skademelding skade;
		SkademeldingReg søktSkadeReg = new SkademeldingReg();
		try{
			while(theIterator.hasNext())
			{
				skade = theIterator.next();
				if(skade.getSkadeNr() == nr)
				{
					søktSkadeReg.add(skade);
					return søktSkadeReg;
				}
			}
		}
		catch(NoSuchElementException nsee)
		{
			JOptionPane.showMessageDialog(null, "Feil i SkademeldingReg (finnSkadeViaNr): No Such Element Exception.",
											"FEIL", JOptionPane.ERROR_MESSAGE);
		}
		catch(NullPointerException npe)
		{
			JOptionPane.showMessageDialog(null, "Det skjedde en NullPointerException i SkademeldingReg finnSkadeViaNr.", "FEIL", JOptionPane.ERROR_MESSAGE);
		}
		return null;
	}

	public SkademeldingReg finnSkadeViaTakstBeløp(int kr)
	{
		Iterator<Skademelding> theIterator = iterator();
		Skademelding skade;
		SkademeldingReg søktSkadeReg = new SkademeldingReg();
		try{
			while(theIterator.hasNext())
			{
				skade = theIterator.next();
				if(skade.getTakst() == kr)
				{
					søktSkadeReg.add(skade);
					return søktSkadeReg;
				}
			}
			return søktSkadeReg;
		}
		catch(NoSuchElementException nsee)
		{
			JOptionPane.showMessageDialog(null, "Feil i SkademeldingReg (finnSkadeViaTakstBeløp): No Such Element Exception.",
											"FEIL", JOptionPane.ERROR_MESSAGE);
		}
		catch(NullPointerException npe)
		{
			JOptionPane.showMessageDialog(null, "Det skjedde en NullPointerException i SkademeldingReg finnSkadeViaTakstBeløp.", "FEIL", JOptionPane.ERROR_MESSAGE);
		}
		return null;
	}

	public SkademeldingReg finnSkadeViaUtbetaltBeløp(int kr)
	{
		Iterator<Skademelding> theIterator = iterator();
		Skademelding skade;
		SkademeldingReg søktSkadeReg = new SkademeldingReg();
		try{
			while(theIterator.hasNext())
			{
				skade = theIterator.next();
				if(skade.getUtbetalt() == kr)
				{
					søktSkadeReg.add(skade);
					return søktSkadeReg;
				}
			}
			return søktSkadeReg;
		}
		catch(NoSuchElementException nsee)
		{
			JOptionPane.showMessageDialog(null, "Feil i SkademeldingReg (finnSkadeViaTakstBeløp): No Such Element Exception.",
											"FEIL", JOptionPane.ERROR_MESSAGE);
		}
		catch(NullPointerException npe)
		{
			//JOptionPane.showMessageDialog(null, "Det skjedde en NullPointerException i SkademeldingReg finnSkadeViaTakstBeløp.", "FEIL", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		return null;
	}

	public Comparator<Skademelding> InitCollator()
	{
		Comparator<Skademelding> collator;
		return collator = new SkadeCollator();
	}

	public void lagreNrNå()
	{
		nrNå = Skademelding.getNrNå();
	}

	public void setNrNå()
	{
		Skademelding.setNrNå(nrNå);
	}
	public String toString()
	{
		StringBuilder res = new StringBuilder();
		Iterator<Skademelding> ite = skadeliste.iterator();
		while(ite.hasNext() )
			res.append(ite.next().toString() );
			res.append("\n");
		return res.toString();
	}
}
