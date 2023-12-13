INSERT INTO rol (name) VALUES ('user'), ('seller'), ('delivery man'), ('admin');

INSERT INTO category (name) VALUES ('electronics'),
                                   ('clothes'),
                                   ('footwear'),
                                   ('health and beauty'),
                                   ('home and kitchen'),
                                   ('sports'),
                                   ('books'),
                                   ('toys'),
                                   ('jewelry');
/*
Johan Johan315*
Ana Ana123*
Carlos Carlos456*
Laura Laura789*
David David012*
*/
INSERT INTO users(name, lastname, email, password, phone, status, id_rol)
VALUES
    ('Johan', 'Gonzalez', 'johan@gmail.com', '$2a$10$zgD.W.5UVLlHtu3lJ1LxJOrl15NpvR87cU2q9e.RBvrUvdWmLlUmW', '7773002068', 1, 1),
    ('Ana', 'Lopez', 'ana@gmail.com', '$2a$10$MTNZdRXkDrpNtMBQWZNDu.4LZa2XOe0dM0TMsOgNPsjDwUawcMzPe', '7773002069', 1, 3),
    ('Carlos', 'Perez', 'carlos@gmail.com', '$2a$10$qc.5Sh3j4MPFoqDmz/BySOp/yF9P0ubSz9CiaxzoT1yIId6GAnHzW', '7773002070', 1, 2),
    ('Laura', 'Ramirez', 'laura@gmail.com', '$2a$10$JaADoXa3F6iGxHWcRdX3e.4oewOl/k03NUoo.VMwZwEZj23.gJDr2', '7773002071', 1, 3),
    ('David', 'Martinez', 'david@gmail.com', '$2a$10$Lly4eq/KqTH.6d3ncVDEqemXe6UmQRlKc7Nk8i.1O5Nc6r89i4ZEG', '7773002072', 1, 2),
    ('Tania', 'Belen', 'tania@gmail.com', '$2a$10$JaADoXa3F6iGxHWcRdX3e.4oewOl/k03NUoo.VMwZwEZj23.gJDr2', '7773002072', 1, 3),
    ('Samuel', 'Cano', 'samuel@gmail.com', '$2a$10$JaADoXa3F6iGxHWcRdX3e.4oewOl/k03NUoo.VMwZwEZj23.gJDr2', '7773002073', 1, 3),
    ('Rafael', 'Gonzalez', 'rafael@gmail.com', '$2a$10$JaADoXa3F6iGxHWcRdX3e.4oewOl/k03NUoo.VMwZwEZj23.gJDr2', '7773002074', 1, 3),
    ('Hector', 'Oragon', 'hector@gmail.com', '$2a$10$JaADoXa3F6iGxHWcRdX3e.4oewOl/k03NUoo.VMwZwEZj23.gJDr2', '7773002075', 1, 3);

INSERT INTO delivery_man(available, license_photo, id_user)
VALUES
    (1, 'https://s3-image-license', 6),
    (1, 'https://s3-image-license', 7),
    (1, 'https://s3-image-license', 8),
    (1, 'https://s3-image-license', 9);



INSERT INTO seller_identity(ine_link, rating, rfc, shop_type, status, id_user)
VALUES ('https://inefoto.com', 4.3, 'DAVJ1123456HEH343', 'Sports shop', 1, 5), ('https://inefoto.com', 4.0, 'GOAJ1123456HEH343', 'Supermarket', 1, 2);

INSERT INTO product(name, description, price, quantity_available, quantity_sold, tags, id_category, id_seller, rating)
VALUES
    ('iPhone 14 Pro Max', 'iPhone reacondicionado con una condición excelente, garantizado.', 12000.00, 20, 10, 'iphone, apple, reacondicionado', 1, 1, 0.0),
    ('Samsung Galaxy S21 Ultra', 'Nuevo Samsung Galaxy con cámara de 108 MP y zoom de 100x.', 11000.00, 15, 2, 'samsung, android, smartphone', 1, 2, 0.0),
    ('MacBook Pro 2021', 'Potente computadora portátil con procesador M1 y pantalla Retina.', 18000.00, 12, 0, 'macbook, apple, laptop', 1, 1, 0.0),
    ('PlayStation 5', 'Consola de última generación con gráficos de alta calidad y 4K.', 8000.00, 25, 10, 'playstation, videojuegos, consola', 3, 2, 0.0),
    ('Nike Air Zoom Pegasus 38', 'Zapatillas de running con tecnología de amortiguación React.', 1500.00, 30, 0, 'nike, zapatillas, deporte', 2, 1, 0.0),
    ('Canon EOS R5', 'Cámara mirrorless con resolución de 45 MP y grabación de video en 8K.', 22000.00, 8, 1, 'canon, cámara, fotografía', 4, 2, 0.0),
    ('Smart TV LG OLED 4K', 'Televisor con tecnología OLED y resolución 4K para una experiencia de visualización inmersiva.', 9000.00, 18, 0, 'lg, televisión, smart tv', 1, 1, 0.0),
    ('FIFA 22 - Edición Deluxe', 'Videojuego de fútbol con nuevas características y gráficos mejorados.', 2500.00, 40, 30, 'fifa, videojuego, deportes', 3, 2, 0.0),
    ('Fitbit Versa 3', 'Smartwatch con seguimiento avanzado de actividad física y monitoreo de salud.', 1200.00, 22, 0, 'fitbit, smartwatch, salud', 2, 1, 0.0),
    ('Bose QuietComfort 45', 'Audífonos inalámbricos con cancelación de ruido y sonido de alta calidad.', 2800.00, 10, 0, 'bose, audífonos, música', 1, 2, 0.0),
    ('GoPro Hero 10 Black', 'Cámara de acción con capacidad para grabar videos en 5.3K y capturar fotos de alta resolución.', 4500.00, 15, 0, 'gopro, cámara, acción', 4, 1, 0.0);

INSERT INTO order_item (id_user, created_at, status, sub_total, date_of_delivery)
VALUES
    (1, NOW(), 'Pendiente', 25000.00, DATE_ADD(NOW(), INTERVAL 1 DAY)),
    (2, NOW(), 'Pendiente', 18000.00, DATE_ADD(NOW(), INTERVAL 1 DAY)),
    (3, NOW(), 'Pendiente', 8000.00, DATE_ADD(NOW(), INTERVAL 1 DAY));

INSERT INTO order_item_product (id_user, id_product, amount, id_order_items)
VALUES
    (1, 1, 2, 1),
    (1, 3, 1, 1),
    (2, 2, 1, 2),
    (2, 4, 2, 2),
    (3, 5, 3, 3),
    (3, 6, 1, 3);
