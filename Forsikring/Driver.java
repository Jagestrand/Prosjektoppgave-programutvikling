import java.awt.*;

public class Driver
{
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable(){
            public void run()
            {
            	Datasjef data = new Datasjef();
            	Register reg = data.lesRegisterFil();
                Huvudvindu vindu = new Huvudvindu(reg);
            }
        });
    }
}