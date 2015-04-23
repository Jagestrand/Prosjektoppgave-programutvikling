import java.util.*;
import java.io.*;
import javax.swing.*;

public class Register implements Serializable
{
	private static final long serialVersionUID = 42L;
	//private ForsikringsReg forReg;
	private AnsattReg ansReg;
	//private KundeReg kunReg;
	//private SkadeReg skaReg;
	private Datasjef data;

	public Register( Datasjef dataer )
	{
		data = dataer;/*
		forReg = new ForsikringsReg();
		ansReg = new AnsattReg();
		kunReg = new KundeReg();
		skaReg = new SkadeReg();*/
	}/*

	public ForsikringsReg getForsikringer()
	{
		return forReg;
	}*/

	public AnsattReg getAnsatte()
	{
		return ansReg;
	}
	/*
	public KundeReg getKunder()
	{
		return kunReg;
	}

	public SkadeReg getSkader()
	{
		return skaReg;
	}*/

	public Ansatt getAnsattViaNr(String nr)	//finner ansatt via ansattnr
	{
		AnsattReg aReg = ansReg.finnAnsattViaPersNr(nr);
		if(aReg == null)
			return null;
		Iterator<Ansatt> iter = aReg.iterator();
		return iter.next();
	}/*

	public Forsikring getForsikringViaNr(int nr)	//finner forsikring via forsikringsnr
	{
		Forsikring fors;
		Iterator<Forsikring> iter = fors.iterator();
		while(iter.hasNext() )
		{
			fors = iter.next();
			if(fors.getForsikringsNr() == nr)
				return fors;
		}
		return null;
	}

	public ForsikringsReg getForsikringViaNr( ForsikringsReg reg, int nr)	//finner forsikring via forsikringsnr, men ikke samme som over
	{
		if( reg == null )
			reg = forReg;
		Forsikring prøv;
		ForsikringsReg ris = new ForsikringsReg();
		Iterator<Forsikring> iter = reg.iterator();
		while(iter.hasNext() )
		{
			prøv = iter.next();
			if(prøv.getForsikringsNr() == nr)
				ris.add(prøv);
		}
		return ris;
	}

	public SkadeReg getSkadeViaNr( int nr )		//finner skade via skadenr
	{
		Skademelding ska;
		Iterator<Skademelding> iter = ska.iterator();
		while(iter.hasNext() )
		{
			ska = iter.next();
			if(ska.getSkadeNr() == nr)
				return ska;
		}
		return null;
	}

	public SkadeReg getSkadeViaNr( SkadeReg reg, int nr )	//finner skade via skadenr
	{
		if( reg == null )
			reg = skaReg;
		Skademelding prøv;
		SkadeReg ade = new SkadeReg();
		Iterator<Skademelding> iter = reg.iterator();
		while(iter.hasNext() )
		{
			prøv = iter.next();
			if(prøv.getSkadeNr() == nr)
				ade.add(prøv);
		}
		return ade;
	}

	public void nyForsikring(Forsikring fors)	//legger inn ny forsikring
	{
		fors.getKunde().nyForsikring(fors);
		//fors.getAnsatt().nyForsikring(fors);
		forReg.add(fors);
	}*/

