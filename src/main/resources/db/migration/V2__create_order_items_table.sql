CREATE TABLE IF NOT EXISTS order_items (
  id bigint GENERATED ALWAYS AS identity
  	CONSTRAINT pk_order_item
  		primary key,
  product_id bigint NOT NULL,
  description varchar(255) DEFAULT NULL,
  qtt integer NOT NULL,
  order_id bigint NOT NULL,
  CONSTRAINT fk_order_item
      FOREIGN KEY(order_id)
        REFERENCES orders(id)
);