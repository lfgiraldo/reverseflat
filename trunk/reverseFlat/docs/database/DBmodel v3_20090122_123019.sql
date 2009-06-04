ALTER TABLE income RENAME TO incomeChips;
ALTER TABLE incomeChips RENAME TO chipsIncome;
ALTER TABLE expenditure RENAME TO chipsExpenditure;
CREATE TABLE freeChipsIncome (
    idIncome BIGINT UNSIGNED NOT NULL,
    user_idUser INT UNSIGNED NOT NULL,
    date TIMESTAMP NOT NULL,
    chipsAmmount INT UNSIGNED NOT NULL
);
ALTER TABLE freeChipsIncome ADD user_idUser_referral INT UNSIGNED;
ALTER TABLE freeChipsIncome ADD CONSTRAINT PK_freeChipsIncome PRIMARY KEY(idIncome);
ALTER TABLE freeChipsIncome MODIFY idIncome BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY;
ALTER TABLE freeChipsIncome ADD CONSTRAINT fk_freeChipsIncome_user FOREIGN KEY (user_idUser) REFERENCES user(idUser) ON DELETE NO ACTION ON UPDATE CASCADE;
ALTER TABLE freeChipsIncome ADD CONSTRAINT fk_freeChipsIncome_userReferral FOREIGN KEY (user_idUser_referral) REFERENCES user(idUser) ON DELETE NO ACTION ON UPDATE CASCADE;
CREATE TABLE freeChipsExpenditure (
    idExpenditure INT UNSIGNED NOT NULL,
    user_idUser INT UNSIGNED NOT NULL,
    date TIMESTAMP NOT NULL,
    chipsAmmount INT UNSIGNED NOT NULL,
    auction_idAuction INT UNSIGNED NOT NULL
);
ALTER TABLE chipsExpenditure MODIFY chipsAmmount INT UNSIGNED NOT NULL;
ALTER TABLE freeChipsExpenditure ADD CONSTRAINT PK_freeChipsExpenditure PRIMARY KEY(idExpenditure);
ALTER TABLE freeChipsExpenditure ADD CONSTRAINT fk_freeChipsExpenditure_user FOREIGN KEY (user_idUser) REFERENCES user(idUser) ON DELETE NO ACTION ON UPDATE CASCADE;
ALTER TABLE freeChipsExpenditure ADD CONSTRAINT fk_freeChipsExpenditure_auction FOREIGN KEY (auction_idAuction) REFERENCES auction(idAuction) ON DELETE NO ACTION ON UPDATE CASCADE;
