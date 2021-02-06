create table flats
(
    id      varchar(255) not null
        constraint flats_pkey primary key,
    active  varchar(255),
    created timestamp,
    updated timestamp,
    flat    smallint,
    floor   smallint,
    housing smallint,
    section smallint
);