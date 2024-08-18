-- Вставка материалов (ингредиентов)
INSERT INTO material (name) VALUES ('Coffee Beans');
INSERT INTO material (name) VALUES ('Water');
INSERT INTO material (name) VALUES ('Milk');
INSERT INTO material (name) VALUES ('Sugar');

-- Вставка продуктов (напитков)
INSERT INTO product (name) VALUES ('Espresso');
INSERT INTO product (name) VALUES ('Latte');
INSERT INTO product (name) VALUES ('Cappuccino');

-- Вставка количества (например, для упаковки кофе)
INSERT INTO quantity (material_id, measure_type, multiplier, amount)
VALUES ((SELECT id FROM material WHERE name = 'Coffee Beans'), 'KILOGRAM', 1.0, 1.0);

-- Вставка правил для Espresso
INSERT INTO rule (product_id, material_id, measure_type, multiplier, amount)
VALUES
    ((SELECT id FROM product WHERE name = 'Espresso'), (SELECT id FROM material WHERE name = 'Coffee Beans'), 'KILOGRAM', 1.0, 18.0),
    ((SELECT id FROM product WHERE name = 'Espresso'), (SELECT id FROM material WHERE name = 'Water'), 'LITER', 1.0, 0.05);

-- Вставка правил для Latte
INSERT INTO rule (product_id, material_id, measure_type, multiplier, amount)
VALUES
    ((SELECT id FROM product WHERE name = 'Latte'), (SELECT id FROM material WHERE name = 'Coffee Beans'), 'KILOGRAM', 1.0, 18.0),
    ((SELECT id FROM product WHERE name = 'Latte'), (SELECT id FROM material WHERE name = 'Water'), 'LITER', 1.0, 0.05),
    ((SELECT id FROM product WHERE name = 'Latte'), (SELECT id FROM material WHERE name = 'Milk'), 'LITER', 1.0, 0.2);

-- Вставка правил для Cappuccino
INSERT INTO rule (product_id, material_id, measure_type, multiplier, amount)
VALUES
    ((SELECT id FROM product WHERE name = 'Cappuccino'), (SELECT id FROM material WHERE name = 'Coffee Beans'), 'KILOGRAM', 1.0, 18.0),
    ((SELECT id FROM product WHERE name = 'Cappuccino'), (SELECT id FROM material WHERE name = 'Water'), 'LITER', 1.0, 0.05),
    ((SELECT id FROM product WHERE name = 'Cappuccino'), (SELECT id FROM material WHERE name = 'Milk'), 'LITER', 1.0, 0.15);
