package model;

import java.util.Objects;

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

	@Override
	public int hashCode() {
		return Objects.hash(marca, modelo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Modelos other = (Modelos) obj;
		return Objects.equals(marca, other.marca) && Objects.equals(modelo, other.modelo);
	}
	
	
	
	
}
