CREATE TABLE if_statements (
  id BIGINT NOT NULL AUTO_INCREMENT,
  variable VARCHAR(100) NOT NULL,
  value BIGINT NOT NULL,
  comparison_operator VARCHAR (5) NOT NULL,
  `else` TINYINT NOT NULL,
  `then` TINYINT NOT NULL,
  `last` TINYINT NOT NULL,
  policy_id BIGINT NOT NULL,
  PRIMARY KEY (id)
);

ALTER TABLE if_statements ADD CONSTRAINT fk_policy_id FOREIGN KEY (policy_id) REFERENCES policies(id);