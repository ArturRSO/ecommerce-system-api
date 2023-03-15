-- SYSTEM ADMIN 
-- Password1

INSERT INTO `db_e-commerce_system`.`tb_user`
(`firstName`, `lastName`, `email`, `password`, `documentNumber`, `fk_documentTypeId`,
`fk_roleId`, `birthday`,`imagePath`, `creationDate`, `lastUpdate`, `verifiedEmail`, `isActive`)
VALUES
('Test', 'One', 'test.one@mail.com',
'c799c0765e15ed61ac4b6c092305126a8493fa811bf6fa884c9201d8bf83e84d39ea97fdf54c2e5f440f81ea945c8700882e23af938f04b64f753849be4f7510',
'11111111111', 1, 1, '1980-01-01', 'A:\\E-commerce System\\Images\\Users\\default.jpg', now(), null, true, true);

-- STORE ADMIN
-- Password2

INSERT INTO `db_e-commerce_system`.`tb_user`
(`firstName`, `lastName`, `email`, `password`, `documentNumber`, `fk_documentTypeId`,
`fk_roleId`, `birthday`,`imagePath`, `creationDate`, `lastUpdate`, `verifiedEmail`, `isActive`)
VALUES
('Test', 'Two', 'arturdev@duck.com',
'31f13b2c67df2fd616fe09be5a952fb9c5f0625be4bf7f0c104fc0b1e7b2c0b2c084a023a22854b2d9960bf33703520d47aaee59db0d5005311b4e7bd7187548',
'22222222222', 1, 2, '1978-01-01', 'A:\\E-commerce System\\Images\\Users\\default.jpg', now(), null, true, true);

-- CUSTOMER
-- Password3

INSERT INTO `db_e-commerce_system`.`tb_user`
(`firstName`, `lastName`, `email`, `password`, `documentNumber`, `fk_documentTypeId`,
`fk_roleId`, `birthday`,`imagePath`, `creationDate`, `lastUpdate`, `verifiedEmail`, `isActive`)
VALUES
('Test', 'Three', 'test.three@mail.com',
'7c7d3955859619c99beb55b112b0ce660be8a5a58a93ba71b17aa066e4585c07be9c0adb40b285c9bd24b53ccac7495b2e25a17c442153d9bdbcab9914d5639f',
'33333333333', 1, 3, '1978-01-01', 'A:\\E-commerce System\\Images\\Users\\default.jpg', now(), null, true, true);

-- ADDRESS ID = 1
INSERT INTO `db_e-commerce_system`.`tb_address`
(`country`,
`postalCode`,
`address`,
`number`,
`stateCode`,
`city`,
`district`,
`complement`,
`creationDate`,
`lastUpdate`,
`isActive`)
VALUES
('Brasil',
'01310300',
'Avenida Paulista',
2300,
'SP',
'São Paulo',
'Bela Vista',
'12º andar',
now(),
null,
true);

-- USER ID = 3
INSERT INTO `db_e-commerce_system`.`tb_address`
(`country`,
`postalCode`,
`address`,
`number`,
`stateCode`,
`city`,
`district`,
`complement`,
`creationDate`,
`lastUpdate`,
`isActive`)
VALUES
('Brasil',
'03331030',
'Rua Florindo Brás',
123,
'SP',
'São Paulo',
'Quarta Parada',
'5º andar',
now(),
null,
true);

INSERT INTO tb_user_address (pk_fk_userId, pk_fk_addressId)
VALUES
(3, 2);

-- USER ID = 3
-- TELEPHONE ID = 1
INSERT INTO `db_e-commerce_system`.`tb_telephone`
(`fk_telephoneTypeId`,
`internationalCode`,
`localCode`,
`number`,
`creationDate`,
`lastUpdate`,
`isActive`)
VALUES
(1,
'+55',
11,
'912345678',
now(),
null,
true);

INSERT INTO `db_e-commerce_system`.`tb_user_telephone`
(`pk_fk_userId`,
`pk_fk_telephoneId`)
VALUES
(3, 1);

