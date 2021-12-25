-- ROLE
INSERT INTO ROLE (ROLE_NAME)
VALUES ('ROLE_ADMIN'),
       ('ROLE_USER');

-- USER
INSERT INTO USER (FIRST_NAME, LAST_NAME, USERNAME, EMAIL, PASSWORD, ACTIVE, ROLE_ID)
VALUES ('djamel', 'korei', 'admin', 'admin@admin.com', '$2a$10$8UXhGyTthdNY3ayoMeyDb.l5R5iXlekMaWpwtqn87c0ZiA8fRaIqm',
        true, 1),
       ('john', 'doe', 'user', 'djamel.korei@gmail.com', '$2a$10$8UXhGyTthdNY3ayoMeyDb.l5R5iXlekMaWpwtqn87c0ZiA8fRaIqm',
        true, 2);
