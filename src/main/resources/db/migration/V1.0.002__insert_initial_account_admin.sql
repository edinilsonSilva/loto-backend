INSERT INTO public.accounts_configs
    (created_at, active)
VALUES (now(), true);

INSERT INTO public.accounts_admins
    (created_at)
VALUES (now());

INSERT INTO public.accounts_admins_permissions
(account_admin_id, permission)
VALUES (1, 'ALLOW_CREATE_ACCOUNT_ADMIN');

INSERT INTO public.accounts_admins_permissions
(account_admin_id, permission)
VALUES (1, 'ALLOW_UPDATE_ACCOUNT_ADMIN');

INSERT INTO public.accounts_admins_permissions
(account_admin_id, permission)
VALUES (1, 'ALLOW_DELETE_ACCOUNT_ADMIN');

INSERT INTO public.accounts_admins_permissions
(account_admin_id, permission)
VALUES (1, 'ALLOW_VIEW_ACCOUNT_ADMIN');

INSERT INTO public.accounts_lotteries
(created_at)
VALUES (now());

INSERT INTO public.accounts_lotteries_permissions
(account_lottery_id, permission)
VALUES (1, 'ALLOW_CREATE_BETTING');

INSERT INTO public.accounts
(created_at, activated_at, name, cpf, admin_id, lottery_id, config_id)
VALUES (now(), now(), 'ADMIN CONECTALOT', '09103997006',  1, 1, 1);

INSERT INTO public.accounts_contacts
(created_at, validated_at, type, value, account_id)
VALUES (now(), now(), 'EMAIL', 'admin-loto@conectalot.com', 1);

INSERT INTO public.accounts_contacts
(created_at, validated_at, type, value, account_id)
VALUES (now(), now(), 'MOBILE_PHONE', '85999999999', 1);

INSERT INTO public.accounts_passwords
(created_at, active, create_password_next_login,same_password_limit, password, account_id)
VALUES (now(), true, false, 0, '$2a$10$MbL1Qe6dZGY6xoE2Pd3kYu/vZQN.1jo./28tOVkW2OQkMQnIezL6a', 1);

INSERT INTO public.accounts_lotteries_wallets
(created_at, balance, type_currency, lottery_id)
VALUES (now(), 0, 'BRL', 1);