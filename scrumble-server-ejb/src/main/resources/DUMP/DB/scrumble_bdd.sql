-- phpMyAdmin SQL Dump
-- version 3.4.10.1deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Sep 22, 2012 at 04:35 PM
-- Server version: 5.5.24
-- PHP Version: 5.3.10-1ubuntu3.4

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `scrumble_bdd`
--
DROP DATABASE `scrumble_bdd`;
CREATE DATABASE `scrumble_bdd` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `scrumble_bdd`;

-- --------------------------------------------------------

--
-- Table structure for table `member`
--

DROP TABLE IF EXISTS `member`;
CREATE TABLE IF NOT EXISTS `member` (
  `id_member` int(11) NOT NULL AUTO_INCREMENT,
  `firstname` varchar(45) DEFAULT NULL,
  `lastname` varchar(75) DEFAULT NULL,
  `login` varchar(20) NOT NULL,
  `password` varchar(45) NOT NULL,
  `email` varchar(75) DEFAULT NULL,
  `id_role` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_member`),
  UNIQUE KEY `login_UNIQUE` (`login`),
  KEY `fk_id_role_idx` (`id_role`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=11 ;

--
-- Dumping data for table `member`
--

INSERT INTO `member` (`id_member`, `firstname`, `lastname`, `login`, `password`, `email`, `id_role`) VALUES
(1, 'Cyril', 'EPINAT', 'cyril', '9b22e8ac450bf8dabd90915b1b00a15c', 'epicyr@gmail.com', 1),
(2, 'Arnaud', 'DE LA CRUZ', 'arnaud', 'ea56f45e66e2c57fc79df7dc3ae0437b', 'arnaud.delacruz@gmail.com', 1),
(3, 'Soukeyna', 'GAYE', 'soukeyna', '416c21534a55783c70de4b14b8719851', 'soukeynag@gmail.com', 1),
(4, 'Romain', 'THIVILLON', 'romain', '5026bc63b5418ffdb54f238db245ec01', 'romain.thivillon@gmail.com', 1),
(5, 'Jérémy', 'BARASCUT', 'jeremy', '6967cabefd763ac1a1a88e11159957db', 'jeremy.barascut@gmail.com', 1),
(6, 'Guest', 'Scrumble', 'guest', '084e0343a0486ff05530df6c705c8bb4', 'guestscrumble@gmailcom', 5),
(7, 'Product', 'Owner', 'prodowner', 'f5bf48aa40cad7891eb709fcf1fde128', 'productowner@gmailcom', 4),
(8, 'Scrum', 'Master', 'scrummaster', 'eb0a191797624dd3a48fa681d3061212', 'scrummaster@gmailcom', 3),
(9, 'Developer', 'Scrum', 'scrumdev', '5e8edd851d2fdfbd7415232c67367cc3', 'scrumdeveloper@gmail.com', 2),
(10, 'Admin', 'Scrum', 'adminscrum', '21232f297a57a5a743894a0e4a801fc3', 'adminscrum@gmail.com', 1);

-- --------------------------------------------------------

--
-- Table structure for table `memberproject`
--

DROP TABLE IF EXISTS `memberproject`;
CREATE TABLE IF NOT EXISTS `memberproject` (
  `id_project` int(11) NOT NULL,
  `id_member` int(11) NOT NULL,
  PRIMARY KEY (`id_project`,`id_member`),
  KEY `fk_project_has_member_member1_idx` (`id_member`),
  KEY `fk_project_has_member_project1_idx` (`id_project`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `planningpoker`
--

DROP TABLE IF EXISTS `planningpoker`;
CREATE TABLE IF NOT EXISTS `planningpoker` (
  `id_planningpoker` int(11) NOT NULL AUTO_INCREMENT,
  `value` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id_planningpoker`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=14 ;

--
-- Dumping data for table `planningpoker`
--

INSERT INTO `planningpoker` (`id_planningpoker`, `value`) VALUES
(1, '0'),
(2, '0.5'),
(3, '1'),
(4, '2'),
(5, '3'),
(6, '5'),
(7, '8'),
(8, '13'),
(9, '20'),
(10, '40'),
(11, '100'),
(12, '?'),
(13, 'Cup of coffee');

-- --------------------------------------------------------

--
-- Table structure for table `project`
--

DROP TABLE IF EXISTS `project`;
CREATE TABLE IF NOT EXISTS `project` (
  `id_project` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(150) DEFAULT NULL,
  `description` text,
  PRIMARY KEY (`id_project`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `project`
--

INSERT INTO `project` (`id_project`, `title`, `description`) VALUES
(1, 'Project1', 'Projet Scrumble Test');

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
CREATE TABLE IF NOT EXISTS `role` (
  `id_role` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(75) DEFAULT NULL,
  `description` text,
  PRIMARY KEY (`id_role`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=6 ;

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`id_role`, `title`, `description`) VALUES
(1, 'Admin', 'Administrateur'),
(2, 'Developer', 'Développeur'),
(3, 'Scrum Master', 'Scrum Master'),
(4, 'Product Owner', 'Product Owwner '),
(5, 'Client', 'Client');

-- --------------------------------------------------------

--
-- Table structure for table `task`
--

DROP TABLE IF EXISTS `task`;
CREATE TABLE IF NOT EXISTS `task` (
  `id_task` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) DEFAULT NULL,
  `estimation` varchar(20) DEFAULT NULL,
  `id_userstory` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_task`),
  KEY `fk_id_userstory_idx` (`id_userstory`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=15 ;

--
-- Dumping data for table `task`
--

INSERT INTO `task` (`id_task`, `title`, `estimation`, `id_userstory`) VALUES
(1, 'Créer un formulaire d''authentification', '0.5', 1),
(2, 'Créer la page d''authentification de l''applica', '2', 1),
(3, 'Créer la Home Page de l''application', '2', 1),
(4, 'Créer le formulaire de création d''un projet', '1', 2),
(5, 'Créer la Page Members de l''application', '2', 3),
(6, 'Ajouter des utilisateurs à la base de données', '1', 3),
(7, 'Faire interagir la Home Page et la Page Membe', '0.5', 3),
(8, 'Créer la page du Dashboard', '3', 4),
(9, 'Faire interagir avec les autres pages du proj', '1', 4),
(10, 'Créer la UserStory Page', '3', 5),
(11, 'Afficher la liste des user stories du projet', '1', 5),
(12, 'Créer le formulaire d''ajout d''une user story', '1', 5),
(13, 'Lier la UserStory Page aux autres pages de l''', '0.5', 5),
(14, 'Créer les fonctions pour modifier, supprimer ', '2', 5);

-- --------------------------------------------------------

--
-- Table structure for table `userstory`
--

DROP TABLE IF EXISTS `userstory`;
CREATE TABLE IF NOT EXISTS `userstory` (
  `id_userstory` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) DEFAULT NULL,
  `importance` varchar(20) DEFAULT NULL,
  `estimation` varchar(20) DEFAULT NULL,
  `demonstration` text,
  `note` text,
  `id_project` int(11) DEFAULT NULL,
  `category` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_userstory`),
  KEY `fk_id_project_idx` (`id_project`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=6 ;

--
-- Dumping data for table `userstory`
--

INSERT INTO `userstory` (`id_userstory`, `title`, `importance`, `estimation`, `demonstration`, `note`, `id_project`, `category`) VALUES
(1, 'Se connecter à l''application', '100', '0.5', '-Renseigner son Login\r\n-Renseigner son mot de passe\r\n-Valider\r\n-Accéder à la page d''accueil de l''application\r\n', NULL, 1, 'Base de données'),
(2, 'Créer un nouveau projet', '90', '1', '- Cliquer sur "New project"\r\n- choisir une méthode ou des modules\r\n- Renseigner les données du formulaire de création\r\n- Valider et revenir à la page d''accueil\r\n- Accéder au projet créé', NULL, 1, 'Web'),
(3, 'Ajouter des membres à un projet', '90', '5', '- Accéder à un projet donné\r\n- Cliquer sur Members\r\n- Visualiser la liste de tous les membres (avec leurs compétences)\r\n- cocher les membres à ajouter au projet et valider\r\n- Revenir sur le projet et visualiser les membres sélectionnés.', NULL, 1, 'Base de données'),
(4, 'Visualiser le Dashboard d''un projet', '50', NULL, '-Accéder à l''application et sélectionner un projet donné\r\n-Cliquer su le menu "Dashbord" et visualiser le tableau de bord du projet', NULL, 1, 'Web'),
(5, 'Gérer les user stories du projet', NULL, NULL, '-Accéder à l''application et sélectionner un projet\r\n-Cliquer sur le menu "Story"\r\n-Cliquer sur "New Story" pour ajouter une story ou sélectionner une story existante pour la modifier ou la supprimer \r\n- Sauvegarder et visualiser les modifications', NULL, 1, 'Base de données');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `member`
--
ALTER TABLE `member`
  ADD CONSTRAINT `fk_id_role` FOREIGN KEY (`id_role`) REFERENCES `role` (`id_role`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `memberproject`
--
ALTER TABLE `memberproject`
  ADD CONSTRAINT `fk_project_has_member_member1` FOREIGN KEY (`id_member`) REFERENCES `member` (`id_member`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_project_has_member_project1` FOREIGN KEY (`id_project`) REFERENCES `project` (`id_project`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `task`
--
ALTER TABLE `task`
  ADD CONSTRAINT `fk_id_userstory` FOREIGN KEY (`id_userstory`) REFERENCES `userstory` (`id_userstory`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `userstory`
--
ALTER TABLE `userstory`
  ADD CONSTRAINT `fk_id_project` FOREIGN KEY (`id_project`) REFERENCES `project` (`id_project`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;