-- STORE ID 1
INSERT INTO `db_e-commerce_system`.`tb_telephone`
(`fk_telephoneTypeId`,
`internationalCode`,
`localCode`,
`number`,
`creationDate`,
`lastUpdate`,
`isActive`)
VALUES
(2,
'+55',
11,
'912345678',
now(),
null,
true);

-- USER ID 2
-- ADDRESS ID 1
-- TELEPHONE ID 2

INSERT INTO `db_e-commerce_system`.`tb_store`
(`name`,
`documentNumber`,
`fk_documentTypeId`,
`fk_addressId`,
`fk_telephoneId`,
`imagePath`,
`creationDate`,
`lastUpdate`,
`isActive`)
VALUES
('One Celulares',
'40227411000139',
2,
1,
2,
'A:\\E-commerce System\\Images\\Stores\\default.jpg',
now(),
null,
true);

INSERT INTO tb_store_user (pk_fk_storeId, pk_fk_userId) 
VALUES
(1, 2);

-- STORE ID 1
INSERT INTO `db_e-commerce_system`.`tb_product`
(`fk_productTypeId`,
`fk_productSubTypeId`,
`fk_storeId`,
`name`,
`price`,
`quantity`,
`creationDate`,
`lastUpdate`,
`isNew`,
`isActive`)
VALUES
(1,
1,
1,
'Apple iPhone 11',
4599.99,
20,
now(),
null,
true,
true);

-- DETAILS
INSERT INTO `db_e-commerce_system`.`tb_productdetail`
(`pk_fk_productId`,
`pk_fk_detailLabelId`,
`value`)
VALUES
(1, 1, 'iOS 13'),
(1, 2, '150.9 x 75.7 x 8.3 mm'),
(1, 3, '194 gramas'),
(1, 4, '2x 2.65 GHz Lightning + 4x 1.8 GHz Thunder'),
(1, 5, 'Apple A13 Bionic'),
(1, 6, '4 GB'),
(1, 7, '6.1 polegadas'),
(1, 8, 'LCD'),
(1, 9, '828 x 1792 pixel');

-- IMAGES 
INSERT INTO `db_e-commerce_system`.`tb_productimage`
(`fk_productId`,
`path`)
VALUES
(1, 'A:\\E-commerce System\\Images\\Products\\1.jpg');

-- *********************************************************** --

-- STORE ID 1
INSERT INTO `db_e-commerce_system`.`tb_product`
(`fk_productTypeId`,
`fk_productSubTypeId`,
`fk_storeId`,
`name`,
`price`,
`quantity`,
`creationDate`,
`lastUpdate`,
`isNew`,
`isActive`)
VALUES
(1,
1,
1,
'Samsung Galaxy S21 Ultra',
8549.99,
20,
now(),
null,
true,
true);

-- DETAILS
INSERT INTO `db_e-commerce_system`.`tb_productdetail`
(`pk_fk_productId`,
`pk_fk_detailLabelId`,
`value`)
VALUES
(2, 1, 'Android 11 Samsung One UI 3.1'),
(2, 2, '165.1 x 75.6 x 8.9 mm'),
(2, 3, '228 gramas'),
(2, 4, '1x Cortex-X1 2.9 GHz + 3x Cortex-A78 2.8GHz + 4x Cortex-A55 2.2 GHz'),
(2, 5, 'SAMSUNG Exynos 2100'),
(2, 6, '16 GB'),
(2, 7, '6.8 polegadas'),
(2, 8, 'Dynamic AMOLED 2X'),
(2, 9, '1440 x 3200 pixel');

-- IMAGES 
INSERT INTO `db_e-commerce_system`.`tb_productimage`
(`fk_productId`,
`path`)
VALUES
(2, 'A:\\E-commerce System\\Images\\Products\\2.jpg');

-- *********************************************************** --

