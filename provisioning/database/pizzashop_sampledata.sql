INSERT INTO base (id, name) VALUES ('1', 'Patty');
INSERT INTO base (id, name) VALUES ('2', 'Spread');

INSERT INTO customer (id, firstName, lastName, address) VALUES ('1', 'JAMES', 'BOND', 'LOS ANGELES');
INSERT INTO customer (id, firstName, lastName, address) VALUES ('2', 'KARL', 'TAGUPA', 'TAGBILARAN CITY');

INSERT INTO pizza (id, name, price, base_id) VALUES ('1', 'Regular', '50', '1');
INSERT INTO pizza (id, name, price, base_id) VALUES ('2', 'Special', '100', '1');

INSERT INTO pizzaorder (id, total, deliveryDate) VALUES ('1', '2', '2014-11-01', '1');
INSERT INTO pizzaorder (id, total, deliveryDate) VALUES ('2', '5', '2014-10-12', '2');

INSERT INTO topping (id, name) VALUES('1', 'HAM AND CHEESE');
INSERT INTO topping (id, name) VALUES('2', 'LETTUCE AND EGG');