## **N11 Bootcamp Test Cases**
This project contains three different test cases. The details of each test case can be obtained from the readme files.

### Technologies
- Java SDK 11.0.13
- Maven
- Cucumber
- TestNG
- Selenium Grid
- Docker

### How to Run
To run this project, download or clone the project. In the project directory run the following commands to run the relevant test case with TestNG test suite.
```
docker-compose up
mvn clean test -Dcucumber.filter.tags="@addIphone"
mvn clean test -Dcucumber.filter.tags="@searchPhone"
mvn clean test -Dcucumber.filter.tags="@commentCount"
```
Selenium grid components are build with the docker-compose.yml file.</br>
Selenium grid nodes run on the docker. And the hub address is set to ***localhost:4444***. </br>

### Reporting
Reports that are obtained with Cucumber are located under the ***/target*** directory.  </br>
Reports provided by the Extent are located under the ***/Reports*** directory. </br>


