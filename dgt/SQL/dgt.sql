CREATE DATABASE  IF NOT EXISTS `dgt` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `dgt`;
-- MySQL dump 10.13  Distrib 8.0.13, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: dgt
-- ------------------------------------------------------
-- Server version	8.0.13

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `agente`
--

DROP TABLE IF EXISTS `agente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `agente` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `placa` int(11) NOT NULL,
  `id_departamento` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `placa_UNIQUE` (`placa`),
  KEY `fk_agente_departamento_idx` (`id_departamento`),
  CONSTRAINT `fk_agente_departamento` FOREIGN KEY (`id_departamento`) REFERENCES `departamento` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `agente`
--

LOCK TABLES `agente` WRITE;
/*!40000 ALTER TABLE `agente` DISABLE KEYS */;
INSERT INTO `agente` VALUES (1,'Majonei',123456,39),(2,'Johnny Walker',987654,36),(3,'Monk',324586,38),(4,'Takelberry',87456,37),(5,'Tontimmy',998776,37);
/*!40000 ALTER TABLE `agente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `coche`
--

DROP TABLE IF EXISTS `coche`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `coche` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `matricula` varchar(10) NOT NULL,
  `modelo` varchar(45) NOT NULL DEFAULT 'cuatro latas',
  `km` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `matricula_UNIQUE` (`matricula`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coche`
--

LOCK TABLES `coche` WRITE;
/*!40000 ALTER TABLE `coche` DISABLE KEYS */;
INSERT INTO `coche` VALUES (1,'3548MKZ','Toyota Yaris',500),(2,'9605EFH','Fiat multipla',800),(3,'5674MBD','GRT',1800),(4,'BI0020AZ','flagoneta',47500);
/*!40000 ALTER TABLE `coche` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `departamento`
--

DROP TABLE IF EXISTS `departamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `departamento` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `departamento`
--

LOCK TABLES `departamento` WRITE;
/*!40000 ALTER TABLE `departamento` DISABLE KEYS */;
INSERT INTO `departamento` VALUES (31,'ventas'),(33,'ingenieria'),(34,'produccion'),(35,'mercadeo'),(36,'Alcholemia'),(37,'oficinistas'),(38,'fealdad'),(39,'velocidad');
/*!40000 ALTER TABLE `departamento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `empleado`
--

DROP TABLE IF EXISTS `empleado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `empleado` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `id_departamento` int(11) DEFAULT NULL,
  `fecha_contrato` date DEFAULT NULL,
  `salario` float DEFAULT '900',
  `comision` float DEFAULT '0',
  `id_jefe` int(11) DEFAULT NULL,
  `id_puesto` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empleado`
--

LOCK TABLES `empleado` WRITE;
/*!40000 ALTER TABLE `empleado` DISABLE KEYS */;
INSERT INTO `empleado` VALUES (1,'Andrade',31,NULL,900,0,NULL,NULL),(2,'Jordán',33,NULL,900,0,NULL,NULL),(3,'Steinberg',33,NULL,900,0,NULL,NULL),(4,'Róbinson',34,NULL,900,0,NULL,NULL),(5,'Zolano',34,NULL,900,0,NULL,NULL),(6,'Gaspar',36,NULL,900,0,NULL,NULL),(7,'borja',38,'2003-08-02',5000,1500,16,2),(8,'hector',38,'2014-11-25',3000,2000,16,2),(9,'andoni',38,'2016-08-27',2500,1000,16,2),(10,'xabier',38,'2019-01-09',900,0,NULL,3),(11,'david',38,'2018-08-13',900,0,NULL,3),(12,'Imanol',38,'2018-07-07',1350,350,13,1),(13,'Oscar',38,'2017-07-07',5000,4000,NULL,1),(14,'Amaia',38,'2016-10-25',2500,700,16,2),(15,'Daniel',38,'2015-07-07',1900,300,16,2),(16,'Ander',38,'2019-01-07',28000,7,16,2);
/*!40000 ALTER TABLE `empleado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `multa`
--

DROP TABLE IF EXISTS `multa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `multa` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `importe` float DEFAULT '50',
  `concepto` varchar(255) DEFAULT NULL,
  `fecha_alta` datetime DEFAULT CURRENT_TIMESTAMP,
  `id_coche` int(11) NOT NULL,
  `id_agente` int(11) NOT NULL,
  `fecha_mod` datetime DEFAULT CURRENT_TIMESTAMP,
  `fecha_baja` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_multa_coches_idx` (`id_coche`) /*!80000 INVISIBLE */,
  KEY `fk__idx` (`id_agente`),
  CONSTRAINT `fk_multa_agente` FOREIGN KEY (`id_agente`) REFERENCES `agente` (`id`),
  CONSTRAINT `fk_multa_coches` FOREIGN KEY (`id_coche`) REFERENCES `coche` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `multa`
--

LOCK TABLES `multa` WRITE;
/*!40000 ALTER TABLE `multa` DISABLE KEYS */;
INSERT INTO `multa` VALUES (1,200,'por feo','2019-01-07 10:37:13',2,4,NULL,'2019-01-14 08:56:31'),(2,500,'exceso velocidad a 240km/h','2019-01-07 10:38:43',4,4,NULL,NULL),(3,700,'por empinar codo 8.0','2019-01-07 10:41:09',1,2,NULL,NULL),(4,700,'por empinar codo 8.0','2018-12-31 22:40:52',1,2,NULL,NULL),(5,800,'por velcoidad','2019-01-07 13:16:21',4,4,NULL,NULL),(6,300,'otra multa','2019-01-07 13:48:49',4,1,NULL,NULL),(7,45,'tuputamadre','2019-01-07 14:48:49',2,4,NULL,NULL),(8,43333,'dfgrsthrtsh','2019-01-07 12:34:49',2,4,NULL,NULL),(9,11,'vvvvv','2019-01-07 13:21:42',2,4,NULL,'2019-01-14 09:08:11'),(10,23,'sssss','2019-02-07 11:45:49',2,4,NULL,NULL),(11,55555,'dcxxxx','2019-01-10 14:23:44',2,4,NULL,NULL),(12,21.53,'234567876532weerewtrwywete','2019-01-14 10:25:30',1,4,'2019-01-14 10:25:30','2019-01-15 11:28:28'),(13,0,'por muy feo','2019-01-14 11:06:39',1,4,'2019-01-14 11:06:39','2019-01-15 13:03:44'),(14,500,'por muy feo','2019-01-14 13:02:47',1,4,'2019-01-14 13:02:47',NULL),(15,500.7,'vas mas borracho que el tato','2019-01-15 12:25:10',2,4,'2019-01-15 12:25:10',NULL),(16,500.7,'borrachuzo de mierda','2019-01-15 12:31:15',2,4,'2019-01-15 12:31:15',NULL),(17,500.7,'borrachuzo de mierda','2019-01-15 12:33:21',2,4,'2019-01-15 12:33:21',NULL),(18,500.7,'borrachuzo de mierda','2019-01-15 12:35:22',2,4,'2019-01-15 12:35:22',NULL),(19,500.7,'borrachuzo de mierda','2019-01-15 12:37:48',2,4,'2019-01-15 12:37:48',NULL),(20,500.7,'borrachuzo de mierda','2019-01-15 12:38:49',2,4,'2019-01-15 12:38:49',NULL),(21,500.7,'borrachuzo de mierda','2019-01-15 12:39:12',2,4,'2019-01-15 12:39:12',NULL),(22,500.7,'borrachuzo de mierda','2019-01-15 12:42:02',2,4,'2019-01-15 12:42:02',NULL),(23,500.7,'borrachuzo de mierda','2019-01-15 12:45:28',2,4,'2019-01-15 12:45:28',NULL),(24,500.7,'borrachuzo de mierda','2019-01-15 12:45:32',2,4,'2019-01-15 12:45:32',NULL),(25,500.7,'borrachuzo de mierda','2019-01-15 12:46:07',2,4,'2019-01-15 12:46:07',NULL),(26,500.7,'borrachuzo de mierda','2019-01-15 12:48:21',2,4,'2019-01-15 12:48:21',NULL),(27,500.7,'borrachuzo de mierda','2019-01-15 12:49:41',2,4,'2019-01-15 12:49:41','2019-01-15 13:09:38');
/*!40000 ALTER TABLE `multa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `puesto`
--

DROP TABLE IF EXISTS `puesto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `puesto` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `id_departamento` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nombre_UNIQUE` (`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `puesto`
--

LOCK TABLES `puesto` WRITE;
/*!40000 ALTER TABLE `puesto` DISABLE KEYS */;
INSERT INTO `puesto` VALUES (1,'secretari@',38),(2,'programd@r',38),(3,'becari@',38);
/*!40000 ALTER TABLE `puesto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'dgt'
--
/*!50003 DROP PROCEDURE IF EXISTS `pa_agente_getById` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_VALUE_ON_ZERO' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `pa_agente_getById`(IN p_id LONG)
BEGIN
	SELECT 
		id, 
        nombre 
    FROM 
		agente 
    WHERE 
		id = p_id;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `pa_coche_getMatricula` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_VALUE_ON_ZERO' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `pa_coche_getMatricula`(IN p_matricula VARCHAR(50))
BEGIN
SELECT id,matricula,modelo,km FROM coche WHERE matricula= p_matricula;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `pa_coche_getMatriculas` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_VALUE_ON_ZERO' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `pa_coche_getMatriculas`()
BEGIN
	SELECT 
		id, 
		matricula, 
		modelo, 
        km 
	FROM 
		coche 
    ORDER BY 
		id 
	DESC LIMIT 100;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `pa_multa_getAllByUserBaja` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_VALUE_ON_ZERO' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `pa_multa_getAllByUserBaja`(IN p_id LONG)
BEGIN
	SELECT m.id, m.fecha_alta, m.fecha_baja, c.matricula, c.modelo FROM multa m, coche c WHERE m.id_coche = c.id AND m.id_agente = p_id AND fecha_baja IS NOT NULL ORDER BY m.id DESC LIMIT 1000;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `pa_multa_getAll_User` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `pa_multa_getAll_User`(IN p_id_agente INT)
BEGIN
SELECT m.id, m.fecha_alta, c.matricula, c.modelo FROM multa m, coche c WHERE m.id_coche = c.id AND m.id_agente = p_id_agente AND fecha_baja IS NULL ORDER BY m.id DESC LIMIT 1000;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `pa_multa_getById` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `pa_multa_getById`(IN p_id_multa LONG)
BEGIN
SELECT m.id, m.fecha_alta, m.importe, m.concepto, c.matricula, modelo, km FROM multa m, coche c WHERE m.id_coche = c.id AND m.id = p_id_multa;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `pa_multa_getByIdBaja` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_VALUE_ON_ZERO' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `pa_multa_getByIdBaja`(IN p_id LONG)
BEGIN
	SELECT 
		m.id, 
		m.fecha_alta, 
        m.fecha_baja, 
        m.importe, 
        m.concepto, 
        c.matricula, 
        modelo, 
        km 
	FROM 
		multa m, 
        coche c 
	WHERE 
		m.id_coche = c.id AND 
        m.id = p_id;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `pa_multa_insert` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_VALUE_ON_ZERO' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `pa_multa_insert`(IN p_importe FLOAT, IN p_concepto VARCHAR(255), IN p_id_coche LONG, IN p_id_agente LONG, OUT o_id LONG)
BEGIN
INSERT INTO multa (`importe`, `concepto`, `id_coche`, `id_agente`) VALUES (p_importe, p_concepto, p_id_coche, p_id_agente);

SET o_id=last_insert_id();

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `pa_multa_update` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_VALUE_ON_ZERO' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `pa_multa_update`(IN p_id LONG)
BEGIN
	UPDATE 
		multa 
    SET 
		fecha_baja = CURRENT_TIMESTAMP  
	WHERE 
		id = p_id;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-01-15 13:10:47
