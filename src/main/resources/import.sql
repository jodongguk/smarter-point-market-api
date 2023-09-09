-- 권한
INSERT INTO authority (authority_name) VALUES ('ROLE_USER');
INSERT INTO authority (authority_name) VALUES ('ROLE_CHILD');
INSERT INTO authority (authority_name) VALUES ('ROLE_INSTITUTE');
INSERT INTO authority (authority_name) VALUES ('ROLE_FRANCHISOR');
INSERT INTO authority (authority_name) VALUES ('ROLE_ADMIN');

-- 회원
INSERT INTO account (activated, account_id, token_weight, name, userid, password) VALUES (true, 1, 1, '관리자', 'admin', '$2a$10$lfOhsoG28WgeO9HYl9Ls7eycUK.UrxjHt5JJTTWfZ10WwK/TPtH5K');

-- 권한부여
INSERT INTO account_authority (account_id, authority_name) VALUES (1, 'ROLE_USER');
INSERT INTO account_authority (account_id, authority_name) VALUES (1, 'ROLE_ADMIN');
