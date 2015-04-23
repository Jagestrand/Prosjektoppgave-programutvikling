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
	public void add(JPanel in)
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
	public void clear()
	{
		tilbakePanel.clear();
		framPanel.clear();
		tilbakeIterator = tilbakePanel.listIterator();
		framIterator = framPanel.listIterator();
	}
// Rader hasTilbake senare i huvudvindu och här. kollar om det är möjligt att navigera framöver
	public boolean hasTilbake()
	{
		if(tilbakeIterator.hasNext())
			return true;
		return false; 
	}
	public JPanel tilbake()
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
	public JPanel fram()
	{
		clear = false;
		JPanel res = framIterator.next();
		return res;
	}




}
