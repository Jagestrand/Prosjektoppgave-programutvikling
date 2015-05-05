import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AnsattProfil extends JFrame
{
    private Register register;
    private Ansatt ansatt;
    private MainListener lytter;
    //private WinListener wLytter;
    private BorderLayout layout;
    //private PanelStack panel;
    private Container c;
    private JButton rediger, lagre;
    private JLabel bilde, navnLabel, fnavn, enavn, persnr, pnrLabel, tlf, tlfLabel, avd, avdLabel, ansid, idLabel;
    private String navn1, navn2, tlfnr, avdeling;
    private JTextField fonavn, etnavn, telf, avde;
    private JPanel editPanel, savePanel, knappePanel, bildePanel, profilen, info, navnPanel, tlfPanel, avdPanel;

    public AnsattProfil(Ansatt anna)
    {
        super("Ansatt");
        ansatt = anna;
        lytter = new MainListener();
        Font font = new Font("SansSerif", Font.BOLD, 15);

        editPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        rediger = new JButton("Rediger");
        rediger.setFont(font);
        rediger.addActionListener(lytter);
        rediger.setEnabled(true);
        editPanel.add(rediger);

        savePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        lagre = new JButton("Lagre");
        lagre.setFont(font);
        lagre.addActionListener(lytter);
        lagre.setEnabled(false);
        savePanel.add(lagre);

        knappePanel = new JPanel( new BorderLayout());
        knappePanel.add(editPanel, BorderLayout.LINE_START);
        knappePanel.add(savePanel, BorderLayout.LINE_END);

        c = getContentPane();
        layout = new BorderLayout();
        c.setLayout(layout);
        c.setBackground(Color.white);

		//bilde = new JPanel(new ImageIcon( getClass().getResource("bilder/" + dyrenavn[dyrevelger.getSelectedIndex()] + ".gif")));
        bilde = new JLabel(new ImageIcon( getClass().getResource("bilder/kong.jpg")));
		bildePanel = new JPanel(new BorderLayout(20,20));
		bildePanel.add(bilde);
		bildePanel.setPreferredSize(new Dimension(230,150));
		bildePanel.setMaximumSize(new Dimension(230,150));

        ansid = new JLabel(ansatt.getAnsattNr());
        ansid.setFont(font);
        navn1 = ansatt.getFornavn();
        navn2 = ansatt.getEtternavn();
        fnavn = new JLabel(navn1);
        fnavn.setFont(font);
        enavn = new JLabel(navn2);
		enavn.setFont(font);
		persnr = new JLabel(ansatt.getPersonNr());
		persnr.setFont(font);
		tlfnr = ansatt.getTelefonNr();
		tlf = new JLabel(tlfnr);
		tlf.setFont(font);
		avdeling = ansatt.getAvdeling();
		avd = new JLabel(avdeling);
		avd.setFont(font);

		fonavn = new JTextField(navn1);
		fonavn.setFont(font);
		fonavn.setVisible(false);
		etnavn = new JTextField(navn2);
		etnavn.setFont(font);
		etnavn.setVisible(false);
		telf = new JTextField(tlfnr);
		telf.setFont(font);
		telf.setVisible(false);
		avde = new JTextField(avdeling);
		avde.setFont(font);
		avde.setVisible(false);

		navnPanel = new JPanel(new GridLayout(2,2));
		navnPanel.add(fnavn);
		navnPanel.add(enavn);
		navnPanel.add(fonavn);
		navnPanel.add(etnavn);

		tlfPanel = new JPanel(new GridLayout(1,1));
		tlfPanel.add(tlf);
		tlfPanel.add(telf);

		avdPanel = new JPanel(new GridLayout(1,1));
		avdPanel.add(avd);
		avdPanel.add(avde);

        info = new JPanel(new GridLayout(5,1));
		info.add(ansid);
		info.add(persnr);
		info.add(navnPanel);
		info.add(tlfPanel);
		info.add(avdPanel);

        profilen = new JPanel(new FlowLayout());
        profilen.add(bildePanel, BorderLayout.PAGE_START);
        profilen.add(info, BorderLayout.CENTER);
        profilen.setBackground(Color.white);

        //c.add(mittenPos, BorderLayout.CENTER);
        c.add(knappePanel, BorderLayout.PAGE_START);
        c.add(profilen, BorderLayout.LINE_START);
        //c.add(bildePanel, BorderLayout.LINE_START);
        //c.add(info, BorderLayout.CENTER);

        //addWindowListener(wLytter);
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
        double hReduction = 0.40, vReduction = 0.30;
        setSize((int)(bredde*vReduction), (int)(høyde*hReduction) );
    }

    public void redigerProfil()
    {
		rediger.setEnabled(false);
        lagre.setEnabled(true);
		fnavn.setVisible(true);
		enavn.setVisible(true);
		fonavn.setVisible(false);
		etnavn.setVisible(false);
		tlf.setVisible(false);
		telf.setVisible(true);
		avd.setVisible(false);
		avde.setVisible(true);
    }

    public void lagreProfil()
    {
		ansatt.setFornavn(fonavn.getText());
		ansatt.setEtternavn(etnavn.getText());
		ansatt.setTelefonNr(telf.getText());
		ansatt.setAvdeling(avde.getText());

		lagre.setEnabled(false);
		rediger.setEnabled(true);
		fnavn.setVisible(true);
		enavn.setVisible(true);
		fonavn.setVisible(false);
		etnavn.setVisible(false);
		tlf.setVisible(true);
		telf.setVisible(false);
		avd.setVisible(true);
		avde.setVisible(false);
	}

    private class MainListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            if(e.getSource() == rediger)
                redigerProfil();
            else if(e.getSource()== lagre)
                lagreProfil();
        }
    }

    /*private class WinListener extends WindowAdapter
    {
        public void windowClosing(WindowEvent e)
        {
            register.exit();
        }
    }*/
}
