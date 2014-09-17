package Trabalhadores;

public abstract class Funcionario {
	protected int id;
	protected double taxa;
	protected double salario;
	protected String nome;
	protected String endereco;
	protected boolean sindicato = false;

	public Funcionario(int id, double taxa, String nome, double salario,
			String endereco) {
		super();
		this.id = id;
		this.taxa = taxa;
		this.nome = nome;
		this.salario = salario;
		this.endereco = endereco;
	}

	public boolean isSindicato() {
		return sindicato;
	}

	public void setSindicato(boolean sindicato) {
		this.sindicato = sindicato;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public void setTaxa(double taxa) {
		this.taxa = taxa;
	}

	public abstract double pagamento();

	public double getTaxa() {
		return taxa;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getId() {
		return id;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

}
