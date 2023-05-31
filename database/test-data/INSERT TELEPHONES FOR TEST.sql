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