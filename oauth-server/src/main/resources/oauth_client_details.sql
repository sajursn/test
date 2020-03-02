
--use passwordencoder.encode(secret) to put it into the client_secret field
INSERT INTO oauth_client_details
    (client_id, client_secret, scope, authorized_grant_types,
    web_server_redirect_uri, authorities, access_token_validity,
    refresh_token_validity, additional_information, autoapprove)
VALUES
    ('employee', '$2a$10$krDTAgKDLpHHbzcngFn2suQ/RNyrf7AOLT8WeX5GftzD4cjIv2pv.', 'read,write',
    'password,authorization_code,refresh_token', null, null, 36000, 36000, null, true);