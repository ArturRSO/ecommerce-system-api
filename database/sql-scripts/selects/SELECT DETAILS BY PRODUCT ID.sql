SELECT dl.name AS 'Rótulo', pd.value AS 'Valor'
FROM
tb_productDetail pd
INNER JOIN tb_detailLabel dl ON pd.pk_fk_detailLabelId = dl.pk_detailLabelId
WHERE pd.pk_fk_productId = 1;