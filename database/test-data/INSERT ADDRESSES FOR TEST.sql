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