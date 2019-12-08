-- DDL

CREATE TABLE products (
    id              VARCHAR(50)     PRIMARY KEY,
    type            BIGINT          NOT NULL,
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
