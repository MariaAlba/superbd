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
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla supermercado.categoria: ~6 rows (aproximadamente)
DELETE FROM `categoria`;
/*!40000 ALTER TABLE `categoria` DISABLE KEYS */;
INSERT INTO `categoria` (`id`, `nombre`) VALUES
	(2, 'alimentacion'),
	(5, 'felinos'),
	(3, 'fruteria'),
	(1, 'general\r\n'),
	(4, 'medicinas'),
	(54, 'musica');
/*!40000 ALTER TABLE `categoria` ENABLE KEYS */;

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
  `fecha_baja` datetime DEFAULT NULL,
  `fecha_alta` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `fecha_modificacion` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nombre` (`nombre`),
  KEY `FK_usuario` (`id_usuario`),
  KEY `FK_categoria_idx` (`id_categoria`),
  CONSTRAINT `FK_categoria` FOREIGN KEY (`id_categoria`) REFERENCES `categoria` (`id`),
  CONSTRAINT `FK_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla supermercado.producto: ~14 rows (aproximadamente)
DELETE FROM `producto`;
/*!40000 ALTER TABLE `producto` DISABLE KEYS */;
INSERT INTO `producto` (`id`, `nombre`, `descripcion`, `imagen`, `precio`, `descuento`, `id_usuario`, `id_categoria`, `fecha_baja`, `fecha_alta`, `fecha_modificacion`) VALUES
	(8, 'donetes nuevos verdes', 'donetes', 'https://image.flaticon.com/icons/png/512/372/372627.png', 5, 2, 1, 2, NULL, '2020-01-09 13:12:39', '2020-01-09 13:12:39'),
	(10, 'galletas3', 'nombre', 'https://image.flaticon.com/icons/png/512/372/372627.png', 0, 0, 1, 2, NULL, '2020-01-09 13:12:39', '2020-01-09 13:12:39'),
	(17, 'donetes rallados', 'nombre', 'https://image.flaticon.com/icons/png/512/372/372627.png', 2.75, 0, 4, 1, NULL, '2020-01-09 13:12:39', '2020-01-09 13:12:39'),
	(18, 'galletitas saladitas', 'galletas', 'https://image.flaticon.com/icons/png/512/372/372627.png', 12, 5, 1, 1, NULL, '2020-01-09 13:12:39', '2020-01-09 13:12:39'),
	(19, 'arena gato', 'higiene gatos', 'https://image.flaticon.com/icons/png/512/372/372627.png', 3.75, 2, 1, 1, NULL, '2020-01-09 13:12:39', '2020-01-09 13:12:39'),
	(22, 'flores secas', 'decoracion', 'https://image.flaticon.com/icons/png/512/372/372627.png', 7.5, 3, 1, 1, NULL, '2020-01-09 13:12:39', '2020-01-09 13:12:39'),
	(23, 'comida peces', 'alimentacion', 'https://image.flaticon.com/icons/png/512/372/372627.png', 5, 1, 1, 1, NULL, '2020-01-09 13:12:39', '2020-01-09 13:12:39'),
	(26, 'donetes pepe', 'donetes', 'https://image.flaticon.com/icons/png/512/372/372627.png', 2.75, 1, 6, 1, NULL, '2020-01-09 13:12:39', '2020-01-09 13:12:39'),
	(27, 'golosinas de fresa', 'dulces', 'https://image.flaticon.com/icons/png/512/372/372627.png', 3.45, 2, 4, 1, NULL, '2020-01-09 13:12:39', '2020-01-09 13:12:39'),
	(28, 'gominolas de colores', 'dulces', 'https://image.flaticon.com/icons/png/512/372/372627.png', 4.75, 3, 6, 1, NULL, '2020-01-09 13:12:39', '2020-01-09 13:12:39'),
	(29, 'pescado', 'pescaderia', 'https://image.flaticon.com/icons/png/512/372/372627.png', 30, 0, 6, 1, NULL, '2020-01-09 13:12:39', '2020-01-09 13:12:39'),
	(31, 'algo', 'donetes', 'https://image.flaticon.com/icons/png/512/372/372627.png', 0, 0, 6, 1, NULL, '2020-01-09 13:12:39', '2020-01-09 13:12:39'),
	(33, 'galletitas dulces', 'galletas', 'https://image.flaticon.com/icons/png/512/372/372627.png', 0, 0, 10, 1, NULL, '2020-01-09 13:12:39', '2020-01-09 13:12:39'),
	(34, 'chocolate', 'dulces', 'https://image.flaticon.com/icons/png/512/372/372627.png', 0, 0, 10, 2, NULL, '2020-01-09 13:12:39', '2020-01-09 13:12:39'),
	(44, 'morcilla de burgos', 'morcilla', 'https://image.flaticon.com/icons/png/512/372/372627.png', 5, 0, 1, 1, NULL, '2020-01-09 13:12:39', '2020-01-09 13:12:39'),
	(45, 'queso de burgos', 'morcilla', 'https://image.flaticon.com/icons/png/512/372/372627.png', 5, 0, 1, 1, NULL, '2020-01-09 13:12:39', '2020-01-09 13:12:39'),
	(46, 'queso manchego', 'morcilla', 'https://image.flaticon.com/icons/png/512/372/372627.png', 5, 100, 1, 1, NULL, '2020-01-09 13:12:39', '2020-01-09 13:12:39'),
	(53, 'morcilla de burgos1', 'morcilla', 'https://image.flaticon.com/icons/png/512/372/372627.png', 5, 0, 1, 1, NULL, '2020-01-09 13:13:46', '2020-01-09 13:13:46'),
	(54, 'queso de burgos2', 'morcilla', 'https://image.flaticon.com/icons/png/512/372/372627.png', 5, 50, 1, 1, NULL, '2020-01-09 13:13:46', '2020-01-09 13:13:46'),
	(55, ' blueberry muffin', 'morcilla', 'https://image.flaticon.com/icons/png/512/372/372627.png', 5, 100, 1, 1, NULL, '2020-01-09 13:13:46', '2020-01-09 13:16:35');
