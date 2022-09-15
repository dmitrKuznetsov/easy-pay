create table payments (
   id 						int primary key auto_increment,
   account_id 				long,
   amount 					double,
   datetime_transaction 	timestamp
)