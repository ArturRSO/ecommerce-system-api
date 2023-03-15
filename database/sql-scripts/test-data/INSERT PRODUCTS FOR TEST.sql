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
