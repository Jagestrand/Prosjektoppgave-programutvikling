import java.awt.*;

public class PrøveStartVindu
{
	public static void main( String[] args )
	{
		EventQueue.invokeLater( new Runnable(){
			public void run()
			{
				Datasjef data = new Datasjef();
				Register reg = data.lesRegisterFil();
				Huvudvindu vindu = new Huvudvindu(reg);

				vindu.setVisible( true );
				//vindu.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
			}
		});
	}
}
