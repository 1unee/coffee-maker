-- Вставка материалов (ингредиентов)
INSERT INTO material (name) VALUES ('Coffee Beans');
INSERT INTO material (name) VALUES ('Water');
INSERT INTO material (name) VALUES ('Milk');
INSERT INTO material (name) VALUES ('Sugar');

-- Вставка продуктов (напитков)
INSERT INTO product (name, price) VALUES ('Espresso', 12.99);
INSERT INTO product (name, price) VALUES ('Latte', 10.99);
INSERT INTO product (name, price) VALUES ('Cappuccino', 13.5);

-- Вставка количества (например, для упаковки кофе)
INSERT INTO quantity (material_id, measure_type, multiplier, amount)
VALUES
    ((SELECT id FROM material WHERE name = 'Coffee Beans'), 'KILOGRAM', 1.0, 15.0),
    ((SELECT id FROM material WHERE name = 'Water'), 'LITER', 1.0, 20.0),
    ((SELECT id FROM material WHERE name = 'Milk'), 'LITER', 1.0, 10.0),
    ((SELECT id FROM material WHERE name = 'Sugar'), 'KILOGRAM', 1.0, 5.0);

-- Вставка правил для Espresso
INSERT INTO rule (product_id, material_id, measure_type, multiplier, amount)
VALUES
    ((SELECT id FROM product WHERE name = 'Espresso'), (SELECT id FROM material WHERE name = 'Coffee Beans'), 'KILOGRAM', 0.001, 18.0),
    ((SELECT id FROM product WHERE name = 'Espresso'), (SELECT id FROM material WHERE name = 'Water'), 'LITER', 0.001, 0.05);

-- Вставка правил для Latte
INSERT INTO rule (product_id, material_id, measure_type, multiplier, amount)
VALUES
    ((SELECT id FROM product WHERE name = 'Latte'), (SELECT id FROM material WHERE name = 'Coffee Beans'), 'KILOGRAM', 0.001, 18.0),
    ((SELECT id FROM product WHERE name = 'Latte'), (SELECT id FROM material WHERE name = 'Water'), 'LITER', 0.001, 0.05),
    ((SELECT id FROM product WHERE name = 'Latte'), (SELECT id FROM material WHERE name = 'Milk'), 'LITER', 0.001, 0.2);

-- Вставка правил для Cappuccino
INSERT INTO rule (product_id, material_id, measure_type, multiplier, amount)
VALUES
    ((SELECT id FROM product WHERE name = 'Cappuccino'), (SELECT id FROM material WHERE name = 'Coffee Beans'), 'KILOGRAM', 0.001, 18.0),
    ((SELECT id FROM product WHERE name = 'Cappuccino'), (SELECT id FROM material WHERE name = 'Water'), 'LITER', 0.001, 0.05),
    ((SELECT id FROM product WHERE name = 'Cappuccino'), (SELECT id FROM material WHERE name = 'Milk'), 'LITER', 0.001, 0.15);

INSERT INTO payment (product_id, total, timestamp, success, message) VALUES (1, '12.990', '2024-08-18 11:31:05.210231', 't', 'Finished at 2024-08-18T14:31:05.263231300');
INSERT INTO payment (product_id, total, timestamp, success, message) VALUES (1, '12.990', '2024-08-18 11:31:06.651582', 't', 'Finished at 2024-08-18T14:31:06.656581300');
INSERT INTO payment (product_id, total, timestamp, success, message) VALUES (2, '10.990', '2024-08-18 11:31:29.128394', 't', 'Finished at 2024-08-18T14:31:29.135393');
INSERT INTO payment (product_id, total, timestamp, success, message) VALUES (2, '10.990', '2024-08-18 11:32:05.052665', 't', 'Finished at 2024-08-18T14:32:05.059665400');
INSERT INTO payment (product_id, total, timestamp, success, message) VALUES (2, '10.990', '2024-08-18 11:32:05.42464', 't', 'Finished at 2024-08-18T14:32:05.430641600');
INSERT INTO payment (product_id, total, timestamp, success, message) VALUES (2, '10.990', '2024-08-18 11:32:05.820528', 't', 'Finished at 2024-08-18T14:32:05.827524200');
INSERT INTO payment (product_id, total, timestamp, success, message) VALUES (3, '13.500', '2024-08-18 11:32:12.504409', 't', 'Finished at 2024-08-18T14:32:12.509409500');
INSERT INTO payment (product_id, total, timestamp, success, message) VALUES (3, '13.500', '2024-08-18 11:32:13.115206', 't', 'Finished at 2024-08-18T14:32:13.120206800');
INSERT INTO payment (product_id, total, timestamp, success, message) VALUES (3, '13.500', '2024-08-18 11:32:13.694158', 't', 'Finished at 2024-08-18T14:32:13.699159100');
INSERT INTO payment (product_id, total, timestamp, success, message) VALUES (2, '10.990', '2024-08-18 11:32:24.702616', 't', 'Finished at 2024-08-18T14:32:24.708615300');
INSERT INTO payment (product_id, total, timestamp, success, message) VALUES (1, '12.990', '2024-08-18 11:32:33.810935', 't', 'Finished at 2024-08-18T14:32:33.815937600');
INSERT INTO payment (product_id, total, timestamp, success, message) VALUES (1, '12.990', '2024-08-18 11:32:34.660854', 't', 'Finished at 2024-08-18T14:32:34.665852500');
