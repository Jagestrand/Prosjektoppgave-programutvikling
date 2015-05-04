import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AnsattVindu extends JPanel
{
	private Register register;
	private Huvudvindu window;
	private Listener lytter;
	private final int DATA_FIELD_LENGTH = 20;
	public static final int SØK_KUNDE = 1, SØK_FORSIKRING = 2, SØK_SKADEMELDING = 3;
	private JTextField kundNavn, kundTelefon, kundAdresse, kundPersonnr, kundPostnr, kundPostby, typeForsikring;
	private JTextArea informationTop, visForsikringInfo;
	private JButton søkKnapp, visForsikringKnapp, skjulForsikringKnapp, deletaForsikringKnapp;
	private JLabel kundNavnLabel, kundTelefonLabel, kundAdresseLabel, kundPersonnrLabel, kundPostnrLabel, kundPostbyLabel;
	private JPanel visForsikring, visForsikringFlow, searchGrid, border, flow;
	private TModel tableModel;
	private JTable table;

	public AnsattVindu(Huvudvindu win)
	{
		/*
		Denne klassen må kunne se 3 faner:
		1. Kunder (og åpne nytt vindu med mer info)
		2. Forsikringer (og åpne vindu med mer info)
		3. Skademeldinger (og åpne vindu med mer info)
		*/
		window = win;
		register = window.getRegister();
		lytter = new Listener();
		//paneler & layoutmanagere
		setLayout(new BorderLayout());

		visForsikring = new JPanel(new BorderLayout());
		visForsikringFlow = new JPanel( new FlowLayout());
		border = new JPanel(new BorderLayout());
		searchGrid = new JPanel(new GridLayout(15,1));
		flow = new JPanel(new FlowLayout());

		//infoormations felt
		informationTop = new JTextArea("Velkommen tilbake");

		informationTop.setLineWrap(true);
		informationTop.setWrapStyleWord(true);
		visForsikringInfo = new JTextArea();
		visForsikringInfo.setEditable(false);
		visForsikringInfo.setVisible(false);
		informationTop.setEditable(false);
		//felt for kunde
		kundNavn = new JTextField(DATA_FIELD_LENGTH);
		kundTelefon = new JTextField(DATA_FIELD_LENGTH);
		kundAdresse = new JTextField(DATA_FIELD_LENGTH);
		kundPersonnr = new JTextField(DATA_FIELD_LENGTH);
		kundPostnr = new JTextField(DATA_FIELD_LENGTH);
		kundPostby = new JTextField(DATA_FIELD_LENGTH);


		kundNavnLabel = new JLabel("Navn:");
		kundTelefonLabel = new JLabel("Telefonnummer:");
		kundAdresseLabel = new JLabel("Adresse:");
		kundPersonnrLabel = new JLabel("Personummer:");
		kundPostnrLabel = new JLabel("Postnr:");
		kundPostbyLabel = new JLabel("Poststed:");
		søkKnapp = new JButton("Søk");

		visForsikringKnapp = new JButton("Åpne forsikring");
		skjulForsikringKnapp = new JButton("Lukk forsikring");
		skjulForsikringKnapp.setVisible(false);
		deletaForsikringKnapp = new JButton("Sletta forsikring");

		søkKnapp.addActionListener(lytter);
		kundNavn.addActionListener(lytter);
		kundTelefon.addActionListener(lytter);
		kundAdresse.addActionListener(lytter);
		kundPersonnr.addActionListener(lytter);
		kundPostnr.addActionListener(lytter);
		kundPostby.addActionListener(lytter);
		visForsikringKnapp.addActionListener(lytter);
		skjulForsikringKnapp.addActionListener(lytter);
		deletaForsikringKnapp.addActionListener(lytter);

		searchGrid.add(kundNavnLabel);
		searchGrid.add(kundNavn);
		searchGrid.add(kundTelefonLabel);
		searchGrid.add(kundTelefon);
		searchGrid.add(kundAdresseLabel);
		searchGrid.add(kundAdresse);
		searchGrid.add(kundPersonnrLabel);
		searchGrid.add(kundPersonnr);
		searchGrid.add(kundPostnrLabel);
		searchGrid.add(kundPostnr);
		searchGrid.add(kundPostbyLabel);
		searchGrid.add(kundPostby);
		searchGrid.add(søkKnapp);
		visForsikringFlow.add(visForsikringKnapp);
		visForsikringFlow.add(skjulForsikringKnapp);
		visForsikringFlow.add(deletaForsikringKnapp);
		visForsikring.add(visForsikringFlow, BorderLayout.LINE_END);
		visForsikring.add(visForsikringInfo, BorderLayout.CENTER);
		border.add(searchGrid, BorderLayout.LINE_START);
		flow.add(border);
		add(informationTop, BorderLayout.PAGE_START);
		add(new JScrollPane(flow, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER), BorderLayout.LINE_START);

		tableModel = new TModel(register.getKunder());
		table = new JTable(tableModel);
		add(new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER), BorderLayout.CENTER);






		//submitKnapp = new JButton("Submit");
		//bilForsikring = new JButton("Bil");
		//båtForsikring = new JButton("Hus");
		//fritidForsikring = new JButton("Fritid");



	}

	public void søk()
	{
		søkKunde();
	}

	public KundeReg søkKunde()
	{
		KundeReg res = register.getKunder();

		String kn = kundNavn.getText();
		String pn = kundPersonnr.getText();
		String tlf = kundTelefon.getText();
		String adr = kundAdresse.getText();
		String pnr = kundPostnr.getText();
		String pb = kundPostby.getText();

		if(!kn.isEmpty() )
			res = register.getKundeViaNavn(res, kn);
		if(!pn.isEmpty() )
			res = register.getKundeViaNummer(res, pn);
		if(!tlf.isEmpty() )
			res = register.getKundeViaTelefon(res, tlf);
		if(!adr.isEmpty() )
			res = register.getKundeViaAdresse(res, adr);
		if(!pnr.isEmpty() )
			res = register.getKundeViaPostnr(res, pnr);
		if(!pb.isEmpty() )
			res = register.getKundeViaBy(res, pb);

		tableModel = new TModel(res);
		table.setModel(tableModel);
		tableModel.setTableCellEditor(table);
		return res;
	}



	private class Listener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if( e.getSource() == søkKnapp || e.getSource() == kundNavn || e.getSource() == kundTelefon
			|| e.getSource() == kundAdresse || e.getSource() == kundPersonnr || e.getSource() == kundPostnr
			|| e.getSource() == kundPostby )
				søk();
		}
	}
}
