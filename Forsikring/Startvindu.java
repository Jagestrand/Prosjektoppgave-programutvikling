import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;

public class Startvindu extends JPanel
{
	private final String ADMIN = "admin", KUNDE = "kunde", ANSATT = "ansatt"; //default brukernavn
	private final String passordetliksom;	//default passord
	//private String adminPass;
	private Huvudvindu vindu;
	private JLabel brukerLabel, passLabel, fNavnLabel, eNavnLabel, passord1Label, passord2Label, tlfNrLabel, persNrLabel, adrLabel, postLabel, stedLabel;
	private JTextField bruker, passord, fNavn, eNavn, persNr, passord1, passord2, tlfNr, adress, postnr, posted;
	private JButton logginn, registrer, regKunde, avbryt;
	private Lytterklasse lytter;
	private Register register;
	private JTextArea info;
	private JPanel kant, kant2, flow, inputFelt, loginFelt, knappeFelt, pNrFelt, tlfFelt, adrFelt, postFelt, regNavnFelt, regPassFelt;

	public Startvindu( Huvudvindu v )
	{
		vindu = v;
		passordetliksom = "123";
		Font font1 = new Font("SansSerif", Font.BOLD, 15);		//standard font for felter
		URL loggbilde = Startvindu.class.getResource("DAFF Forsikring");	//bakgrunnsbilde

		lytter = new Lytterklasse();
		register = v.getRegister();

		//Info-felt
		info = new JTextArea( "Velkommen!\nSkriv inn ditt brukernavn og passord. Hvis du ikke har bruker kan du registrere deg." );

		info.setEditable( false );
		info.setLineWrap( true );
		info.setWrapStyleWord( true );

		//Innloggingspanelet
		bruker = new JTextField( 11 );
		bruker.setFont(font1);
		passord = new JPasswordField( 11 );
		passord.setFont(font1);

		brukerLabel = new JLabel( "Brukernavn" );
		brukerLabel.setFont(font1);
		passLabel = new JLabel( "Passord" );
		passLabel.setFont(font1);
		logginn = new JButton( "Logg inn" );
		logginn.setSize(10, 10);
		registrer = new JButton( "Registrer bruker" );
		bruker.addActionListener( lytter );
		passord.addActionListener( lytter );
		logginn.addActionListener( lytter );
		registrer.addActionListener( lytter );

		GridLayout gridlayout = new GridLayout(6,1);
		gridlayout.setVgap(10);
		inputFelt = new JPanel( gridlayout );

        inputFelt.setPreferredSize(new Dimension(100, 100));
        inputFelt.setMaximumSize(new Dimension(100, 100));
		kant = new JPanel( new BorderLayout(10,10) );
        kant.setPreferredSize(new Dimension(300, 300));
        kant.setMaximumSize(new Dimension(300, 300));

		inputFelt.add( brukerLabel, BorderLayout.CENTER );
		inputFelt.add( bruker );
		inputFelt.add( passLabel );
		inputFelt.add( passord );
		inputFelt.add( logginn );
		inputFelt.add( registrer );
		inputFelt.setBorder(new EmptyBorder(new Insets(20, 40, 40, 40)));
		inputFelt.setBackground(Color.gray);

		kant.setBorder( BorderFactory.createLineBorder(Color.black) );
		kant.add( inputFelt, BorderLayout.CENTER );
		kant.setVisible( true );
		//Slutt på innloggingspanel

		//Registreringspanelet:
		fNavn = new JTextField( 20 );
		fNavn.setFont(font1);
		eNavn = new JTextField( 20 );
		eNavn.setFont(font1);
		persNr = new JTextField( 11 );
		persNr.setFont(font1);
		tlfNr = new JTextField ( 20 );
		tlfNr.setFont(font1);
		adress = new JTextField( 20 );
		adress.setFont(font1);
		postnr = new JTextField( 20 );
		postnr.setFont(font1);
		posted = new JTextField( 20 );
		posted.setFont(font1);
		passord1 = new JPasswordField( 20 );
		passord1.setFont(font1);
		passord2 = new JPasswordField( 20 );
		passord2.setFont(font1);

		fNavnLabel = new JLabel( "Fornavn:" );
		fNavnLabel.setFont(font1);
		eNavnLabel = new JLabel( "Etternavn:" );
		eNavnLabel.setFont(font1);
		persNrLabel = new JLabel( "Personnummer:" );
		persNrLabel.setFont(font1);
		tlfNrLabel = new JLabel( "Telefonnummer:" );
		tlfNrLabel.setFont(font1);
		adrLabel = new JLabel( "Adresse:" );
		adrLabel.setFont(font1);
		postLabel = new JLabel( "Postnr:" );
		postLabel.setFont(font1);
		stedLabel = new JLabel( "Poststed:" );
		stedLabel.setFont(font1);
		passord1Label = new JLabel( "Passord:" );
		passord1Label.setFont(font1);
		passord2Label = new JLabel( "Gjenta passord:" );
		passord2Label.setFont(font1);

		regKunde = new JButton( "Registrer bruker" );
		regKunde.setSize(10,10);
		avbryt = new JButton( "Avbryt" );
		avbryt.setSize(10,10);

		fNavn.addActionListener( lytter );
		eNavn.addActionListener( lytter );
		persNr.addActionListener( lytter );
		passord1.addActionListener( lytter );
		passord2.addActionListener( lytter );
		tlfNr.addActionListener( lytter );
		adress.addActionListener( lytter );
		postnr.addActionListener( lytter );
		posted.addActionListener( lytter );
		regKunde.addActionListener( lytter );
		avbryt.addActionListener( lytter );

		regNavnFelt = new JPanel( new GridLayout( 2, 2 ) );
		regNavnFelt.setBackground(Color.gray);
		pNrFelt = new JPanel( new GridLayout( 2, 1 ) );
		pNrFelt.setBackground(Color.gray);
		tlfFelt = new JPanel( new GridLayout( 2, 1 ) );
		tlfFelt.setBackground(Color.gray);
		adrFelt = new JPanel( new GridLayout( 2, 1 ) );
		adrFelt.setBackground(Color.gray);
		postFelt = new JPanel( new GridLayout( 2, 2 ) );
		postFelt.setBackground(Color.gray);
		regPassFelt = new JPanel( new GridLayout( 2, 2 ) );
		regPassFelt.setBackground(Color.gray);
		GridLayout gridlayout1 = new GridLayout( 1, 2 );
		gridlayout1.setVgap(10);
		knappeFelt = new JPanel( gridlayout1 );
		knappeFelt.setBackground(Color.gray);

		GridLayout gridlayout2 = new GridLayout(7,1);
		gridlayout2.setVgap(10);
		JPanel gridReg = new JPanel(gridlayout2 );
		gridReg.setPreferredSize( new Dimension(100,100));
		gridReg.setMaximumSize(new Dimension(100,100));

		regNavnFelt.add(fNavnLabel);
		regNavnFelt.add(eNavnLabel);
		regNavnFelt.add(fNavn);
		regNavnFelt.add(eNavn);

		regPassFelt.add(passord1Label);
		regPassFelt.add(passord2Label);
		regPassFelt.add(passord1);
		regPassFelt.add(passord2);

		pNrFelt.add(persNrLabel);
		pNrFelt.add(persNr);
		tlfFelt.add(tlfNrLabel);
		tlfFelt.add(tlfNr);
		adrFelt.add(adrLabel);
		adrFelt.add(adress);
		postFelt.add(postLabel);
		postFelt.add(stedLabel);
		postFelt.add(postnr);
		postFelt.add(posted);

		knappeFelt.add(regKunde);
		knappeFelt.add(avbryt);

		gridReg.add( regNavnFelt, BorderLayout.CENTER );
		gridReg.add( pNrFelt, BorderLayout.CENTER );
		gridReg.add( tlfFelt, BorderLayout.CENTER );
		gridReg.add( adrFelt, BorderLayout.CENTER );
		gridReg.add( postFelt, BorderLayout.CENTER );
		gridReg.add( regPassFelt, BorderLayout.CENTER );
		gridReg.add( knappeFelt, BorderLayout.CENTER );
		gridReg.setBorder(new EmptyBorder(new Insets(20,40,40,40)));
		gridReg.setBackground(Color.gray);

		kant2 = new JPanel( new BorderLayout(10,10) );
        kant2.setPreferredSize(new Dimension(480, 480));
        kant2.setMaximumSize(new Dimension(480, 480));
		kant2.setBorder( BorderFactory.createLineBorder(Color.black) );
		kant2.add( gridReg, BorderLayout.CENTER );
		kant2.setVisible(false);
		//Slutt på registreringspanelet

		JPanel flyt = new JPanel( new FlowLayout( FlowLayout.CENTER ) );
		flyt.setBorder(new EmptyBorder(new Insets(200, 0, 0, 0)));
		flyt.add( kant );
		flyt.add( kant2 );

		setLayout( new BorderLayout() );
		add(info, BorderLayout.PAGE_START );
		add( new JScrollPane(flyt), BorderLayout.CENTER );
		flyt.setBackground(Color.white);
	}

