insert into address_entity (id, city, number, street) values (1, 'st. 1', 22, 'Ariana');
insert into person (id, address_id, date_of_birth, person_name) values (1, 1, '1994-05-04', 'pers1');
insert into telephone_number_entity (id, number, operator, person_id) values (1, '99999999', 'TunisieTelecom', 1);
insert into telephone_number_entity (id, number, operator, person_id) values (2, '55555555', 'Ooreddo', 1);
insert into games_entity (id, title, type) values (1, 'Fifa2020', 'football');
insert into played_by (games_id, persons_id) values (1, 1);
insert into games_entity (id, title, type) values (2, 'POP', 'advanture');
insert into played_by (games_id, persons_id) values (2, 1);
insert into address_entity (id, city, number, street) values (2, 'st. 2', 44, 'Tunis');
insert into person (id, address_id, date_of_birth, person_name) values (2, 2, '1998-10-08', 'pers2');
insert into telephone_number_entity (id, number, operator, person_id) values (3, '54444444', 'Orange', 2);
insert into telephone_number_entity (id, number, operator, person_id) values (4, '22222222', 'Ooreddo', 2);
insert into games_entity (id, title, type) values (3, 'GOD', 'advanture');
insert into played_by (games_id, persons_id) values (2, 2);
insert into played_by (games_id, persons_id) values (3, 2);
insert into address_entity (id, city, number, street) values (3, 'st. 3', 66, 'Manouba');
insert into person (id, address_id, date_of_birth, person_name) values (3, 3, '1999-11-28', 'pers3');
insert into telephone_number_entity (id, number, operator, person_id) values (5, '53333333', 'Orange', 3);
insert into telephone_number_entity (id, number, operator, person_id) values (6, '55333333', 'Orange', 3);
insert into games_entity (id, title, type) values (4, 'PES2020', 'football');
insert into games_entity (id, title, type) values (5, 'Room', 'puzzle');
insert into played_by (games_id, persons_id) values (1, 3);
insert into played_by (games_id, persons_id) values (4, 3);
insert into played_by (games_id, persons_id) values (5, 3);
insert into played_by (games_id, persons_id) values (3, 3);