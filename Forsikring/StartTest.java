//Prototype/eksperiment for Startvindu

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StartTest extends JPanel
{
	private final String ADMIN = "admin", ADMIN_PASS = "123456";
	private String adminPass;
	private Vindu vindu;
	private JLabel brukerLabel, passLabel, fNavnLabel, eNavnLabel, passord1Label, passord2Label, tlfNrLabel, persNrLabel;
	private JTextField bruker, passord, fNavn, eNavn, persNr, passord1, passord2, tlfNr;
	private JButton logginn, regKunde;
	private Lytterklasse lytter;  //ButtonListener
	//private Register register;
	private JTextArea info, reg;
	private JPanel flow, loginFelt, regFelt, regNavnFelt, regPassFelt;

	public StartTest( Vindu v ) //Hovedvindu v
	{
		vindu = v;

		//register = v.getRegister();
		//adminPass = ADMIN_PASS;
		info = new JTextArea( "Velkommen!\nSkriv inn ditt brukernavn og passord. Hvis du ikke har bruker kan du registrere deg." );
		info.setEditable( false );
		reg = new JTextArea( "Registrer deg" );
		reg.setEditable( false );
		setLayout( new BorderLayout() );
		add(info, BorderLayout.PAGE_START);
		loginFelt = new JPanel( new GridLayout( 2,2 ));

		regFelt = new JPanel( new GridLayout( 17, 1 ) );
		regNavnFelt = new JPanel( new GridLayout( 1, 2 ) );
		regPassFelt = new JPanel( new GridLayout( 1, 2 ) );
		flow = new JPanel( new FlowLayout(FlowLayout.CENTER) );

		bruker = new JTextField( 10 );
		passord = new JTextField( 10 );
		fNavn = new JTextField( 20 );
		eNavn = new JTextField( 20 );
		persNr = new JTextField( 11 );
		tlfNr = new JTextField ( 20 );
		passord1 = new JTextField( 20 );
		passord2 = new JTextField( 20 );

		brukerLabel = new JLabel( "Brukernavn:" );
		passLabel = new JLabel( "Passord:" );
		fNavnLabel = new JLabel( "Fornavn:" );
		eNavnLabel = new JLabel( "Etternavn:" );
		persNrLabel = new JLabel( "Personnummer:" );
		tlfNrLabel = new JLabel( "Telefonnummer:" );
		passord1Label = new JLabel( "Passord:" );
		passord2Label = new JLabel( "Gjenta passord:" );

		logginn = new JButton( "Logg inn" );
		regKunde = new JButton( "Registrer bruker" );

		logginn.addActionListener( lytter );
		bruker.addActionListener( lytter );
		passord.addActionListener( lytter );
		fNavn.addActionListener( lytter );
		eNavn.addActionListener( lytter );
		persNr.addActionListener( lytter );
		passord1.addActionListener( lytter );
		passord2.addActionListener( lytter );
		tlfNr.addActionListener( lytter );

		regNavnFelt.add(fNavnLabel);
		regNavnFelt.add(eNavnLabel);
		regNavnFelt.add(fNavn);
		regNavnFelt.add(eNavn);

		regPassFelt.add(passord1Label);
		regPassFelt.add(passord2Label);
		regPassFelt.add(passord1);
		regPassFelt.add(passord2);

		regFelt.add(reg);
		regFelt.add(regNavnFelt);
		regFelt.add(persNrLabel);
		regFelt.add(persNr);
		regFelt.add(tlfNrLabel);
		regFelt.add(tlfNr);
		regFelt.add(regPassFelt);

		flow.add(regFelt);
		add(new JScrollPane( flow, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER), BorderLayout.LINE_START );


		/*lytter = new Lytterklasse();
		vindu = v;
		register = v.getRegister();
		//adminPass = ADMIN_PASS;
		info = new JTextArea( "Velkommen!\nSkriv inn ditt brukernavn og passord. Hvis du ikke har bruker kan du registrere deg." );

		info.setEditable( false );
		//info.setLineWrap( true );
		//info.setWrapStyleWord( true );
		brukerLabel = new JLabel( "Brukernavn" );
		passLabel = new JLabel( "Passord" );
		logginn = new JButton( "Logg inn" );
		bruker.addActionListener( lytter );
		passord.addActionListener( lytter );

		JPanel inputFelt = new JPanel( new GridLayout(2,2));
		JPanel kant = new JPanel( new BorderLayout() );
		JPanel flyt = new JPanel( new FlowLayout( FlowLayout.CENTER ) );
		inputFelt.add( brukerLabel );
		inputFelt.add( bruker );
		inputFelt.add( passLabel );
		inputFelt.add( passord );

		setLayout( new BorderLayout() );
		add(info, BorderLayout.PAGE_START );
		kant.add( inputFelt, BorderLayout.CENTER );
		kant.add( logginn, BorderLayout.PAGE_END );
		flyt.add( kant );
		add( new JScrollPane(flyt), BorderLayout.CENTER );
		//kant.setBorder( BorderFactory.createTitledBorder("Logginn" ) );*/
	}

	/*public JFrame getHovedVindu()
	{
		return vindu;
	}*/
	/*
	public void logginn()
	{
		String pass = passord.getText();
		String bruk = bruker.getText();

		if( bruk.toLowerCase().equals( ADMIN ) )
			LogginnAdmin( pass );

		else if( bruk.matches("[0-9]+") && bruk.length() == 11 )
			LogginnKunde( pass );

		else
			LogginnAnsatt( pass );
	}

	public void LogginnAdmin( String pass )
	{
		if( Passordtest( pass ) )
		{
			JPanel ny = new AdminVindu( vindu );
			vindu.byttPanel( ny );
			return;
		}
		JOptionPane.showMessageDialog( null, "Feil passord" );
	}

	public void LogginnKunde( String pass )
	{
		Kunde kunde = register.getKundeNummer( bruker.getText() );
		if( kunde == null )
		{
			JOptionPane.showMessageDialog( null, "Ugyldig brukernavn" );
			return;
		}
		if( Passordtest( pass ) )
		{
			JPanel ny = new KundeVindu( vindu, kunde );
			vindu.byttPanel( ny );
			return;
		}
		JOptionPane.showMessageDialog( null, "Feil passord" );
	}

	public void LogginnAnsatt( String pass )
	{
		Ansatt ansatt = register.getAnsattNummer( bruker.getText() );
		if( ansatt == null )
		{
			JOptionPane.showMessageDialog( null, "Ugyldig brukernavn" );
			return;
		}
		if( Passordtest( pass ) )
		{
			JPanel ny = new AnsattVindu( vindu, ansatt );
			vindu.byttPanel( ny );
			return;
		}
		JOptionPane.showMessageDialog( null, "Feil passord" );
	}

	/*
	public boolean Passordtest( String p )
	{
		h
	}*/



	/*private class Lytterklasse implements ActionListener
	{
		public void actionPerformed( ActionEvent e )
		{
			if( e.getSource() == logginn || e.getSource() == passord || e.getSource() == bruker )
				logginn();

			else if( e.getSource() == regKunde || e.getSource() == fNavn || e.getSource() == eNavn
			|| e.getSource() == persNr || e.getSource() == passord1 || e.getSource() == passord2
			|| e.getSource() == tlfNr )
				nyKunde();
		}
	}*/
}