	public void nyAnsatt( Ansatt ans)		//legger inn ny ansatt
	{
		ansReg.add(ans);
	}/*

	public void nyKunde(Kunde kun)		//legger inn ny kunde
	{
		kunReg.add(kun);
	}

	public void nySkade(Skademelding skad)		//legger inn ny skademelding
	{
		skad.getKunde().nySkade(skad);
		//skad.getAnsatt().nySkade(skad);
		skaReg.add(skad);
	}

	public ForsikringsReg getForsikringerFørDato(ForsikringsReg reg, Calender dato)	//finner forsikringer kjøpt før dato
	{
		if( reg == null )
			reg = forReg;
		Iterator<Forsikring> iter = reg.iterator();
		ForsikringsReg fors = new ForsikringsReg();
		Forsikring prøv;
		while(iter.hasNext() )
		{
			prøv = iter.next();
			if(prøv.getKjøpt().before(date) )
				fors.add(prøv);
		}
		return fors;
	}

	public SkadeReg getSkademeldingFørDato(SkadeReg reg, Calender dato)	//finner skademeldinger registrert før dato
	{
		if( reg == null )
			reg = skaReg;
		Iterator<Skademelding> iter = reg.iterator();
		SkadeReg skads = new SkadeReg();
		Skademelding prøv;
		while(iter.hasNext() )
		{
			prøv = iter.next();
			if(prøv.getKjøpt().before(date) )
				skads.add(prøv);
		}
		return skads;
	}

	public SkadeReg getSkademeldingEtterDato(ForsikringsReg reg, Calender dato)	//finner skademeldinger registrert etter dato
	{
		if( reg == null )
			reg = skaReg;
		Iterator<Skademelding> iter = reg.iterator();
		SkadeReg skads = new ForsikringsReg();
		Skademelding prøv;
		while(iter.hasNext() )
		{
			prøv = iter.next();
			if(prøv.getKjøpt().after(date) )
				skads.add(prøv);
		}
		return skads;
	}

	public ForsikringsReg getForsikringPerKunde( ForsikringsReg reg, String navn )	//finner forsikringer på kunde
	{
		KundeReg kReg = kunReg.finnKunder(navn);
		Iterator<Kunde> kunIter = kReg.iterator();
		Kunde kun;
		if(kunIter.hasNext())
			kun = kunIter.next();
		else
			return new ForsikringsReg();
		if(kunIter.hasNext())
			JOptionPane.showMessageDialog(null, "Flere personer funnet, vennligst spesifiser søket bedre");

		ForsikringsReg fr = kun.getForsikringer();
		if(reg == null)
			return fr;

		ForsikringsReg fir = new ForsikringsReg();
		Iterator<Forsikring> iter = fr.iterator();
		Forsikring prøv;
		while(iter.hasNext())
		{
			prøv = iter.next();
			if(reg.contains(prøv))
				fir.add(prøv);
		}
		return fir;
	}

	public SkadeReg getSkadePerKunde( SkadeReg reg, String navn )	//finner skader registrert på kunde
	{
		KundeReg kReg = kunReg.finnKunder(navn);
		Iterator<Kunde> kunIter = kReg.iterator();
		Kunde kun;
		if(kunIter.hasNext())
			kun = kunIter.next();
		else
			return new SkadeReg();
		if(kunIter.hasNext())
			JOptionPane.showMessageDialog(null, "Flere personer funnet, vennligst spesifiser søket bedre");

		SkadeReg sr = kun.getSkademeldinger();
		if(reg == null)
			return sr;

		SkadeReg ade = new SkadeReg();
		Iterator<Skademelding> iter = sr.iterator();
		Skademelding prøv;
		while(iter.hasNext())
		{
			prøv = iter.next();
			if(reg.contains(prøv))
				ade.add(prøv);
		}
		return ade;
	}
	//Denne må sees mer på, fungerer mest sannsynlig ikke:
	public KundeReg getKunderPerForsikringKategori(ForsikringsReg reg, boolean bil, boolean båt, boolean hus, boolean innbo, boolean hytte, boolean innbo2)
	{															//finner kunde med den type forsikring
		if(reg == null)
			reg = forReg;
		if(!(bil || båt || hus || innbo || hytte || innbo2) )
			return reg;
		Iterator<Forsikring> iter = reg.iterator();
		ForsikringsReg frs = new ForsikringsReg();
		Forsikring prøv;
		while(iterator.hasNext())
		{
			prøv = iter.next();
			if(bil && prøv.getType() == Forsikring.TYPE_BI)
				frs.add(prøv);
			if(båt && prøv.getType() == Forsikring.TYPE_BA)
				frs.add(prøv);
			if(hus && prøv.getType() == Forsikring.TYPE_HU)
				frs.add(prøv);
			if(innbo && prøv.getType() == Forsikring.TYPE_IN)
				frs.add(prøv);
			if(hytte && prøv.getType() == Forsikring.TYPE_HY)
				frs.add(prøv);
			if(innbo2 && prøv.getType() == Forsikring.TYPE_IN2)
				frs.add(prøv);
		}
		return frs;
	}
	//Se mer på
	public ForsikringsReg getForsikringViaKategori( ForsikringsReg reg, boolean bil, boolean båt, boolean hus, boolean innbo, boolean hytte, boolean innbo2)
	{									//finner forsikringer av den kategori
		if(reg == null)
			reg = forReg;
		if(!(bil || båt || hus || innbo || hytte || innbo2) )
			return reg;
		Iterator<Forsikring> iter = reg.iterator();
		ForsikringsReg frs = new ForsikringsReg();
		Forsikring prøv;
		while(iterator.hasNext())
		{
			prøv = iter.next();
			if(bil && prøv.getType() == Forsikring.TYPE_BI)
				frs.add(prøv);
			if(båt && prøv.getType() == Forsikring.TYPE_BA)
				frs.add(prøv);
			if(hus && prøv.getType() == Forsikring.TYPE_HU)
				frs.add(prøv);
			if(innbo && prøv.getType() == Forsikring.TYPE_IN)
				frs.add(prøv);
			if(hytte && prøv.getType() == Forsikring.TYPE_HY)
				frs.add(prøv);
			if(innbo2 && prøv.getType() == Forsikring.TYPE_IN2)
				frs.add(prøv);
		}
		return frs;
	}

	public KundeReg getKundeViaNavn(KundeReg reg, String navn)	//åpenbar
	{
		if(reg == null)
			return kunReg.finnKunder(navn);
		return reg.finnKunder(navn);
	}

	public KundeReg getKundePerSkadeType(KundeReg reg, String skadet)	//finner kunder med den type skade registrert
	{
		if(reg == null)
			reg = kunReg;
		Iterator<Skademelding> iter = getSkademeldingViaType(null, skadet).iterator();
		KundeReg kun = new KundeReg();
		Skademelding prøv;
		while(iter.hadNext())
		{
			prøv = iter.next();
			if(prøv.getSkadeType().toLowerCase().equals(type.toLowerCase()) && reg.contains(prøv.getKunde()))
				kun.add(prøv.getKunde());
		}
		return kun;
	}*/

	public AnsattReg getAnsattViaNavn(AnsattReg reg, String navn)	//åpenbar
	{
		if(reg == null)
			return ansReg.finnAnsatte(navn);
		return reg.finnAnsatte(navn);
	}/*

	public void setNåForsikringsNr()	//noe med forsikringsnummer
	{
		forReg.setNåNr();
	}

	public void setNåSkadeNr()	//noe med skadenummer
	{
		skaReg.setNåNr();
	}*/

	public void exit()	//avslutter program og lagrer nummere
	{
		//forReg.saveNåNr();
		//skaReg.saveNåNr();
		data.skrivRegister(this);
		System.exit(0);
	}
}
