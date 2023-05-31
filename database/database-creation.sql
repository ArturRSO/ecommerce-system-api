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
                                                                `description` VARCHAR(200) NOT NULL,
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
                                                                `imagePath` VARCHAR(500) NULL,
                                                                `creationDate` DATETIME NOT NULL,
                                                                `lastUpdate` DATETIME NULL,
                                                                `verifiedEmail` TINYINT NOT NULL,
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
                                                                     `fk_telephoneTypeId` INT NOT NULL,
                                                                     `internationalCode` VARCHAR(5) NOT NULL,
                                                                     `localCode` INT NOT NULL,
                                                                     `number` VARCHAR(9) NOT NULL,
                                                                     `creationDate` DATETIME NOT NULL,
                                                                     `lastUpdate` DATETIME NULL,
                                                                     `isActive` TINYINT NOT NULL,
                                                                     INDEX `fk_tb_telephone_tb_telephoneType1_idx` (`fk_telephoneTypeId` ASC) VISIBLE,
                                                                     PRIMARY KEY (`pk_telephoneId`),
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
                                                                   PRIMARY KEY (`pk_addressId`))
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
                                                                 `fk_telephoneId` INT NOT NULL,
                                                                 `imagePath` VARCHAR(500) NULL,
                                                                 `creationDate` DATETIME NOT NULL,
                                                                 `lastUpdate` DATETIME NULL,
                                                                 `isActive` TINYINT NOT NULL,
                                                                 PRIMARY KEY (`pk_storeId`),
                                                                 INDEX `fk_tb_store_tb_documentType1_idx` (`fk_documentTypeId` ASC) VISIBLE,
                                                                 INDEX `fk_tb_store_tb_address1_idx` (`fk_addressId` ASC) VISIBLE,
                                                                 INDEX `fk_tb_store_tb_telephone1_idx` (`fk_telephoneId` ASC) VISIBLE,
                                                                 CONSTRAINT `fk_tb_store_tb_documentType1`
                                                                     FOREIGN KEY (`fk_documentTypeId`)
                                                                         REFERENCES `db_e-commerce_system`.`tb_documentType` (`pk_documentTypeId`)
                                                                         ON DELETE NO ACTION
                                                                         ON UPDATE NO ACTION,
                                                                 CONSTRAINT `fk_tb_store_tb_address1`
                                                                     FOREIGN KEY (`fk_addressId`)
                                                                         REFERENCES `db_e-commerce_system`.`tb_address` (`pk_addressId`)
                                                                         ON DELETE NO ACTION
                                                                         ON UPDATE NO ACTION,
                                                                 CONSTRAINT `fk_tb_store_tb_telephone1`
                                                                     FOREIGN KEY (`fk_telephoneId`)
                                                                         REFERENCES `db_e-commerce_system`.`tb_telephone` (`pk_telephoneId`)
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
                                                                        `pk_orderSummaryId` INT NOT NULL AUTO_INCREMENT,
                                                                        `fk_userId` INT NOT NULL,
                                                                        `fk_paymentMethodId` INT NOT NULL,
                                                                        `totalPrice` DECIMAL(19,4) NOT NULL,
                                                                        `totalDiscountPercentage` DECIMAL(19,4) NOT NULL,
                                                                        `finalPrice` DECIMAL(19,4) NOT NULL,
                                                                        `installment` INT NOT NULL,
                                                                        `creationDate` DATETIME NOT NULL,
                                                                        `lastUpdate` DATETIME NULL,
                                                                        `fk_orderStatusId` INT NOT NULL,
                                                                        PRIMARY KEY (`pk_orderSummaryId`),
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
                                                                 `fk_orderSummaryId` INT NOT NULL,
                                                                 `fk_storeId` INT NOT NULL,
                                                                 `totalPrice` DECIMAL(19,4) NOT NULL,
                                                                 `totalDiscountPercentage` DECIMAL(19,4) NOT NULL,
                                                                 `finalPrice` DECIMAL(19,4) NOT NULL,
                                                                 `creationDate` DATETIME NOT NULL,
                                                                 `lastUpdate` DATETIME NULL,
                                                                 `fk_orderStatusId` INT NOT NULL,
                                                                 PRIMARY KEY (`pk_orderId`),
                                                                 INDEX `fk_tb_order_tb_store1_idx` (`fk_storeId` ASC) VISIBLE,
                                                                 INDEX `fk_tb_order_tb_orderStatus1_idx` (`fk_orderStatusId` ASC) VISIBLE,
                                                                 INDEX `fk_tb_order_tb_orderSummary1_idx` (`fk_orderSummaryId` ASC) VISIBLE,
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
                                                                     FOREIGN KEY (`fk_orderSummaryId`)
                                                                         REFERENCES `db_e-commerce_system`.`tb_orderSummary` (`pk_orderSummaryId`)
                                                                         ON DELETE NO ACTION
                                                                         ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db_e-commerce_system`.`tb_systemCashFlow`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_e-commerce_system`.`tb_systemCashFlow` (
                                                                          `pk_cashFlowId` INT NOT NULL AUTO_INCREMENT,
                                                                          `fk_orderId` INT NOT NULL,
                                                                          `value` DECIMAL(19,4) NOT NULL,
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
-- Table `db_e-commerce_system`.`tb_productSubtype`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_e-commerce_system`.`tb_productSubtype` (
                                                                          `pk_productSubtypeId` INT NOT NULL AUTO_INCREMENT,
                                                                          `fk_productTypeId` INT NOT NULL,
                                                                          `name` VARCHAR(200) NOT NULL,
                                                                          PRIMARY KEY (`pk_productSubtypeId`),
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
                                                                   `fk_productSubtypeId` INT NOT NULL,
                                                                   `fk_storeId` INT NOT NULL,
                                                                   `name` VARCHAR(200) NOT NULL,
                                                                   `price` DECIMAL(19,4) NOT NULL,
                                                                   `quantity` INT NOT NULL,
                                                                   `creationDate` DATETIME NOT NULL,
                                                                   `lastUpdate` DATETIME NULL,
                                                                   `isNew` TINYINT NOT NULL,
                                                                   `isActive` TINYINT NOT NULL,
                                                                   PRIMARY KEY (`pk_productId`),
                                                                   INDEX `fk_tb_product_tb_productType1_idx` (`fk_productTypeId` ASC) VISIBLE,
                                                                   INDEX `fk_tb_product_tb_productSubType1_idx` (`fk_productSubtypeId` ASC) VISIBLE,
                                                                   INDEX `fk_tb_product_tb_store1_idx` (`fk_storeId` ASC) VISIBLE,
                                                                   CONSTRAINT `fk_tb_product_tb_productType1`
                                                                       FOREIGN KEY (`fk_productTypeId`)
                                                                           REFERENCES `db_e-commerce_system`.`tb_productType` (`pk_productTypeId`)
                                                                           ON DELETE NO ACTION
                                                                           ON UPDATE NO ACTION,
                                                                   CONSTRAINT `fk_tb_product_tb_productSubType1`
                                                                       FOREIGN KEY (`fk_productSubtypeId`)
                                                                           REFERENCES `db_e-commerce_system`.`tb_productSubtype` (`pk_productSubtypeId`)
                                                                           ON DELETE NO ACTION
                                                                           ON UPDATE NO ACTION,
                                                                   CONSTRAINT `fk_tb_product_tb_store1`
                                                                       FOREIGN KEY (`fk_storeId`)
                                                                           REFERENCES `db_e-commerce_system`.`tb_store` (`pk_storeId`)
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
                                                                    `price` DECIMAL(19,4) NOT NULL,
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
-- Table `db_e-commerce_system`.`tb_product_order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_e-commerce_system`.`tb_product_order` (
                                                                         `pk_fk_productId` INT NOT NULL,
                                                                         `pk_fk_orderId` INT NOT NULL,
                                                                         `quantity` INT NOT NULL,
                                                                         INDEX `fk_tb_product_promotion_order_tb_product1_idx` (`pk_fk_productId` ASC) VISIBLE,
                                                                         INDEX `fk_tb_product_promotion_order_tb_order1_idx` (`pk_fk_orderId` ASC) VISIBLE,
                                                                         PRIMARY KEY (`pk_fk_productId`, `pk_fk_orderId`),
                                                                         CONSTRAINT `fk_tb_product_promotion_order_tb_product1`
                                                                             FOREIGN KEY (`pk_fk_productId`)
                                                                                 REFERENCES `db_e-commerce_system`.`tb_product` (`pk_productId`)
                                                                                 ON DELETE NO ACTION
                                                                                 ON UPDATE NO ACTION,
                                                                         CONSTRAINT `fk_tb_product_promotion_order_tb_order1`
                                                                             FOREIGN KEY (`pk_fk_orderId`)
                                                                                 REFERENCES `db_e-commerce_system`.`tb_order` (`pk_orderId`)
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
                                                                         `value` DECIMAL(19,4) NOT NULL,
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
                                                                         `pk_fk_productId` INT NOT NULL,
                                                                         `pk_fk_detailLabelId` INT NOT NULL,
                                                                         `value` VARCHAR(500) NOT NULL,
                                                                         PRIMARY KEY (`pk_fk_productId`, `pk_fk_detailLabelId`),
                                                                         INDEX `fk_tb_productDetail_tb_product1_idx` (`pk_fk_productId` ASC) VISIBLE,
                                                                         INDEX `fk_tb_productDetail_tb_detailLabel1_idx` (`pk_fk_detailLabelId` ASC) VISIBLE,
                                                                         CONSTRAINT `fk_tb_productDetail_tb_product1`
                                                                             FOREIGN KEY (`pk_fk_productId`)
                                                                                 REFERENCES `db_e-commerce_system`.`tb_product` (`pk_productId`)
                                                                                 ON DELETE NO ACTION
                                                                                 ON UPDATE NO ACTION,
                                                                         CONSTRAINT `fk_tb_productDetail_tb_detailLabel1`
                                                                             FOREIGN KEY (`pk_fk_detailLabelId`)
                                                                                 REFERENCES `db_e-commerce_system`.`tb_detailLabel` (`pk_detailLabelId`)
                                                                                 ON DELETE NO ACTION
                                                                                 ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db_e-commerce_system`.`tb_detailLabel_productSubtype`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_e-commerce_system`.`tb_detailLabel_productSubtype` (
                                                                                      `pk_fk_productSubtypeId` INT NOT NULL,
                                                                                      `pk_fk_detailLabelId` INT NOT NULL,
                                                                                      INDEX `fk_tb_detailLabel_productSubtype_tb_productSubtype1_idx` (`pk_fk_productSubtypeId` ASC) VISIBLE,
                                                                                      INDEX `fk_tb_detailLabel_productSubtype_tb_detailLabel1_idx` (`pk_fk_detailLabelId` ASC) VISIBLE,
                                                                                      PRIMARY KEY (`pk_fk_productSubtypeId`, `pk_fk_detailLabelId`),
                                                                                      CONSTRAINT `fk_tb_detailLabel_productSubtype_tb_productSubtype1`
                                                                                          FOREIGN KEY (`pk_fk_productSubtypeId`)
                                                                                              REFERENCES `db_e-commerce_system`.`tb_productSubtype` (`pk_productSubtypeId`)
                                                                                              ON DELETE NO ACTION
                                                                                              ON UPDATE NO ACTION,
                                                                                      CONSTRAINT `fk_tb_detailLabel_productSubtype_tb_detailLabel1`
                                                                                          FOREIGN KEY (`pk_fk_detailLabelId`)
                                                                                              REFERENCES `db_e-commerce_system`.`tb_detailLabel` (`pk_detailLabelId`)
                                                                                              ON DELETE NO ACTION
                                                                                              ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db_e-commerce_system`.`tb_user_address`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_e-commerce_system`.`tb_user_address` (
                                                                        `pk_fk_userId` INT NOT NULL,
                                                                        `pk_fk_addressId` INT NOT NULL,
                                                                        INDEX `fk_tb_user_address_tb_user1_idx` (`pk_fk_userId` ASC) VISIBLE,
                                                                        INDEX `fk_tb_user_address_tb_address1_idx` (`pk_fk_addressId` ASC) VISIBLE,
                                                                        PRIMARY KEY (`pk_fk_userId`, `pk_fk_addressId`),
                                                                        CONSTRAINT `fk_tb_user_address_tb_user1`
                                                                            FOREIGN KEY (`pk_fk_userId`)
                                                                                REFERENCES `db_e-commerce_system`.`tb_user` (`pk_userId`)
                                                                                ON DELETE NO ACTION
                                                                                ON UPDATE NO ACTION,
                                                                        CONSTRAINT `fk_tb_user_address_tb_address1`
                                                                            FOREIGN KEY (`pk_fk_addressId`)
                                                                                REFERENCES `db_e-commerce_system`.`tb_address` (`pk_addressId`)
                                                                                ON DELETE NO ACTION
                                                                                ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db_e-commerce_system`.`tb_user_telephone`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_e-commerce_system`.`tb_user_telephone` (
                                                                          `pk_fk_userId` INT NOT NULL,
                                                                          `pk_fk_telephoneId` INT NOT NULL,
                                                                          INDEX `fk_tb_user_telephone_tb_user1_idx` (`pk_fk_userId` ASC) VISIBLE,
                                                                          INDEX `fk_tb_user_telephone_tb_telephone1_idx` (`pk_fk_telephoneId` ASC) VISIBLE,
                                                                          PRIMARY KEY (`pk_fk_userId`, `pk_fk_telephoneId`),
                                                                          CONSTRAINT `fk_tb_user_telephone_tb_user1`
                                                                              FOREIGN KEY (`pk_fk_userId`)
                                                                                  REFERENCES `db_e-commerce_system`.`tb_user` (`pk_userId`)
                                                                                  ON DELETE NO ACTION
                                                                                  ON UPDATE NO ACTION,
                                                                          CONSTRAINT `fk_tb_user_telephone_tb_telephone1`
                                                                              FOREIGN KEY (`pk_fk_telephoneId`)
                                                                                  REFERENCES `db_e-commerce_system`.`tb_telephone` (`pk_telephoneId`)
                                                                                  ON DELETE NO ACTION
                                                                                  ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db_e-commerce_system`.`tb_productImage`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_e-commerce_system`.`tb_productImage` (
                                                                        `pk_productImageId` INT NOT NULL AUTO_INCREMENT,
                                                                        `fk_productId` INT NOT NULL,
                                                                        `path` VARCHAR(500) NOT NULL,
                                                                        PRIMARY KEY (`pk_productImageId`),
                                                                        INDEX `fk_tb_productImage_tb_product1_idx` (`fk_productId` ASC) VISIBLE,
                                                                        CONSTRAINT `fk_tb_productImage_tb_product1`
                                                                            FOREIGN KEY (`fk_productId`)
                                                                                REFERENCES `db_e-commerce_system`.`tb_product` (`pk_productId`)
                                                                                ON DELETE NO ACTION
                                                                                ON UPDATE NO ACTION)
    ENGINE = InnoDB;

USE `db_e-commerce_system` ;

-- -----------------------------------------------------
-- Placeholder table for view `db_e-commerce_system`.`vw_systemCashFlowByOrder`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_e-commerce_system`.`vw_systemCashFlowByOrder` (`id` INT, `orderId` INT, `storeId` INT, `storeName` INT, `value` INT, `timestamp` INT);

-- -----------------------------------------------------
-- Placeholder table for view `db_e-commerce_system`.`vw_systemCashFlowRevenue`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_e-commerce_system`.`vw_systemCashFlowRevenue` (`id` INT, `revenue` INT, `timestamp` INT);

-- -----------------------------------------------------
-- Placeholder table for view `db_e-commerce_system`.`vw_storeCashFlowByOrder`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_e-commerce_system`.`vw_storeCashFlowByOrder` (`id` INT, `storeId` INT, `orderId` INT, `value` INT, `productId` INT, `productName` INT, `productQuantity` INT, `timestamp` INT);

-- -----------------------------------------------------
-- Placeholder table for view `db_e-commerce_system`.`vw_storeCashFlowRevenue`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_e-commerce_system`.`vw_storeCashFlowRevenue` (`id` INT, `storeId` INT, `revenue` INT, `timestamp` INT);

-- -----------------------------------------------------
-- Placeholder table for view `db_e-commerce_system`.`vw_storesByUserAndStatus`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_e-commerce_system`.`vw_storesByUserAndStatus` (`id` INT, `userId` INT, `stores` INT, `activeStores` INT);

-- -----------------------------------------------------
-- Placeholder table for view `db_e-commerce_system`.`vw_ordersByStoreAndStatus`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_e-commerce_system`.`vw_ordersByStoreAndStatus` (`id` INT, `storeId` INT, `storeName` INT, `orders` INT, `receivedOrders` INT, `paidOrders` INT, `sentOrders` INT, `finishedOrders` INT);

-- -----------------------------------------------------
-- Placeholder table for view `db_e-commerce_system`.`vw_productsByStoreAndStatus`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_e-commerce_system`.`vw_productsByStoreAndStatus` (`id` INT, `storeId` INT, `storeName` INT, `products` INT, `activeProducts` INT);

-- -----------------------------------------------------
-- Placeholder table for view `db_e-commerce_system`.`vw_usersCountByRole`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_e-commerce_system`.`vw_usersCountByRole` (`id` INT, `users` INT, `admins` INT, `storeAdmins` INT, `customers` INT);

-- -----------------------------------------------------
-- Placeholder table for view `db_e-commerce_system`.`vw_storesCountByStatus`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_e-commerce_system`.`vw_storesCountByStatus` (`id` INT, `stores` INT, `activeStores` INT);

-- -----------------------------------------------------
-- View `db_e-commerce_system`.`vw_systemCashFlowByOrder`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `db_e-commerce_system`.`vw_systemCashFlowByOrder`;
USE `db_e-commerce_system`;
CREATE  OR REPLACE VIEW `vw_systemCashFlowByOrder` AS
SELECT UUID() AS id , o.pk_orderId AS orderId, s.pk_storeId as storeId, s.name AS storeName, scf.value, scf.timestamp
FROM tb_order o
         INNER JOIN tb_systemCashFlow scf ON scf.fk_orderId = o.pk_orderId
         INNER JOIN tb_store s ON s.pk_storeId = o.fk_storeId
ORDER BY scf.timestamp ASC;

-- -----------------------------------------------------
-- View `db_e-commerce_system`.`vw_systemCashFlowRevenue`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `db_e-commerce_system`.`vw_systemCashFlowRevenue`;
USE `db_e-commerce_system`;
CREATE  OR REPLACE VIEW `vw_systemCashFlowRevenue` AS
SELECT UUID() AS id, SUM(scf.value) as revenue, scf.timestamp
FROM tb_systemCashFlow scf
GROUP BY scf.timestamp;

-- -----------------------------------------------------
-- View `db_e-commerce_system`.`vw_storeCashFlowByOrder`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `db_e-commerce_system`.`vw_storeCashFlowByOrder`;
USE `db_e-commerce_system`;
CREATE  OR REPLACE VIEW `vw_storeCashFlowByOrder` AS
SELECT
    UUID() AS id,
    o.fk_storeId AS storeId,
    o.pk_orderId AS orderId,
    scf.value,
    p.pk_productId AS productId,
    p.name AS productName,
    po.quantity AS productQuantity,
    scf.timestamp
FROM tb_order o
         INNER JOIN tb_product_order po ON po.pk_fk_orderId = o.pk_orderId
         INNER JOIN tb_product p ON p.pk_productId = po.pk_fk_productId
         INNER JOIN tb_storeCashFlow scf ON scf.fk_orderId = o.pk_orderId
ORDER BY scf.timestamp ASC;

-- -----------------------------------------------------
-- View `db_e-commerce_system`.`vw_storeCashFlowRevenue`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `db_e-commerce_system`.`vw_storeCashFlowRevenue`;
USE `db_e-commerce_system`;
CREATE  OR REPLACE VIEW `vw_storeCashFlowRevenue` AS
SELECT UUID() AS id, scf.fk_storeId AS storeId, SUM(scf.value) as revenue, scf.timestamp
FROM tb_storeCashFlow scf
GROUP BY scf.fk_storeId;

-- -----------------------------------------------------
-- View `db_e-commerce_system`.`vw_storesByUserAndStatus`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `db_e-commerce_system`.`vw_storesByUserAndStatus`;
USE `db_e-commerce_system`;
CREATE  OR REPLACE VIEW `vw_storesByUserAndStatus` AS
SELECT
    UUID() AS id,
    su.pk_fk_userId AS userId,
    COUNT(IF(s.isActive = true, 1, null)) AS stores,
    COUNT(IF(pss.activeProducts > 0, 1, null)) AS activeStores
FROM tb_store s
         INNER JOIN tb_store_user su ON su.pk_fk_storeId = s.pk_storeId
         INNER JOIN vw_productsByStoreAndStatus pss ON pss.storeId = s.pk_storeId
GROUP BY su.pk_fk_userId
ORDER BY su.pk_fk_userId ASC;

-- -----------------------------------------------------
-- View `db_e-commerce_system`.`vw_ordersByStoreAndStatus`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `db_e-commerce_system`.`vw_ordersByStoreAndStatus`;
USE `db_e-commerce_system`;
CREATE  OR REPLACE VIEW `vw_ordersByStoreAndStatus` AS
SELECT
    UUID() AS id,
    s.pk_storeId AS storeId,
    s.name AS storeName,
    COUNT(o.pk_orderId) AS orders,
    COUNT(IF(o.fk_orderStatusId = 1, 1, null)) AS receivedOrders,
    COUNT(IF(o.fk_orderStatusId = 2, 1, null)) AS paidOrders,
    COUNT(IF(o.fk_orderStatusId = 3, 1, null)) AS sentOrders,
    COUNT(IF(o.fk_orderStatusId = 4, 1, null)) AS finishedOrders
FROM tb_order o
         INNER JOIN tb_store s ON s.pk_storeId = o.fk_storeId
GROUP BY s.pk_storeId
ORDER BY s.pk_storeId ASC;

-- -----------------------------------------------------
-- View `db_e-commerce_system`.`vw_productsByStoreAndStatus`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `db_e-commerce_system`.`vw_productsByStoreAndStatus`;
USE `db_e-commerce_system`;
CREATE  OR REPLACE VIEW `vw_productsByStoreAndStatus` AS
SELECT
    UUID() AS id,
    s.pk_storeId AS storeId,
    s.name AS storeName,
    COUNT(IF(p.isActive = true, 1, null)) AS products,
    COUNT(IF(p.quantity > 0 AND p.isActive = true, 1, null)) AS activeProducts
FROM tb_product p
         INNER JOIN tb_store s ON s.pk_storeId = p.fk_storeId
WHERE s.isActive = true
GROUP BY s.pk_storeId
ORDER BY s.pk_storeId ASC;

-- -----------------------------------------------------
-- View `db_e-commerce_system`.`vw_usersCountByRole`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `db_e-commerce_system`.`vw_usersCountByRole`;
USE `db_e-commerce_system`;
CREATE  OR REPLACE VIEW `vw_usersCountByRole` AS
SELECT
    UUID() AS id,
    COUNT(IF(u.isActive = true, 1, null)) AS users,
    COUNT(IF(u.isActive = true AND u.fk_roleId = 1, 1, null)) AS admins,
    COUNT(IF(u.isActive = true AND u.fk_roleId = 2, 1, null)) AS storeAdmins,
    COUNT(IF(u.isActive = true AND u.fk_roleId = 3, 1, null)) AS customers
FROM tb_user u;

-- -----------------------------------------------------
-- View `db_e-commerce_system`.`vw_storesCountByStatus`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `db_e-commerce_system`.`vw_storesCountByStatus`;
USE `db_e-commerce_system`;
CREATE  OR REPLACE VIEW `vw_storesCountByStatus` AS
SELECT
    UUID() AS id,
    COUNT(IF(s.isActive = true, 1, null)) AS stores,
    COUNT(IF(pss.activeProducts > 0, 1, null)) AS activeStores
FROM tb_store s
         INNER JOIN vw_productsByStoreAndStatus pss ON pss.storeId = s.pk_storeId;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
