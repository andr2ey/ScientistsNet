INSERT INTO degree (d_name) VALUES ('NONE');
INSERT INTO degree (d_name) VALUES ('BACHELOR');
INSERT INTO degree (d_name) VALUES ('MASTER');
INSERT INTO degree (d_name) VALUES ('SPECIALIST');

INSERT INTO Gender (g_name) VALUES ('OTHER');
INSERT INTO Gender (g_name) VALUES ('MALE');
INSERT INTO Gender (g_name) VALUES ('FEMALE');
INSERT INTO Gender (g_name) VALUES ('NONE');

INSERT INTO university (u_scientist_id, u_country, u_city, u_full_name, u_degree_id)
VALUES (1,
        'Russia',
        'Saint-Petersburg',
        'Saint-Petersburg Electrotechnical University',
        2);

INSERT INTO scientist (s_email, s_password, s_first_name, s_second_name, s_middle_name, s_dob, s_gender_id)
  VALUES ('ao.andronov@gmail.com',
        '123',
        'Andrey',
        'Andronov',
        'Olegovich',
        '1989-03-04',
        2);

INSERT INTO roles (s_email, r_name) VALUES ('ao.andronov@gmail.com', 'user');

INSERT INTO university (u_scientist_id, u_country, u_city, u_full_name, u_degree_id)
VALUES (2,
        'USA',
        'Boston',
        'Harvard',
        3);

INSERT INTO scientist (s_email, s_password, s_first_name, s_second_name, s_middle_name, s_dob, s_gender_id)
VALUES ('kkad@mail.ru',
        '123',
        'Luidmila',
        'Popova',
        'Vadimovna',
        '1993-03-04',
        3);

INSERT INTO roles (s_email, r_name) VALUES ('kkad@mail.ru', 'user');