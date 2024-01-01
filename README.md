My Project is OkutanApi which has mvn modules that are all spring-boot projects.

1) EurekaServer submodule is Discovery Service Registry. Essential controllers ExchangeRate and CurrencyConversion register to this. It is necessary to communicate microservices and load-balancing them. Run on port 8761.

2) Gateway submodule is ApiGateway project. It provides a gateway endpoints to essential microservices. Run on port 8082.

3) ExchangeRate is a microservice that takes source and target currencies and call external exchange rate service from "freecurrencyapi.com". configuration properties can be found application.properties files of ExchangeRate project. There is a swagger-ui documentation for api of this project(http://localhost:8081/swagger-ui.html). Run on port 8081.

4) CurrencyConversion is a microservice that has 2 operation. One of them is converting from source currency to target currency calling ExchangeRate microservice and calculate source amount then save transaction to database. The other operation takes transaction Id or transaction date and retrieve corresponding transaction list back.  There is a swagger-ui documentation for api of this project(http://localhost:8080/swagger-ui.html). Run on port 8080.

5) To run the services, check-out OkutanApi project and run the command mvn spring-boot:run command for all submodules. EurekaServer must be started first. Then Gateway must be started. Others' start order is not important.
Maven and Java is must to run the modules. Minimum Java version is 17.
