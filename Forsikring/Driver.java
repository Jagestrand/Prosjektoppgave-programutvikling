import java.awt.*;

public class Driver
{
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable(){
            public void run()
            {

                Huvudvindu window = new Huvudvindu();
            }
        });
    }
}