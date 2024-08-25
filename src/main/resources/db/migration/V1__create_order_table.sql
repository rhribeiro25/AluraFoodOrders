CREATE TABLE IF NOT EXISTS orders (
  id bigint GENERATED ALWAYS AS identity
  	CONSTRAINT pk_order
  		primary key,
  order_date date NOT NULL,
  order_status varchar(255) NOT null
);