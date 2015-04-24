import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;

public class KundeGUI
{
	private JButton søkeKnapp, lagForsikring, editForsikring, visForsikringer
	private JTextField
	private JTable mainArea;
	private Huvudvindu vindu;
	private Kunde kunde;
	private Register register;
	private TModel model;
	private JPanel navnSøk, søkeKriterie, søkeKnappPlass, venstrePanel,, topPanel, lavKnapp, hovedVindu;
	private Knappelytter lytter;

	public KundeGUI( Huvudvindu hovedVin, Kunde kun )
	{
		vindu = hovedVin;
		register = vindu.getRegister();
		kunde = kun;

		lytter = new Knappelytter();
		visForsikringer = new JButton("Vis forsikringer");
		editForsikring = new JButton("Forandre forsikring");
		lagForsikring = new JButton("Skriv ny forsikring");
		lagForsikring.addActionListener(lytter);
		editForsikring.addActionListener(lytter);
		søkeKnapp = new JButton("Søk");
		søkeKnapp.addActionListener(lytter);