-- STORE ID 1
INSERT INTO `db_e-commerce_system`.`tb_product`
(`fk_productTypeId`,
`fk_productSubTypeId`,
`fk_storeId`,
`name`,
`price`,
`quantity`,
`creationDate`,
`lastUpdate`,
`isNew`,
`isActive`)
VALUES
(1,
1,
1,
'Nokia 5.3',
1499.99,
20,
now(),
null,
true,
true);

-- DETAILS
INSERT INTO `db_e-commerce_system`.`tb_productdetail`
(`pk_fk_productId`,
`pk_fk_detailLabelId`,
`value`)
VALUES
(3, 1, 'Android 10'),
(3, 2, '164.28 x 76.62 x 8.5 mm'),
(3, 3, '228 gramas'),
(3, 4, '4x 2.0 GHz Kyro 260 + 4x 1.8 GHz Kyro 260'),
(3, 5, 'Snapdragon 665 Qualcomm SDM665'),
(3, 6, '4 GB'),
(3, 7, '6.55 polegadas'),
(3, 8, 'IPS LCD'),
(3, 9, '720 x 1600 pixel');

-- IMAGES 
INSERT INTO `db_e-commerce_system`.`tb_productimage`
(`fk_productId`,
`path`)
VALUES
(3, 'A:\\E-commerce System\\Images\\Products\\3.jpg');

-- *********************************************************** --

-- STORE ID 1
INSERT INTO `db_e-commerce_system`.`tb_product`
(`fk_productTypeId`,
`fk_productSubTypeId`,
`fk_storeId`,
`name`,
`price`,
`quantity`,
`creationDate`,
`lastUpdate`,
`isNew`,
`isActive`)
VALUES
(1,
2,
1,
'Acer Aspire Nitro 5 AN515-55-59MT',
6899.99,
20,
now(),
null,
true,
true);

-- DETAILS
INSERT INTO `db_e-commerce_system`.`tb_productdetail`
(`pk_fk_productId`,
`pk_fk_detailLabelId`,
`value`)
VALUES
(4, 1, 'Windows 10 Home'),
(4, 4, 'Intel Core i5-10300H (10ª geração) – 8 MB de cache'),
(4, 6, '16 GB DDR4 2666 MHz – Expansível até 32 GB'),
(4, 7, '15,6″ polegadas'),
(4, 8, 'IPS 144 Hz antirreflexo'),
(4, 9, 'Full HD (1920 x 1080 px)'),
(4, 10, 'SSD de 512 GB M.2 PCIe NVMe 1x slot já ocupado + 2x slots M.2 livres');

-- IMAGES 
INSERT INTO `db_e-commerce_system`.`tb_productimage`
(`fk_productId`,
`path`)
VALUES
(4, 'A:\\E-commerce System\\Images\\Products\\4.jpg');

-- CUSTOMER ID = 3
-- STORE ID = 1
-- PAYMENT METHOD ID = 1
-- INSTALLMENT = 1
-- ORDER STATUS ID = 4
-- 41 ORDERS

