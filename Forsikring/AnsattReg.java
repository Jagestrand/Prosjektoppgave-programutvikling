import java.util.*;
import java.io.Serializable;

import javax.swing.JOptionPane;

public class AnsattReg implements Serializable
{
	private static final long serialVersionUID = 42L;
	private TreeSet<Ansatt> docList;
	private int nrNå;
	private Ansatt første;

	public AnsattReg()
	{
		docList = new TreeSet<Ansatt>(InitCollator());
	}

	public void add(Ansatt watson)
	{
		docList.add(watson);
	}

	public void remove(Ansatt watson)
	{
		docList.remove(watson);
	}

	public boolean contains(Ansatt doc)
	{
		return docList.contains(doc);
	}

	public boolean isEmpty()
	{
		return docList.isEmpty();
	}

	public int size()
	{
		return docList.size();
	}

	public Iterator<Ansatt> iterator()
	{
		return docList.iterator();
	}

	public AnsattReg findDoctors(String criteria)
	{
		AnsattReg searchedDocList = new AnsattReg();
		searchedDocList = findDoctorByPersonNr(criteria);
		if(searchedDocList == null)
		{
			searchedDocList = findDoctorByName(criteria);
			if(searchedDocList == null)
			{
				searchedDocList = findDoctorByTelephone(criteria);
				if(searchedDocList == null)
				{
					searchedDocList = findDoctorByAvdeling(criteria);
					if(searchedDocList == null)
					{
						return null;
					}
				}
			}
		}
		return searchedDocList;
	}

	public AnsattReg findDoctorByPersonNr(String personNr)
	{
		Iterator<Ansatt> theIterator = iterator();
		Ansatt doc;
		AnsattReg searchedDocReg = new AnsattReg();
		try{
			while(theIterator.hasNext())
			{
				doc = theIterator.next();
				if(doc.getPersonNr().matches(personNr))
				{
					searchedDocReg.add(doc);
					return searchedDocReg;
				}
			}
		}
		catch(NoSuchElementException nsee)
		{
			JOptionPane.showMessageDialog(null, "Feil i AnsattReg (findDoctorByPersonNr): No Such Element Exception.",
											"FEIL", JOptionPane.ERROR_MESSAGE);
		}
		/*catch(NullPointerException npe)
		{
			JOptionPane.showMessageDialog(null, "Det skjedde en NullPointerException i AnsattReg findDoctorByPersonNr.", "FEIL", JOptionPane.ERROR_MESSAGE);
		}*/
		return null;
	}

	public AnsattReg findDoctorByName(String name)
	{
		Iterator<Ansatt> theIterator = iterator();
		Ansatt doc;
		AnsattReg searchedDocReg = new AnsattReg();
		try{
			while(theIterator.hasNext())
			{
				doc = theIterator.next();
				if(doc.getFornavn().equals(name) || doc.getEtternavn().equals(name))
				{
					searchedDocReg.add(doc);
				}
			}
			return searchedDocReg;
		}
		catch(NoSuchElementException nsee){
			JOptionPane.showMessageDialog(null, "Feil i DoctorReg (findDoctorByName): No Such Element Exception.",
					"FEIL", JOptionPane.ERROR_MESSAGE);
		}
		return null;
	}

	public AnsattReg findDoctorByTelephone(String telephone)
	{
		Iterator<Ansatt> theIterator = iterator();
		Ansatt doc;
		AnsattReg searchedDocReg = new AnsattReg();
		try{
			while(theIterator.hasNext())
			{
				doc = theIterator.next();
				if(doc.getTelefonNr().matches(telephone))
				{
					searchedDocReg.add(doc);
				}
			}
			return searchedDocReg;
		}
		catch(NoSuchElementException nsee){
			JOptionPane.showMessageDialog(null, "Feil i AnsattReg (findDoctorByTelephone): No Such Element Exception.",
					"FEIL", JOptionPane.ERROR_MESSAGE);
		}
		return null;
	}

	public AnsattReg findDoctorByAvdeling(String avdeling)
	{
		Iterator<Ansatt> theIterator = iterator();
		Ansatt doc;
		AnsattReg searchedDocReg = new AnsattReg();
		try{
			while(theIterator.hasNext())
			{
				doc = theIterator.next();
				if(doc.getAvdeling().matches(avdeling))
				{
					searchedDocReg.add(doc);
				}
			}
			return searchedDocReg;
		}
		catch(NoSuchElementException nsee){
			JOptionPane.showMessageDialog(null, "Feil i AnsattReg (findDoctorByAvdeling): No Such Element Exception.",
					"FEIL", JOptionPane.ERROR_MESSAGE);
		}
		return null;
	}

	public AnsattReg findDoctorByAnsattNr(String ansattnr)
	{
		Iterator<Ansatt> theIterator = iterator();
		Ansatt doc;
		AnsattReg searchedDocReg = new AnsattReg();
		try{
			while(theIterator.hasNext())
			{
				doc = theIterator.next();
				if(doc.getAnsattNr().matches(ansattnr))
				{
					searchedDocReg.add(doc);
				}
			}
			return searchedDocReg;
		}
		catch(NoSuchElementException nsee){
			JOptionPane.showMessageDialog(null, "Feil i AnsattReg (findDoctorByAnsattNr): No Such Element Exception.",
					"FEIL", JOptionPane.ERROR_MESSAGE);
		}
		return null;
	}


	/*public AnsattReg findDoctorByAnsattNr(int ansattnr)
	{
		Iterator<Ansatt> theIterator = iterator();
		Ansatt doc;
		AnsattReg searchedDocReg = new AnsattReg();
		try{
			while(theIterator.hasNext())
			{
				doc = theIterator.next();
				if(doc.getAnsattNr() == ansattnr)
				{
					searchedDocReg.add(doc);
				}
			}
			return searchedDocReg;
		}
		catch(NoSuchElementException nsee){
			JOptionPane.showMessageDialog(null, "Feil i AnsattReg (findDoctorByTelephone): No Such Element Exception.",
					"FEIL", JOptionPane.ERROR_MESSAGE);
		}
		return null;
	}*/

	public Comparator<Person> InitCollator()
	{ // Initialiserer kollator
		Comparator<Person> collator;
		return collator = new PersonCollator();
	}

	public void lagreNrNå()
	{
		nrNå = Ansatt.getNrNå();
	}

	public void setNåNr()
	{
		Ansatt.setNrNå(nrNå);
	}

	public String toString()
	{
		StringBuilder info = new StringBuilder();
		Iterator<Ansatt> iter = iterator();
		while(iter.hasNext() )
		{
			info.append(iter.next().toString() + "\n-----------------------------------\n\n");
		}
		return info.toString();
	}
} // End of class
