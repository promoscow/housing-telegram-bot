create table chat_users
(
    id               varchar(255) not null
        constraint chat_users_pkey
            primary key,
    active           varchar(255),
    created          timestamp,
    updated          timestamp,
    first_name       varchar(255),
    interaction_step varchar(255),
    last_name        varchar(255),
    registered       boolean,
    telegram_id      bigint,
    user_name        varchar(255),
    flat_id          varchar(255)
        constraint fkphbekinsubbhrfqv073xvrrdr
            references flats
);

create index telegram_id_idx
    on chat_users (telegram_id);