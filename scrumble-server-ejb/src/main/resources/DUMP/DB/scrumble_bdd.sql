-- phpMyAdmin SQL Dump
-- version 3.4.11.1deb1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jun 26, 2013 at 08:11 PM
-- Server version: 5.5.31
-- PHP Version: 5.4.6-1ubuntu1.2

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
-- Dumping data for table `member`
--

INSERT INTO `member` (`id_member`, `firstname`, `lastname`, `login`, `password`, `email`, `id_role`, `internal_phone`, `mobile_phone`) VALUES
(1, 'Cyril', 'EPINAT', 'cyril', '367d233c448abb973a0136207eb0cf446765a6f5', 'epicyr@gmail.com', 1, '1257', '0470707070'),
(2, 'Arnaud', 'DE LA CRUZ', 'arnaud', '1a340065606634b575ed162e9c434c27fe24a7c3', 'arnaud.delacruz@gmail.com', 1, '1258', '0470707071'),
(3, 'Soukeyna', 'GAYE', 'soukeyna', 'b8aabb4b95c817d9df69b6be95b2b94d6b1efe17', 'soukeynag@gmail.com', 4, '1259', '0470707072'),
(4, 'Romain', 'THIVILLON', 'romain', 'b8aabb4b95c817d9df69b6be95b2b94d6b1efe17', 'romain.thivillon@gmail.com', 1, '1260', '0470707073'),
(5, 'Jérémy', 'BARASCUT', 'jeremy', 'b3f594e10a9edcf5413cf1190121d45078c62290', 'jeremy.barascut@gmail.com', 3, '1261', '0470707074'),
(6, 'Guest', 'Scrumble', 'guest', '35675e68f4b5af7b995d9205ad0fc43842f16450', 'guestscrumble@gmailcom', 5, NULL, NULL),
(7, 'Product', 'Owner', 'prodowner', '7d02c6ad9bad384be2ed6bc2bcc8d8e03e95e7ac', 'productowner@gmailcom', 4, NULL, NULL),
(8, 'Scrum', 'Master', 'scrummaster', '6978ec3924c878881eb5fffc33a9d8ebbd768310', 'scrummaster@gmailcom', 3, NULL, NULL),
(9, 'Developer', 'Scrum', 'scrumdev', '0aebcac78827760d5eb302ab20c8d962ce1e92ab', 'scrumdeveloper@gmail.com', 2, NULL, NULL),
(10, 'Admin', 'Scrum', 'adminscrum', '8b0a5649ef0b35347ccfb45de80316f007beaba3', 'adminscrum@gmail.com', 1, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `memberproject`
--

CREATE TABLE IF NOT EXISTS `memberproject` (
  `id_project` int(11) NOT NULL,
  `id_member` int(11) NOT NULL,
  PRIMARY KEY (`id_project`,`id_member`),
  KEY `fk_project_has_member_member1_idx` (`id_member`),
  KEY `fk_project_has_member_project1_idx` (`id_project`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `memberproject`
--

INSERT INTO `memberproject` (`id_project`, `id_member`) VALUES
(1, 1),
(1, 2),
(1, 3),
(1, 4),
(1, 5);

-- --------------------------------------------------------

--
-- Table structure for table `planningpoker`
--

CREATE TABLE IF NOT EXISTS `planningpoker` (
  `id_planningpoker` int(11) NOT NULL AUTO_INCREMENT,
  `value` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id_planningpoker`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `processstatus`
--

CREATE TABLE IF NOT EXISTS `processstatus` (
  `id_process_status` int(11) NOT NULL AUTO_INCREMENT,
  `code_status` varchar(3) DEFAULT NULL,
  `title_status` varchar(45) DEFAULT NULL,
  `sort_order` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_process_status`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=7 ;

--
-- Dumping data for table `processstatus`
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
-- Table structure for table `project`
--

CREATE TABLE IF NOT EXISTS `project` (
  `id_project` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(150) DEFAULT NULL,
  `description` text,
  PRIMARY KEY (`id_project`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `project`
--

INSERT INTO `project` (`id_project`, `title`, `description`) VALUES
(1, 'Scrumble-project', ''),
(2, 'Test', 'essai'),
(3, 'dddddd', '');

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

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
-- Table structure for table `sprint`
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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=8 ;

--
-- Dumping data for table `sprint`
--

INSERT INTO `sprint` (`id_sprint`, `num_sprint`, `title`, `id_project`, `velocity`, `date_start`, `date_end`, `duree`, `id_process_status`) VALUES
(2, 0, 'Sprint 1', 1, 20, '2012-09-22 01:00:00', '2012-10-06 01:00:00', NULL, 6),
(3, 0, 'Sprint 2', 1, 12, '2012-10-07 00:00:00', '2012-10-21 00:00:00', NULL, 6),
(4, 0, 'Sprint 3', 1, 0, '2012-10-22 00:00:00', '2012-11-05 00:00:00', NULL, 5),
(5, 0, 'Sprint 4', 1, 6, '2012-11-06 00:00:00', '2012-11-20 00:00:00', NULL, 4),
(6, 0, 'Sprint 5', 1, 10, '2012-11-07 00:00:00', '2012-11-21 00:00:00', NULL, 4),
(7, 0, 'Sprint 6', 1, 15, '2012-11-22 00:00:00', '2012-12-06 01:00:00', NULL, 4);

-- --------------------------------------------------------

--
-- Table structure for table `sprinttaskassignation`
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
-- Dumping data for table `sprinttaskassignation`
--

INSERT INTO `sprinttaskassignation` (`id_task`, `id_sprint`, `id_member`, `date_start`, `date_end`) VALUES
(33, 2, 1, '2012-09-22 17:13:40', '2012-09-23 17:13:46'),
(34, 2, 1, '2012-09-22 17:13:41', '2012-09-24 17:13:47'),
(35, 2, 1, '2012-09-23 17:13:38', '2012-09-25 17:13:44'),
(36, 2, 1, '2012-09-26 17:13:42', '2012-09-28 17:13:50'),
(37, 2, 1, '2012-09-25 17:13:38', '2012-09-28 17:13:45'),
(38, 2, 1, '2012-09-29 17:13:43', '2012-10-04 17:13:49'),
(39, 2, 1, '2012-10-01 17:13:51', '2012-10-04 17:13:51'),
(40, 2, 1, '2012-10-02 12:13:53', '2012-10-02 17:13:53'),
(41, 2, 1, '2012-10-02 17:13:52', '2012-10-06 17:13:52'),
(42, 2, 1, '2012-10-01 09:13:55', '2012-10-01 12:13:55'),
(43, 2, 1, '2012-10-02 14:13:54', '2012-10-02 17:13:54'),
(44, 2, 1, '2012-10-03 17:13:56', '2013-10-06 17:13:56'),
(50, 3, 1, '2012-10-07 08:15:10', '2012-10-07 17:15:10'),
(51, 3, 1, '2012-10-07 08:15:14', '2012-10-10 17:15:14'),
(59, 3, 1, '2012-10-07 08:15:16', '2012-10-07 17:15:16'),
(60, 3, 1, '2012-10-08 17:15:17', '2012-10-12 17:15:17'),
(61, 3, 1, '2012-10-07 17:15:18', '2012-10-12 17:15:18'),
(62, 3, 1, '2012-10-12 08:15:19', '2012-10-14 17:15:19'),
(63, 3, 1, '2012-10-14 17:15:20', '2012-10-16 17:15:20'),
(64, 3, 1, '2012-10-16 17:15:21', '2012-10-21 17:15:21'),
(70, 4, 1, '2012-10-22 17:15:48', NULL),
(71, 4, 1, '2012-10-22 08:15:47', '2012-10-24 11:32:49'),
(76, 4, 1, '2012-10-23 11:32:57', NULL),
(77, 4, 1, '2012-10-22 11:32:55', '2012-10-24 12:00:26'),
(78, 4, 1, '2012-10-24 17:15:50', NULL),
(80, 4, 1, '2012-10-23 12:00:27', NULL),
(81, 4, 1, '2012-10-22 08:15:55', '2012-10-24 17:15:55');

-- --------------------------------------------------------

--
-- Table structure for table `task`
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
-- Dumping data for table `task`
--

INSERT INTO `task` (`id_task`, `title`, `estimation`, `id_userstory`, `id_process_status`) VALUES
(26, 'List task', 1, 8, 4),
(27, 'Task form', 1, 8, 4),
(28, 'Webservice connect', 2, 8, 4),
(29, 'Product backlog', 2, 9, 4),
(30, 'Form: user story', 1, 9, 4),
(31, 'User storie priorisation', 1, 9, 4),
(32, 'Webservices', 1, 9, 4),
(33, 'Users Table: 5 members team + guest + admin', 1, 10, 6),
(34, 'Rules Table ', 2, 10, 6),
(35, 'Plannig poker table', 1, 10, 6),
(36, 'Projects Table', 1, 10, 6),
(37, 'Tasks Table', 1, 10, 6),
(38, 'User stories Table', 2, 10, 6),
(39, 'Members Table', 1, 11, 6),
(40, 'Rules Table', 1, 11, 6),
(41, 'Projects/Products Table', 2, 11, 6),
(42, 'User stories Table', 1, 11, 6),
(43, 'Tasks Table', 1, 11, 6),
(44, 'Planning poker Table', 2, 11, 6),
(45, 'User stories list', 1, 12, 4),
(46, 'Add story', 1, 12, 4),
(47, 'Delete story', 1, 12, 4),
(48, 'Display story', 1, 12, 4),
(49, 'Update Story', 1, 12, 4),
(50, 'Display project', 1, 13, 6),
(51, 'Webservices', 1, 13, 6),
(53, 'List all users', 1, 14, 4),
(54, 'Search Users', 2, 14, 4),
(55, 'Add Users', 1, 14, 4),
(56, 'Delete Users', 1, 14, 4),
(57, 'Show one user', 1, 14, 4),
(58, 'Modify user', 1, 14, 4),
(59, 'List All Project', 1, 15, 6),
(60, 'Search Project', 2, 15, 6),
(61, 'Add Project', 1, 15, 6),
(62, 'Delete Project', 1, 15, 6),
(63, 'Show Project', 1, 15, 6),
(64, 'Modify Project', 1, 15, 6),
(65, 'List member', 1, 16, 4),
(66, 'Form Member', 1, 16, 4),
(67, 'Web Services Connection', 2, 16, 4),
(68, 'List all project members', 1, 17, 4),
(69, 'List all member which not project member', 1, 17, 4),
(70, 'Add project member', 1, 17, 5),
(71, 'Delete project member', 1, 17, 6),
(72, 'web connection ', 2, 18, 4),
(73, 'List member in project', 1, 18, 4),
(74, 'Delete member in project', 1, 18, 4),
(75, 'Autocomplete in add form', 2, 18, 4),
(76, 'List All task', 1, 19, 5),
(77, 'Search task', 2, 19, 6),
(78, 'Add task', 1, 19, 5),
(79, 'Delete task', 1, 19, 4),
(80, 'Show task', 1, 19, 5),
(81, 'Modify task', 1, 19, 6);

-- --------------------------------------------------------

--
-- Table structure for table `userstory`
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
-- Dumping data for table `userstory`
--

INSERT INTO `userstory` (`id_userstory`, `title`, `importance`, `estimation`, `demonstration`, `note`, `id_project`, `category`, `id_process_status`) VALUES
(8, 'HIM: Tasks manager', 1, 4, '', '', 1, NULL, NULL),
(9, 'HIM: User stories manager', 0, 5, '', '', 1, NULL, NULL),
(10, 'Create tests data for data base', 10, 8, '', '', 1, NULL, NULL),
(11, 'Design DB', 0, 8, '', '', 1, NULL, NULL),
(12, 'Implement user stories request', 0, 5, '', '', 1, NULL, NULL),
(13, 'HIM: Project Management', 0, 2, '', '', 1, NULL, NULL),
(14, 'Implement MEMBER request', 0, 7, '', '', 1, NULL, NULL),
(15, 'Implement PROJECT request', 5, 6, '', '', 1, NULL, NULL),
(16, 'IHM : (ADMIN) Member management', 4, 4, '', '', 1, NULL, NULL),
(17, 'Implement PROJECT-MEMBER request', 8, 8, '', '', 1, NULL, NULL),
(18, 'IHM : Member management in project', 0, 6, '', '', 1, NULL, NULL),
(19, 'Implement task request', 0, 7, '', '', 1, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `userstorysprint`
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
-- Dumping data for table `userstorysprint`
--

INSERT INTO `userstorysprint` (`id_sprint`, `id_userstory`, `date_start`, `date_end`) VALUES
(2, 10, NULL, NULL),
(2, 11, NULL, NULL),
(3, 13, NULL, NULL),
(3, 15, NULL, NULL),
(4, 17, NULL, NULL),
(4, 19, NULL, NULL),
(5, 16, NULL, NULL),
(6, 8, NULL, NULL),
(6, 12, NULL, NULL),
(7, 9, NULL, NULL),
(7, 14, NULL, NULL),
(7, 18, NULL, NULL);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `member`
--
ALTER TABLE `member`
  ADD CONSTRAINT `fk_id_role` FOREIGN KEY (`id_role`) REFERENCES `role` (`id_role`) ON DELETE CASCADE ON UPDATE NO ACTION;

--
-- Constraints for table `memberproject`
--
ALTER TABLE `memberproject`
  ADD CONSTRAINT `fk_project_has_member_member1` FOREIGN KEY (`id_member`) REFERENCES `member` (`id_member`) ON DELETE CASCADE ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_project_has_member_project1` FOREIGN KEY (`id_project`) REFERENCES `project` (`id_project`) ON DELETE CASCADE ON UPDATE NO ACTION;

--
-- Constraints for table `sprint`
--
ALTER TABLE `sprint`
  ADD CONSTRAINT `fk_sprint_processstatus` FOREIGN KEY (`id_process_status`) REFERENCES `processstatus` (`id_process_status`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_sprint_project` FOREIGN KEY (`id_project`) REFERENCES `project` (`id_project`) ON DELETE CASCADE ON UPDATE NO ACTION;

--
-- Constraints for table `sprinttaskassignation`
--
ALTER TABLE `sprinttaskassignation`
  ADD CONSTRAINT `fk_assignation_member` FOREIGN KEY (`id_member`) REFERENCES `member` (`id_member`) ON DELETE CASCADE ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_assignation_sprint` FOREIGN KEY (`id_sprint`) REFERENCES `sprint` (`id_sprint`) ON DELETE CASCADE ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_assignation_task` FOREIGN KEY (`id_task`) REFERENCES `task` (`id_task`) ON DELETE CASCADE ON UPDATE NO ACTION;

--
-- Constraints for table `task`
--
ALTER TABLE `task`
  ADD CONSTRAINT `fk_id_userstory` FOREIGN KEY (`id_userstory`) REFERENCES `userstory` (`id_userstory`) ON DELETE CASCADE ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_task_status` FOREIGN KEY (`id_process_status`) REFERENCES `processstatus` (`id_process_status`) ON DELETE CASCADE ON UPDATE NO ACTION;

--
-- Constraints for table `userstory`
--
ALTER TABLE `userstory`
  ADD CONSTRAINT `fk_id_project` FOREIGN KEY (`id_project`) REFERENCES `project` (`id_project`) ON DELETE CASCADE ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_userstory_status` FOREIGN KEY (`id_process_status`) REFERENCES `processstatus` (`id_process_status`) ON DELETE CASCADE ON UPDATE NO ACTION;

--
-- Constraints for table `userstorysprint`
--
ALTER TABLE `userstorysprint`
  ADD CONSTRAINT `fk_ustorysprint_sprint` FOREIGN KEY (`id_sprint`) REFERENCES `sprint` (`id_sprint`) ON DELETE CASCADE ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_ustorysprint_userstory` FOREIGN KEY (`id_userstory`) REFERENCES `userstory` (`id_userstory`) ON DELETE CASCADE ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;