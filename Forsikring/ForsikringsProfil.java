import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class ForsikringsProfil extends JFrame
{
    private Register register;
    private BilForsikring bil;
    private BåtForsikring båt;
    private HusForsikring hus;
    private HytteForsikring hytte;
    private MainListener lytter;
    private BorderLayout layout;
    private Container c;
    private Font font, font2, errorfont;
    private JButton rediger, lagre, aktiver;
    private JLabel navn, bilde, fnLab, navnLabel, idLabel, nrLabel,
    		tlfLabel, adrLabel, pnrLabel, pstedLabel, persnr, kunid, tlf, adr, pnr, psted;
    private String bilnr, kunr;
    private boolean eier;
    private JTextField fnavn, enavn, telf, adrr, ponr, pested;
    private JPanel editPanel, savePanel, aktiverPanel, knappePanel, bildePanel, profilen, info, navnPanel, flyt;

    public ForsikringsProfil(BilForsikring1 bilfors, boolean ok)
    {
        super("Bilforsikring");
		eier = ok;
        lytter = new MainListener();
        font = new Font("SansSerif", Font.BOLD, 20);
        font2 = new Font("SansSerif", Font.BOLD, 15);
        errorfont = new Font("SansSerif", Font.BOLD, 30);

        editPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        rediger = new JButton("Rediger");
        rediger.setFont(font2);
        rediger.addActionListener(lytter);
        rediger.setEnabled(true);
        editPanel.add(rediger);

        savePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        lagre = new JButton("Lagre");
        lagre.setFont(font2);
        lagre.addActionListener(lytter);
        lagre.setEnabled(false);
        savePanel.add(lagre);

        knappePanel = new JPanel( new BorderLayout());
        knappePanel.add(editPanel, BorderLayout.LINE_START);
        knappePanel.add(savePanel, BorderLayout.LINE_END);
        knappePanel.setBorder(BorderFactory.createLineBorder(Color.black));

        c = getContentPane();
        layout = new BorderLayout();
        c.setLayout(layout);
        c.setBackground(Color.white);

		//bilde = new JPanel(new ImageIcon( getClass().getResource("bilder/" + dyrenavn[dyrevelger.getSelectedIndex()] + ".gif")));
        bilde = new JLabel(new ImageIcon( getClass().getResource("bilder/bil.png")));
		bildePanel = new JPanel(new BorderLayout());
		bildePanel.add(bilde, BorderLayout.PAGE_START);
		bildePanel.setPreferredSize(new Dimension(130,130));
		bildePanel.setMaximumSize(new Dimension(130,130));

		kid = bilfors.getKundeNr();
        penr = bilfors.getPersonNr();
        navn1 = bilfors.getFornavn();
        navn2 = bilfors.getEtternavn();
		tlfnr = bilfors.getTelefonNr();
		adresse = bilfors.getAdresse();
		postnr = bilfors.getPostnr();
		poststed = bilfors.getPoststed();

		deaktivLabel = new JLabel("Kundeforholdet er opphevet");
		deaktivLabel.setFont(errorfont);
		deaktivLabel.setForeground(Color.red);
		idLabel = new JLabel("Kundenr");
		idLabel.setFont(font2);
		kunid = new JLabel(kid);
		kunid.setFont(font);
		nrLabel = new JLabel("Personnr");
		nrLabel.setFont(font2);
		persnr = new JLabel(penr);
		persnr.setFont(font);
		navnLabel = new JLabel("Navn");
		navnLabel.setFont(font2);
		navn = new JLabel(navn1 + " " + navn2);
		navn.setFont(font);
		tlfLabel = new JLabel("Telefon");
		tlfLabel.setFont(font2);
		tlf = new JLabel(tlfnr);
		tlf.setFont(font);
		adrLabel = new JLabel("Adresse");
		adrLabel.setFont(font2);
		adr = new JLabel(adresse);
		adr.setFont(font);
		pnrLabel = new JLabel("Postnr");
		pnrLabel.setFont(font2);
		pnr = new JLabel(postnr);
		pnr.setFont(font);
		pstedLabel = new JLabel("Poststed");
		pstedLabel.setFont(font2);
		psted = new JLabel(poststed);
		psted.setFont(font);

		fnavn = new JTextField(navn1);
		fnavn.setFont(font);
		enavn = new JTextField(navn2);
		enavn.setFont(font);
		telf = new JTextField(tlfnr);
		telf.setFont(font);
		adrr = new JTextField(adresse);
		adrr.setFont(font);
		ponr = new JTextField(postnr);
		ponr.setFont(font);
		pested = new JTextField(poststed);
		pested.setFont(font);

		navnPanel = new JPanel(new GridLayout(2,2));
		navnPanel.add(fnavn);
		navnPanel.add(enavn);

        info = new JPanel(new GridLayout(15,1));
        info.add(idLabel);
		info.add(kunid);
		info.add(nrLabel);
		info.add(persnr);
		info.add(navnLabel);
		info.add(navn);
		info.add(tlfLabel);
		info.add(tlf);
		info.add(adrLabel);
		info.add(adr);
		info.add(pnrLabel);
		info.add(pnr);
		info.add(pstedLabel);
		info.add(psted);
		info.setBorder(new EmptyBorder(new Insets(5, 20, 20, 5)));
		info.setBackground(Color.white);


        profilen = new JPanel(new BorderLayout(10,10));
        profilen.setPreferredSize(new Dimension(200, 550));
        profilen.setMaximumSize(new Dimension(200, 550));
        //profilen.add(bildePanel, BorderLayout.WEST);
        //profilen.add(endaPanel, BorderLayout.WEST);
        profilen.add(bildePanel, BorderLayout.PAGE_START);
        profilen.add(info, BorderLayout.CENTER);
        //profilen.setBackground(Color.white);

        //c.add(profilen, BorderLayout.CENTER);
        flyt = new JPanel( new FlowLayout(FlowLayout.CENTER));
        if(!kunde.getAktiv())
        {
			knappePanel.removeAll();
			knappePanel.add(aktiverPanel, BorderLayout.CENTER);
			flyt.add(deaktivLabel, BorderLayout.PAGE_START);
		}
        flyt.add(profilen);
        setLayout(new BorderLayout());
        c.add(knappePanel, BorderLayout.PAGE_START);
        //c.add(new JScrollPane(flyt), BorderLayout.CENTER);
		c.add(flyt, BorderLayout.CENTER);

        setDimensjon();
        setLocationRelativeTo(null);
        setVisible(true);

    }
    private void setDimensjon()
    {
        /*Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int høyde = screenSize.height;
        int bredde = screenSize.width;
        double hReduction = 0.40, vReduction = 0.30;
        setSize((int)(bredde*vReduction), (int)(høyde*hReduction) );*/
        int høyde = 650, bredde = 400;
        setSize(bredde, høyde);
    }

    /*public void byttLabels(JLabel l1, JLabel l2)
    {
		l1.setVisible(false);
		l2.setVisible(true);
	}*/

    public void redigerProfil()
    {
		rediger.setEnabled(false);
        lagre.setEnabled(true);
		info.removeAll();
		info.add(idLabel);
		info.add(kunid);
		info.add(nrLabel);
		info.add(persnr);
		info.add(navnLabel);
		info.add(fnavn);
		info.add(enavn);
		info.add(tlfLabel);
		info.add(telf);
		info.add(adrLabel);
		info.add(adrr);
		info.add(pnrLabel);
		info.add(ponr);
		info.add(pstedLabel);
		info.add(pested);
		info.revalidate();
		info.repaint();
    }

    public void lagreProfil()
    {
		kunde.setFornavn(fnavn.getText());
		kunde.setEtternavn(enavn.getText());
		kunde.setTelefonNr(tlf.getText());
		kunde.setAdresse(adrr.getText());
		kunde.setPostnr(ponr.getText());
		kunde.setPoststed(pested.getText());

		navn = new JLabel(kunde.getFornavn() + " " + kunde.getEtternavn());
		navn.setFont(font);
		tlf = new JLabel(kunde.getTelefonNr());
		tlf.setFont(font);
		adr = new JLabel(kunde.getAdresse());
		adr.setFont(font);
		pnr = new JLabel(kunde.getPostnr());
		pnr.setFont(font);
		psted = new JLabel(kunde.getPoststed());
		psted.setFont(font);

		lagre.setEnabled(false);
		rediger.setEnabled(true);
		info.removeAll();
		info.add(idLabel);
		info.add(kunid);
		info.add(nrLabel);
		info.add(persnr);
		info.add(navnLabel);
		info.add(navn);
		info.add(tlfLabel);
		info.add(tlf);
		info.add(adrLabel);
		info.add(adr);
		info.add(pnrLabel);
		info.add(pnr);
		info.add(pstedLabel);
		info.add(psted);
		info.revalidate();
		info.repaint();
	}

	public void aktiverBruker()
	{
		kunde.setAktiv(true);
		knappePanel.remove(aktiverPanel);
		knappePanel.add(editPanel, BorderLayout.LINE_START);
		knappePanel.add(savePanel, BorderLayout.LINE_END);
		knappePanel.revalidate();
		knappePanel.repaint();
		flyt.remove(deaktivLabel);
		flyt.revalidate();
		flyt.repaint();
	}

    private class MainListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            if(e.getSource() == rediger)
                redigerProfil();
            else if(e.getSource() == lagre)
                lagreProfil();
            else if(e.getSource() == aktiver)
            	aktiverBruker();
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
