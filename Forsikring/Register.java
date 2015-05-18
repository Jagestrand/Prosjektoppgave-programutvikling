import java.util.*;
import java.io.*;
import javax.swing.*;

public class Register implements Serializable
{
	private static final long serialVersionUID = 42L;
	private BilForsikringsReg forReg;
	private Forsikring1 f;
	private AnsattReg ansReg;
	private KundeReg kunReg;
	private ForsikringsReg fReg;
	private BilForsikringsReg bilReg;
	private BåtForsikringsReg båtReg;
	private HusForsikringsReg husReg;
	private HytteForsikringsReg hytReg;
	private SkademeldingReg skaReg;
	private Datasjef data;

	public Register( Datasjef dataer )
	{
		data = dataer;
		fReg = new ForsikringsReg();
		forReg = new BilForsikringsReg();
		ansReg = new AnsattReg();
		kunReg = new KundeReg();
		bilReg = new BilForsikringsReg();
		båtReg = new BåtForsikringsReg();
		husReg = new HusForsikringsReg();
		hytReg = new HytteForsikringsReg();
		skaReg = new SkademeldingReg();
	}

	public void nyAnsatt( Ansatt ans )		//legger inn ny ansatt
	{
		ansReg.add(ans);
	}

	public void nyKunde(Kunde kun)		//legger inn ny kunde
	{
		kunReg.add(kun);
	}

	/*public ForsikringsReg getForsikringer()
	{
		return forReg;
	}*/

	public void nyBil(BilForsikring bil)
	{
		bil.getKunde().nyForsikring(bil);
		forReg.add(bil);
	}

	public void nyBåt(BåtForsikring båt)
	{
		båt.getKunde().nyForsikring(båt);
		båtReg.add(båt);
	}

	public void nyttHus(HusForsikring hus)
	{
		hus.getKunde().nyForsikring(hus);
		husReg.add(hus);
	}

	public void nyHytte(HytteForsikring hyt)
	{
		hyt.getKunde().nyForsikring(hyt);
		hytReg.add(hyt);
	}

	public void nySkade(Skademelding skad, BilForsikring bil)		//legger inn ny skademelding
	{
		skad.getKunde().nySkademelding(skad);
		bil.nySkade(skad);
		skaReg.add(skad);
	}

	public void nySkade(Skademelding skad, BåtForsikring båt)		//legger inn ny skademelding
	{
		skad.getKunde().nySkademelding(skad);
		båt.nySkade(skad);
		skaReg.add(skad);
	}

	public void nySkade(Skademelding skad, HusForsikring hus)		//legger inn ny skademelding
	{
		skad.getKunde().nySkademelding(skad);
		hus.nySkade(skad);
		skaReg.add(skad);
	}

	public void nySkade(Skademelding skad, HytteForsikring hytt)		//legger inn ny skademelding
	{
		skad.getKunde().nySkademelding(skad);
		hytt.nySkade(skad);
		skaReg.add(skad);
	}

	public AnsattReg getAnsatte()
	{
		return ansReg;
	}

	public KundeReg getKunder()
	{
		return kunReg;
	}

	public BilForsikringsReg getBiler()
	{
		return forReg;
	}

	public BåtForsikringsReg getBåter()
	{
		return båtReg;
	}

	public HusForsikringsReg getHus()
	{
		return husReg;
	}

	public HytteForsikringsReg getHytter()
	{
		return hytReg;
	}

	public ForsikringsReg getForsikringer()
	{
		return fReg;
	}

	public SkademeldingReg getSkademeldinger()
	{
		return skaReg;
	}

	public void slettAnsatt(Ansatt anna)
	{
		ansReg.remove(anna);
	}

	public void deaktiverBruker(Ansatt anna)
	{
		Calendar slutt = Calendar.getInstance();
		anna.setAvslutta(slutt);
		anna.setAktiv(false);
	}

	public Ansatt getAnsattViaNr(int nr)
	{
		String mnr = Integer.toString(nr);
		AnsattReg aReg = ansReg.findDoctorByPersonNr(mnr);
		if(aReg == null)
			return null;
		Iterator<Ansatt> iter = aReg.iterator();
		return iter.next();
	}

	public Ansatt getAnsattViaNr(String nr)
	{
		String mnr = nr;
		AnsattReg aReg = ansReg.findDoctorByPersonNr(mnr);
		if(aReg == null)
			return null;
		Iterator<Ansatt> iter = aReg.iterator();
		return iter.next();
	}

	public Ansatt getAnsattViaAnsattNr(String nr)
	{
		//String anr = nr;
		AnsattReg aReg = ansReg.findDoctorByAnsattNr(nr);
		if(aReg == null)
			return null;
		else
		{
			Iterator<Ansatt> iter = aReg.iterator();
			return iter.next();
		}
	}

	public AnsattReg getAnsattViaNr(AnsattReg reg, String nr)
	{
		if(reg == null)
			return ansReg.findDoctorByPersonNr(nr);
		return reg.findDoctors(nr);
	}

