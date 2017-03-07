INSERT INTO Degree (name) VALUES ('NONE');
INSERT INTO Degree (name) VALUES ('BACHELOR');
INSERT INTO Degree (name) VALUES ('MASTER');
INSERT INTO Degree (name) VALUES ('SPECIALIST');

INSERT INTO University (country, city, full_name, degree_id)
VALUES ('Russia',
        'Saint-Petersburg',
        'Saint-Petersburg Electrotechnical University',
        1);

INSERT INTO Scientist (first_name, second_name, middle_name, email, password, birthday, university_id)
  VALUES ('Andrey',
        'Andronov',
        'Olegovich',
        'ao.andronov@gmail.com',
        '123',
        1989 - 03 - 04,
        1);