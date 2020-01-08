-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         8.0.16 - MySQL Community Server - GPL
-- SO del servidor:              Win64
-- HeidiSQL Versión:             10.3.0.5771
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Volcando estructura de base de datos para supermercado
DROP DATABASE IF EXISTS `supermercado`;
CREATE DATABASE IF NOT EXISTS `supermercado` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `supermercado`;

-- Volcando estructura para tabla supermercado.categoria
DROP TABLE IF EXISTS `categoria`;
CREATE TABLE IF NOT EXISTS `categoria` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(150) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nombre_UNIQUE` (`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla supermercado.categoria: ~3 rows (aproximadamente)
DELETE FROM `categoria`;
/*!40000 ALTER TABLE `categoria` DISABLE KEYS */;
INSERT INTO `categoria` (`id`, `nombre`) VALUES
	(2, 'alimentacion'),
	(1, 'general\r\n'),
	(3, 'musica');
/*!40000 ALTER TABLE `categoria` ENABLE KEYS */;

-- Volcando estructura para tabla supermercado.rol
DROP TABLE IF EXISTS `rol`;
CREATE TABLE IF NOT EXISTS `rol` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '1: usuario normal\n2: administador',
  `nombre` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nombre_UNIQUE` (`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla supermercado.rol: ~2 rows (aproximadamente)
DELETE FROM `rol`;
/*!40000 ALTER TABLE `rol` DISABLE KEYS */;
INSERT INTO `rol` (`id`, `nombre`) VALUES
	(2, 'administrador'),
	(1, 'usuario');
/*!40000 ALTER TABLE `rol` ENABLE KEYS */;

-- Volcando estructura para tabla supermercado.usuario
DROP TABLE IF EXISTS `usuario`;
CREATE TABLE IF NOT EXISTS `usuario` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL DEFAULT '0',
  `contrasenia` varchar(50) NOT NULL DEFAULT '0',
  `id_rol` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `nombre` (`nombre`),
  KEY `fk_rol_idx` (`id_rol`),
  CONSTRAINT `fk_rol` FOREIGN KEY (`id_rol`) REFERENCES `rol` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla supermercado.usuario: ~4 rows (aproximadamente)
DELETE FROM `usuario`;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` (`id`, `nombre`, `contrasenia`, `id_rol`) VALUES
	(1, 'admin', 'admin', 2),
	(4, 'Dolores', '56789', 1),
	(6, 'pepe', 'pepe', 1),
	(10, 'miu', 'miu', 1);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;

-- Volcando estructura para tabla supermercado.producto
DROP TABLE IF EXISTS `producto`;
CREATE TABLE IF NOT EXISTS `producto` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `descripcion` varchar(150) NOT NULL DEFAULT 'nombre',
  `imagen` varchar(150) NOT NULL DEFAULT 'https://image.flaticon.com/icons/png/512/372/372627.png',
  `precio` float NOT NULL DEFAULT '0',
  `descuento` int(11) NOT NULL DEFAULT '0',
  `id_usuario` int(11) NOT NULL,
  `id_categoria` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `nombre` (`nombre`),
  KEY `FK_usuario` (`id_usuario`),
  KEY `FK_categoria_idx` (`id_categoria`),
  CONSTRAINT `FK_categoria` FOREIGN KEY (`id_categoria`) REFERENCES `categoria` (`id`),
  CONSTRAINT `FK_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla supermercado.producto: ~14 rows (aproximadamente)
DELETE FROM `producto`;
/*!40000 ALTER TABLE `producto` DISABLE KEYS */;
INSERT INTO `producto` (`id`, `nombre`, `descripcion`, `imagen`, `precio`, `descuento`, `id_usuario`, `id_categoria`) VALUES
	(8, 'donetes nuevos verdes', 'donetes', 'https://image.flaticon.com/icons/png/512/372/372627.png', 5, 2, 1, 2),
	(10, 'galletas3', 'nombre', 'https://image.flaticon.com/icons/png/512/372/372627.png', 0, 0, 1, 2),
	(17, 'donetes rallados', 'nombre', 'https://image.flaticon.com/icons/png/512/372/372627.png', 2.75, 0, 4, 1),
	(18, 'galletitas saladitas', 'galletas', 'https://image.flaticon.com/icons/png/512/372/372627.png', 12, 5, 1, 1),
	(19, 'arena gato', 'higiene gatos', 'https://image.flaticon.com/icons/png/512/372/372627.png', 3.75, 2, 1, 1),
	(22, 'flores secas', 'decoracion', 'https://image.flaticon.com/icons/png/512/372/372627.png', 7.5, 3, 1, 1),
	(23, 'comida peces', 'alimentacion', 'https://image.flaticon.com/icons/png/512/372/372627.png', 5, 1, 1, 1),
	(26, 'donetes pepe', 'donetes', 'https://image.flaticon.com/icons/png/512/372/372627.png', 2.75, 1, 6, 1),
	(27, 'golosinas de fresa', 'dulces', 'https://image.flaticon.com/icons/png/512/372/372627.png', 3.45, 2, 4, 1),
	(28, 'gominolas de colores', 'dulces', 'https://image.flaticon.com/icons/png/512/372/372627.png', 4.75, 3, 6, 1),
	(29, 'pescado', 'pescaderia', 'https://image.flaticon.com/icons/png/512/372/372627.png', 30, 0, 6, 1),
	(31, 'algo', 'donetes', 'https://image.flaticon.com/icons/png/512/372/372627.png', 0, 0, 6, 1),
	(33, 'galletitas dulces', 'galletas', 'https://image.flaticon.com/icons/png/512/372/372627.png', 0, 0, 10, 1),
	(34, 'chocolate', 'dulces', 'https://image.flaticon.com/icons/png/512/372/372627.png', 0, 0, 10, 2);
/*!40000 ALTER TABLE `producto` ENABLE KEYS */;





-- Volcando estructura para procedimiento supermercado.pa_categoria_getall
DROP PROCEDURE IF EXISTS `pa_categoria_getall`;
DELIMITER //
CREATE PROCEDURE `pa_categoria_getall`()
BEGIN
	
	--	nuestro primer pa --
	/* muestra toddas las categorias
		comentario de bloque
	*/
	SELECT id, nombre FROM categoria ORDER BY nombre ASC LIMIT 500;
	
END//
DELIMITER ;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
