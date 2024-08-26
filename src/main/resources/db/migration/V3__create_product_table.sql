CREATE TABLE IF NOT EXISTS products (
  id bigint GENERATED ALWAYS AS identity
  	CONSTRAINT pk_product
  		primary key,
  name varchar(128) NOT NULL,
  description varchar(256) NOT NULL
);