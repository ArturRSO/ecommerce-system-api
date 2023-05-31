-- VERSION 1
SELECT storesByUser.userId, storesByUser.stores, activeStoresByUser.stores AS activeStores FROM
	(SELECT su.pk_fk_userId AS userId, COUNT(s.pk_storeId) AS stores FROM tb_store s
		INNER JOIN tb_store_user su ON su.pk_fk_storeId = s.pk_storeId
		GROUP BY su.pk_fk_userId) AS storesByUser,
	(SELECT su.pk_fk_userId AS userId, COUNT(s.pk_storeId) AS stores FROM tb_store s
		INNER JOIN tb_store_user su ON su.pk_fk_storeId = s.pk_storeId
		INNER JOIN 
			(SELECT s.pk_storeId, COUNT(*) AS activeProducts FROM tb_product p 
				INNER JOIN tb_store s ON s.pk_storeId = p.fk_storeId
				INNER JOIN tb_store_user su ON su.pk_fk_storeId = s.pk_storeId
				WHERE p.quantity > 0 AND p.isActive = true
				GROUP BY s.pk_storeId) activeProductsByStore ON activeProductsByStore.pk_storeId = s.pk_storeId AND activeProductsByStore.activeProducts > 0
		ORDER BY su.pk_fk_userId ASC) AS activeStoresByUser
    WHERE storesByUser.userId = activeStoresByUser.userId
    ORDER BY storesByUser.userId ASC;
    
    -- VERSION 2 (FINAL)
SELECT su.pk_fk_userId AS userId, COUNT(IF(s.isActive = true, 1, null)) AS stores, COUNT(IF(pss.activeProducts > 0, 1, null)) AS activeStores FROM tb_store s
	INNER JOIN tb_store_user su ON su.pk_fk_storeId = s.pk_storeId
	INNER JOIN vw_productsByStoreAndStatus pss ON pss.storeId = s.pk_storeId
	GROUP BY su.pk_fk_userId
	ORDER BY su.pk_fk_userId ASC;