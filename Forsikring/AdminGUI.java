/*Skrevet av Rebwar Eliassi, s183736. Sist endret 16.04.2015
Klassen definerer brukergrensesnittet for administrator, klassen utvider JPanel med felter og knapper for søkefunksjoner
brukeren kan bruke til å søke seg frem til øsnket data. Panelet har også funskjoner for å endre data der dette er tillat
*/

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;

public class AdminGUI extends JPanel
{
	public static final int SØK_FORSIKRING = 1, SØK_KUNDE = 2, SØK_ANSATT = 3, DATAFELT_LENGDE = 20;
	//private boolean .....;
	private int søkFor;
	private Huvudvindu vindu;
	private JLabel søkEtterLabel, ansattFornavnLabel, ansattEtternavnLabel, ansattPersNrLabel, ansattNummerLabel;
	private JTextArea info, visAnsattInfo;
	private JTextField ansattFornavn, ansattEtternavn, ansattPersNr, ansattNummer;
	private ButtonGroup gruppeKnapper;
	private JRadioButton ans;
	private JButton søkeKnapp, nyAns, lagre, visAnsattKnapp, statButton;
	//private RadioButtonLytter radioLytter;
	private Lytterklasse lytter;
	private JPanel hasLicChoose, grid, searchGrid, bord, licChoose, flow, visAnsatte, visAnsatteFlow;
	private Register register;
	private TModel tableModel;
	private JTable table;


	public AdminGUI(Huvudvindu v)
	{
			vindu = v;
			register = vindu.getRegister();
			//radioLytter = new RadioButtonLytter();
			lytter = new Lytterklasse();

			gruppeKnapper = new ButtonGroup();
			ans = new JRadioButton("Ansatte", true);
			//ans.addItemListener(radioLytter);
			gruppeKnapper.add(ans);

			//Setter layout:
			setLayout(new BorderLayout() );
			//oppretter JPaneler med ulike LayoutManagere
			visAnsatte = new JPanel(new BorderLayout() ); //show prescription var skrevet tidligere her
			grid = new JPanel (new GridLayout(4, 1) );
			searchGrid = new JPanel(new GridLayout( 15, 1) );
			bord = new JPanel(new BorderLayout() );
			//licChoose = new Jpanel(new GridLayout(1, 3) );
			bord = new JPanel(new BorderLayout() );
			//licChoose = new JPanel(new GridLayout(1, 3) );
			//hasLicChoose = new JPanel(new GridLayout(1, 3) );
			flow = new JPanel(new FlowLayout() );
			visAnsatteFlow = new JPanel(new FlowLayout() ); //show prescription var skrevet tidligere

			info = new JTextArea(""); //Info tekst

			info.setEditable(false);
			info.setLineWrap(true);
			info.setWrapStyleWord(true);
			visAnsattInfo = new JTextArea();
			visAnsattInfo.setEditable(false);
			visAnsattInfo.setVisible(false);
			ansattFornavn = new JTextField(DATAFELT_LENGDE);
			ansattEtternavn = new JTextField(DATAFELT_LENGDE);
			ansattPersNr = new JTextField(11);
			ansattNummer = new JTextField(DATAFELT_LENGDE);
			søkEtterLabel = new JLabel("Søk etter:");
			ansattFornavnLabel = new JLabel("Ansatt fornavn:");// Ansatts Navn?((legge til tooltip på disse
			ansattEtternavnLabel = new JLabel("Ansatt etternavn:");
			ansattPersNrLabel = new JLabel("Ansatt personnummer:");
			ansattNummerLabel = new JLabel("Ansattnummer:");
			søkeKnapp = new JButton("Søk");
			nyAns = new JButton("Legg til en ny ansatt");
			lagre = new JButton("Lagre endringer");
			visAnsattKnapp = new JButton("Vis ansattinfo");
			statButton = new JButton("Vis statistikk");
			//Lyttere:

			søkeKnapp.addActionListener(lytter);
			ansattFornavn.addActionListener(lytter);
			//ansattEtternavn.addActionListener(lytter);
			//ansattPersNr.addActionListener(lytter);
			//ansattNummer.addActionListener(lytter);
			nyAns.addActionListener(lytter);
			lagre.addActionListener(lytter);
			visAnsattKnapp.addActionListener(lytter);
			statButton.addActionListener(lytter);
			//legger elementer til i GUI
			grid.add(søkEtterLabel);
			grid.add(ans);
			searchGrid.add(ansattFornavnLabel);
			searchGrid.add(ansattFornavn);
			searchGrid.add(ansattEtternavnLabel);
			searchGrid.add(ansattEtternavn);
			searchGrid.add(ansattNummerLabel);
			searchGrid.add(ansattNummer);
			searchGrid.add(ansattPersNrLabel);
			searchGrid.add(ansattPersNr);
			searchGrid.add(søkeKnapp);
			searchGrid.add(nyAns);
			searchGrid.add(statButton);

			bord.add(grid, BorderLayout.PAGE_START);
			bord.add(searchGrid, BorderLayout.LINE_START);
			flow.add(bord);



			add(info, BorderLayout.PAGE_START);
			add(new JScrollPane(flow, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER), BorderLayout.LINE_START);
			//oppretter tabellen for visning og redigering
			tableModel = new TModel();
			table = new JTable(tableModel);
			//legger til elementer i hovedpanelet
			add(new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER), BorderLayout.CENTER);
			add(new JScrollPane(visAnsatte), BorderLayout.PAGE_END);
			//setter guien til å starte med resept valgt