-- *********************************************************** ORDERS ************************************************************
INSERT INTO `db_e-commerce_system`.`tb_ordersummary`
(`fk_userId`,
`fk_paymentMethodId`,
`totalPrice`,
`totalDiscountPercentage`,
`finalPrice`,
`installment`,
`creationDate`,
`lastUpdate`,
`fk_orderStatusId`)
VALUES
(3, 1, 4599.9900, 0, 4599.9900, 1, '2020-10-01 12:30:00', null, 4),
(3, 1, 4599.9900, 0, 4599.9900, 1, '2020-10-02 12:30:00', null, 4),
(3, 1, 4599.9900, 0, 4599.9900, 1, '2020-10-03 12:30:00', null, 4),
(3, 1, 4599.9900, 0, 4599.9900, 1, '2020-10-04 12:30:00', null, 4),
(3, 1, 4599.9900, 0, 4599.9900, 1, '2020-10-05 12:30:00', null, 4),
(3, 1, 4599.9900, 0, 4599.9900, 1, '2020-11-02 13:45:00', null, 4),
(3, 1, 4599.9900, 0, 4599.9900, 1, '2020-11-06 13:45:00', null, 4),
(3, 1, 4599.9900, 0, 4599.9900, 1, '2020-11-07 13:45:00', null, 4),
(3, 1, 4599.9900, 0, 4599.9900, 1, '2020-11-08 13:45:00', null, 4),
(3, 1, 4599.9900, 0, 4599.9900, 1, '2020-11-09 13:45:00', null, 4),
(3, 1, 4599.9900, 0, 4599.9900, 1, '2020-12-10 14:10:00', null, 4),
(3, 1, 4599.9900, 0, 4599.9900, 1, '2020-12-11 14:10:00', null, 4),
(3, 1, 4599.9900, 0, 4599.9900, 1, '2020-12-12 14:10:00', null, 4),
(3, 1, 4599.9900, 0, 4599.9900, 1, '2020-12-13 14:10:00', null, 4),
(3, 1, 4599.9900, 0, 4599.9900, 1, '2020-12-14 14:10:00', null, 4),
(3, 1, 4599.9900, 0, 4599.9900, 1, '2020-01-15 15:22:00', null, 4),
(3, 1, 4599.9900, 0, 4599.9900, 1, '2020-01-16 15:22:00', null, 4),
(3, 1, 4599.9900, 0, 4599.9900, 1, '2020-01-17 15:22:00', null, 4),
(3, 1, 4599.9900, 0, 4599.9900, 1, '2020-01-18 15:22:00', null, 4),
(3, 1, 4599.9900, 0, 4599.9900, 1, '2020-01-19 15:22:00', null, 4),
(3, 1, 4599.9900, 0, 4599.9900, 1, '2020-02-05 16:35:00', null, 4),
(3, 1, 4599.9900, 0, 4599.9900, 1, '2020-02-20 16:35:00', null, 4),
(3, 1, 4599.9900, 0, 4599.9900, 1, '2020-02-21 16:35:00', null, 4),
(3, 1, 4599.9900, 0, 4599.9900, 1, '2020-02-22 16:35:00', null, 4),
(3, 1, 4599.9900, 0, 4599.9900, 1, '2020-02-23 16:35:00', null, 4),
(3, 1, 4599.9900, 0, 4599.9900, 1, '2020-03-06 17:48:00', null, 4),
(3, 1, 4599.9900, 0, 4599.9900, 1, '2020-03-07 17:48:00', null, 4),
(3, 1, 4599.9900, 0, 4599.9900, 1, '2020-03-08 17:48:00', null, 4),
(3, 1, 4599.9900, 0, 4599.9900, 1, '2020-03-09 17:48:00', null, 4),
(3, 1, 4599.9900, 0, 4599.9900, 1, '2020-03-10 17:48:00', null, 4),
(3, 1, 4599.9900, 0, 4599.9900, 1, '2020-04-07 18:50:00', null, 4),
(3, 1, 4599.9900, 0, 4599.9900, 1, '2020-04-11 18:50:00', null, 4),
(3, 1, 4599.9900, 0, 4599.9900, 1, '2020-04-12 18:50:00', null, 4),
(3, 1, 4599.9900, 0, 4599.9900, 1, '2020-04-13 18:50:00', null, 4),
(3, 1, 4599.9900, 0, 4599.9900, 1, '2020-04-14 18:50:00', null, 4),
(3, 1, 4599.9900, 0, 4599.9900, 1, '2020-05-08 19:15:00', null, 4),
(3, 1, 4599.9900, 0, 4599.9900, 1, '2020-05-15 19:15:00', null, 4),
(3, 1, 4599.9900, 0, 4599.9900, 1, '2020-05-16 19:15:00', null, 4),
(3, 1, 4599.9900, 0, 4599.9900, 1, '2020-05-17 19:15:00', null, 4),
(3, 1, 4599.9900, 0, 4599.9900, 1, '2020-05-18 19:15:00', null, 4),
(3, 1, 4599.9900, 0, 4599.9900, 1, '2020-05-19 19:15:00', null, 4);

