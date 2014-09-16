package Trabalhadores;

import java.util.*;
import java.text.*;

public class Assalariado extends Funcionario {
	private Date data;
	private SimpleDateFormat ft = new SimpleDateFormat("dd.MM.yyyy");

	public Assalariado(int id, double taxa, String nome, String endereco,
			double salario, Date data) {
		super(id, taxa, nome, salario, endereco);
		// TODO Auto-generated constructor stub
		this.data = data;
	}

	public double pagamento() {
		return salario;
	}

	public String getData() {
		return ft.format(data);
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String toString() {
		return "id: " + getId() + "\nNome: " + getNome() + "\nEndereço: "
				+ getEndereco() + "\nSalario: R$" + pagamento() + "0"
				+ "\nData de pagamento: " + getData();
	}
}
