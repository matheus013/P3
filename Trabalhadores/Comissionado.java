package Trabalhadores;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Comissionado extends Assalariado {
	private Date data;
	private SimpleDateFormat ft = new SimpleDateFormat("dd.MM.yyyy");
	private double comissao;
	private double vendas = 0;

	public Comissionado(int id, double taxa, String nome, String endereco,
			Date data, double comissao, double vendas) {
		super(id,taxa,nome,endereco,0,data);
		// TODO Auto-generated constructor stub
		this.data = data;
		this.comissao = comissao;
		this.vendas += vendas;
	}

	public String getData() {
		return ft.format(data);
	}

	public void setData(Date data) {
		this.data = data;
	}

	public double pagamento() {
		return salario + (vendas + (vendas * (comissao / 100)));
	}

	public double getComissao() {
		return comissao;
	}

	public void setComissao(double comissao) {
		this.comissao = comissao;
	}

	public double getVendas() {
		return vendas;
	}

	public void addVendas(double vendas) {
		this.vendas += vendas;
	}
	public String toString() {
		return "id: " + getId() + "\nNome: " + getNome() + "\nEndereço: "
				+ getEndereco() + "\nSalario: R$" + pagamento() + "0"
				+ "\nData de pagamento: " + getData();
	}

}
