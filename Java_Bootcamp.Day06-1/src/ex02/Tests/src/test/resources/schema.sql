DROP TABLE IF EXISTS Product CASCADE;
CREATE TABLE Product
(
    id      INTEGER PRIMARY KEY NOT NULL,
    name    varchar,
    price   INTEGER
);