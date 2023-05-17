CREATE TABLE crypto_currencies
(
    id     BIGINT NOT NULL PRIMARY KEY,
    price  DOUBLE PRECISION,
    symbol VARCHAR(255)
);

CREATE TABLE subscriptions
(
    id                 BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    subscribed_price   DOUBLE PRECISION,
    username           VARCHAR(255),
    crypto_currency_id BIGINT,
    CONSTRAINT fk_cryptocurrency
        FOREIGN KEY (crypto_currency_id) REFERENCES crypto_currencies (id)
);

INSERT INTO crypto_currencies (id, price, symbol)
VALUES (90, 1.0, 'BTC'),
       (80, 1.0, 'ETH'),
       (48543, 1.0, 'SOL');
