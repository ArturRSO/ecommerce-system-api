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
