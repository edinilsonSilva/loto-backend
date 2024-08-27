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
VALUES (now(), true, false, 0, '$2a$10$MbL1Qe6dZGY6xoE2Pd3kYu/vZQN.1jo./28tOVkW2OQkMQnIezL6a', 1);

INSERT INTO public.accounts_roles
    (created_at, type_role, account_id)
VALUES (now(), 'ROLE_ADMINISTRATOR', 1);