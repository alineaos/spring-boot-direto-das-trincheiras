INSERT INTO user_service.user (id, email, first_name, last_name, password, roles) VALUES (1, 'ichigo.kurosaki@gmail.com', 'Ichigo', 'Kurosaki', '{bcrypt}$2a$10$S.Tv461Aa9IFFYnYbMIdVeU1rxTbxJMXWmby4F47M/7nP0st3Ql8y', 'USER');
INSERT INTO user_service.user (id, email, first_name, last_name, password, roles) VALUES (7, 'eren.yeager@gmail.com', 'Eren', 'Yeager', '', 'USER');
INSERT INTO user_service.user (id, email, first_name, last_name, password, roles) VALUES (8, 'izuku.midoriya@gmail.com', 'Izuku', 'Midoriya', '', 'USER');
INSERT INTO user_service.user (id, email, first_name, last_name, password, roles) VALUES (12, 'okarun@dandadan.com', 'Ken', 'Takakura', '{bcrypt}$2a$10$MW/L3JGfWytzf7ABG8pqH.ltb72eIaVqP4ymegr25JszcYpRrkodK', 'ADMIN');
INSERT INTO user_service.profile (id, description, name) VALUES (1, 'Regular user with regular permissions', 'Regular User');
INSERT INTO user_service.profile (id, description, name) VALUES (2, 'Administrator', 'Admin');
INSERT INTO user_service.user_profile (id, profile_id, user_id) VALUES (1, 1, 1);
INSERT INTO user_service.user_profile (id, profile_id, user_id) VALUES (2, 1, 7);
INSERT INTO user_service.user_profile (id, profile_id, user_id) VALUES (3, 2, 8);