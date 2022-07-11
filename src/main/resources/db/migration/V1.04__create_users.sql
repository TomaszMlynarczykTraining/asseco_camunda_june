CREATE TABLE IF NOT EXISTS `users` (

    `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `first_name` varchar(50),
    `last_name` varchar(50),
    `email_id` varchar(50),
    `password` varchar(50),
    `is_logged` varchar(1),
    `login` varchar(50)

);


--ALTER  TABLE  loan  CONSTRAINT  loan_x_client_con  FOREIGN  KEY  (client_id) REFERENCES  client (id);

INSERT INTO users (id, first_name, last_name, email_id, password, is_logged, login)
VALUES ('1', 'Jan', 'Nowak', 'jan@nowak.pl', 'pass', '1', 'log_1');


INSERT INTO users (id, first_name, last_name, email_id, password, is_logged, login)
VALUES ('2', 'Jan', 'Kosa', 'jan@kosa.pl', 'pass2', '1', 'log_2');