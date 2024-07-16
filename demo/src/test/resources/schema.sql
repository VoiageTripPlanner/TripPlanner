CREATE TABLE VO_USER_ROLE (
                              role_id INTEGER AUTO_INCREMENT PRIMARY KEY,
                              name VARCHAR(255) NOT NULL,
                              abbreviation VARCHAR(255) NOT NULL,
                              operational BOOLEAN NOT NULL,
                              creation_datetime DATE NOT NULL,
                              creation_responsible BIGINT NOT NULL,
                              last_update_datetime DATE,
                              update_responsible BIGINT
);

CREATE TABLE VO_CURRENCY(
                            currency_id INT AUTO_INCREMENT PRIMARY KEY,
                            currency_name VARCHAR(255) NOT NULL,
                            currency_code VARCHAR(2) NOT NULL,
                            currency_symbol VARCHAR(255) NOT NULL
);

CREATE TABLE VO_COUNTRY (
                            country_id INTEGER AUTO_INCREMENT PRIMARY KEY,
                            country_name VARCHAR(255),
                            country_code VARCHAR(255),
                            operational BOOLEAN NOT NULL,
                            country_currency_id INTEGER NOT NULL,
                            CONSTRAINT fk_currency FOREIGN KEY (country_currency_id) REFERENCES VO_CURRENCY(currency_id)
);

CREATE TABLE VO_User (
                         user_id INTEGER AUTO_INCREMENT PRIMARY KEY,
                         name VARCHAR(255) NOT NULL,
                         last_name VARCHAR(255) NOT NULL,
                         second_last_name VARCHAR(255),
                         country_id INT NOT NULL,
                         email VARCHAR(100) UNIQUE NOT NULL,
                         password VARCHAR(255) NOT NULL,
                         operational BOOLEAN NOT NULL,
                         created_at DATE NOT NULL,
                         creation_responsible BIGINT NOT NULL,
                         updated_at DATE,
                         update_responsible BIGINT,
                         role_id INTEGER NOT NULL,
                         CONSTRAINT fk_country FOREIGN KEY (country_id) REFERENCES VO_COUNTRY(country_id),
                         CONSTRAINT fk_role FOREIGN KEY (role_id) REFERENCES VO_USER_ROLE(role_id)
);
