CREATE TABLE orders (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  order_date datetime NOT NULL,
  order_status varchar(255) NOT NULL,
  PRIMARY KEY (id)
)