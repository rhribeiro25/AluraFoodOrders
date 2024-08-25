CREATE TABLE IF NOT EXISTS orders (
  id bigserial CONSTRAINT pk_id_order PRIMARY KEY,
  order_date date NOT NULL,
  order_status varchar(255) NOT NULL
);