INSERT INTO `db_e-commerce_system`.`tb_order`
(`fk_orderSummaryId`,
`fk_storeId`,
`totalPrice`,
`totalDiscountPercentage`,
`finalPrice`,
`creationDate`,
`lastUpdate`,
`fk_orderStatusId`)
VALUES
(1, 1, 4599.9900, 0, 4599.9900, '2020-10-01 12:30:00', null, 4),
(2, 1, 4599.9900, 0, 4599.9900, '2020-10-02 12:30:00', null, 4),
(3, 1, 4599.9900, 0, 4599.9900, '2020-10-03 12:30:00', null, 4),
(4, 1, 4599.9900, 0, 4599.9900, '2020-10-04 12:30:00', null, 4),
(5, 1, 4599.9900, 0, 4599.9900, '2020-10-05 12:30:00', null, 4),
(6, 1, 4599.9900, 0, 4599.9900, '2020-11-02 13:45:00', null, 4),
(7, 1, 4599.9900, 0, 4599.9900, '2020-11-06 13:45:00', null, 4),
(8, 1, 4599.9900, 0, 4599.9900, '2020-11-07 13:45:00', null, 4),
(9, 1, 4599.9900, 0, 4599.9900, '2020-11-08 13:45:00', null, 4),
(10, 1, 4599.9900, 0, 4599.9900, '2020-11-09 13:45:00', null, 4),
(11, 1, 4599.9900, 0, 4599.9900, '2020-12-10 14:10:00', null, 4),
(12, 1, 4599.9900, 0, 4599.9900, '2020-12-11 14:10:00', null, 4),
(13, 1, 4599.9900, 0, 4599.9900, '2020-12-12 14:10:00', null, 4),
(14, 1, 4599.9900, 0, 4599.9900, '2020-12-13 14:10:00', null, 4),
(15, 1, 4599.9900, 0, 4599.9900, '2020-12-14 14:10:00', null, 4),
(16, 1, 4599.9900, 0, 4599.9900, '2020-01-15 15:22:00', null, 4),
(17, 1, 4599.9900, 0, 4599.9900, '2020-01-16 15:22:00', null, 4),
(18, 1, 4599.9900, 0, 4599.9900, '2020-01-17 15:22:00', null, 4),
(19, 1, 4599.9900, 0, 4599.9900, '2020-01-18 15:22:00', null, 4),
(20, 1, 4599.9900, 0, 4599.9900, '2020-01-19 15:22:00', null, 4),
(21, 1, 4599.9900, 0, 4599.9900, '2020-02-05 16:35:00', null, 4),
(22, 1, 4599.9900, 0, 4599.9900, '2020-02-20 16:35:00', null, 4),
(23, 1, 4599.9900, 0, 4599.9900, '2020-02-21 16:35:00', null, 4),
(24, 1, 4599.9900, 0, 4599.9900, '2020-02-22 16:35:00', null, 4),
(25, 1, 4599.9900, 0, 4599.9900, '2020-02-23 16:35:00', null, 4),
(26, 1, 4599.9900, 0, 4599.9900, '2020-03-06 17:48:00', null, 4),
(27, 1, 4599.9900, 0, 4599.9900, '2020-03-07 17:48:00', null, 4),
(28, 1, 4599.9900, 0, 4599.9900, '2020-03-08 17:48:00', null, 4),
(29, 1, 4599.9900, 0, 4599.9900, '2020-03-09 17:48:00', null, 4),
(30, 1, 4599.9900, 0, 4599.9900, '2020-03-10 17:48:00', null, 4),
(31, 1, 4599.9900, 0, 4599.9900, '2020-04-07 18:50:00', null, 4),
(32, 1, 4599.9900, 0, 4599.9900, '2020-04-11 18:50:00', null, 4),
(33, 1, 4599.9900, 0, 4599.9900, '2020-04-12 18:50:00', null, 4),
(34, 1, 4599.9900, 0, 4599.9900, '2020-04-13 18:50:00', null, 4),
(35, 1, 4599.9900, 0, 4599.9900, '2020-04-14 18:50:00', null, 4),
(36, 1, 4599.9900, 0, 4599.9900, '2020-05-08 19:15:00', null, 4),
(37, 1, 4599.9900, 0, 4599.9900, '2020-05-15 19:15:00', null, 4),
(38, 1, 4599.9900, 0, 4599.9900, '2020-05-16 19:15:00', null, 4),
(39, 1, 4599.9900, 0, 4599.9900, '2020-05-17 19:15:00', null, 4),
(40, 1, 4599.9900, 0, 4599.9900, '2020-05-18 19:15:00', null, 4),
(41, 1, 4599.9900, 0, 4599.9900, '2020-05-19 19:15:00', null, 4);