/*!40000 ALTER TABLE `producto` ENABLE KEYS */;

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

-- Volcando estructura para procedimiento supermercado.pa_categoria_delete
DROP PROCEDURE IF EXISTS `pa_categoria_delete`;
DELIMITER //
CREATE PROCEDURE `pa_categoria_delete`(
	IN `p_id` INT
)
BEGIN

DELETE FROM categoria WHERE id = p_id;

END//
DELIMITER ;

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

-- Volcando estructura para procedimiento supermercado.pa_categoria_getbyid
DROP PROCEDURE IF EXISTS `pa_categoria_getbyid`;
DELIMITER //
CREATE PROCEDURE `pa_categoria_getbyid`(
	IN `p_id` INT
)
BEGIN

	SELECT id, nombre FROM categoria WHERE id = p_id;

END//
DELIMITER ;

-- Volcando estructura para procedimiento supermercado.pa_categoria_insert
DROP PROCEDURE IF EXISTS `pa_categoria_insert`;
DELIMITER //
CREATE PROCEDURE `pa_categoria_insert`(
	IN `p_nombre` VARCHAR(100),
	OUT `p_id` INT
)
BEGIN

	-- crear nuevo registro
		INSERT INTO categoria (nombre) VALUES(p_nombre);
	-- obtener id generado y devolver el id SETeando el parametro de salida	
		SET p_id = LAST_INSERT_ID();




END//
DELIMITER ;

-- Volcando estructura para procedimiento supermercado.pa_categoria_update
DROP PROCEDURE IF EXISTS `pa_categoria_update`;
DELIMITER //
CREATE PROCEDURE `pa_categoria_update`(
	IN `p_id` INT,
	IN `p_nombre` VARCHAR(100)
)
BEGIN

	UPDATE categoria SET nombre = p_nombre
	WHERE id = p_id;

END//
DELIMITER ;

-- Volcando estructura para función supermercado.GET_MES
DROP FUNCTION IF EXISTS `GET_MES`;
DELIMITER //
CREATE FUNCTION `GET_MES`(
	`pFecha` DATE
) RETURNS varchar(100) CHARSET utf8
BEGIN

	DECLARE mes VARCHAR(100);
	SET lc_time_names = 'es_ES';
	SET mes = MONTHNAME(pFecha);
	
	RETURN mes;


