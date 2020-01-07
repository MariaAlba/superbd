package com.ipartek.formacion.supermercado.modelo.dao;

public class ProductoException extends Exception {

	private static final long serialVersionUID = 1L;

	public static final String EXCEPTION_UNAUTHORIZED = "El producto no pertenece al usuario";

	public ProductoException(String msg) {
		super(msg);
	}

}
