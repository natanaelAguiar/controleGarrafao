use controleGarrafaob;

CREATE TABLE `cliente` (
  `CLIENTE_ID` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `CLIENTE_NOME` VARCHAR(10) NOT NULL,
  `CLIENTE_RUA` VARCHAR(20) NOT NULL,
  `CLIENTE_NUMERO` VARCHAR(20) NOT NULL,
  `CLIENTE_COMPLEMENTO` VARCHAR(20),
  PRIMARY KEY (`CLIENTE_ID`) USING BTREE
);

CREATE TABLE `garrafao` (
  `GARRAFAO_ID` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `GARRAFAO_NOME` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`GARRAFAO_ID`) USING BTREE
);

CREATE TABLE  `cliente_garrafao` (
  `CLIENTE_ID` INT(10) UNSIGNED NOT NULL,
  `GARRAFAO_ID` INT(10) UNSIGNED NOT NULL,
  `QUANTIDADE` INT(10),
  PRIMARY KEY (`CLIENTE_ID`,`GARRAFAO_ID`),
  CONSTRAINT `FK_GARRAFAO_ID` FOREIGN KEY (`GARRAFAO_ID`)
             REFERENCES `garrafao` (`GARRAFAO_ID`),
  CONSTRAINT `FK_CLIENTE_ID` FOREIGN KEY (`CLIENTE_ID`)
             REFERENCES `cliente` (`CLIENTE_ID`)
);