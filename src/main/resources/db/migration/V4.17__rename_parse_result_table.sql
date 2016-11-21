DROP TABLE ptcore.parse_result;
CREATE TABLE ptcore.parse_user (
    id BIGSERIAL PRIMARY KEY NOT NULL,
    program_id BIGINT NOT NULL,
    created TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT now(),
    name VARCHAR(200),
    sheet_index INT NOT NULL DEFAULT 0,
    errors VARCHAR(1000),
    FOREIGN KEY (program_id) REFERENCES ptcore.program (id) ON UPDATE CASCADE ON DELETE CASCADE
);
