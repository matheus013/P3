package Recursos;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;
import Trabalhadores.*;

public class Recursos {

	ArrayList<Funcionario> funcionario = new ArrayList<Funcionario>();
	ArrayList<String> logs = new ArrayList<String>();
	private SimpleDateFormat ft = new SimpleDateFormat("dd.MM.yyyy");
	Date data;
	Scanner scan = new Scanner(System.in);
	int id = 0;
	boolean loop = true;
	double salario;

	public Recursos(Date data) {
		super();
		this.data = data;
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
	}

	public void update(int id) {

		if (funcionario.get(id) instanceof Assalariado) {
			Assalariado aux = (Assalariado) funcionario.get(id);
			System.out.println("Tipo: Assalariado");
			System.out.println(aux.toString());
			funcionario.set(id, aux);

		} else if (funcionario.get(id) instanceof Horista) {
			Horista aux = (Horista) funcionario.get(id);
			System.out.println("Tipo: Horista");
			System.out.println(aux.toString());

			funcionario.set(id, aux);
		} else if (funcionario.get(id) instanceof Comissionado) {
			Comissionado aux = (Comissionado) funcionario.get(id);
			System.out.println("Tipo: Comissionado");
			System.out.println(aux.toString());

			funcionario.set(id, aux);

		}

	}

	public void comprar(int idFuncionario, double valor) {

		logs.add("Nome: " + funcionario.get(idFuncionario).getNome()
				+ "\nValor vendido: " + valor);

	}

	public ArrayList<Funcionario> getFuncionario() {
		return funcionario;
	}

}
