SELECT pt.pk_productTypeId, pt.name AS 'Tipo', ps.pk_productSubtypeId, ps.name AS 'Subtipo'
FROM
tb_producttype pt
INNER JOIN tb_productsubtype ps ON pt.pk_productTypeId = ps.fk_productTypeId
WHERE pt.name = 'Eletrônicos';