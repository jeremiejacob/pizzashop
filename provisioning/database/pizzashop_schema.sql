
CREATE TABLE base (
    id serial PRIMARY KEY,
    name character varying(20) NOT NULL
);

CREATE TABLE customer (
    id serial PRIMARY KEY,
    firstName character varying(20) NOT NULL,
    lastName character varying(20) NOT NULL,
    address character varying(20) NOT NULL
);

CREATE TABLE pizza (
    id serial PRIMARY KEY,
    name character varying(20) NOT NULL,
    price numeric NOT NULL,
    base_id integer NOT NULL REFERENCES base(id)
);

CREATE TABLE pizzaorder (
    id serial PRIMARY KEY,
    total integer NOT NULL,
    deliveryDate date NOT NULL,
    customer_id integer NOT NULL REFERENCES customer(id)
);

CREATE TABLE topping (
    id serial PRIMARY KEY,
    name character varying(20) NOT NULL
);

CREATE TABLE pizzatopping (
    pizza_id integer REFERENCES pizza(id),
    topping_id integer REFERENCES topping(id),
    PRIMARY KEY (pizza_id, topping_id)
);
