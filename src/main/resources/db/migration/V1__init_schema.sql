-- Создание последовательностей
CREATE SEQUENCE IF NOT EXISTS product_id_seq START WITH 1 INCREMENT BY 1;

-- Создание таблицы Product
CREATE TABLE IF NOT EXISTS product (
    id BIGINT PRIMARY KEY DEFAULT NEXTVAL('product_id_seq'),
    name VARCHAR(255) NOT NULL
);

CREATE SEQUENCE IF NOT EXISTS material_id_seq START WITH 1 INCREMENT BY 1;

-- Создание таблицы Material
CREATE TABLE IF NOT EXISTS material (
    id BIGINT PRIMARY KEY DEFAULT NEXTVAL('material_id_seq'),
    name VARCHAR(255) NOT NULL,
    measure_type VARCHAR(50),
    multiplier_relative_measure_type DECIMAL(15, 5),
    amount DECIMAL(15, 5)
);

CREATE SEQUENCE IF NOT EXISTS rule_id_seq START WITH 1 INCREMENT BY 1;

-- Создание таблицы Rule
CREATE TABLE IF NOT EXISTS rule (
    id BIGINT PRIMARY KEY DEFAULT NEXTVAL('rule_id_seq'),
    product_id BIGINT,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    FOREIGN KEY (product_id) REFERENCES product (id)
);

-- Создание таблицы RuleMaterialLink
CREATE TABLE IF NOT EXISTS rule_material_link (
    rule_id BIGINT,
    material_id BIGINT,
    timestamp TIMESTAMP,
    PRIMARY KEY (rule_id, material_id),
    FOREIGN KEY (rule_id) REFERENCES rule(id),
    FOREIGN KEY (material_id) REFERENCES material(id)
);