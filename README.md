# nova
##Technologies and Tools
_Maven 3.6_\
_Java 1.8_\
_Spring-Boot_ 2.3.0.RELEASE\
_lombok \
_IntelliJ_
More dependency can be referred from the **pom.xml** file
##Basic
This project will help the cab service provided book the cab on the basic of following rules:\
SafeCab can have
   - all Females or
   - all Males or
   - two Females and two Males.
##Fairness
This solution might not serve the customer's request 100% in the same order in which requested.

##Limitation
1) Application will give status **false** if a car or customer already register with the system.
2) A trip already requested and trip in progress, still another trip can be requested with same cab.
3) A customer has already requested the ride, but ride not completed, so a customer can request ride while his\her previous ride in progress
4) If booking processor thread die, booking process will also die.
##How to use
Run the application with main method of spring-boot
Follow the instruction to start the application from terminal\
compile the code
1) $ mvn clean install\

Run the application
1) $ ./mvnw spring-boot:run

#Proposed System design
A high level system designed proposed and document available in the **docs** directory of this project.

#Postman Request
restfull request available with the application in **docs** directory.