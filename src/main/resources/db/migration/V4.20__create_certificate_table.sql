CREATE TABLE ptcore.certificate (
    id BIGSERIAL PRIMARY KEY NOT NULL,
    created TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT now(),
    code VARCHAR(10) NOT NULL,
    amount_of_days INT NOT NULL,
    activated BOOLEAN NOT NULL
);
