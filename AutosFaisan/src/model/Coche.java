package model;

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
	
	
	
}
