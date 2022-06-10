-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jun 04, 2021 at 03:16 AM
-- Server version: 5.6.17
-- PHP Version: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `ocsmodular`
--

-- --------------------------------------------------------

--
-- Table structure for table `entradas`
--

CREATE TABLE IF NOT EXISTS `entradas` (
  `FOLIO` mediumint(9) NOT NULL AUTO_INCREMENT,
  `FECHA` date NOT NULL,
  `RECIBIO` varchar(150) NOT NULL,
  `REFERENCIA` varchar(200) NOT NULL,
  `MOTIVO` varchar(200) NOT NULL,
  `OBSERVACIONES` varchar(300) DEFAULT NULL,
  `CODIGO_PROVEEDOR` mediumint(9) NOT NULL,
  `PROYECTO` mediumint(9) DEFAULT NULL,
  `IS_ELIMINATED` char(3) DEFAULT NULL,
  PRIMARY KEY (`FOLIO`),
  KEY `CODIGO_PROVEEDOR` (`CODIGO_PROVEEDOR`),
  KEY `PROYECTO` (`PROYECTO`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=36 ;

--
-- Dumping data for table `entradas`
--

INSERT INTO `entradas` (`FOLIO`, `FECHA`, `RECIBIO`, `REFERENCIA`, `MOTIVO`, `OBSERVACIONES`, `CODIGO_PROVEEDOR`, `PROYECTO`, `IS_ELIMINATED`) VALUES
(1, '0000-00-00', '', '', '', '', 1, 2, '*'),
(2, '2021-06-15', 'Jorge Hernandez Ramirez', '250', 'Surtir proyecto', 'Observando', 1, 1, NULL),
(4, '2021-04-15', 'Juan Abel Lopez Andrade', '252', 'Surtir proyecto', 'Observadas', 5, 1, NULL),
(7, '2021-04-15', 'Salvador Mendoza Briseño', '4654', 'Surtir almacen', 'Observada', 1, 1, NULL),
(9, '2021-04-20', 'Juan Abel', '12156', 'Devolución de proveedor', 'Observada', 2, 1, NULL),
(15, '2021-04-28', '', '', 'Surtir proyecto', '', 2, 1, NULL),
(16, '2021-04-28', '', '', 'Surtir proyecto', '', 3, 1, NULL),
(18, '2021-05-12', 'Salvador Mendoza ', '12520', 'Almacenar materiales para proyecto', 'Observadas', 3, 2, NULL),
(19, '2021-05-12', 'Jorge ISIDRO ', '356461', 'Almacenar materiales para proyecto', '', 2, 2, NULL),
(20, '2021-05-12', 'Salvador Mendoza', '26131465', 'Almacenar materiales para proyecto', '', 3, 2, NULL),
(21, '2021-05-12', 'Juan abel ', '545132456', 'Almacenar materiales para proyecto', '', 5, 2, NULL),
(22, '2021-05-12', 'Juan Abel ', '25250', 'Almacenar materiales para proyecto', '', 1, 2, NULL),
(23, '2021-05-12', 'Juan Abel', '564651324568', 'Almacenar materiales para proyecto', '', 2, 2, NULL),
(24, '2021-05-12', 'Cris ', '256483131', 'Almacenar materiales para proyecto', '', 3, 2, NULL),
(25, '2021-05-12', 'Cris Sahagun', '65451', 'Almacenar materiales para proyecto', '', 3, 2, NULL),
(26, '2021-05-12', 'Juan Abel Lopez Andrade', '7451', 'Almacenar materiales para proyecto', '', 5, 2, NULL),
(27, '2021-05-12', 'Jorge Isidro Lopez Andrade', '2500', 'Almacenar materiales para proyecto', 'Observadas ', 3, 2, '*'),
(28, '2021-05-17', 'Juan Isidro Hernandez Andrade', '10250', 'Almacenar materiales para proyecto', 'Obseradas', 1, 2, NULL),
(29, '2021-05-20', 'Juan abel Lopez Alcala', '5452', 'Almacenar materiales para proyecto', 'Probando \nlas sumatorias del invetario', 2, 2, NULL),
(31, '2021-05-25', 'Juan Abel Lopez', '2525252', 'Surtir almacen', 'Taladros de sobra para trabajar rapido', 1, 1, NULL),
(32, '2021-05-25', 'Jorge Hernandez Ramirez', '15431', 'Surtir almacen', '50 de todo para que rinda', 2, 1, NULL),
(33, '2021-05-26', 'Juan ', '445', 'Surtir almacen', 'Se metieron mas', 2, 1, NULL),
(34, '2021-05-27', 'Salvador Mendoza Briseño', '1250', 'Almacenar materiales para proyecto', 'Inrtroducimos todo ', 2, 2, NULL),
(35, '2021-06-03', 'Juan Abel Lopez', '231', 'Surtir almacen', 'jkjjkkjhkjhjk', 1, 1, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `herramientas`
--

CREATE TABLE IF NOT EXISTS `herramientas` (
  `CODIGO` mediumint(9) NOT NULL AUTO_INCREMENT,
  `NOMBRE_CORTO` varchar(100) NOT NULL,
  `DESCRIPCION` varchar(300) DEFAULT NULL,
  `CLASE` mediumint(9) NOT NULL,
  `COSTO` decimal(10,0) DEFAULT NULL,
  `IS_ELIMINATED` char(2) DEFAULT NULL,
  PRIMARY KEY (`CODIGO`),
  KEY `CLASE` (`CLASE`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `herramientas`
--

INSERT INTO `herramientas` (`CODIGO`, `NOMBRE_CORTO`, `DESCRIPCION`, `CLASE`, `COSTO`, `IS_ELIMINATED`) VALUES
(1, ' ', NULL, 14, NULL, '*'),
(2, 'Taladro', 'Taladro de alta potencia', 14, '12542', NULL),
(3, 'Taladro pesado Makita', 'Taladro fijo', 14, '253000', NULL),
(4, 'Taladro Mentor', 'taladro', 14, '145642', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `herramientas_inventario`
--

CREATE TABLE IF NOT EXISTS `herramientas_inventario` (
  `ID` mediumint(9) NOT NULL,
  `COSTO` decimal(10,0) NOT NULL,
  `EXISTENCIA` mediumint(9) NOT NULL,
  KEY `ID` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `herramientas_inventario`
--

INSERT INTO `herramientas_inventario` (`ID`, `COSTO`, `EXISTENCIA`) VALUES
(3, '253000', 3000),
(4, '145642', 5000);

-- --------------------------------------------------------

--
-- Table structure for table `introduce_herramientas`
--

CREATE TABLE IF NOT EXISTS `introduce_herramientas` (
  `ID_HRTA` mediumint(9) NOT NULL,
  `FOLIO_ENTRADA` mediumint(9) NOT NULL,
  `CANTIDAD` mediumint(9) NOT NULL,
  `COSTO` decimal(10,0) NOT NULL,
  KEY `ID_HRTA` (`ID_HRTA`),
  KEY `FOLIO_ENTRADA` (`FOLIO_ENTRADA`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `introduce_herramientas`
--

INSERT INTO `introduce_herramientas` (`ID_HRTA`, `FOLIO_ENTRADA`, `CANTIDAD`, `COSTO`) VALUES
(3, 34, 3000, '253000'),
(4, 34, 5000, '145642'),
(2, 1, 200, '12542');

-- --------------------------------------------------------

--
-- Table structure for table `introduce_productos`
--

CREATE TABLE IF NOT EXISTS `introduce_productos` (
  `ID_PRODUCTO` mediumint(9) NOT NULL,
  `FOLIO_ENTRADA` mediumint(9) NOT NULL,
  `COSTO` decimal(10,0) NOT NULL,
  `CANTIDAD` mediumint(9) NOT NULL,
  KEY `FOLIO_ENTRADA` (`FOLIO_ENTRADA`),
  KEY `ID_PRODUCTO` (`ID_PRODUCTO`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `introduce_productos`
--

INSERT INTO `introduce_productos` (`ID_PRODUCTO`, `FOLIO_ENTRADA`, `COSTO`, `CANTIDAD`) VALUES
(2, 35, '2300', 550);

-- --------------------------------------------------------

--
-- Table structure for table `productos`
--

CREATE TABLE IF NOT EXISTS `productos` (
  `ID` mediumint(9) NOT NULL AUTO_INCREMENT,
  `NOMBRE_CORTO` varchar(100) NOT NULL,
  `DESCRIPCION` varchar(300) DEFAULT NULL,
  `UNIDAD` mediumint(9) NOT NULL,
  `COSTO` decimal(10,0) DEFAULT NULL,
  `CLASE` mediumint(9) NOT NULL,
  `SUBCLASE` mediumint(9) NOT NULL,
  `IS_ELIMINATED` char(2) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `UNIDAD` (`UNIDAD`),
  KEY `CLASE` (`CLASE`),
  KEY `SUBCLASE` (`SUBCLASE`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Dumping data for table `productos`
--

INSERT INTO `productos` (`ID`, `NOMBRE_CORTO`, `DESCRIPCION`, `UNIDAD`, `COSTO`, `CLASE`, `SUBCLASE`, `IS_ELIMINATED`) VALUES
(1, ' ', NULL, 1, NULL, 1, 1, '*'),
(2, 'Purificador', 'Purificador de aire de baja densidad', 1, '2300', 1, 1, NULL),
(3, 'Tornillos', 'Tornillo de acero inoxidable, 120 pzas por kilogramo', 1, '200', 9, 22, NULL),
(4, 'Purificador de aire', 'Purificador de aire de alta potencia', 13, '27000', 10, 11, NULL),
(5, 'Aire acondicionado', 'Aire acondicionado de alta potencia', 13, '15000', 20, 21, '*');

-- --------------------------------------------------------

--
-- Table structure for table `productos_inventario`
--

CREATE TABLE IF NOT EXISTS `productos_inventario` (
  `ID` mediumint(9) NOT NULL,
  `COSTO` decimal(10,0) NOT NULL,
  `EXISTENCIA` smallint(6) NOT NULL,
  KEY `ID` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `productos_inventario`
--

INSERT INTO `productos_inventario` (`ID`, `COSTO`, `EXISTENCIA`) VALUES
(2, '2300', 550);

-- --------------------------------------------------------

--
-- Table structure for table `provee`
--

CREATE TABLE IF NOT EXISTS `provee` (
  `ID_PRODUCTO` mediumint(9) NOT NULL,
  `CODIGO_PROVEEDOR` mediumint(9) DEFAULT NULL,
  KEY `CODIGO_PROVEEDOR` (`CODIGO_PROVEEDOR`),
  KEY `ID_PRODUCTO` (`ID_PRODUCTO`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `provee`
--

INSERT INTO `provee` (`ID_PRODUCTO`, `CODIGO_PROVEEDOR`) VALUES
(5, 1),
(5, 2),
(2, 1),
(2, 2),
(3, 5),
(4, 1),
(4, 2),
(4, 3),
(4, 5);

-- --------------------------------------------------------

--
-- Table structure for table `proveedores`
--

CREATE TABLE IF NOT EXISTS `proveedores` (
  `CODIGO` mediumint(9) NOT NULL AUTO_INCREMENT,
  `NOMBRE` varchar(100) NOT NULL,
  `TELEFONO` varchar(20) DEFAULT NULL,
  `DOMICILIO` varchar(150) DEFAULT NULL,
  `TIPO` mediumint(9) NOT NULL,
  `IS_ELIMINATED` varchar(3) DEFAULT NULL,
  PRIMARY KEY (`CODIGO`),
  KEY `TIPO` (`TIPO`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Dumping data for table `proveedores`
--

INSERT INTO `proveedores` (`CODIGO`, `NOMBRE`, `TELEFONO`, `DOMICILIO`, `TIPO`, `IS_ELIMINATED`) VALUES
(1, 'Prominox', '33145453154', 'Las lomas #23', 16, NULL),
(2, 'Ineza', '3314549998', 'Tuxtla #12', 16, NULL),
(3, 'Multiplasticos', '3921172180', 'Pedro moreno #26', 16, NULL),
(5, 'Dominox', '393101450541', 'Av. Ignacio #126', 19, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `proyectos`
--

CREATE TABLE IF NOT EXISTS `proyectos` (
  `ID` mediumint(9) NOT NULL AUTO_INCREMENT,
  `NOMBRE` varchar(150) NOT NULL,
  `DESCRIPCION` varchar(400) DEFAULT NULL,
  `FECHA_CONTRATO` date NOT NULL,
  `FECHA_ENTREGA` date NOT NULL,
  `IS_ELIMINATED` varchar(3) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `proyectos`
--

INSERT INTO `proyectos` (`ID`, `NOMBRE`, `DESCRIPCION`, `FECHA_CONTRATO`, `FECHA_ENTREGA`, `IS_ELIMINATED`) VALUES
(1, '', '', '0000-00-00', '0000-00-00', '*'),
(2, 'Proyecto DBA', 'Proyecto modular para calificacion final ', '2021-04-08', '2021-04-08', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `requieren`
--

CREATE TABLE IF NOT EXISTS `requieren` (
  `FOLIO_ENTRADA` mediumint(9) NOT NULL,
  `ID_PRODUCTO` mediumint(9) DEFAULT NULL,
  `TIPO` varchar(15) DEFAULT NULL,
  `CODIGO_HRTA` mediumint(9) DEFAULT NULL,
  KEY `CODIGO_HRTA` (`CODIGO_HRTA`),
  KEY `ID_PRODUCTO` (`ID_PRODUCTO`),
  KEY `FOLIO_ENTRADA` (`FOLIO_ENTRADA`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `requieren`
--

INSERT INTO `requieren` (`FOLIO_ENTRADA`, `ID_PRODUCTO`, `TIPO`, `CODIGO_HRTA`) VALUES
(15, 2, 'Producto', 1),
(18, 2, 'Producto', 1),
(20, 2, 'Producto', 1),
(20, 3, 'Producto', 1),
(20, 4, 'Producto', 1),
(21, 2, 'Producto', 1),
(21, 3, 'Producto', 1),
(21, 4, 'Producto', 1),
(22, 2, 'Producto', 1),
(23, 3, 'Producto', 1),
(23, 4, 'Producto', 1),
(23, 2, 'Producto', 1),
(24, 2, 'Producto', 1),
(24, 3, 'Producto', 1),
(24, 4, 'Producto', 1),
(25, 2, 'Producto', 1),
(25, 3, 'Producto', 1),
(25, 4, 'Producto', 1),
(26, 2, 'Producto', 1),
(26, 3, 'Producto', 1),
(26, 4, 'Producto', 1),
(28, 1, 'Herramienta', 2),
(28, 1, 'Herramienta', 3),
(28, 1, 'Herramienta', 4),
(27, 2, 'Producto', 1),
(27, 4, 'Producto', 1),
(27, 3, 'Producto', 1),
(29, 2, 'Producto', 1),
(31, 2, 'Producto', 1),
(31, 4, 'Producto', 1),
(31, 1, 'Herramienta', 3),
(32, 2, 'Producto', 1),
(32, 3, 'Producto', 1),
(32, 4, 'Producto', 1),
(32, 1, 'Herramienta', 2),
(32, 1, 'Herramienta', 3),
(32, 1, 'Herramienta', 4),
(33, 2, 'Producto', 1),
(34, 2, 'Producto', 1),
(34, 3, 'Producto', 1),
(34, 4, 'Producto', 1),
(34, 1, 'Herramienta', 2),
(34, 1, 'Herramienta', 3),
(34, 1, 'Herramienta', 4),
(35, 2, 'Producto', 1);

-- --------------------------------------------------------

--
-- Table structure for table `salen_por`
--

CREATE TABLE IF NOT EXISTS `salen_por` (
  `FOLIO_SALIDA` mediumint(9) NOT NULL,
  `ID_PRODUCTO_I` mediumint(9) NOT NULL,
  `ID_HRTA` mediumint(9) NOT NULL,
  `CANTIDAD` mediumint(9) NOT NULL,
  `TIPO` varchar(50) DEFAULT NULL,
  `COSTO` decimal(10,0) NOT NULL,
  `CANTIDAD_TOTAL` mediumint(9) NOT NULL,
  KEY `FOLIO_SALIDA` (`FOLIO_SALIDA`),
  KEY `ID_PRODUCTO_I` (`ID_PRODUCTO_I`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `salen_por`
--

INSERT INTO `salen_por` (`FOLIO_SALIDA`, `ID_PRODUCTO_I`, `ID_HRTA`, `CANTIDAD`, `TIPO`, `COSTO`, `CANTIDAD_TOTAL`) VALUES
(29, 3, 1, 2500, 'Producto', '200', 2100),
(30, 2, 1, 150, 'Producto', '2300', 150),
(31, 4, 1, 60, 'Producto', '27000', 20),
(32, 3, 1, 1500, 'Producto', '200', 200),
(33, 2, 1, 150, 'Producto', '2300', 150),
(34, 2, 1, 200, 'Producto', '2300', 2500),
(35, 2, 1, 1500, 'Producto', '2300', 550),
(36, 2, 1, 500, 'Producto', '2300', 50);

-- --------------------------------------------------------

--
-- Table structure for table `salidas`
--

CREATE TABLE IF NOT EXISTS `salidas` (
  `FOLIO` mediumint(9) NOT NULL AUTO_INCREMENT,
  `FECHA` date NOT NULL,
  `ENTREGO` varchar(150) NOT NULL,
  `REFERENCIA` varchar(200) NOT NULL,
  `MOTIVO` varchar(30) NOT NULL,
  `OBSERVACIONES` varchar(300) DEFAULT NULL,
  `PROYECTO` mediumint(9) DEFAULT NULL,
  `PROVEEDOR` mediumint(9) NOT NULL,
  `IS_ELIMINATED` char(3) DEFAULT NULL,
  PRIMARY KEY (`FOLIO`),
  KEY `PROYECTOS` (`PROYECTO`),
  KEY `PROVEEDORES` (`PROVEEDOR`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=37 ;

--
-- Dumping data for table `salidas`
--

INSERT INTO `salidas` (`FOLIO`, `FECHA`, `ENTREGO`, `REFERENCIA`, `MOTIVO`, `OBSERVACIONES`, `PROYECTO`, `PROVEEDOR`, `IS_ELIMINATED`) VALUES
(29, '2021-06-02', 'Juan Abel Lopez ', '54564', 'Surtir proyecto', 'Espermos ', 2, 1, NULL),
(30, '2021-06-03', 'Salvador mendoza briseño ', '2525', 'Surtir proyecto', '', 2, 1, NULL),
(31, '2021-06-03', 'Juan Abel ', '4545231', 'Surtir proyecto', '', 1, 1, NULL),
(32, '2021-06-03', 'Salvador ', '45564', 'Surtir proyecto', '', 2, 1, NULL),
(33, '2021-06-03', 'Jorge isiddro ', '41564', 'Surtir proyecto', '456231', 1, 1, NULL),
(34, '2021-06-03', 'juanito Abelardo ', '54564', 'Surtir proyecto', 'kljkhkjghjf', 2, 1, NULL),
(35, '2021-06-03', 'Salvador mendoza', '564564', 'Surtir proyecto', '45656231456', 1, 1, NULL),
(36, '2021-06-03', 'Jorge Isisdro ', '6742314', 'Surtir proyecto', '4231521', 2, 1, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `unidades_tipos`
--

CREATE TABLE IF NOT EXISTS `unidades_tipos` (
  `ID` mediumint(9) NOT NULL AUTO_INCREMENT,
  `DATO` varchar(30) NOT NULL,
  `TIPO` varchar(30) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=23 ;

--
-- Dumping data for table `unidades_tipos`
--

INSERT INTO `unidades_tipos` (`ID`, `DATO`, `TIPO`) VALUES
(1, 'Kilo', 'Unidad'),
(2, 'Metro', 'Unidad'),
(3, 'Litro', 'Unidad'),
(9, 'Consumibles', 'Clase'),
(10, 'Purificador', 'Clase'),
(11, 'Purificadores de aire ', 'SubClase'),
(12, 'Purificadores de agua', 'SubClase'),
(13, 'Pieza', 'Unidad'),
(14, 'Herramienta de mano pesada', 'Clase_hrta'),
(15, 'Herramienta de piso ', 'Clase_hrta'),
(16, 'Proveente', 'Tipo_proveedor'),
(17, 'Proveente', 'Tipo_proveedor'),
(18, 'De laminas', 'Tipo_proveedor'),
(19, 'De acero', 'Tipo_proveedor'),
(20, 'Aire acondicionados', 'Clase'),
(21, 'Aire acondicionados alta', 'SubClase'),
(22, 'tornillos', 'SubClase');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `entradas`
--
ALTER TABLE `entradas`
  ADD CONSTRAINT `entradas_ibfk_1` FOREIGN KEY (`CODIGO_PROVEEDOR`) REFERENCES `proveedores` (`CODIGO`),
  ADD CONSTRAINT `entradas_ibfk_2` FOREIGN KEY (`PROYECTO`) REFERENCES `proyectos` (`ID`);

--
-- Constraints for table `herramientas`
--
ALTER TABLE `herramientas`
  ADD CONSTRAINT `herramientas_ibfk_1` FOREIGN KEY (`CLASE`) REFERENCES `unidades_tipos` (`ID`) ON UPDATE CASCADE;

--
-- Constraints for table `herramientas_inventario`
--
ALTER TABLE `herramientas_inventario`
  ADD CONSTRAINT `herramientas_inventario_ibfk_1` FOREIGN KEY (`ID`) REFERENCES `herramientas` (`CODIGO`);

--
-- Constraints for table `introduce_herramientas`
--
ALTER TABLE `introduce_herramientas`
  ADD CONSTRAINT `introduce_herramientas_ibfk_1` FOREIGN KEY (`ID_HRTA`) REFERENCES `herramientas` (`CODIGO`),
  ADD CONSTRAINT `introduce_herramientas_ibfk_2` FOREIGN KEY (`FOLIO_ENTRADA`) REFERENCES `entradas` (`FOLIO`);

--
-- Constraints for table `introduce_productos`
--
ALTER TABLE `introduce_productos`
  ADD CONSTRAINT `introduce_productos_ibfk_2` FOREIGN KEY (`FOLIO_ENTRADA`) REFERENCES `entradas` (`FOLIO`),
  ADD CONSTRAINT `introduce_productos_ibfk_3` FOREIGN KEY (`ID_PRODUCTO`) REFERENCES `productos_inventario` (`ID`);

--
-- Constraints for table `productos`
--
ALTER TABLE `productos`
  ADD CONSTRAINT `productos_ibfk_1` FOREIGN KEY (`UNIDAD`) REFERENCES `unidades_tipos` (`ID`),
  ADD CONSTRAINT `productos_ibfk_2` FOREIGN KEY (`CLASE`) REFERENCES `unidades_tipos` (`ID`),
  ADD CONSTRAINT `productos_ibfk_3` FOREIGN KEY (`SUBCLASE`) REFERENCES `unidades_tipos` (`ID`);

--
-- Constraints for table `productos_inventario`
--
ALTER TABLE `productos_inventario`
  ADD CONSTRAINT `productos_inventario_ibfk_1` FOREIGN KEY (`ID`) REFERENCES `productos` (`ID`);

--
-- Constraints for table `provee`
--
ALTER TABLE `provee`
  ADD CONSTRAINT `provee_ibfk_2` FOREIGN KEY (`CODIGO_PROVEEDOR`) REFERENCES `proveedores` (`CODIGO`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `provee_ibfk_3` FOREIGN KEY (`ID_PRODUCTO`) REFERENCES `productos` (`ID`);

--
-- Constraints for table `proveedores`
--
ALTER TABLE `proveedores`
  ADD CONSTRAINT `proveedores_ibfk_1` FOREIGN KEY (`TIPO`) REFERENCES `unidades_tipos` (`ID`);

--
-- Constraints for table `requieren`
--
ALTER TABLE `requieren`
  ADD CONSTRAINT `requieren_ibfk_5` FOREIGN KEY (`ID_PRODUCTO`) REFERENCES `productos` (`ID`),
  ADD CONSTRAINT `requieren_ibfk_6` FOREIGN KEY (`FOLIO_ENTRADA`) REFERENCES `entradas` (`FOLIO`) ON DELETE CASCADE;

--
-- Constraints for table `salen_por`
--
ALTER TABLE `salen_por`
  ADD CONSTRAINT `salen_por_ibfk_1` FOREIGN KEY (`FOLIO_SALIDA`) REFERENCES `salidas` (`FOLIO`);

--
-- Constraints for table `salidas`
--
ALTER TABLE `salidas`
  ADD CONSTRAINT `salidas_ibfk_1` FOREIGN KEY (`PROYECTO`) REFERENCES `proyectos` (`ID`),
  ADD CONSTRAINT `salidas_ibfk_2` FOREIGN KEY (`PROVEEDOR`) REFERENCES `proveedores` (`CODIGO`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
