package Recursos;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

import Trabalhadores.*;

public class Recursos {
	private int position = 0;
	private ArrayList<Funcionario> funcionario = new ArrayList<Funcionario>();
	private ArrayList<ArrayList<Funcionario>> stats = new ArrayList<ArrayList<Funcionario>>();
	private ArrayList<String> logs = new ArrayList<String>();
	private SimpleDateFormat ft = new SimpleDateFormat("dd.MM.yyyy");
	private Date data;
	private Scanner scan = new Scanner(System.in);
	private int id = 0;
	private boolean loop = true;
	@SuppressWarnings("unused")
	private double salario;

	public Recursos(Date data) {
		super();
		this.data = data;
	}

	public void set(int id, Funcionario obj) {
		funcionario.set(id, obj);
	}

	public void add() {
		// TODO Auto-generated method stub
		System.out.println("Entre como nome do Funcionario");
		String nome = scan.next();
		nome += scan.nextLine();
		System.out.println("Entre como endereço do Funcionario");
		String endereco = scan.next();
		endereco += scan.nextLine();
		System.out.println("Entre como tipo do funcionario");
		System.out.println("1 - Assalariado");
		System.out.println("2 - Comissionado");
		System.out.println("3 - Horista");
		int tipo = scan.nextInt();

		while (loop) {

			if (tipo == 1) {
				loop = false;
				Assalariado assalariado = new Assalariado(id, 0, nome,
						endereco, 0, data);
				funcionario.add(assalariado);
				System.out.println("Funcionario adicionado com sucesso");
				System.out.println("Seu codigo de acesso é: " + id);
				id++;

			} else if (tipo == 2) {
				loop = false;
				Comissionado comissionado = new Comissionado(id, 0, nome,
						endereco, data, 0, 0);
				funcionario.add(comissionado);
				System.out.println("Funcionario adicionado com sucesso");
				System.out.println("Seu codigo de acesso é: " + id);
				id++;

			} else if (tipo == 3) {
				loop = false;
				Horista horista = new Horista(id, 0, nome, endereco, 0, 0);
				funcionario.add(horista);
				System.out.println("Funcionario adicionado com sucesso");
				System.out.println("Seu codigo de acesso é: " + id);
				id++;

			} else {
				System.out
						.println("Tipo não existe, por favor tente novamene.");
			}
			if (loop) {
				tipo = scan.nextInt();
			}
		}
		stats.add(funcionario);
	}

	public void remove() {
		// TODO Auto-generated method stub
		if (funcionario.isEmpty()) {
			System.out.println("Não Há funcionarios");
		} else {
			System.out.println("Lista de Funcionarios");
			for (int i = 0; i < funcionario.size(); i++) {
				if (funcionario.get(i) instanceof Comissionado) {
					Comissionado aux = (Comissionado) funcionario.get(i);
					System.out.println("Tipo: Comissionado");
					System.out.println("id: " + aux.getId());
					System.out.println("Nome: " + aux.getNome());
				} else if (funcionario.get(i) instanceof Assalariado) {
					Assalariado aux = (Assalariado) funcionario.get(i);
					System.out.println("Tipo: Assalariado");
					System.out.println("id: " + aux.getId());
					System.out.println("Nome: " + aux.getNome());
				} else if (funcionario.get(i) instanceof Horista) {
					Horista aux = (Horista) funcionario.get(i);
					System.out.println("Tipo: Horista");
					System.out.println("id: " + aux.getId());
					System.out.println("Nome: " + aux.getNome());
				}
				System.out
						.println("Entre com 'id' do Funcionario que quer remover");
				int r = scan.nextInt();
				funcionario.remove(r);
				System.out.println("Funcionario Removido com sucesso");

			}
			System.out.println("\n");
		}
		stats.add(funcionario);
	}

	public void print() {
		// TODO Auto-generated method stub
		if (funcionario.isEmpty()) {
			System.out.println("Não Há funcionarios");
		} else {
			for (int i = 0; i < funcionario.size(); i++) {
				if (funcionario.get(i) instanceof Assalariado) {
					Assalariado aux = (Assalariado) funcionario.get(i);
					System.out.println("Tipo: Assalariado");
					System.out.println(aux.toString() + "\n");

				} else if (funcionario.get(i) instanceof Horista) {
					Horista aux = (Horista) funcionario.get(i);
					System.out.println("Tipo: Horista");
					System.out.println(aux.toString() + "\n");

				} else if (funcionario.get(i) instanceof Comissionado) {
					Comissionado aux = (Comissionado) funcionario.get(i);
					System.out.println("Tipo: Comissionado");
					System.out.println(aux.toString() + "\n");

				}
			}
		}
		stats.add(funcionario);
	}

