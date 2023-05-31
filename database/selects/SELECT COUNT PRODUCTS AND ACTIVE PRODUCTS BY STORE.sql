-- VERSION 1
SELECT products.storeId, products.products, activeProducts.products AS activeProducts FROM
	(SELECT s.pk_storeId AS storeId, COUNT(p.pk_productId) AS products FROM tb_product p
		INNER JOIN tb_store s ON s.pk_storeId = p.fk_storeId
		WHERE p.isActive = true
		GROUP BY s.pk_storeId) AS products,    
	(SELECT s.pk_storeId AS storeId, COUNT(p.pk_productId) AS products FROM tb_product p
		INNER JOIN tb_store s ON s.pk_storeId = p.fk_storeId
		WHERE p.quantity > 0 AND p.isActive = true
		GROUP BY s.pk_storeId) AS activeProducts
	WHERE products.storeId = activeProducts.storeId
    ORDER BY products.storeId ASC;
    
-- VERSION 2 (FINAL)
SELECT s.pk_storeId AS storeId, COUNT(IF(p.isActive = true, 1, null)) AS products, COUNT(IF(p.quantity > 0 AND p.isActive = true, 1, null)) AS activeProducts FROM tb_product p
		INNER JOIN tb_store s ON s.pk_storeId = p.fk_storeId
		WHERE s.isActive = true
		GROUP BY s.pk_storeId
        ORDER BY s.pk_storeId ASC;