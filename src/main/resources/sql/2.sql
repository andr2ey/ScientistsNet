INSERT INTO Degree (name) VALUES ('NONE');
INSERT INTO Degree (name) VALUES ('BACHELOR');
INSERT INTO Degree (name) VALUES ('MASTER');
INSERT INTO Degree (name) VALUES ('SPECIALIST');

INSERT INTO Gender (name) VALUES ('MALE');
INSERT INTO Gender (name) VALUES ('FEMALE');

INSERT INTO University (country, city, full_name, degree_id)
VALUES ('Russia',
        'Saint-Petersburg',
        'Saint-Petersburg Electrotechnical University',
        2);

INSERT INTO Scientist (email, password, first_name, second_name, middle_name, birthday, university_id)
  VALUES ('ao.andronov@gmail.com',
        '123',
        'Andrey',
        'Andronov',
        'Olegovich',
        '1989- 03 - 04',
        1);