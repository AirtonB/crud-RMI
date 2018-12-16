import java.rmi.*;
import java.util.*;
/**
 *
 * @author leandro
 */
public interface InterfaceServidorRMI extends Remote
{
    public void criaCarro(Carro carro) throws RemoteException;
    public boolean validaCarro(Carro carro) throws RemoteException;
    public List<Carro> listaDeCarros() throws RemoteException;
    public void deleteCarro(int id) throws RemoteException;
    public void atualizarCarro(int id, String a, String b, String c) throws RemoteException;
    public void addCarro(Carro carro) throws RemoteException;
    public Carro buscaCarro(int id) throws RemoteException;
    public void excluirCarro(Carro carro) throws RemoteException;
} 