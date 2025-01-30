package model;

public class Moto extends Vehiculo{
	
	//ATRIBUTOS
	
	private int cilindrada;

	//CONSTRUCTORES
	
	public Moto() {
	
	}

	public Moto(String matricula, Modelos modelo, String color, float precio, Local id_local, boolean disponibilidad, int cilindrada) {
		super(matricula, modelo, color, precio, id_local, disponibilidad);
		this.cilindrada = cilindrada;
	}

	//GETTERS & SETTERS
	
	public int getCilindrada() {
		return cilindrada;
	}

	public void setCilindrada(int cilindrada) {
		this.cilindrada = cilindrada;
	}

	
}
