-- Вставка данных в таблицу Product
INSERT INTO product (name) VALUES ('Espresso');
INSERT INTO product (name) VALUES ('Americano');
INSERT INTO product (name) VALUES ('Cappuccino');

-- Вставка данных в таблицу Material
INSERT INTO material (name, measure_type, multiplier_relative_measure_type, amount)
VALUES ('Coffee Beans', 'KILOGRAM', 0.001, 18.00);

INSERT INTO material (name, measure_type, multiplier_relative_measure_type, amount)
VALUES ('Water', 'LITER', 0.001, 30.00);

INSERT INTO material (name, measure_type, multiplier_relative_measure_type, amount)
VALUES ('Milk', 'LITER', 0.001, 150.00);

-- Вставка данных в таблицу Rule
INSERT INTO rule (product_id, name, description) VALUES (1, 'Espresso Preparation', 'Use fine ground coffee.');
INSERT INTO rule (product_id, name, description) VALUES (2, 'Americano Preparation', 'Add hot water to espresso.');
INSERT INTO rule (product_id, name, description) VALUES (3, 'Cappuccino Preparation', 'Steam and froth milk.');

-- Вставка данных в таблицу RuleMaterialLink
INSERT INTO rule_material_link (rule_id, material_id, timestamp)
VALUES (1, 1, CURRENT_TIMESTAMP);

INSERT INTO rule_material_link (rule_id, material_id, timestamp)
VALUES (1, 2, CURRENT_TIMESTAMP);

INSERT INTO rule_material_link (rule_id, material_id, timestamp)
VALUES (2, 1, CURRENT_TIMESTAMP);

INSERT INTO rule_material_link (rule_id, material_id, timestamp)
VALUES (2, 2, CURRENT_TIMESTAMP);

INSERT INTO rule_material_link (rule_id, material_id, timestamp)
VALUES (3, 1, CURRENT_TIMESTAMP);

INSERT INTO rule_material_link (rule_id, material_id, timestamp)
VALUES (3, 3, CURRENT_TIMESTAMP);
