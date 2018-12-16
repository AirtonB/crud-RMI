import java.rmi.*;
public class StartServidor
/**
 *
 * @author leandro
 */
{
    public static void main(String argv[])
    {
        try
        {
            System.out.println("Iniciando servidor...");
            Naming.rebind("Servidor", new ServidorRMI());
        }
        catch (Exception e)
        {
            System.out.println("Ocorreu um problema na inicialização do servidor.\n");
            e.printStackTrace();
        }
    }
}