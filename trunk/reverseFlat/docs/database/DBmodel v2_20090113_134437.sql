ALTER TABLE auction ADD fileName VARCHAR(255) NOT NULL;
ALTER TABLE proposeditem CHANGE user_idUser user_nickname INT UNSIGNED NOT NULL;
ALTER TABLE proposedItemVotes CHANGE user_idUser user_nickname INT UNSIGNED NOT NULL;
