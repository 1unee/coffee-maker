CREATE SEQUENCE material_id_seq INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE product_id_seq INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE quantity_id_seq INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE rule_id_seq INCREMENT BY 1 START WITH 1;

CREATE TABLE material (
    id BIGINT PRIMARY KEY DEFAULT nextval('material_id_seq'),
    name VARCHAR(255) NOT NULL
);

CREATE TABLE product (
    id BIGINT PRIMARY KEY DEFAULT nextval('product_id_seq'),
    name VARCHAR(255) NOT NULL
);

CREATE TABLE quantity (
    id BIGINT PRIMARY KEY DEFAULT nextval('quantity_id_seq'),
    material_id BIGINT REFERENCES material(id),
    measure_type VARCHAR(50) NOT NULL,
    multiplier DECIMAL(15, 5) NOT NULL,
    amount DECIMAL(15, 5) NOT NULL
);

CREATE TABLE rule (
    id BIGINT PRIMARY KEY DEFAULT nextval('product_id_seq'),
    product_id BIGINT REFERENCES product(id),
    material_id BIGINT REFERENCES material(id),
    measure_type VARCHAR(50) NOT NULL,
    multiplier DECIMAL(15, 5) NOT NULL,
    amount DECIMAL(15, 5) NOT NULL
);
