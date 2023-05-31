INSERT INTO `db_e-commerce_system`.`tb_deliveryservice`
(`name`)
VALUES
('Correios');

INSERT INTO tb_documentType (name) 
VALUES 
	('CPF'),
	('CNPJ');

INSERT INTO `db_e-commerce_system`.`tb_paymentmethod`
(`name`,
`maxInstallment`,
`isEnable`)
VALUES
('Cartão de crédito',
1,
true);

INSERT INTO `db_e-commerce_system`.`tb_orderstatus`
(`name`)
VALUES
('Pedido recebido'),
('Pagamento confirmado'),
('Pedido enviado'),
('Pedido finalizado');

INSERT INTO tb_role (name, description) 
VALUES 
	('system_admin', 'System administrator, has full access.'),
    ('store_admin', 'Store administrator, has access to all store features.'),
    ('customer', 'Customer, can manage his own information and make orders.');

INSERT INTO `db_e-commerce_system`.`tb_telephonetype`
(`name`)
VALUES
("Celular pessoal"),
("Telefone pessoal"),
("Celular comercial"),
("Telefone comercial");

INSERT INTO tb_productType (name)
VALUES
('Eletrônicos'),
('Eletrodomésticos'),
('Livros'),
('Quadrinhos');

INSERT INTO tb_productSubtype (fk_productTypeId, name)
VALUES
	(1, 'Celulares'),
	(1, 'Notebooks'),
	(2, 'Geladeiras'),
	(2, 'Máquinas de Lavar'),
	(3, 'Romance'),
	(3, 'Técnicos'),
	(4, 'Americanos'),
	(4, 'Mangás'),
	(4, 'Nacionais');

-- **************************************************** CELULARES ********************************
INSERT INTO `db_e-commerce_system`.`tb_detaillabel`
(`name`)
VALUES
('Sistema Operacional'),
('Dimensões'),
('Peso'),
('Processador'),
('Chipset'),
('Memória RAM'),
('Tamanho da tela'),
('Tipo da tela'),
('Resolução');

INSERT INTO `db_e-commerce_system`.`tb_detaillabel_productsubtype`
(`pk_fk_productSubtypeId`,
`pk_fk_detailLabelId`)
VALUES
(1, 1),
(1, 2),
(1, 3),
(1, 4),
(1, 5),
(1, 6),
(1, 7),
(1, 8),
(1, 9);

-- ************************ NOTEBOOKS *************************************************
INSERT INTO `db_e-commerce_system`.`tb_detaillabel`
(`name`)
VALUES
('Armazenamento');

INSERT INTO `db_e-commerce_system`.`tb_detaillabel_productsubtype`
(`pk_fk_productSubtypeId`,
`pk_fk_detailLabelId`)
VALUES
(2, 1),
(2, 4),
(2, 6),
(2, 7),
(2, 8),
(2, 9),
(2, 10);