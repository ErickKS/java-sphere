ALTER TABLE TB_SPHERE_USER
ADD COLUMN avatar VARCHAR(255);

UPDATE TB_SPHERE_USER SET avatar = 'https://avatar.iran.liara.run/username?username=Alice+Smith' WHERE email = 'alice@example.com';
UPDATE TB_SPHERE_USER SET avatar = 'https://avatar.iran.liara.run/username?username=Bob+Johnson' WHERE email = 'bob@example.com';