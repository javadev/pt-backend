ALTER TABLE ptcore.in_user_email ADD COLUMN confirmed TIMESTAMP WITHOUT TIME ZONE;
ALTER TABLE ptcore.in_user_email ADD COLUMN is_confirmed BOOLEAN NOT NULL DEFAULT FALSE;
ALTER TABLE ptcore.in_user_email ADD COLUMN confirm_token VARCHAR(35) NOT NULL DEFAULT '';
