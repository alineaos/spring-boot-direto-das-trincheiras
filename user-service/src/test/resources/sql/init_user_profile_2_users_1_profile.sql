insert into user (id, email, first_name, last_name) values (1, 'rin.okumura@gmail.com','Rin','Okumura');
insert into user (id, email, first_name, last_name) values (2, 'yukio.okumura@gmail.com','Yukio','Okumura');
insert into profile (id, name, description) values (1, 'Admin','Manages everything');
insert into user_profile ( id, user_id, profile_id) values (1,1,1);
insert into user_profile ( id, user_id, profile_id) values (2,2,1);