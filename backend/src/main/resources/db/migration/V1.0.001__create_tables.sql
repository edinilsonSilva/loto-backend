CREATE TABLE IF NOT EXISTS public.accounts_configs
(
    id         SERIAL NOT NULL,
    created_at timestamp(30) NULL,
    deleted_at timestamp(30) NULL,
    updated_at timestamp(30) NULL,
    active     bool   NOT NULL DEFAULT TRUE,
    CONSTRAINT accounts_configs_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.accounts_admins
(
    id SERIAL NOT NULL,
    created_at timestamp with time zone NOT NULL,
    updated_at timestamp with time zone,
    deleted_at timestamp with time zone,
    CONSTRAINT pk_accounts_admins PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.accounts_admins_permissions
(
    account_admin_id bigint NOT NULL,
    permission text NOT NULL,
    CONSTRAINT fk_accounts_admins_permissions FOREIGN KEY (account_admin_id) REFERENCES public.accounts_admins (id)
);

CREATE TABLE IF NOT EXISTS public.accounts_lotteries
(
    id SERIAL NOT NULL,
    created_at timestamp with time zone NOT NULL,
    updated_at timestamp with time zone,
    deleted_at timestamp with time zone,
    CONSTRAINT pk_accounts_lotteries PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.accounts_lotteries_permissions
(
    account_lottery_id bigint NOT NULL,
    permission text NOT NULL,
    CONSTRAINT fk_accounts_lottery_permissions FOREIGN KEY (account_lottery_id) REFERENCES public.accounts_lotteries (id)
);

CREATE TABLE IF NOT EXISTS public.accounts
(
    id              SERIAL NOT NULL,
    created_at      timestamp(30) NULL,
    deleted_at      timestamp(30) NULL,
    updated_at      timestamp(30) NULL,
    activated_at    timestamp with time zone,
    status          character varying (50),
    name            character varying (255),
    cpf             character varying (20),
    config_id       int8 NULL,
    admin_id        int8 NULL,
    lottery_id      int8 NULL,
    CONSTRAINT accounts_pkey PRIMARY KEY (id),
    CONSTRAINT accounts_cpf_unique_key UNIQUE (cpf),
    CONSTRAINT accounts_admin_fkey FOREIGN KEY (admin_id) REFERENCES public.accounts_admins (id),
    CONSTRAINT accounts_lottery_fkey FOREIGN KEY (lottery_id) REFERENCES public.accounts_lotteries (id),
    CONSTRAINT accounts_configs_fkey FOREIGN KEY (config_id) REFERENCES public.accounts_configs (id)
);

CREATE TABLE IF NOT EXISTS public.accounts_contacts
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

CREATE TABLE IF NOT EXISTS public.accounts_passwords
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

CREATE TABLE IF NOT EXISTS public.accounts_photos
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

CREATE TABLE IF NOT EXISTS public.accounts_lotteries_wallets
(
    id              SERIAL NOT NULL,
    created_at      timestamp(30) NULL,
    deleted_at      timestamp(30) NULL,
    updated_at      timestamp(30) NULL,
    balance         NUMERIC(19, 4) NOT NULL DEFAULT 0,
    type_currency   character varying (4),
    lottery_id      int8 NULL,
    CONSTRAINT accounts_lotteries_wallets_pkey PRIMARY KEY (id),
    CONSTRAINT accounts_lotteries_wallets_fkey FOREIGN KEY (lottery_id) REFERENCES public.accounts_lotteries (id)
);


CREATE TABLE IF NOT EXISTS public.draws (
      id SERIAL NOT NULL,
      draw_date timestamp(6) NULL,
      CONSTRAINT draws_pkey PRIMARY KEY (id)
);

CREATE TABLE public.winning_numbers (
        numero int4 NULL,
        draw_id int8 NOT NULL,
        CONSTRAINT fk_winning_numbers_draw FOREIGN KEY (draw_id) REFERENCES public.draws(id)
);

CREATE TABLE IF NOT EXISTS public.games (
      id SERIAL NOT NULL,
      max_number_value int4 NULL,
      max_numbers int4 NULL,
      min_numbers int4 NULL,
      created_at timestamp(6) NULL,
      created_by int8 NULL,
      updated_at timestamp(6) NULL,
      "name" varchar(255) NULL,
      CONSTRAINT games_pkey PRIMARY KEY (id),
      CONSTRAINT fk_game_account_id FOREIGN KEY (created_by) REFERENCES public.accounts(id)
);

CREATE TABLE public.pools (
      number_of_draws int4 NOT NULL,
      total_amount numeric(38, 2) NULL,
      created_at timestamp(6) NULL,
      created_by int8 NULL,
      game_id int8 NULL,
      id bigserial NOT NULL,
      updated_at timestamp(6) NULL,
      "name" varchar(255) NULL,
      status varchar(255) NULL,
      CONSTRAINT pools_pkey PRIMARY KEY (id),
      CONSTRAINT pools_status_check CHECK (((status)::text = ANY ((ARRAY['ABERTO'::character varying, 'FECHADO'::character varying, 'FINALIZADO'::character varying])::text[]))),
	CONSTRAINT fk_pool_game_id FOREIGN KEY (game_id) REFERENCES public.games(id),
	CONSTRAINT fk_pool_account_id FOREIGN KEY (created_by) REFERENCES public.accounts(id)
);

CREATE TABLE public.participants (
         created_at timestamp(6) NULL,
         created_by int8 NULL,
         id bigserial NOT NULL,
         pool_id int8 NULL,
         CONSTRAINT participants_pkey PRIMARY KEY (id),
         CONSTRAINT fk_participant_pool_id FOREIGN KEY (pool_id) REFERENCES public.pools(id),
         CONSTRAINT fk_participant_account_id FOREIGN KEY (created_by) REFERENCES public.accounts(id)
);

CREATE TABLE public.proprietary_bets (
         amount numeric(38, 2) NULL,
         created_at timestamp(6) NULL,
         created_by int8 NULL,
         game_id int8 NULL,
         id bigserial NOT NULL,
         participant_id int8 NULL,
         pool_id int8 NULL,
         CONSTRAINT proprietary_bets_pkey PRIMARY KEY (id),
         CONSTRAINT fk_propietary_bet_game_id FOREIGN KEY (game_id) REFERENCES public.games(id),
         CONSTRAINT fk_propietary_bet_account_id FOREIGN KEY (created_by) REFERENCES public.accounts(id),
         CONSTRAINT fk_propietary_bet_pool_id FOREIGN KEY (pool_id) REFERENCES public.pools(id),
         CONSTRAINT fk_propietary_bet_participant_id FOREIGN KEY (participant_id) REFERENCES public.participants(id)
);

CREATE TABLE public.bets (
         amount numeric(38, 2) NULL,
         id bigserial NOT NULL,
         participant_id int8 NULL,
         pool_id int8 NULL,
         CONSTRAINT bets_pkey PRIMARY KEY (id),
         CONSTRAINT fk_bet_participant_id FOREIGN KEY (participant_id) REFERENCES public.participants(id),
         CONSTRAINT fk_bet_pool_id FOREIGN KEY (pool_id) REFERENCES public.pools(id)
);

CREATE TABLE public.proprietary_bet_chosen_numbers (
       chosen_numbers int4 NULL,
       proprietary_bet_id int8 NOT NULL,
       CONSTRAINT fk_proprietry_bet_chosen_number_id FOREIGN KEY (proprietary_bet_id) REFERENCES public.proprietary_bets(id)
);

CREATE TABLE public.awards (
       number_of_matches int4 NOT NULL,
       value numeric(38, 2) NULL,
       bet_id int8 NULL,
       draw_id int8 NULL,
       id bigserial NOT NULL,
       CONSTRAINT awards_pkey PRIMARY KEY (id),
       CONSTRAINT fk_award_bet_id FOREIGN KEY (bet_id) REFERENCES public.bets(id),
       CONSTRAINT fk_award_draw_id FOREIGN KEY (draw_id) REFERENCES public.draws(id)
);

CREATE TABLE public.bet_chosen_numbers (
       chosen_numbers int4 NULL,
       bet_id int8 NOT NULL,
       CONSTRAINT fk_bet_chosen_number_bet_id FOREIGN KEY (bet_id) REFERENCES public.bets(id)
);