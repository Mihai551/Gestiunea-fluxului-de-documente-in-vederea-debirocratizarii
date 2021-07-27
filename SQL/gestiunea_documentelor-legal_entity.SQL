CREATE TABLE `legal_entity` (
  `legalEntityName` varchar(45) DEFAULT NULL,
  `emailAddress` varchar(45) DEFAULT NULL,
  `country` varchar(45) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `type` varchar(45) DEFAULT 'legal_entity',
  KEY `emailAddress_idx` (`emailAddress`),
  CONSTRAINT `legalEntityEmailAddress` FOREIGN KEY (`emailAddress`) REFERENCES `user` (`emailAddress`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci