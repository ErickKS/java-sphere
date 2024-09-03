ALTER TABLE TB_SPHERE_POST
ADD COLUMN user_id BIGINT;

ALTER TABLE TB_SPHERE_POST
ADD CONSTRAINT fk_user
FOREIGN KEY (user_id) REFERENCES TB_SPHERE_USER(id);

INSERT INTO TB_SPHERE_USER (name, bio, email, password, created_at, updated_at) VALUES
('Alice', 'Software Developer', 'alice@example.com', '$2a$12$4TgHfKR9lQibgu65IkAxDO1FN7ubGk42sk8iPvpZ.Dcf4Qnyzmtei', NOW(), NOW()),
('Bob', 'Data Scientist', 'bob@example.com', '$2a$12$4TgHfKR9lQibgu65IkAxDO1FN7ubGk42sk8iPvpZ.Dcf4Qnyzmtei', NOW(), NOW());

UPDATE TB_SPHERE_POST SET user_id = (SELECT id FROM TB_SPHERE_USER WHERE email = 'alice@example.com') WHERE id IN (1, 2);
UPDATE TB_SPHERE_POST SET user_id = (SELECT id FROM TB_SPHERE_USER WHERE email = 'bob@example.com') WHERE id IN (3, 4, 5);