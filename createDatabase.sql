-- MySQL dump 10.13  Distrib 5.6.24, for osx10.8 (x86_64)
--
-- Host: 127.0.0.1    Database: bancosd
-- ------------------------------------------------------
-- Server version	5.6.27

create database IF not Exists bancosd;
use bancosd;

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Evento`
--

DROP TABLE IF EXISTS `Evento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Evento` (
  `idEvento` int(11) NOT NULL,
  `nome` varchar(100) DEFAULT NULL,
  `tipo` varchar(40) DEFAULT NULL,
  `horaInicial` varchar(5) DEFAULT NULL,
  `horaFinal` varchar(5) DEFAULT NULL,
  `date` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`idEvento`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Evento`
--

LOCK TABLES `Evento` WRITE;
/*!40000 ALTER TABLE `Evento` DISABLE KEYS */;
INSERT INTO `Evento` VALUES (251,'Vinicius','Palestra','10:00','12:00','10/10/2000'),(603,'Evento 1','Oficina','12:00','14:00','12/11/2016'),(604,'Evento 2','Minicurso','13:00','18:00','12/11/2015');
/*!40000 ALTER TABLE `Evento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Matricula`
--

DROP TABLE IF EXISTS `Matricula`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Matricula` (
  `idMatricula` int(11) NOT NULL,
  `horaEntrada` time DEFAULT NULL,
  `horaSaida` time DEFAULT NULL,
  PRIMARY KEY (`idMatricula`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Matricula`
--

LOCK TABLES `Matricula` WRITE;
/*!40000 ALTER TABLE `Matricula` DISABLE KEYS */;
/*!40000 ALTER TABLE `Matricula` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Relatório`
--

DROP TABLE IF EXISTS `Relatório`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Relatório` (
  `idRelatório` int(11) NOT NULL,
  `cargaHorariaTotal` int(11) DEFAULT NULL,
  PRIMARY KEY (`idRelatório`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Relatório`
--

LOCK TABLES `Relatório` WRITE;
/*!40000 ALTER TABLE `Relatório` DISABLE KEYS */;
/*!40000 ALTER TABLE `Relatório` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SEQUENCE`
--

DROP TABLE IF EXISTS `SEQUENCE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SEQUENCE` (
  `SEQ_NAME` varchar(50) NOT NULL,
  `SEQ_COUNT` decimal(38,0) DEFAULT NULL,
  PRIMARY KEY (`SEQ_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SEQUENCE`
--

LOCK TABLES `SEQUENCE` WRITE;
/*!40000 ALTER TABLE `SEQUENCE` DISABLE KEYS */;
INSERT INTO `SEQUENCE` VALUES ('SEQ_GEN',650);
/*!40000 ALTER TABLE `SEQUENCE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Sorteio`
--

DROP TABLE IF EXISTS `Sorteio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Sorteio` (
  `idSorteio` int(11) NOT NULL,
  `brinde` varchar(45) DEFAULT NULL,
  `Matricula_idMatricula` int(11) NOT NULL,
  PRIMARY KEY (`idSorteio`,`Matricula_idMatricula`),
  KEY `fk_Sorteio_Matricula1_idx` (`Matricula_idMatricula`),
  CONSTRAINT `fk_Sorteio_Matricula1` FOREIGN KEY (`Matricula_idMatricula`) REFERENCES `Matricula` (`idMatricula`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Sorteio`
--

LOCK TABLES `Sorteio` WRITE;
/*!40000 ALTER TABLE `Sorteio` DISABLE KEYS */;
/*!40000 ALTER TABLE `Sorteio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Turma`
--

DROP TABLE IF EXISTS `Turma`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Turma` (
  `idTurma` int(11) NOT NULL,
  `data` date DEFAULT NULL,
  `horaInicial` time DEFAULT NULL,
  `horaFinal` time DEFAULT NULL,
  `numero` int(11) DEFAULT NULL,
  PRIMARY KEY (`idTurma`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Turma`
--

LOCK TABLES `Turma` WRITE;
/*!40000 ALTER TABLE `Turma` DISABLE KEYS */;
/*!40000 ALTER TABLE `Turma` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Usuario`
--

DROP TABLE IF EXISTS `Usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Usuario` (
  `idUsuario` int(11) NOT NULL,
  `nome` varchar(45) DEFAULT NULL,
  `senha` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idUsuario`),
  UNIQUE KEY `senha_UNIQUE` (`senha`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Usuario`
--

LOCK TABLES `Usuario` WRITE;
/*!40000 ALTER TABLE `Usuario` DISABLE KEYS */;
INSERT INTO `Usuario` VALUES (1,'usuario','senha');
/*!40000 ALTER TABLE `Usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `aluno`
--

DROP TABLE IF EXISTS `aluno`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `aluno` (
  `idAluno` int(11) NOT NULL,
  `ra` int(11) DEFAULT NULL,
  `nome` varchar(100) DEFAULT NULL,
  `curso` varchar(100) DEFAULT NULL,
  `periodo` int(11) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `telefone` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idAluno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `aluno`
--

LOCK TABLES `aluno` WRITE;
/*!40000 ALTER TABLE `aluno` DISABLE KEYS */;
INSERT INTO `aluno` VALUES (152,1372475,'Vinicius Custodio','Engenharia Civil',7,'vinihcius.custodio@gmail.com','(42) 8425-9609'),(551,1372475,'Lucas Emanuel','Engenharia Mecânica',1,'vinihcius.custodio@gmail.com','(42) 8425-9609'),(601,1372475,'Marielly F. L.','Ciência da Computação',10,'vinihcius.custodio@gmail.com','(42) 8425-9609'),(602,1372475,'Pedro','Ciências Naturais',3,'pedro@hotmail.com','(42) 8425-9609');
/*!40000 ALTER TABLE `aluno` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-05-30 13:55:09
