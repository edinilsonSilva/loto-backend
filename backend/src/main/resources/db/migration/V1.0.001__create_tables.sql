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