package model;

import java.util.Objects;

public class Local {
	
	//ATRIBUTOS
	
	private String id;
	private String nombre;
	private String localidad;
	
	//CONSTRUCTORES
	
	public Local() {
	}

	public Local(String id, String nombre, String localidad) {
		this.id = id;
		this.nombre = nombre;
		this.localidad = localidad;
	}
	
	//GETTERS & SETTERS

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, localidad, nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Local other = (Local) obj;
		return Objects.equals(id, other.id) && Objects.equals(localidad, other.localidad)
				&& Objects.equals(nombre, other.nombre);
	}
	
	
	
	
}
