/*
Skrevet av Even Nerheim, s199184, sist redigert 19.05.2015
Sorterer personer etter navn og sjekker at ingen blir lagret to ganger
*/

import java.util.*;
import java.io.Serializable;
import java.text.*;

import javax.swing.JOptionPane;

class PersonCollator implements Comparator<Person>, Serializable
{
	private static final long serialVersionUID = 42L;

	private String regex = "<\0<0<1<2<3<4<5<6<7<8<9" +
                  "<A,a<B,b<C,c<D,d<E,e<F,f<G,g<H,h<I,i<J,j" +
                 "<K,k<L,l<M,m<N,n<O,o<P,p<Q,q<R,r<S,s<T,t" +
                 "<U,u<V,v<W,w<X,x<Y,y<Z,z<Æ,æ<Ø,ø<Å=AA,å=aa;AA,aa";

	private transient RuleBasedCollator collator;

	  public PersonCollator(){
	    try{
	      collator = new RuleBasedCollator(regex);
	    }
	    catch (ParseException pe){
	    	JOptionPane.showMessageDialog(null, "En feil ved oppretting av kollator har oppstått.", "FEIL", JOptionPane.ERROR_MESSAGE);
	    }
	  }

	  public int compare(Person person1, Person person2){

		  	if(collator == null){ // Oppretter ny kollator dersom mangler etter lesing av fil.
				try {
					collator = new RuleBasedCollator(regex);
				} catch (ParseException e) {
					JOptionPane.showMessageDialog(null, "En feil har oppstått ved oppretting av kollator. Dette er en kritisk feil.\n" +
										"Lukk programmet og slett data mappe for ditt eget beste.", "FEIL", JOptionPane.ERROR_MESSAGE);
				}
		  	}

		    String først = person1.getFornavn() + " " + person1.getFornavn();
		    String andre = person2.getEtternavn() + " " + person2.getEtternavn();
		    int sammenlignNavn = collator.compare( først, andre );
		    først = person1.getPersonNr();
		    andre = person2.getPersonNr();
		    int sammenlignPersonNr = collator.compare(først, andre);
		    if(sammenlignPersonNr == 0){
		    	return sammenlignPersonNr; // Returnerer 0 for å ikke legge inn person dersom personnummer er likt.
		    }
		    if(sammenlignNavn == 0)
		    	sammenlignNavn++;
		    return sammenlignNavn;
		 }

} // End of class
