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