	public AnsattReg getAnsattViaAvdeling(AnsattReg reg, String avde)/*returnerer en liste over alle doktorer som har skrevet resept på en med medisinn
																i kategori c, tar imot en liste eller bruker klassens egen liste*/
	{
		if(reg == null)
			return ansReg.findDoctors(avde);
		return reg.findDoctorByAvdeling(avde);
	}

	public AnsattReg getAnsattViaNavn(AnsattReg reg, String navn)	//åpenbar
	{
		if(reg == null)
			return ansReg.findDoctors(navn);	//finnAnsatte(navn);
		return reg.findDoctors(navn);
	}

	public AnsattReg getAnsattViaAnsattNr(AnsattReg reg, String anr)
	{
		if(reg == null)
			return ansReg.findDoctorByAnsattNr(anr);
		return reg.findDoctorByAnsattNr(anr);
	}

	public BilForsikring getBilViaNr(int nr)
	{
		BilForsikring rem;
		Iterator<BilForsikring> iter = forReg.iterator();
		while(iter.hasNext())
		{
			rem = iter.next();
			if(rem.getForsikringsNr() == nr)
				return rem;
		}
		return null;
	}

	public BilForsikringsReg getBilViaNr(BilForsikringsReg reg, int nr)
	{				//forsikringsnr
		if(reg == null)
			reg = forReg;
		BilForsikring temp;
		BilForsikringsReg rem = new BilForsikringsReg();
		Iterator<BilForsikring> iter = reg.iterator();
		while(iter.hasNext())
		{
			temp = iter.next();
			if(temp.getForsikringsNr() == nr)
				rem.add(temp);
		}
		return rem;
	}

	public BilForsikringsReg getBilViaKundeNr(BilForsikringsReg reg, String knr)
	{
		if(reg == null)
			reg = forReg;
		BilForsikring temp;
		BilForsikringsReg rem = new BilForsikringsReg();
		Iterator<BilForsikring> iter = reg.iterator();
		while(iter.hasNext())
		{
			temp = iter.next();
			if(temp.getKundeNr() == knr)
				rem.add(temp);
		}
		return rem;
	}

	public BilForsikring getBilViaRegNr(String nr)
	{
		BilForsikring rem;
		Iterator<BilForsikring> iter = forReg.iterator();
		while(iter.hasNext())
		{
			rem = iter.next();
			if(rem.getRegistreringsnr() == nr)
				return rem;
		}
		return null;
	}

	public BilForsikringsReg getBilViaRegNr(BilForsikringsReg reg, String nr)
	{
		if(reg == null)
			reg = forReg;
		BilForsikring temp;
		BilForsikringsReg rem = new BilForsikringsReg();
		Iterator<BilForsikring> iter = reg.iterator();
		while(iter.hasNext())
		{
			temp = iter.next();
			if(temp.getRegistreringsnr() == nr)
				rem.add(temp);
		}
		return rem;
	}

	public BilForsikringsReg getBilViaEier(BilForsikringsReg reg, String navn)
	{
		if(reg == null)
			reg = forReg;
		BilForsikring temp;
		BilForsikringsReg rem = new BilForsikringsReg();
		Iterator<BilForsikring> iter = reg.iterator();
		while(iter.hasNext())
		{
			temp = iter.next();
			if(temp.getEiernavn().equals(navn))
				rem.add(temp);
		}
		return rem;
	}

	public BilForsikringsReg getBilViaType(BilForsikringsReg reg, String t)
	{
		if(reg == null)
			reg = forReg;
		BilForsikring temp;
		BilForsikringsReg rem = new BilForsikringsReg();
		Iterator<BilForsikring> iter = reg.iterator();
		while(iter.hasNext())
		{
			temp = iter.next();
			if(temp.getType().equals(t))
				rem.add(temp);
		}
		return rem;
	}

	public BilForsikringsReg getBilViaModell(BilForsikringsReg reg, String m)
	{
		if(reg == null)
			reg = forReg;
		BilForsikring temp;
		BilForsikringsReg rem = new BilForsikringsReg();
		Iterator<BilForsikring> iter = reg.iterator();
		while(iter.hasNext())
		{
			temp = iter.next();
			if(temp.getModell().equals(m))
				rem.add(temp);
		}
		return rem;
	}

	public BilForsikringsReg getBilViaRegÅr(BilForsikringsReg reg, int år)
	{
		if(reg == null)
			reg = forReg;
		BilForsikring temp;
		BilForsikringsReg rem = new BilForsikringsReg();
		Iterator<BilForsikring> iter = reg.iterator();
		while(iter.hasNext())
		{
			temp = iter.next();
			if(temp.getRegistreringsår() == år)
				rem.add(temp);
		}
		return rem;
	}

	public BilForsikringsReg getBilViaKjørelengde(BilForsikringsReg reg, int l)
	{
		if(reg == null)
			reg = forReg;
		BilForsikring temp;
		BilForsikringsReg rem = new BilForsikringsReg();
		Iterator<BilForsikring> iter = reg.iterator();
		while(iter.hasNext())
		{
			temp = iter.next();
			if(temp.getKjørelengde() == l)
				rem.add(temp);
		}
		return rem;
	}

