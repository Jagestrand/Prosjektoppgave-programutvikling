import javax.swing.*;

import java.awt.*;
import java.awt.event.*;


public class KundeGUI1 extends JPanel
{
	private Register register;
	private Huvudvindu vindu;
	private Lytterklasse lytter;
	//private ForsikringsReg;
	private Kunde kunde;
	private int DATA_FIELD_LENGTH = 20;
	private JTextField navn1, navn2, navn3, navn4, navn5, navn6, navn7, navn8, navn9, navn10, navn11, navn12, navn13, navn14, telefon, adresse, personnr;
	private String navnet, innlogget;
	private JTextArea informationTop, visForsikringInfo;
	private JButton skademeldinger, regForsikring, båt, bil, hus, fritid;
	private JLabel navnLabel, telefonLabel, adresseLabel, personnrLabel;
	private JPanel innloggPanel, border, border2, flow, registerGrid, forsikringsGrid;
	private TModel tableModel;
	private JTable table, table2;
	//public final static String typeForsikring = "Båt";
	//private Lytterklasse lytter;

	public KundeGUI1(Huvudvindu v, Kunde kunn)
	{
		/*
		knapp henter fram info i det store feltet/egen popoppboks
		det står "Innlogget: <Kundenavn>" oppe i venstre hjørne
		har en "meld skade"-knapp og "si opp forsikring"-knapp og en "skadesaker"-knapp
		"skadesaker-knapp" leder til liste med skademeldinger
		*/
		vindu = v;
		kunde = kunn;
		lytter = new Lytterklasse();
		register = vindu.getRegister();

		setLayout(new BorderLayout() );

		border = new JPanel(new BorderLayout());
		registerGrid = new JPanel(new GridLayout(2, 4));
		flow = new JPanel( new FlowLayout());
		/*String[] comboBoxItem = {typeForsikring};
		JComboBox cb = new JComboBox(comboBoxItem);
		cb.setEditable(false);
		cb.addActionListener(this);*/



		//infoormations felt
		informationTop = new JTextArea("Velkommen tilbake");

		informationTop.setLineWrap(true);
		informationTop.setWrapStyleWord(true);
		informationTop.setEditable(false);

		navnet = kunde.getFornavn() + " " + kunde.getEtternavn();
		innlogget = "Innlogget: " + navnet;
		navnLabel = new JLabel(innlogget);
		innloggPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		innloggPanel.add(navnLabel);


		/*navn = new JTextField(DATA_FIELD_LENGTH);
		telefon = new JTextField(DATA_FIELD_LENGTH);
		adresse = new JTextField(DATA_FIELD_LENGTH);
		personnr = new JTextField(DATA_FIELD_LENGTH);

		navnLabel = new JLabel("Navn:");
		telefonLabel = new JLabel("Telefon:");
		adresseLabel = new JLabel("Adresse:");
		personnrLabel = new JLabel("Personnummer:");

		registerGrid.add(navnLabel);
		registerGrid.add(navn);
		registerGrid.add(telefonLabel);
		registerGrid.add(telefon);
		registerGrid.add(adresseLabel);
		registerGrid.add(adresse);
		registerGrid.add(personnrLabel);
		registerGrid.add(personnr);*/

		navn1 = new JTextField(DATA_FIELD_LENGTH);
		navn1.setVisible(false);
		navn2 = new JTextField(DATA_FIELD_LENGTH);
		navn2.setVisible(false);
		navn3 = new JTextField(DATA_FIELD_LENGTH);
		navn3.setVisible(false);
		navn4 = new JTextField(DATA_FIELD_LENGTH);
		navn4.setVisible(false);
		navn5 = new JTextField(DATA_FIELD_LENGTH);
		navn5.setVisible(false);
		navn6 = new JTextField(DATA_FIELD_LENGTH);
		navn6.setVisible(false);
		navn7 = new JTextField(DATA_FIELD_LENGTH);
		navn7.setVisible(false);
		navn8 = new JTextField(DATA_FIELD_LENGTH);
		navn8.setVisible(false);
		navn9 = new JTextField(DATA_FIELD_LENGTH);
		navn9.setVisible(false);
		navn10 = new JTextField(DATA_FIELD_LENGTH);
		navn10.setVisible(false);
		navn11 = new JTextField(DATA_FIELD_LENGTH);
		navn11.setVisible(false);
		navn12 = new JTextField(DATA_FIELD_LENGTH);
		navn12.setVisible(false);
		navn13 = new JTextField(DATA_FIELD_LENGTH);
		navn13.setVisible(false);
		navn14 = new JTextField(DATA_FIELD_LENGTH);
		navn14.setVisible(false);

		regForsikring = new JButton("Registrere forsikringer");
		regForsikring.addActionListener(lytter);



		registerGrid.add(innloggPanel);//, BorderLayout.LINE_START);
		registerGrid.add(navn1);
		registerGrid.add(navn2);
		registerGrid.add(navn3);
		registerGrid.add(navn4);
		registerGrid.add(navn5);
		registerGrid.add(navn6);
		registerGrid.add(navn7);
		registerGrid.add(navn8);
		registerGrid.add(navn9);
		registerGrid.add(regForsikring);
		registerGrid.add(navn10);
		registerGrid.add(navn11);
		registerGrid.add(navn12);
		//registerGrid.add(navn13);
		//registerGrid.add(navn14);


		border.add(registerGrid, BorderLayout.LINE_START);	//LINE_START
		flow.add(border, BorderLayout.PAGE_START);
		add(informationTop, BorderLayout.PAGE_START);
		add( new JScrollPane(flow, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER)
		,BorderLayout.PAGE_START);	//LINE_START
		tableModel = new TModel(register.getAnsatte()); //new TModel()
		table = new JTable(tableModel);
		table2 = new JTable();

		for(int i = 0; i <
		//legger til elementer i hovedpanelet
		add(new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER), BorderLayout.CENTER);
		add(new JScrollPane(table2, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER), BorderLayout.LINE_END));




		skademeldinger = new JButton("Registrere skademeldinger");
		//registerGrid.add(skademeldinger);

	}
		private class Lytterklasse implements ActionListener
		{
			public void actionPerformed (ActionEvent e)
			{
				if(e.getSource() == regForsikring)
				{
					ValgAvForsikring cl = new ValgAvForsikring(kunde);
					//  cl.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					cl.setVisible(true);
				}
			}
		}


}
