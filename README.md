# VirtualLibrary

Tables

CREATE TABLE `authors` (
  `Aid` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) NOT NULL,
  `Photo` varchar(255) NOT NULL,
  `Birth` varchar(255) NOT NULL,
  `Death` varchar(255) NOT NULL,
  PRIMARY KEY (`Aid`));
  
  CREATE TABLE `users` (
  `Lid` int NOT NULL AUTO_INCREMENT,
  `LastName` varchar(255) NOT NULL,
  `FirstName` varchar(255) NOT NULL,
  `Email` varchar(255) NOT NULL,
  `PhoneNumber` varchar(255) NOT NULL,
  `Password` varchar(255) NOT NULL,
  `Term` int DEFAULT '0',
  PRIMARY KEY (`Lid`));
