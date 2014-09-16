package Trabalhadores;

public class Horista extends Funcionario {

	private double perHora;
	private int nHoras;

	public Horista(int id, double taxa, String nome, String endereco,
			int nHoras, double perHora) {
		super(id, taxa, nome, 0, endereco);
		// TODO Auto-generated constructor stub
		this.perHora = perHora;
		this.nHoras = nHoras;

	}

	public double pagamento() {
		return nHoras * perHora;
	}

	public double getPerHora() {
		return perHora;
	}

	public void setPerHora(double perHora) {
		this.perHora = perHora;
	}

	public int getnHoras() {
		return nHoras;
	}

	public void setnHoras(int nHoras) {
		this.nHoras = nHoras;
	}
	public String toString() {
		return "id: " + getId() + "\nNome: " + getNome() + "\nEndereço: "
				+ getEndereco() + "\nSalario: R$" + pagamento() + "0"
				+ "\nData de pagamento: 31.09.2014";
	}

}
