TRUNCATE TABLE ptcore.in_user_facebook;
ALTER TABLE ptcore.in_user_facebook ADD COLUMN user_name VARCHAR(100) NOT NULL;
ALTER TABLE ptcore.in_user_facebook ADD COLUMN user_id VARCHAR(100) NOT NULL;
ALTER TABLE ptcore.in_user_facebook ADD COLUMN picture_url VARCHAR(200) NULL;

CREATE INDEX facebook_user_id_index ON ptcore.in_user_facebook (user_id);
