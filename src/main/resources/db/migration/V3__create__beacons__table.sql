CREATE TABLE beacons (
     id BIGINT AUTO_INCREMENT PRIMARY KEY,
     beacon_code VARCHAR(36) NOT NULL, -- substitui RAW(36)
     beacon_status VARCHAR(50),
     motorcycle_id BIGINT,
     CONSTRAINT fk_beacon_motorcycle FOREIGN KEY (motorcycle_id)
     REFERENCES motorcycles(id)
);