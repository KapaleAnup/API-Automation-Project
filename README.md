API AUTOMATION PROJECT

This project is created to design how the framework design works in Api testing using rest assured.

Task to worked upon:

1) Create an automated test suite to test all the CRUD operations for the API's http://dummy.restapiexample.com/.
2) Test Cases should cover both Positive and negative Test cases.
3) Put/Post method should be read from .json file.
4) Should be well Architecture.
5) Summary Report should get generate.


Implemented Architecture:

1) src/main/java :
    contains all main functions
	PACKAGES, Class and Functions
2) src/main/resources :
    Contains all the Payload with json data.
3) src/test/java/TestCases :
    Contains all the Test Cases which we have to performed.
4) Config package:
    Create config properties such as BaseUrl, Username  & password if any. Also we can provide log4j property file as well.
5) Comman Package:
     Create BaseClass to read the config properties and create a setup file to initialize the Base url.
6) ExtentReports:
   	 contains generated reports after the testcase executions.
7) Listners:
     contains class and function to read property from property file.
8) Logs :
    Contains all get all the logs after the testcase executions.
9) Utilits:
    Contains all the common funtions which will help us to reduce the repetitive work.
10) Resources :
    Contains .json file which we can read by JSONParser.

Json Reader concept:

1) First of all, we will create JSONParser instance to parse JSON file.
2) Use FileReader to read JSON file and pass it to parser.
3) Use Hashmap to fetch the values in key value pair and return mappeddata so that the method will fetch the data from it.

