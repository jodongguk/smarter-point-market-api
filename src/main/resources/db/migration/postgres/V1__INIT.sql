CREATE TABLE account
(
    account_id   BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    userid       VARCHAR(50),
    password     VARCHAR(100),
    name         VARCHAR(50),
    token_weight BIGINT,
    activated    BOOLEAN,
    created_at   TIMESTAMP WITHOUT TIME ZONE,
    created_by   VARCHAR(255),
    updated_at   TIMESTAMP WITHOUT TIME ZONE,
    updated_by   VARCHAR(255),
    CONSTRAINT account_pkey PRIMARY KEY (account_id)
);
COMMENT ON TABLE account IS '사용자 계정 정보';
COMMENT ON COLUMN account.activated IS '사용자 활성 유무';
COMMENT ON COLUMN account.account_id IS '사용자 고유번호';
COMMENT ON COLUMN account.name IS '사용자 이름';
COMMENT ON COLUMN account.userid IS '사용자 아이디';
COMMENT ON COLUMN account.password IS '사용자 비밀번호';

CREATE TABLE badge
(
    badge_id     BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    institute_id BIGINT,
    recipient    BIGINT,
    title        VARCHAR(255)                            NOT NULL,
    description  OID,
    created_at   TIMESTAMP WITHOUT TIME ZONE,
    created_by   VARCHAR(255),
    updated_at   TIMESTAMP WITHOUT TIME ZONE,
    updated_by   VARCHAR(255),
    CONSTRAINT badge_pkey PRIMARY KEY (badge_id)
);
COMMENT ON TABLE badge IS '배지 정보';
COMMENT ON COLUMN badge.badge_id IS '배지 고유키';
COMMENT ON COLUMN badge.institute_id IS '배지 수여 학원';
COMMENT ON COLUMN badge.recipient IS '배지 취득자';
COMMENT ON COLUMN badge.title IS '배지 제목';
COMMENT ON COLUMN badge.description IS '배지 설명';

CREATE TABLE budget
(
    budget_id     BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    institute_id  BIGINT                                  NOT NULL,
    start_date    date                                    NOT NULL,
    end_date      date                                    NOT NULL,
    budget_amount numeric(38, 2)                          NOT NULL,
    created_at    TIMESTAMP WITHOUT TIME ZONE,
    created_by    VARCHAR(255),
    updated_at    TIMESTAMP WITHOUT TIME ZONE,
    updated_by    VARCHAR(255),
    CONSTRAINT budget_pkey PRIMARY KEY (budget_id)
);
COMMENT ON TABLE budget IS '예산 정보';
COMMENT ON COLUMN budget.budget_amount IS '예산 금액';
COMMENT ON COLUMN budget.end_date IS '예산 종료일';
COMMENT ON COLUMN budget.start_date IS '예산 시작일';
COMMENT ON COLUMN budget.institute_id IS '예산 등록 학원';

CREATE TABLE franchisor
(
    franchisor_id                BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name                         VARCHAR(20),
    owner                        VARCHAR(20),
    tel                          VARCHAR(20),
    zip                          VARCHAR(20),
    addr1                        VARCHAR(200),
    addr2                        VARCHAR(200),
    business_registration_number VARCHAR(20),
    created_at                   TIMESTAMP WITHOUT TIME ZONE,
    created_by                   VARCHAR(255),
    updated_at                   TIMESTAMP WITHOUT TIME ZONE,
    updated_by                   VARCHAR(255),
    CONSTRAINT franchisor_pkey PRIMARY KEY (franchisor_id)
);
COMMENT ON TABLE franchisor IS '가맹점 정보';
COMMENT ON COLUMN franchisor.business_registration_number IS '사업자등록 번호';
COMMENT ON COLUMN franchisor.name IS '학원명';
COMMENT ON COLUMN franchisor.owner IS '대표자명';
COMMENT ON COLUMN franchisor.tel IS '연락처';
COMMENT ON COLUMN franchisor.zip IS '우편번호';
COMMENT ON COLUMN franchisor.addr1 IS '주소1';
COMMENT ON COLUMN franchisor.addr2 IS '주소2';

