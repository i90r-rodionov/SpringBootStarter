create table TEST_ENTITY
(
    id               UUID      not null,
    number           VARCHAR(100),
    data             VARCHAR(100),
    create_date      TIMESTAMP not null,
    last_change_date TIMESTAMP not null,
    version          BIGINT    not null,
    is_deleted       BOOL      not null,

    constraint PK_ORDER primary key (id)
);