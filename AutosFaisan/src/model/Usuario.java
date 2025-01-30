package model;



import java.sql.Date;

import enums.TipoUsuario;

public class Usuario {
	
	//ATRIBUTOS
	
	private String dni;
	private String nombre;
	private String apellido;
	private Date fec_nac;
	private TipoUsuario tipo;
	
	//CONSTRUCTORES
	
	public Usuario() {
	}
	
	public Usuario(String dni, String nombre, String apellido, Date fec_nac, TipoUsuario tipo) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fec_nac = fec_nac;
		this.tipo = tipo;
	}

	//GETTERS & SETTERS

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Date getFec_nac() {
		return fec_nac;
	}

	public void setFec_nac(Date fec_nac) {
		this.fec_nac = fec_nac;
	}

	public TipoUsuario getTipo() {
		return tipo;
	}

	public void setTipo(TipoUsuario tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "Usuario [dni=" + dni + ", nombre=" + nombre + ", apellido=" + apellido + ", fec_nac=" + fec_nac
				+ ", tipo=" + tipo + "]";
	}
	
	
	
}
