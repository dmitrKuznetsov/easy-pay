# Easy-pay


## Local development
For local development and testing.
```bash
# Run mySQL database
docker-compose -f docker-compose-mysql.yml -p mysql-easypay up -d
# Run Kafka
docker-compose -f docker-compose-kafka.yml -p kafka-easypay up -d      
```

## <span style="color:rgb(62,134,160)">Payment</span> microservice technology  stack
- Spring Boot Starter Web
- Spring Kafka Producer

##  <span style="color:rgb(62,134,160)">Backend</span> microservice technology  stack
- Spring without Boot
- Slf4j logger
- Hibernate
- MySQL database
- Flyway database migration tool
- Spring Kafka Consumer 