INSERT INTO `db_e-commerce_system`.`tb_product_order`
(`pk_fk_productId`, `pk_fk_orderId`, `quantity`)
VALUES
(1, 1, 1),
(1, 2, 1),
(1, 3, 1),
(1, 4, 1),
(1, 5, 1),
(1, 6, 1),
(1, 7, 1),
(1, 8, 1),
(1, 9, 1),
(1, 10, 1),
(1, 11, 1),
(1, 12, 1),
(1, 13, 1),
(1, 14, 1),
(1, 15, 1),
(1, 16, 1),
(1, 17, 1),
(1, 18, 1),
(1, 19, 1),
(1, 20, 1),
(1, 21, 1),
(1, 22, 1),
(1, 23, 1),
(1, 24, 1),
(1, 25, 1),
(1, 26, 1),
(1, 27, 1),
(1, 28, 1),
(1, 29, 1),
(1, 30, 1),
(1, 31, 1),
(1, 32, 1),
(1, 33, 1),
(1, 34, 1),
(1, 35, 1),
(1, 36, 1),
(1, 37, 1),
(1, 38, 1),
(1, 39, 1),
(1, 40, 1),
(1, 41, 1);

-- ************************************************************* CASHFLOW ************************************************************
INSERT INTO `db_e-commerce_system`.`tb_storecashflow`
(`fk_storeId`,
`fk_orderId`,
`value`,
`timestamp`)
VALUES
(1, 1, 4599.9900, '2020-10-01 12:30:00'),
(1, 2, 4599.9900, '2020-10-02 12:30:00'),
(1, 3, 4599.9900, '2020-10-03 12:30:00'),
(1, 4, 4599.9900, '2020-10-04 12:30:00'),
(1, 5, 4599.9900, '2020-10-05 12:30:00'),
(1, 6, 4599.9900, '2020-11-02 13:45:00'),
(1, 7, 4599.9900, '2020-11-06 13:45:00'),
(1, 8, 4599.9900, '2020-11-07 13:45:00'),
(1, 9, 4599.9900, '2020-11-08 13:45:00'),
(1, 10, 4599.9900, '2020-11-09 13:45:00'),
(1, 11, 4599.9900, '2020-12-10 14:10:00'),
(1, 12, 4599.9900, '2020-12-11 14:10:00'),
(1, 13, 4599.9900, '2020-12-12 14:10:00'),
(1, 14, 4599.9900, '2020-12-13 14:10:00'),
(1, 15, 4599.9900, '2020-12-14 14:10:00'),
(1, 16, 4599.9900, '2020-01-15 15:22:00'),
(1, 17, 4599.9900, '2020-01-16 15:22:00'),
(1, 18, 4599.9900, '2020-01-17 15:22:00'),
(1, 19, 4599.9900, '2020-01-18 15:22:00'),
(1, 20, 4599.9900, '2020-01-19 15:22:00'),
(1, 21, 4599.9900, '2020-02-05 16:35:00'),
(1, 22, 4599.9900, '2020-02-20 16:35:00'),
(1, 23, 4599.9900, '2020-02-21 16:35:00'),
(1, 24, 4599.9900, '2020-02-22 16:35:00'),
(1, 25, 4599.9900, '2020-02-23 16:35:00'),
(1, 26, 4599.9900, '2020-03-06 17:48:00'),
(1, 27, 4599.9900, '2020-03-07 17:48:00'),
(1, 28, 4599.9900, '2020-03-08 17:48:00'),
(1, 29, 4599.9900, '2020-03-09 17:48:00'),
(1, 30, 4599.9900, '2020-03-10 17:48:00'),
(1, 31, 4599.9900, '2020-04-07 18:50:00'),
(1, 32, 4599.9900, '2020-04-11 18:50:00'),
(1, 33, 4599.9900, '2020-04-12 18:50:00'),
(1, 34, 4599.9900, '2020-04-13 18:50:00'),
(1, 35, 4599.9900, '2020-04-14 18:50:00'),
(1, 36, 4599.9900, '2020-05-08 19:15:00'),
(1, 37, 4599.9900, '2020-05-15 19:15:00'),
(1, 38, 4599.9900, '2020-05-16 19:15:00'),
(1, 39, 4599.9900, '2020-05-17 19:15:00'),
(1, 40, 4599.9900, '2020-05-18 19:15:00'),
(1, 41, 4599.9900, '2020-05-19 19:15:00');