	public JFrame getHovedVindu()
	{
		return vindu;
	}

	private void visFeilmelding( String melding )	//mal for feilmelding
	{
		//Standard for feilmelding
		JOptionPane.showMessageDialog(this, melding, "Problem", JOptionPane.ERROR_MESSAGE);
	}

	private void slettFelter()	//denne setter alle felter tomme
	{
		bruker.setText( "" );
		passord.setText( "" );
		fNavn.setText( "" );
		eNavn.setText( "" );
		persNr.setText( "" );
		passord1.setText( "" );
		passord2.setText( "" );
		tlfNr.setText( "" );
		adress.setText( "" );
		postnr.setText( "" );
		posted.setText( "" );
	}

	public void logginn()	//sorterer til hvilken innloggingsmetode som skal brukes
	{
		String pass = passord.getText();
		String bruk = bruker.getText();

		if(bruk.equals("") || pass.equals(""))
			visFeilmelding("Begge felter må fylles ut");

		else if( bruk.toLowerCase().equals( ADMIN ) )
			LogginnAdmin( pass );

		else if( bruk.toLowerCase().equals( KUNDE ) || bruk.length() == 11 )
			LogginnKunde( bruk, pass );

		else
			LogginnAnsatt( bruk, pass );
	}

	public void nyKunde()	//registrerer ny kunde
	{
		String fon, etn, penr, pass, telf, adrrr, pnr, ps, regexPattern;
		regexPattern = "(0[1-9]|[12][0-9]|3[01])(0[1-9]|1[012])(\\d{7})";
		fon = fNavn.getText();
		etn = eNavn.getText();
		penr = persNr.getText();
		telf = tlfNr.getText();
		adrrr = adress.getText();
		pnr = postnr.getText();
		ps = posted.getText();


		pass = passord1.getText();
		if(!pass.equals(passord2.getText() ) )
			visFeilmelding("Passordene må være like");
		else if(fon.equals("") || etn.equals("") || penr.equals("") || telf.equals("") || adrrr.equals("") || pnr.equals("") || ps.equals("") || pass.equals("") )
			visFeilmelding("Alle feltene må fylles ut");
		else if(!penr.matches(regexPattern) )
			visFeilmelding("Personnummeret er ikke gyldig");
		else if(!(register.getKundeViaNummer(penr) == null) )
			visFeilmelding("Dette personnummeret er allerede brukt");
		else if(pnr.length() != 4)
			visFeilmelding("Ugyldig postnr");
		else
		{
			Kunde asa = new Kunde(fon, etn, penr, telf, adrrr, pnr, ps, pass);
			register.nyKunde(asa);
			JOptionPane.showMessageDialog(null, "Du er registrert" );
			kant2.setVisible(false);
			kant.setVisible(true);
			slettFelter();
		}
	}

