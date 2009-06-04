CREATE TABLE auction (
    idAuction DOUBLE(22,0) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    minBids INT NOT NULL,
    bidEndDate DATETIME NOT NULL,
    item_idItem INT
);
CREATE INDEX fk_auction_item ON auction (item_idItem);
CREATE TABLE bid (
    idBid INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    bidDate DATETIME,
    bidAmount DOUBLE(22,0) NOT NULL,
    User_idUser INT,
    auction_idAuction DOUBLE(22,0)
);
CREATE INDEX fk_userBid_User ON bid (User_idUser);
CREATE INDEX fk_userBid_auction ON bid (auction_idAuction);
CREATE TABLE bidder (
    idUser INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nickname VARCHAR(20) NOT NULL,
    password VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL,
    name VARCHAR(50),
    lastName VARCHAR(50),
    gender TINYINT,
    address VARCHAR(150),
    idCity INT,
    idCountry INT,
    postalCode VARCHAR(20),
    birthday DATE,
    landPhone VARCHAR(20),
    cellPhone VARCHAR(20),
    userRole_idRole INT
);
CREATE UNIQUE INDEX nickname ON bidder (nickname);
CREATE INDEX fk_User_userRole ON bidder (userRole_idRole);
CREATE TABLE bidderchips (
    User_idUser INT NOT NULL,
    changeDate DATETIME NOT NULL,
    chargeAmount INT,
    pastAmount INT,
    newAmount INT,
    cargeMethod TINYINT,
    bidder_idUser INT
);
ALTER TABLE bidderchips ADD CONSTRAINT PK_bidderchips PRIMARY KEY(User_idUser,changeDate);
CREATE INDEX fk_bidderChips_bidder ON bidderchips (bidder_idUser);
CREATE TABLE grouptable (
    nickname VARCHAR(20),
    GROUPID VARCHAR(20)
);
CREATE TABLE item (
    idItem INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    image VARCHAR(100),
    description TEXT,
    minBids SMALLINT
);
CREATE TABLE proposeditem (
    idItem INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    itemDescription TEXT NOT NULL,
    itemPictureFile VARCHAR(120) NOT NULL,
    idUser INT UNSIGNED NOT NULL,
    itemTitle VARCHAR(255) NOT NULL
);
CREATE TABLE userrole (
    idRole INT NOT NULL,
    role VARCHAR(20)
);
ALTER TABLE userrole ADD CONSTRAINT PK_userrole PRIMARY KEY(idRole);
ALTER TABLE auction ADD CONSTRAINT fk_auction_item FOREIGN KEY () REFERENCES item() ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE bid ADD CONSTRAINT fk_userBid_auction FOREIGN KEY () REFERENCES auction() ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE bid ADD CONSTRAINT fk_userBid_User FOREIGN KEY () REFERENCES bidder() ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE bidder ADD CONSTRAINT fk_User_userRole FOREIGN KEY () REFERENCES userrole() ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE bidderchips ADD CONSTRAINT fk_bidderChips_bidder FOREIGN KEY () REFERENCES bidder() ON DELETE NO ACTION ON UPDATE NO ACTION;
