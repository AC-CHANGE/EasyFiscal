-- phpMyAdmin SQL Dump
-- version 3.5.1
-- http://www.phpmyadmin.net
--
-- Хост: 127.0.0.1
-- Время создания: Янв 21 2016 г., 01:53
-- Версия сервера: 5.5.25
-- Версия PHP: 5.3.13

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- База данных: `fiscal`
--

-- --------------------------------------------------------

--
-- Структура таблицы `kassi`
--

CREATE TABLE IF NOT EXISTS `kassi` (
  `zavNum` varchar(12) NOT NULL,
  `fiscNum` varchar(10) NOT NULL,
  `dateCreate` varchar(10) NOT NULL,
  `dateActive` varchar(10) DEFAULT NULL,
  `ownerName` varchar(50) NOT NULL,
  `model` varchar(30) NOT NULL,
  `modelPO` varchar(30) NOT NULL,
  `dateFirstActive` varchar(10) NOT NULL,
  PRIMARY KEY (`zavNum`),
  UNIQUE KEY `zavNum` (`zavNum`),
  UNIQUE KEY `fiscNum` (`fiscNum`),
  KEY `ownerID` (`ownerName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Структура таблицы `owners`
--

CREATE TABLE IF NOT EXISTS `owners` (
  `name` varchar(100) NOT NULL,
  `adres` varchar(300) NOT NULL,
  `edrpo` bigint(30) unsigned NOT NULL,
  `IPN` varchar(30) DEFAULT NULL,
  UNIQUE KEY `edrpo` (`edrpo`),
  UNIQUE KEY `name` (`name`),
  UNIQUE KEY `edrpo_2` (`edrpo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Ограничения внешнего ключа сохраненных таблиц
--

--
-- Ограничения внешнего ключа таблицы `kassi`
--
ALTER TABLE `kassi`
  ADD CONSTRAINT `kassi_ibfk_1` FOREIGN KEY (`ownerName`) REFERENCES `owners` (`name`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
