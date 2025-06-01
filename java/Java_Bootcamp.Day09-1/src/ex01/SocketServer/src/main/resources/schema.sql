-- Создание схемы chat
CREATE SCHEMA IF NOT EXISTS chat;

-- Таблица пользователей (User)
CREATE TABLE IF NOT EXISTS chat.users
(
    id BIGSERIAL PRIMARY KEY, -- Уникальный идентификатор пользователя
    username VARCHAR(50) NOT NULL UNIQUE, -- Логин пользователя
    password VARCHAR(255) NOT NULL -- Пароль пользователя
);

-- Таблица сообщений (Message)
CREATE TABLE IF NOT EXISTS chat.messages
(
    id BIGSERIAL PRIMARY KEY, -- Уникальный идентификатор сообщения
    sender VARCHAR(255), -- Автор сообщения (ссылка на пользователя)
    text text NOT NULL, -- Текст сообщения
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL-- Дата и время отправки сообщения
);

-- Добавление индексов для ускорения работы с внешними ключами
CREATE INDEX IF NOT EXISTS idx_messages_sender ON chat.messages(sender);