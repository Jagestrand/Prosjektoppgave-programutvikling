/*Skrevet av Even Nerheim, s199184, sist rediger 19.05.2015
Et vindu for informasjon om enkeltkunden som både kunden, ansatt og admin kan se og redigere på
*/

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class KundeProfil extends JFrame
{
    private Register register;
    private Kunde kunde;
    private MainListener lytter;
    private BorderLayout layout;
    private Container c;
    private Font font, font2, errorfont;
    private JButton rediger, lagre, aktiver;
    private JLabel deaktivLabel, navn, bilde, fnLab, navnLabel, idLabel, nrLabel,
    		tlfLabel, adrLabel, pnrLabel, pstedLabel, persnr, kunid, tlf, adr, pnr, psted;
    private String kid, penr, navn1, navn2, tlfnr, adresse, postnr, poststed;
    private JTextField fnavn, enavn, telf, adrr, ponr, pested;
    private JPanel editPanel, savePanel, aktiverPanel, knappePanel, bildePanel, profilen, info, navnPanel, flyt;

    public KundeProfil(Kunde kunne)
    {
        super("Kunde");
        kunde = kunne;
        lytter = new MainListener();
        font = new Font("SansSerif", Font.BOLD, 20);
        font2 = new Font("SansSerif", Font.BOLD, 15);
        errorfont = new Font("SansSerif", Font.BOLD, 30);

		//lite knappepanel på toppen
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

		//selve profilen:
        bilde = new JLabel(new ImageIcon( getClass().getResource("bilder/bilde.png")));
		bildePanel = new JPanel(new BorderLayout());
		bildePanel.add(bilde, BorderLayout.PAGE_START);
		bildePanel.setPreferredSize(new Dimension(130,130));
		bildePanel.setMaximumSize(new Dimension(130,130));

		kid = kunde.getKundeNr();
        penr = kunde.getPersonNr();
        navn1 = kunde.getFornavn();
        navn2 = kunde.getEtternavn();
		tlfnr = kunde.getTelefonNr();
		adresse = kunde.getAdresse();
		postnr = kunde.getPostnr();
		poststed = kunde.getPoststed();

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
        profilen.add(bildePanel, BorderLayout.PAGE_START);
        profilen.add(info, BorderLayout.CENTER);

        flyt = new JPanel( new FlowLayout(FlowLayout.CENTER));

        if(!kunde.getAktiv())			//hvis kunden ikke er kunde lenger vil det stå på toppen av profilen
        {								// og profilen kan ikke lenger redigeres
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
    private void setDimensjon()					//setter størrelse på vinduet
    {
        int høyde = 650, bredde = 400;
        setSize(bredde, høyde);
    }

    public void redigerProfil()					//setter inn textfield for å redigere informajson
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

    public void lagreProfil()			//lagrer informasjonen skrevet i feltene og henter de fram
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

	public void aktiverBruker()					//metode for å reaktivere en bruker
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

    private class MainListener implements ActionListener		//lytter til bruk av knapper
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

    private class WinListener extends WindowAdapter		//lagrer data når vinduet lukkes
    {
        public void windowClosing(WindowEvent e)
        {
            register.exit();
        }
    }
}
