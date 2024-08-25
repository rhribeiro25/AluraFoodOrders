CREATE TABLE IF NOT EXISTS order_items (
  id bigint CONSTRAINT pk_id_order_item PRIMARY KEY,
  description varchar(255) DEFAULT NULL,
  qtt integer NOT NULL,
  order_id bigint NOT NULL,
  FOREIGN KEY (order_id) REFERENCES orders(id)
);