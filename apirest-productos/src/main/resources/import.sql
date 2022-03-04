INSERT INTO productos(codigo, tipo, cantidad, precio, marca, fecha_ingreso, descripcion) VALUES ('abcdef','comida',3,12.50,'Cuetara','2010-08-12','Galletas para desayunar');
INSERT INTO productos(codigo, tipo, cantidad, precio, marca, fecha_ingreso, descripcion) VALUES ('uiwjks','vehiculo',2,18500,'Renault','2008-07-02','Vehiculos de ocasion');
INSERT INTO productos(codigo, tipo, cantidad, precio, marca, fecha_ingreso, descripcion) VALUES ('eeekkk','juguete',6,45.95,'Barbie','2010-11-05','Muñecas de barbie');
INSERT INTO productos(codigo, tipo, cantidad, precio, marca, fecha_ingreso, descripcion) VALUES ('llowwl','comida',2,13.75,'Gallo','2021-03-09','Diferentes platos de pasta');
INSERT INTO productos(codigo, tipo, cantidad, precio, marca, fecha_ingreso, descripcion) VALUES ('jajaja','vehiculo',5,4600,'Suzuki','2010-10-02','Motocicleta de segunda mano');

INSERT INTO usuarios (username, password, enabled) VALUES ('juan', '$2a$10$WVh4dBD7WxPXBd82ZCxHNOmd/wemrdxfzvK7.Rwzbqh0QaoUrQwdC', 1);
INSERT INTO usuarios (username, password, enabled) VALUES ('admin', '$2a$10$qXUacjhDtl4Gh.PdC28c6.vljAjg./kjrWb.v3xuG5VtOJHxmguGe', 1);
INSERT INTO usuarios (username, password, enabled) VALUES ('pepe', '$2a$10$Wkoc/2cuB1nQzC3BKHHZUesuKkP/B9G.5yT5ZVDP1HreevGjTibbq', 1);

INSERT INTO roles (nombre) VALUES ('ROLE_USER');
INSERT INTO roles (nombre) VALUES ('ROLE_ADMIN');

INSERT INTO usuarios_roles ('usuario_id', 'role_id') VALUES (1, 1);
INSERT INTO usuarios_roles ('usuario_id', 'role_id') VALUES (2, 2);
INSERT INTO usuarios_roles ('usuario_id', 'role_id') VALUES (2, 1);