END//
DELIMITER ;

-- Volcando estructura para función supermercado.GET_MES_CASE
DROP FUNCTION IF EXISTS `GET_MES_CASE`;
DELIMITER //
CREATE FUNCTION `GET_MES_CASE`(
	`pFecha` DATE
) RETURNS varchar(100) CHARSET utf8
BEGIN
 
 	DECLARE mes VARCHAR(100);
 	CASE MONTH(pFecha)
 	WHEN 1 THEN SET mes = 'Enero';
 	WHEN 2 THEN SET mes = 'Febrero';
 	WHEN 3 THEN SET mes = 'Marzo';
 	WHEN 4 THEN SET mes = 'Abril';
 	WHEN 5 THEN SET mes = 'Mayo';
 	WHEN 6 THEN SET mes = 'Junio';
 	WHEN 7 THEN SET mes = 'Julio';
 	WHEN 8 THEN SET mes = 'Agosto';
 	WHEN 9 THEN SET mes = 'Septiembre';
 	WHEN 10 THEN SET mes = 'Octubre';
 	WHEN 11 THEN SET mes = 'Noviembre';
 	WHEN 12 THEN SET mes = 'Diciembre';
 	ELSE SET mes ='no sé';
 	END CASE;
 	
 	RETURN mes;
/*CASE MONTH(pFecha)
		WHEN 1 THEN RETURN "Enero";
		WHEN 2 THEN RETURN "Febrero";
		WHEN 3 THEN RETURN "Marzo";
		WHEN 4 THEN RETURN "Abril";
		WHEN 5 THEN RETURN "Mayo";
		WHEN 6 THEN RETURN "Junio";
		WHEN 7 THEN RETURN "Julio";
		WHEN 8 THEN RETURN "Agosto";
		WHEN 9 THEN RETURN "Septiembre";
		WHEN 10 THEN RETURN "Octubre";
		WHEN 11 THEN RETURN "Noviembre";
		WHEN 12 THEN RETURN "Diciembre";
		ELSE RETURN "No sé";
	END CASE;
*/
END//
DELIMITER ;

-- Volcando estructura para función supermercado.HELLO_WORLD
DROP FUNCTION IF EXISTS `HELLO_WORLD`;
DELIMITER //
CREATE FUNCTION `HELLO_WORLD`() RETURNS varchar(100) CHARSET utf8
BEGIN

	RETURN "hola mundo";

END//
DELIMITER ;

-- Volcando estructura para función supermercado.HELLO_WORLD2
DROP FUNCTION IF EXISTS `HELLO_WORLD2`;
DELIMITER //
CREATE FUNCTION `HELLO_WORLD2`(
	`pNombre` VARCHAR(100)
) RETURNS varchar(100) CHARSET utf8
BEGIN

	DECLARE nombre VARCHAR(100) DEFAULT 'anonimo';
	
	IF(TRIM(pNombre) != '')THEN 
	SET nombre = TRIM(pNombre);
	END IF;
	
	/*no se concatena con + "Hola " + pNombre; se usa CONCAT()*/
	RETURN CONCAT("Hello world, ",nombre);

END//
DELIMITER ;

-- Volcando estructura para disparador supermercado.tbi_producto
DROP TRIGGER IF EXISTS `tbi_producto`;
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `tbi_producto` BEFORE INSERT ON `producto` FOR EACH ROW BEGIN
	-- comprueba que descuento sea de 0 a 100
	
	-- si es <0 poner a 0
	IF NEW.descuento <0 THEN 
		SET NEW.descuento = 0; 
	END IF;
	
	-- si es > 100 poner a 100
	IF NEW.descuento >100 THEN 
		SET NEW.descuento = 100; 
	END IF;

END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Volcando estructura para disparador supermercado.tbu_producto
DROP TRIGGER IF EXISTS `tbu_producto`;
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `tbu_producto` BEFORE UPDATE ON `producto` FOR EACH ROW BEGIN

	IF NEW.descuento <0 THEN 
		SET NEW.descuento = 0; 
	END IF;
	
	-- si es > 100 poner a 100
	IF NEW.descuento >100 THEN 
		SET NEW.descuento = 100; 
	END IF;

END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
