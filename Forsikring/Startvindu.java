import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Startvindu extends JPanel
{
	private final String ADMIN = "admin", ADMIN_PASS = "123456";
	private final String passordetliksom;
	private String adminPass;
	private Huvudvindu vindu;	//HovedVindu
	private JLabel brukerLabel, passLabel, fNavnLabel, eNavnLabel, passord1Label, passord2Label, tilfNrLabel, persNrLabel;
	private JTextField bruker, passord, fNavn, eNavn, persNr, passord1, passord2, tlfNr;
	private JButton logginn, regKunde;
	private Lytterklasse lytter;  //ButtonListener
	private Register register;
	private JTextArea info;
	private JPanel flow, loginFelt, regFelt, regNavnFelt, regPassFelt;

	public Startvindu( Huvudvindu v )
	{
		passordetliksom = "123";
		
		bruker = new JTextField( 10 );
		passord = new JTextField( 10 );

		lytter = new Lytterklasse();
		vindu = v;
		register = v.getRegister();

		info = new JTextArea( "Velkommen!\nSkriv inn ditt brukernavn og passord. Hvis du ikke har bruker kan du registrere deg." );

		info.setEditable( false );
		info.setLineWrap( true );
		info.setWrapStyleWord( true );
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
		kant.setBorder( BorderFactory.createTitledBorder("Logginn" ) );
	}

	public JFrame getHovedVindu()
	{
		return vindu;
	}

	/*public void logginn()
	{
		String pass = passord.getText();
		String bruk = bruker.getText();

		if( bruk.toLowerCase().equals( ADMIN ) )
			LogginnAdmin( pass );

		else if( bruk.matches("[0-9]+") && bruk.length() == 11 )
			LogginnKunde( pass );

		else
			LogginnAnsatt( pass );
	}*/
	
	public void logginn()
	{
		JOptionPane.showMessageDialog( null, "Du er logget inn" );
	}

	/*public void LogginnAdmin( String pass )
	{
		if( Passordtest( pass ) )
		{
			JPanel ny = new AdminVindu( vindu );
			vindu.byttPanel( ny );
			return;
		}
		JOptionPane.showMessageDialog( null, "Feil passord" );
	}*/
	
	public void LogginnAdmin( String pass )
	{
		String pwa = pass;
		JOptionPane.showMessageDialog( null, "Du er logget inn" );
	}

	/*public void LogginnKunde( String pass )
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
	}*/
	
	public void LogginnKunde( String pass )
	{
		String pwa = pass;
		JOptionPane.showMessageDialog( null, "Du er logget inn" );
	}

	/*public void LogginnAnsatt( String pass )
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
	}*/
	
	public void LogginnAnsatt( String pass )
	{
		String pwa = pass;
		JOptionPane.showMessageDialog( null, "Du er logget inn" );
	}

	
	public boolean Passordtest( String p )
	{
		String pwo = p;
		if( pwo.equals(passordetliksom) )
		{
			return true;
		}
		return false;
	}



	private class Lytterklasse implements ActionListener
	{
		public void actionPerformed( ActionEvent e )
		{
			if( e.getSource() == logginn || e.getSource() == passord || e.getSource() == bruker )
				logginn();

			/*
			else if( e.getSource() == regKunde || e.getSource() == fNavn || e.getSource() == eNavn
			|| e.getSource() == persNr || e.getSource() == passord1 || e.getSource() == passord2
			|| e.getSource() == tlfNr )
				nyKunde();*/
		}
	}
}
