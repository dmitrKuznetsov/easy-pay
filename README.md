# Easy-pay
This repository contains a couple of microservices which communicate using Kafka.


## <span style="color:rgb(62,134,160)">Payment-ms</span> 
### Features:
- Receive transaction info using endpoint:
```bash
POST http://localhost:8080/pay
{
    "accountId": 1000, # ID consultant account
    "amount": 120.20 # Replenishment amount
}
```
- Put it in Kafka topic  
### Technology  stack:
- Spring Boot Starter Web
- Spring Kafka Producer


##  <span style="color:rgb(62,134,160)">Backend-ms</span> 
### Features:
- Get transaction from Kafka topic 
- Persist it in MySQL database.
- Every 2 minutes append new transactions to CSV file in the form:
```bash
  id;account_id;amount;datetime_transaction
  1;1000;120.20;2022-07-28 07:00:00
```
### Technology  stack:
- Spring without Boot
- Slf4j logger
- Hibernate
- MySQL database
- Flyway database migration tool
- Spring Kafka Consumer
- Opencsv for writing CSV files



## Local development
```bash
# Run mySQL database
docker-compose -f docker-compose-mysql.yml -p mysql-easypay up -d
# Run Kafka
docker-compose -f docker-compose-kafka.yml -p kafka-easypay up -d      
```


