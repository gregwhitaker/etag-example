-- DDL

CREATE TABLE product_types (
    id      BIGINT          PRIMARY KEY,
    name    VARCHAR(50)     NOT NULL
);

CREATE TABLE product_genders (
    id      BIGINT          PRIMARY KEY,
    name    VARCHAR(50)     NOT NULL
);

CREATE TABLE products (
    id              VARCHAR(50)     PRIMARY KEY,
    type            BIGINT          REFERENCES product_types(id) NOT NULL,
    active          BOOLEAN         NOT NULL,
    start_time      TIMESTAMP       NOT NULL,
    end_time        TIMESTAMP       NOT NULL,
    short_name      VARCHAR(50)     NOT NULL,
    long_name       VARCHAR(250),
    description     TEXT            NOT NULL,
    gender          BIGINT          NOT NULL
);

CREATE TABLE product_skus (
    id              VARCHAR(50)     PRIMARY KEY,
    product_id      VARCHAR(50)     REFERENCES products(id) ON DELETE CASCADE,
    active          BOOLEAN         NOT NULL,
    colorway_id     VARCHAR(20)     NOT NULL,
    colorway        VARCHAR(100)    NOT NULL,
    size            VARCHAR(20)     NOT NULL
);

CREATE TABLE sku_prices (
    sku_id          VARCHAR(50)     REFERENCES product_skus(id) ON DELETE CASCADE,
    currency_code   VARCHAR(10)     NOT NULL,
    price_list      NUMERIC         NOT NULL,
    price_msrp      NUMERIC         NOT NULL,
    price_sale      NUMERIC         NOT NULL,

    PRIMARY KEY(sku_id, currency_code)
);

-- Data
INSERT INTO product_types(id, name) VALUES (1, 'Apparel');
INSERT INTO product_types(id, name) VALUES (2, 'Shoes');

INSERT INTO product_genders(id, name) VALUES (1, 'Male');
INSERT INTO product_genders(id, name) VALUES (2, 'Female');
INSERT INTO product_genders(id, name) VALUES (3, 'Boy');
INSERT INTO product_genders(id, name) VALUES (4, 'Girl');
INSERT INTO product_genders(id, name) VALUES (5, 'Adult - Unisex');
INSERT INTO product_genders(id, name) VALUES (6, 'Child - Unisex');

INSERT INTO products(id, type, active, start_time, end_time, short_name, long_name, description, gender)  VALUES ('001', 1, TRUE, now(), now() + INTERVAL '1 year', 'T-Shirt 1', 'Super Good T-Shirt', 'afsadfasdfasdfasdfsdfsdfsadfsadfsdfsafdsfasdfsdfsdfasdfasfs', 1);
INSERT INTO products(id, type, active, start_time, end_time, short_name, long_name, description, gender)  VALUES ('002', 1, TRUE, now(), now() + INTERVAL '1 year', 'T-Shirt 2', 'Super Good T-Shirt', 'afsadfasdfasdfasdfsdfsdfsadfsadfsdfsafdsfasdfsdfsdfasdfasfs', 2);
INSERT INTO products(id, type, active, start_time, end_time, short_name, long_name, description, gender)  VALUES ('003', 1, TRUE, now(), now() + INTERVAL '1 year', 'Shorts 1', 'Super Cool Shorts', 'afsadfasdfasdfasdfsdfsdfsadfsadfsdfsafdsfasdfsdfsdfasdfasfs', 1);
INSERT INTO products(id, type, active, start_time, end_time, short_name, long_name, description, gender)  VALUES ('004', 1, TRUE, now(), now() + INTERVAL '1 year', 'Shorts 1', 'Super Cool Womens Shorts', 'afsadfasdfasdfasdfsdfsdfsadfsadfsdfsafdsfasdfsdfsdfasdfasfs', 2);
INSERT INTO products(id, type, active, start_time, end_time, short_name, long_name, description, gender)  VALUES ('005', 1, TRUE, now(), now() + INTERVAL '1 year', 'Shorts 1', 'Super Cool Boys Shorts', 'afsadfasdfasdfasdfsdfsdfsadfsadfsdfsafdsfasdfsdfsdfasdfasfs', 3);
INSERT INTO products(id, type, active, start_time, end_time, short_name, long_name, description, gender)  VALUES ('006', 1, TRUE, now(), now() + INTERVAL '1 year', 'Shorts 1', 'Super Cool Girls Shorts', 'afsadfasdfasdfasdfsdfsdfsadfsadfsdfsafdsfasdfsdfsdfasdfasfs', 4);

