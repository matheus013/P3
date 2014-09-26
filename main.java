
import objeto.*;
import negocio.*;

import java.util.*;

public class main {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		@SuppressWarnings("resource")
		Scanner leitor = new Scanner(System.in);
		ArrayList<Funcionario> list_Funcionario=new ArrayList<Funcionario>();
		N_Funcionario n_Funcionario=new N_Funcionario();
		FolhaPagamento folhaPagamento=new FolhaPagamento(21, 11, 2014);
		list_Funcionario.add(new Comissionado("Admin", "Beco Diagonal", 0, false, 0, 1000, (float) 0.2));
		list_Funcionario.add(new Assalariado("Admin", "Travessa do Tranco", 1, false, 1, 2000));
		list_Funcionario.add(new Horista("Admin","Biboca Diagonal", 2, false, 2, 10));
		Stack<ArrayList<Funcionario>> redo = new Stack<ArrayList<Funcionario>>();
		Stack<ArrayList<Funcionario>> undo = new Stack<ArrayList<Funcionario>>();
		Stack<Integer> undo_folha = new Stack<Integer>();
		Stack<Integer> redo_folha = new Stack<Integer>();
		int menu=0;
		int auxInt=0;
		boolean aux = false;
		boolean aux2 = false;
		boolean isContinue=true;
		do
		{
			System.out.println("\t---Menu---\n\tData:"+folhaPagamento.getDate()+"\n\n");
			System.out.println("1 - Inserir funcionario");
			System.out.println("2 - Remover funcionario");
			System.out.println("3 - Mostrar Funcionario");
			System.out.println("4 - Alterar Dados");
			System.out.println("5 - Lanca venda");
			System.out.println("6 - Contra Cheque");
			System.out.println("7 - Cobra taxas sindicais");
			System.out.println("8 - Bate ponto");
			System.out.println("9 - Folha de pagamento do dia");
			System.out.println("10 - Refazer operacao");
			System.out.println("11 - Fazer operacao");
			System.out.println("12 - Sair\n");
			System.out.print("Escolha:");
			try{
				menu=leitor.nextInt();
			}catch (Exception e)
			{
				System.out.println("Digite novamente!!\nErro!!");
				leitor.next();
				menu=0;
			}
			switch(menu)
			{
			case 1:
				Funcionario novo_funcionario = null;
				try
				{
					novo_funcionario= n_Funcionario.addFuncionario(list_Funcionario);
				}catch (Exception e) {
					// TODO: handle exception
					System.out.println("Erro!");
					novo_funcionario = null;
					menu=0;
				}
				if(novo_funcionario!=null)
				{
					undo.push(new ArrayList<Funcionario>(list_Funcionario));
					list_Funcionario.add(novo_funcionario);
					System.out.println("Funcionario cadastrado com sucesso!");
					aux=true;
					aux2=false;
					undo_folha.push(0);
				}
				else
					System.out.println("O funcionario nao pode ser cadastrado!!!");
				break;
			case 2:
				System.out.print("Digite a matricula do funcionario que deseja apaga:");
				int matricula=-1;
				Funcionario antigo_funcionario=null;
				try
				{
					matricula=leitor.nextInt();
					antigo_funcionario=n_Funcionario.procuraFuncionario(list_Funcionario, matricula);
				}catch (Exception e)
				{
					System.out.println("Erro!!");
					antigo_funcionario=null;
					menu=0;
				}
				if(antigo_funcionario!=null)
				{
					undo.push(new ArrayList<Funcionario>(list_Funcionario));
					list_Funcionario.remove(antigo_funcionario);
					System.out.println("Funcionario apagado com sucesso!");
					aux=false;
					aux2=true;
					undo_folha.push(0);
				}
				else
					System.out.println("O funcionario nao foi encontrado!!!");
				break;
				
			case 3:
				for(Funcionario obj_funcionario : list_Funcionario)
				{
					if(obj_funcionario!=null)
						System.out.println(obj_funcionario.toString());
				}
				break;
			case 4:
				System.out.print("Digite a matricula do funcionario que deseja atualiza:");
				int atualizaMatricula=-1;
				try
				{
				atualizaMatricula=leitor.nextInt();
				}catch (Exception e)
				{
					System.out.println("Erro!!");
					leitor.next();
				}
				Funcionario atualizaFuncionario=n_Funcionario.procuraFuncionario(list_Funcionario, atualizaMatricula);
				if(atualizaFuncionario!=null)
				{
					undo.push(new ArrayList<Funcionario>(list_Funcionario));
					Funcionario novo_Funcionario=null;
					try
					{
						novo_Funcionario=n_Funcionario.alterarFuncionario(list_Funcionario, atualizaFuncionario);
					}catch(Exception e)
					{
						System.out.println("Erro!!");
						novo_Funcionario=null;
						menu=0;
					}
					if(novo_Funcionario!=null)
					{
						list_Funcionario.remove(atualizaFuncionario);
						list_Funcionario.add(novo_Funcionario);
						System.out.println("O funcionario atualizado com sucesso!!!");
						aux=false;
						aux2=false;
						undo_folha.push(0);
					}
					else
						System.out.println("O funcionario nao foi encontrado!!!");
				}
				else
					System.out.println("O funcionario nao foi encontrado!!!");
				break;
			case 5:
				System.out.println("Digite o id do funcionario:");
				int id=leitor.nextInt();
				Funcionario obj_VendedorAntigo=n_Funcionario.procuraFuncionario(list_Funcionario, id);
				if(obj_VendedorAntigo!=null)
				{
					undo.push(new ArrayList<Funcionario>(list_Funcionario));
					Funcionario obj_Vendedor;
					try
					{
					obj_Vendedor= n_Funcionario.addVenda(obj_VendedorAntigo, folhaPagamento.getDate());
					}catch(Exception e)
					{
						System.out.println("Erro!!");
						obj_Vendedor=null;
						menu=0;
					}
					if(obj_Vendedor!=null)
					{
						list_Funcionario.add(obj_Vendedor);
						list_Funcionario.remove(obj_VendedorAntigo);
						System.out.println("Venda lancada com sucesso!!!");
						aux=false;
						aux2=false;
						undo_folha.push(0);
					}
				}
				break;
			case 6:
				Funcionario obj_VendedorPago;
				System.out.println("Digite o id do funcionario:");
				try
				{
					int identificador=leitor.nextInt();
					obj_VendedorPago =n_Funcionario.procuraFuncionario(list_Funcionario, identificador);
				}catch(Exception e)
				{
					System.out.println("Erro!!");
					obj_VendedorPago=null;
					menu=0;
				}
				if(obj_VendedorPago!=null)
					System.out.println(obj_VendedorPago.pagamento());
				else
					System.out.println("O funcionario nao foi encontrado!!\nErro!");
				break;
			case 7:
				Funcionario obj_funcionarioSindicato;
				Funcionario obj_funFuncionarioSindicatonovo;
				System.out.println("Digite o id do funcionario:");
				try
				{
					int identificado=leitor.nextInt();
					obj_funcionarioSindicato=n_Funcionario.procuraFuncionario(list_Funcionario, identificado);
					if(obj_funcionarioSindicato!=null)
					{
					obj_funFuncionarioSindicatonovo=n_Funcionario.addTaxa(obj_funcionarioSindicato);
					if(obj_funFuncionarioSindicatonovo!=null)
					{

						undo.push(new ArrayList<Funcionario>(list_Funcionario));
						list_Funcionario.add(obj_funFuncionarioSindicatonovo);
						list_Funcionario.remove(obj_funcionarioSindicato);
						System.out.println("Taxa adicionada com sucesso!!!");
						aux=false;
						aux2=false;
						undo_folha.push(0);
					}
					else
						System.out.println("O funcionario nao foi encontrado!!\nErro!");
				}
				else
					System.out.println("O funcionario nao foi encontrado!!\nErro!");
				}catch(Exception e)
				{
					System.out.println("Erro!!");
					menu=0;
				}
				break;
			case 8:
				Funcionario obj_funcionarioPontoNovo=null;
				Funcionario obj_funcionarioPonto=null;
				try{
					System.out.println("Digite o id do funcionario:");
					int ident=leitor.nextInt();
					obj_funcionarioPonto=n_Funcionario.procuraFuncionario(list_Funcionario, ident);
					if(obj_funcionarioPonto!=null)
					{
						obj_funcionarioPontoNovo=n_Funcionario.addPonto(obj_funcionarioPonto, folhaPagamento.getDate());
						if(obj_funcionarioPontoNovo!=null)
						{
							undo.push(new ArrayList<Funcionario>(list_Funcionario));
							list_Funcionario.add(obj_funcionarioPontoNovo);
							list_Funcionario.remove(obj_funcionarioPonto);
							System.out.println("Ponto registado com sucesso!!!");
							aux=false;
							aux2=false;
							undo_folha.push(0);
						}
						else
							System.out.println("O funcionario nao foi encontrado!!\nErro!");
					}
					else
						System.out.println("O funcionario nao foi encontrado!!\nErro!");
				}catch(Exception e)
				{
					System.out.println("Erro!!!");
					menu=0;
				}
				break;
			case 9:
				undo_folha.push(1);
				undo.push(new ArrayList<Funcionario>(list_Funcionario));
				System.out.println(folhaPagamento.geraPagamento(list_Funcionario));
				list_Funcionario = new ArrayList<Funcionario>(folhaPagamento.limpaBoleto(list_Funcionario));
				list_Funcionario = new ArrayList<Funcionario>(folhaPagamento.baterPonto(list_Funcionario));
				break;		
			case 10:
				
				try
				{
					int undo_folha_int=undo_folha.pop();
					redo_folha.push(undo_folha_int);
					if(undo_folha_int==1)
					{
						folhaPagamento.retornaDia();
					}	
					redo.push(new ArrayList<Funcionario>(list_Funcionario));
					list_Funcionario=new ArrayList<Funcionario>(undo.pop());
				}catch(Exception e)
				{
					System.out.println("Erro!!\nNao se pode realiza a acao!");
				}
				break;
			case 11:
				try
				{
					int redo_folha_int=redo_folha.pop();
					undo_folha.push(redo_folha_int);
					if(redo_folha_int==1)
					{
						folhaPagamento.adicionaDia();
						System.out.println(folhaPagamento.geraPagamento(list_Funcionario));	
					}
					undo.push(new ArrayList<Funcionario>(list_Funcionario));
					list_Funcionario=new ArrayList<Funcionario>(redo.pop());
				}catch(Exception e)
				{
					System.out.println("Erro!!\nNao se pode realiza a acao!");
				}
				break;
			case 12:
				isContinue=false;
				break;
			}
		}while(isContinue);
	}
}
