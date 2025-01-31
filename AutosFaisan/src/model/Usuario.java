package model;



import java.sql.Date;
import java.util.Objects;

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

	@Override
	public int hashCode() {
		return Objects.hash(apellido, dni, fec_nac, nombre, tipo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(apellido, other.apellido) && Objects.equals(dni, other.dni)
				&& Objects.equals(fec_nac, other.fec_nac) && Objects.equals(nombre, other.nombre) && tipo == other.tipo;
	}
	
	
	
}
