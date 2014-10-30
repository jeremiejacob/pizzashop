INSERT INTO base (id, name) VALUES ('1', 'Patty');
INSERT INTO base (id, name) VALUES ('2', 'Spread');
SELECT setval('base_id_seq', max(id)) FROM base;

INSERT INTO customer (id, first_name, last_name, address) VALUES ('1', 'JAMES', 'BOND', 'LOS ANGELES');
INSERT INTO customer (id, first_name, last_name, address) VALUES ('2', 'KARL', 'TAGUPA', 'TAGBILARAN CITY');
SELECT setval('customer_id_seq', max(id)) FROM customer;

INSERT INTO pizza (id, name, price, base_id) VALUES ('1', 'Regular', '50', '1');
INSERT INTO pizza (id, name, price, base_id) VALUES ('2', 'Special', '100', '1');
SELECT setval('pizza_id_seq', max(id)) FROM pizza;

INSERT INTO pizza_order (id, total, delivery_date, customer_id) VALUES ('1', '2', '2014-11-01', '1');
INSERT INTO pizza_order (id, total, delivery_date, customer_id) VALUES ('2', '5', '2014-10-12', '2');
SELECT setval('pizza_order_id_seq', max(id)) FROM pizza_order;

INSERT INTO topping (id, name) VALUES('1', 'HAM AND CHEESE');
INSERT INTO topping (id, name) VALUES('2', 'LETTUCE AND EGG');
SELECT setval('topping_id_seq', max(id)) FROM topping;