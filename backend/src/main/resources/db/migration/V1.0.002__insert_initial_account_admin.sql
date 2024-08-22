INSERT INTO public.permissions
    (created_at, name, description)
VALUES (now(), 'ADMIN', 'Administrador');

INSERT INTO public.accounts_configs
    (created_at, active)
VALUES (now(), true);

INSERT INTO public.accounts_personal
    (created_at, cpf, name)
VALUES (now(), '01966601301', 'Edinilson Silva');

INSERT INTO public.accounts
(created_at, username, personal_id, config_id)
VALUES (now(), 'bateraed', 1, 1);

INSERT INTO public.accounts_contacts
(created_at, validated_at, type, value, account_id)
VALUES (now(), now(), 'EMAIL', 'bateraed@gmail.com', 1);

INSERT INTO public.accounts_passwords
(created_at, active, create_password_next_login,same_password_limit, password, account_id)
VALUES (now(), true, false, 0, '$2a$10$yfQhPlgEX8Yuc.SxUWKTl.tpGQtnWXAylMoK.0UfhZ8lNFxyJ9hTy', 1);

INSERT INTO public.accounts_permissions
    (created_at, permission_id, account_id)
VALUES (now(), 1, 1);