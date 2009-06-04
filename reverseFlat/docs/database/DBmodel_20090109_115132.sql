ALTER TABLE proposeditem ADD itemEndDate DATETIME;
ALTER TABLE proposeditem ADD itemPublishDate DATETIME DEFAULT now();
CREATE TABLE item (
    itemDescription TEXT NOT NULL,
    itemPicture BLOB,
    itemProposerUser INT UNSIGNED,
    itemTitle VARCHAR(255) NOT NULL,
    itemPublishDate DATETIME NOT NULL DEFAULT now(),
    itemEndDate DATETIME NOT NULL,
    idItem INT UNSIGNED NOT NULL
);
ALTER TABLE item ADD CONSTRAINT idItem PRIMARY KEY(idItem);
ALTER TABLE item CHANGE image itemPicture VARCHAR(100) ;
ALTER TABLE item CHANGE minBids itemProposerUser SMALLINT ;
ALTER TABLE item ADD itemMinBids INT NOT NULL;
ALTER TABLE item ADD itemPublishDate DATETIME NOT NULL DEFAULT now();
ALTER TABLE item CHANGE description itemDescription TEXT ;
ALTER TABLE item ADD itemTitle VARCHAR(255) NOT NULL;
ALTER TABLE item ADD itemEndDate DATETIME NOT NULL;
ALTER TABLE item DROP COLUMN itemMinBids;
ALTER TABLE item DROP COLUMN itemMinBids;
ALTER TABLE item DROP COLUMN itemEndDate;
ALTER TABLE item DROP COLUMN itemPublishDate;
ALTER TABLE auction ADD auctionPublishDate DATETIME NOT NULL DEFAULT now();
ALTER TABLE auction CHANGE bidEndDate auctionBidEndDate DATETIME NOT NULL;
ALTER TABLE auction CHANGE minBids auctionMinBids INT NOT NULL;
ALTER TABLE bidder RENAME TO user;
ALTER TABLE bid MODIFY bidDate DATETIME NOT NULL DEFAULT now();
ALTER TABLE auction MODIFY idAuction INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY;
ALTER TABLE bid MODIFY auction_idAuction INT UNSIGNED NOT NULL;
ALTER TABLE bid MODIFY bidAmount FLOAT(22,0) NOT NULL;
CREATE TABLE proposedItemVotes (
    idVote INT UNSIGNED NOT NULL,
    idProposedItem INT UNSIGNED NOT NULL
);
ALTER TABLE proposedItemVotes ADD idUser INT UNSIGNED NOT NULL;
ALTER TABLE proposedItemVotes ADD userComment TEXT;
ALTER TABLE proposedItemVotes ADD CONSTRAINT PK_proposedItemVotes PRIMARY KEY(idVote,idProposedItem);
ALTER TABLE proposeditem CHANGE idItem idProposedItem INT UNSIGNED NOT NULL;
ALTER TABLE auction DROP FOREIGN KEY fk_auction_item;
ALTER TABLE auction ADD CONSTRAINT fk_auction_item FOREIGN KEY (item_idItem) REFERENCES item(idItem) ON DELETE NO ACTION ON UPDATE CASCADE;
CREATE INDEX fk_proposedItem ON proposedItemVotes (idProposedItem);
