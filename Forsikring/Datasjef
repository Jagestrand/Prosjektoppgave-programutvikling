import java.io.*;

public class Datasjef implements Serializable
{
	private static final long serialVersionUID = 42L;
	StringBuilder readWriteError;
	boolean error;

	public Datasjef()
	{
		readWriteError = new StringBuilder(70);
		error = false;
	}

	public Register lesRegisterFil()
	{
		// Leser fra fil.
		Register register;
		try (ObjectInputStream registerFile = new ObjectInputStream( new FileInputStream( "data/register.dtabse" ))){
			register = (Register) registerFile.readObject();
			//register.setCurrentPrescriptionNumber();
			return register;
		}
		catch(ClassNotFoundException cnfe) {
			System.out.println("Error: Cannot find class Register.\n");
		    error = true;
		}
		catch(FileNotFoundException fne) {
		    checkForFolder();
		    register = new Register(this);
		    skrivRegister(register);
		    return register;
		}
		catch(IOException ioe) {
			error = true;
		   	readWriteError.append("Finner korrupt data hos registret. Slett register.dtabse for å løse problemet.\n");
		}
		return null;
	}


	public void skrivRegister(Register registerPara)
	{
		// Skriver til fil
		try (ObjectOutputStream writeRegister = new ObjectOutputStream(new FileOutputStream("data/register.dtabse"))) {
		      writeRegister.writeObject(registerPara);
		}
		catch( NotSerializableException nse ) {
			error = true;
		    readWriteError.append("Registeret er ikke serialisert.\n\n");
		}
		catch( IOException ioe ) {
			error = true;
		    readWriteError.append("Feil ved skriving av filen register.dtabse.\n\n");
		}
	}

	public void checkForFolder()
	{
		// Sjekker om data mappe eksisterer
		File checkFolder = new File("data");
		if(!checkFolder.exists())
		{
			checkFolder.mkdir();
		}
	}

	public String errorMessage()
	{
		error = false;
		return readWriteError.toString();
	}

	public boolean getError()
	{
		return error;
	}
}
