Feature: Retrieve Student Information

  Scenario: When a name prefix is passed all the students information starting with that is returned
    Given Student enters name prefix "n"
    When The student makes a call to "http://localhost:9090/student/search/" and get the details
    Then The API should return the student details and response code 200

  Scenario: When a student want to search student details by last name and the api is case insensitive
    Given Student enters last name "RaJpUt"
    When The student makes a call to "http://localhost:9090/student/searchbylastname/" and get the details
    Then The API should return the student details where firstname is "abhishek", lastname is "rajput"
    And response code 200

  Scenario: When a name prefix is passed but no data is matched
    Given Student enters name prefix "Z"
    When The student makes a call to "http://localhost:9090/student/search/" and get the details
    Then The API should return a message "no student data found" and response code 404

  Scenario: When a non alphabetic name prefix is passed
    Given Student enters name prefix "11111111"
    When The student makes a call to "http://localhost:9090/student/search/" and get the details
    Then The API should return a message "bad request a name prefix can only contain alphabets" and response code 400