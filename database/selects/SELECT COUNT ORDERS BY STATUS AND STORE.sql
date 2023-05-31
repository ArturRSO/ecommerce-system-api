-- VERSION 1
SELECT orders.storeId, orders.orders, finishedOrders.orders AS finishedOrders FROM
	(SELECT s.pk_storeId AS storeId, COUNT(o.pk_orderId) AS orders FROM tb_order o
		INNER JOIN tb_store s ON s.pk_storeId = o.fk_storeId
		GROUP BY s.pk_storeId) AS orders,
    (SELECT s.pk_storeId AS storeId, COUNT(o.pk_orderId) AS orders FROM tb_order o
		INNER JOIN tb_store s ON s.pk_storeId = o.fk_storeId
		INNER JOIN tb_orderStatus os ON os.pk_orderStatusId = o.fk_orderStatusId WHERE os.name = 'Pedido finalizado' 
		GROUP BY s.pk_storeId) AS finishedOrders
	WHERE orders.storeId = finishedOrders.storeId
    ORDER BY orders.storeId ASC;
    
-- VERSION 2 (FINAL)    
SELECT s.pk_storeId AS storeId, 
COUNT(o.pk_orderId) AS orders,
COUNT(IF(o.fk_orderStatusId = 1, 1, null)) AS receivedOrders,
COUNT(IF(o.fk_orderStatusId = 2, 1, null)) AS paidOrders,
COUNT(IF(o.fk_orderStatusId = 3, 1, null)) AS sentOrders,
COUNT(IF(o.fk_orderStatusId = 4, 1, null)) AS finishedOrders
	FROM tb_order o
	INNER JOIN tb_store s ON s.pk_storeId = o.fk_storeId
	GROUP BY s.pk_storeId
    ORDER BY s.pk_storeId ASC;