INSERT INTO product_skus (id, product_id, active, colorway_id, colorway, size) VALUES ('000-13456', '001', true, '5391028', 'Yellow', 'L');
INSERT INTO product_skus (id, product_id, active, colorway_id, colorway, size) VALUES ('000-43211', '001', true, '2919781', 'Indigo', 'L');
INSERT INTO product_skus (id, product_id, active, colorway_id, colorway, size) VALUES ('000-48168', '001', false, '2444890', 'Puce', '2XL');
INSERT INTO product_skus (id, product_id, active, colorway_id, colorway, size) VALUES ('000-18317', '001', false, '3177878', 'Fuscia', 'XL');
INSERT INTO product_skus (id, product_id, active, colorway_id, colorway, size) VALUES ('000-13075', '001', true, '5953027', 'Teal', 'M');
INSERT INTO product_skus (id, product_id, active, colorway_id, colorway, size) VALUES ('000-20239', '001', true, '9051615', 'Yellow', 'L');
INSERT INTO product_skus (id, product_id, active, colorway_id, colorway, size) VALUES ('000-44438', '001', true, '5528906', 'Blue', 'XL');
INSERT INTO product_skus (id, product_id, active, colorway_id, colorway, size) VALUES ('000-77535', '001', true, '4114938', 'Violet', 'XL');
INSERT INTO product_skus (id, product_id, active, colorway_id, colorway, size) VALUES ('000-75902', '001', true, '2276786', 'Violet', '3XL');
INSERT INTO product_skus (id, product_id, active, colorway_id, colorway, size) VALUES ('000-32883', '001', false, '9041457', 'Red', '2XL');
INSERT INTO product_skus (id, product_id, active, colorway_id, colorway, size) VALUES ('000-45383', '002', false, '2684291', 'Goldenrod', 'S');
INSERT INTO product_skus (id, product_id, active, colorway_id, colorway, size) VALUES ('000-35327', '002', false, '1344680', 'Maroon', 'XS');
INSERT INTO product_skus (id, product_id, active, colorway_id, colorway, size) VALUES ('000-44740', '002', true, '1312934', 'Orange', 'M');
INSERT INTO product_skus (id, product_id, active, colorway_id, colorway, size) VALUES ('000-75018', '002', true, '5636317', 'Indigo', 'S');
INSERT INTO product_skus (id, product_id, active, colorway_id, colorway, size) VALUES ('000-79514', '002', true, '6861764', 'Khaki', 'XS');
INSERT INTO product_skus (id, product_id, active, colorway_id, colorway, size) VALUES ('000-70958', '002', false, '9089593', 'Teal', 'M');
INSERT INTO product_skus (id, product_id, active, colorway_id, colorway, size) VALUES ('000-87423', '002', false, '1222720', 'Orange', 'XL');
INSERT INTO product_skus (id, product_id, active, colorway_id, colorway, size) VALUES ('000-18636', '002', false, '9036904', 'Yellow', 'M');
INSERT INTO product_skus (id, product_id, active, colorway_id, colorway, size) VALUES ('000-70716', '002', true, '3106936', 'Pink', 'XL');
INSERT INTO product_skus (id, product_id, active, colorway_id, colorway, size) VALUES ('000-79205', '002', false, '2393125', 'Red', 'XS');
INSERT INTO product_skus (id, product_id, active, colorway_id, colorway, size) VALUES ('000-86593', '003', true, '1882303', 'Indigo', 'S');
INSERT INTO product_skus (id, product_id, active, colorway_id, colorway, size) VALUES ('000-60323', '003', true, '5519036', 'Aquamarine', '2XL');
INSERT INTO product_skus (id, product_id, active, colorway_id, colorway, size) VALUES ('000-63890', '003', true, '7826594', 'Yellow', 'M');
INSERT INTO product_skus (id, product_id, active, colorway_id, colorway, size) VALUES ('000-70306', '003', false, '3136797', 'Pink', '3XL');
INSERT INTO product_skus (id, product_id, active, colorway_id, colorway, size) VALUES ('000-15330', '003', false, '2498334', 'Aquamarine', 'XL');
INSERT INTO product_skus (id, product_id, active, colorway_id, colorway, size) VALUES ('000-46004', '003', false, '8596916', 'Mauv', '3XL');
INSERT INTO product_skus (id, product_id, active, colorway_id, colorway, size) VALUES ('000-01667', '004', false, '8351623', 'Puce', 'M');
INSERT INTO product_skus (id, product_id, active, colorway_id, colorway, size) VALUES ('000-58561', '004', false, '0670135', 'Aquamarine', 'M');
INSERT INTO product_skus (id, product_id, active, colorway_id, colorway, size) VALUES ('000-27358', '004', false, '5750335', 'Maroon', '3XL');
INSERT INTO product_skus (id, product_id, active, colorway_id, colorway, size) VALUES ('000-99606', '004', false, '2915381', 'Mauv', 'XS');
INSERT INTO product_skus (id, product_id, active, colorway_id, colorway, size) VALUES ('000-52779', '004', true, '9961368', 'Mauv', 'XS');
INSERT INTO product_skus (id, product_id, active, colorway_id, colorway, size) VALUES ('000-73985', '004', false, '1472614', 'Purple', '2XL');
INSERT INTO product_skus (id, product_id, active, colorway_id, colorway, size) VALUES ('000-32346', '005', true, '0607225', 'Blue', 'S');
INSERT INTO product_skus (id, product_id, active, colorway_id, colorway, size) VALUES ('000-86942', '005', true, '2772228', 'Indigo', 'S');
INSERT INTO product_skus (id, product_id, active, colorway_id, colorway, size) VALUES ('000-85217', '005', false, '9918336', 'Aquamarine', 'S');
INSERT INTO product_skus (id, product_id, active, colorway_id, colorway, size) VALUES ('000-29384', '005', true, '3661223', 'Khaki', '2XL');
INSERT INTO product_skus (id, product_id, active, colorway_id, colorway, size) VALUES ('000-57339', '005', false, '5958923', 'Green', '3XL');
INSERT INTO product_skus (id, product_id, active, colorway_id, colorway, size) VALUES ('000-52986', '005', false, '9730519', 'Yellow', '2XL');
INSERT INTO product_skus (id, product_id, active, colorway_id, colorway, size) VALUES ('000-34982', '005', false, '3802688', 'Crimson', 'S');
INSERT INTO product_skus (id, product_id, active, colorway_id, colorway, size) VALUES ('000-08352', '005', true, '7089302', 'Green', '3XL');
INSERT INTO product_skus (id, product_id, active, colorway_id, colorway, size) VALUES ('000-69331', '005', true, '6765816', 'Crimson', 'XL');
INSERT INTO product_skus (id, product_id, active, colorway_id, colorway, size) VALUES ('000-69809', '005', false, '9165710', 'Goldenrod', 'XS');
INSERT INTO product_skus (id, product_id, active, colorway_id, colorway, size) VALUES ('000-62666', '005', true, '4366796', 'Goldenrod', 'XS');
INSERT INTO product_skus (id, product_id, active, colorway_id, colorway, size) VALUES ('000-01084', '005', true, '1364367', 'Red', 'S');
