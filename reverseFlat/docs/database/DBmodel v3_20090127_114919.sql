ALTER TABLE auction ADD longDescription TEXT NOT NULL;
ALTER TABLE auction CHANGE description shortDescription TEXT NOT NULL;
