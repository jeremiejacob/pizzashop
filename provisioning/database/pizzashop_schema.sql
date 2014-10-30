
CREATE TABLE base (
    id serial PRIMARY KEY,
    name character varying(20) NOT NULL
);

CREATE TABLE customer (
    id serial PRIMARY KEY,
    first_name character varying(20) NOT NULL,
    last_name character varying(20) NOT NULL,
    address character varying(20) NOT NULL
);

CREATE TABLE pizza (
    id serial PRIMARY KEY,
    name character varying(20) NOT NULL,
    price float(4) NOT NULL,
    base_id integer NOT NULL REFERENCES base(id)
);

CREATE TABLE pizza_order (
    id serial PRIMARY KEY,
    total float(4) NOT NULL,
    delivery_date timestamp without time zone,
    customer_id integer NOT NULL REFERENCES customer(id)

);

CREATE TABLE topping (
    id serial PRIMARY KEY,
    name character varying(20) NOT NULL
);

CREATE TABLE pizza_topping (
    pizza_id integer REFERENCES pizza(id),
    topping_id integer REFERENCES topping(id),
    PRIMARY KEY (pizza_id, topping_id)
);

CREATE TABLE pizza_order_pizza(
    pizza_order_id integer REFERENCES pizza_order(id),
    pizza_id integer REFERENCES pizza(id),
    PRIMARY KEY (pizza_id, pizza_order_id)
);
