package br.edu.ifrs.canoas.jee.webapp.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Entity implementation class for Entity: Automovel
 *
 */
@Entity

public class Automovel extends BaseEntity<Long> implements Serializable {

	private static final long serialVersionUID = 2643031518170491233L;

	public Automovel() {
		super();
	}
	
	private String marca;
	private String modelo;
	private String ano;
	
	@NotNull
	private String placa;
	
	@NotNull
	private String renavan;

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getRenavan() {
		return renavan;
	}

	public void setRenavan(String renavan) {
		this.renavan = renavan;
	}
	
	
   
}