				//BÅT:

	public BåtForsikring getBåtViaNr(int nr)
	{
		BåtForsikring rem;
		Iterator<BåtForsikring> iter = båtReg.iterator();
		while(iter.hasNext())
		{
			rem = iter.next();
			if(rem.getForsikringsNr() == nr)
				return rem;
		}
		return null;
	}

	public BåtForsikringsReg getBåtViaNr(BåtForsikringsReg reg, int nr)
	{				//forsikringsnr
		if(reg == null)
			reg = båtReg;
		BåtForsikring temp;
		BåtForsikringsReg rem = new BåtForsikringsReg();
		Iterator<BåtForsikring> iter = reg.iterator();
		while(iter.hasNext())
		{
			temp = iter.next();
			if(temp.getForsikringsNr() == nr)
				rem.add(temp);
		}
		return rem;
	}

	public BåtForsikringsReg getBåtViaKundeNr(BåtForsikringsReg reg, String knr)
	{
		if(reg == null)
			reg = båtReg;
		BåtForsikring temp;
		BåtForsikringsReg rem = new BåtForsikringsReg();
		Iterator<BåtForsikring> iter = reg.iterator();
		while(iter.hasNext())
		{
			temp = iter.next();
			if(temp.getKundeNr() == knr)
				rem.add(temp);
		}
		return rem;
	}

	public BåtForsikring getBåtViaRegNr(String nr)
	{
		BåtForsikring rem;
		Iterator<BåtForsikring> iter = båtReg.iterator();
		while(iter.hasNext())
		{
			rem = iter.next();
			if(rem.getRegistreringsnr() == nr)
				return rem;
		}
		return null;
	}

	public BåtForsikringsReg getBåtViaRegNr(BåtForsikringsReg reg, String nr)
	{
		if(reg == null)
			reg = båtReg;
		BåtForsikring temp;
		BåtForsikringsReg rem = new BåtForsikringsReg();
		Iterator<BåtForsikring> iter = reg.iterator();
		while(iter.hasNext())
		{
			temp = iter.next();
			if(temp.getRegistreringsnr() == nr)
				rem.add(temp);
		}
		return rem;
	}

	public BåtForsikringsReg getBåtViaEier(BåtForsikringsReg reg, String navn)
	{
		if(reg == null)
			reg = båtReg;
		BåtForsikring temp;
		BåtForsikringsReg rem = new BåtForsikringsReg();
		Iterator<BåtForsikring> iter = reg.iterator();
		while(iter.hasNext())
		{
			temp = iter.next();
			if(temp.getEiernavn().equals(navn))
				rem.add(temp);
		}
		return rem;
	}

	public BåtForsikringsReg getBåtViaType(BåtForsikringsReg reg, String t)
	{
		if(reg == null)
			reg = båtReg;
		BåtForsikring temp;
		BåtForsikringsReg rem = new BåtForsikringsReg();
		Iterator<BåtForsikring> iter = reg.iterator();
		while(iter.hasNext())
		{
			temp = iter.next();
			if(temp.getType().equals(t))
				rem.add(temp);
		}
		return rem;
	}

	public BåtForsikringsReg getBåtViaModell(BåtForsikringsReg reg, String m)
	{
		if(reg == null)
			reg = båtReg;
		BåtForsikring temp;
		BåtForsikringsReg rem = new BåtForsikringsReg();
		Iterator<BåtForsikring> iter = reg.iterator();
		while(iter.hasNext())
		{
			temp = iter.next();
			if(temp.getModell().equals(m))
				rem.add(temp);
		}
		return rem;
	}

	public BåtForsikringsReg getBåtViaRegÅr(BåtForsikringsReg reg, int år)
	{
		if(reg == null)
			reg = båtReg;
		BåtForsikring temp;
		BåtForsikringsReg rem = new BåtForsikringsReg();
		Iterator<BåtForsikring> iter = reg.iterator();
		while(iter.hasNext())
		{
			temp = iter.next();
			if(temp.getÅrsmodell() == år)
				rem.add(temp);
		}
		return rem;
	}

	public BåtForsikringsReg getBåtViaLengde(BåtForsikringsReg reg, int l)
	{
		if(reg == null)
			reg = båtReg;
		BåtForsikring temp;
		BåtForsikringsReg rem = new BåtForsikringsReg();
		Iterator<BåtForsikring> iter = reg.iterator();
		while(iter.hasNext())
		{
			temp = iter.next();
			if(temp.getBåtlengde() == l)
				rem.add(temp);
		}
		return rem;
	}

	public BåtForsikringsReg getBåtViaMotor(BåtForsikringsReg reg, String m)
	{
		if(reg == null)
			reg = båtReg;
		BåtForsikring temp;
		BåtForsikringsReg rem = new BåtForsikringsReg();
		Iterator<BåtForsikring> iter = reg.iterator();
		while(iter.hasNext())
		{
			temp = iter.next();
			if(temp.getMotortype().equals(m))
				rem.add(temp);
		}
		return rem;
	}

