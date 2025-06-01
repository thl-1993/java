-- Вставка тестовых данных в таблицу пользователей (User)
INSERT INTO chat.User (login, password) VALUES
('user1', 'password1'),
('user2', 'password2'),
('user3', 'password3'),
('user4', 'password4'),
('user5', 'password5');

-- Вставка тестовых данных в таблицу комнат чата (Chatroom)
INSERT INTO chat.Chatroom (name, owner) VALUES
('Chatroom 1', 1), -- Владелец user1
('Chatroom 2', 2), -- Владелец user2
('Chatroom 3', 3); -- Владелец user3

-- Вставка данных в таблицу для связи пользователей и комнат чата (User_Chatroom)
INSERT INTO chat.User_Chatroom (user_id, chatroom_id) VALUES
(1, 1), -- user1 в Chatroom 1
(2, 1), -- user2 в Chatroom 1
(1, 2), -- user1 в Chatroom 2
(3, 2), -- user3 в Chatroom 2
(4, 3), -- user4 в Chatroom 3
(5, 3); -- user5 в Chatroom 3

-- Вставка тестовых данных в таблицу сообщений (Message)
INSERT INTO chat.Message (author, room, text, date_time) VALUES
(1, 1, 'Hello everyone!', NOW()), -- Сообщение от user1 в Chatroom 1
(2, 1, 'Hi user1!', NOW()), -- Сообщение от user2 в Chatroom 1
(3, 2, 'Welcome to Chatroom 2!', NOW()), -- Сообщение от user3 в Chatroom 2
(1, 2, 'Thank you, user3!', NOW()), -- Сообщение от user1 в Chatroom 2
(4, 3, 'Good evening!', NOW()), -- Сообщение от user4 в Chatroom 3
(5, 3, 'How is everyone doing?', NOW()); -- Сообщение от user5 в Chatroom 3