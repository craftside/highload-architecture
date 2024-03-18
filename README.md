# Highload-architecture
An application represents REST-service of a social network (demo).
The implementation is based on JDBC-technology.

## Used Technologies
- Java 11;
- Spring Boot;
- JDBC;
- PostgreSQL;
- Tomcat;
- Maven;

## Database credentials
```
database.driverClassName=org.postgresql.Driver
database.url=jdbc:postgresql://localhost:5432/highload-db
database.username=user
database.password=password
```

## REST API (curl commands)

### User registration
```
curl --location --request POST 'localhost:8080/api/user/register' \
--header 'Content-Type: application/json' \
--data-raw '{
"firstName": "Yuri",
"secondName": "Gagarin",
"biography": "spaceman",
"gender": "male",
"city": "Smolensk",
"password": "flymetothemoon",
"birthdate": "1934-03-09"
}'
```

### User auth
```
curl --location --request POST 'localhost:8080/api/login?id=aa93aea8-eb89-4994-afc5-ed19c8c29231&password=flymetothemoon' \
--header 'Content-Type: application/json' \
--data-raw '{
"firstName" : "Yuri",
"secondName" : "Gagarin",
"biography" : "spaceman",
"gender" : "male",
"city" : "Smolensk",
"password" : "flymetothemoon"
}'
```

### Get an user by user_id
```
curl --location --request GET 'localhost:8080/api/user/get/84ce52ed-c924-4a39-90e9-df5edd71fccd' \
--header 'Content-Type: application/json' \
--data-raw ''
```