	public BåtForsikringsReg getBåtViaStyrke(BåtForsikringsReg reg, int s)
	{
		if(reg == null)
			reg = båtReg;
		BåtForsikring temp;
		BåtForsikringsReg rem = new BåtForsikringsReg();
		Iterator<BåtForsikring> iter = reg.iterator();
		while(iter.hasNext())
		{
			temp = iter.next();
			if(temp.getMotorstyrke() == s)
				rem.add(temp);
		}
		return rem;
	}

					//HUS:

	public HusForsikring getHusViaNr(int nr)
	{
		HusForsikring rem;
		Iterator<HusForsikring> iter = husReg.iterator();
		while(iter.hasNext())
		{
			rem = iter.next();
			if(rem.getForsikringsNr() == nr)
				return rem;
		}
		return null;
	}

	public HusForsikringsReg getHusViaNr(HusForsikringsReg reg, int nr)
	{				//forsikringsnr
		if(reg == null)
			reg = husReg;
		HusForsikring temp;
		HusForsikringsReg rem = new HusForsikringsReg();
		Iterator<HusForsikring> iter = reg.iterator();
		while(iter.hasNext())
		{
			temp = iter.next();
			if(temp.getForsikringsNr() == nr)
				rem.add(temp);
		}
		return rem;
	}

	public HusForsikringsReg getHusViaKundeNr(HusForsikringsReg reg, String knr)
	{
		if(reg == null)
			reg = husReg;
		HusForsikring temp;
		HusForsikringsReg rem = new HusForsikringsReg();
		Iterator<HusForsikring> iter = reg.iterator();
		while(iter.hasNext())
		{
			temp = iter.next();
			if(temp.getKundeNr() == knr)
				rem.add(temp);
		}
		return rem;
	}

	public HusForsikring getHusViaAdresse(String adr)
	{
		HusForsikring rem;
		Iterator<HusForsikring> iter = husReg.iterator();
		while(iter.hasNext())
		{
			rem = iter.next();
			if(rem.getAdresse() == adr)
				return rem;
		}
		return null;
	}

	public HusForsikringsReg getHusViaAdresse(HusForsikringsReg reg, String adr)
	{
		if(reg == null)
			reg = husReg;
		HusForsikring temp;
		HusForsikringsReg rem = new HusForsikringsReg();
		Iterator<HusForsikring> iter = reg.iterator();
		while(iter.hasNext())
		{
			temp = iter.next();
			if(temp.getAdresse() == adr)
				rem.add(temp);
		}
		return rem;
	}

	public HusForsikringsReg getHusViaStandard(HusForsikringsReg reg, String stand)
	{
		if(reg == null)
			reg = husReg;
		HusForsikring temp;
		HusForsikringsReg rem = new HusForsikringsReg();
		Iterator<HusForsikring> iter = reg.iterator();
		while(iter.hasNext())
		{
			temp = iter.next();
			if(temp.getStandard().equals(stand))
				rem.add(temp);
		}
		return rem;
	}

	public HusForsikringsReg getHusViaType(HusForsikringsReg reg, String t)
	{
		if(reg == null)
			reg = husReg;
		HusForsikring temp;
		HusForsikringsReg rem = new HusForsikringsReg();
		Iterator<HusForsikring> iter = reg.iterator();
		while(iter.hasNext())
		{
			temp = iter.next();
			if(temp.getBoligtype().equals(t))
				rem.add(temp);
		}
		return rem;
	}

	public HusForsikringsReg getHusViaMateriale(HusForsikringsReg reg, String m)
	{
		if(reg == null)
			reg = husReg;
		HusForsikring temp;
		HusForsikringsReg rem = new HusForsikringsReg();
		Iterator<HusForsikring> iter = reg.iterator();
		while(iter.hasNext())
		{
			temp = iter.next();
			if(temp.getMateriale().equals(m))
				rem.add(temp);
		}
		return rem;
	}

	public HusForsikringsReg getHusViaBeløpB(HusForsikringsReg reg, int b)
	{
		if(reg == null)
			reg = husReg;
		HusForsikring temp;
		HusForsikringsReg rem = new HusForsikringsReg();
		Iterator<HusForsikring> iter = reg.iterator();
		while(iter.hasNext())
		{
			temp = iter.next();
			if(temp.getBeløpBygg() == b)
				rem.add(temp);
		}
		return rem;
	}

	public HusForsikringsReg getHusViaBeløpI(HusForsikringsReg reg, int i)
	{
		if(reg == null)
			reg = husReg;
		HusForsikring temp;
		HusForsikringsReg rem = new HusForsikringsReg();
		Iterator<HusForsikring> iter = reg.iterator();
		while(iter.hasNext())
		{
			temp = iter.next();
			if(temp.getBeløpInnbo() == i)
				rem.add(temp);
		}
		return rem;
	}

	public HusForsikringsReg getHusViaBeløpT(HusForsikringsReg reg, int t)
	{
		if(reg == null)
			reg = husReg;
		HusForsikring temp;
		HusForsikringsReg rem = new HusForsikringsReg();
		Iterator<HusForsikring> iter = reg.iterator();
		while(iter.hasNext())
		{
			temp = iter.next();
			if((temp.getBeløpInnbo() + temp.getBeløpBygg()) == t)
				rem.add(temp);
		}
		return rem;
	}

