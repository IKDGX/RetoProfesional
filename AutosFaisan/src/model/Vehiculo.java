package model;

import java.util.Objects;

public class Vehiculo {
	
	//ATRIBUTOS
	
	protected String matricula;
	protected Modelos modelo;
	protected String color;
	protected float precio;
	protected Local id_local;
	protected boolean disponibilidad;
	
	//CONSTRUCTORES
	
	public Vehiculo() {

	}


	public Vehiculo(String matricula, Modelos modelo, String color, float precio, Local id_local, boolean disponibilidad) {
		this.matricula = matricula;
		this.modelo = modelo;
		this.color = color;
		this.precio = precio;
		this.id_local = id_local;
		this.disponibilidad = disponibilidad;
	}

	//GETTERS & SETTERS

	public String getMatricula() {
		return matricula;
	}


	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}


	public Modelos getModelo() {
		return modelo;
	}


	public void setModelo(Modelos modelo) {
		this.modelo = modelo;
	}


	public String getColor() {
		return color;
	}


	public void setColor(String color) {
		this.color = color;
	}


	public float getPrecio() {
		return precio;
	}


	public void setPrecio(float precio) {
		this.precio = precio;
	}


	public Local getId_local() {
		return id_local;
	}


	public void setId_local(Local id_local) {
		this.id_local = id_local;
	}


	public boolean isDisponibilidad() {
		return disponibilidad;
	}


	public void setDisponibilidad(boolean disponibilidad) {
		this.disponibilidad = disponibilidad;
	}


	@Override
	public int hashCode() {
		return Objects.hash(color, disponibilidad, id_local, matricula, modelo, precio);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vehiculo other = (Vehiculo) obj;
		return Objects.equals(color, other.color) && disponibilidad == other.disponibilidad
				&& Objects.equals(id_local, other.id_local) && Objects.equals(matricula, other.matricula)
				&& Objects.equals(modelo, other.modelo)
				&& Float.floatToIntBits(precio) == Float.floatToIntBits(other.precio);
	}
	
	
	
	
}
