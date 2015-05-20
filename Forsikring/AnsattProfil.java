/*Skrevet av Even Nerheim, s199184, sist redigert 19.05.15
Standardvindu for visning av ansattprofil, både for ansatte selv og for admin
*/

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class AnsattProfil extends JFrame
{
    private Register register;
    private Ansatt ansatt;
    private MainListener lytter;
    private BorderLayout layout;
    private Container c;
    private Font font, font2, errorfont;
    private JButton rediger, lagre, aktiver;
    private JLabel deaktivLabel, navn, bilde, navnLabel, idLabel, nrLabel,
    		tlfLabel, avdLabel, persnr, ansid, tlf, avd;
    private String aid, penr, navn1, navn2, tlfnr, avdeling;
    private JTextField fnavn, enavn, telf, avde;
    private JPanel editPanel, savePanel, aktiverPanel, knappePanel, bildePanel, profilen, info, navnPanel, flyt;

    public AnsattProfil(Ansatt anna)
    {
        super("Ansatt");
        ansatt = anna;
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

        aktiverPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        aktiver = new JButton("Reaktiver bruker");
        aktiver.setFont(font2);
        aktiver.addActionListener(lytter);
        aktiver.setEnabled(true);
        aktiverPanel.add(aktiver);

        knappePanel = new JPanel( new BorderLayout());
        knappePanel.add(editPanel, BorderLayout.LINE_START);
        knappePanel.add(savePanel, BorderLayout.LINE_END);
        knappePanel.setBorder(BorderFactory.createLineBorder(Color.black));

        c = getContentPane();
        layout = new BorderLayout();
        c.setLayout(layout);
        c.setBackground(Color.white);

		//bilde = new JPanel(new ImageIcon( getClass().getResource("bilder/" + dyrenavn[dyrevelger.getSelectedIndex()] + ".gif")));
        bilde = new JLabel(new ImageIcon( getClass().getResource("bilder/bilde.png")));
		bildePanel = new JPanel(new BorderLayout());
		bildePanel.add(bilde, BorderLayout.PAGE_START);
		bildePanel.setPreferredSize(new Dimension(130,130));
		bildePanel.setMaximumSize(new Dimension(130,130));


		aid = ansatt.getAnsattNr();
        penr = ansatt.getPersonNr();
        navn1 = ansatt.getFornavn();
        navn2 = ansatt.getEtternavn();
		tlfnr = ansatt.getTelefonNr();
		avdeling = ansatt.getAvdeling();

		deaktivLabel = new JLabel("Brukeren er deaktivert");
		deaktivLabel.setFont(errorfont);
		deaktivLabel.setForeground(Color.red);
		idLabel = new JLabel("Ansattnr");
		idLabel.setFont(font2);
		ansid = new JLabel(aid);
		ansid.setFont(font);
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
		avdLabel = new JLabel("Avdeling");
		avdLabel.setFont(font2);
		avd = new JLabel(avdeling);
		avd.setFont(font);

		fnavn = new JTextField(navn1);
		fnavn.setFont(font);
		enavn = new JTextField(navn2);
		enavn.setFont(font);
		telf = new JTextField(tlfnr);
		telf.setFont(font);
		avde = new JTextField(avdeling);
		avde.setFont(font);

		navnPanel = new JPanel(new GridLayout(2,2));
		navnPanel.add(fnavn);
		navnPanel.add(enavn);

        info = new JPanel(new GridLayout(11,1));
        info.add(idLabel);
		info.add(ansid);
		info.add(nrLabel);
		info.add(persnr);
		info.add(navnLabel);
		info.add(navn);
		info.add(tlfLabel);
		info.add(tlf);
		info.add(avdLabel);
		info.add(avd);
		info.setBorder(new EmptyBorder(new Insets(5, 20, 20, 5)));
		info.setBackground(Color.white);


        profilen = new JPanel(new BorderLayout(10,10));
        profilen.setPreferredSize(new Dimension(200, 500));
        profilen.setMaximumSize(new Dimension(200, 500));
        profilen.add(bildePanel, BorderLayout.PAGE_START);
        profilen.add(info, BorderLayout.CENTER);

        flyt = new JPanel( new FlowLayout(FlowLayout.CENTER));
        if(!ansatt.getAktiv())
        {
			knappePanel.removeAll();
			knappePanel.add(aktiverPanel, BorderLayout.CENTER);
			flyt.add(deaktivLabel, BorderLayout.PAGE_START);
		}
		flyt.add(profilen);
        setLayout(new BorderLayout());
        c.add(knappePanel, BorderLayout.PAGE_START);
		c.add(flyt, BorderLayout.CENTER);

        setDimensjon();
        setLocationRelativeTo(null);
        setVisible(true);

    }
    private void setDimensjon()
    {
        int bredde = 400, høyde = 650;
        setSize(bredde, høyde);
    }

    public void redigerProfil()
    {
		rediger.setEnabled(false);
        lagre.setEnabled(true);
		info.removeAll();
		info.add(idLabel);
		info.add(ansid);
		info.add(nrLabel);
		info.add(persnr);
		info.add(navnLabel);
		info.add(fnavn);
		info.add(enavn);
		info.add(tlfLabel);
		info.add(telf);
		info.add(avdLabel);
		info.add(avde);
		info.revalidate();
		info.repaint();
    }

    public void lagreProfil()
    {
		ansatt.setFornavn(fnavn.getText());
		ansatt.setEtternavn(enavn.getText());
		ansatt.setTelefonNr(telf.getText());
		ansatt.setAvdeling(avde.getText());

		navn = new JLabel(ansatt.getFornavn() + " " + ansatt.getEtternavn());
		navn.setFont(font);
		tlf = new JLabel(ansatt.getTelefonNr());
		tlf.setFont(font);
		avd = new JLabel(ansatt.getAvdeling());
		avd.setFont(font);

		lagre.setEnabled(false);
		rediger.setEnabled(true);
		info.removeAll();
		info.add(idLabel);
		info.add(ansid);
		info.add(nrLabel);
		info.add(persnr);
		info.add(navnLabel);
		info.add(navn);
		info.add(tlfLabel);
		info.add(tlf);
		info.add(avdLabel);
		info.add(avd);
		info.revalidate();
		info.repaint();
	}

	public void aktiverBruker()
	{
		String melding = "Vil du reaktivere denne brukeren?";
		String tittel = "Validering";
		int svar = JOptionPane.showConfirmDialog(null, melding, tittel, JOptionPane.YES_NO_OPTION);
		if (svar == JOptionPane.YES_OPTION)
		{
			ansatt.setAktiv(true);
			knappePanel.remove(aktiverPanel);
			knappePanel.add(editPanel, BorderLayout.LINE_START);
			knappePanel.add(savePanel, BorderLayout.LINE_END);
			knappePanel.revalidate();
			knappePanel.repaint();
			flyt.remove(deaktivLabel);
			flyt.revalidate();
			flyt.repaint();
		}
	}

    private class MainListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            if(e.getSource() == rediger)
                redigerProfil();
            else if(e.getSource()== lagre)
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
