CREATE TABLE employees (
     id BIGINT AUTO_INCREMENT PRIMARY KEY,
     name VARCHAR(100) NOT NULL,
     cpf VARCHAR(14) NOT NULL UNIQUE,
     birth_date DATE NOT NULL,
     email VARCHAR(100) NOT NULL UNIQUE,
     role VARCHAR(50) NOT NULL,
     password VARCHAR(200) NOT NULL,
     parking_id BIGINT NOT NULL,
     CONSTRAINT fk_employees_parkings FOREIGN KEY (parking_id)
          REFERENCES parkings(id) ON DELETE CASCADE
);