	public HusForsikringsReg getHusViaÅr(HusForsikringsReg reg, int år)
	{
		if(reg == null)
			reg = husReg;
		HusForsikring temp;
		HusForsikringsReg rem = new HusForsikringsReg();
		Iterator<HusForsikring> iter = reg.iterator();
		while(iter.hasNext())
		{
			temp = iter.next();
			if(temp.getByggeår() == år)
				rem.add(temp);
		}
		return rem;
	}

	public HusForsikringsReg getHusViaStørrelse(HusForsikringsReg reg, int s)
	{
		if(reg == null)
			reg = husReg;
		HusForsikring temp;
		HusForsikringsReg rem = new HusForsikringsReg();
		Iterator<HusForsikring> iter = reg.iterator();
		while(iter.hasNext())
		{
			temp = iter.next();
			if(temp.getKvadratmeter() == s)
				rem.add(temp);
		}
		return rem;
	}
				//HYTTE

	public HytteForsikring getHytteViaNr(int nr)
	{
		HytteForsikring rem;
		Iterator<HytteForsikring> iter = hytReg.iterator();
		while(iter.hasNext())
		{
			rem = iter.next();
			if(rem.getForsikringsNr() == nr)
				return rem;
		}
		return null;
	}

	public HytteForsikringsReg getHytteViaNr(HytteForsikringsReg reg, int nr)
	{				//forsikringsnr
		if(reg == null)
			reg = hytReg;
		HytteForsikring temp;
		HytteForsikringsReg rem = new HytteForsikringsReg();
		Iterator<HytteForsikring> iter = reg.iterator();
		while(iter.hasNext())
		{
			temp = iter.next();
			if(temp.getForsikringsNr() == nr)
				rem.add(temp);
		}
		return rem;
	}

	public HytteForsikringsReg getHytteViaKundeNr(HytteForsikringsReg reg, String knr)
	{
		if(reg == null)
			reg = hytReg;
		HytteForsikring temp;
		HytteForsikringsReg rem = new HytteForsikringsReg();
		Iterator<HytteForsikring> iter = reg.iterator();
		while(iter.hasNext())
		{
			temp = iter.next();
			if(temp.getKundeNr() == knr)
				rem.add(temp);
		}
		return rem;
	}

	public HytteForsikring getHytteViaAdresse(String adr)
	{
		HytteForsikring rem;
		Iterator<HytteForsikring> iter = hytReg.iterator();
		while(iter.hasNext())
		{
			rem = iter.next();
			if(rem.getAdresse() == adr)
				return rem;
		}
		return null;
	}

	public HytteForsikringsReg getHytteViaAdresse(HytteForsikringsReg reg, String adr)
	{
		if(reg == null)
			reg = hytReg;
		HytteForsikring temp;
		HytteForsikringsReg rem = new HytteForsikringsReg();
		Iterator<HytteForsikring> iter = reg.iterator();
		while(iter.hasNext())
		{
			temp = iter.next();
			if(temp.getAdresse() == adr)
				rem.add(temp);
		}
		return rem;
	}

	public HytteForsikringsReg getHytteViaStandard(HytteForsikringsReg reg, String stand)
	{
		if(reg == null)
			reg = hytReg;
		HytteForsikring temp;
		HytteForsikringsReg rem = new HytteForsikringsReg();
		Iterator<HytteForsikring> iter = reg.iterator();
		while(iter.hasNext())
		{
			temp = iter.next();
			if(temp.getStandard().equals(stand))
				rem.add(temp);
		}
		return rem;
	}

	public HytteForsikringsReg getHytteViaType(HytteForsikringsReg reg, String t)
	{
		if(reg == null)
			reg = hytReg;
		HytteForsikring temp;
		HytteForsikringsReg rem = new HytteForsikringsReg();
		Iterator<HytteForsikring> iter = reg.iterator();
		while(iter.hasNext())
		{
			temp = iter.next();
			if(temp.getBoligtype().equals(t))
				rem.add(temp);
		}
		return rem;
	}

	public HytteForsikringsReg getHytteViaMateriale(HytteForsikringsReg reg, String m)
	{
		if(reg == null)
			reg = hytReg;
		HytteForsikring temp;
		HytteForsikringsReg rem = new HytteForsikringsReg();
		Iterator<HytteForsikring> iter = reg.iterator();
		while(iter.hasNext())
		{
			temp = iter.next();
			if(temp.getMateriale().equals(m))
				rem.add(temp);
		}
		return rem;
	}

	public HytteForsikringsReg getHytteViaBeløpB(HytteForsikringsReg reg, int b)
	{
		if(reg == null)
			reg = hytReg;
		HytteForsikring temp;
		HytteForsikringsReg rem = new HytteForsikringsReg();
		Iterator<HytteForsikring> iter = reg.iterator();
		while(iter.hasNext())
		{
			temp = iter.next();
			if(temp.getBeløpBygg() == b)
				rem.add(temp);
		}
		return rem;
	}

