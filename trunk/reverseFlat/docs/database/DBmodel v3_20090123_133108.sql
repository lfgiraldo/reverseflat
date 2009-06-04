ALTER TABLE freechipsexpenditure CHANGE chipsAmmount chipsAmount INT UNSIGNED NOT NULL;
ALTER TABLE chipsincome CHANGE chipsAmmount chipsAmount INT UNSIGNED NOT NULL;
ALTER TABLE freechipsincome CHANGE chipsAmmount chipsAmount INT UNSIGNED NOT NULL;
ALTER TABLE chipsexpenditure CHANGE chipsAmmount chipsAmount INT UNSIGNED NOT NULL;
ALTER TABLE user ADD freeChipsBalance INT UNSIGNED NOT NULL DEFAULT 0;