			//radioLytter.itemStateChanged(null);
		}
		public int getSøkFor()
		{
			return søkFor;
		}

		public void søk()
		{
			søkAnsatt();
		}

	public AnsattReg søkAnsatt()
	{
		AnsattReg res = register.getAnsatte();

		String ansa = ansattFornavn.getText();
		//String afn = ansattFornavn.getText();
		//String aen = ansattEtternavn.getText();

		if(!ansa.isEmpty() )
			res = register.getAnsattViaNavn(res, ansa);
		/*if(!d.isEmpty() )
			res = register.getPatientsByDoctor(res, d);
		if(!m.isEmpty() )
			res = register.getPatientsByMed(res, m);
		if(!c.isEmpty() )
			res = register.getPatientsByCat(res, c);*/
		//res = register.getPatientsByGroup(res, la, lb, lc);

		tableModel = new TModel(res);
		table.setModel(tableModel);
		tableModel.setTableCellEditor(table);
		return res;
	}

	public void nyAnsatt()
	{
		vindu.swapPanel(new NyAnsattGUI(vindu));
	}

	public void visAnsatt()
	{
		try
		{
			int row = table.getSelectedRow();
			if(row == -1 )
			{
				JOptionPane.showMessageDialog(null, "Du må velge en ansatt");
				return;
			}
			Ansatt ass = register.getAnsattViaNr( (String)tableModel.getValueAt(row, TModel.PRESCRIPTION_NR) );
			visAnsattInfo.setText(ass.toString());
		}
		catch(NullPointerException npe)
		{
			JOptionPane.showMessageDialog(null, "Feil", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		visAnsattInfo.setVisible(false);
		validate();
	}

	public void visStatistikk()
	{
		JOptionPane.showMessageDialog(null, "Her har du statistikken din");
	}

	/*private class RadioButtonLytter implements ItemListener
	{
		public void itemStateChanged(ItemEvent e)
		{
			if(ans.isSelected())
			{
				søkFor = SØK_ANSATT;
				lagre.setVisible(true);
				søkAnsatt();
			}
		}
	}*/

	/*private class Lytterklasse implements ActionListener//knappelytter
	{
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource() == søkeKnapp || e.getSource() == ansattFornavn)
				søk();
			else if(e.getSource() == nyAns)
				nyAnsatt();
			//else if(e.getSource() == showPrescriptionButton)
			//	showPrescription();
			//else if(e.getSource() == hidePrescriptionButton)
			//	hidePrescription();
			//else if(e.getSource() == save)
			//	tableModel.saveChanges();
			else if(e.getSource() == statButton)
				visStatistikk();
		}
	}*/

	private class Lytterklasse implements ActionListener//knappelytter
	{
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource() == søkeKnapp || e.getSource() == ansattFornavn)
				søk();
			else if(e.getSource() == nyAns)
				nyAnsatt();
			else if(e.getSource() == lagre)
				tableModel.saveChanges();
			else if(e.getSource() == statButton)
				visStatistikk();
		}
	}
}
