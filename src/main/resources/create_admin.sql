INSERT INTO mydb.users (first_name, last_name, address, phonenumber)
SELECT 'admin', 'admin', '', ''
WHERE NOT EXISTS (SELECT * FROM mydb.users WHERE first_name = 'admin' AND last_name = 'admin');

SELECT id_user INTO @admin_id_user FROM mydb.users WHERE first_name = 'admin' AND last_name = 'admin';

INSERT INTO mydb.accounts (id_user, pin, balance, type)
SELECT @admin_id_user, 2178, 0, 'admin'
WHERE NOT EXISTS (SELECT * FROM mydb.accounts WHERE id_user = @admin_id_user);

SELECT id_account INTO @admin_id_account FROM mydb.accounts WHERE id_user = @admin_id_user;

INSERT INTO mydb.cards (id_account, number, expiration_date, cvc, type, block)
SELECT @admin_id_account, '0', '12-99', 314, 'admin', 0
WHERE NOT EXISTS (SELECT * FROM mydb.cards WHERE id_account = @admin_id_account);


