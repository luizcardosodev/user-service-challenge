CREATE TABLE IF NOT EXISTS tb_department (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS tb_user (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    deleted BOOLEAN NOT NULL DEFAULT false,
    department_id BIGINT NOT NULL,
    FOREIGN KEY (department_id) REFERENCES tb_department(id)
);