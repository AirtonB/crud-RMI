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

    @Override
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
            excluirCarro(carro);
        }
    }
    @Override
    public void excluirCarro(Carro carro) {
        listaComCarros.remove(carro);
        // System.out.println("excluindo carro " + carro.getId() + "...");
        carro = null;
        // System.out.println("Carro excluido!");
    }

    @Override
    public void atualizarCarro(int id, String modelo, String marca, String placa) throws RemoteException {
        Carro carro = buscaCarro(id);
        carro.setModelo(modelo);
        carro.setMarca(marca);
        carro.setPlaca(placa);
        // System.out.println("ID DO PRODUTO BUSCADO => " +carro.getId());
        if (carro != null) {
            addCarro(carro);
        }
    }

    @Override
    public Carro buscaCarro(int id) {
        Carro carro = null;
        for (Carro car : listaComCarros) {
            if (car.getId() == id) {
                carro = car;
            }
        }
        return carro;
    }

    public void existeCarro(Carro carro) {
        if (listaComCarros.contains(carro)) {
            // System.out.println("Carro " + carro.getId() + " atualizado com sucesso!");
        } else {
            listaComCarros.add(carro);
            // System.out.println("Carro adicionado com sucesso!");
        }
    }

    @Override
    public void addCarro(Carro carro) throws RemoteException {
        try {
            if (validaCarro(carro)) {
                existeCarro(carro);
            } else {
                // System.out.println("Marca ou descrição são campos obrigatórios!!");
                carro = null;
                --i;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}