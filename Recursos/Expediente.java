package Recursos;

import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import Trabalhadores.Funcionario;

public class Expediente {
	ArrayList<String> inWork =  new ArrayList<String>();
	Date data = new Date();
	Hashtable horas = new Hashtable();

	public Expediente() {
		// TODO Auto-generated constructor stub
	}
	public void checkIn(Funcionario funcionario){
		horas.put(funcionario.getNome(), data.getHours());
		inWork.add(funcionario.getNome());
		
	}
	public int checkOut(String funcionario){
		int horasTrabalhadas = (int) horas.get(funcionario) - data.getHours();
		horas.remove(funcionario);
		inWork.remove(funcionario);
		if(horasTrabalhadas < 0){horasTrabalhadas *= -1;}
		return horasTrabalhadas;
	}
	public void in(){
		for(int i = 0; i < inWork.size();i++){
			System.out.println(inWork.get(i));
		}
	}
}
