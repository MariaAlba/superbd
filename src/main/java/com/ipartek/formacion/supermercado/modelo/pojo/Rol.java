package com.ipartek.formacion.supermercado.modelo.pojo;

public class Rol {

	public static final int ROL_USUARIO = 1;
	public static final int ROL_ADMIN = 2;

	private int id;
	private String nombre;

	public Rol() {
		super();
		this.id = ROL_USUARIO;
		this.nombre = "usuario";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}

/*
 * un usuario administrador tiene permisos totales y cuando accede al backoffice
 * (seguridad) puede editar cualquier producto y cualquier usuario
 * 
 * un usuario normal cuando se logea accede añ frontoffice (mipanel) el usuario
 * logeado desde su panel solo puede hacer crud de sus productos y solo podría
 * cambiar su contraseña de su perfil
 */
