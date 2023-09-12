-- 권한
INSERT INTO authority (authority_name) VALUES ('ROLE_ANONYMOUS');
INSERT INTO authority (authority_name) VALUES ('ROLE_TEST');
INSERT INTO authority (authority_name) VALUES ('ROLE_USER');
INSERT INTO authority (authority_name) VALUES ('ROLE_CHILD');
INSERT INTO authority (authority_name) VALUES ('ROLE_PARENT');
INSERT INTO authority (authority_name) VALUES ('ROLE_INSTITUTE');
INSERT INTO authority (authority_name) VALUES ('ROLE_FRANCHISOR');
INSERT INTO authority (authority_name) VALUES ('ROLE_ADMIN');
INSERT INTO authority (authority_name) VALUES ('ROLE_SYSTEM');

-- 관리자
INSERT INTO account (activated, account_id, token_weight, name, userid, password) VALUES (true, 1, 1, '관리자', 'admin', '$2a$10$lfOhsoG28WgeO9HYl9Ls7eycUK.UrxjHt5JJTTWfZ10WwK/TPtH5K');
INSERT INTO account_authority (account_id, authority_name) VALUES (1, 'ROLE_ADMIN');

-- 학무보1 샘플
INSERT INTO account (activated, account_id, token_weight, name, userid, password) VALUES (true, 2, 1, '학무보1', 'parent1', '$2a$10$lfOhsoG28WgeO9HYl9Ls7eycUK.UrxjHt5JJTTWfZ10WwK/TPtH5K');
INSERT INTO account_authority (account_id, authority_name) VALUES (2, 'ROLE_PARENT');

-- 학무보1-자식1
INSERT INTO account (activated, account_id, token_weight, name, userid, password) VALUES (true, 3, 1, '학무보1-자식1', (SELECT uuid_in(md5(random()::text || now()::text)::cstring)), NULL);
INSERT INTO account_authority (account_id, authority_name) VALUES (3, 'ROLE_CHILD');
INSERT INTO public.account_child (account_id, children_account_id) VALUES (2, 3);

-- 학무보1-자식2
INSERT INTO account (activated, account_id, token_weight, name, userid, password) VALUES (true, 4, 1, '학무보1-자식2', (SELECT uuid_in(md5(random()::text || now()::text)::cstring)), NULL);
INSERT INTO account_authority (account_id, authority_name) VALUES (4, 'ROLE_CHILD');
INSERT INTO public.account_child (account_id, children_account_id) VALUES (2, 4);