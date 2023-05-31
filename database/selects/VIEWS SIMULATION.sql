-- SYSTEM CASH FLOW ORDERS (SELECT BY DATE RANGE)
SELECT * FROM (
	SELECT o.pk_orderId, s.name, scf.value, scf.timestamp
		FROM tb_order o
		INNER JOIN tb_systemCashFlow scf ON scf.fk_orderId = o.pk_orderId
		INNER JOIN tb_store s ON s.pk_storeId = o.fk_storeId
		ORDER BY scf.timestamp ASC
	) view
WHERE DATE(view.timestamp) BETWEEN '2021-01-01' AND '2021-01-31';


-- SYSTEM CASH FLOW REVENUE (SELECT BY YEAR)
SELECT MONTHNAME(view.timestamp) as month, view.revenue FROM (
	SELECT SUM(scf.value) as revenue, scf.timestamp
		FROM tb_systemCashFlow scf
		GROUP BY scf.timestamp
	) view
WHERE YEAR(timestamp) BETWEEN '2021' AND '2021';


-- STORE CASH FLOW ORDERS (SELECT BY DATE RANGE)
SELECT * FROM (
	SELECT o.pk_orderId, scf.value, p.name, po.quantity, scf.timestamp
		FROM tb_order o
		INNER JOIN tb_product_order po ON po.pk_fk_orderId = o.pk_orderId
		INNER JOIN tb_product p ON p.pk_productId = po.pk_fk_productId
		INNER JOIN tb_storeCashFlow scf ON scf.fk_orderId = o.pk_orderId
		WHERE scf.fk_storeId = 1
		ORDER BY scf.timestamp ASC
	) view
WHERE DATE(view.timestamp) BETWEEN '2021-01-01' AND '2021-01-31';


-- STORE CASH FLOW REVENUE (SELECT BY YEAR)
SELECT MONTHNAME(view.timestamp) as month, view.revenue FROM (
	SELECT SUM(scf.value) as revenue, scf.timestamp
		FROM tb_storeCashFlow scf
		WHERE scf.fk_storeId = 1
		GROUP BY scf.timestamp
	) view
WHERE YEAR(timestamp) BETWEEN '2021' AND '2021';