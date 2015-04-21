import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Ansatt extends JPanel
{
	private Register register;
	private Huvudvindu window;
	//private Listener lytter;
	private final int DATA_FIELD_LENGTH = 20; 
	private JTextField kundNavn, kundTelefon, kundAdresse, kundPersonnr, typeForsikring;
	private JTextArea informationTop, visForsikringInfo;
	private JButton søkKnapp, visForsikringKnapp, skjulForsikringKnapp, deletaForsikringKnapp;
	private JLabel kundNavnLabel, kundTelefonLabel, kundAdresseLabel, kundPersonnrLabel;
	private JPanel visForsikring, visForsikringFlow, searchGrid, border, flow;
	//private TModel tableModell;
	//private JTable table;

	public Ansatt(Huvudvindu win)
	{
		window = win;
		register = window.getRegister();
		//lytter
		//lytter = new Listener();
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

		
		kundNavnLabel = new JLabel("Navn:");
		kundTelefonLabel = new JLabel("Telefonnummer:");
		kundAdresseLabel = new JLabel("Adresse:");
		kundPersonnrLabel = new JLabel("Personummer:");
		søkKnapp = new JButton("Søk");

		visForsikringKnapp = new JButton("Åpne forsikring");
		skjulForsikringKnapp = new JButton("Lukk forsikring");
		skjulForsikringKnapp.setVisible(false);
		deletaForsikringKnapp = new JButton("Sletta forsikring");

		//søkKnapp.addActionListener(lytter);
		//kundNavn.addActionListener(lytter);
		//kundTelefon.addActionListener(lytter);
		//kundAdresse.addActionListener(lytter);
		//kundPersonnr.addActionListener(lytter);
		//visForsikringKnapp.addActionListener(lytter);
		//skjulForsikringKnapp.addActionListener(lytter);
		//deletaForsikringKnapp.addActionListener(lytter);

		searchGrid.add(kundNavnLabel);
		searchGrid.add(kundNavn);
		searchGrid.add(kundTelefonLabel);
		searchGrid.add(kundTelefon);
		searchGrid.add(kundAdresseLabel);
		searchGrid.add(kundAdresse);
		searchGrid.add(kundPersonnrLabel);
		searchGrid.add(kundPersonnr);
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

		//tableModel = new TModel(register.getForsikringMot);


		

		//submitKnapp = new JButton("Submit");
		//bilForsikring = new JButton("Bil");
		//båtForsikring = new JButton("Hus");
		//fritidForsikring = new JButton("Fritid");



	}
}