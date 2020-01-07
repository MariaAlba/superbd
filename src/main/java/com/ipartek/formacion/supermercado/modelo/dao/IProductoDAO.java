package com.ipartek.formacion.supermercado.modelo.dao;

import java.util.List;

import com.ipartek.formacion.supermercado.modelo.pojo.Producto;

public interface IProductoDAO extends IDAO<Producto> {

	/**
	 * Lista los productos de un usuario
	 * 
	 * @param idUsuario
	 * @return List<Producto>, lista inicializada en caso de que no tenga productos
	 */
	List<Producto> getAllByUser(int idUsuario);

	/**
	 * 
	 * @param idProducto
	 * @param idUsuario
	 * @return Producto si encuentra / null si no encuentra
	 * @throws ProductoException cuando intenta recuperar un producto que no
	 *                           pertenece al usuario
	 */
	Producto getByIdByUser(int idProducto, int idUsuario) throws ProductoException;

	/**
	 * Modifica un producto de un usuario determinado
	 * 
	 * @param idProducto
	 * @param idUsuario
	 * @param pojo
	 * @return
	 * @throws ProductoException cuando intenta modificar un producto que no
	 *                           pertenece al usuario
	 */
	Producto updateByUser(int idProducto, int idUsuario, Producto pojo) throws ProductoException;

	/**
	 * Elimina un @class Producto de un @class Usuario concreto
	 * 
	 * @param idProducto
	 * @param idUSuario
	 * @return el producto eliminado si lo encuentra / lanza productoException si no
	 *         lo encuentra
	 * @throws ProductoException
	 *                           <ol>
	 *                           <li>Cuando intenta eliminar un producto que no
	 *                           pertenece al usuario</li>
	 *                           <li>Cuando no encuentra producto por id para ese
	 *                           usuario</li>
	 *                           </ol>
	 */
	Producto deleteByUser(int idProducto, int idUSuario) throws ProductoException;
}
