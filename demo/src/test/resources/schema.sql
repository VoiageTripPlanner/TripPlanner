CREATE TABLE ROLE (
                     id INT AUTO_INCREMENT PRIMARY KEY,
                     name VARCHAR(255),
                     description VARCHAR(255),
                     created_at TIMESTAMP,
                     updated_at TIMESTAMP
);

CREATE TABLE "USER" (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      created_at TIMESTAMP,
                      email VARCHAR(255) NOT NULL,
                      lastname VARCHAR(255),
                      name VARCHAR(255),
                      password VARCHAR(255),
                      role_id BIGINT,
                      updated_at TIMESTAMP
);

