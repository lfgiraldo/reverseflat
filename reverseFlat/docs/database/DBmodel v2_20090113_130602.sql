ALTER TABLE auction ADD picture BLOB NOT NULL;
ALTER TABLE auction ADD title VARCHAR(255) NOT NULL;
ALTER TABLE auction ADD user_idUser INT UNSIGNED;
ALTER TABLE auction ADD description TEXT NOT NULL;
ALTER TABLE auction DROP FOREIGN KEY fk_auction_item;
DROP TABLE item;
ALTER TABLE auction DROP COLUMN item_idItem;
ALTER TABLE auction CHANGE user_idUser user_nickname INT UNSIGNED ;
ALTER TABLE auction MODIFY user_nickname VARCHAR(20);