	public HytteForsikringsReg getHytteViaBeløpI(HytteForsikringsReg reg, int i)
	{
		if(reg == null)
			reg = hytReg;
		HytteForsikring temp;
		HytteForsikringsReg rem = new HytteForsikringsReg();
		Iterator<HytteForsikring> iter = reg.iterator();
		while(iter.hasNext())
		{
			temp = iter.next();
			if(temp.getBeløpInnbo() == i)
				rem.add(temp);
		}
		return rem;
	}

	public HytteForsikringsReg getHytteViaBeløpT(HytteForsikringsReg reg, int t)
	{
		if(reg == null)
			reg = hytReg;
		HytteForsikring temp;
		HytteForsikringsReg rem = new HytteForsikringsReg();
		Iterator<HytteForsikring> iter = reg.iterator();
		while(iter.hasNext())
		{
			temp = iter.next();
			if((temp.getBeløpInnbo() + temp.getBeløpBygg()) == t)
				rem.add(temp);
		}
		return rem;
	}

	public HytteForsikringsReg getHytteViaÅr(HytteForsikringsReg reg, int år)
	{
		if(reg == null)
			reg = hytReg;
		HytteForsikring temp;
		HytteForsikringsReg rem = new HytteForsikringsReg();
		Iterator<HytteForsikring> iter = reg.iterator();
		while(iter.hasNext())
		{
			temp = iter.next();
			if(temp.getByggeår() == år)
				rem.add(temp);
		}
		return rem;
	}

	public HytteForsikringsReg getHytteViaStørrelse(HytteForsikringsReg reg, int s)
	{
		if(reg == null)
			reg = hytReg;
		HytteForsikring temp;
		HytteForsikringsReg rem = new HytteForsikringsReg();
		Iterator<HytteForsikring> iter = reg.iterator();
		while(iter.hasNext())
		{
			temp = iter.next();
			if(temp.getKvadratmeter() == s)
				rem.add(temp);
		}
		return rem;
	}



	public Skademelding getSkadeViaNr( int nr )		//finner skade via skadenr
	{
		Skademelding ska;
		Iterator<Skademelding> iter = skaReg.iterator();
		while(iter.hasNext() )
		{
			ska = iter.next();
			if(ska.getSkadeNr() == nr)
				return ska;
		}
		return null;
	}

	public SkademeldingReg getSkadeViaNr( SkademeldingReg reg, String nr )	//finner skade via skadenr
	{
		if( reg == null || nr == "" )
			reg = skaReg;
		int nnr = Integer.valueOf(nr);
		Skademelding prøv;
		SkademeldingReg ade = new SkademeldingReg();
		Iterator<Skademelding> iter = reg.iterator();
		while(iter.hasNext() )
		{
			prøv = iter.next();
			if(prøv.getSkadeNr() == nnr)
				ade.add(prøv);
		}
		return ade;
	}

	public SkademeldingReg getSkadeViaNr( SkademeldingReg reg, int nr )	//finner skade via skadenr
	{
		if( reg == null )
			reg = skaReg;
		Skademelding prøv;
		SkademeldingReg ade = new SkademeldingReg();
		Iterator<Skademelding> iter = reg.iterator();
		while(iter.hasNext() )
		{
			prøv = iter.next();
			if(prøv.getSkadeNr() == nr)
				ade.add(prøv);
		}
		return ade;
	}

	public SkademeldingReg getSkadeViaKundeNr(SkademeldingReg reg, String nr)
	{
		if(reg == null)
			reg = skaReg;
		Skademelding temp;
		SkademeldingReg rem = new SkademeldingReg();
		Iterator<Skademelding> iter = reg.iterator();
		while(iter.hasNext())
		{
			temp = iter.next();
			if(temp.getKundeNr().equals(nr))
				rem.add(temp);
		}
		return rem;
	}

	public SkademeldingReg getSkadeViaType(SkademeldingReg reg, String t)
	{
		if(reg == null)
			reg = skaReg;
		Skademelding temp;
		SkademeldingReg rem = new SkademeldingReg();
		Iterator<Skademelding> iter = reg.iterator();
		while(iter.hasNext())
		{
			temp = iter.next();
			if(temp.getSkadeType().equals(t))
				rem.add(temp);
		}
		return rem;
	}

	public SkademeldingReg getSkadeViaAdresse(SkademeldingReg reg, String adr)
	{
		if(reg == null)
			reg = skaReg;
		Skademelding temp;
		SkademeldingReg rem = new SkademeldingReg();
		Iterator<Skademelding> iter = reg.iterator();
		while(iter.hasNext())
		{
			temp = iter.next();
			if(temp.getSkadeAdresse().equals(adr))
				rem.add(temp);
		}
		return rem;
	}

