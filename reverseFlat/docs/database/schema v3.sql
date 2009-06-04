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
    active BOOL NOT NULL DEFAULT 0,
    type VARCHAR(20),
    cogs FLOAT(10,2) NOT NULL,
    margin TINYINT UNSIGNED NOT NULL,
    chipsPerBid3rdQ TINYINT UNSIGNED NOT NULL,
    chipsPerBid1stQ TINYINT UNSIGNED NOT NULL,
    chipsPerBid4thQ TINYINT UNSIGNED NOT NULL,
    chipsPerBid2ndQ TINYINT UNSIGNED NOT NULL,
    chipPrice FLOAT(10,2) NOT NULL,
    hoursAfterClose TINYINT UNSIGNED NOT NULL
);
CREATE TABLE bid (
    idBid INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    bidDate TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    bidAmount NUMERIC(6,1) NOT NULL,
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
    fileName VARCHAR(255) NOT NULL,
    active BOOL NOT NULL DEFAULT 0
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
    active BOOL NOT NULL DEFAULT 0,
    chipsBalance INT UNSIGNED NOT NULL DEFAULT 0
);
CREATE UNIQUE INDEX nickname ON user (nickname);
CREATE TABLE userrole (
    idRole INT UNSIGNED NOT NULL,
    role VARCHAR(20)
);
ALTER TABLE userrole ADD CONSTRAINT PK_userrole PRIMARY KEY(idRole);
CREATE TABLE chipsIncome (
    user_idUser INT UNSIGNED NOT NULL,
    date TIMESTAMP NOT NULL,
    chipsAmmount INT UNSIGNED NOT NULL,
    moneyPaid DECIMAL(5,2) NOT NULL,
    transactionNumber VARCHAR(255) NOT NULL,
    chargeMethod TINYINT,
    idIncome BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY
);
CREATE TABLE chipsExpenditure (
    user_idUser INT UNSIGNED NOT NULL,
    date TIMESTAMP NOT NULL,
    chipsAmmount INT UNSIGNED NOT NULL,
    auction_idAuction INT UNSIGNED NOT NULL,
    idExpenditure BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY
);
CREATE TABLE freeChipsIncome (
    idIncome BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_idUser INT UNSIGNED NOT NULL,
    date TIMESTAMP NOT NULL,
    chipsAmmount INT UNSIGNED NOT NULL,
    user_idUser_referral INT UNSIGNED
);
CREATE TABLE freeChipsExpenditure (
    idExpenditure INT UNSIGNED NOT NULL,
    user_idUser INT UNSIGNED NOT NULL,
    date TIMESTAMP NOT NULL,
    chipsAmmount INT UNSIGNED NOT NULL,
    auction_idAuction INT UNSIGNED NOT NULL
);
ALTER TABLE freeChipsExpenditure ADD CONSTRAINT PK_freeChipsExpenditure PRIMARY KEY(idExpenditure);
ALTER TABLE bid ADD CONSTRAINT fk_bid_auction FOREIGN KEY (auction_idAuction) REFERENCES auction(idAuction) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE bid ADD CONSTRAINT fk_bid_User FOREIGN KEY (user_idUser) REFERENCES user(idUser) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE facebookinfo ADD CONSTRAINT fk_user_facebookUser FOREIGN KEY (user_idUser) REFERENCES user(idUser) ON DELETE NO ACTION ON UPDATE CASCADE;
ALTER TABLE proposeditemvotes ADD CONSTRAINT fk_proposedItem_vote FOREIGN KEY (item_idItem) REFERENCES proposeditem(idItem) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE chipsExpenditure ADD CONSTRAINT fk_expenditure_user FOREIGN KEY (user_idUser) REFERENCES user(idUser) ON DELETE NO ACTION ON UPDATE CASCADE;
ALTER TABLE chipsIncome ADD CONSTRAINT fk_income_user FOREIGN KEY (user_idUser) REFERENCES user(idUser) ON DELETE NO ACTION ON UPDATE CASCADE;
ALTER TABLE freeChipsIncome ADD CONSTRAINT fk_freeChipsIncome_user FOREIGN KEY (user_idUser) REFERENCES user(idUser) ON DELETE NO ACTION ON UPDATE CASCADE;
ALTER TABLE freeChipsIncome ADD CONSTRAINT fk_freeChipsIncome_userReferral FOREIGN KEY (user_idUser_referral) REFERENCES user(idUser) ON DELETE NO ACTION ON UPDATE CASCADE;
ALTER TABLE freeChipsExpenditure ADD CONSTRAINT fk_freeChipsExpenditure_user FOREIGN KEY (user_idUser) REFERENCES user(idUser) ON DELETE NO ACTION ON UPDATE CASCADE;
ALTER TABLE freeChipsExpenditure ADD CONSTRAINT fk_freeChipsExpenditure_auction FOREIGN KEY (auction_idAuction) REFERENCES auction(idAuction) ON DELETE NO ACTION ON UPDATE CASCADE;
