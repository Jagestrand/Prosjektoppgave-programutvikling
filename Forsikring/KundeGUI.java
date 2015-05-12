import javax.swing.*;

import java.awt.*;
import java.awt.event.*;


public class KundeGUI extends JPanel
{
	private Register register;
	private Huvudvindu vindu;
	private int DATA_FIELD_LENGTH = 20;
	private JTextField navn, telefon, adresse, personnr;
	private JTextArea informationTop, visForsikringInfo;
	private JButton skademeldinger, regForsikring, båt, bil, hus, fritid;
	private JLabel navnLabel, telefonLabel, adresseLabel, personnrLabel;
	private JPanel border, border2, flow, registerGrid, forsikringsGrid;
	private TModel tableModel;
	private JTable table;
	//public final static String typeForsikring = "Båt";
	//private Lytterklasse lytter;
	
	public KundeGUI(Huvudvindu v)
	{
		/*
		knapp henter fram info i det store feltet/egen popoppboks
		det står "Innlogget: <Kundenavn>" oppe i venstre hjørne
		har en "meld skade"-knapp og "si opp forsikring"-knapp og en "skadesaker"-knapp
		"skadesaker-knapp" leder til liste med skademeldinger
		*/

		vindu = v;
	//	lytter = new Lytterklasse();
		register = vindu.getRegister();
		
		setLayout(new BorderLayout() );
		
		border = new JPanel(new BorderLayout());
		registerGrid = new JPanel(new GridLayout(10,1));
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
		
		navn = new JTextField(DATA_FIELD_LENGTH);
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
		registerGrid.add(personnr);
		
		border.add(registerGrid, BorderLayout.LINE_START);
		flow.add(border);
		add(informationTop, BorderLayout.PAGE_START);
		add( new JScrollPane(flow, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER)
		,BorderLayout.LINE_START);
		tableModel = new TModel(register.getAnsatte()); //new TModel()
		table = new JTable(tableModel);
		//legger til elementer i hovedpanelet
		add(new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER), BorderLayout.CENTER);

		
		regForsikring = new JButton("Registrere forsikringer");
		registerGrid.add(regForsikring);
		regForsikring.addActionListener (new Action1());
		
		skademeldinger = new JButton("Registrere skademeldinger");
		registerGrid.add(skademeldinger);
		
	}
		class Action1 implements ActionListener 
		{	
		public void actionPerformed (ActionEvent e) 
			{
				ValgAvForsikring cl = new ValgAvForsikring();
				//  cl.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				cl.setVisible(true);    
			}
		}
		
	
}
