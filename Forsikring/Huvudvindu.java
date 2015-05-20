/*Skrevet av William Jagestrand, s , sist redigert 10.05.2015
Klassen fungerer som bakgrunnsvinduet for alle GUIene med standardknapper
på bunnen av skjermen
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Huvudvindu extends JFrame
{
    private Register register;
    private MainListener lytter;
    private WinListener wLytter;
    private BorderLayout layout;
    private PanelStack panel;
    private Container container;
    private JButton tilbake, loggaut;
    private JPanel tilbakePos, loggautPos, knappLayoutPos, mittenPos;

    public Huvudvindu(Register reg)
    {
        super("Forsikring");
       	register = reg;
        wLytter = new WinListener();
        lytter = new MainListener();

		//legger inn knapper
        tilbakePos = new JPanel(new FlowLayout(FlowLayout.LEFT));
        tilbake = new JButton("Tilbake");
        tilbake.addActionListener(lytter);
        tilbake.setEnabled(false);
        tilbakePos.add(tilbake);

        loggautPos = new JPanel(new FlowLayout(FlowLayout.LEFT));
        loggaut = new JButton("Logga ut");
        loggaut.addActionListener(lytter);
        loggaut.setVisible(false);
        loggautPos.add(loggaut);

        knappLayoutPos = new JPanel( new BorderLayout());
        knappLayoutPos.add(tilbakePos, BorderLayout.LINE_START);
        knappLayoutPos.add(loggautPos, BorderLayout.LINE_END); //LINE_START

        container = getContentPane();
        layout = new BorderLayout();
        container.setLayout(layout);

		//Startvindu er første vinduet som blir hentet opp og tar opp nesten hele vinduet
        mittenPos = new Startvindu(this);
        panel = new PanelStack(mittenPos);

        container.add(mittenPos, BorderLayout.CENTER);
        container.add(knappLayoutPos, BorderLayout.PAGE_END);

        addWindowListener(wLytter);
        setDimensjon();
        setLocationRelativeTo(null);
        setVisible(true);

    }
    private void setDimensjon()					//setter størrelse for bakgrunnsvinduet
    {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int høyde = screenSize.height;
        int bredde = screenSize.width;
        double hReduction = 0.85, vReduction = 0.85;
        setSize((int)(bredde*vReduction), (int)(høyde*hReduction) );
    }

    private void setSizeByPanel()
    {
		setSize(mittenPos.getPreferredSize() );
	}

    public void loggaut()			//logger ut brukeren
    {
        panel.clear();
        swapPanel(new Startvindu(this) );
        loggaut.setVisible(false);
    }
    public Register getRegister()
    {
        return register;
    }
    public PanelStack getPanelStack()
    {
        return panel;
    }
    public void swapPanel(JPanel in)		//bytter heldekkende panel uten å bytte vindu
    {
        panel.add(in);
        tilbake.setEnabled(panel.hasTilbake());
        container.remove(layout.getLayoutComponent(BorderLayout.CENTER));
        mittenPos = in;
        container.add(mittenPos, BorderLayout.CENTER);
        loggaut.setVisible(true);
        validate();
        repaint();
    }

    public void tilbake()		//går tilbake til forrige side brukeren var på
    {
        swapPanel(panel.tilbake());
    }

    private class MainListener implements ActionListener		//lytter til når knappene brukes
    {
        public void actionPerformed(ActionEvent e)
        {
            if(e.getSource() == tilbake)
                tilbake();
            else if(e.getSource()== loggaut)
                loggaut();
        }
    }

    private class WinListener extends WindowAdapter		//når programmet lukkes lagres ale
    {
        public void windowClosing(WindowEvent e)
        {
            register.exit();
        }
    }
}
