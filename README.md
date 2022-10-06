## **N11 TEST CASE Writing To Excel**
### Test Case Details
Writing test automation code to obtain list of stores and writing the results into Excel. And reading the store url from excel to obtain the comment count for the second store that starts with S.
### Test Steps
1. Open https://www.n11.com/ page
2. Hover over the "Mağazalar" tab and click on the "Mağazaları Gör" section
3. Click on the "Tüm Mağazalar" button and obtain the list of stores through A-Z into Excel
4. From the excel read the second store url that starts with S and go to main page of the store
5. Obtain the comment count for that store

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
mvn clean test
```
Selenium grid components are build with the docker-compose.yml file.</br>
Selenium grid nodes run on the docker. And the hub address is set to ***localhost:4444***. </br>
After the tests are completed stores list is written into the ***./storesList.xlsx*** file.

### Reporting
Reports that are obtained with Cucumber are located under the ***/target*** directory.  </br>
Reports provided by the Extent are located under the ***/Reports*** directory. </br>