	public SkademeldingReg getSkadeViaStatus(SkademeldingReg reg, String s)
	{
		if(reg == null)
			reg = skaReg;
		Skademelding temp;
		SkademeldingReg rem = new SkademeldingReg();
		Iterator<Skademelding> iter = reg.iterator();
		while(iter.hasNext())
		{
			temp = iter.next();
			if(temp.getStatus().equals(s))
				rem.add(temp);
		}
		return rem;
	}

	public SkademeldingReg getSkadeViaTakst(SkademeldingReg reg, int t)
	{
		if(reg == null)
			reg = skaReg;
		Skademelding temp;
		SkademeldingReg rem = new SkademeldingReg();
		Iterator<Skademelding> iter = reg.iterator();
		while(iter.hasNext())
		{
			temp = iter.next();
			if(temp.getTakst() == t)
				rem.add(temp);
		}
		return rem;
	}

	public SkademeldingReg getSkadeViaTakst(SkademeldingReg reg, String t)
	{
		if(reg == null || t == "")
			reg = skaReg;
		int tt = Integer.valueOf(t);
		Skademelding temp;
		SkademeldingReg rem = new SkademeldingReg();
		Iterator<Skademelding> iter = reg.iterator();
		while(iter.hasNext())
		{
			temp = iter.next();
			if(temp.getTakst() == tt)
				rem.add(temp);
		}
		return rem;
	}

	public SkademeldingReg getSkadeViaErstatning(SkademeldingReg reg, int e)
	{
		if(reg == null)
			reg = skaReg;
		Skademelding temp;
		SkademeldingReg rem = new SkademeldingReg();
		Iterator<Skademelding> iter = reg.iterator();
		while(iter.hasNext())
		{
			temp = iter.next();
			if(temp.getUtbetalt() == e)
				rem.add(temp);
		}
		return rem;
	}

	public SkademeldingReg getSkadeViaErstatning(SkademeldingReg reg, String e)
	{
		if(reg == null || e == "")
			reg = skaReg;
		int ee = Integer.valueOf(e);
		Skademelding temp;
		SkademeldingReg rem = new SkademeldingReg();
		Iterator<Skademelding> iter = reg.iterator();
		while(iter.hasNext())
		{
			temp = iter.next();
			if(temp.getUtbetalt() == ee)
				rem.add(temp);
		}
		return rem;
	}

	public SkademeldingReg getSkadeViaDato(SkademeldingReg reg, Calendar d)
	{
		if(reg == null)
			reg = skaReg;
		Skademelding temp;
		SkademeldingReg rem = new SkademeldingReg();
		Iterator<Skademelding> iter = reg.iterator();
		while(iter.hasNext())
		{
			temp = iter.next();
			if(temp.getDato() == d)
				rem.add(temp);
		}
		return rem;
	}

	public void godkjennTakst(Skademelding ska)
	{
		int kr = ska.getTakst();
		ska.setUtbetalt(kr);
		ska.setGodkjent(true);
		ska.setAvslått(false);
		ska.setStatus("Ja");
	}

	public void avslåTakst(Skademelding ska, int kr)
	{
		ska.setUtbetalt(kr);
		ska.setAvslått(true);
		ska.setGodkjent(false);
		ska.setStatus("Nei");
	}


	/*public ForsikringsReg getForsikringFørDato(ForsikringsReg reg, Calendar dato)	//finner forsikringer kjøpt før dato
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
	}*/

	public SkademeldingReg getSkadeFørDato(SkademeldingReg reg, Calendar dato)	//finner skademeldinger registrert før dato
	{
		if( reg == null )
			reg = skaReg;
		Iterator<Skademelding> iter = reg.iterator();
		SkademeldingReg skads = new SkademeldingReg();
		Skademelding prøv;
		while(iter.hasNext() )
		{
			prøv = iter.next();
			if(prøv.getDato().before(dato) )
				skads.add(prøv);
		}
		return skads;
	}

	public SkademeldingReg getSkadeEtterDato(SkademeldingReg reg, Calendar dato)	//finner skademeldinger registrert etter dato
	{
		if( reg == null )
			reg = skaReg;
		Iterator<Skademelding> iter = reg.iterator();
		SkademeldingReg skads = new SkademeldingReg();
		Skademelding prøv;
		while(iter.hasNext() )
		{
			prøv = iter.next();
			if(prøv.getDato().after(dato) )
				skads.add(prøv);
		}
		return skads;
	}
	public SkademeldingReg getSkadePerKunde( SkademeldingReg reg, String kundenr )	//finner skader registrert på kunde
	{
		KundeReg kReg = kunReg.finnKundeViaKundenr(kundenr);
		Iterator<Kunde> kunIter = kReg.iterator();
		Kunde kun;
		if(kunIter.hasNext())
			kun = kunIter.next();
		else
			return new SkademeldingReg();
		if(kunIter.hasNext())
			JOptionPane.showMessageDialog(null, "Flere personer funnet, vennligst spesifiser søket bedre");

		SkademeldingReg sr = kun.getSkademeldinger();
		if(reg == null)
			return sr;

		SkademeldingReg ade = new SkademeldingReg();
		Iterator<Skademelding> iter = sr.iterator();
		Skademelding prøv;
		while(iter.hasNext())
		{
			prøv = iter.next();
			if(reg.contains(prøv))
				ade.add(prøv);
		}
		return ade;
	}/*
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
	}*/

