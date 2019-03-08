
insert into users (id,email,password) values (1, 'email@t.com', '$2a$10$VEjxo0jq2YG9Rbk2HmX9S.k1uZBGYUHdUcid3g/vfiEl7lwWgOH/K');

insert into authority (name ) values ('ROLE_USER');

insert into users_authority (user_id,authority) values (1,'ROLE_USER');

insert into meetup (id, name, address, start_date, end_date,  description, speaker, status)
values (1, 'Java for sceptics', 'First Address Place', '2019-01-19 21:05:13', '2019-01-19 22:05:13', 'This is first meetup.', 'First speaker', 'NEW');

insert into meetup (id, name, address, start_date, end_date,  description, speaker, status)
values (2, 'Java approach in Chemistry', 'Second Address Place', '2019-01-20 21:05:13', '2019-01-20 22:05:13', 'This is second meetup.', 'Second speaker', 'IN_PROGRESS');