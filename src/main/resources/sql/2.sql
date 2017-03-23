USE scientists;

INSERT INTO degree (d_name) VALUES ('NONE');
INSERT INTO degree (d_name) VALUES ('BACHELOR');
INSERT INTO degree (d_name) VALUES ('MASTER');
INSERT INTO degree (d_name) VALUES ('SPECIALIST');

INSERT INTO gender (g_name) VALUES ('NONE');
INSERT INTO gender (g_name) VALUES ('MALE');
INSERT INTO gender (g_name) VALUES ('FEMALE');

INSERT INTO field (f_name) VALUES ('NONE');
INSERT INTO field (f_name) VALUES ('ARCHITECTURE');
INSERT INTO field (f_name) VALUES ('BUSINESS_ADMINISTRATION');
INSERT INTO field (f_name) VALUES ('DESIGN');
INSERT INTO field (f_name) VALUES ('IT');
INSERT INTO field (f_name) VALUES ('HISTORY');
INSERT INTO field (f_name) VALUES ('LITERATURE');
INSERT INTO field (f_name) VALUES ('MEDICINE');
INSERT INTO field (f_name) VALUES ('EARTH_SCIENCES');
INSERT INTO field (f_name) VALUES ('ENVIRONMENTAL_SCIENCES');
INSERT INTO field (f_name) VALUES ('PEDAGOGICAL_SCIENCES');
INSERT INTO field (f_name) VALUES ('PSYCHOLOGY');
INSERT INTO field (f_name) VALUES ('PHILOSOPHY');
INSERT INTO field (f_name) VALUES ('JURISPRUDENCE');
INSERT INTO field (f_name) VALUES ('ASTRONOMY');
INSERT INTO field (f_name) VALUES ('BIOLOGY');
INSERT INTO field (f_name) VALUES ('ENGINEERING');
INSERT INTO field (f_name) VALUES ('ARTS');
INSERT INTO field (f_name) VALUES ('LINGUISTICS');
INSERT INTO field (f_name) VALUES ('MATHEMATICS');
INSERT INTO field (f_name) VALUES ('MANAGEMENT');
INSERT INTO field (f_name) VALUES ('MATERIALS_SCIENCE');
INSERT INTO field (f_name) VALUES ('SOCIAL_SCIENCES');
INSERT INTO field (f_name) VALUES ('POLITICS');
INSERT INTO field (f_name) VALUES ('PHYSICS');
INSERT INTO field (f_name) VALUES ('CHEMISTRY');
INSERT INTO field (f_name) VALUES ('ELECTRICAL_AND_ELECTRONICS');
INSERT INTO field (f_name) VALUES ('ECONOMICS');

INSERT INTO university (u_scientist_id, u_country, u_city, u_full_name, u_degree_id, u_graduation_year)
VALUES (1,
        'Россия',
        'Санкт-Петербург',
        'СПбГЭТУ',
        2,
        2013);

INSERT INTO university (u_scientist_id, u_country, u_city, u_full_name, u_degree_id, u_graduation_year)
VALUES (1,
        'Россия',
        'Санкт-Петербург',
        'СПбГЭТУ',
        3,
        2012);

INSERT INTO university (u_scientist_id, u_country, u_city, u_full_name, u_degree_id, u_graduation_year)
VALUES (1,
        'Россия',
        'Москва',
        'МГУ',
        4,
        2014);

INSERT INTO scientist (s_email, s_password, s_first_name, s_second_name, s_middle_name, s_dob, s_gender_id, s_field_id)
  VALUES ('ao.andronov@gmail.com',
        '202cb962ac59075b964b07152d234b70',
        'Андрей',
        'Андронов',
        'Олегович',
        '1992-12-15',
        2,
        13);

INSERT INTO roles (s_email, r_name) VALUES ('ao.andronov@gmail.com', 'user');

INSERT INTO university (u_scientist_id, u_country, u_city, u_full_name, u_degree_id, u_graduation_year)
VALUES (2,
        'USA',
        'Boston',
        'Harvard',
        3,
        2015);

INSERT INTO scientist (s_email, s_password, s_first_name, s_second_name, s_dob, s_gender_id, s_field_id)
VALUES ('kkad@mail.ru',
        '202cb962ac59075b964b07152d234b70',
        'Людмила',
        'Попова',
        '1993-10-27',
        3,
        11);

INSERT INTO roles (s_email, r_name) VALUES ('kkad@mail.ru', 'user');

INSERT INTO scientist (s_email, s_password, s_first_name, s_second_name, s_middle_name, s_dob, s_gender_id, s_field_id)
VALUES ('andrey.andronov92@gmail.com',
        '202cb962ac59075b964b07152d234b70',
        'Андрей',
        'Андронов',
        'Олегович',
        '1993-11-02',
        2,
        7);

INSERT INTO roles (s_email, r_name) VALUES ('andrey.andronov92@gmail.com', 'user');

INSERT INTO messages (m_from, m_to, m_text, m_datetime)
VALUES ('ao.andronov@gmail.com',
        'andrey.andronov92@gmail.com',
        'MESSAGE',
        '2017-03-19 23:59:30.001');

INSERT INTO messages (m_from, m_to, m_text, m_datetime)
VALUES ('ao.andronov@gmail.com',
        'kkad@mail.ru',
        'MESSAGE',
        '2017-03-19 23:59:30.002');

INSERT INTO messages (m_from, m_to, m_text, m_datetime)
VALUES ('ao.andronov@gmail.com',
        'kkad@mail.ru',
        'MESSAGE',
        '2017-03-19 23:59:30.000');
