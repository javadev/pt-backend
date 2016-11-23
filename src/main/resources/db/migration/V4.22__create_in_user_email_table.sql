CREATE TABLE ptcore.in_user_email (
    id BIGSERIAL PRIMARY KEY NOT NULL,
    in_user_id BIGINT NOT NULL,
    created TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT now(),
    login VARCHAR(50) NOT NULL,
    password VARCHAR(70) NOT NULL,
    user_name VARCHAR(100) NOT NULL,
    FOREIGN KEY (in_user_id) REFERENCES ptcore.in_user (id) ON UPDATE CASCADE ON DELETE CASCADE
);
