package model;

import java.util.Objects;

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(cilindrada);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Moto other = (Moto) obj;
		return cilindrada == other.cilindrada;
	}

	
}
