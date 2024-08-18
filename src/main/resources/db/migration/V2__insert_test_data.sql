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