CREATE TABLE institute
(
    institute_id                 BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name                         VARCHAR(20),
    owner                        VARCHAR(20),
    tel                          VARCHAR(20),
    zip                          VARCHAR(20),
    addr1                        VARCHAR(200),
    addr2                        VARCHAR(200),
    business_registration_number VARCHAR(20),
    created_at                   TIMESTAMP WITHOUT TIME ZONE,
    created_by                   VARCHAR(255),
    updated_at                   TIMESTAMP WITHOUT TIME ZONE,
    updated_by                   VARCHAR(255),
    CONSTRAINT institute_pkey PRIMARY KEY (institute_id)
);
COMMENT ON TABLE institute IS '학원정보';
COMMENT ON COLUMN institute.business_registration_number IS '사업자등록 번호';
COMMENT ON COLUMN institute.name IS '학원명';
COMMENT ON COLUMN institute.owner IS '대표자명';
COMMENT ON COLUMN institute.tel IS '연락처';
COMMENT ON COLUMN institute.zip IS '우편번호';
COMMENT ON COLUMN institute.addr1 IS '주소1';
COMMENT ON COLUMN institute.addr2 IS '주소2';

CREATE TABLE quest
(
    quest_id            BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    institute_id        BIGINT                                  NOT NULL,
    quest_end_date      date                                    NOT NULL,
    quest_start_date    date                                    NOT NULL,
    quest_reward_credit numeric(38, 2)                          NOT NULL,
    created_at          TIMESTAMP WITHOUT TIME ZONE,
    created_by          VARCHAR(255),
    updated_at          TIMESTAMP WITHOUT TIME ZONE,
    updated_by          VARCHAR(255),
    CONSTRAINT quest_pkey PRIMARY KEY (quest_id)
);
COMMENT ON TABLE quest IS '미션 정보';
COMMENT ON COLUMN quest.quest_end_date IS '미션 종료일';
COMMENT ON COLUMN quest.quest_reward_credit IS '미션 수행 금액';
COMMENT ON COLUMN quest.quest_start_date IS '미션 시작일';
COMMENT ON COLUMN quest.institute_id IS '미션 등록 학원';

ALTER TABLE account
    ADD CONSTRAINT account_userid_key UNIQUE (userid);

CREATE TABLE account_authority
(
    account_id     BIGINT       NOT NULL,
    authority_name VARCHAR(255) NOT NULL,
    created_at     TIMESTAMP WITHOUT TIME ZONE,
    created_by     VARCHAR(255),
    updated_at     TIMESTAMP WITHOUT TIME ZONE,
    updated_by     VARCHAR(255),
    CONSTRAINT account_authority_pkey PRIMARY KEY (account_id, authority_name)
);
COMMENT ON TABLE account_authority IS '사용자 권한 정보';

CREATE TABLE account_children
(
    children_id BIGINT NOT NULL,
    parent_id   BIGINT NOT NULL,
    created_at  TIMESTAMP WITHOUT TIME ZONE,
    created_by  VARCHAR(255),
    updated_at  TIMESTAMP WITHOUT TIME ZONE,
    updated_by  VARCHAR(255),
    CONSTRAINT account_children_pkey PRIMARY KEY (children_id, parent_id)
);
COMMENT ON TABLE account_children IS '사용자 자녀 정보';

CREATE TABLE account_franchisor
(
    account_id    BIGINT NOT NULL,
    franchisor_id BIGINT NOT NULL,
    created_at    TIMESTAMP WITHOUT TIME ZONE,
    created_by    VARCHAR(255),
    updated_at    TIMESTAMP WITHOUT TIME ZONE,
    updated_by    VARCHAR(255),
    CONSTRAINT account_franchisor_pkey PRIMARY KEY (account_id, franchisor_id)
);
COMMENT ON TABLE account_franchisor IS '사용자 가맹점 정보';

CREATE TABLE account_institute
(
    account_id   BIGINT NOT NULL,
    institute_id BIGINT NOT NULL,
    created_at   TIMESTAMP WITHOUT TIME ZONE,
    created_by   VARCHAR(255),
    updated_at   TIMESTAMP WITHOUT TIME ZONE,
    updated_by   VARCHAR(255),
    CONSTRAINT account_institute_pkey PRIMARY KEY (account_id, institute_id)
);
COMMENT ON TABLE account_institute IS '사용자 학원 정보';

