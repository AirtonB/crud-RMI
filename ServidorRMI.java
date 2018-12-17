import java.rmi.*;
import java.rmi.server.*;
import java.util.*;
import java.lang.*;

/**
 *
 * @author leandro
 */
public class ServidorRMI extends UnicastRemoteObject implements InterfaceServidorRMI {

    private static final long serialVersionUID = 1L;

    protected ServidorRMI() throws RemoteException {
        System.out.println("Servidor iniciado...");

    }

    int i = 0;
    public List<Carro> listaComCarros = new ArrayList<Carro>();

    @Override
    public void criaCarro(Carro carro) throws RemoteException {
        carro.setId(++i);
        addCarro(carro);
    }

    public boolean validaCarro(Carro carro) throws RemoteException {
        return ((carro.getModelo() != null) && (carro.getMarca() != null) && !(carro.getMarca().isEmpty())
                && !(carro.getModelo().isEmpty() && (carro.getPlaca() != null) && (carro.getPlaca().isEmpty())));
    }

    @Override
    public List<Carro> listaDeCarros() throws RemoteException {
        if (!listaComCarros.isEmpty()) {
            return listaComCarros;
        }
        return listaComCarros;
    }

    @Override
    public void deleteCarro(int id) throws RemoteException {
        Carro carro = buscaCarro(id);
        if (carro != null) {
            listaComCarros.remove(carro);
        }
    }

    @Override
    public void atualizarCarro(int id, String modelo, String marca, String placa) throws RemoteException {
        Carro carro = buscaCarro(id);
        carro.setModelo(modelo);
        carro.setMarca(marca);
        carro.setPlaca(placa);
        if (carro != null) {
            addCarro(carro);
        }
    }

    public Carro buscaCarro(int id) {
        Carro carro = null;
        for (Carro car : listaComCarros) {
            if (car.getId() == id) {
                carro = car;
            }
        }
        return carro;
    }

    @Override
    public void addCarro(Carro carro) throws RemoteException {
        try {
            if (validaCarro(carro)) {
                if (!listaComCarros.contains(carro)) {
                    listaComCarros.add(carro);
                }
            } else {
                carro = null;
                --i;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Inicializador do servidor
    public static void main(String argv[]) {
        try {
            System.out.println("Iniciando servidor...");
            Naming.rebind("Servidor", new ServidorRMI());
        } catch (Exception e) {
            System.out.println("Ocorreu um problema na inicialização do servidor.\n");
            e.printStackTrace();
        }
    }

}