CREATE TABLE `user` (
  `emailAddress` varchar(16) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `salt` varchar(32) DEFAULT NULL,
  `type` varchar(45) NOT NULL,
  PRIMARY KEY (`emailAddress`,`type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci