CREATE TABLE auction (
    idAuction INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    minBids INT NOT NULL,
    endDate DATETIME NOT NULL,
    publishDate TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    picture MEDIUMBLOB NOT NULL,
    title VARCHAR(255) NOT NULL,
    user_nickname VARCHAR(20),
    description TEXT NOT NULL,
    fileName VARCHAR(255) NOT NULL,
    active BIT(0) NOT NULL DEFAULT 48,
    type VARCHAR(20)
);
CREATE TABLE bid (
    idBid INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    bidDate TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    bidAmount FLOAT(22,0) NOT NULL,
    user_idUser INT UNSIGNED NOT NULL,
    auction_idAuction INT UNSIGNED NOT NULL
);
CREATE INDEX fk_userBid_User ON bid (user_idUser);
CREATE INDEX fk_userBid_auction ON bid (auction_idAuction);
CREATE TABLE facebookinfo (
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
ALTER TABLE facebookinfo ADD CONSTRAINT PK_facebookinfo PRIMARY KEY(user_idUser);
CREATE TABLE grouptable (
    nickname VARCHAR(20) NOT NULL,
    rolename VARCHAR(20) NOT NULL
);
ALTER TABLE grouptable ADD CONSTRAINT PK_grouptable PRIMARY KEY(nickname,rolename);
CREATE TABLE proposeditem (
    idItem INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    description TEXT NOT NULL,
    picture MEDIUMBLOB NOT NULL,
    user_nickname VARCHAR(20) NOT NULL,
    title VARCHAR(255) NOT NULL,
    endDate DATETIME,
    publishDate TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    fileName VARCHAR(255) NOT NULL
);
CREATE TABLE proposeditemvotes (
    idVote INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    item_idItem INT UNSIGNED NOT NULL,
    user_nickname VARCHAR(20) NOT NULL,
    userComment TEXT,
    voteDate TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
CREATE INDEX fk_proposedItem ON proposeditemvotes (item_idItem);
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
    registerDate TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    active BIT(0) NOT NULL DEFAULT 48
);
CREATE UNIQUE INDEX nickname ON user (nickname);
CREATE TABLE userchips (
    user_idUser INT UNSIGNED NOT NULL,
    changeDate TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    chargeAmount INT,
    pastAmount INT,
    newAmount INT,
    cargeMethod TINYINT
);
ALTER TABLE userchips ADD CONSTRAINT PK_userchips PRIMARY KEY(changeDate,user_idUser);
CREATE INDEX fk_userChips_user ON userchips (user_idUser);
CREATE TABLE userrole (
    idRole INT UNSIGNED NOT NULL,
    role VARCHAR(20)
);
ALTER TABLE userrole ADD CONSTRAINT PK_userrole PRIMARY KEY(idRole);
ALTER TABLE bid ADD CONSTRAINT fk_bid_auction FOREIGN KEY () REFERENCES auction() ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE bid ADD CONSTRAINT fk_bid_User FOREIGN KEY () REFERENCES user() ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE facebookinfo ADD CONSTRAINT fk_user_facebookUser FOREIGN KEY () REFERENCES user() ON DELETE NO ACTION ON UPDATE CASCADE;
ALTER TABLE proposeditemvotes ADD CONSTRAINT fk_proposedItem_vote FOREIGN KEY () REFERENCES proposeditem() ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE userchips ADD CONSTRAINT fk_user_userChips FOREIGN KEY () REFERENCES user() ON DELETE NO ACTION ON UPDATE CASCADE;
CREATE TABLE income (
    user_idUser INT UNSIGNED NOT NULL,
    date TIMESTAMP NOT NULL,
    chipsAmmount INT NOT NULL,
    moneyPaid DECIMAL UNSIGNED(5,2) NOT NULL,
    transactionNumber VARCHAR(255) NOT NULL,
    chargeMethod TINYINT
);
CREATE TABLE expenditure (
    user_idUser INT UNSIGNED NOT NULL,
    date TIMESTAMP NOT NULL,
    chipsAmmount INT NOT NULL,
    auction_idAuction INT UNSIGNED NOT NULL
);
ALTER TABLE proposeditem ADD active BOOL NOT NULL DEFAULT 0;
ALTER TABLE user MODIFY active BOOL NOT NULL DEFAULT 0;
ALTER TABLE auction MODIFY active BOOL NOT NULL DEFAULT 0;
ALTER TABLE expenditure ADD idExpenditure BIGINT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT;
ALTER TABLE expenditure MODIFY idExpenditure BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY;
ALTER TABLE income ADD idIncome BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY;
ALTER TABLE expenditure ADD CONSTRAINT fk_expenditure_user FOREIGN KEY (user_idUser) REFERENCES user(idUser) ON DELETE NO ACTION ON UPDATE CASCADE;
ALTER TABLE income ADD CONSTRAINT fk_income_user FOREIGN KEY (user_idUser) REFERENCES user(idUser) ON DELETE NO ACTION ON UPDATE CASCADE;
ALTER TABLE bid MODIFY bidAmount FLOAT(22,1) NOT NULL;
ALTER TABLE bid MODIFY bidAmount FLOAT(10,1) NOT NULL;
ALTER TABLE userchips DROP FOREIGN KEY fk_user_userChips;
DROP TABLE userchips;
ALTER TABLE user ADD chipsBalance INT UNSIGNED NOT NULL DEFAULT 0;
ALTER TABLE auction ADD cogs FLOAT(10,2) NOT NULL;
ALTER TABLE auction ADD margin TINYINT UNSIGNED NOT NULL;
ALTER TABLE auction ADD chipsPerBid3rdQ TINYINT UNSIGNED NOT NULL;
ALTER TABLE auction ADD chipsPerBid1stQ TINYINT UNSIGNED NOT NULL;
ALTER TABLE auction ADD chipsPerBid4thQ TINYINT UNSIGNED NOT NULL;
ALTER TABLE auction ADD chipsPerBid2ndQ TINYINT UNSIGNED NOT NULL;
ALTER TABLE auction ADD chipPrice FLOAT(10,2) NOT NULL;
ALTER TABLE auction ADD hoursAfterClose TINYINT UNSIGNED NOT NULL;
