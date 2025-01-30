package model;

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
	
	
	
	
}
