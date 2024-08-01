CREATE TABLE t_health
(
    id          BIGSERIAL PRIMARY KEY,
    update_date TIMESTAMP,
    status      VARCHAR(255)
);
CREATE TABLE t_cage
(
    id           BIGSERIAL PRIMARY KEY,
    availability VARCHAR(255),
    cage_number  VARCHAR(255)
);
CREATE TABLE t_role
(
    id          BIGSERIAL PRIMARY KEY,
    description VARCHAR(255),
    name        VARCHAR(255)
);
CREATE TABLE t_user
(
    id           BIGSERIAL PRIMARY KEY,
    email        VARCHAR(255),
    first_name   VARCHAR(255),
    last_name    VARCHAR(255),
    password     VARCHAR(255),
    phone_number VARCHAR(255)
);
CREATE TABLE t_animal
(
    id        BIGSERIAL PRIMARY KEY,
    age       INTEGER,
    cage_id   BIGINT,
    health_id BIGINT,
    name      VARCHAR(255),
    species   VARCHAR(255),
    FOREIGN KEY (cage_id) REFERENCES t_cage (id),
    FOREIGN KEY (health_id) REFERENCES t_health (id)
);
CREATE TABLE t_user_animal
(
    id         BIGSERIAL PRIMARY KEY,
    animals_id BIGINT,
    users_id   BIGINT,
    FOREIGN KEY (animals_id) REFERENCES t_animal (id),
    FOREIGN KEY (users_id) REFERENCES t_user (id)
);
CREATE TABLE t_user_role
(
    id       BIGSERIAL PRIMARY KEY,
    roles_id BIGINT,
    users_id BIGINT,
    FOREIGN KEY (roles_id) REFERENCES t_role (id),
    FOREIGN KEY (users_id) REFERENCES t_user (id)
);
