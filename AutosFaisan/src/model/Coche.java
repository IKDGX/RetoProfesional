package model;

import java.util.Objects;

import enums.Conduccion;

public class Coche extends Vehiculo{
	
	//ATRIBUTOS
	
	private Conduccion tipo;

	//CONSTRUCTORES
	
	public Coche() {

	}

	public Coche(String matricula, Modelos modelo, String color, float precio, Local id_local, boolean disponibilidad, Conduccion tipo) {
		super(matricula, modelo, color, precio, id_local, disponibilidad);
		this.tipo = tipo;
	}
	
	//GETTERS & SETTERS

	public Conduccion getTipo() {
		return tipo;
	}

	public void setTipo(Conduccion tipo) {
		this.tipo = tipo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(tipo);
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
		Coche other = (Coche) obj;
		return tipo == other.tipo;
	}
	
	
	
}
