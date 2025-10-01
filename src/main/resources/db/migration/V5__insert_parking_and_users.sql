
INSERT INTO parkings (name, street, city, state, postal_code, complement, available_area, capacity)
VALUES ('Estacionamento Central', 'Rua das Flores', 'São Paulo', 'SP',
        '01001-000', 'Vila Olímpia', 5000, 500);


INSERT INTO employees (name, cpf, birth_date, email, role, password, parking_id)
VALUES (
           'Gabriela Sousa',
           '45986930214',
           PARSEDATETIME('1990-01-01','yyyy-MM-dd'),
           'admin@pulse.com',
           'GESTOR',
           '$2a$12$43GmIV2x1dun4jQgZsBSDOT8kNZomgY1UecO/6iit7j7Ei.HThOTq',
           (SELECT MAX(id) FROM parkings)
       );


INSERT INTO employees (name, cpf, birth_date, email, role, password, parking_id)
VALUES (
           'Amanda Perez',
           '52365896523',
           PARSEDATETIME('2000-02-15','yyyy-MM-dd'),
           'amanda.perez@pulse.com',
           'OPERADOR',
           '$2a$12$APdmkBphNyreUkOnt4qRwubmwYxYFPJ0EzIjtW5sCQO.hLcqqp66a',
           (SELECT MAX(id) FROM parkings)
       );


INSERT INTO employees (name, cpf, birth_date, email, role, password, parking_id)
VALUES (
           'Marcos Carvalho',
           '89652365897',
           PARSEDATETIME('2000-02-13','yyyy-MM-dd'),
           'marcos.carvalho@pulse.com',
           'MECANICO',
           '$2a$12$OGB35gIaOTN3XFiY4QMbluFL899rMEol0h/e.YKVptXacNlfP746q',
           (SELECT MAX(id) FROM parkings)
       );
