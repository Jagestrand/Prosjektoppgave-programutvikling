import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Startvindu extends JPanel
{
	private final String ADMIN = "admin", KUNDE = "kunde", ANSATT = "ansatt", ADMIN_PASS = "123456";
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
		vindu = v;
		passordetliksom = "123";

		bruker = new JTextField( 10 );
		passord = new JPasswordField( 10 );

		lytter = new Lytterklasse();
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

		JPanel inputFelt = new JPanel( new GridLayout(1,3));
		JPanel labelFelt = new JPanel( new GridLayout(1,2));
		JPanel kant = new JPanel( new BorderLayout() );
		JPanel flyt = new JPanel( new FlowLayout( FlowLayout.LEADING ) );	//CENTER
		labelFelt.add( brukerLabel );
		inputFelt.add( bruker );
		labelFelt.add( passLabel );
		inputFelt.add( passord );
		inputFelt.add( logginn );

		setLayout( new BorderLayout() );
		add(info, BorderLayout.PAGE_START );
		kant.add( labelFelt, BorderLayout.CENTER );
		kant.add( inputFelt, BorderLayout.PAGE_END );
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

	/*
	public void logginn()
	{
		String pass = passord.getText();
		JOptionPane.showMessageDialog( null, "Du er logget inn" );
		LogginnAnsatt(pass);
	}*/

	public void logginn()
	{
		String pass = passord.getText();
		String bruk = bruker.getText();

		if( bruk.toLowerCase().equals( ADMIN ) )
			LogginnAdmin( pass );

		else if( bruk.toLowerCase().equals( KUNDE ) )
			LogginnKunde( pass );

		else
			LogginnAnsatt( bruk, pass );
	}


	/*
	public void LogginnAdmin( String pass )
	{
		String pwa = pass;
		JOptionPane.showMessageDialog( null, "Du er logget inn" );
	}*/

	/*public void LogginnAdmin( String pass )
	{
		String pwa = pass;
		JPanel ny = new AdminGUI(vindu);
		vindu.swapPanel( ny );
	}*/


	public void LogginnAdmin( String pass )
	{
		if( Passordtest( pass ) )
		{
			JPanel ny = new AdminGUI( vindu );
			vindu.swapPanel( ny );
			return;
		}
		JOptionPane.showMessageDialog( null, "Feil passord" );
	}

	public void LogginnKunde( String pass )
	{
		String pwa = pass;
		JOptionPane.showMessageDialog( null, "Du er logget inn" );
	}


	/*
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
			vindu.swapPanel( ny );
			return;
		}
		JOptionPane.showMessageDialog( null, "Feil passord" );
	}*/

	public void LogginnAnsatt( String bruk, String pass )
	{
		String brk = bruk;
		String pwa = pass;
		if( bruk.toLowerCase().equals( ANSATT ) )
		{
			//JOptionPane.showMessageDialog( null, "Du er logget inn" );
			JPanel ny = new AnsattVindu(vindu);
			vindu.swapPanel( ny );
		}
		else
			JOptionPane.showMessageDialog(null, "Feil brukernavn");
	}


	/*
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
			vindu.swapPanel( ny );
			return;
		}
		JOptionPane.showMessageDialog( null, "Feil passord" );
	}*/

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
