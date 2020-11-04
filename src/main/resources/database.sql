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
  `documentNumber` VARCHAR(25) NOT NULL,
  `fk_documentTypeId` INT NOT NULL,
  `fk_roleId` INT NOT NULL,
  `birthday` DATE NOT NULL,
  `profileImagePath` VARCHAR(500) NULL,
  `creationDate` DATETIME NOT NULL,
  `lastUpdate` DATETIME NULL,
  `verifiedEmail` TINYINT NOT NULL,
  `isActive` TINYINT NOT NULL,
  PRIMARY KEY (`pk_userId`),
  INDEX `fk_tb_user_tb_role_idx` (`fk_roleId` ASC) VISIBLE,
  INDEX `fk_tb_user_tb_documentType1_idx` (`fk_documentTypeId` ASC) VISIBLE,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
  UNIQUE INDEX `documentNumber_UNIQUE` (`documentNumber` ASC) VISIBLE,
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
-- Table `db_e-commerce_system`.`tb_store`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_e-commerce_system`.`tb_store` (
  `pk_storeId` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(200) NOT NULL,
  `documentNumber` VARCHAR(14) NOT NULL,
  `fk_documentTypeId` INT NOT NULL,
  `fk_addressId` INT NOT NULL,
  `profileImagePath` VARCHAR(500) NULL,
  `creationDate` DATETIME NOT NULL,
  `lastUpdate` DATETIME NULL,
  `isActive` TINYINT NOT NULL,
  PRIMARY KEY (`pk_storeId`),
  INDEX `fk_tb_store_tb_documentType1_idx` (`fk_documentTypeId` ASC) VISIBLE,
  INDEX `fk_tb_store_tb_address1_idx` (`fk_addressId` ASC) VISIBLE,
  CONSTRAINT `fk_tb_store_tb_documentType1`
    FOREIGN KEY (`fk_documentTypeId`)
    REFERENCES `db_e-commerce_system`.`tb_documentType` (`pk_documentTypeId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tb_store_tb_address1`
    FOREIGN KEY (`fk_addressId`)
    REFERENCES `db_e-commerce_system`.`tb_address` (`pk_addressId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db_e-commerce_system`.`tb_store_user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_e-commerce_system`.`tb_store_user` (
  `pk_fk_storeId` INT NOT NULL,
  `pk_fk_userId` INT NOT NULL,
  INDEX `fk_tb_store_user_tb_store1_idx` (`pk_fk_storeId` ASC) VISIBLE,
  INDEX `fk_tb_store_user_tb_user1_idx` (`pk_fk_userId` ASC) VISIBLE,
  PRIMARY KEY (`pk_fk_storeId`, `pk_fk_userId`),
  CONSTRAINT `fk_tb_store_user_tb_store1`
    FOREIGN KEY (`pk_fk_storeId`)
    REFERENCES `db_e-commerce_system`.`tb_store` (`pk_storeId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tb_store_user_tb_user1`
    FOREIGN KEY (`pk_fk_userId`)
    REFERENCES `db_e-commerce_system`.`tb_user` (`pk_userId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db_e-commerce_system`.`tb_orderStatus`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_e-commerce_system`.`tb_orderStatus` (
  `pk_orderStatusId` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(200) NOT NULL,
  PRIMARY KEY (`pk_orderStatusId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db_e-commerce_system`.`tb_paymentMethod`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_e-commerce_system`.`tb_paymentMethod` (
  `pk_paymentMethodId` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(200) NOT NULL,
  `maxInstallment` INT NOT NULL,
  `isEnable` TINYINT NOT NULL,
  PRIMARY KEY (`pk_paymentMethodId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db_e-commerce_system`.`tb_orderSummary`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_e-commerce_system`.`tb_orderSummary` (
  `pk_orderSummary` INT NOT NULL AUTO_INCREMENT,
  `fk_userId` INT NOT NULL,
  `fk_paymentMethodId` INT NOT NULL,
  `totalPrice` DECIMAL NOT NULL,
  `totalDiscountPercentage` DECIMAL NOT NULL,
  `finalPrice` DECIMAL NOT NULL,
  `installment` INT NOT NULL,
  `creationDate` DATETIME NOT NULL,
  `lastUpdate` DATETIME NULL,
  `fk_orderStatusId` INT NOT NULL,
  PRIMARY KEY (`pk_orderSummary`),
  INDEX `fk_tb_orderSummary_tb_user1_idx` (`fk_userId` ASC) VISIBLE,
  INDEX `fk_tb_orderSummary_tb_paymentMethod1_idx` (`fk_paymentMethodId` ASC) VISIBLE,
  INDEX `fk_tb_orderSummary_tb_orderStatus1_idx` (`fk_orderStatusId` ASC) VISIBLE,
  CONSTRAINT `fk_tb_orderSummary_tb_user1`
    FOREIGN KEY (`fk_userId`)
    REFERENCES `db_e-commerce_system`.`tb_user` (`pk_userId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tb_orderSummary_tb_paymentMethod1`
    FOREIGN KEY (`fk_paymentMethodId`)
    REFERENCES `db_e-commerce_system`.`tb_paymentMethod` (`pk_paymentMethodId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tb_orderSummary_tb_orderStatus1`
    FOREIGN KEY (`fk_orderStatusId`)
    REFERENCES `db_e-commerce_system`.`tb_orderStatus` (`pk_orderStatusId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db_e-commerce_system`.`tb_order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_e-commerce_system`.`tb_order` (
  `pk_orderId` INT NOT NULL AUTO_INCREMENT,
  `fk_orderSummary` INT NOT NULL,
  `fk_storeId` INT NOT NULL,
  `totalPrice` DECIMAL NOT NULL,
  `totalDiscountPercentage` DECIMAL NOT NULL,
  `finalPrice` DECIMAL NOT NULL,
  `creationDate` DATETIME NOT NULL,
  `lastUpdate` DATETIME NULL,
  `fk_orderStatusId` INT NOT NULL,
  PRIMARY KEY (`pk_orderId`),
  INDEX `fk_tb_order_tb_store1_idx` (`fk_storeId` ASC) VISIBLE,
  INDEX `fk_tb_order_tb_orderStatus1_idx` (`fk_orderStatusId` ASC) VISIBLE,
  INDEX `fk_tb_order_tb_orderSummary1_idx` (`fk_orderSummary` ASC) VISIBLE,
  CONSTRAINT `fk_tb_order_tb_store1`
    FOREIGN KEY (`fk_storeId`)
    REFERENCES `db_e-commerce_system`.`tb_store` (`pk_storeId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tb_order_tb_orderStatus1`
    FOREIGN KEY (`fk_orderStatusId`)
    REFERENCES `db_e-commerce_system`.`tb_orderStatus` (`pk_orderStatusId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tb_order_tb_orderSummary1`
    FOREIGN KEY (`fk_orderSummary`)
    REFERENCES `db_e-commerce_system`.`tb_orderSummary` (`pk_orderSummary`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db_e-commerce_system`.`tb_systemCashFlow`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_e-commerce_system`.`tb_systemCashFlow` (
  `pk_cashFlowId` INT NOT NULL AUTO_INCREMENT,
  `fk_orderId` INT NOT NULL,
  `value` DECIMAL NOT NULL,
  `timestamp` DATETIME NOT NULL,
  PRIMARY KEY (`pk_cashFlowId`),
  INDEX `fk_tb_cashFlow_tb_order1_idx` (`fk_orderId` ASC) VISIBLE,
  CONSTRAINT `fk_tb_cashFlow_tb_order1`
    FOREIGN KEY (`fk_orderId`)
    REFERENCES `db_e-commerce_system`.`tb_order` (`pk_orderId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db_e-commerce_system`.`tb_productType`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_e-commerce_system`.`tb_productType` (
  `pk_productTypeId` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(200) NOT NULL,
  PRIMARY KEY (`pk_productTypeId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db_e-commerce_system`.`tb_productSubType`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_e-commerce_system`.`tb_productSubType` (
  `pk_productSubTypeId` INT NOT NULL AUTO_INCREMENT,
  `fk_productTypeId` INT NOT NULL,
  `name` VARCHAR(200) NOT NULL,
  PRIMARY KEY (`pk_productSubTypeId`),
  INDEX `fk_tb_productSubType_tb_productType1_idx` (`fk_productTypeId` ASC) VISIBLE,
  CONSTRAINT `fk_tb_productSubType_tb_productType1`
    FOREIGN KEY (`fk_productTypeId`)
    REFERENCES `db_e-commerce_system`.`tb_productType` (`pk_productTypeId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db_e-commerce_system`.`tb_product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_e-commerce_system`.`tb_product` (
  `pk_productId` INT NOT NULL AUTO_INCREMENT,
  `fk_productTypeId` INT NOT NULL,
  `fk_productSubTypeId` INT NOT NULL,
  `fk_storeId` INT NOT NULL,
  `name` VARCHAR(200) NOT NULL,
  `imagePath` VARCHAR(500) NOT NULL,
  `price` DECIMAL NOT NULL,
  `quantity` INT NOT NULL,
  `creationDate` DATETIME NOT NULL,
  `lastUpdate` DATETIME NULL,
  `isActive` TINYINT NOT NULL,
  PRIMARY KEY (`pk_productId`),
  INDEX `fk_tb_product_tb_productType1_idx` (`fk_productTypeId` ASC) VISIBLE,
  INDEX `fk_tb_product_tb_productSubType1_idx` (`fk_productSubTypeId` ASC) VISIBLE,
  INDEX `fk_tb_product_tb_store1_idx` (`fk_storeId` ASC) VISIBLE,
  CONSTRAINT `fk_tb_product_tb_productType1`
    FOREIGN KEY (`fk_productTypeId`)
    REFERENCES `db_e-commerce_system`.`tb_productType` (`pk_productTypeId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tb_product_tb_productSubType1`
    FOREIGN KEY (`fk_productSubTypeId`)
    REFERENCES `db_e-commerce_system`.`tb_productSubType` (`pk_productSubTypeId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tb_product_tb_store1`
    FOREIGN KEY (`fk_storeId`)
    REFERENCES `db_e-commerce_system`.`tb_store` (`pk_storeId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db_e-commerce_system`.`tb_detailLabel`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_e-commerce_system`.`tb_detailLabel` (
  `pk_detailLabelId` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(200) NOT NULL,
  PRIMARY KEY (`pk_detailLabelId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db_e-commerce_system`.`tb_productDetail`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_e-commerce_system`.`tb_productDetail` (
  `pk_productDetailId` INT NOT NULL AUTO_INCREMENT,
  `fk_detailLabelId` INT NOT NULL,
  `fk_productId` INT NOT NULL,
  `description` VARCHAR(500) NOT NULL,
  PRIMARY KEY (`pk_productDetailId`),
  INDEX `fk_tb_productDetail_tb_detailLabel1_idx` (`fk_detailLabelId` ASC) VISIBLE,
  INDEX `fk_tb_productDetail_tb_product1_idx` (`fk_productId` ASC) VISIBLE,
  CONSTRAINT `fk_tb_productDetail_tb_detailLabel1`
    FOREIGN KEY (`fk_detailLabelId`)
    REFERENCES `db_e-commerce_system`.`tb_detailLabel` (`pk_detailLabelId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tb_productDetail_tb_product1`
    FOREIGN KEY (`fk_productId`)
    REFERENCES `db_e-commerce_system`.`tb_product` (`pk_productId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db_e-commerce_system`.`tb_deliveryService`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_e-commerce_system`.`tb_deliveryService` (
  `pk_deliveryServiceId` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(200) NOT NULL,
  PRIMARY KEY (`pk_deliveryServiceId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db_e-commerce_system`.`tb_delivery`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_e-commerce_system`.`tb_delivery` (
  `pk_deliveryId` INT NOT NULL AUTO_INCREMENT,
  `fk_deliveryServiceId` INT NOT NULL,
  `fk_orderId` INT NOT NULL,
  `fk_senderAddressId` INT NOT NULL,
  `fk_receiverAddressId` INT NOT NULL,
  `price` DECIMAL NOT NULL,
  `isSuccess` TINYINT NOT NULL,
  PRIMARY KEY (`pk_deliveryId`),
  INDEX `fk_tb_delivery_tb_deliveryService1_idx` (`fk_deliveryServiceId` ASC) VISIBLE,
  INDEX `fk_tb_delivery_tb_address1_idx` (`fk_senderAddressId` ASC) VISIBLE,
  INDEX `fk_tb_delivery_tb_address2_idx` (`fk_receiverAddressId` ASC) VISIBLE,
  INDEX `fk_tb_delivery_tb_order1_idx` (`fk_orderId` ASC) VISIBLE,
  CONSTRAINT `fk_tb_delivery_tb_deliveryService1`
    FOREIGN KEY (`fk_deliveryServiceId`)
    REFERENCES `db_e-commerce_system`.`tb_deliveryService` (`pk_deliveryServiceId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tb_delivery_tb_address1`
    FOREIGN KEY (`fk_senderAddressId`)
    REFERENCES `db_e-commerce_system`.`tb_address` (`pk_addressId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tb_delivery_tb_address2`
    FOREIGN KEY (`fk_receiverAddressId`)
    REFERENCES `db_e-commerce_system`.`tb_address` (`pk_addressId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tb_delivery_tb_order1`
    FOREIGN KEY (`fk_orderId`)
    REFERENCES `db_e-commerce_system`.`tb_order` (`pk_orderId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db_e-commerce_system`.`tb_promotionType`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_e-commerce_system`.`tb_promotionType` (
  `pk_promotionTypeId` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(200) NOT NULL,
  `type` VARCHAR(25) NOT NULL,
  PRIMARY KEY (`pk_promotionTypeId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db_e-commerce_system`.`tb_promotion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_e-commerce_system`.`tb_promotion` (
  `pk_promotionId` INT NOT NULL AUTO_INCREMENT,
  `fk_promotionTypeId` INT NOT NULL,
  `name` VARCHAR(200) NOT NULL,
  `startDate` DATETIME NOT NULL,
  `endDate` DATETIME NOT NULL,
  `discount` DECIMAL NULL,
  `isPercentage` TINYINT NOT NULL,
  `orderPriceMinimum` DECIMAL NULL,
  `orderPriceMaximum` DECIMAL NULL,
  `creationDate` DATETIME NOT NULL,
  `lastUpdate` DATETIME NULL,
  `isActive` TINYINT NOT NULL,
  PRIMARY KEY (`pk_promotionId`),
  INDEX `fk_tb_promotion_tb_promotionType1_idx` (`fk_promotionTypeId` ASC) VISIBLE,
  CONSTRAINT `fk_tb_promotion_tb_promotionType1`
    FOREIGN KEY (`fk_promotionTypeId`)
    REFERENCES `db_e-commerce_system`.`tb_promotionType` (`pk_promotionTypeId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db_e-commerce_system`.`tb_product_order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_e-commerce_system`.`tb_product_order` (
  `pk_fk_productId` INT NOT NULL,
  `pk_fk_orderId` INT NOT NULL,
  `quantity` INT NOT NULL,
  `pk_fk_promotionId` INT NULL,
  INDEX `fk_tb_product_promotion_order_tb_product1_idx` (`pk_fk_productId` ASC) VISIBLE,
  INDEX `fk_tb_product_promotion_order_tb_promotion1_idx` (`pk_fk_promotionId` ASC) VISIBLE,
  INDEX `fk_tb_product_promotion_order_tb_order1_idx` (`pk_fk_orderId` ASC) VISIBLE,
  PRIMARY KEY (`pk_fk_productId`, `pk_fk_orderId`),
  CONSTRAINT `fk_tb_product_promotion_order_tb_product1`
    FOREIGN KEY (`pk_fk_productId`)
    REFERENCES `db_e-commerce_system`.`tb_product` (`pk_productId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tb_product_promotion_order_tb_promotion1`
    FOREIGN KEY (`pk_fk_promotionId`)
    REFERENCES `db_e-commerce_system`.`tb_promotion` (`pk_promotionId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tb_product_promotion_order_tb_order1`
    FOREIGN KEY (`pk_fk_orderId`)
    REFERENCES `db_e-commerce_system`.`tb_order` (`pk_orderId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db_e-commerce_system`.`tb_promotion_product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_e-commerce_system`.`tb_promotion_product` (
  `pk_fk_productId` INT NOT NULL,
  `pk_fk__promotionId` INT NOT NULL,
  `discount` DECIMAL NOT NULL,
  INDEX `fk_tb_promotion_product_tb_product1_idx` (`pk_fk_productId` ASC) VISIBLE,
  INDEX `fk_tb_promotion_product_tb_promotion1_idx` (`pk_fk__promotionId` ASC) VISIBLE,
  PRIMARY KEY (`pk_fk__promotionId`, `pk_fk_productId`),
  CONSTRAINT `fk_tb_promotion_product_tb_product1`
    FOREIGN KEY (`pk_fk_productId`)
    REFERENCES `db_e-commerce_system`.`tb_product` (`pk_productId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tb_promotion_product_tb_promotion1`
    FOREIGN KEY (`pk_fk__promotionId`)
    REFERENCES `db_e-commerce_system`.`tb_promotion` (`pk_promotionId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db_e-commerce_system`.`tb_storeCashFlow`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_e-commerce_system`.`tb_storeCashFlow` (
  `pk_storeCashFlowId` INT NOT NULL AUTO_INCREMENT,
  `fk_storeId` INT NOT NULL,
  `fk_orderId` INT NOT NULL,
  `value` DECIMAL NOT NULL,
  `timestamp` DATETIME NOT NULL,
  PRIMARY KEY (`pk_storeCashFlowId`),
  INDEX `fk_tb_storeCashFlow_tb_store1_idx` (`fk_storeId` ASC) VISIBLE,
  INDEX `fk_tb_storeCashFlow_tb_order1_idx` (`fk_orderId` ASC) VISIBLE,
  CONSTRAINT `fk_tb_storeCashFlow_tb_store1`
    FOREIGN KEY (`fk_storeId`)
    REFERENCES `db_e-commerce_system`.`tb_store` (`pk_storeId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tb_storeCashFlow_tb_order1`
    FOREIGN KEY (`fk_orderId`)
    REFERENCES `db_e-commerce_system`.`tb_order` (`pk_orderId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
