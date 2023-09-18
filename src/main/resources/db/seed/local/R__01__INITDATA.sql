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
INSERT INTO public.credit (account_id, balance) VALUES (1, 0);

-- 학무보1 샘플
INSERT INTO account (activated, account_id, token_weight, name, userid, password) VALUES (true, 2, 1, '학무보1', 'parent1', '$2a$10$lfOhsoG28WgeO9HYl9Ls7eycUK.UrxjHt5JJTTWfZ10WwK/TPtH5K');
INSERT INTO account_authority (account_id, authority_name) VALUES (2, 'ROLE_PARENT');
INSERT INTO public.credit (account_id, balance) VALUES (2, 0);

-- 학무보1-자식1
INSERT INTO account (activated, account_id, token_weight, name, userid, password) VALUES (true, 3, 1, '학무보1-자식1', (SELECT uuid_in(md5(random()::text || now()::text)::cstring)), NULL);
INSERT INTO account_authority (account_id, authority_name) VALUES (3, 'ROLE_CHILD');
INSERT INTO public.account_children (parent_id, children_id) VALUES (2, 3);
INSERT INTO public.credit (account_id, balance) VALUES (3, 0);

-- 학무보1-자식2
INSERT INTO account (activated, account_id, token_weight, name, userid, password) VALUES (true, 4, 1, '학무보1-자식2', (SELECT uuid_in(md5(random()::text || now()::text)::cstring)), NULL);
INSERT INTO account_authority (account_id, authority_name) VALUES (4, 'ROLE_CHILD');
INSERT INTO public.account_children (parent_id, children_id) VALUES (2, 4);
INSERT INTO public.credit (account_id, balance) VALUES (4, 0);

-- 학무보1-자식2
INSERT INTO account (activated, account_id, token_weight, name, userid, password) VALUES (true, 5, 1, '학무보1-자식3', (SELECT uuid_in(md5(random()::text || now()::text)::cstring)), NULL);
INSERT INTO account_authority (account_id, authority_name) VALUES (5, 'ROLE_CHILD');
INSERT INTO public.account_children (parent_id, children_id) VALUES (2, 5);
INSERT INTO public.credit (account_id, balance) VALUES (5, 0);

-- 가맹점1
INSERT INTO public.franchisor (franchisor_id, name, owner, business_registration_number) VALUES (1, '돌하루마트', '조동국', '1565400614');
INSERT INTO public.account_franchisor (account_id, franchisor_id) VALUES (1, 1);

-- 학원1
INSERT INTO public.institute (institute_id, name, owner, business_registration_number) VALUES (1, '태권도장', '조동국', '1565400614');
INSERT INTO public.account_institute (account_id, institute_id) VALUES (1, 1);
INSERT INTO public.institute_children (institute_id, children_id) VALUES (1, 3);
INSERT INTO public.institute_children (institute_id, children_id) VALUES (1, 4);

INSERT INTO account (activated, account_id, token_weight, name, userid, password) VALUES (true, 6, 1, '학원선생1', 'teacher1', '$2a$10$lfOhsoG28WgeO9HYl9Ls7eycUK.UrxjHt5JJTTWfZ10WwK/TPtH5K');
INSERT INTO account_authority (account_id, authority_name) VALUES (6, 'ROLE_INSTITUTE');
INSERT INTO public.credit (account_id, balance) VALUES (6, 0);
INSERT INTO public.account_institute (account_id, institute_id) VALUES (6, 1);

-- 학원2
INSERT INTO public.institute (institute_id, name, owner, business_registration_number) VALUES (2, 'LT미술학원', '조동국', '1565400614');
INSERT INTO public.account_institute (account_id, institute_id) VALUES (1, 2);
INSERT INTO public.institute_children (institute_id, children_id) VALUES (2, 4);
INSERT INTO public.institute_children (institute_id, children_id) VALUES (2, 5);
