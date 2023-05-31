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

