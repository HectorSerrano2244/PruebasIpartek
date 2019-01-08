-- phpMyAdmin SQL Dump
-- version 4.2.7.1
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 08-01-2019 a las 23:47:05
-- Versión del servidor: 5.6.20
-- Versión de PHP: 5.5.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `dgt`
--
CREATE DATABASE IF NOT EXISTS `dgt` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `dgt`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `agente`
--

DROP TABLE IF EXISTS `agente`;
CREATE TABLE IF NOT EXISTS `agente` (
`id` int(11) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `placa` int(11) NOT NULL,
  `id_departamento` int(11) DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=6 ;

--
-- Volcado de datos para la tabla `agente`
--

INSERT INTO `agente` (`id`, `nombre`, `placa`, `id_departamento`) VALUES
(1, 'Majonei', 123456, 39),
(2, 'Johnny Walker', 987654, 36),
(3, 'Monk', 324586, 38),
(4, 'Takelberry', 87456, 37),
(5, 'Tontimmy', 998776, 37);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `coche`
--

DROP TABLE IF EXISTS `coche`;
CREATE TABLE IF NOT EXISTS `coche` (
`id` int(11) NOT NULL,
  `matricula` varchar(10) NOT NULL,
  `modelo` varchar(45) NOT NULL DEFAULT 'cuatro latas',
  `km` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=6 ;

--
-- Volcado de datos para la tabla `coche`
--

INSERT INTO `coche` (`id`, `matricula`, `modelo`, `km`) VALUES
(1, '3548MKZ', 'Toyota Yaris', 500),
(2, '9605EFH', 'Fiat multipla', 800),
(3, '5674MBD', 'GRT', 1800),
(4, 'BI0020AZ', 'flagoneta', 47500);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `departamento`
--

DROP TABLE IF EXISTS `departamento`;
CREATE TABLE IF NOT EXISTS `departamento` (
`id` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=40 ;

--
-- Volcado de datos para la tabla `departamento`
--

INSERT INTO `departamento` (`id`, `nombre`) VALUES
(31, 'ventas'),
(33, 'ingenieria'),
(34, 'produccion'),
(35, 'mercadeo'),
(36, 'Alcholemia'),
(37, 'oficinistas'),
(38, 'fealdad'),
(39, 'velocidad');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empleado`
--

DROP TABLE IF EXISTS `empleado`;
CREATE TABLE IF NOT EXISTS `empleado` (
`id` int(11) NOT NULL,
  `apellido` varchar(50) NOT NULL,
  `id_departamento` int(11) DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=7 ;

--
-- Volcado de datos para la tabla `empleado`
--

INSERT INTO `empleado` (`id`, `apellido`, `id_departamento`) VALUES
(1, 'Andrade', 31),
(2, 'Jordan', 33),
(3, 'Steinberg', 33),
(4, 'Róbinson', 34),
(5, 'Zolano', 34),
(6, 'Gaspar', 36);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `multa`
--

DROP TABLE IF EXISTS `multa`;
CREATE TABLE IF NOT EXISTS `multa` (
`id` int(11) NOT NULL,
  `importe` float DEFAULT '50',
  `concepto` varchar(255) DEFAULT NULL,
  `fecha` datetime DEFAULT CURRENT_TIMESTAMP,
  `id_coche` int(11) NOT NULL,
  `id_agente` int(11) DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=7 ;

--
-- Volcado de datos para la tabla `multa`
--

INSERT INTO `multa` (`id`, `importe`, `concepto`, `fecha`, `id_coche`, `id_agente`) VALUES
(1, 200, 'por feo', '2019-01-07 10:37:13', 2, 3),
(2, 500, 'exceso velocidad a 240km/h', '2019-01-07 10:38:43', 3, 4),
(3, 700, 'por empinar codo 8.0', '2019-01-07 10:41:09', 1, 2),
(4, 700, 'por empinar codo 8.0', '2018-12-31 22:40:52', 1, 2),
(5, 800, 'por velcoidad', '2019-01-07 13:16:21', 4, 4),
(6, 300, 'otra multa', '2019-01-07 13:48:49', 2, 4);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `agente`
--
ALTER TABLE `agente`
 ADD PRIMARY KEY (`id`), ADD UNIQUE KEY `placa_UNIQUE` (`placa`), ADD KEY `fk_agente_departamento_idx` (`id_departamento`);

--
-- Indices de la tabla `coche`
--
ALTER TABLE `coche`
 ADD PRIMARY KEY (`id`), ADD UNIQUE KEY `matricula_UNIQUE` (`matricula`);

--
-- Indices de la tabla `departamento`
--
ALTER TABLE `departamento`
 ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `empleado`
--
ALTER TABLE `empleado`
 ADD PRIMARY KEY (`id`), ADD KEY `fk_empleado_departamento_idx` (`id_departamento`);

--
-- Indices de la tabla `multa`
--
ALTER TABLE `multa`
 ADD PRIMARY KEY (`id`), ADD KEY `fk_multa_coches_idx` (`id_coche`), ADD KEY `fk__idx` (`id_agente`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `agente`
--
ALTER TABLE `agente`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT de la tabla `coche`
--
ALTER TABLE `coche`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT de la tabla `departamento`
--
ALTER TABLE `departamento`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=40;
--
-- AUTO_INCREMENT de la tabla `empleado`
--
ALTER TABLE `empleado`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT de la tabla `multa`
--
ALTER TABLE `multa`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=7;
--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `agente`
--
ALTER TABLE `agente`
ADD CONSTRAINT `fk_agente_departamento` FOREIGN KEY (`id_departamento`) REFERENCES `departamento` (`id`);

--
-- Filtros para la tabla `multa`
--
ALTER TABLE `multa`
ADD CONSTRAINT `fk_multa_agente` FOREIGN KEY (`id_agente`) REFERENCES `agente` (`id`),
ADD CONSTRAINT `fk_multa_coches` FOREIGN KEY (`id_coche`) REFERENCES `coche` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
