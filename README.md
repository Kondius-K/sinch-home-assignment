# Sinch home assignment (by Konstantin Kondius)
The app implements home assignment tasks:

###Task 1: 
You are given an array of n unique integers a = a[0], a[1], ... , a[n - 1] and an integer value k. 

Find and print the number of pairs (a[i], a[j])wherei < janda[i] + a[j] = k.

###Task 2: 
Create a program that evaluates arithmetic expressions written in Polish notation. 

Expressions can contain double-precision floating point numbers and the following operations: addition, subtraction, division and multiplication.

## How to use:
Given tasks implemented in two ways: by terminal and by rest api

### Using terminal
After application launch enter "go" to run terminal task processing.

Then enter input parameters and enter "run" to calculate and get the result.

### Using REST API
Two endpoints have been implemented:

* GET http://localhost:8080/pairs/

  RequestBody should be in a json format and contain field "expressions" with input int array


* GET http://localhost:8080/polish/

  RequestBody should be in a json format and contain field "expressions" with input polish notation expressions array

Swagger is available by the following link: http://localhost:8080/swagger-ui.html
    









