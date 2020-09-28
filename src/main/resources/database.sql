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


-- -----------------------------------------------------
-- Table `db_e-commerce_system`.`tb_telephoneType`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_e-commerce_system`.`tb_telephoneType` (
  `pk_telephoneTypeId` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(200) NOT NULL,
  PRIMARY KEY (`pk_telephoneTypeId`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db_e-commerce_system`.`tb_telephone`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_e-commerce_system`.`tb_telephone` (
  `pk_telephoneId` INT NOT NULL AUTO_INCREMENT,
  `fk_userId` INT NOT NULL,
  `fk_telephoneTypeId` INT NOT NULL,
  `internationalCode` VARCHAR(5) NOT NULL,
  `localCode` INT NOT NULL,
  `number` VARCHAR(9) NOT NULL,
  `creationDate` DATETIME NOT NULL,
  `lastUpdate` DATETIME NULL,
  `isActive` TINYINT NOT NULL,
  INDEX `fk_tb_telephone_tb_user1_idx` (`fk_userId` ASC) VISIBLE,
  INDEX `fk_tb_telephone_tb_telephoneType1_idx` (`fk_telephoneTypeId` ASC) VISIBLE,
  PRIMARY KEY (`pk_telephoneId`),
  CONSTRAINT `fk_tb_telephone_tb_user1`
    FOREIGN KEY (`fk_userId`)
    REFERENCES `db_e-commerce_system`.`tb_user` (`pk_userId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tb_telephone_tb_telephoneType1`
    FOREIGN KEY (`fk_telephoneTypeId`)
    REFERENCES `db_e-commerce_system`.`tb_telephoneType` (`pk_telephoneTypeId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db_e-commerce_system`.`tb_address`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_e-commerce_system`.`tb_address` (
  `pk_addressId` INT NOT NULL AUTO_INCREMENT,
  `fk_userId` INT NOT NULL,
  `country` VARCHAR(200) NOT NULL,
  `postalCode` VARCHAR(8) NOT NULL,
  `address` VARCHAR(500) NOT NULL,
  `number` INT NOT NULL,
  `stateCode` VARCHAR(2) NOT NULL,
  `city` VARCHAR(200) NOT NULL,
  `district` VARCHAR(200) NOT NULL,
  `complement` VARCHAR(200) NOT NULL,
  `creationDate` DATETIME NOT NULL,
  `lastUpdate` DATETIME NULL,
  `isActive` TINYINT NOT NULL,
  PRIMARY KEY (`pk_addressId`),
  INDEX `fk_tb_address_tb_user1_idx` (`fk_userId` ASC) VISIBLE,
  CONSTRAINT `fk_tb_address_tb_user1`
    FOREIGN KEY (`fk_userId`)
    REFERENCES `db_e-commerce_system`.`tb_user` (`pk_userId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db_e-commerce_system`.`tb_userOption`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_e-commerce_system`.`tb_userOption` (
  `pk_userOptionId` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(200) NOT NULL,
  `elementId` VARCHAR(50) NULL,
  `route` VARCHAR(50) NULL,
  `samePage` TINYINT NOT NULL,
  `isActive` TINYINT NOT NULL,
  PRIMARY KEY (`pk_userOptionId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db_e-commerce_system`.`tb_role_userOption`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_e-commerce_system`.`tb_role_userOption` (
  `pk_fk_roleId` INT NOT NULL,
  `pk_fk_userOptionId` INT NOT NULL,
  INDEX `fk_tb_role_userOption_tb_role1_idx` (`pk_fk_roleId` ASC) VISIBLE,
  INDEX `fk_tb_role_userOption_tb_userOption1_idx` (`pk_fk_userOptionId` ASC) VISIBLE,
  CONSTRAINT `fk_tb_role_userOption_tb_role1`
    FOREIGN KEY (`pk_fk_roleId`)
    REFERENCES `db_e-commerce_system`.`tb_role` (`pk_roleId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tb_role_userOption_tb_userOption1`
    FOREIGN KEY (`pk_fk_userOptionId`)
    REFERENCES `db_e-commerce_system`.`tb_userOption` (`pk_userOptionId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
