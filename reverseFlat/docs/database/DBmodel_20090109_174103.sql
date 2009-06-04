ALTER TABLE user DROP FOREIGN KEY fk_User_userRole;
DROP TABLE userrole;
ALTER TABLE grouptable CHANGE GROUPID rolename VARCHAR(20) ;
ALTER TABLE user DROP COLUMN userRole_idRole;
