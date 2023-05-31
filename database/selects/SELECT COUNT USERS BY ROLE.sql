-- VERSION 1
SELECT users.users, storeAdmins.users AS storeAdmins FROM
(SELECT COUNT(u.pk_userId) AS users FROM tb_user u) AS users,
(SELECT COUNT(u.pk_userId) AS users FROM tb_user u
	INNER JOIN tb_role r ON r.pk_roleId = u.fk_roleId
    WHERE r.name = 'store_admin') AS storeAdmins;
    
-- VERSION 2 (FINAL)
SELECT 
COUNT(IF(u.isActive = true, 1, null)) AS users,
COUNT(IF(u.isActive = true AND u.fk_roleId = 1, 1, null)) AS admins,
COUNT(IF(u.isActive = true AND u.fk_roleId = 2, 1, null)) AS storeAdmins,
COUNT(IF(u.isActive = true AND u.fk_roleId = 3, 1, null)) AS customers
FROM tb_user u;