package model;

import java.util.Objects;

public class Alquiler {
	
	//ATRIBUTOS
	
	private int Codigo;
	private String Matricula;
	private int Fecha;
	private int Dias;
	private String Cargo;
	
	//CONSTRUCTORES
	
	public Alquiler() {
	}

	public Alquiler(int codigo, String matricula, int fecha, int dias, String cargo) {
		super();
		Codigo = codigo;
		Matricula = matricula;
		Fecha = fecha;
		Dias = dias;
		Cargo = cargo;
	}

	//GETTERS & SETTERS
	
	public int getCodigo() {
		return Codigo;
	}

	public void setCodigo(int codigo) {
		Codigo = codigo;
	}

	public String getMatricula() {
		return Matricula;
	}

	public void setMatricula(String matricula) {
		Matricula = matricula;
	}

	public int getFecha() {
		return Fecha;
	}

	public void setFecha(int fecha) {
		Fecha = fecha;
	}

	public int getDias() {
		return Dias;
	}

	public void setDias(int dias) {
		Dias = dias;
	}

	public String getCargo() {
		return Cargo;
	}

	public void setCargo(String cargo) {
		Cargo = cargo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(Cargo, Codigo, Dias, Fecha, Matricula);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Alquiler other = (Alquiler) obj;
		return Objects.equals(Cargo, other.Cargo) && Codigo == other.Codigo && Dias == other.Dias
				&& Fecha == other.Fecha && Objects.equals(Matricula, other.Matricula);
	}
	
	

}
