CREATE TABLE ptcore.pt_user (
    id BIGSERIAL PRIMARY KEY NOT NULL,
    created TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT now(),
    login VARCHAR(50),
    password VARCHAR(70),
    name VARCHAR(100),
    activated TIMESTAMP WITHOUT TIME ZONE,
    is_blocked BOOLEAN NOT NULL DEFAULT FALSE,
    is_blocked_date_set BOOLEAN NOT NULL DEFAULT FALSE,
    blocked_comment VARCHAR(500),
    blocked_start TIMESTAMP WITHOUT TIME ZONE,
    blocked_finish TIMESTAMP WITHOUT TIME ZONE,
    deleted TIMESTAMP WITHOUT TIME ZONE,
    deleted_comment VARCHAR(500),
    is_deleted BOOLEAN NOT NULL DEFAULT FALSE,
    is_default_password BOOLEAN NOT NULL DEFAULT TRUE,
    description VARCHAR(500),
    phone VARCHAR(15),
    phone2 VARCHAR(15)
);

INSERT INTO ptcore.pt_user (login, password) VALUES
('admin', '$2a$10$x/8Ts1lvLC.j8xofd9Zu3uuEQf2sXj8wpWLaUZtOAOFXrjhcCJaLC');

CREATE TABLE ptcore.pt_role (
    id BIGSERIAL PRIMARY KEY NOT NULL,
    created TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT now(),
    name VARCHAR(45)
);

INSERT INTO ptcore.pt_role (name) VALUES
('Administrator'),
('Trainer');

CREATE TABLE ptcore.pt_user_has_pt_role (
    pt_user_id BIGINT NOT NULL REFERENCES ptcore.pt_user(id),
    pt_role_id BIGINT NOT NULL REFERENCES ptcore.pt_role(id)
);

INSERT INTO ptcore.pt_user_has_pt_role (pt_user_id, pt_role_id) VALUES
(1, 1);