CREATE TABLE authority
(
    authority_name VARCHAR(50) NOT NULL,
    created_at     TIMESTAMP WITHOUT TIME ZONE,
    created_by     VARCHAR(255),
    updated_at     TIMESTAMP WITHOUT TIME ZONE,
    updated_by     VARCHAR(255),
    CONSTRAINT authority_pkey PRIMARY KEY (authority_name)
);
COMMENT ON TABLE authority IS '사용자 권한';

CREATE TABLE institute_children
(
    children_id  BIGINT NOT NULL,
    institute_id BIGINT NOT NULL,
    created_at   TIMESTAMP WITHOUT TIME ZONE,
    created_by   VARCHAR(255),
    updated_at   TIMESTAMP WITHOUT TIME ZONE,
    updated_by   VARCHAR(255),
    CONSTRAINT institute_children_pkey PRIMARY KEY (children_id, institute_id)
);
COMMENT ON TABLE institute_children IS '학원 등록 자녀 정보';

ALTER TABLE account_children
    ADD CONSTRAINT fk5v4oqiwnc560sw9cg8impy5lv FOREIGN KEY (children_id) REFERENCES account (account_id) ON UPDATE NO ACTION ON DELETE NO ACTION;

ALTER TABLE account_franchisor
    ADD CONSTRAINT fk9t7em4mv0s88qmc1nfuelm3di FOREIGN KEY (account_id) REFERENCES account (account_id) ON UPDATE NO ACTION ON DELETE NO ACTION;

ALTER TABLE account_authority
    ADD CONSTRAINT fkaffih17qsu6xtryrgtal364ap FOREIGN KEY (authority_name) REFERENCES authority (authority_name) ON UPDATE NO ACTION ON DELETE NO ACTION;

ALTER TABLE account_institute
    ADD CONSTRAINT fkasex6eyox9j3u9tat9e9lwlcb FOREIGN KEY (account_id) REFERENCES account (account_id) ON UPDATE NO ACTION ON DELETE NO ACTION;

ALTER TABLE account_franchisor
    ADD CONSTRAINT fkc1wtdoldhs9w7r7rwpq5640jv FOREIGN KEY (franchisor_id) REFERENCES franchisor (franchisor_id) ON UPDATE NO ACTION ON DELETE NO ACTION;

ALTER TABLE institute_children
    ADD CONSTRAINT fkd9f66kh29l539v8krwitvstc7 FOREIGN KEY (institute_id) REFERENCES institute (institute_id) ON UPDATE NO ACTION ON DELETE NO ACTION;

ALTER TABLE institute_children
    ADD CONSTRAINT fkedr8xsmwhww48x8yqsfftxnih FOREIGN KEY (children_id) REFERENCES account (account_id) ON UPDATE NO ACTION ON DELETE NO ACTION;

ALTER TABLE quest
    ADD CONSTRAINT fkekhq6gadxxqvubigrhc17dit6 FOREIGN KEY (institute_id) REFERENCES institute (institute_id) ON UPDATE NO ACTION ON DELETE NO ACTION;

ALTER TABLE budget
    ADD CONSTRAINT fkfk0kqhvd85pktx0g08fc5nd1r FOREIGN KEY (institute_id) REFERENCES institute (institute_id) ON UPDATE NO ACTION ON DELETE NO ACTION;

ALTER TABLE badge
    ADD CONSTRAINT fkh6qr5hf8uhv01wtacdl84kxhh FOREIGN KEY (institute_id) REFERENCES institute (institute_id) ON UPDATE NO ACTION ON DELETE NO ACTION;

ALTER TABLE badge
    ADD CONSTRAINT fkhqwnkd2ynu103jvheubp9opt3 FOREIGN KEY (recipient) REFERENCES account (account_id) ON UPDATE NO ACTION ON DELETE NO ACTION;

ALTER TABLE account_children
    ADD CONSTRAINT fkl6ejveis3y31pci1duey99unw FOREIGN KEY (parent_id) REFERENCES account (account_id) ON UPDATE NO ACTION ON DELETE NO ACTION;

ALTER TABLE account_institute
    ADD CONSTRAINT fkouiolw72f26qxre7y483vim6p FOREIGN KEY (institute_id) REFERENCES institute (institute_id) ON UPDATE NO ACTION ON DELETE NO ACTION;

ALTER TABLE account_authority
    ADD CONSTRAINT fksglqde2oirunehlexcjkt68eb FOREIGN KEY (account_id) REFERENCES account (account_id) ON UPDATE NO ACTION ON DELETE NO ACTION;