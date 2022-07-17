create table IDEMPOTENT_RECORD_2
(
    id               UUID         not null,
    idempotent_key   VARCHAR(255) not null constraint idempotent_record_key unique,
    request_type     VARCHAR(255) not null,
    request_object   TEXT         not null,
    response_type    VARCHAR(255),
    response_object  TEXT,

    create_date      TIMESTAMP    not null,
    last_change_date TIMESTAMP    not null,
    version          BIGINT       not null,
    is_deleted       BOOL         not null,

    constraint pk_idempotent_record_2 primary key (id)
);