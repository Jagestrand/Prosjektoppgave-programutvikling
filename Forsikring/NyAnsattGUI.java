import javax.swing.*;
import java.awt.*;
import java.awt.event.*;;

public class NyAnsattGUI extends JPanel
{
	private final int DATA_FIELD_LENGTH = 20;
	private boolean a = false, b = false, c = false;
	private Huvudvindu win;
	private Register reg;
	private JTextField fName, lName, pNr, pass1, pass2, phone, empAt;
	private JTextArea info;
	private JLabel fNameLabel, lNameLabel, pNrLabel, pass1Label, pass2Label, phoneLabel, licLabel, empAtLabel;
	private JButton addAns;
	private Listener listener;
	private JPanel fieldGrid, lisenceGrid, flow;

	public NyAnsattGUI(Huvudvindu w)
	{
		win = w;
		reg = w.getRegister();
		listener = new Listener();
		//oppretter ulike jpaneler med ulike lyoutmanagere
		setLayout(new BorderLayout() );
		fieldGrid = new JPanel(new GridLayout(17, 1) );//ser ingen grunn til å opprette disse tallene som egne konstanter
		lisenceGrid= new JPanel(new GridLayout(1,3) );
		flow = new JPanel(new FlowLayout() );
		//oppretter info feltet
		info = new JTextArea();
		info.setEditable(false);
		info.setText("Fyll ut feltene for å registrere en ny ansatt. Personnummeret skal være 11 siffer, og passordene må være like.\n" +
						"Klikk på Lagre ansatt når du er ferdig");
		//oppretter data feltene og lagre knapp
		fName = new JTextField(DATA_FIELD_LENGTH);
		lName = new JTextField(DATA_FIELD_LENGTH);
		pNr = new JTextField(DATA_FIELD_LENGTH);
		pass1 = new JPasswordField(DATA_FIELD_LENGTH);
		pass2 = new JPasswordField(DATA_FIELD_LENGTH);
		phone = new JTextField(DATA_FIELD_LENGTH);
		empAt = new JTextField(DATA_FIELD_LENGTH);
		fNameLabel = new JLabel("Fornavn:");
		lNameLabel = new JLabel("Etternavn:");
		pNrLabel = new JLabel("Personnummer:");
		pass1Label = new JLabel("Login passord:");
		pass2Label = new JLabel("Gjenta passord:");
		phoneLabel = new JLabel("Telefonnummer:");
		licLabel = new JLabel("Lisens:");
		empAtLabel = new JLabel("Arbeidsplass:");
		addAns = new JButton("Lagre ansatt");
		//legger til lyttere
		addAns.addActionListener(listener);
		fName.addActionListener(listener);
		lName.addActionListener(listener);
		pNr.addActionListener(listener);
		pass1.addActionListener(listener);
		pass2.addActionListener(listener);
		phone.addActionListener(listener);
		empAt.addActionListener(listener);
		//legger til elementer i paneler
		fieldGrid.add(fNameLabel);
		fieldGrid.add(fName);
		fieldGrid.add(lNameLabel);
		fieldGrid.add(lName);
		fieldGrid.add(pNrLabel);
		fieldGrid.add(pNr);
		fieldGrid.add(licLabel);
		fieldGrid.add(lisenceGrid);
		fieldGrid.add(empAtLabel);
		fieldGrid.add(empAt);
		fieldGrid.add(phoneLabel);
		fieldGrid.add(phone);
		fieldGrid.add(pass1Label);
		fieldGrid.add(pass1);
		fieldGrid.add(pass2Label);
		fieldGrid.add(pass2);
		fieldGrid.add(addAns);
		flow.add(fieldGrid);
		add(new JScrollPane(flow, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER), BorderLayout.LINE_START);
		add(info, BorderLayout.CENTER);
	}

	public void nyAnsatt()
	{
		String fn, ln, pn, p, t, ea, regexPattern;
		regexPattern = "(0[1-9]|[12][0-9]|3[01])(0[1-9]|1[012])(\\d{7})";
		fn = fName.getText();
		ln = lName.getText();
		pn = pNr.getText();
		t = phone.getText();
		ea = empAt.getText();
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

	private class Listener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource() == addAns || e.getSource() == fName || e.getSource() == lName
			|| e.getSource() == pNr || e.getSource() == pass1 || e.getSource() == pass2
			 || e.getSource() == phone || e.getSource() == empAt)
				nyAnsatt();
		}
	}
}