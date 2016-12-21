ALTER TABLE ptcore.parse_goal ADD COLUMN errors VARCHAR(1000);
ALTER TABLE ptcore.parse_goal ADD COLUMN sheet_index INT NOT NULL DEFAULT 0;
