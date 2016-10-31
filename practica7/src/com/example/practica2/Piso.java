package com.example.practica2;

import java.io.Serializable;
import java.util.List;

public class Piso implements Serializable{

	private int id;
	private String miniatura;
	private String titulo;
	private String descripcion;
	private int metros;
	private int precios;
	private List<String> imagenes;
	
	

	public Piso(int id, String miniatura, String titulo, String descripcion,
			int metros, int precios, List<String> imagenes) {
		super();
		this.id = id;
		this.miniatura = miniatura;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.metros = metros;
		this.precios = precios;
		this.imagenes = imagenes;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMiniatura() {
		return miniatura;
	}
	public void setMiniatura(String miniatura) {
		this.miniatura = miniatura;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public int getMetros() {
		return metros;
	}
	public void setMetros(int metros) {
		this.metros = metros;
	}
	public int getPrecios() {
		return precios;
	}
	public void setPrecios(int precios) {
		this.precios = precios;
	}
	public List<String> getImagenes() {
		return imagenes;
	}
	public void setImagenes(List<String> imagenes) {
		this.imagenes = imagenes;
	}
}
