CREATE TABLE ptcore.in_user_email (
    id BIGSERIAL PRIMARY KEY NOT NULL,
    in_user_id BIGINT NOT NULL,
    created TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT now(),
    login VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    user_name VARCHAR(100) NOT NULL,
    is_deleted BOOLEAN NOT NULL DEFAULT FALSE,
    deleted TIMESTAMP WITHOUT TIME ZONE,
    deleted_comment VARCHAR(200),
    FOREIGN KEY (in_user_id) REFERENCES ptcore.in_user (id) ON UPDATE CASCADE ON DELETE CASCADE
);
