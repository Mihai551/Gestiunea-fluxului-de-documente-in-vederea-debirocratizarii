CREATE TABLE `legal_entities` (
   `legalEntityName` varchar(45) DEFAULT NULL,
   `emailAddress` varchar(45) DEFAULT NULL,
   `country` varchar(45) DEFAULT NULL,
   `city` varchar(45) DEFAULT NULL,
   `address` varchar(45) DEFAULT NULL,
   `password` varchar(45) DEFAULT NULL,
   `inviteCodes` varchar(45) DEFAULT NULL,
   `myStreams` varchar(45) DEFAULT NULL,
   `streamsForMe` varchar(45) DEFAULT NULL
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci