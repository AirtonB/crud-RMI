import java.rmi.*;
import java.util.*;
import java.io.*;
/**
 *
 * @author leandro
 */
public class ClienteRMI implements Serializable {
	private static final long serialVersionUID = 1L;

	public ClienteRMI() {

		System.out.println("Iniciando o Cliente...");
		try {
			interfaceRemota = (InterfaceServidorRMI) Naming.lookup("rmi://127.0.0.1/Servidor");
		} catch (Exception e) {
			System.out.println("Cliente falhou.\n" + e);
			e.printStackTrace();
			System.exit(0);
		}

	}

	public static void main(String[] args) {

		ClienteRMI main = new ClienteRMI();
		Scanner sc = new Scanner(System.in);
		int op;
		while (true) {
			main.menu();
			op = sc.nextInt();
			main.principal(op);
		}
	}

	public void menu() {
		System.out.println(
				"'                   ### MENU ### \n " + "Digite - 1  Para Adiciona Carro - 2  Para Listar Carros -"
						+ " 3  Alterar Carro - 4  Excluir Carro - 5 Sair");
	}

	public void principal(int op) {
		try {
			switch (op) {
			case 1:
				Carro carro = new Carro(0);
				Scanner sc = new Scanner(System.in);
				System.out.println("Modelo do Carro: ");
				String modelo = sc.nextLine();
				carro.setModelo(modelo);
				System.out.println("Marca do Carro: ");
				String marca = sc.nextLine();
				carro.setMarca(marca);
				System.out.println("Placa do Carro: ");
				String placa = sc.nextLine();
				carro.setPlaca(placa);
				interfaceRemota.criaCarro(carro);
				break;
			case 2:
				if (!interfaceRemota.listaDeCarros().isEmpty()) {
					System.out.println("\n|id| \t |Modelo| \t |Marca| \t |Placa|" + "\n********************************"+
					"*****************");
					for (Carro car : interfaceRemota.listaDeCarros()) {
						System.out.println("|" + car.getId() + "|" + " \t |" + car.getModelo() + "|" + " \t |"
								+ car.getMarca() + "| \t" + "|" + car.getPlaca()+"|" + "\n");
					}
				} else {
					System.out.println("Não existem carros cadastrados");
				}

				break;
			case 3:
				Scanner scan = new Scanner(System.in);
				System.out.println("Digite o ID do carro: ");
				int id = scan.nextInt();
				Scanner scans = new Scanner(System.in);
				System.out.println("Digite o Modelo: ");
				String modeloUp = scans.nextLine();
				System.out.println("Digite a Marca: ");
				String marcaUp = scans.nextLine();
				System.out.println("Digite a placa: ");
				String placaUp = scans.nextLine();

				interfaceRemota.atualizarCarro(id, modeloUp, marcaUp, placaUp);

				break;
			case 4:
				Scanner sca = new Scanner(System.in);
				System.out.println("Digite o ID do Carro para Excluir: ");
				interfaceRemota.deleteCarro(sca.nextInt());
				System.out.println("excluindo carro ...");
				break;
			case 5:
				System.out.println("Saindo...");
				System.exit(0);
			default:
				System.out.println("Opção inválida");
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private InterfaceServidorRMI interfaceRemota;
}
