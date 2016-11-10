CREATE TABLE ptcore.in_user_weight (
    id BIGSERIAL PRIMARY KEY NOT NULL,
    in_user_id BIGINT NOT NULL,
    created TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT now(),
    weight DECIMAL(9, 2),
    FOREIGN KEY (in_user_id) REFERENCES ptcore.in_user (id) ON UPDATE CASCADE ON DELETE CASCADE
);
