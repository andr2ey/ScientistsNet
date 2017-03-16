USE scientists;

INSERT INTO degree (d_name) VALUES ('NONE');
INSERT INTO degree (d_name) VALUES ('BACHELOR');
INSERT INTO degree (d_name) VALUES ('MASTER');
INSERT INTO degree (d_name) VALUES ('SPECIALIST');

INSERT INTO gender (g_name) VALUES ('MALE');
INSERT INTO gender (g_name) VALUES ('FEMALE');
INSERT INTO gender (g_name) VALUES ('OTHER');
INSERT INTO gender (g_name) VALUES ('NONE');

INSERT INTO university (u_scientist_id, u_country, u_city, u_full_name, u_degree_id)
VALUES (1,
        'Россия',
        'Санкт-Петербург',
        'СПбГЭТУ',
        2);

INSERT INTO university (u_scientist_id, u_country, u_city, u_full_name, u_degree_id)
VALUES (1,
        'Россия',
        'Санкт-Петербург',
        'СПбГЭТУ',
        3);

INSERT INTO university (u_scientist_id, u_country, u_city, u_full_name, u_degree_id)
VALUES (1,
        'Россия',
        'Москва',
        'МГУ',
        4);

INSERT INTO scientist (s_email, s_password, s_first_name, s_second_name, s_middle_name, s_dob, s_gender_id)
  VALUES ('ao.andronov@gmail.com',
        '123',
        'Андрей',
        'Андронов',
        'Олегович',
        '1992-12-15',
        1);

INSERT INTO roles (s_email, r_name) VALUES ('ao.andronov@gmail.com', 'user');

INSERT INTO university (u_scientist_id, u_country, u_city, u_full_name, u_degree_id)
VALUES (2,
        'USA',
        'Boston',
        'Harvard',
        3);

INSERT INTO scientist (s_email, s_password, s_first_name, s_second_name, s_dob, s_gender_id)
VALUES ('kkad@mail.ru',
        '123',
        'Людмила',
        'Попова',
        '1993-10-27',
        2);

INSERT INTO roles (s_email, r_name) VALUES ('kkad@mail.ru', 'user');