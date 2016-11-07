ALTER TABLE ptcore.in_user ADD COLUMN birthday TIMESTAMP WITHOUT TIME ZONE;
ALTER TABLE ptcore.in_user_facebook ADD COLUMN birthday TIMESTAMP WITHOUT TIME ZONE;

CREATE INDEX facebook_token_device_id_index ON ptcore.in_user_facebook (token, device_id);
