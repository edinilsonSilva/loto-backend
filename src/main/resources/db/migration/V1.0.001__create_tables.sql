-- Tabela principal para armazenar os concursos
CREATE TABLE lottery_draw
(
    id                          SERIAL  NOT NULL PRIMARY KEY,
    created_at                  timestamp(30) NULL,
    updated_at                  timestamp(30) NULL,
    accumulated                 BOOLEAN NOT NULL,
    draw_date                   DATE,
    next_draw_date              DATE,
    show_details_by_city        BOOLEAN NOT NULL,
    special_draw_indicator      INT,
    draw_location               VARCHAR(255),
    draw_city_state             VARCHAR(255),
    heart_team_name             VARCHAR(255),
    number                      INT,
    previous_draw_number        INT,
    final_draw_number_0_5       INT,
    next_draw_number            INT,
    game_number                 INT,
    notes                       TEXT,
    contingency_prize           VARCHAR(500),
    game_type                   VARCHAR(50),
    publication_type            INT,
    last_draw                   BOOLEAN NOT NULL,
    collected_amount            VARCHAR(500),
    accumulated_amount_0_5      VARCHAR(500),
    special_accumulated_amount  VARCHAR(500),
    next_accumulated_amount     VARCHAR(500),
    estimated_next_draw_amount  VARCHAR(500),
    guarantee_reserve_balance   VARCHAR(500),
    total_prize_amount_tier_one VARCHAR(500)
);

-- Tabela para armazenar a lista de dezenas sorteadas em ordem
CREATE TABLE drawn_numbers_in_order
(
    id              SERIAL NOT NULL PRIMARY KEY,
    lottery_draw_id BIGINT,
    number          VARCHAR(10),
    FOREIGN KEY (lottery_draw_id) REFERENCES lottery_draw (id) ON DELETE CASCADE
);

-- Tabela para armazenar a lista de dezenas do sorteio
CREATE TABLE draw_numbers
(
    id              SERIAL NOT NULL PRIMARY KEY,
    lottery_draw_id BIGINT,
    number          VARCHAR(10),
    FOREIGN KEY (lottery_draw_id) REFERENCES lottery_draw (id) ON DELETE CASCADE
);

-- Tabela para armazenar a lista de dezenas do segundo sorteio (se aplicável)
CREATE TABLE second_draw_numbers
(
    id              SERIAL NOT NULL PRIMARY KEY,
    lottery_draw_id BIGINT,
    number          VARCHAR(10),
    FOREIGN KEY (lottery_draw_id) REFERENCES lottery_draw (id) ON DELETE CASCADE
);

-- Tabela para armazenar os detalhes do rateio de prêmios
CREATE TABLE prize_breakdown
(
    id                     SERIAL NOT NULL PRIMARY KEY,
    lottery_draw_id        BIGINT,
    prize_tier_description VARCHAR(255),
    tier                   INT,
    number_of_winners      INT,
    prize_amount           VARCHAR(500),
    FOREIGN KEY (lottery_draw_id) REFERENCES lottery_draw (id) ON DELETE CASCADE
);

CREATE TABLE public.accounts_configs
(
    id         SERIAL NOT NULL,
    created_at timestamp(30) NULL,
    deleted_at timestamp(30) NULL,
    updated_at timestamp(30) NULL,
    active     bool   NOT NULL DEFAULT TRUE,
    CONSTRAINT accounts_configs_pkey PRIMARY KEY (id)
);

CREATE TABLE public.accounts_admins
(
    id         SERIAL                   NOT NULL,
    created_at timestamp with time zone NOT NULL,
    updated_at timestamp with time zone,
    deleted_at timestamp with time zone,
    CONSTRAINT pk_accounts_admins PRIMARY KEY (id)
);

CREATE TABLE public.accounts_admins_permissions
(
    account_admin_id bigint NOT NULL,
    permission       text   NOT NULL,
    CONSTRAINT fk_accounts_admins_permissions FOREIGN KEY (account_admin_id) REFERENCES public.accounts_admins (id)
);

