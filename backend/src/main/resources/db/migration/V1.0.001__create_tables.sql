CREATE TABLE IF NOT EXISTS public.accounts_configs
(
    id         SERIAL NOT NULL,
    created_at timestamp(30) NULL,
    deleted_at timestamp(30) NULL,
    updated_at timestamp(30) NULL,
    active     bool   NOT NULL DEFAULT TRUE,
    CONSTRAINT accounts_configs_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.accounts_personal
(
    id         SERIAL NOT NULL,
    created_at timestamp(30) NULL,
    deleted_at timestamp(30) NULL,
    updated_at timestamp(30) NULL,
    cpf        varchar(255) NULL,
    name       varchar(255) NULL,
    CONSTRAINT accounts_personal_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.accounts
(
    id          SERIAL NOT NULL,
    created_at  timestamp(30) NULL,
    deleted_at  timestamp(30) NULL,
    updated_at  timestamp(30) NULL,
    username    varchar(255) NULL,
    config_id   int8 NULL,
    personal_id int8 NULL,
    CONSTRAINT accounts_config_id_key UNIQUE (config_id),
    CONSTRAINT accounts_personal_id_key UNIQUE (personal_id),
    CONSTRAINT accounts_pkey PRIMARY KEY (id),
    CONSTRAINT accounts_personal_fkey FOREIGN KEY (personal_id) REFERENCES public.accounts_personal (id),
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

CREATE TABLE IF NOT EXISTS public.accounts_roles
(
    id            SERIAL NOT NULL,
    created_at    timestamp(30) NULL,
    updated_at    timestamp(30) NULL,
    deleted_at    timestamp(30) NULL,
    type_role     varchar(100) NULL,
    account_id    int8 NULL,
    CONSTRAINT accounts_roles_pkey PRIMARY KEY (id),
    CONSTRAINT permission_account_fkey FOREIGN KEY (account_id) REFERENCES public.accounts (id)
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
