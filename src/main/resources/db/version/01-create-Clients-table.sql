create table clients
(
    client_id BIGSERIAL PRIMARY KEY,
    full_name    varchar(255),
    phone       varchar(255),
    birthday    DATE,
    message_send BOOLEAN

);
