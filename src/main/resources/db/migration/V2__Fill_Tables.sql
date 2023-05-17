UPDATE crypto_currencies SET price = 1819.2, symbol = 'ETH' WHERE id = 80;
UPDATE crypto_currencies SET price = 20.69, symbol = 'SOL' WHERE id = 48543;
UPDATE crypto_currencies SET price = 27004.1, symbol = 'BTC' WHERE id = 90;

INSERT INTO subscriptions (subscribed_price, username, crypto_currency_id)
VALUES
    (27032.77, 'Mikita', 90),
    (1820.11, 'Mikita', 80),
    (1820.76, 'Jenya', 80),
    (1820.76, 'Mikhail', 80),
    (27017.3, 'Vladislav', 90),
    (20.71, 'Dima', 48543),
    (1820.76, 'Karasik', 80);