CREATE TABLE public.accounts_lotteries
(
    id         SERIAL                   NOT NULL,
    created_at timestamp with time zone NOT NULL,
    updated_at timestamp with time zone,
    deleted_at timestamp with time zone,
    CONSTRAINT pk_accounts_lotteries PRIMARY KEY (id)
);

CREATE TABLE public.accounts_lotteries_permissions
(
    account_lottery_id bigint NOT NULL,
    permission         text   NOT NULL,
    CONSTRAINT fk_accounts_lottery_permissions FOREIGN KEY (account_lottery_id) REFERENCES public.accounts_lotteries (id)
);

CREATE TABLE public.accounts
(
    id           SERIAL NOT NULL,
    created_at   timestamp(30) NULL,
    deleted_at   timestamp(30) NULL,
    updated_at   timestamp(30) NULL,
    activated_at timestamp with time zone,
    status       character varying(50),
    name         character varying(255),
    cpf          character varying(20),
    config_id    int8 NULL,
    admin_id     int8 NULL,
    lottery_id   int8 NULL,
    CONSTRAINT accounts_pkey PRIMARY KEY (id),
    CONSTRAINT accounts_cpf_unique_key UNIQUE (cpf),
    CONSTRAINT accounts_admin_fkey FOREIGN KEY (admin_id) REFERENCES public.accounts_admins (id),
    CONSTRAINT accounts_lottery_fkey FOREIGN KEY (lottery_id) REFERENCES public.accounts_lotteries (id),
    CONSTRAINT accounts_configs_fkey FOREIGN KEY (config_id) REFERENCES public.accounts_configs (id)
);

CREATE TABLE public.accounts_contacts
(
    id           SERIAL NOT NULL,
    created_at   timestamp(30) NULL,
    deleted_at   timestamp(30) NULL,
    expired_at   timestamp(30) NULL,
    updated_at   timestamp(30) NULL,
    validated_at timestamp(30) NULL,
    code         varchar(255) NULL,
    type         varchar(255) NULL,
    value        varchar(255) NULL,
    account_id   int8 NULL,
    CONSTRAINT accounts_contacts_pkey PRIMARY KEY (id),
    CONSTRAINT contacts_accounts_fkey FOREIGN KEY (account_id) REFERENCES public.accounts (id)
);

CREATE TABLE public.accounts_passwords
(
    id                         SERIAL NOT NULL,
    created_at                 timestamp(30) NULL,
    updated_at                 timestamp(30) NULL,
    deleted_at                 timestamp(30) NULL,
    expired_at                 timestamp(30) NULL,
    active                     bool   NOT NULL,
    password                   varchar(255) NULL,
    create_password_next_login bool NULL,
    same_password_limit        int8 NULL,
    token_forget_password      varchar(255) NULL,
    account_id                 int8 NULL,
    CONSTRAINT accounts_passwords_pkey PRIMARY KEY (id),
    CONSTRAINT password_accounts_fkey FOREIGN KEY (account_id) REFERENCES public.accounts (id)
);

CREATE TABLE public.accounts_photos
(
    id           SERIAL NOT NULL,
    created_at   timestamp(30) NULL,
    updated_at   timestamp(30) NULL,
    deleted_at   timestamp(30) NULL,
    name         varchar(255) NULL,
    description  varchar(255) NULL,
    path         varchar(255) NULL,
    content_type varchar(255) NULL,
    size         int8 NULL,
    base_64      varchar(10000000) NULL,
    type_photo   varchar(255) NULL,
    account_id   int8 NULL,
    CONSTRAINT accounts_photos_pkey PRIMARY KEY (id),
    CONSTRAINT photo_accounts_fkey FOREIGN KEY (account_id) REFERENCES public.accounts (id)
);

CREATE TABLE public.accounts_lotteries_wallets
(
    id            SERIAL         NOT NULL,
    created_at    timestamp(30) NULL,
    deleted_at    timestamp(30) NULL,
    updated_at    timestamp(30) NULL,
    balance       NUMERIC(19, 4) NOT NULL DEFAULT 0,
    type_currency character varying(4),
    lottery_id    int8 NULL,
    CONSTRAINT accounts_lotteries_wallets_pkey PRIMARY KEY (id),
    CONSTRAINT accounts_lotteries_wallets_fkey FOREIGN KEY (lottery_id) REFERENCES public.accounts_lotteries (id)
);


