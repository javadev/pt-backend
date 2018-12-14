SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET search_path = ptcore;
SET default_tablespace = '';
SET default_with_oids = false;

CREATE SCHEMA IF NOT EXISTS ptcore;
CREATE TABLE ptcore.in_user (
    id BIGSERIAL PRIMARY KEY NOT NULL,
    created TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT now(),
    d_sex VARCHAR(45),
    age DECIMAL(9, 2),
    height DECIMAL(9, 2),
    weight DECIMAL(9, 2),
    d_level VARCHAR(45),
    updated TIMESTAMP WITHOUT TIME ZONE
);

CREATE TABLE ptcore.in_user_facebook (
    id BIGSERIAL PRIMARY KEY NOT NULL,
    in_user_id BIGINT NOT NULL,
    created TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT now(),
    token VARCHAR(300),
    FOREIGN KEY (in_user_id) REFERENCES ptcore.in_user (id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE ptcore.in_user_login (
    id BIGSERIAL PRIMARY KEY NOT NULL,
    in_user_id BIGINT NOT NULL,
    created TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT now(),
    token VARCHAR(35),
    ip_address VARCHAR(40),
    FOREIGN KEY (in_user_id) REFERENCES ptcore.in_user (id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE ptcore.in_user_logout (
    id BIGSERIAL PRIMARY KEY NOT NULL,
    in_user_id BIGINT NOT NULL,
    created TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT now(),
    token VARCHAR(35),
    FOREIGN KEY (in_user_id) REFERENCES ptcore.in_user (id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE INDEX facebook_token_index ON ptcore.in_user_facebook (token);
CREATE INDEX login_token_index ON ptcore.in_user_login (token);
CREATE INDEX logout_token_index ON ptcore.in_user_logout (token);
