import javax.swing.*;
import javax.swing.border.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;

public class MineForsikringer extends JPanel
{

	private static final long serialVersionUID = 42L;
	private JPanel forsikringListe;
	private BilForsikringsReg bil;
	private BåtForsikringsReg båt;
	private HusForsikringsReg hus;
	private HytteForsikringsReg hytte;

	public MineForsikringer(BilForsikringsReg bi, BåtForsikringsReg bå, HusForsikringsReg hu, HytteForsikringsReg hy)
	{
		if(!(bi == null))
			bil = bi;
		if(!(bå == null))
			båt = bå;
		if(!(hu == null))
			hus = hu;
		if(!(hy == null))
			hytte = hy;

		setLayout(new BorderLayout());

    	forsikringListe = new JPanel(new GridBagLayout());
    	GridBagConstraints minF = new GridBagConstraints();
    	minF.gridwidth = GridBagConstraints.REMAINDER;
    	minF.weightx = 1;
    	minF.weighty = 1;
    	forsikringListe.add(new JPanel(), minF);

        add(new JScrollPane(forsikringListe));
     // , JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER)
        int størrelse = bil.size() + båt.size() + hus.size() + hytte.size();
        
        Iterator<BilForsikring> iter = bil.iterator();
        BilForsikring temp1;
        
        Iterator<BåtForsikring> iter2 = båt.iterator();
        BåtForsikring temp2;
        
        Iterator<HusForsikring> iter3 = hus.iterator();
        HusForsikring temp3;

        Iterator<HytteForsikring> iter4 = hytte.iterator();
        HytteForsikring temp4;
        
        

			for(int j = 0; j < bil.size(); j++)
			{
				temp1 = iter.next();
				JPanel panel = new JPanel();
        		panel.add(new JLabel(temp1.toString1()));
        		panel.setBorder(new MatteBorder(0, 0, 1, 0, Color.RED));
        		GridBagConstraints minK = new GridBagConstraints();
        		minK.gridwidth = GridBagConstraints.REMAINDER;
        		minK.weightx = 1;
        		minK.fill = GridBagConstraints.HORIZONTAL;
        		forsikringListe.add(panel, minK, 0);

         		validate();
         		repaint();
			}
			for(int j = 0; j < båt.size(); j++)
			{
				temp2 = iter2.next();
				JPanel panel = new JPanel();
        		panel.add(new JLabel(temp2.toString2()));
        		panel.setBorder(new MatteBorder(0, 0, 1, 0, Color.RED));
        		GridBagConstraints minK = new GridBagConstraints();
        		minK.gridwidth = GridBagConstraints.REMAINDER;
        		minK.weightx = 1;
        		minK.fill = GridBagConstraints.HORIZONTAL;
        		forsikringListe.add(panel, minK, 0);

         		validate();
         		repaint();
			}
			for(int j = 0; j < hus.size(); j++)
			{
				temp3 = iter3.next();
				JPanel panel = new JPanel();
				panel.add(new JLabel(temp3.toString3()));
        		panel.setBorder(new MatteBorder(0, 0, 1, 0, Color.RED));
        		GridBagConstraints minK = new GridBagConstraints();
        		minK.gridwidth = GridBagConstraints.REMAINDER;
        		minK.weightx = 1;
        		minK.fill = GridBagConstraints.HORIZONTAL;
        		forsikringListe.add(panel, minK, 0);

         		validate();
         		repaint();
			}
			for(int j = 0; j < hytte.size(); j++)
			{
				temp4 = iter4.next();
				JPanel panel = new JPanel();
				panel.add(new JLabel(temp4.toString4()));
        		panel.setBorder(new MatteBorder(0, 0, 1, 0, Color.RED));
        		GridBagConstraints minK = new GridBagConstraints();
        		minK.gridwidth = GridBagConstraints.REMAINDER;
        		minK.weightx = 1;
        		minK.fill = GridBagConstraints.HORIZONTAL;
        		forsikringListe.add(panel, minK, 0);

         		validate();
         		repaint();
			}

		
     }

     @Override
     public Dimension getPreferredSize()
     {
         return new Dimension(400, 400);
     }
}
