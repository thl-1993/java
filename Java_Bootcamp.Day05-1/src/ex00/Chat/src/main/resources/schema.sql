-- Удаление схемы, если она существует, и каскадное удаление всех объектов
DROP SCHEMA IF EXISTS chat CASCADE;

-- Создание схемы chat
CREATE SCHEMA IF NOT EXISTS chat;

-- Таблица пользователей (User)
CREATE TABLE IF NOT EXISTS chat.User
(
    id BIGSERIAL PRIMARY KEY, -- Уникальный идентификатор пользователя
    login VARCHAR(255) NOT NULL UNIQUE, -- Логин пользователя
    password VARCHAR(255) NOT NULL -- Пароль пользователя
);

-- Таблица комнат чата (Chatroom)
CREATE TABLE IF NOT EXISTS chat.Chatroom
(
    id BIGSERIAL PRIMARY KEY, -- Уникальный идентификатор комнаты
    name VARCHAR(255) NOT NULL, -- Название комнаты
    owner BIGINT NOT NULL, -- Владелец комнаты (ссылка на пользователя)
    CONSTRAINT fk_owner FOREIGN KEY (owner) REFERENCES chat.User(id) -- Внешний ключ на таблицу пользователей
);

-- Таблица для связи пользователей и комнат чата (многие ко многим)
CREATE TABLE IF NOT EXISTS chat.User_Chatroom
(
    user_id BIGINT NOT NULL, -- Пользователь (ссылка на таблицу пользователей)
    chatroom_id BIGINT NOT NULL, -- Комната чата (ссылка на таблицу комнат)
    PRIMARY KEY (user_id, chatroom_id), -- Уникальная комбинация пользователя и комнаты
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES chat.User(id), -- Внешний ключ на таблицу пользователей
    CONSTRAINT fk_chatroom FOREIGN KEY (chatroom_id) REFERENCES chat.Chatroom(id) ON DELETE CASCADE -- Внешний ключ на таблицу комнат чата
);

-- Таблица сообщений (Message)
CREATE TABLE IF NOT EXISTS chat.Message
(
    id BIGSERIAL PRIMARY KEY, -- Уникальный идентификатор сообщения
    author BIGINT, -- Автор сообщения (ссылка на пользователя, может быть NULL)
    room BIGINT, -- Комната чата (ссылка на комнату, может быть NULL)
    text VARCHAR(255), -- Текст сообщения (может быть NULL)
    date_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Дата и время отправки сообщения (может быть NULL)
    CONSTRAINT fk_author FOREIGN KEY (author) REFERENCES chat.User(id), -- Внешний ключ на таблицу пользователей
    CONSTRAINT fk_room FOREIGN KEY (room) REFERENCES chat.Chatroom(id) ON DELETE CASCADE -- Внешний ключ на таблицу комнат чата
);