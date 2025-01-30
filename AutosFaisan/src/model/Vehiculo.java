package model;

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
	
	
	
	
}
