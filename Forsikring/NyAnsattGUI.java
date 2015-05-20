/*Skrevet av Even Nerhei, s199184, sist redigert 11.05.2015
Vindu for å registrere nye ansatte i bedriften og dermed også nye brukere.
Kun admin får adgang til denne siden
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;

public class NyAnsattGUI extends JPanel
{
	private final int DATA_FIELD_LENGTH = 20;
	private boolean a = false, b = false, c = false;
	private Huvudvindu win;
	private Register reg;
	private JTextField fNavn, eNavn, pNr, pass1, pass2, tlf, ansAt;
	private JTextArea info;
	private JLabel fNavnLabel, eNavnLabel, pNrLabel, pass1Label, pass2Label, tlfLabel, ansAtLabel;
	private JButton addAns;
	private Lytterklasse lytter;
	private JPanel fieldGrid, flow;

	public NyAnsattGUI(Huvudvindu w)
	{
		win = w;
		reg = w.getRegister();
		lytter = new Lytterklasse();

		//oppretter ulike jpaneler med ulike lyoutmanagere
		setLayout(new BorderLayout() );
		fieldGrid = new JPanel(new GridLayout(17, 1) );
		fieldGrid.setBorder(new EmptyBorder(new Insets(30, 0, 0, 0)));
		flow = new JPanel(new FlowLayout() );

		//oppretter info feltet
		info = new JTextArea();
		info.setEditable(false);
		info.setText("Fyll ut feltene for å registrere en ny ansatt. Personnummeret skal være 11 siffer, og passordene må være like.\n" +
						"Klikk på Lagre ansatt når du er ferdig");

		//oppretter data feltene og lagre knapp
		fNavn = new JTextField(DATA_FIELD_LENGTH);
		eNavn = new JTextField(DATA_FIELD_LENGTH);
		pNr = new JTextField(DATA_FIELD_LENGTH);
		pass1 = new JPasswordField(DATA_FIELD_LENGTH);
		pass2 = new JPasswordField(DATA_FIELD_LENGTH);
		tlf = new JTextField(DATA_FIELD_LENGTH);
		ansAt = new JTextField(DATA_FIELD_LENGTH);
		fNavnLabel = new JLabel("Fornavn:");
		eNavnLabel = new JLabel("Etternavn:");
		pNrLabel = new JLabel("Personnummer:");
		pass1Label = new JLabel("Login passord:");
		pass2Label = new JLabel("Gjenta passord:");
		tlfLabel = new JLabel("Telefonnummer:");
		ansAtLabel = new JLabel("Arbeidsplass:");
		addAns = new JButton("Lagre ansatt");
		//legger til lyttere
		addAns.addActionListener(lytter);
		fNavn.addActionListener(lytter);
		eNavn.addActionListener(lytter);
		pNr.addActionListener(lytter);
		pass1.addActionListener(lytter);
		pass2.addActionListener(lytter);
		tlf.addActionListener(lytter);
		ansAt.addActionListener(lytter);

		//legger til elementer i paneler
		fieldGrid.add(fNavnLabel);
		fieldGrid.add(fNavn);
		fieldGrid.add(eNavnLabel);
		fieldGrid.add(eNavn);
		fieldGrid.add(pNrLabel);
		fieldGrid.add(pNr);
		fieldGrid.add(ansAtLabel);
		fieldGrid.add(ansAt);
		fieldGrid.add(tlfLabel);
		fieldGrid.add(tlf);
		fieldGrid.add(pass1Label);
		fieldGrid.add(pass1);
		fieldGrid.add(pass2Label);
		fieldGrid.add(pass2);
		fieldGrid.add(addAns);
		flow.add(fieldGrid);
		add(new JScrollPane(flow, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER), BorderLayout.LINE_START);
		add(info, BorderLayout.CENTER);
	}

	public void nyAnsatt()				//metode for å sjekke om feltene er riktig fylt ut og hvis de er, registrerer ny ansattbruker
	{
		String fn, ln, pn, p, t, ea, regexPattern, ansnr;
		regexPattern = "(0[1-9]|[12][0-9]|3[01])(0[1-9]|1[012])(\\d{7})";
		fn = fNavn.getText();
		ln = eNavn.getText();
		pn = pNr.getText();
		t = tlf.getText();
		ea = ansAt.getText();
		p = pass1.getText();
		if(!p.equals(pass2.getText() ) )
			info.setText("Passordene må være like");
		else if(fn.equals("") || ln.equals("") || pn.equals("") || t.equals("") || ea.equals("") || p.equals("") )
			info.setText("Alle feltene må fylles ut");
		else if(!pn.matches(regexPattern) )
			info.setText("Personnummeret er ikke gyldig");
		else if(!(reg.getAnsattViaNr(pn) == null) )
			info.setText("En person med samme personnummer finnes fra før");
		else
		{
			Ansatt res = new Ansatt(fn, ln, pn, t, ea, p);
			reg.nyAnsatt(res);
			info.setText("Ansatt lagret:\n" + res.toString() );
		}
	}

	private class Lytterklasse implements ActionListener		//lytter til knapp og felter, så man ikke nødvendigvis må trykke på knappen
	{
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource() == addAns || e.getSource() == fNavn || e.getSource() == eNavn
			|| e.getSource() == pNr || e.getSource() == pass1 || e.getSource() == pass2
			 || e.getSource() == tlf || e.getSource() == ansAt)
				nyAnsatt();
		}
	}
}
