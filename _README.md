##Check List
- Add your service to main pom.xml as a module
- Change the parent attribute of your pom.xml as in the delivery-service pom.xml
- Set up the database connection in the application.properties file
- Add your database container to the docker-compose.yml file and docker-compose-dev.yml file
- Add your service container to the docker-compose.yml file

##How to run for development
- Run the command `docker compose -f docker-compose-dev.yml up --build`
- Goto `/discovery-service` and run the command `mvn spring-boot:run`
- Goto `/gateway-service` and run the command `mvn spring-boot:run`
- Then goto your service and run the command `mvn spring-boot:run` or run it using your IDE

##How to run for production
- Run the command `docker compose -f docker-compose.yml up --build`
