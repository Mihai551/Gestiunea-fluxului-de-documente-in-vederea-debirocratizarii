CREATE TABLE `gestiunea_documentelor`.`employee` (
   `legalEntityName` varchar(45) DEFAULT NULL,
   `firstName` varchar(45) DEFAULT NULL,
   `lastName` varchar(45) DEFAULT NULL,
   `pin` varchar(45) DEFAULT NULL,
   `emailAddress` varchar(45) DEFAULT NULL,
   `password` varchar(45) DEFAULT NULL,
   `mystreams` varchar(45) DEFAULT NULL,
   `streamsForMe` varchar(45) DEFAULT NULL,
   `inviteCode` varchar(45) DEFAULT NULL
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci