CREATE TABLE parkings (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    street VARCHAR(100) NOT NULL,
    city VARCHAR(50) NOT NULL,
    state VARCHAR(50) NOT NULL,
    postal_code VARCHAR(20) NOT NULL,
    complement VARCHAR(20),
    available_area BIGINT NOT NULL,
    capacity BIGINT NOT NULL,
    structurePlan VARCHAR(500) NOT NULL,
    floorPlan VARCHAR(500) NOT NULL,
    mapPlan CLOB NOT NULL
);