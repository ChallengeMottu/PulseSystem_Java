CREATE TABLE motorcycles (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    license_plate VARCHAR(10) NOT NULL UNIQUE,
    model VARCHAR(50),
    chassis_number VARCHAR(100),
    operational_status VARCHAR(50),
    mechanical_condition VARCHAR(50),
    parking_id BIGINT NOT NULL,
    CONSTRAINT fk_motorcycles_parkings FOREIGN KEY (parking_id)
           REFERENCES parkings(id)
);