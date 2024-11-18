## Prerequisite
1. JDK 17
2. MongoDB
3. Intellij Idea (Optional)

## How to run application

1. Run below command from root folder
mvn clean install
java -jar target/library-management-service-1.0.0.jar

or
1. mvn clean install
2. docker-compose build
3. docker-compose up


3. Access swagger api using bellow url:
http://localhost:8089/library-management-service/webjars/swagger-ui/index.html

## Actuator Endpoint
http://localhost:9089/actuator/health

## Sample Request for each APIs:

### CreateBook API
curl --location --request POST 'http://localhost:8089/library-management-service/api/create-book' \
--header 'Content-Type: application/json' \
--data-raw '{
"isbn": "ISB-123567",
"title": "abt-test1",
"author": "abc-test"
}'

### RegisterBook API
curl --location --request POST 'http://localhost:8089/library-management-service/api/register-borrower' \
--header 'Content-Type: application/json' \
--data-raw '{
"name": "test2",
"email": "test2@gmail.com"
}'

### GetAllBook API
curl --location --request GET 'http://localhost:8089/library-management-service/api/books?page=0&size=3'


### BorrowBook API
curl --location --request POST 'http://localhost:8089/library-management-service/api/borrow-book' \
--header 'Content-Type: application/json' \
--data-raw '{
"bookId": "673a13e7ece39e323ee9617c",
"borrowerId": "673a13f4ece39e323ee9617d"
}'

### ReturnBook API
curl --location --request POST 'http://localhost:8089/library-management-service/api/return-book' \
--header 'Content-Type: application/json' \
--data-raw '{
"bookId" : "673a13e7ece39e323ee9617c",
"borrowerId" : "673a13f4ece39e323ee9617d"
}'