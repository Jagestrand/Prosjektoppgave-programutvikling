/*Skrevet av William Jagestrand, s , sist redigert 25.04.2015
Håndterer det å bytte mellom paneler i hovedvinduet og sørger for at knappene fungerer
*/

import javax.swing.*;
import java.util.*;

public class PanelStack
{
	private LinkedList<JPanel>tilbakePanel;
	private LinkedList<JPanel>framPanel;
	private ListIterator<JPanel>tilbakeIterator;
	private ListIterator<JPanel>framIterator;
	private JPanel aktuellPanel;
	private final int MAX_SIZE = 30;
	private boolean clear = true;

	public PanelStack(JPanel s)
	{
		tilbakePanel = new LinkedList<JPanel>();
		framPanel = new LinkedList<JPanel>();
		tilbakePanel.addFirst(s);
	}
	public void add(JPanel in)		//legger inn nytt panel i hovedvinduet
	{
		aktuellPanel = in;
		tilbakePanel.addFirst(in);
		tilbakeIterator = tilbakePanel.listIterator();
		tilbakeIterator.next();
		if(clear)
			framPanel.clear();
		clear = true;
		if(tilbakePanel.size()>MAX_SIZE)
			tilbakePanel.removeLast();
	}
	public void clear()					//klarerer panel
	{
		tilbakePanel.clear();
		framPanel.clear();
		tilbakeIterator = tilbakePanel.listIterator();
		framIterator = framPanel.listIterator();
	}

	public boolean hasTilbake()			//sjekker om det er mulig å gå tilbake til forrige panel
	{
		if(tilbakeIterator.hasNext())
			return true;
		return false;
	}
	public JPanel tilbake()			//går tilbake til forrige panel
	{
		tilbakeIterator.remove();
		JPanel res = tilbakeIterator.next();
		tilbakeIterator.remove();
		if(!framPanel.contains(aktuellPanel))
			framPanel.addFirst(aktuellPanel);
		framIterator = framPanel.listIterator();
		clear = false;
		return res;
	}
}
