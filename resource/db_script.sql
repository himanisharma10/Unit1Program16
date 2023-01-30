   CREATE TABLE `registeredusers` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(200) ,
  `password` varchar(400) ,
  `email_id` varchar(400),
  `mobile` varchar(400),
  PRIMARY KEY (`user_id`)
);