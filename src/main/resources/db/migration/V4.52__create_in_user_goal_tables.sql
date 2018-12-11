DROP TABLE ptcore.in_goal CASCADE;
DROP TABLE ptcore.in_goal_parameter CASCADE;
DROP TABLE ptcore.in_goal_has_in_goal_parameter CASCADE;

CREATE TABLE ptcore.in_user_goal (
    id BIGSERIAL PRIMARY KEY NOT NULL,
    created TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT now(),
    goal_id BIGINT,
    d_goal_title VARCHAR(45),
    d_goal_title_2 VARCHAR(45),
    goal_value INT
);

CREATE TABLE ptcore.in_user_has_in_user_goal (
    in_user_id      BIGINT NOT NULL REFERENCES ptcore.in_user(id),
    in_user_goal_id BIGINT NOT NULL REFERENCES ptcore.in_user_goal(id)
);
