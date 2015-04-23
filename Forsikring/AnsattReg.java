import java.util.*;
import java.io.Serializable;

import javax.swing.JOptionPane;

public class AnsattReg implements Serializable {
	private static final long serialVersionUID = 42L;
	private TreeSet<Ansatt> ansListe;

	public AnsattReg(){
		ansListe = new TreeSet<Ansatt>(InitCollator());
	}

	public void add(Ansatt watson) {
		ansListe.add(watson);
	}

	public void remove(Ansatt watson) {
		ansListe.remove(watson);
	}

	public boolean contains(Ansatt doc){
		return ansListe.contains(doc);
	}

	public boolean isEmpty(){
		return ansListe.isEmpty();
	}

	public int size(){
		return ansListe.size();
	}

	public Iterator<Ansatt> iterator(){
		return ansListe.iterator();
	}

	public AnsattReg finnAnsatte(String kriterie){
		AnsattReg søktAnsListe = new AnsattReg();
		søktAnsListe = finnAnsattViaPersNr(kriterie);
		if(søktAnsListe == null){
			søktAnsListe = finnAnsattViaNavn(kriterie);
			if(søktAnsListe == null){
				søktAnsListe = finnAnsattViaTelefon(kriterie);
				if(søktAnsListe == null){
					return null;
				}
			}
		}
		return søktAnsListe;
	}

	public AnsattReg finnAnsattViaPersNr(String personNr) {
		Iterator<Ansatt> theIterator = iterator();
		Ansatt ans;
		AnsattReg søktAnsReg = new AnsattReg();
		try{
			while(theIterator.hasNext()) {
				ans = theIterator.next();
				if(ans.getPersonNr().matches(personNr)) {
					søktAnsReg.add(ans);
					return søktAnsReg;
				}
			}
		}
		catch(NoSuchElementException nsee){
			JOptionPane.showMessageDialog(null, "Feil i AnsattReg (finnAnsattViaPersonNr): No Such Element Exception.",
											"FEIL", JOptionPane.ERROR_MESSAGE);
		}
		return null;
	}

	public AnsattReg finnAnsattViaNavn(String navn) {
		Iterator<Ansatt> theIterator = iterator();
		Ansatt ans;
		AnsattReg søktAnsReg = new AnsattReg();
		try{
			while(theIterator.hasNext()) {
				ans = theIterator.next();
				if(ans.getFornavn().equals(navn) || ans.getEtternavn().equals(navn)) {
					søktAnsReg.add(ans);
				}
			}
			return søktAnsReg;
		}
		catch(NoSuchElementException nsee){
			JOptionPane.showMessageDialog(null, "Feil i AnsattReg (finnAnsattViaNavn): No Such Element Exception.",
					"FEIL", JOptionPane.ERROR_MESSAGE);
		}
		return null;
	}

	public AnsattReg finnAnsattViaTelefon(String tlf) {
		Iterator<Ansatt> theIterator = iterator();
		Ansatt ans;
		AnsattReg søktAnsReg = new AnsattReg();
		try{
			while(theIterator.hasNext()) {
				ans = theIterator.next();
				if(ans.getTelefonNr().matches(tlf)) {
					søktAnsReg.add(ans);
				}
			}
			return søktAnsReg;
		}
		catch(NoSuchElementException nsee){
			JOptionPane.showMessageDialog(null, "Feil i AnsattReg (finnAnsattViaTelefon): No Such Element Exception.",
					"FEIL", JOptionPane.ERROR_MESSAGE);
		}
		return null;
	}
	/*
	public DoctorReg findDoctorByLicense(int group) {
		DoctorReg listofDoctors = new DoctorReg();
		Doctor doc;
		Iterator<Doctor> theIterator = iterator();

		if(group == Prescription.GROUP_A) {
			while(theIterator.hasNext()) {
				doc = theIterator.next();
				if(doc.getLicenseA()) {
					listofDoctors.add(doc);
				}
			}
		}

		else if(group == Prescription.GROUP_B) {
			while(theIterator.hasNext()) {
				doc = theIterator.next();
				if(doc.getLicenseB()) {
					listofDoctors.add(doc);
				}
			}
		}

		else if(group == Prescription.GROUP_C) {
			while(theIterator.hasNext()) {
				doc = theIterator.next();
				if(doc.getLicenseC()) {
					listofDoctors.add(doc);
				}
			}
		}
		return listofDoctors;
	}*/

	public Comparator<Person> InitCollator(){ // Initialiserer kollator
		Comparator<Person> collator;
		return collator = new PersonCollator();
	}

	public String toString() {
		StringBuilder info = new StringBuilder();
		Iterator<Ansatt> iter = iterator();
		while(iter.hasNext() )
		{
			info.append(iter.next().toString() + "\n-----------------------------------\n\n");
		}
		return info.toString();
	}
} // End of class
