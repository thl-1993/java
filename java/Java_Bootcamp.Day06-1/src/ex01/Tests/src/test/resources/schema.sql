DROP TABLE IF EXISTS Product CASCADE;
CREATE TABLE Product
(
    id      INTEGER IDENTITY PRIMARY KEY NOT NULL,
    name    varchar,
    price   decimal
);