	public Kunde getKundeViaNummer(String nr)
	{
		String mnr = nr;
		KundeReg kReg = kunReg.finnKundeViaNr(mnr);
		if(kReg == null)
			return null;
		Iterator<Kunde> iter = kReg.iterator();
		return iter.next();
	}

	public KundeReg getKundeViaNummer(KundeReg reg, String nr)
	{
		if(reg == null)
			reg = kunReg;
		Kunde temp;
		KundeReg rem = new KundeReg();
		Iterator<Kunde> iter = reg.iterator();
		while(iter.hasNext())
		{
			temp = iter.next();
			if(temp.getPersonNr().equals(nr))
				rem.add(temp);
		}
		return rem;
	}

	public Kunde getKundeViaKundeNr(String nr)
	{
		String anr = nr;
		KundeReg kReg = kunReg.finnKundeViaKundenr(anr);
		if(kReg == null)
			return null;
		else
		{
			Iterator<Kunde> iter = kReg.iterator();
			return iter.next();
		}
	}

	public KundeReg getKundeViaKundeNr(KundeReg reg, String knr)
	{
		if(reg == null)
			reg = kunReg;
		Kunde temp;
		KundeReg rem = new KundeReg();
		Iterator<Kunde> iter = reg.iterator();
		while(iter.hasNext())
		{
			temp = iter.next();
			if(temp.getKundeNr().equals(knr))
				rem.add(temp);
		}
		return rem;
	}

	public KundeReg getKunderViaAktiv(KundeReg reg, boolean a)
	{
		if(reg == null)
			reg = kunReg;
		if(!a)
			return reg;
		KundeReg res = new KundeReg();
		Iterator<Kunde> iter = res.iterator();
		Kunde temp;
		while(iter.hasNext())
		{
			temp = iter.next();
			if(temp.getAktiv() == a)
				res.add(temp);
			else
				res.add(temp);
		}
		return res;
	}


	public KundeReg getKundeViaNavn(KundeReg reg, String navn)	//åpenbar
	{
		if(reg == null)
			reg = kunReg;
		Kunde temp;
		KundeReg rem = new KundeReg();
		Iterator<Kunde> iter = reg.iterator();
		while(iter.hasNext())
		{
			temp = iter.next();
			if(temp.getFornavn().toLowerCase().equals(navn.toLowerCase()) ||
				temp.getEtternavn().toLowerCase().equals(navn.toLowerCase()))
				rem.add(temp);
		}
		return rem;
	}

	public KundeReg getKundeViaTelefon(KundeReg reg, String tlf)
	{
		if(reg == null)
			reg = kunReg;
		Kunde temp;
		KundeReg rem = new KundeReg();
		Iterator<Kunde> iter = reg.iterator();
		while(iter.hasNext())
		{
			temp = iter.next();
			if(temp.getTelefonNr().equals(tlf))
				rem.add(temp);
		}
		return rem;
	}

	public KundeReg getKundeViaAdresse(KundeReg reg, String adr)
	{
		if(reg == null)
			reg = kunReg;
		Kunde temp;
		KundeReg rem = new KundeReg();
		Iterator<Kunde> iter = reg.iterator();
		while(iter.hasNext())
		{
			temp = iter.next();
			if(temp.getAdresse().equals(adr))
				rem.add(temp);
		}
		return rem;
	}

	public KundeReg getKundeViaPostnr(KundeReg reg, String pnr)
	{
		if(reg == null)
			reg = kunReg;
		Kunde temp;
		KundeReg rem = new KundeReg();
		Iterator<Kunde> iter = reg.iterator();
		while(iter.hasNext())
		{
			temp = iter.next();
			if(temp.getPostnr().equals(pnr))
				rem.add(temp);
		}
		return rem;
	}

	public KundeReg getKundeViaBy(KundeReg reg, String by)
	{
		if(reg == null)
			reg = kunReg;
		Kunde temp;
		KundeReg rem = new KundeReg();
		Iterator<Kunde> iter = reg.iterator();
		while(iter.hasNext())
		{
			temp = iter.next();
			if(temp.getPoststed().equals(by))
				rem.add(temp);
		}
		return rem;
	}

	/*
	public void setNåForsikringsNr()	//noe med forsikringsnummer
	{
		forReg.setNåNr();
	}

	public void setNåSkadeNr()	//noe med skadenummer
	{
		skaReg.setNåNr();
	}*/

	public void setNåAnsattNr()
	{
		ansReg.setNåNr();
	}

	public void setNåKundeNr()
	{
		kunReg.setNåNr();
	}

	public void setNåForsikringsNr()
	{
		forReg.setNåNr();
	}

	public void exit()	//avslutter program og lagrer nummere
	{
		forReg.lagreNåNr();
		//skaReg.lagreNåNr();
		//bilReg.lagreNrNå();
		ansReg.lagreNrNå();
		kunReg.lagreNrNå();

		data.skrivRegister(this);
		System.exit(0);
	}
}
