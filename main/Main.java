package main;

import java.sql.Date;
import java.util.Calendar;
import java.util.Scanner;

import Recursos.*;
import Trabalhadores.*;


public class Main {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Calendar cal = Calendar.getInstance();
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		Expediente expediente = new Expediente();
		System.out.println("Entre com dia de pagamento padrão");
		int mes = cal.get(Calendar.MONTH) + 1;
		int dia = scan.nextInt();
		int diaAtual = cal.get(Calendar.DATE);
		int ano = cal.get(Calendar.YEAR);
		Date data = new Date(ano, mes, dia);
		Recursos recursos = new Recursos(data);
		while (true) {
			System.out.println("Data: " + diaAtual + "/" + mes + "/" + ano + "       " + data.getHours() + ":" + data.getMinutes());
			System.out.println("Funções administrativas");
			System.out.println("Lista de comando:");
			System.out.println("1 - Adicionar funcionario");
			System.out.println("2 - Remover Funcionario");
			System.out.println("3 - Imprimir lista de funcionarios");
			System.out.println("4 - Lança cartão de ponto");
			System.out.println("5 - Lança um resultado de vendas");
			System.out.println("6 - Lança uma taxa de serviços");
			System.out.println("7 - Folha de pagamento");
			System.out.println("9 - Passa um dia");
			System.out.println("10 - Atualizar dados");
			System.out.println("0 - Fechar");
			int comando = scan.nextInt();
			if (comando == 0) {
				break;
			} else if (comando == 1) {
				recursos.add();

			} else if (comando == 2) {
				recursos.remove();

			} else if (comando == 3) {
				recursos.print();

			} else if (comando == 4) {
				System.out.println("Escolha comando:");
				System.out.println("1 - Abrir ponto");
				System.out.println("2 - Fechar ponto");
				int com = scan.nextInt();
				if (com == 1) {
					System.out.println("Entre com codigo de acesso do funcionario");
					int id = scan.nextInt();
					expediente.checkIn(recursos.getFuncionario().get(id));
				} else if (com == 2) {
					System.out.println("Pontos Abertos:");
					expediente.in();
					System.out.println("Entre com codigo de acesso do funcionario");
					int idF = scan.nextInt();
					if (recursos.getFuncionario().get(idF) instanceof Assalariado) {
						Assalariado aux = (Assalariado) recursos.getFuncionario().get(idF);
						expediente.checkOut(aux.getNome());
						System.out.println(aux.getNome());
						

					} else if (recursos.getFuncionario().get(idF) instanceof Horista) {
						Horista aux = (Horista) recursos.getFuncionario().get(idF);
						System.out.println("Horas Trabalhada: "+ expediente.checkOut(aux.getNome()));
						aux.setnHoras(expediente.checkOut(aux.getNome()));
						recursos.set(idF, aux);
						

					} else if (recursos.getFuncionario().get(idF) instanceof Comissionado) {
						Comissionado aux = (Comissionado) recursos.getFuncionario().get(idF);
						expediente.checkOut(aux.getNome());
						System.out.println(aux.getNome());

					}
					
				}
			} else if (comando == 5){
				recursos.pontoVenda();
			}
			else if (comando == 7) {
				recursos.folha(data);

			} else if (comando == 9) {
				diaAtual++;
			} else if (comando == 10){
				System.out.println("Entre com seu codigo de acesso:");
				int id = scan.nextInt();
				recursos.update(id);
			}

		}

	}

}