	public void LogginnAdmin( String pass )	//logger inn admin
	{
		if( Passordtest( pass ) )
		{
			JPanel ny = new AdminGUI( vindu );
			vindu.swapPanel( ny );
			slettFelter();
			return;
		}
		visFeilmelding("Feil passord" );
	}

	public void LogginnKunde( String bruk, String pass )	//logger inn kunde
	{
		String brk = bruk;
		String pwa = pass;
		if( bruk.toLowerCase().equals( KUNDE ) )
		{
			if(Passordtest(pwa))
			{
				JPanel ny = new KundeGUI(vindu, null);
				vindu.swapPanel( ny );
				slettFelter();
			}
		}
		else if( brk.length() == 11 )
		{
			Kunde kunn = register.getKundeViaNummer(brk);
			if( kunn != null )
			{
				if(Passordtest(kunn, pwa))
				{
					JPanel ny = new KundeGUI(vindu, kunn);
					vindu.swapPanel( ny );
					slettFelter();
				}
				else
				{
					visFeilmelding("Feil passord!");
					return;
				}
			}
			else
			{
				visFeilmelding("Feil brukernavn!");
				return;
			}
		}
		else
			visFeilmelding("Feil brukernavn");
	}


	public void LogginnAnsatt( String bruk, String pass )	//logger inn ansatt
	{
		String brk = bruk;
		String pwa = pass;
		if( bruk.toLowerCase().equals( ANSATT ) )
		{
			if(Passordtest(pwa))
			{
				JPanel ny = new AnsattVindu(vindu);
				vindu.swapPanel( ny );
				slettFelter();
			}
		}

		else if( brk.length() == 5 )
		{
			if( register.getAnsattViaAnsattNr(brk) != null )
			{
				Ansatt ansatt = register.getAnsattViaAnsattNr(brk);
				if(ansatt.getAktiv())
				{
					if(Passordtest(ansatt, pwa))
					{
						JPanel ny = new AnsattVindu(vindu);
						vindu.swapPanel( ny );
						slettFelter();
					}
					else
					{
						visFeilmelding("Feil passord");
						return;
					}
				}
				else
				{
					visFeilmelding("Brukeren er deaktivert");
					return;
				}
			}
			else
			{
				visFeilmelding("Feil brukernavn");
				return;
			}
		}
		else
			visFeilmelding("Feil brukernavn");
	}


