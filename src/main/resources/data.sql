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
    ('David', 'Martinez', 'david@gmail.com', '$2a$10$Lly4eq/KqTH.6d3ncVDEqemXe6UmQRlKc7Nk8i.1O5Nc6r89i4ZEG', '7773002072', 1, 2);

INSERT INTO seller_identity(ine_link, rating, rfc, shop_type, status, id_user)
VALUES ('https://inefoto.com', 4.3, 'DAVJ1123456HEH343', 'Sports shop', 1, 5), ('https://inefoto.com', 4.0, 'GOAJ1123456HEH343', 'Supermarket', 1, 2);

INSERT INTO product(name, description, price, quantity_available, quantity_sold, tags, id_category, id_seller)
VALUES
    ('iPhone 14 Pro Max', 'iPhone reacondicionado con una condición excelente, garantizado.', 12000.00, 20, 10, 'iphone, apple, reacondicionado', 1, 1),
    ('Samsung Galaxy S21 Ultra', 'Nuevo Samsung Galaxy con cámara de 108 MP y zoom de 100x.', 11000.00, 15, 2, 'samsung, android, smartphone', 1, 2),
    ('MacBook Pro 2021', 'Potente computadora portátil con procesador M1 y pantalla Retina.', 18000.00, 12, 0, 'macbook, apple, laptop', 1, 1),
    ('PlayStation 5', 'Consola de última generación con gráficos de alta calidad y 4K.', 8000.00, 25, 10, 'playstation, videojuegos, consola', 3, 2),
    ('Nike Air Zoom Pegasus 38', 'Zapatillas de running con tecnología de amortiguación React.', 1500.00, 30, 0, 'nike, zapatillas, deporte', 2, 1),
    ('Canon EOS R5', 'Cámara mirrorless con resolución de 45 MP y grabación de video en 8K.', 22000.00, 8, 1, 'canon, cámara, fotografía', 4, 2),
    ('Smart TV LG OLED 4K', 'Televisor con tecnología OLED y resolución 4K para una experiencia de visualización inmersiva.', 9000.00, 18, 0, 'lg, televisión, smart tv', 1, 1),
    ('FIFA 22 - Edición Deluxe', 'Videojuego de fútbol con nuevas características y gráficos mejorados.', 2500.00, 40, 30, 'fifa, videojuego, deportes', 3, 2),
    ('Fitbit Versa 3', 'Smartwatch con seguimiento avanzado de actividad física y monitoreo de salud.', 1200.00, 22, 0, 'fitbit, smartwatch, salud', 2, 1),
    ('Bose QuietComfort 45', 'Audífonos inalámbricos con cancelación de ruido y sonido de alta calidad.', 2800.00, 10, 0, 'bose, audífonos, música', 1, 2),
    ('GoPro Hero 10 Black', 'Cámara de acción con capacidad para grabar videos en 5.3K y capturar fotos de alta resolución.', 4500.00, 15, 0, 'gopro, cámara, acción', 4, 1);

