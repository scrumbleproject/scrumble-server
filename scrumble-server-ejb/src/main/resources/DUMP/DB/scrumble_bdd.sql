-- phpMyAdmin SQL Dump
-- version 3.3.7deb7
-- http://www.phpmyadmin.net
--
-- Serveur: localhost
-- Généré le : Lun 10 Juin 2013 à 08:42
-- Version du serveur: 5.1.66
-- Version de PHP: 5.3.3-7+squeeze15

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données: `scrumble_bdd`
--
DROP DATABASE `scrumble_bdd`;
CREATE DATABASE `scrumble_bdd` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `scrumble_bdd`;

-- --------------------------------------------------------

--
-- Structure de la table `member`
--

CREATE TABLE IF NOT EXISTS `member` (
  `id_member` int(11) NOT NULL AUTO_INCREMENT,
  `firstname` varchar(45) DEFAULT NULL,
  `lastname` varchar(75) DEFAULT NULL,
  `login` varchar(20) NOT NULL,
  `password` varchar(45) NOT NULL,
  `email` varchar(75) DEFAULT NULL,
  `id_role` int(11) DEFAULT NULL,
  `internal_phone` varchar(20) DEFAULT NULL,
  `mobile_phone` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id_member`),
  UNIQUE KEY `login_UNIQUE` (`login`),
  KEY `fk_id_role_idx` (`id_role`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=11 ;

--
-- Contenu de la table `member`
--

INSERT INTO `member` (`id_member`, `firstname`, `lastname`, `login`, `password`, `email`, `id_role`, `internal_phone`, `mobile_phone`) VALUES
(1, 'Cyril', 'EPINAT', 'cyril', '367d233c448abb973a0136207eb0cf446765a6f5', 'epicyr@gmail.com', 1, '1257', '0470707070'),
(2, 'Arnaud', 'DE LA CRUZ', 'arnaud', 'ea56f45e66e2c57fc79df7dc3ae0437b', 'arnaud.delacruz@gmail.com', 1, '1258', '0470707071'),
(3, 'Soukeyna', 'GAYE', 'soukeyna', '416c21534a55783c70de4b14b8719851', 'soukeynag@gmail.com', 4, '1259', '0470707072'),
(4, 'Romain', 'THIVILLON', 'romain', '5026bc63b5418ffdb54f238db245ec01', 'romain.thivillon@gmail.com', 1, '1260', '0470707073'),
(5, 'Jérémy', 'BARASCUT', 'jeremy', '6967cabefd763ac1a1a88e11159957db', 'jeremy.barascut@gmail.com', 3, '1261', '0470707074'),
(6, 'Guest', 'Scrumble', 'guest', '084e0343a0486ff05530df6c705c8bb4', 'guestscrumble@gmailcom', 5, NULL, NULL),
(7, 'Product', 'Owner', 'prodowner', 'f5bf48aa40cad7891eb709fcf1fde128', 'productowner@gmailcom', 4, NULL, NULL),
(8, 'Scrum', 'Master', 'scrummaster', 'eb0a191797624dd3a48fa681d3061212', 'scrummaster@gmailcom', 3, NULL, NULL),
(9, 'Developer', 'Scrum', 'scrumdev', '5e8edd851d2fdfbd7415232c67367cc3', 'scrumdeveloper@gmail.com', 2, NULL, NULL),
(10, 'Admin', 'Scrum', 'adminscrum', '21232f297a57a5a743894a0e4a801fc3', 'adminscrum@gmail.com', 1, NULL, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `memberproject`
--

CREATE TABLE IF NOT EXISTS `memberproject` (
  `id_project` int(11) NOT NULL,
  `id_member` int(11) NOT NULL,
  PRIMARY KEY (`id_project`,`id_member`),
  KEY `fk_project_has_member_member1_idx` (`id_member`),
  KEY `fk_project_has_member_project1_idx` (`id_project`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `memberproject`
--

INSERT INTO `memberproject` (`id_project`, `id_member`) VALUES
(1, 1),
(1, 2),
(1, 3),
(1, 5);

-- --------------------------------------------------------

--
-- Structure de la table `planningpoker`
--

CREATE TABLE IF NOT EXISTS `planningpoker` (
  `id_planningpoker` int(11) NOT NULL AUTO_INCREMENT,
  `value` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id_planningpoker`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- Contenu de la table `planningpoker`
--


-- --------------------------------------------------------

--
-- Structure de la table `processstatus`
--

CREATE TABLE IF NOT EXISTS `processstatus` (
  `id_process_status` int(11) NOT NULL AUTO_INCREMENT,
  `code_status` varchar(3) DEFAULT NULL,
  `title_status` varchar(45) DEFAULT NULL,
  `sort_order` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_process_status`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=7 ;

--
-- Contenu de la table `processstatus`
--

INSERT INTO `processstatus` (`id_process_status`, `code_status`, `title_status`, `sort_order`) VALUES
(1, 'pro', 'Proposed', 10),
(2, 'acc', 'Accepted', 20),
(3, 'est', 'Estimated', 30),
(4, 'tod', 'To do', 40),
(5, 'inp', 'In progress', 50),
(6, 'don', 'Done', 60);

-- --------------------------------------------------------

--
-- Structure de la table `project`
--

CREATE TABLE IF NOT EXISTS `project` (
  `id_project` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(150) DEFAULT NULL,
  `description` text,
  PRIMARY KEY (`id_project`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

--
-- Contenu de la table `project`
--

INSERT INTO `project` (`id_project`, `title`, `description`) VALUES
(1, 'Scrumble-project', '');

-- --------------------------------------------------------

--
-- Structure de la table `role`
--

CREATE TABLE IF NOT EXISTS `role` (
  `id_role` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(75) DEFAULT NULL,
  `description` text,
  PRIMARY KEY (`id_role`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=6 ;

--
-- Contenu de la table `role`
--

INSERT INTO `role` (`id_role`, `title`, `description`) VALUES
(1, 'Admin', 'Administrateur'),
(2, 'Developer', 'Développeur'),
(3, 'Scrum Master', 'Scrum Master'),
(4, 'Product Owner', 'Product Owwner '),
(5, 'Client', 'Client');

-- --------------------------------------------------------

--
-- Structure de la table `sprint`
--

CREATE TABLE IF NOT EXISTS `sprint` (
  `id_sprint` int(11) NOT NULL AUTO_INCREMENT,
  `num_sprint` int(11) DEFAULT NULL,
  `title` varchar(45) DEFAULT NULL,
  `id_project` int(11) DEFAULT NULL,
  `velocity` int(3) DEFAULT NULL,
  `date_start` datetime DEFAULT NULL,
  `date_end` datetime DEFAULT NULL,
  `duree` int(3) DEFAULT NULL,
  `id_process_status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_sprint`),
  KEY `fk_sprint_1_idx` (`id_project`),
  KEY `fk_sprint_processstatus` (`id_process_status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- Contenu de la table `sprint`
--


-- --------------------------------------------------------

--
-- Structure de la table `sprinttaskassignation`
--

CREATE TABLE IF NOT EXISTS `sprinttaskassignation` (
  `id_task` int(11) NOT NULL,
  `id_sprint` int(11) NOT NULL,
  `id_member` int(11) NOT NULL,
  `date_start` datetime DEFAULT NULL,
  `date_end` datetime DEFAULT NULL,
  PRIMARY KEY (`id_task`,`id_sprint`,`id_member`),
  KEY `fk_assignation_task_idx` (`id_task`),
  KEY `fk_sprinttaskassignation_1_idx` (`id_sprint`),
  KEY `fk_sprinttaskassignation_1_idx1` (`id_member`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `sprinttaskassignation`
--


-- --------------------------------------------------------

--
-- Structure de la table `task`
--

CREATE TABLE IF NOT EXISTS `task` (
  `id_task` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) DEFAULT NULL,
  `estimation` int(3) DEFAULT NULL,
  `id_userstory` int(11) DEFAULT NULL,
  `id_process_status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_task`),
  KEY `fk_id_userstory_idx` (`id_userstory`),
  KEY `fk_task_1_idx` (`id_process_status`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=82 ;

--
-- Contenu de la table `task`
--

INSERT INTO `task` (`id_task`, `title`, `estimation`, `id_userstory`, `id_process_status`) VALUES
(26, 'List task', 0, 8, 4),
(27, 'Task form', 0, 8, 4),
(28, 'Webservice connect', 0, 8, 4),
(29, 'Product backlog', 0, 9, 4),
(30, 'Form: user story', 0, 9, 4),
(31, 'User storie priorisation', 0, 9, 4),
(32, 'Webservices', 0, 9, 4),
(33, 'Users Table: 5 members team + guest + admin', 0, 10, 4),
(34, 'Rules Table ', 0, 10, 4),
(35, 'Plannig poker table', 0, 10, 4),
(36, 'Projects Table', 0, 10, 4),
(37, 'Tasks Table', 0, 10, 4),
(38, 'User stories Table', 0, 10, 4),
(39, 'Members Table', 0, 11, 4),
(40, 'Rules Table', 0, 11, 4),
(41, 'Projects/Products Table', 0, 11, 4),
(42, 'User stories Table', 0, 11, 4),
(43, 'Tasks Table', 0, 11, 4),
(44, 'Planning poker Table', 0, 11, 4),
(45, 'User stories list', 0, 12, 4),
(46, 'Add story', 0, 12, 4),
(47, 'Dell story', 0, 12, 4),
(48, 'Display story', 0, 12, 4),
(49, 'Update Story', 0, 12, 4),
(50, 'dfgsd', 0, 13, 4),
(51, 'bgsdfgfgh', 0, 13, 4),
(52, 'sdfg', 0, 13, 4),
(53, 'List all users', 0, 14, 4),
(54, 'Search Users', 0, 14, 4),
(55, 'Add Users', 0, 14, 4),
(56, 'Delete Users', 0, 14, 4),
(57, 'Show one user', 0, 14, 4),
(58, 'Modify user', 0, 14, 4),
(59, 'List All Project', 0, 15, 4),
(60, 'Search Project', 0, 15, 4),
(61, 'Add Project', 0, 15, 4),
(62, 'Delete Project', 0, 15, 4),
(63, 'Show Project', 0, 15, 4),
(64, 'Modify Project', 0, 15, 4),
(65, 'List member', 0, 16, 4),
(66, 'Form Member', 0, 16, 4),
(67, 'Web Services Connection', 0, 16, 4),
(68, 'List all project members', 0, 17, 4),
(69, 'List all member which not project member', 0, 17, 4),
(70, 'Add project member', 0, 17, 4),
(71, 'Delete project member', 0, 17, 4),
(72, 'web connection connection', 0, 18, 4),
(73, 'List member in project', 0, 18, 4),
(74, 'Delete member in project', 0, 18, 4),
(75, 'Autocomplete in add form', 0, 18, 4),
(76, 'List All task', 0, 19, 4),
(77, 'Search task', 0, 19, 4),
(78, 'Add task', 0, 19, 4),
(79, 'Delete task', 0, 19, 4),
(80, 'Show task', 0, 19, 4),
(81, 'Modify task', 0, 19, 4);

-- --------------------------------------------------------

--
-- Structure de la table `userstory`
--

CREATE TABLE IF NOT EXISTS `userstory` (
  `id_userstory` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) DEFAULT NULL,
  `importance` int(4) DEFAULT NULL,
  `estimation` int(4) DEFAULT NULL,
  `demonstration` text,
  `note` text,
  `id_project` int(11) DEFAULT NULL,
  `category` varchar(45) DEFAULT NULL,
  `id_process_status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_userstory`),
  KEY `fk_id_project_idx` (`id_project`),
  KEY `fk_userstory_status_idx` (`id_process_status`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=20 ;

--
-- Contenu de la table `userstory`
--

INSERT INTO `userstory` (`id_userstory`, `title`, `importance`, `estimation`, `demonstration`, `note`, `id_project`, `category`, `id_process_status`) VALUES
(8, 'HIM: Tasks manager', 0, NULL, '', '', 1, NULL, NULL),
(9, 'HIM: User stories manager', 0, NULL, '', '', 1, NULL, NULL),
(10, 'Create tests data for data base', 10, 0, '', '', 1, NULL, NULL),
(11, 'Design DB', 0, 0, '', '', 1, NULL, NULL),
(12, 'Implement user stories request', 0, 0, '', '', 1, NULL, NULL),
(13, 'HIM: Project Management', 0, 0, '', '', 1, NULL, NULL),
(14, 'Implement MEMBER request', 0, 0, '', '', 1, NULL, NULL),
(15, 'Implement PROJECT request', 0, 0, '', '', 1, NULL, NULL),
(16, 'IHM : (ADMIN) Member management', 0, 0, '', '', 1, NULL, NULL),
(17, 'Implement PROJECT-MEMBER request', 0, 0, '', '', 1, NULL, NULL),
(18, 'IHM : Member mgmt in project', 0, 0, '', '', 1, NULL, NULL),
(19, 'Implement task request', 0, 0, '', '', 1, NULL, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `userstorysprint`
--

CREATE TABLE IF NOT EXISTS `userstorysprint` (
  `id_sprint` int(11) NOT NULL,
  `id_userstory` int(11) NOT NULL,
  `date_start` datetime DEFAULT NULL,
  `date_end` datetime DEFAULT NULL,
  PRIMARY KEY (`id_sprint`,`id_userstory`),
  KEY `fk_userstorysprint_sprint_idx` (`id_sprint`),
  KEY `fk_userstorysprint_userstory_idx` (`id_userstory`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `userstorysprint`
--


--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `member`
--
ALTER TABLE `member`
  ADD CONSTRAINT `fk_id_role` FOREIGN KEY (`id_role`) REFERENCES `role` (`id_role`) ON DELETE CASCADE ON UPDATE NO ACTION;

--
-- Contraintes pour la table `memberproject`
--
ALTER TABLE `memberproject`
  ADD CONSTRAINT `fk_project_has_member_member1` FOREIGN KEY (`id_member`) REFERENCES `member` (`id_member`) ON DELETE CASCADE ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_project_has_member_project1` FOREIGN KEY (`id_project`) REFERENCES `project` (`id_project`) ON DELETE CASCADE ON UPDATE NO ACTION;

--
-- Contraintes pour la table `sprint`
--
ALTER TABLE `sprint`
  ADD CONSTRAINT `fk_sprint_processstatus` FOREIGN KEY (`id_process_status`) REFERENCES `processstatus` (`id_process_status`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_sprint_project` FOREIGN KEY (`id_project`) REFERENCES `project` (`id_project`) ON DELETE CASCADE ON UPDATE NO ACTION;

--
-- Contraintes pour la table `sprinttaskassignation`
--
ALTER TABLE `sprinttaskassignation`
  ADD CONSTRAINT `fk_assignation_member` FOREIGN KEY (`id_member`) REFERENCES `member` (`id_member`) ON DELETE CASCADE ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_assignation_sprint` FOREIGN KEY (`id_sprint`) REFERENCES `sprint` (`id_sprint`) ON DELETE CASCADE ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_assignation_task` FOREIGN KEY (`id_task`) REFERENCES `task` (`id_task`) ON DELETE CASCADE ON UPDATE NO ACTION;

--
-- Contraintes pour la table `task`
--
ALTER TABLE `task`
  ADD CONSTRAINT `fk_id_userstory` FOREIGN KEY (`id_userstory`) REFERENCES `userstory` (`id_userstory`) ON DELETE CASCADE ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_task_status` FOREIGN KEY (`id_process_status`) REFERENCES `processstatus` (`id_process_status`) ON DELETE CASCADE ON UPDATE NO ACTION;

--
-- Contraintes pour la table `userstory`
--
ALTER TABLE `userstory`
  ADD CONSTRAINT `fk_id_project` FOREIGN KEY (`id_project`) REFERENCES `project` (`id_project`) ON DELETE CASCADE ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_userstory_status` FOREIGN KEY (`id_process_status`) REFERENCES `processstatus` (`id_process_status`) ON DELETE CASCADE ON UPDATE NO ACTION;

--
-- Contraintes pour la table `userstorysprint`
--
ALTER TABLE `userstorysprint`
  ADD CONSTRAINT `fk_ustorysprint_sprint` FOREIGN KEY (`id_sprint`) REFERENCES `sprint` (`id_sprint`) ON DELETE CASCADE ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_ustorysprint_userstory` FOREIGN KEY (`id_userstory`) REFERENCES `userstory` (`id_userstory`) ON DELETE CASCADE ON UPDATE NO ACTION;