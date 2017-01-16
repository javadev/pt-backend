DROP TABLE ptcore.in_user_goal_photo CASCADE;
DROP TABLE ptcore.in_user_goal_has_in_user_goal_photo CASCADE;

CREATE TABLE ptcore.in_user_photo (
    id BIGSERIAL PRIMARY KEY NOT NULL,
    created TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT now(),
    goal_id BIGINT NOT NULL,
    file_name VARCHAR(200) NOT NULL DEFAULT '',
    file_size BIGINT NOT NULL DEFAULT 0,
    file_type VARCHAR(100) NOT NULL DEFAULT '',
    data_url TEXT
);

CREATE TABLE ptcore.in_user_has_in_user_photo (
    in_user_id BIGINT NOT NULL REFERENCES in_user(id),
    in_user_photo_id BIGINT NOT NULL REFERENCES in_user_photo(id)
);
