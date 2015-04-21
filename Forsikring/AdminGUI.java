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
	public static final int.....;
	private boolean .....;
	private int searchFor;
	private MainWindow win;
	private JTextArea info,....;
	private JTextfield empName, empLastName, ...;

	private Register register;
	private TModel tableModel;
	private JTable table;


	public AdminGUI(MainWindow w)
		{
			win = w;
			register = win.getRegister();
			radioButtonListener = new RadiobuttonListener(); ???
			listener = new Listener();???
			licenceListener = new LicenceListener();???

			setLayout(new BorderLayout() );
			//oppretter JPaneler med ulike LayoutManagere
		?	showEmployee = new JPanel(new BorderLayout() ); //show prescription var skrevet tidligere her
			grid = new JPanel (new GridLayout(4, 1) );
			searchGrid = new JPanel(new GridLayout( 15, 1) );
			bord = new JPanel(new BorderLAyout() );
			licChoose = new Jpanel(new GridLayout(1, 3) );
			bord = new JPanel(new BorderLayout() );
			licChoose = new JPanel(new GridLayout(1, 3) );
			hasLicChoose = new JPanel(new GridLayout(1, 3) );
			flow = new JPanel(new FlowLayout() );
		?	showEmployeeFlow = new JPanel(new FlowLayout() ); //show prescription var skrevet tidligere

			info = new JTextArea("") //Info tekst

			info.setEditable(false);
			info.setLineWrap(true);
			info.setWrapStyleWord(true);

			searchForLabel = new JLabel("Søk etter:");
			patNameLabel = new JLabel("Pasient navn/personnummer:");// Ansatts Navn?((legge til tooltip på disse
			patNameLabel.setToolTipText("Søk på peronnummer for mest presise søk, evt. etternavn");//ansatt etternavn?

			medNameLabel = new JLabel("Medikament navn:");

			searchButton = new JButton("Søk");

			statButton = new JButton("Vis statistikk");

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
			add(new JScrollPane(showPrescription), BorderLayout.PAGE_END);
			//setter guien til å starte med resept valgt
			radioButtonListener.itemStateChanged(null);
			licenceListener.itemStateChanged(null);
				}
				public int getSearchFor()
				{
					return searchFor;
				}

				public void search()
				{
					switch(searchFor)
					{
						case SEARCH_PRESCRIPTION:
							searchPrescription();
						break;
						case SEARCH_PATIENT:
							searchPatient();
						break;
						case SEARCH_DOCTOR:
							searchDoctor();
						break;
					}
	}

	public PatientReg searchPatient()
	{
		PatientReg res = register.getPatients();

		String p = patName.getText();
		String d = docName.getText();
		String m = medName.getText();
		String c = medCat.getText();

		if(!p.isEmpty() )
			res = register.getPatientsByName(res, p);
		if(!d.isEmpty() )
			res = register.getPatientsByDoctor(res, d);
		if(!m.isEmpty() )
			res = register.getPatientsByMed(res, m);
		if(!c.isEmpty() )
			res = register.getPatientsByCat(res, c);
		res = register.getPatientsByGroup(res, la, lb, lc);

		tableModel = new TModel(res);
		table.setModel(tableModel);
		tableModel.setTableCellEditor(table);
		return res;
	}
		private class Listener implements ActionListener//knappelytter
		{
			public void actionPerformed(ActionEvent e)
			{
				if(e.getSource() == searchButton || e.getSource() == patName || e.getSource() ==  docName ||
					e.getSource() == medName ||e.getSource() ==  medCat)
					search();
				else if(e.getSource() == newDoc)
					newDoctor();
				else if(e.getSource() == showPrescriptionButton)
					showPrescription();
				else if(e.getSource() == hidePrescriptionButton)
					hidePrescription();
				else if(e.getSource() == save)
					tableModel.saveChanges();
				else if(e.getSource() == statButton)
					showStatistics();
			}
		}
}












