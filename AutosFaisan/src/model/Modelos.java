package model;

public class Modelos {
	
	//ATRIBUTOS
	
	private String modelo;
	private String marca;
	
	//CONSTRUCTORES
	
	public Modelos() {
	}

	public Modelos(String modelo, String marca) {
		this.modelo = modelo;
		this.marca = marca;
	}

	//GETTERS & SETTERS
	
	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}
	
	
	
	
}
