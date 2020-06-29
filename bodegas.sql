-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost
-- Tiempo de generación: 23-06-2020 a las 05:12:42
-- Versión del servidor: 10.4.10-MariaDB
-- Versión de PHP: 7.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `bodegas`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cuenta`
--

CREATE TABLE `cuenta` (
  `id_usuario` int(8) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `apellido` varchar(255) NOT NULL,
  `dui` varchar(20) NOT NULL,
  `direccion` varchar(255) NOT NULL,
  `telefono` int(20) NOT NULL,
  `nombre_usuario` varchar(20) NOT NULL,
  `correo` varchar(100) NOT NULL,
  `id_rol` int(8) NOT NULL,
  `contrasena` varchar(300) NOT NULL,
  `id_empresa` int(8) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `cuenta`
--

INSERT INTO `cuenta` (`id_usuario`, `nombre`, `apellido`, `dui`, `direccion`, `telefono`, `nombre_usuario`, `correo`, `id_rol`, `contrasena`, `id_empresa`, `created_at`) VALUES
(5, 'Luis', 'Espinoza', '05380307-5', 'San Sebastián Salitrillo', 70495077, 'luis', 'luis@gmail.com', 1, '5994471abb01112afcc18159f6cc74b4f511b99806da59b3caf5a9c173cacfc5', 1, '2020-05-24 19:52:39'),
(7, 'Antonio', 'Medina', '45646464-6', 'Santa Tecla', 49797979, 'amedina', 'medina@gmail.com', 3, '5994471abb01112afcc18159f6cc74b4f511b99806da59b3caf5a9c173cacfc5', 2, '2020-06-19 19:12:42');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empresa`
--

CREATE TABLE `empresa` (
  `id_empresa` int(8) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `telefono` varchar(20) NOT NULL,
  `direccion` varchar(255) NOT NULL,
  `estado` tinyint(4) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `empresa`
--

INSERT INTO `empresa` (`id_empresa`, `nombre`, `telefono`, `direccion`, `estado`) VALUES
(1, 'LuisxLuis', '22002200', 'Santa Ana, Santa Ana', 1),
(2, 'nueva', '12345', 'prueba', 1),
(3, 'a versa', '123456789', 'a vers', 1),
(4, 'Otra', '12345', 'a vers', 0),
(5, 'Probando', '74469898', 'Santa Tecla', 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto`
--

CREATE TABLE `producto` (
  `id_producto` int(8) NOT NULL,
  `codigo` varchar(8) NOT NULL,
  `id_empresa` int(8) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `cantidad` int(10) DEFAULT 0,
  `numero_bodega` int(8) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `producto`
--

INSERT INTO `producto` (`id_producto`, `codigo`, `id_empresa`, `nombre`, `cantidad`, `numero_bodega`, `created_at`) VALUES
(1, '1', 2, 'Galletas \"nueva\"', 7, 1, '2020-06-20 16:55:28');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `registro`
--

CREATE TABLE `registro` (
  `id_registro` int(8) NOT NULL,
  `fecha_ingresa` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `entrada` int(10) DEFAULT NULL,
  `salida` int(10) DEFAULT NULL,
  `id_producto` int(8) NOT NULL,
  `cantidad` int(8) NOT NULL,
  `usuario_recibe` int(8) NOT NULL,
  `usuario_entrega` int(8) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `registro`
--

INSERT INTO `registro` (`id_registro`, `fecha_ingresa`, `entrada`, `salida`, `id_producto`, `cantidad`, `usuario_recibe`, `usuario_entrega`) VALUES
(1, '2020-06-20 18:19:08', 1, 0, 1, 0, 5, 7),
(2, '2020-06-20 18:19:21', 0, 1, 1, 0, 7, 5),
(3, '2020-06-22 17:35:49', 0, 1, 1, 23, 7, 5),
(4, '2020-06-22 18:26:54', 1, 0, 1, 20, 5, 7),
(5, '2020-06-22 18:42:29', 0, 1, 1, 14, 7, 5);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `rol`
--

CREATE TABLE `rol` (
  `id_rol` int(8) NOT NULL,
  `rol` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `rol`
--

INSERT INTO `rol` (`id_rol`, `rol`) VALUES
(1, 'Administrador'),
(2, 'Inventario'),
(3, 'Externo');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `cuenta`
--
ALTER TABLE `cuenta`
  ADD PRIMARY KEY (`id_usuario`),
  ADD KEY `id_empresa` (`id_empresa`),
  ADD KEY `id_rol` (`id_rol`);

--
-- Indices de la tabla `empresa`
--
ALTER TABLE `empresa`
  ADD PRIMARY KEY (`id_empresa`);

--
-- Indices de la tabla `producto`
--
ALTER TABLE `producto`
  ADD PRIMARY KEY (`id_producto`),
  ADD KEY `id_empresa` (`id_empresa`);

--
-- Indices de la tabla `registro`
--
ALTER TABLE `registro`
  ADD PRIMARY KEY (`id_registro`),
  ADD KEY `id_producto` (`id_producto`),
  ADD KEY `usuario_recibe` (`usuario_recibe`),
  ADD KEY `usuario_entrega` (`usuario_entrega`);

--
-- Indices de la tabla `rol`
--
ALTER TABLE `rol`
  ADD PRIMARY KEY (`id_rol`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `cuenta`
--
ALTER TABLE `cuenta`
  MODIFY `id_usuario` int(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `empresa`
--
ALTER TABLE `empresa`
  MODIFY `id_empresa` int(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `producto`
--
ALTER TABLE `producto`
  MODIFY `id_producto` int(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `registro`
--
ALTER TABLE `registro`
  MODIFY `id_registro` int(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `rol`
--
ALTER TABLE `rol`
  MODIFY `id_rol` int(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `cuenta`
--
ALTER TABLE `cuenta`
  ADD CONSTRAINT `cuenta_ibfk_1` FOREIGN KEY (`id_rol`) REFERENCES `rol` (`id_rol`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `cuenta_ibfk_2` FOREIGN KEY (`id_empresa`) REFERENCES `empresa` (`id_empresa`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `producto`
--
ALTER TABLE `producto`
  ADD CONSTRAINT `producto_ibfk_1` FOREIGN KEY (`id_empresa`) REFERENCES `empresa` (`id_empresa`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `registro`
--
ALTER TABLE `registro`
  ADD CONSTRAINT `registro_ibfk_1` FOREIGN KEY (`id_producto`) REFERENCES `producto` (`id_producto`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `registro_ibfk_2` FOREIGN KEY (`usuario_recibe`) REFERENCES `cuenta` (`id_usuario`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `registro_ibfk_3` FOREIGN KEY (`usuario_entrega`) REFERENCES `cuenta` (`id_usuario`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