	public void folha(Date data) {
		// TODO Auto-generated method stub
		double total = 0;
		for (int i = 0; i < funcionario.size(); i++) {
			if (funcionario.get(i) instanceof Assalariado) {
				Assalariado aux = (Assalariado) funcionario.get(i);
				System.out.println(ft.format(data));
				System.out.println(aux.getData());
				if (ft.format(data) == aux.getData()) {
					System.out.println(aux.toString());
					total += aux.pagamento();
				}

			} else if (funcionario.get(i) instanceof Comissionado) {
				Comissionado aux = (Comissionado) funcionario.get(i);
				if (ft.format(data) == aux.getData()) {
					total += aux.pagamento();
					System.out.println(aux.toString());
				}

			}

		}
		if (total == 0) {
			System.out.println("Não Há pagamentos nesse dia.");
		} else {
			System.out.println("Total a pagar: " + total);
		}
		stats.add(funcionario);
	}

	public void update(int id) {
		
		System.out.println("1 - Alterar tipo");
		System.out.println("2 - Alterar nome");
		System.out.println("3 - Sindicato");
		System.out.println("4 - Alterar endereço");
		System.out.println("5 - Alterar Taxa sindical");
		int comando = scan.nextInt();
		if (comando == 1) {
			if (funcionario.get(id) instanceof Assalariado) {
				Assalariado aux = (Assalariado) funcionario.get(id);
				funcionario.set(id, aux);
				System.out.println("1 - Horista");
				System.out.println("2 - Comissionado");
				int com = scan.nextInt();
				if (com == 1){					
					Horista auxH = new Horista(aux.getId(), aux.getTaxa(), aux.getNome(), aux.getEndereco(), 0, 0);
					funcionario.set(aux.getId(), auxH);
					
				}else if (com == 2){
					Comissionado auxC = new Comissionado(aux.getId(), aux.getTaxa(), aux.getNome(), aux.getEndereco(), data, 0, 0);
				}

				
			} else if (funcionario.get(id) instanceof Horista) {
				Horista aux = (Horista) funcionario.get(id);
				funcionario.set(id, aux);
				System.out.println("1 - Assalariado");
				System.out.println("2 - Comissionado");
				int com = scan.nextInt();
				if (com == 1){					
					Horista auxH = new Horista(aux.getId(), aux.getTaxa(), aux.getNome(), aux.getEndereco(), 0, 0);
					funcionario.set(aux.getId(), auxH);
					
				}else if (com == 2){
					Comissionado auxC = new Comissionado(aux.getId(), aux.getTaxa(), aux.getNome(), aux.getEndereco(), data, 0, 0);
				}

			} else if (funcionario.get(id) instanceof Comissionado) {
				Comissionado aux = (Comissionado) funcionario.get(id);
				funcionario.set(id, aux);
				System.out.println("1 - Horista");
				System.out.println("2 - Assalariado");
				int com = scan.nextInt();
				if (com == 1){					
					Horista auxH = new Horista(aux.getId(), aux.getTaxa(), aux.getNome(), aux.getEndereco(), 0, 0);
					funcionario.set(aux.getId(), auxH);
					
				}else if (com == 2){
					Comissionado auxC = new Comissionado(aux.getId(), aux.getTaxa(), aux.getNome(), aux.getEndereco(), data, 0, 0);
				}

			}
		}
		stats.add(funcionario);

	}

	public void comprar(int idFuncionario, double valor) {

		logs.add("Nome: " + funcionario.get(idFuncionario).getNome()
				+ "\nValor vendido: " + valor);
		stats.add(funcionario);

	}

	public ArrayList<Funcionario> getFuncionario() {
		return funcionario;
	}

	public void pontoVenda() {
		System.out.println("Entre com codigo de acessso do funcionario: ");
		int idF = scan.nextInt();
		System.out.println("Entre com valor da venda");
		double valor = scan.nextDouble();
		if (funcionario.get(idF) instanceof Comissionado) {
			Comissionado aux = (Comissionado) funcionario.get(idF);
			aux.addVendas(valor);
			funcionario.set(idF, aux);

		}
		stats.add(funcionario);
	}
	
}
