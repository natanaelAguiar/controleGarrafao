CREATE TABLE `clientes`(
  `cliente_id` int(11) NOT NULL AUTO_INCREMENT,
  `cliente_nome` varchar(45) NOT NULL,
  `cliente_rua` varchar(45) NOT NULL,
  `cliente_numero` varchar(45) NOT NULL,
  `cliente_complemento` varchar(45),
  PRIMARY KEY (`cliente_id`)
);
CREATE TABLE `garrafaos`(
  `garrafao_id` int(11) NOT NULL AUTO_INCREMENT,
  `garrafao_nome` varchar(45) NOT NULL,
  PRIMARY KEY (`garrafao_id`)
);

CREATE TABLE `clientes_garrafaos` (
  `cliente_garrafao_id` int(11) NOT NULL,
  `cliente_id` int(11) NOT NULL,
  `garrafao_id` int(11) NOT NULL,
  `quantidade` int(11) NOT NULL,
  PRIMARY KEY (`cliente_garrafao_id`), 
  KEY `fk_user` (`cliente_id`),
  KEY `fk_group` (`garrafao_id`),
  CONSTRAINT `fk_cliente` FOREIGN KEY (`cliente_id`) REFERENCES `clientes` (`cliente_id`),
  CONSTRAINT `fk_garrafao` FOREIGN KEY (`garrafao_id`) REFERENCES `garrafaos` (`garrafao_id`)
);