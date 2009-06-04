CREATE TABLE auction (
    idAuction INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    auctionMinBids INT NOT NULL,
    auctionBidEndDate DATETIME NOT NULL,
    item_idItem INT UNSIGNED,
    auctionPublishDate TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
CREATE INDEX fk_auction_item ON auction (item_idItem);
CREATE TABLE bid (
    idBid INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    bidDate TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    bidAmount FLOAT(22,0) NOT NULL,
    user_idUser INT UNSIGNED NOT NULL,
    auction_idAuction INT UNSIGNED NOT NULL
);
CREATE INDEX fk_userBid_User ON bid (user_idUser);
CREATE INDEX fk_userBid_auction ON bid (auction_idAuction);
CREATE TABLE facebookInfo (
    user_idUser INT UNSIGNED NOT NULL,
    facebookId VARCHAR(255),
    current_location VARCHAR(255),
    first_name VARCHAR(255),
    education_history TEXT,
    has_added_app BIT(0),
    hometown_location VARCHAR(255),
    affiliations TEXT,
    books TEXT,
    birthday DATE,
    activities TEXT,
    about_me TEXT,
    pic_small VARCHAR(255),
    tv TEXT,
    work_history TEXT,
    wall_count VARCHAR(255),
    meeting_for VARCHAR(255),
    quotes TEXT,
    pic_big VARCHAR(255),
    notes_count TEXT,
    music TEXT,
    last_name VARCHAR(255),
    relationships_status VARCHAR(255),
    pic VARCHAR(255),
    status VARCHAR(255),
    profile_update_time DATETIME,
    interests TEXT,
    timezone VARCHAR(255),
    pic_square VARCHAR(255),
    political TEXT,
    is_app_user BIT(0),
    movies TEXT,
    name VARCHAR(255),
    meeting_sex VARCHAR(255),
    religion VARCHAR(255),
    sex VARCHAR(255),
    significant_other_id TEXT,
    hs_info TEXT
);
ALTER TABLE facebookInfo ADD CONSTRAINT PK_facebookInfo PRIMARY KEY(user_idUser);
CREATE TABLE grouptable (
    user_nickname VARCHAR(20) NOT NULL,
    rolename VARCHAR(20) NOT NULL
);
ALTER TABLE grouptable ADD CONSTRAINT PK_grouptable PRIMARY KEY(rolename,user_nickname);
CREATE TABLE item (
    idItem INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    picture VARCHAR(100),
    description TEXT,
    user_idUser INT UNSIGNED,
    title VARCHAR(255) NOT NULL
);
CREATE TABLE proposedItemVotes (
    idVote INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    item_idItem INT UNSIGNED NOT NULL,
    user_idUser INT UNSIGNED NOT NULL,
    userComment TEXT,
    voteDate TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
CREATE INDEX fk_proposedItem ON proposedItemVotes (item_idItem);
CREATE TABLE proposeditem (
    idItem INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    description TEXT NOT NULL,
    pictureFile VARCHAR(120) NOT NULL,
    user_idUser INT UNSIGNED NOT NULL,
    title VARCHAR(255) NOT NULL,
    endDate DATETIME,
    publishDate TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
CREATE TABLE user (
    idUser INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nickname VARCHAR(20) NOT NULL,
    password VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL,
    name VARCHAR(50),
    lastName VARCHAR(50),
    gender TINYINT,
    address VARCHAR(150),
    idCity INT UNSIGNED,
    idCountry INT UNSIGNED,
    postalCode VARCHAR(20),
    birthday DATE,
    landPhone VARCHAR(20),
    cellPhone VARCHAR(20),
    registerDate TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
CREATE UNIQUE INDEX nickname ON user (nickname);
CREATE TABLE userChips (
    user_idUser INT UNSIGNED NOT NULL,
    changeDate TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    chargeAmount INT,
    pastAmount INT,
    newAmount INT,
    cargeMethod TINYINT
);
ALTER TABLE userChips ADD CONSTRAINT PK_userChips PRIMARY KEY(changeDate,user_idUser);
CREATE INDEX fk_userChips_user ON userChips (user_idUser);
CREATE TABLE userrole (
    idRole INT UNSIGNED NOT NULL,
    role VARCHAR(20)
);
ALTER TABLE userrole ADD CONSTRAINT PK_userrole PRIMARY KEY(idRole);
ALTER TABLE auction ADD CONSTRAINT fk_auction_proposedItem FOREIGN KEY (item_idItem) REFERENCES proposeditem(idItem) ON DELETE NO ACTION ON UPDATE CASCADE;
ALTER TABLE auction ADD CONSTRAINT fk_auction_item FOREIGN KEY (item_idItem) REFERENCES item(idItem) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE bid ADD CONSTRAINT fk_bid_User FOREIGN KEY (user_idUser) REFERENCES user(idUser) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE bid ADD CONSTRAINT fk_bid_auction FOREIGN KEY (auction_idAuction) REFERENCES auction(idAuction) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE facebookInfo ADD CONSTRAINT fk_user_facebookUser FOREIGN KEY (user_idUser) REFERENCES user(idUser) ON DELETE NO ACTION ON UPDATE CASCADE;
ALTER TABLE proposedItemVotes ADD CONSTRAINT fk_proposedItem_vote FOREIGN KEY (item_idItem) REFERENCES proposeditem(idItem) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE userChips ADD CONSTRAINT fk_user_userChips FOREIGN KEY (user_idUser) REFERENCES user(idUser) ON DELETE NO ACTION ON UPDATE CASCADE;
