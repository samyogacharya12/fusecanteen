INSERT INTO oauth_client_details
	(client_id, client_secret, scope, authorized_grant_types,
	web_server_redirect_uri, authorities, access_token_validity,
	refresh_token_validity, additional_information, autoapprove)
VALUES
	('{client_id}', '{client_secret}', 'read,write,trust',
	'client_credentials,password,authorization_code,refresh_token', null, 'ROLE_CLIENT,ROLE_TRUSTED_CLIENT', 36000, 0, '{"bibash":"no additional data"}', true);