INSERT INTO `db_e-commerce_system`.`tb_systemcashflow`
(`fk_orderId`,
`value`,
`timestamp`)
VALUES
(1, 4599.9900, '2020-10-01 12:30:00'),
(2, 4599.9900, '2020-10-02 12:30:00'),
(3, 4599.9900, '2020-10-03 12:30:00'),
(4, 4599.9900, '2020-10-04 12:30:00'),
(5, 4599.9900, '2020-10-05 12:30:00'),
(6, 4599.9900, '2020-11-02 13:45:00'),
(7, 4599.9900, '2020-11-06 13:45:00'),
(8, 4599.9900, '2020-11-07 13:45:00'),
(9, 4599.9900, '2020-11-08 13:45:00'),
(10, 4599.9900, '2020-11-09 13:45:00'),
(11, 4599.9900, '2020-12-10 14:10:00'),
(12, 4599.9900, '2020-12-11 14:10:00'),
(13, 4599.9900, '2020-12-12 14:10:00'),
(14, 4599.9900, '2020-12-13 14:10:00'),
(15, 4599.9900, '2020-12-14 14:10:00'),
(16, 4599.9900, '2020-01-15 15:22:00'),
(17, 4599.9900, '2020-01-16 15:22:00'),
(18, 4599.9900, '2020-01-17 15:22:00'),
(19, 4599.9900, '2020-01-18 15:22:00'),
(20, 4599.9900, '2020-01-19 15:22:00'),
(21, 4599.9900, '2020-02-05 16:35:00'),
(22, 4599.9900, '2020-02-20 16:35:00'),
(23, 4599.9900, '2020-02-21 16:35:00'),
(24, 4599.9900, '2020-02-22 16:35:00'),
(25, 4599.9900, '2020-02-23 16:35:00'),
(26, 4599.9900, '2020-03-06 17:48:00'),
(27, 4599.9900, '2020-03-07 17:48:00'),
(28, 4599.9900, '2020-03-08 17:48:00'),
(29, 4599.9900, '2020-03-09 17:48:00'),
(30, 4599.9900, '2020-03-10 17:48:00'),
(31, 4599.9900, '2020-04-07 18:50:00'),
(32, 4599.9900, '2020-04-11 18:50:00'),
(33, 4599.9900, '2020-04-12 18:50:00'),
(34, 4599.9900, '2020-04-13 18:50:00'),
(35, 4599.9900, '2020-04-14 18:50:00'),
(36, 4599.9900, '2020-05-08 19:15:00'),
(37, 4599.9900, '2020-05-15 19:15:00'),
(38, 4599.9900, '2020-05-16 19:15:00'),
(39, 4599.9900, '2020-05-17 19:15:00'),
(40, 4599.9900, '2020-05-18 19:15:00'),
(41, 4599.9900, '2020-05-19 19:15:00');
