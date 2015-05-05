/*Skrevet av Even, s199184. Sist endret 05.05.2015
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
	private int søkFor;
	private Huvudvindu vindu;
	//private AnsattProfil profil;
	private JLabel søkEtterLabel, ansattFornavnLabel, ansattEtternavnLabel, ansattPersNrLabel, ansattNummerLabel, ansattAvdelingLabel;
	private JTextArea info, visAnsattInfo;
	private JTextField ansattFornavn, ansattEtternavn, ansattPersNr, ansattNummer, ansattAvdeling;
	private ButtonGroup gruppeKnapper;
	private JRadioButton ans;
	private JButton søkeKnapp, nyAns, slettAns, lagre, visAnsattKnapp, statButton;
	//private RadioButtonLytter radioLytter;
	private Lytterklasse lytter;
	private JPanel hasLicChoose, grid, searchGrid, bord, licChoose, flow, visAnsatte, visAnsatteFlow;
	private Register register;
	private TModel tableModel;
	private JTable table;


	public AdminGUI(Huvudvindu v)
	{
			/*

			Dobbelklikk på bruker skal åpne JOptionPane med info om brukern


			*/
			vindu = v;
			register = vindu.getRegister();
			//radioLytter = new RadioButtonLytter();
			lytter = new Lytterklasse();

			//avkrysningsboks

			gruppeKnapper = new ButtonGroup();
			ans = new JRadioButton("Ansatte", true);
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
			ansattAvdeling = new JTextField(DATAFELT_LENGDE);
			søkEtterLabel = new JLabel("Søk etter:");
			ansattFornavnLabel = new JLabel("Ansatt fornavn:");// Ansatts Navn?((legge til tooltip på disse
			ansattEtternavnLabel = new JLabel("Ansatt etternavn:");
			ansattPersNrLabel = new JLabel("Ansatt personnummer:");
			ansattNummerLabel = new JLabel("Ansattnummer:");
			ansattAvdelingLabel = new JLabel("Avdeling:");
			søkeKnapp = new JButton("Søk");
			nyAns = new JButton("Legg til en ny ansatt");
			slettAns = new JButton("Slett bruker");
			lagre = new JButton("Lagre endringer");
			visAnsattKnapp = new JButton("Vis ansattinfo");
			statButton = new JButton("Vis statistikk");
			//Lyttere:

			søkeKnapp.addActionListener(lytter);
			ansattFornavn.addActionListener(lytter);
			ansattEtternavn.addActionListener(lytter);
			//ansattPersNr.addActionListener(lytter);
			//ansattNummer.addActionListener(lytter);
			nyAns.addActionListener(lytter);
			slettAns.addActionListener(lytter);
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
			searchGrid.add(ansattAvdelingLabel);
			searchGrid.add(ansattAvdeling);
			searchGrid.add(søkeKnapp);
			searchGrid.add(nyAns);
			searchGrid.add(slettAns);
			searchGrid.add(statButton);

			bord.add(grid, BorderLayout.PAGE_START);
			bord.add(searchGrid, BorderLayout.LINE_START);
			flow.add(bord);



			add(info, BorderLayout.PAGE_START);
			add(new JScrollPane(flow, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER), BorderLayout.LINE_START);
			//oppretter tabellen for visning og redigering
			tableModel = new TModel(register.getAnsatte()); //new TModel()
			table = new JTable(tableModel);
			//legger til elementer i hovedpanelet
			add(new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER), BorderLayout.CENTER);
			add(new JScrollPane(visAnsatte), BorderLayout.PAGE_END);

			//vindu.tilbakePos.setVisible(false);

			MouseListener mouseListener = new MouseAdapter()
			{
				public void mouseClicked(MouseEvent mouseEvent)
				{
			        JTable theTable = (JTable) mouseEvent.getSource();
			        if (mouseEvent.getClickCount() == 2)
			        {
			        	//int index = theTable.locationToIndex(mouseEvent.getPoint());
			        	int rad = theTable.getSelectedRow();
			        	if (rad >= 0)
			          	{
							Ansatt anna = register.getAnsattViaAnsattNr( (String)theTable.getValueAt(rad, TModel.ANSATT_NR) );
							AnsattProfil vin = new AnsattProfil(anna);
							//JOptionPane.showMessageDialog(null, anna.toString() );
			            	//Object o = theTable.getModel().getValueAt(rad, );
			            	//JOptionPane.showMessageDialog(null, "Double-clicked on: " + o.toString());
			          	}
			        }
			      }
			};
			table.addMouseListener(mouseListener);


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

		String afn = ansattFornavn.getText();
		String aen = ansattEtternavn.getText();
		String apn = ansattPersNr.getText();
		//Integer anr = Integer.valueOf( ansattNummer.getText());
		String anr = ansattNummer.getText();
		String avd = ansattAvdeling.getText();

		if(!afn.isEmpty() )
			res = register.getAnsattViaNavn(res, afn);
		if(!aen.isEmpty() )
			res = register.getAnsattViaNavn(res, aen);
		if(!apn.isEmpty() )
			res = register.getAnsattViaNr(res, apn);
		if(!anr.isEmpty() )
			res = register.getAnsattViaAnsattNr(res, anr);
		//if(anr != null )
		//	res = register.getAnsattViaAnsattNr(res, anr);
		if(!avd.isEmpty() )
			res = register.getAnsattViaAvdeling(res, avd);

		//res = register.getPatientsByGroup(res, la, lb, lc);

		tableModel = new TModel(res);
		table.setModel(tableModel);
		tableModel.setTableCellEditor(table);
		return res;
	}

	public void nyAnsatt() //
	{
		vindu.swapPanel(new NyAnsattGUI(vindu));
	}

	public void visAnsatt() //<en JOptionPane.showmessagedialog skal poppe opp>
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

	public void slettBruker()
	{
		try
		{
			int row = table.getSelectedRow();
			if(row == -1)
				return;
			String melding = "Er du sikker på at du vil slette brukeren?";
			String tittel = "Validering";

    		int svar = JOptionPane.showConfirmDialog(null, melding, tittel, JOptionPane.YES_NO_OPTION);
        	if (svar == JOptionPane.YES_OPTION)
        	{
          		Ansatt anna = register.getAnsattViaNr( (String)tableModel.getValueAt(row, TModel.PERSON_NR) );
				register.slettAnsatt(anna);
				return;
			}
        }
        catch(NoSuchElementException nsee)
        {
			JOptionPane.showMessageDialog(null, "NoSuchElementException i slettBruker-metoden", "FEIL", JOptionPane.ERROR_MESSAGE);
		}
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
			if(e.getSource() == søkeKnapp || e.getSource() == ansattFornavn || e.getSource() == ansattEtternavn
				|| e.getSource() == ansattPersNr || e.getSource() == ansattNummer || e.getSource() == ansattAvdeling)
				søk();
			else if(e.getSource() == nyAns)
				nyAnsatt();
			else if(e.getSource() == slettAns)
				slettBruker();
			else if(e.getSource() == lagre)
				tableModel.saveChanges();
			else if(e.getSource() == statButton)
				visStatistikk();
		}
	}
}