INSERT INTO product_image_links(product_id_product, image_link)
VALUES (1,'https://ishopmx.vtexassets.com/arquivos/ids/219429-800-auto?v=637998065132630000&width=800&height=auto&aspect=true'),
       (1, 'https://www.apple.com/newsroom/images/product/iphone/geo/Apple-iPhone-14-Pro-iPhone-14-Pro-Max-gold-220907-geo_inline.jpg.large.jpg'),
       (1, 'https://powermaccenter.com/cdn/shop/files/iPhone_14_Pro_Max_Deep_Purple_PDP_Image_Position-1a__en-US.jpg?v=1689792112'),
       (2,'https://cdn1.coppel.com/images/catalog/mkp/1042/5000/10424224-1.jpg'),
       (2,'https://www.ktronix.com/medias/7707222704732-010-750Wx750H?context=bWFzdGVyfGltYWdlc3w1NDY5NnxpbWFnZS9qcGVnfGFXMWhaMlZ6TDJnNVlpOW9ZVFl2TVRBMU1UY3pNelF4TmpNME9EWXVhbkJufGJkYzVkZTgwOTA5NmJkYzRjMTc1MmE0ZDlhMjdkNTljMWUzNjY3ZmE5MzM1ZjIyNDM1MThkZDU3OWZkODY4YTg'),
       (2,'https://s-cf-my.shopeesz.com/file/24dec823af753fb7ed95c8f5df7be345'),
       (3, 'https://m.media-amazon.com/images/I/416sLDYqjrL._AC_UF894,1000_QL80_.jpg'),
       (3, 'https://m.media-amazon.com/images/I/416sLDYqjrL._AC_UF894,1000_QL80_.jpg'),
       (3, 'https://i.blogs.es/fb8524/fwebfd/1366_2000.jpeg'),
       (4, 'https://i5.walmartimages.com.mx/mg/gm/1p/images/product-images/img_large/00071171954176l.jpg?odnHeight=612&odnWidth=612&odnBg=FFFFFF'),
       (4, 'https://mxsonyb2c.vtexassets.com/arquivos/ids/312362/711719558934_003.jpg?v=638015908503400000'),
       (4, 'https://resources.sanborns.com.mx/imagenes-sanborns-ii/1200/711719548898_2.jpg?scale=500&qlty=75'),
       (5, 'https://www.innovasport.com/medias/IS-CW7358-101-1.png?context=bWFzdGVyfGltYWdlc3w4NjMxOHxpbWFnZS9wbmd8aW1hZ2VzL2g2NC9oZGYvMTAxNDc2Mjk5NTcxNTAucG5nfDJlNTM2YzJlNzJhNGY0ZGRmNTEyOTFmYzU2NTM2YTRkNDE3MzdjOWMxNGE2YTM3NTI4N2ZjMmQxNzYwZWVjY2U'),
       (5, 'https://www.utennis.com.br/media/catalog/product/cache/89bf88682659cca671013fd904f60135/c/w/cw3585-500-nike-air-zoom-pegasus-38-fem.jpg'),
       (5, 'https://static.nike.com/a/images/t_PDP_1280_v1/f_auto,q_auto:eco/c156386a-c37e-473c-8324-c8d552fc652d/calzado-de-running-en-carretera-air-zoom-pegasus-38-ekiden-S0nz9k.png'),
       (6, 'https://vyorsa.com.mx/pub/media/catalog/product/1/5/1594281159_1573879.jpg'),
       (6, 'https://www.tiendacanon.com.mx/wcsstore/cmex/es_MX/marketing/cmex-web-development/eos-r5/eos-r5_product.png'),
       (6, 'https://estore.canon.com.pa/wcsstore/CPSACatalogAssetStore/eos-r5_1_xl.jpg'),
       (7, 'https://www.lg.com/cac/images/televisores/md07529735/gallery/D1.jpg'),
       (7, 'https://www.lg.com/cac/images/televisores/md07529735/gallery/D3.jpg'),
       (8, 'https://www.radioshack.com.mx/medias/100048731-3.jpg-515ftw?context=bWFzdGVyfHJvb3R8MTg2NDAzfGltYWdlL2pwZWd8aDQwL2hhZC85MDI4NzAwMjA5MTgyLzEwMDA0ODczMS0zLmpwZ181MTVmdHd8MDEwZDk1NjBkODBjMGU0MzIxMDFkMTE2MjcyYjQzODFmODUzNWZkZDZhMThmMTQ5ZDdiNzdiZmNmNWY0MDE3Mg'),
       (8, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRsZfarfbY6qBokfPXHgDmzFBZ94BNvr7vGTwjcR0s666rILxqd7c5c7kkwn5-RMh-gL0k&usqp=CAU'),
       (9, 'https://www.innovasport.com/medias/reloj-fitbit-versa-3-is-FB511GLPK-1.jpg?context=bWFzdGVyfGltYWdlc3wyNzU0NHxpbWFnZS9qcGVnfGltYWdlcy9oMDkvaDQyLzEwNjcwMDU5OTQ2MDE0LmpwZ3xhMTU0NGYzYjE5NmUxNmU2NDIwNGUyNmMwODc5ZmQ3MzZiNzEwMTdiYzI1NWVkY2QwYjZmZTdkZWJhMDBmY2Iy'),
       (9, 'https://www.innovasport.com/medias/reloj-fitbit-versa-3-is-FB511GLPK-4.jpg?context=bWFzdGVyfGltYWdlc3w4NDIyMnxpbWFnZS9qcGVnfGltYWdlcy9oNDQvaDk3LzEwNjcwMDU4NDM4Njg2LmpwZ3xlYWNmYzBjOWM1MzFhZDgzMTE2Yjk5NmFkNjZhYTQ0YTBkNTQ2NWU4OWI2MTI0MTZkODk2OWQ5OGE0OTBmYTU2'),
       (9, 'https://www.innovasport.com/medias/reloj-fitbit-versa-3-is-FB511GLPK-5.jpg?context=bWFzdGVyfGltYWdlc3w1NTY2MHxpbWFnZS9qcGVnfGltYWdlcy9oYTQvaDY5LzEwNjcwMDU3OTc5OTM0LmpwZ3w5YzJlMmQxZTA1MzcxYTU3ODk1ZDVhYmRiMGM0ZTQ4MjQwYTRjMDcwMGY0ZWUwMjNhODdjYjY4MjE0ZWNjN2Nm'),
       (10, 'https://m.media-amazon.com/images/S/aplus-media-library-service-media/703e9c87-b134-4adc-b9d0-6cf5ff0c2e61.__CR0,0,600,450_PT0_SX600_V1___.jpg'),
       (10, 'https://www.bigw.com.au/medias/sys_master/images/images/h26/h0b/44254416240670.jpg'),
       (10, 'https://www.notebookcheck.org/fileadmin/_processed_/b/1/csm_Bose_QC45_2560_5dc4116d49.jpg'),
       (11, 'https://static.gopro.com/assets/blta2b8522e5372af40/blt5f9f7914f505c38d/643ee10de8155811eeeadfa5/pdp-h10-image01-1024-2x.png'),
       (11, 'https://www.digital2home.com/wp-content/uploads/2023/01/1661877324_1658382.jpg'),
       (11, 'https://www.cordobadigital.net/wp-content/uploads/2021/10/pdp-h10b-standalone-gallery-3.png');
