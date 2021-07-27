CREATE TABLE `individual` (
  `firstName` varchar(45) DEFAULT NULL,
  `lastName` varchar(45) DEFAULT NULL,
  `pin` varchar(45) DEFAULT NULL,
  `emailAddress` varchar(45) DEFAULT NULL,
  `type` varchar(45) DEFAULT 'individual',
  KEY `emailAddress_idx` (`emailAddress`),
  KEY `type_idx` (`type`),
  CONSTRAINT `individual_emailAdress` FOREIGN KEY (`emailAddress`) REFERENCES `user` (`emailAddress`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci