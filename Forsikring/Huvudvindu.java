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
        knappLayoutPos.add(loggautPos, BorderLayout.LINE_START);

        container = getContentPane();
        layout = new BorderLayout();
        container.setLayout(layout);

        mittenPos = new Startvindu(this);
        panel = new PanelStack(mittenPos);

        container.add(mittenPos, BorderLayout.CENTER);
        container.add(knappLayoutPos, BorderLayout.PAGE_END);

        addWindowListener(wLytter);
        setDimensjon();
        setLocationRelativeTo(null);
        setVisible(true);

    }
    private void setDimensjon()
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

    public void loggaut()
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
    public void swapPanel(JPanel in)
    {
        panel.add(in);
        tilbake.setEnabled(panel.hasTilbake());
        container.remove(layout.getLayoutComponent(BorderLayout.CENTER));
        mittenPos = in;
        container.add(mittenPos, BorderLayout.CENTER);
        loggaut.setVisible(true);
    }

    public void tilbake()
    {
        swapPanel(panel.tilbake());
    }

    private class MainListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            if(e.getSource() == tilbake)
                tilbake();
            else if(e.getSource()== loggaut)
                loggaut();
        }
    }

    private class WinListener extends WindowAdapter
    {
        public void windowClosing(WindowEvent e)
        {
            register.exit();
        }
    }
}
