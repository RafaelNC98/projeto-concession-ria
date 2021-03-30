import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
	private CarroN_SQL dao = new CarroN_SQL();
    private CarroS_SQL da = new CarroS_SQL();
    private Cliente_SQL cao = new Cliente_SQL();
	public static void main(String[] args) {
		Menu menuc = new Menu();
		menuc.menuConcessionaria();
	}

	private void menuConcessionaria() {
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("Concessionaria");
			System.out.println("1 - Listar Carros Zero Km");
			System.out.println("2 - Incluir Carro Zero Km");
			System.out.println("3 - Efetuar Venda Carro ZeroKm");
			System.out.println("4 - Exibir informações CarroZeroKm");
			System.out.println("5 - Listar Carro SemiNovos");
			System.out.println("6 - Incluir CarroSemiNovo");
			System.out.println("7 - Efetuar Venda de Seminovo");
			System.out.println("8 - Exibir Informaçoes de Carro SemiNovo");
			System.out.println("9 - Listar Clientes");
			System.out.println("10 - Incluir Cliente");
			System.out.println("11 - Excluir Cliente");
			System.out.println("0 - Sair");
			System.out.println("--------------------------");
			try {
				String opcao = sc.nextLine();
				if (opcao.equals("1")) {
					listarCarN();
				} else if (opcao.equals("2")) {
					incluirCarN(sc);
				}
					else if (opcao.equals("3")) {
						Vendacarn(sc);		
				} else if (opcao.equals("4")) {
					exibirCarN(sc);
				} else if (opcao.equals("5")) {
					listarCarS();
				} 
				else if (opcao.equals("6")) {
					incluirCarS(sc);
				} 
				else if (opcao.equals("7")) {
					Vendacars(sc);
				} 
				else if (opcao.equals("8")) {
					exibirCarS(sc);
				}
				else if (opcao.equals("9")) {
					listarCliente();
				}
				else if (opcao.equals("10")) {
					incluirCliente(sc);
				}
				else if (opcao.equals("11")) {
					excluirCliente(sc);
				}
				else if (opcao.equals("0")) {
					break;
				} else {
					System.out.println("Opção não reconhecida");
				}
			} catch (Exception ex) {
				System.out.println("Erro ao executar:" + ex.getMessage());
				ex.printStackTrace();
			}
		}
		sc.close();
	}

	private void exibirCarN(Scanner sc) throws Exception {
		System.out.println("Exibir informações do Carro");

		System.out.println("id:");
		int id = sc.nextInt();
		sc.nextLine();
		CarroN cn = dao.getCarroN(id);
		if (cn == null) {
			System.out.println("Carro não encontrado");
			return;
		}
		System.out.println("ID:" + cn.getId());
		System.out.println("Modelo:" + cn.getModelo());
		System.out.println("Marca:" + cn.getMarca());
		System.out.println("Ano:" + cn.getAno());
		System.out.println("Preco:" + cn.getPreco());

	}

	private void incluirCarN(Scanner sc) throws Exception {
		System.out.println("Incluir Carro");
		System.out.println("ID:");
		int id = sc.nextInt();
		sc.nextLine();
		System.out.println("Modelo:");
		String modelo = sc.nextLine();
		System.out.println("Marca:");
		String marca = sc.nextLine();
		
		System.out.println("Ano:");
		int ano = sc.nextInt();
		sc.nextLine();
		System.out.println("Preco:");
	String preco = sc.nextLine();

		CarroN cn = new CarroN();
		cn.setId(id);
		cn.setModelo(modelo);
		cn.setMarca(marca);
		cn.setAno(ano);
		cn.setPreco(preco);


		dao.inserir(cn);
	}

	private void Vendacarn(Scanner sc) throws Exception {
		System.out.println("Efetuar Venda");
		System.out.println("Código:");
		int id = sc.nextInt();
		sc.nextLine();

		CarroN cn = dao.getCarroN(id);
		if (cn == null) {
			System.out.println("Carro nao Encontrado.");
			return;
		}
		System.out.println("Forma de Pagamento:");
		String pagamento = sc.nextLine();
		System.out.println("Venda Concluida Com Sucesso!");
		dao.excluir(cn);
	}

	private void listarCarN() throws Exception {
		List<CarroN> list = dao.listAll();
		for (CarroN cn : list) {
			System.out.println("ID:" + cn.getId()+
					"\nModelo:" + cn.getModelo()+
					"\nMarca:"+cn.getMarca()+
					"\nAno:" + cn.getAno() +
		            "\nPreco:"+cn.getPreco());
			System.out.println("----------------------");
		}
	}
	
	private void exibirCarS(Scanner sc) throws Exception {
		System.out.println("Exibir informações do Carro");

		System.out.println("id:");
		int id = sc.nextInt();
		sc.nextLine();
		CarroS cs = da.getCarroS(id);
		if (cs == null) {
			System.out.println("Carro não encontrado");
			return;
		}
		System.out.println("ID:" + cs.getId());
		System.out.println("Modelo:" + cs.getModelo());
		System.out.println("Marca:" + cs.getMarca());
		System.out.println("Ano:" + cs.getAno());
		System.out.println("Preco:" + cs.getPreco());

	}
	
	private void incluirCarS(Scanner sc) throws Exception {
		System.out.println("Incluir Carro Semi-Novo");
		System.out.println("ID:");
		int id = sc.nextInt();
		sc.nextLine();
		System.out.println("Modelo:");
		String modelo = sc.nextLine();
		System.out.println("Marca:");
		String marca = sc.nextLine();
		
		System.out.println("Ano:");
		int ano = sc.nextInt();
		sc.nextLine();
		System.out.println("Preco:");
	String preco = sc.nextLine();

		CarroS cs = new CarroS();
		cs.setId(id);
		cs.setModelo(modelo);
		cs.setMarca(marca);
		cs.setAno(ano);
		cs.setPreco(preco);


		da.inserir(cs);
	}

	private void Vendacars(Scanner sc) throws Exception {
		System.out.println("Efetuar Venda");
		System.out.println("ID:");
		int id = sc.nextInt();
		sc.nextLine();

		CarroS cs = da.getCarroS(id);
		if (cs == null) {
			System.out.println("Carro nao Encontrado.");
			return;
		}
		System.out.println("Forma de Pagamento:");
		String pagamento = sc.nextLine();
		System.out.println("Venda Concluida Com Sucesso!");
		da.excluir(cs);
	}
	
	private void listarCarS() throws Exception {
		List<CarroS> list = da.listAll();
		for (CarroS cs : list) {
			System.out.println("ID:" + cs.getId()+
					"\nModelo:" + cs.getModelo()+
					"\nMarca:"+cs.getMarca()+
					"\nAno:" + cs.getAno() +
		            "\nPreco:"+cs.getPreco());
			System.out.println("----------------------");
		}
	}
	
	private void listarCliente() throws Exception {
		List<Cliente> list = cao.listAll();
		for (Cliente clt : list) {
			System.out.println("CPF:" + clt.getCpf()+
					"\nNome:" + clt.getNome()+
					"\nNascimento:"+clt.getNascimento());
			System.out.println("----------------------");
		}
	}
	
	private void incluirCliente(Scanner sc) throws Exception {
		System.out.println("Incluir Cliente");
		System.out.println("CPF:");
		String cpf = sc.nextLine();
		sc.nextLine();
		System.out.println("Nome:");
		String nome = sc.nextLine();
		System.out.println("Nascimento:");
		String nascimento = sc.nextLine();
		
		Cliente clt = new Cliente();
		clt.setCpf(cpf);
		clt.setNome(nome);
		clt.setNascimento(nascimento);


		cao.inserirCliente(clt);
	}
	
	private void excluirCliente(Scanner sc) throws Exception {
		System.out.println("Excluir Cliente");
		System.out.println("CPF:");
		String cpf = sc.nextLine();
		sc.nextLine();

		Cliente clit = cao.getCliente(cpf);
		if (clit == null) {
			System.out.println("Cliente nao Encontrado.");
			return;
		}
		System.out.println("Confirme sua escolha");
		System.out.println("1 - Sim");
		System.out.println("2 - Não");
		int opc = sc.nextInt();
		if(opc==1){
		System.out.println("Venda Concluida Com Sucesso!");
		cao.excluirCliente(clit);
		}
		else{
			return;
		}
		
	}

}