CREATE TABLE public.draws
(
    id        SERIAL NOT NULL,
    draw_date timestamp(6) NULL,
    CONSTRAINT draws_pkey PRIMARY KEY (id)
);

CREATE TABLE public.winning_numbers
(
    numero  int4 NULL,
    draw_id int8 NOT NULL,
    CONSTRAINT fk_winning_numbers_draw FOREIGN KEY (draw_id) REFERENCES public.draws (id)
);

CREATE TABLE pools
(
    id              SERIAL       NOT NULL,
    created_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    created_by      BIGINT,
    status          VARCHAR(20)  NOT NULL,
    code            VARCHAR(50)  NOT NULL,
    probability     VARCHAR(100) NOT NULL,
    total_shares    INT          NOT NULL,
    draw_number     INT          NOT NULL,
    entry_fee       VARCHAR(255) NOT NULL,
    lottery_draw_id BIGINT       NOT NULL,
    CONSTRAINT pools_pkey PRIMARY KEY (id),
    CONSTRAINT fk_pool_account FOREIGN KEY (created_by) REFERENCES accounts (id),
    CONSTRAINT fk_pool_lottery_draw FOREIGN KEY (lottery_draw_id) REFERENCES lottery_draw (id)
);

CREATE TABLE public.participants
(
    created_at timestamp(6) NULL,
    created_by int8 NULL,
    id         bigserial NOT NULL,
    pool_id    int8 NULL,
    CONSTRAINT participants_pkey PRIMARY KEY (id),
    CONSTRAINT fk_participant_pool_id FOREIGN KEY (pool_id) REFERENCES public.pools (id),
    CONSTRAINT fk_participant_account_id FOREIGN KEY (created_by) REFERENCES public.accounts (id)
);

CREATE TABLE public.proprietary_bets
(
    id              SERIAL NOT NULL,
    created_at      timestamp(6) NULL,
    created_by      int8 NULL,
    amount          numeric(38, 2) NULL,
    lottery_draw_id int8 NULL,
    participant_id  int8 NULL,
    pool_id         int8 NULL,
    CONSTRAINT proprietary_bets_pkey PRIMARY KEY (id),
    CONSTRAINT fk_propietary_bet_lottery_draw FOREIGN KEY (lottery_draw_id) REFERENCES lottery_draw (id),
    CONSTRAINT fk_propietary_bet_account_id FOREIGN KEY (created_by) REFERENCES public.accounts (id),
    CONSTRAINT fk_propietary_bet_pool_id FOREIGN KEY (pool_id) REFERENCES public.pools (id),
    CONSTRAINT fk_propietary_bet_participant_id FOREIGN KEY (participant_id) REFERENCES public.participants (id)
);

CREATE TABLE public.bets
(
    amount         numeric(38, 2) NULL,
    id             bigserial NOT NULL,
    participant_id int8 NULL,
    pool_id        int8 NULL,
    CONSTRAINT bets_pkey PRIMARY KEY (id),
    CONSTRAINT fk_bet_participant_id FOREIGN KEY (participant_id) REFERENCES public.participants (id),
    CONSTRAINT fk_bet_pool_id FOREIGN KEY (pool_id) REFERENCES public.pools (id)
);

CREATE TABLE public.proprietary_bet_chosen_numbers
(
    chosen_numbers     int4 NULL,
    proprietary_bet_id int8 NOT NULL,
    CONSTRAINT fk_proprietry_bet_chosen_number_id FOREIGN KEY (proprietary_bet_id) REFERENCES public.proprietary_bets (id)
);

CREATE TABLE public.bet_chosen_numbers
(
    chosen_numbers int4 NULL,
    bet_id         int8 NOT NULL,
    CONSTRAINT fk_bet_chosen_number_bet_id FOREIGN KEY (bet_id) REFERENCES public.bets (id)
);