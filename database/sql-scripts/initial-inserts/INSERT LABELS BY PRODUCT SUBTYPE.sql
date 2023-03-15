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