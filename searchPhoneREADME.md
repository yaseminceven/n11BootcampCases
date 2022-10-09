## **N11 TEST CASE 3**
### Test Case Details
Writing test automation code to search "telefon" and filter the results by the second brand, comment count and free shipment option.
### Test Steps
1. Open https://www.n11.com/ page
2. Search for "telefon" on the search bar
3. From the result page select the second brand filter
4. Sort the results by comment count
5. Select free shipment option

### Technologies
- Java SDK 11.0.13
- Maven
- Cucumber
- TestNG
- Selenium Grid
- Docker

### How to Run
To run this project, download or clone the project. In the project directory run the following commands to run the TestNG test suite.
```
docker-compose up
mvn clean test -Dcucumber.filter.tags="@searchPhone"
```
Selenium grid components are build with the docker-compose.yml file.</br>
Selenium grid nodes run on the docker. And the hub address is set to ***localhost:4444***. </br>

### Reporting
Reports that are obtained with Cucumber are located under the ***/target*** directory.  </br>
Extent reports are located under the ***/Reports*** directory. </br>


