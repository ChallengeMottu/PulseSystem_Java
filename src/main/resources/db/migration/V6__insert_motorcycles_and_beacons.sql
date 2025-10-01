INSERT INTO beacons (beacon_code, beacon_status)
VALUES ('123e4567-e89b-12d3-a456-426614174000', 'ATIVO');

INSERT INTO beacons (beacon_code, beacon_status)
VALUES ('223e4567-e89b-12d3-a456-426614174001', 'INATIVO');

INSERT INTO beacons (beacon_code, beacon_status)
VALUES ('323e4567-e89b-12d3-a456-426614174002', 'ATIVO');

INSERT INTO beacons (beacon_code, beacon_status)
VALUES ('423e4567-e89b-12d3-a456-426614174003', 'INATIVO');


INSERT INTO motorcycles (license_plate, model, chassis_number, operational_status, mechanical_condition, parking_id)
VALUES ('ABC1D23', 'SPORT_110i', '9C2PC1230JR000001', 'EM_USO', 'Ótima', (SELECT MAX(id) FROM parkings));

INSERT INTO motorcycles (license_plate, model, chassis_number, operational_status, mechanical_condition, parking_id)
VALUES ('DEF2E34', 'MOTTU_E', '9C2PC1230JR000002', 'DISPONIVEL', 'Regular', (SELECT MAX(id) FROM parkings));

INSERT INTO motorcycles (license_plate, model, chassis_number, operational_status, mechanical_condition, parking_id)
VALUES ('GHI3F45', 'SPORT_ESD', '9C2PC1230JR000003', 'EM_USO', 'Boa', (SELECT MAX(id) FROM parkings));

INSERT INTO motorcycles (license_plate, model, chassis_number, operational_status, mechanical_condition, parking_id)
VALUES ('JKL4G56', 'POP_110i', '9C2PC1230JR000004', 'EM_MANUTENCAO', 'Ruim', (SELECT MAX(id) FROM parkings));