	public boolean Passordtest( String p )		//sjekker om passorder er standardpassordet
	{
		String pwo = p;
		if( pwo.equals(passordetliksom) )
		{
			return true;
		}
		return false;
	}

	public boolean Passordtest( Ansatt a, String p )	//sjekker om det er riktig passord for ansatt-bruker
	{
		if( a.getPassord().equals(p) )
		{
			return true;
		}
		return false;
	}

	public boolean Passordtest( Kunde k, String p )		//sjekker om det er riktig passord for kunde-bruker
	{
		if( k.getPassord().equals(p) )
		{
			return true;
		}
		return false;
	}

	private class Lytterklasse implements ActionListener	//bestemmer hva knappene gjør
	{
		public void actionPerformed( ActionEvent e )
		{
			if( e.getSource() == logginn || e.getSource() == passord
					|| e.getSource() == bruker )	//utfører innlogging
				logginn();

			else if( e.getSource() == registrer )	//bytter fra innlogging til registrering
			{
				kant.setVisible(false);
				kant2.setVisible(true);
			}

			else if( e.getSource() == regKunde || e.getSource() == fNavn || e.getSource() == eNavn
			|| e.getSource() == persNr || e.getSource() == adress || e.getSource() == postnr
			|| e.getSource() == posted || e.getSource() == passord1 || e.getSource() == passord2
			|| e.getSource() == tlfNr )	//registrerer ny kunde
				nyKunde();

			else if( e.getSource() == avbryt )	//avbryter registrering og bytter tilbake til innlogging
			{
				kant2.setVisible(false);
				kant.setVisible(true);
			}
		}
	}
}
