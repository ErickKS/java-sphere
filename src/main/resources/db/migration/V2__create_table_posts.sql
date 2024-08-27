CREATE TABLE IF NOT EXISTS TB_SPHERE_POST (
  id BIGINT AUTO_INCREMENT,
  text VARCHAR(255),
  created_at DATETIME,

  PRIMARY KEY (id)
);

INSERT INTO TB_SPHERE_POST (text, created_at) VALUES ('First post content', '2024-08-27 10:00:00');
INSERT INTO TB_SPHERE_POST (text, created_at) VALUES ('Second post content', '2024-08-27 11:00:00');
INSERT INTO TB_SPHERE_POST (text, created_at) VALUES ('Third post content', '2024-08-27 12:00:00');
INSERT INTO TB_SPHERE_POST (text, created_at) VALUES ('Fourth post content', '2024-08-27 13:00:00');
INSERT INTO TB_SPHERE_POST (text, created_at) VALUES ('Fifth post content', '2024-08-27 14:00:00')