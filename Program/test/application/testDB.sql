-- phpMyAdmin SQL Dump
-- version 3.5.5
-- http://www.phpmyadmin.net
--
-- Vert: sql11.freemysqlhosting.net
-- Generert den: 04. Apr, 2017 14:39 PM
-- Tjenerversjon: 5.5.53-0ubuntu0.14.04.1
-- PHP-Versjon: 5.3.28

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `sql11165164`
--

-- --------------------------------------------------------

--
-- Tabellstruktur for tabell `course`
--

CREATE TABLE IF NOT EXISTS `course` (
  `courseID` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(7) NOT NULL,
  `name` varchar(60) NOT NULL,
  PRIMARY KEY (`courseID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dataark for tabell `course`
--

INSERT INTO `course` (`courseID`, `code`, `name`) VALUES
(1, 'TDT4140', 'Software Engineering'),
(2, 'TDT4145', 'Data Modelling, Databases and Database Management Systems'),
(3, 'TDT4180', 'Human-Computer Interaction'),
(4, 'TMA4140', 'Discrete Mathematics');

-- --------------------------------------------------------

--
-- Tabellstruktur for tabell `lecture`
--

CREATE TABLE IF NOT EXISTS `lecture` (
  `lectureID` int(11) NOT NULL AUTO_INCREMENT,
  `courseID` int(11) NOT NULL,
  `number` int(11) NOT NULL,
  `name` varchar(60) NOT NULL,
  PRIMARY KEY (`lectureID`),
  KEY `Lecture_FK` (`courseID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=21 ;

--
-- Dataark for tabell `lecture`
--

INSERT INTO `lecture` (`lectureID`, `courseID`, `number`, `name`) VALUES
(1, 1, 1, 'The essence of Software Engineering (1)'),
(2, 1, 2, 'Software development methodologies'),
(3, 1, 3, 'The essence of Software Engineering (2)'),
(4, 1, 4, 'Agile Project Plan'),
(5, 2, 1, 'Introduksjon til faget og databaser '),
(6, 2, 2, 'Datamodellering (1)'),
(7, 2, 3, 'Datamodellering (2)'),
(8, 2, 4, 'Relasjonsmodellen'),
(9, 2, 5, 'Modelloversetting og Relasjonsalgebra'),
(10, 3, 1, 'Fagintroduksjon'),
(11, 3, 2, 'Brukskvalitet'),
(12, 3, 3, 'Brukersentrert design - Intro'),
(13, 3, 4, 'MMI-historie'),
(20, 1, 5, 'The Display');

-- --------------------------------------------------------

--
-- Tabellstruktur for tabell `question`
--

CREATE TABLE IF NOT EXISTS `question` (
  `questionID` int(11) NOT NULL AUTO_INCREMENT,
  `topicID` int(11) NOT NULL,
  `userID` int(11) NOT NULL,
  `question` text NOT NULL,
  `answer` text,
  `rating` int(11) NOT NULL,
  PRIMARY KEY (`questionID`),
  KEY `QuestionTopic_FK` (`topicID`),
  KEY `QuestionUser_FK` (`userID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dataark for tabell `question`
--

INSERT INTO `question` (`questionID`, `topicID`, `userID`, `question`, `answer`, `rating`) VALUES
(1, 10, 1, 'Hvor mange øvinger må man ha godkjent', NULL, 0);

-- --------------------------------------------------------

--
-- Tabellstruktur for tabell `rating`
--

CREATE TABLE IF NOT EXISTS `rating` (
  `ratingID` int(11) NOT NULL AUTO_INCREMENT,
  `topicID` int(11) NOT NULL,
  `userID` int(11) NOT NULL,
  `stars` int(1) NOT NULL,
  PRIMARY KEY (`ratingID`),
  KEY `Rating_Topic_FK` (`topicID`),
  KEY `Rating_User_FK` (`userID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=119 ;

--
-- Dataark for tabell `rating`
--

INSERT INTO `rating` (`ratingID`, `topicID`, `userID`, `stars`) VALUES
(112, 10, 1, 3),
(113, 6, 1, 1),
(114, 11, 1, 5),
(115, 10, 1, 3),
(116, 1, 1, 1),
(117, 1, 1, 4),
(118, 1, 1, 1);

-- --------------------------------------------------------

--
-- Tabellstruktur for tabell `topic`
--

CREATE TABLE IF NOT EXISTS `topic` (
  `topicID` int(11) NOT NULL AUTO_INCREMENT,
  `lectureID` int(11) NOT NULL,
  `number` int(11) NOT NULL,
  `name` varchar(60) NOT NULL,
  PRIMARY KEY (`topicID`),
  KEY `Topic_FK` (`lectureID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=40 ;

--
-- Dataark for tabell `topic`
--

INSERT INTO `topic` (`topicID`, `lectureID`, `number`, `name`) VALUES
(1, 1, 1, 'Course Information'),
(2, 1, 2, 'The Semat Kernel'),
(3, 2, 1, 'The Cynefin model'),
(4, 2, 2, 'Waterfall life cycle'),
(5, 2, 3, 'Extreme Programming Change'),
(6, 2, 4, 'Agile Manifesto'),
(7, 3, 1, 'Semat'),
(8, 4, 1, 'Agile project planning'),
(9, 4, 2, 'Extreme Programming Explained'),
(10, 5, 1, 'Fagintroduksjon'),
(11, 5, 2, 'Databaseintroduksjon'),
(12, 6, 1, 'Entitetsklasser'),
(13, 6, 2, 'Relasjonsklasser'),
(14, 6, 3, 'Atributt'),
(15, 6, 4, 'Nøkkler'),
(16, 6, 5, 'Restriksjoner'),
(17, 6, 6, 'Subklasser og kategorier'),
(18, 7, 1, 'Modelleringsprossesen'),
(19, 8, 1, 'ER-modellering'),
(20, 8, 2, 'Oppgave a) fra eksamen mai 2013'),
(21, 9, 1, 'RelasjonsDB'),
(22, 9, 2, 'Mapping av ER til RDB'),
(23, 9, 3, 'Using mySQL'),
(24, 10, 1, 'Fagintroduksjon'),
(25, 11, 1, 'Brukskvalitet'),
(26, 11, 2, 'Brukskontekst'),
(27, 11, 3, 'Brukeropplevelse'),
(28, 12, 1, 'Brukersentrert design'),
(29, 12, 2, 'ISO 9241-11'),
(30, 12, 3, 'ISO 9241-210'),
(31, 12, 4, 'Iterativt design'),
(32, 13, 1, 'MMI-historie'),
(39, 20, 1, 'Tull');

-- --------------------------------------------------------

--
-- Tabellstruktur for tabell `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `userID` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(15) NOT NULL,
  PRIMARY KEY (`userID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=14 ;

--
-- Dataark for tabell `user`
--

INSERT INTO `user` (`userID`, `name`) VALUES
(1, 'Preference'),
(5, 'Trygve'),
(6, 'Trygve2'),
(7, 'Henke'),
(8, 'Preferences'),
(9, 'Christian'),
(10, 'Demo'),
(11, 'Preference ss'),
(12, 'ee'),
(13, 'tets');

--
-- Begrensninger for dumpede tabeller
--

--
-- Begrensninger for tabell `lecture`
--
ALTER TABLE `lecture`
  ADD CONSTRAINT `Lecture_FK` FOREIGN KEY (`courseID`) REFERENCES `course` (`courseID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Begrensninger for tabell `question`
--
ALTER TABLE `question`
  ADD CONSTRAINT `QuestionTopic_FK` FOREIGN KEY (`topicID`) REFERENCES `topic` (`topicID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `QuestionUser_FK` FOREIGN KEY (`userID`) REFERENCES `user` (`userID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Begrensninger for tabell `rating`
--
ALTER TABLE `rating`
  ADD CONSTRAINT `Rating_Topic_FK` FOREIGN KEY (`topicID`) REFERENCES `topic` (`topicID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `Rating_User_FK` FOREIGN KEY (`userID`) REFERENCES `user` (`userID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Begrensninger for tabell `topic`
--
ALTER TABLE `topic`
  ADD CONSTRAINT `Topic_FK` FOREIGN KEY (`lectureID`) REFERENCES `lecture` (`lectureID`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
