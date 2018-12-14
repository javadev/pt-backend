CREATE TABLE ptcore.in_user_goal_photo (
    id BIGSERIAL PRIMARY KEY NOT NULL,
    created TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT now(),
    file_name VARCHAR(200) NOT NULL DEFAULT '',
    file_size BIGINT NOT NULL DEFAULT 0,
    file_type VARCHAR(100) NOT NULL DEFAULT '',
    data_url TEXT
);

CREATE TABLE ptcore.in_user_goal_has_in_user_goal_photo (
    in_user_goal_id BIGINT NOT NULL REFERENCES ptcore.in_user_goal(id),
    in_user_goal_photo_id BIGINT NOT NULL REFERENCES ptcore.in_user_goal_photo(id)
);
