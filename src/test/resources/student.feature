Feature: Retrieve Student Information

  Scenario: When a name prefix is passed all the students information starting with that is returned
    Given Student enters name prefix "n"
    When The student makes a call to "http://localhost:9090/student/search/" get the details
    Then The API should return the student details and response code 200

  Scenario: When a student want to search student details by last name and the api is case insensitive
    Given Student enters last name "RaJpUt"
    When The student makes a call to "http://localhost:9090/student/searchbylastname/" get the details
    Then The API should return the student details where firstname is "abhishek", lastname is "rajput"
    And response code 200
