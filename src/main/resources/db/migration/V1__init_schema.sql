CREATE SEQUENCE material_id_seq INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE product_id_seq INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE quantity_id_seq INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE rule_id_seq INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE payment_id_seq INCREMENT BY 1 START WITH 1;

CREATE TABLE material (
    id BIGINT PRIMARY KEY DEFAULT nextval('material_id_seq'),
    name VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE product (
    id BIGINT PRIMARY KEY DEFAULT nextval('product_id_seq'),
    price DECIMAL(8, 3) NOT NULL,
    name VARCHAR(255) UNIQUE NOT NULL
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

CREATE TABLE payment (
    id BIGINT PRIMARY KEY DEFAULT nextval('payment_id_seq'),
    product_id BIGINT REFERENCES product(id),
    total DECIMAL(8, 3) NOT NULL DEFAULT 0,
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    success BOOLEAN DEFAULT FALSE,
    message VARCHAR(2048) DEFAULT 'Not filled'
);
