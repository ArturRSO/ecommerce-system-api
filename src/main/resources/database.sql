-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema db_e-commerce_system
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema db_e-commerce_system
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `db_e-commerce_system` DEFAULT CHARACTER SET utf8 ;
USE `db_e-commerce_system` ;

-- -----------------------------------------------------
-- Table `db_e-commerce_system`.`tb_role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_e-commerce_system`.`tb_role` (
  `pk_roleId` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(200) NOT NULL,
  `description` VARCHAR(500) NOT NULL,
  PRIMARY KEY (`pk_roleId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db_e-commerce_system`.`tb_documentType`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_e-commerce_system`.`tb_documentType` (
  `pk_documentTypeId` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(200) NOT NULL,
  PRIMARY KEY (`pk_documentTypeId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db_e-commerce_system`.`tb_user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_e-commerce_system`.`tb_user` (
  `pk_userId` INT NOT NULL AUTO_INCREMENT,
  `firstName` VARCHAR(200) NOT NULL,
  `lastName` VARCHAR(500) NOT NULL,
  `email` VARCHAR(200) NOT NULL,
  `password` VARCHAR(128) NOT NULL,
  `documentNumber` VARCHAR(14) NOT NULL,
  `fk_documentTypeId` INT NOT NULL,
  `fk_roleId` INT NOT NULL,
  `birthday` DATE NOT NULL,
  `profileImagePath` VARCHAR(500) NULL,
  `creationDate` DATETIME NOT NULL,
  `lastUpdate` DATETIME NULL,
  `isActive` TINYINT NOT NULL,
  PRIMARY KEY (`pk_userId`),
  INDEX `fk_tb_user_tb_role_idx` (`fk_roleId` ASC) VISIBLE,
  INDEX `fk_tb_user_tb_documentType1_idx` (`fk_documentTypeId` ASC) VISIBLE,
  CONSTRAINT `fk_tb_user_tb_role`
    FOREIGN KEY (`fk_roleId`)
    REFERENCES `db_e-commerce_system`.`tb_role` (`pk_roleId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tb_user_tb_documentType1`
    FOREIGN KEY (`fk_documentTypeId`)
    REFERENCES `db_e-commerce_system`.`tb_documentType` (`pk_documentTypeId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
